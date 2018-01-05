package com.troy;

import com.troy.domain.entity.Permission;
import com.troy.domain.entity.Resource;
import com.troy.domain.entity.Role;
import com.troy.domain.entity.User;
import com.troy.repository.PermissionRepository;
import com.troy.repository.RoleRepository;
import com.troy.repository.UserRepository;
import com.troy.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.util.HashSet;
import java.util.Set;

@EnableTransactionManagement
@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public InitializingBean insertDefaultUsers() {
		return new InitializingBean() {

			@Value("${start.init.enabled}")
			private Boolean initEnable;

			@Autowired
			private RoleRepository roleRepository;

			@Autowired
			private UserService userService;

			@Autowired
			private PermissionRepository permissionRepository;

			@Override
			public void afterPropertiesSet() {
				if(initEnable){
					Permission  userList = addPermission("获取所有用户信息","userList","/api/users");

					Role adminRole = addRole("ROLE_ADMIN","管理员角色",userList);
					Role userRole = addRole("ROLE_USER","普通用户角色");

					addUser("admin", "admin",adminRole,userRole);
					addUser("user", "user");

				}
			}

			private Role addRole(String code, String name,Permission... permissions) {
				Role role = new Role();
				role.setCode(code);
				role.setName(name);
				Set<Permission> permissionSet = new HashSet<>();
				for( Permission permission:permissions){
					permissionSet.add(permission);
				}
				role.setPermissions(permissionSet);
			    return roleRepository.save(role);
			}


			private User addUser(String username, String password ,Role... roles) {
				User user = new User();
				user.setUsername(username);
				user.setPassword(new BCryptPasswordEncoder().encode(password));
				Set<Role> roleSet = new HashSet<>();
				for( Role role:roles){
					roleSet.add(role);
				}
				user.setRoles(roleSet);
				return userService.save(user,null);
			}

			private Permission addPermission(String name, String code,String address) {
				Permission permission = new Permission();
				permission.setCode(code);
				permission.setName(name);
				Resource resource  = new Resource();
				resource.setName(name);
				resource.setAddress(address);
				resource.setPermission(permission);
				permission.setResource(resource);
				return permissionRepository.save(permission);
			}
		};
	}


	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}
}

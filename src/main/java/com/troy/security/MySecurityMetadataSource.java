package com.troy.security;

import com.troy.domain.entity.Permission;
import com.troy.domain.entity.Resource;
import com.troy.domain.entity.Role;
import com.troy.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Athos on 2016-10-16.
 */
public class MySecurityMetadataSource  implements FilterInvocationSecurityMetadataSource {

    private ResourceService resourceService;

    private Map<String, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<>();

    public MySecurityMetadataSource(ResourceService resourceService){
        this.resourceService = resourceService;
        loadResourceDefine();
    }

    private void loadResourceDefine(){
        List<Resource> resourceList = resourceService.findAll();
        for (Resource resource : resourceList) {
            Set<ConfigAttribute> itemAttributes = new HashSet<>();
            Permission permission = resource.getPermission();
            Set<Role> roles = permission.getRoles();
            for (Role role : roles) {
                ConfigAttribute ca = new SecurityConfig(role.getAuthority());
                // 每一个请求资源对应的Role
                itemAttributes.add(ca);
                // 所有的Role对象
            }
            requestMap.put(resource.getAddress(), itemAttributes);
        }
    }
    /**
     * @param object 当前用户访问的受保护的资源
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        HttpServletRequest request=((FilterInvocation)object).getRequest();
        Iterator<String> ite = requestMap.keySet().iterator();
        while (ite.hasNext()){
            String resURL = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if(requestMatcher.matches(request)){
                return requestMap.get(resURL);
            }
        }
        return new ArrayList<ConfigAttribute>();
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {

        Set<ConfigAttribute> allAttributes = new HashSet<>();

        List<Resource> resourceList = resourceService.findAll();

        for (Resource resource : resourceList) {

            Set<ConfigAttribute> itemAttributes = new HashSet<>();

            Permission permission = resource.getPermission();

            Set<Role> roles = permission.getRoles();

            for (Role role : roles) {

                ConfigAttribute ca = new SecurityConfig(role.getAuthority());

                // 每一个请求资源对应的Role
                itemAttributes.add(ca);
                // 所有的Role对象
                allAttributes.add(ca);
            }
            requestMap.put(resource.getAddress(), itemAttributes);
        }
        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

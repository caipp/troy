package com.troy.web.rest;

import com.troy.domain.entity.User;
import com.troy.enums.ResultCode;
import com.troy.service.UserService;
import com.troy.utils.ApiResult;
import com.troy.utils.TokenUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ApiResult auth(@RequestParam String username,@RequestParam String password,Device device) {

        // Perform the authentication
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        UserDetails userDetails = this.userService.loadUserByUsername(username);
        String token = this.tokenUtils.generateToken(userDetails, device);
        return new ApiResult(ResultCode.SUCCESS,null,token);
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ApiResult refresh(HttpServletRequest request) {
        String token = request.getHeader(this.tokenHeader);
        String username = this.tokenUtils.getUsernameFromToken(token);
        User user = this.userService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
            String refreshedToken = this.tokenUtils.refreshToken(token);
            return new ApiResult(ResultCode.SUCCESS,null,refreshedToken);
        } else {
            return new ApiResult(ResultCode.INVALID_AUTHCODE);
        }
    }

}

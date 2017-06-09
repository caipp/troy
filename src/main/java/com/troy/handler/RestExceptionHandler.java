package com.troy.handler;

import com.troy.enums.ResultCode;
import com.troy.utils.ApiResult;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseBody
    public ApiResult BadCredentialsExceptionHandler(Exception e){
        return new ApiResult(ResultCode.LOGIN_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult defaultErrorHandler(Exception e){
        return new ApiResult(ResultCode.SYS_ERROR,e.getMessage());
    }

}

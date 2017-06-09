package com.troy.utils;

import com.troy.enums.ResultCode;
import org.springframework.util.StringUtils;

/**
 * Created by 12546 on 2016/10/22.
 */
public class ApiResult {

    private String code;
    private String message;
    private Object data;

    public ApiResult() {
        this.setCode(ResultCode.SUCCESS);
        this.setMessage(ResultCode.SUCCESS.msg());
    }

    public ApiResult(ResultCode code) {
        this.setCode(code);
        this.setMessage(code.msg());
    }

    public ApiResult(ResultCode code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public ApiResult(ResultCode code, String message, Object data) {
        this.setCode(code);
        if(StringUtils.isEmpty(message)){
            this.setMessage(code.msg());
        }else{
            this.setMessage(message);
        }

        this.setData(data);
    }

    public String getCode() {
        return code;
    }
    public void setCode(ResultCode code) {
        this.code = code.val();
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

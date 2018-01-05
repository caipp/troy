package com.troy.enums;

/**
 * @author troy
 * @version V1.0
 * @Description: TODO
 * @date 2017-10-27
 */
public enum OrderStatus {

    UNPAY("10", "未支付"),

    PAY_SUCCESS("20", "支付成功"),

    COMPLET("30","完成"),

    CLOSE("99", "关闭");

    private OrderStatus(String value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public String val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private String val;
    private String msg;


}

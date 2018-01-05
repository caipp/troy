package com.troy.enums;

/**
 * @author troy
 * @version V1.0
 * @Description: TODO
 * @date 2017-10-27
 */
public enum RecordType {

    OWNERQRCODEOPEN("ownerQrcodeOpen", "业主二维码开门"),

    VISITORQRCODEOPEN("visitorQrcodeOpen", "访客二维码"),

    NFCOPEN("nfcOpen","NFC开门"),

    REMOTEOPEN("remoteOpen", "远程开门");

    private RecordType(String value, String msg){
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

    public static RecordType toEnum(String value) {
        RecordType type = null;
        for(RecordType recordType: RecordType.values()){
            if(recordType.val().equalsIgnoreCase(value.trim())){
                type =  recordType;
            }
        }
        return type;
    }

}

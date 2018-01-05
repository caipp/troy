package com.troy.domain;

import com.troy.domain.entity.Record;
import com.troy.enums.RecordType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-19
 */
public class NoticeMsg {

    private String noticeName;

    private Msg msg;

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }

    public class Msg {

        private String lingLingId;

        private String deviceCode;

        private Date openTime;

        private String deviceId;

        private String qrcodeKey;

        private String cardUid;

        private String status;

        public String getLingLingId() {
            return lingLingId;
        }

        public void setLingLingId(String lingLingId) {
            this.lingLingId = lingLingId;
        }

        public String getDeviceCode() {
            return deviceCode;
        }

        public void setDeviceCode(String deviceCode) {
            this.deviceCode = deviceCode;
        }

        public Date getOpenTime() {
            return openTime;
        }

        public void setOpenTime(Date openTime) {
            this.openTime = openTime;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getQrcodeKey() {
            return qrcodeKey;
        }

        public void setQrcodeKey(String qrcodeKey) {
            this.qrcodeKey = qrcodeKey;
        }

        public String getCardUid() {
            return cardUid;
        }

        public void setCardUid(String cardUid) {
            this.cardUid = cardUid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

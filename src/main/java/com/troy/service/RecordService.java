package com.troy.service;

import com.troy.domain.NoticeMsg;
import com.troy.domain.entity.*;
import com.troy.enums.RecordType;
import com.troy.repository.RecordRepository;
import com.troy.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author caipiaoping
 * @date 2016/10/22
 */
@Service
public class RecordService extends BaseService<Record> {

    @Autowired
    private RecordRepository recordRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private CardService cardService;

    @Override
    protected RecordRepository getRepository() {
        return recordRepo;
    }

    public void callback(NoticeMsg noticeMsg) {
        RecordType recordType = RecordType.toEnum(noticeMsg.getNoticeName());
        NoticeMsg.Msg  msg = noticeMsg.getMsg();
        Record record = new Record();
        record.setOpenTime(msg.getOpenTime());
        Device device = deviceService.findByCode(msg.getDeviceId());
        record.setDevice(device);
        record.setType(recordType);
        switch (recordType){
            case OWNERQRCODEOPEN:
                User user = userService.getUserByLinglingId(msg.getLingLingId());
                if(user !=null ){
                    record.setUser(user);
                    save(record,null);
                }
                break;
            case NFCOPEN:
                Card card = cardService.findByCode(msg.getCardUid());
                if(card != null){
                    record.setUser(card.getUser());
                    save(record,null);
                }
                break;
            case VISITORQRCODEOPEN:
                Visitor visitor = visitorService.getVisitorByQrCodeKey(msg.getQrcodeKey());
                if(visitor != null){
                    record.setUser(visitor.getUser());
                    record.setVisitor(visitor);
                    save(record,null);
                }
                break;
            case REMOTEOPEN:
                break;
            default:
                break;
        }

    }
}

package cn.edu.xmu.user.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CouponRecordDTO {
    private String id;
    private String metaId;
    private String userId;
    /**
     * 订单号：仅在使用时才和订单关联
     */
    private String businessId;
    /**
     * 券状态：0-未使用，1-已使用，2-已过期，3-已冻结
     */
    private String status;
    private Date receiveTime;
    private Date endTime;
    private Date consumeTime;


}

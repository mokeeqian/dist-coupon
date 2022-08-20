package cn.edu.xmu.voucher.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author mokeeqian
 * @email mokeeqian@gmail.com
 * @date 2022-08-20 00:14:03
 */
@Data
@TableName("tb_coupon_record")
public class CouponRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 卷领取记录ID
	 */
	@TableId
	private String id;
	/**
	 * 卷模板ID
	 */
	private String metaId;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 订单号
	 */
	private String businessId;
	/**
	 * 券状态：0-未使用，1-已使用，2-已过期，3-已冻结
	 */
	private Integer status;
	/**
	 * 领取时间
	 */
	private Date receiveTime;
	/**
	 * 截止日期
	 */
	private Date validTime;
	/**
	 * 消费时间
	 */
	private Date consumeTime;

}

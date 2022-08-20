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
@TableName("tb_coupon_meta")
public class CouponMetaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 卷模板ID
	 */
	@TableId
	private String id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 类型：满减、直减、折扣
	 */
	private String type;
	/**
	 * 有效开始时间
	 */
	private Date validStartTime;
	/**
	 * 有效截止时间
	 */
	private Date validEndTime;
	/**
	 * 状态：0-不可用， 1-可用
	 */
	private Integer status;
	/**
	 * 库存量
	 */
	private Integer totalStock;
	/**
	 * 领取规则
	 */
	private String receiveRule;
	/**
	 * 消费规则：0-满减 1-直降，2-折扣
	 */
	private String consumeRule;

}

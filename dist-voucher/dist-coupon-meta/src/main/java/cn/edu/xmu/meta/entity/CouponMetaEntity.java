package cn.edu.xmu.meta.entity;

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
 * @date 2022-08-23 12:01:05
 */
@Data
@TableName("tb_coupon_meta")
public class CouponMetaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 券模板ID
	 */
	@TableId
	private String id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 类型：01-满减、02-直减、03折扣
	 */
	private String type;
	/**
	 * 领取开始时间
	 */
	private Date receiveStartTime;
	/**
	 * 领取截止时间
	 */
	private Date receiveEndTime;
	/**
	 * 消费开始时间
	 */
	private Date conumeStartTime;
	/**
	 * 消费截止时间
	 */
	private Date conumeEndTime;
	/**
	 * 状态：0-不可用， 1-可用
	 */
	private Integer status;
	/**
	 * 库存量
	 */
	private Long totalStock;
	/**
	 * 领取规则
	 */
	private String receiveRule;
	/**
	 * 消费规则
	 */
	private String consumeRule;

}

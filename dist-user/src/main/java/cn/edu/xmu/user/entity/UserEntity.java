package cn.edu.xmu.user.entity;

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
 * @date 2022-08-20 16:07:12
 */
@Data
@TableName("tb_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分布式ID 类型+年份+雪花ID
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 账户类型：01-个人账户 02-个人定期 03-对公户
	 */
	private String type;
	/**
	 * 创建年份
	 */
	private String year;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

}

# dist-coupon
大流量、高并发、分布式优惠券平台

## 系统架构

![系统架构](pics/arch.png)

## 数据模型
uer_id 采用 账户类型 + 创立年份 + 雪花ID 设计

用户库表进行水平拆分，其中，按账户类型分库；按账户创立年份分表
![用户库表](pics/tb_user.png)

![优惠券库表](pics/db_coupon.png)

## 系统设计


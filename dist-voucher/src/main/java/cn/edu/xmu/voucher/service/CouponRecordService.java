package cn.edu.xmu.voucher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.xmu.common.utils.PageUtils;
import cn.edu.xmu.voucher.entity.CouponRecordEntity;

import java.util.Map;

/**
 * 
 *
 * @author mokeeqian
 * @email mokeeqian@gmail.com
 * @date 2022-08-20 00:14:03
 */
public interface CouponRecordService extends IService<CouponRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


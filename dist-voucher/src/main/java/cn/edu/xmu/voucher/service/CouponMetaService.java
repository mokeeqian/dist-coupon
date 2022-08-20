package cn.edu.xmu.voucher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.xmu.common.utils.PageUtils;
import cn.edu.xmu.voucher.entity.CouponMetaEntity;

import java.util.Map;

/**
 * 
 *
 * @author mokeeqian
 * @email mokeeqian@gmail.com
 * @date 2022-08-20 00:14:03
 */
public interface CouponMetaService extends IService<CouponMetaEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


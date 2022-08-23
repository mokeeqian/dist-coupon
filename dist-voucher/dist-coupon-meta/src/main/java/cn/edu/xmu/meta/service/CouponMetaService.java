package cn.edu.xmu.meta.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.xmu.common.utils.PageUtils;
import cn.edu.xmu.meta.entity.CouponMetaEntity;

import java.util.Map;

/**
 * 
 *
 * @author mokeeqian
 * @email mokeeqian@gmail.com
 * @date 2022-08-23 12:01:05
 */
public interface CouponMetaService extends IService<CouponMetaEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


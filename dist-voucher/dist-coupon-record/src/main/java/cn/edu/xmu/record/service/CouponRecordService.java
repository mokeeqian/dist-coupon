package cn.edu.xmu.record.service;

import cn.edu.xmu.common.utils.R;
import cn.edu.xmu.record.param.RecordParam;
import cn.edu.xmu.user.model.dto.CouponRecordDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.xmu.common.utils.PageUtils;
import cn.edu.xmu.record.entity.CouponRecordEntity;

import java.util.Map;

/**
 * 
 *
 * @author mokeeqian
 * @email mokeeqian@gmail.com
 * @date 2022-08-23 12:08:21
 */
public interface CouponRecordService extends IService<CouponRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


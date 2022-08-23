package cn.edu.xmu.record.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xmu.common.utils.PageUtils;
import cn.edu.xmu.common.utils.Query;

import cn.edu.xmu.record.dao.CouponRecordDao;
import cn.edu.xmu.record.entity.CouponRecordEntity;
import cn.edu.xmu.record.service.CouponRecordService;


@Service("couponRecordService")
public class CouponRecordServiceImpl extends ServiceImpl<CouponRecordDao, CouponRecordEntity> implements CouponRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponRecordEntity> page = this.page(
                new Query<CouponRecordEntity>().getPage(params),
                new QueryWrapper<CouponRecordEntity>()
        );

        return new PageUtils(page);
    }

}
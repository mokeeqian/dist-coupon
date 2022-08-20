package cn.edu.xmu.voucher.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xmu.common.utils.PageUtils;
import cn.edu.xmu.common.utils.Query;

import cn.edu.xmu.voucher.dao.CouponMetaDao;
import cn.edu.xmu.voucher.entity.CouponMetaEntity;
import cn.edu.xmu.voucher.service.CouponMetaService;


@Service("couponMetaService")
public class CouponMetaServiceImpl extends ServiceImpl<CouponMetaDao, CouponMetaEntity> implements CouponMetaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponMetaEntity> page = this.page(
                new Query<CouponMetaEntity>().getPage(params),
                new QueryWrapper<CouponMetaEntity>()
        );

        return new PageUtils(page);
    }

}
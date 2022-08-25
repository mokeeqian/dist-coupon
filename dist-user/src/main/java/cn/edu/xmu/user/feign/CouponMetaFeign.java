package cn.edu.xmu.user.feign;

import cn.edu.xmu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 用户领取优惠券，检查券模板库存
 */
@FeignClient(name = "dist-coupon-meta", path = "voucher/meta", contextId = "CouponMetaFeign")
public interface CouponMetaFeign {

    @PostMapping("/getStock")
    R getStock(@RequestBody Map<String, Object> params);

    @PostMapping("/subStock")
    R subStock(@RequestBody Map<String, Object> params);
}

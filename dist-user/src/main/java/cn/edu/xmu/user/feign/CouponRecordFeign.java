package cn.edu.xmu.user.feign;

import cn.edu.xmu.common.utils.R;
import cn.edu.xmu.user.model.dto.CouponRecordDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "dist-coupon-record", path = "voucher/record", contextId = "CouponRecordFeign")
public interface CouponRecordFeign {

    @PostMapping("/addRecord")
    R addRecord(@RequestBody CouponRecordDTO couponRecordDTO);
}

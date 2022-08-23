package cn.edu.xmu.record.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.xmu.record.entity.CouponRecordEntity;
import cn.edu.xmu.record.service.CouponRecordService;
import cn.edu.xmu.common.utils.PageUtils;
import cn.edu.xmu.common.utils.R;



/**
 * 
 *
 * @author mokeeqian
 * @email mokeeqian@gmail.com
 * @date 2022-08-23 12:08:21
 */
@RestController
@RequestMapping("voucher/record/")
public class CouponRecordController {
    @Autowired
    private CouponRecordService couponRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("record:couponrecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("record:couponrecord:info")
    public R info(@PathVariable("id") String id){
		CouponRecordEntity couponRecord = couponRecordService.getById(id);

        return R.ok().put("couponRecord", couponRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("record:couponrecord:save")
    public R save(@RequestBody CouponRecordEntity couponRecord){
		couponRecordService.save(couponRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("record:couponrecord:update")
    public R update(@RequestBody CouponRecordEntity couponRecord){
		couponRecordService.updateById(couponRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("record:couponrecord:delete")
    public R delete(@RequestBody String[] ids){
		couponRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

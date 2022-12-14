package cn.edu.xmu.voucher.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.xmu.voucher.entity.CouponMetaEntity;
import cn.edu.xmu.voucher.service.CouponMetaService;
import cn.edu.xmu.common.utils.PageUtils;
import cn.edu.xmu.common.utils.R;



/**
 * 
 *
 * @author mokeeqian
 * @email mokeeqian@gmail.com
 * @date 2022-08-20 00:14:03
 */
@RestController
@RequestMapping("voucher/couponmeta")
public class CouponMetaController {
    @Autowired
    private CouponMetaService couponMetaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("voucher:couponmeta:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponMetaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("voucher:couponmeta:info")
    public R info(@PathVariable("id") String id){
		CouponMetaEntity couponMeta = couponMetaService.getById(id);

        return R.ok().put("couponMeta", couponMeta);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("voucher:couponmeta:save")
    public R save(@RequestBody CouponMetaEntity couponMeta){
		couponMetaService.save(couponMeta);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("voucher:couponmeta:update")
    public R update(@RequestBody CouponMetaEntity couponMeta){
		couponMetaService.updateById(couponMeta);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("voucher:couponmeta:delete")
    public R delete(@RequestBody String[] ids){
		couponMetaService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

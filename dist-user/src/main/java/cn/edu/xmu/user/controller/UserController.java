package cn.edu.xmu.user.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import cn.edu.xmu.user.feign.CouponMetaFeign;
import cn.edu.xmu.user.feign.CouponRecordFeign;
import cn.edu.xmu.user.model.param.AccountParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.edu.xmu.user.entity.UserEntity;
import cn.edu.xmu.user.service.UserService;
import cn.edu.xmu.common.utils.PageUtils;
import cn.edu.xmu.common.utils.R;



/**
 * 
 *
 * @author mokeeqian
 * @email mokeeqian@gmail.com
 * @date 2022-08-20 16:07:12
 */
@RestController
@RequestMapping("user/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CouponMetaFeign couponMetaFeign;

    @Autowired
    private CouponRecordFeign couponRecordFeign;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("user:user:info")
    public R info(@PathVariable("id") String id){
		UserEntity user = userService.findById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("user:user:save")
    public R save(@Validated  @RequestBody AccountParam accountParam){
        userService.addAccount(accountParam);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:user:update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("user:user:delete")
    public R delete(@RequestBody String[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    // 测试远程调用 couponMetaFeign，获取优惠券模板库存
    @GetMapping("/test")
    public R test() {
        Map<String, Object> param = new HashMap<>();
        param.put("metaId", "6317836172678312");
        R stock = couponMetaFeign.subStock(param);

        return R.ok().put("stock", stock);
    }


}

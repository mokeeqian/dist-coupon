package cn.edu.xmu.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.xmu.common.utils.PageUtils;
import cn.edu.xmu.user.entity.UserEntity;

import java.util.Map;

/**
 * 
 *
 * @author mokeeqian
 * @email mokeeqian@gmail.com
 * @date 2022-08-20 16:07:12
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


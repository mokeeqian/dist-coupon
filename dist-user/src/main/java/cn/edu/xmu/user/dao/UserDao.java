package cn.edu.xmu.user.dao;

import cn.edu.xmu.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author mokeeqian
 * @email mokeeqian@gmail.com
 * @date 2022-08-20 16:07:12
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}

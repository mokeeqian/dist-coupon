package cn.edu.xmu.user.service.impl;

import cn.edu.xmu.common.utils.BeanUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xmu.common.utils.PageUtils;
import cn.edu.xmu.common.utils.Query;

import cn.edu.xmu.user.dao.UserDao;
import cn.edu.xmu.user.entity.UserEntity;
import cn.edu.xmu.user.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    private static Map<String, String> typeMap = Maps.newHashMap();

    private static List<String> typeList = Arrays.asList("01", "02", "03");
    private static List<String> yearList = Arrays.asList("2020", "2021", "2022");

    static {
        typeMap.put("01", "个人活期");
        typeMap.put("02", "个人定期");
        typeMap.put("03", "对公户");
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        // TODO: 分库分表下，分页查询
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        String type = (String) params.get("type"), year = (String) params.get("year");
        if (StringUtils.isEmpty(type)) {
            wrapper.in(UserEntity::getType, typeList);
        } else {
            wrapper.eq(UserEntity::getType, type);
        }
        if ( StringUtils.isEmpty(year) ) {
            wrapper.in(UserEntity::getYear, yearList);
        } else {
            wrapper.eq(UserEntity::getYear, year);
        }

        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public UserEntity findById(String id) {
        if ( !checkAccount(id) ) throw new RuntimeException("账户类型非法");
        UserEntity rst = this.find(id);
        if ( null == rst ) throw new RuntimeException("账户不存在");
        rst.setType(typeMap.get(rst.getType()));
        return BeanUtils.toBean(rst, UserEntity.class);
    }

    private UserEntity find(String id) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        // 先查账户类型，路由到不同的库
        wrapper.eq(UserEntity::getType, id.substring(0, 2));
        // 再查年份，路由到不同的表
        wrapper.eq(UserEntity::getYear, id.substring(2, 6));
        wrapper.eq((UserEntity::getId), id);
        return this.baseMapper.selectOne(wrapper);
    }

    private boolean checkAccount(String id) {
        if ( StringUtils.isEmpty(id) || id.length() != 24 ) return false;
        if ( !typeMap.containsKey(id.substring(0, 2)) ) return false;
        return yearList.contains(id.substring(2, 6));
    }

}
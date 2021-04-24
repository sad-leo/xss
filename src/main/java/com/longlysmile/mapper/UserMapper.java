package com.longlysmile.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longlysmile.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper 接口
 *
 * @author wujie
 * @since 2020-10-25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

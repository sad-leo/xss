package com.longlysmile.service.impl;

import com.longlysmile.entity.User;
import com.longlysmile.mapper.UserMapper;
import com.longlysmile.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author wujie
 * @since 2020-10-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

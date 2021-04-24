package com.longlysmile.service.impl;

import com.longlysmile.entity.Blog;
import com.longlysmile.mapper.BlogMapper;
import com.longlysmile.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author wujie
 * @since 2020-10-25
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}

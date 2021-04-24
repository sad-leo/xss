package com.longlysmile.mapper;

import com.longlysmile.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper 接口
 *
 * @author wujie
 * @since 2020-10-25
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

}

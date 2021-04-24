package com.longlysmile.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longlysmile.common.lang.Result;
import com.longlysmile.entity.Blog;
import com.longlysmile.service.BlogService;
import com.longlysmile.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 博客控制器
 *
 * @author wujie
 * @since 2020-10-25
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    BlogService blogService;

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success(pageData);
    }

    @GetMapping("/info/{id}")
    public Result info(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        return StringUtils.isEmpty(blog) ? Result.fail("文章不存在") : Result.success(blog);
    }

    @RequiresAuthentication
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Blog blog) {
        blog.setUserId(ShiroUtil.getProfile().getId());
        blog.setCreated(LocalDateTime.now());
        return blogService.save(blog) ? Result.success("添加成功") : Result.fail("添加失败");
    }

    @RequiresAuthentication
    @PutMapping("/edit")
    public Result edit(@RequestBody Blog blog) {
        if (StringUtils.isEmpty(blog.getId())) {
            return Result.fail("主键不存在，无法更新");
        }
        Blog temp = blogService.getById(blog.getId());
        if (temp.getUserId().longValue() != ShiroUtil.getProfile().getId().longValue()) {
            return Result.fail("无权限修改其他人创建的文章");
        }
        return blogService.updateById(blog) ? Result.success("更新成功") : Result.fail("更新失败");
    }

    @RequiresAuthentication
    @DeleteMapping("/delete")
    public Result delete(@RequestBody Blog blog) {
        return blogService.removeById(blog) ? Result.success("删除成功") : Result.fail("删除失败");
    }

}

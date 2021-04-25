package com.longlysmile.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.longlysmile.common.lang.Result;
import com.longlysmile.entity.Blog;
import com.longlysmile.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author wujie
 * @version 1.0
 * @date 2021/4/24 17:03
 */
@Controller
public class IndexController {

    @Resource
    BlogService blogService;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
    @RequestMapping("/head")
    public ModelAndView head() {
        return new ModelAndView("head");
    }

    @RequestMapping("/reflection")
    public ModelAndView reflection(String param) {
        ModelAndView reflection = new ModelAndView("reflection");
        reflection.addObject("key",param);
        return reflection;
    }

    @RequestMapping("/storage")
    public ModelAndView storage() {
        ModelAndView reflection = new ModelAndView("storage");
        List<Blog> list = Lists.newArrayList();
        list.add(new Blog("标题1","摘要1","内容1"));
        list.add(new Blog("标题2","摘要2","内容2"));
        list.add(new Blog("标题3","摘要3","内容3"));
        reflection.addObject("blogList",list);
        return reflection;
    }



}

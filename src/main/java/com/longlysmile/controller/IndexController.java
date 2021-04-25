package com.longlysmile.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.longlysmile.common.lang.Result;
import com.longlysmile.entity.Blog;
import com.longlysmile.service.BlogService;
import com.longlysmile.util.XssFilter;
import com.sun.xml.internal.txw2.output.IndentingXMLFilter;
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

    public static int xssStatus = 0;

    @Resource
    BlogService blogService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView index = new ModelAndView("index");
        index.addObject("xssStatus", xssStatus);
        return index;
    }

    @RequestMapping("/head")
    public ModelAndView head() {
        return new ModelAndView("head");
    }

    @RequestMapping("/reflection")
    public ModelAndView reflection(String param) {
        ModelAndView reflection = new ModelAndView("reflection");
        reflection.addObject("key", param);
        return reflection;
    }

    @RequestMapping("addBlog")
    public ModelAndView addBlog(Blog blog) {
        blogService.save(blog);
        return new ModelAndView("redirect:/storage");
    }

    @RequestMapping("/storage")
    public ModelAndView storage() {
        ModelAndView storage = new ModelAndView("storage");
        List<Blog> list = blogService.list();
        if(xssStatus == 1){
            list.forEach(item ->{
                item.setTitle(XssFilter.transform(item.getTitle()));
                item.setDescription(XssFilter.transform(item.getDescription()));
                item.setContent(XssFilter.transform(item.getContent()));
            });
        }
        storage.addObject("blogList", list);
        storage.addObject("xssStatus", xssStatus);
        return storage;
    }

    @RequestMapping("/filter")
    public ModelAndView filter() {
        ModelAndView index = new ModelAndView("index");
        xssStatus = xssStatus == 0 ? 1 : 0;
        index.addObject("xssStatus", xssStatus);
        return index;
    }


}

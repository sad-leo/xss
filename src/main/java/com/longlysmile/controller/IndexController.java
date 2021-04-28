package com.longlysmile.controller;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longlysmile.common.lang.Result;
import com.longlysmile.config.GlobalParameters;
import com.longlysmile.entity.AttackRecord;
import com.longlysmile.entity.Blog;
import com.longlysmile.entity.User;
import com.longlysmile.service.BlogService;
import com.longlysmile.service.impl.AttackRecordServiceImpl;
import com.longlysmile.service.impl.UserServiceImpl;
import com.longlysmile.util.XssFilter;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
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

    public static String USERNAME = "username";
    public static String USER = "user";

    public static String XSS_STORAGE = "存储型";
    public static String XSS_REFLECTION = "反射型";


    @Resource
    private BlogService blogService;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private AttackRecordServiceImpl attackRecordService;

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

    @RequestMapping("/wordcloud")
    public ModelAndView wordcloud() {
        return new ModelAndView("wordcloud");
    }

    @RequestMapping("/reflection")
    public ModelAndView reflection(String param) {
        if (XssFilter.checkXss(param)) {
            attackRecordService.saveAndSend(new AttackRecord(XSS_REFLECTION, param, LocalDateTime.now()));
        }
        ModelAndView reflection = new ModelAndView("reflection");
        reflection.addObject("key", param);
        return reflection;
    }

    @RequestMapping("addBlog")
    public ModelAndView addBlog(Blog blog) throws IllegalAccessException {
        Field[] declaredFields = Blog.class.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if ("java.lang.String".equals(field.getType().getName())) {
                String str = (String) field.get(blog);
                if (XssFilter.checkXss(str)) {
                    attackRecordService.saveAndSend(new AttackRecord(XSS_STORAGE, str, LocalDateTime.now()));
                }
            }
        }
        blogService.save(blog);
        return new ModelAndView("redirect:/storage");
    }

    @RequestMapping("/storage")
    public ModelAndView storage() {
        ModelAndView storage = new ModelAndView("storage");
        List<Blog> list = blogService.list();
        if (xssStatus == 1) {
            list.forEach(item -> {
                item.setTitle(XssFilter.transform(item.getTitle()));
                item.setDescription(XssFilter.transform(item.getDescription()));
                item.setContent(XssFilter.transform(item.getContent()));
            });
        }
        storage.addObject("blogList", list);
        storage.addObject("xssStatus", xssStatus);
        return storage;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    @ResponseBody
    public Integer filter(Integer status) {
        xssStatus = status;
        return xssStatus;
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ModelAndView list(String account, String pwd, HttpSession session) {
        System.out.println(account + " " + pwd);
        User one = userService.getOne(new QueryWrapper<User>().eq("username", account).eq("password", SecureUtil.md5(pwd)));
        if (ObjectUtils.isEmpty(one)) {
            return new ModelAndView("/error/401");
        }
        session.setAttribute(USERNAME, account);
        session.setAttribute(USER, one);
        GlobalParameters.RECIPIENT = one.getEmail();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("email", one.getEmail());
        return modelAndView;
    }

    @RequestMapping(value = "logout")
    public ModelAndView list(HttpSession session) {
        session.removeAttribute(USERNAME);
        session.removeAttribute(USER);
        return new ModelAndView("login");
    }

    @RequestMapping(value = "record", method = RequestMethod.POST)
    @ResponseBody
    public List<AttackRecord> getRecord(String type, String content) {
        QueryWrapper<AttackRecord> queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(type)) {
            queryWrapper.like("type", type);
        }
        if (!StringUtils.isEmpty(content)) {
            queryWrapper.like("content", content);
        }
        return attackRecordService.list(queryWrapper);
    }

    @RequestMapping(value = "save/email", method = RequestMethod.POST)
    @ResponseBody
    public Result saveEmail(String email, HttpSession session) {
        if (StringUtils.isEmpty(email)) {
            return Result.fail("邮件不能为空");
        }
        User user = (User) session.getAttribute(USER);
        User u = new User();
        u.setId(user.getId());
        u.setEmail(email);
        userService.updateById(u);
        GlobalParameters.RECIPIENT = email;
        return Result.success();
    }

}

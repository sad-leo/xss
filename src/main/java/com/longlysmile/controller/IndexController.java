package com.longlysmile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * TODO
 *
 * @author wujie
 * @version 1.0
 * @date 2021/4/24 17:03
 */
@Controller
public class IndexController {

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
}

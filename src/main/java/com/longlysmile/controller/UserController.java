package com.longlysmile.controller;


import com.longlysmile.common.lang.Result;
import com.longlysmile.entity.User;
import com.longlysmile.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户控制器
 *
 * @author wujie
 * @since 2020-10-25
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    UserService userService;


    @RequiresAuthentication
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id") long id) {

        System.out.println("asdas");
        return Result.success(userService.getById(id));
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        return Result.success(user.toString());
    }


}

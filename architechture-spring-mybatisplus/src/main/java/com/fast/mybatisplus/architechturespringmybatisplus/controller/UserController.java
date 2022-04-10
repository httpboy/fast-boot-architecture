package com.fast.mybatisplus.architechturespringmybatisplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fast.mybatisplus.architechturespringmybatisplus.model.User;
import com.fast.mybatisplus.architechturespringmybatisplus.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author boyi.chen
 * @since 2022-01-09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("add")
    @ResponseBody
    public Object add(@RequestBody User user) {
        user.setCreateUser("boyi.chen");
        user.setCreateTime(new Date());
        boolean save = userService.save(user);
        return save;
    }

    /**
     * @Description: 获取用户列表
     */
    @PostMapping("list")
    @ResponseBody
    public Object list() throws ParseException {
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getId, "10");
//        wrapper.eq(User::getCreateTime,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-04-10 23:37:03"));
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.gt(User::getCreateTime, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-04-10 23:36:48"));
        wrapper.lt(User::getCreateTime, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-04-11 23:37:08"));

        return userService.list(wrapper);
    }
}

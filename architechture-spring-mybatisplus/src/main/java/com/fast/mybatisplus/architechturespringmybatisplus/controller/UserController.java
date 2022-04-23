package com.fast.mybatisplus.architechturespringmybatisplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fast.mybatisplus.architechturespringmybatisplus.model.User;
import com.fast.mybatisplus.architechturespringmybatisplus.service.impl.UserServiceImpl;
import lombok.SneakyThrows;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    @SneakyThrows
    @GetMapping("add")
    @ResponseBody
    public Object add() {
        List<Date> dates = Arrays.asList(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-10-15 23:08:07"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-11-15 23:08:07"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-12-15 23:08:07"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-01-15 23:08:07"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-02-15 23:08:07")
        );

        int nextInt = new Random().nextInt(50);
        User user = new User();
        user.setUserName("陈博易" + nextInt);
        user.setUserPass(nextInt + "");
        user.setCreateUser("boyi.chen");
        user.setCreateTime(dates.get(new Random().nextInt(3)));
        boolean save = userService.save(user);

        return true;
    }

    /**
     * @Description: 获取用户列表
     */
    @GetMapping("list")
    @ResponseBody
    public Object list() throws ParseException {
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getId, "10");
//        wrapper.eq(User::getCreateTime,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-04-10 23:37:03"));
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(User::getCreateTime, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-04-15 23:08:07"));
        wrapper.le(User::getCreateTime, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-05-16 23:08:31"));


        IPage pageResult = userService.page(new Page<>(1, 40), wrapper);

        return pageResult;
    }
}

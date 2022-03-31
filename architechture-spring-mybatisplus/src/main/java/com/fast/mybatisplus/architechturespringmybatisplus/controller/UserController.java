package com.fast.mybatisplus.architechturespringmybatisplus.controller;


import com.fast.mybatisplus.architechturespringmybatisplus.model.User;
import com.fast.mybatisplus.architechturespringmybatisplus.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *  前端控制器
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
    public Object add(@RequestBody User user){
        user.setCreateUser("boyi.chen");
        boolean save = userService.save(user);
        return save;
    }
}

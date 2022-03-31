package com.fast.mybatisplus.architechturespringmybatisplus.service.impl;

import com.fast.mybatisplus.architechturespringmybatisplus.model.User;
import com.fast.mybatisplus.architechturespringmybatisplus.mapper.UserMapper;
import com.fast.mybatisplus.architechturespringmybatisplus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author boyi.chen
 * @since 2022-01-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

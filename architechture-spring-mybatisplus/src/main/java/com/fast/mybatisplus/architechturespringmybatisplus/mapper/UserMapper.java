package com.fast.mybatisplus.architechturespringmybatisplus.mapper;

import com.fast.mybatisplus.architechturespringmybatisplus.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author boyi.chen
 * @since 2022-01-09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

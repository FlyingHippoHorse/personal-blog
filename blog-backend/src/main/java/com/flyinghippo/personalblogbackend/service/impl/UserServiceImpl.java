package com.flyinghippo.personalblogbackend.service.impl;

import com.flyinghippo.personalblogbackend.entity.User;
import com.flyinghippo.personalblogbackend.mapper.UserMapper;
import com.flyinghippo.personalblogbackend.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FlyHippo
 * @since 2024-04-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

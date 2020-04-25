package com.shizhichao.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shizhichao.user.mapper.UserMapper;
import com.shizhichao.user.service.UserService;
import com.shizhichao.userInterface.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



}

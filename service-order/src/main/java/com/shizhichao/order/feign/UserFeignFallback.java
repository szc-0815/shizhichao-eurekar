package com.shizhichao.order.feign;


import com.shizhichao.userInterface.User;
import org.springframework.stereotype.Component;

@Component
public class UserFeignFallback implements  UserFeignClient {


    @Override
    public User getUserById(Integer id) {
        return new User();
    }

    @Override
    public String getUsernameById(Integer id) {
        return "服务失败"+id;
    }


    @Override
    public User getUserByUser(User user) {
        User u=new User();
        u.setUsername("固定值");

        return u;
    }


}

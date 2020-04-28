package com.shizhichao.demo;


import com.shizhichao.demo.dao.UserDao;
import com.shizhichao.demo.entity.UserEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void save(){
        //添加
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("张5555555");
        userEntity.setPassword("zanggdd");
        userEntity.setSex(1);
        UserEntity user = userDao.save(userEntity);
        System.out.println(user);
        //修改
        user.setCreateTime(new Date());
        userDao.save(user);
    }

    @Test
    public void findById(){
        UserEntity user = userDao.findById(2);
        System.out.println(user);
    }

    @Test
    public void delete(){
        //如果删除的对象Entity不存在，会有异常
        userDao.deleteById(2);
    }

    @Test
    public void find(){
        List<UserEntity> all = userDao.findAll();
        System.out.println(all);
        //模糊查询,需要添加“%”
        List<UserEntity> likeResult = userDao.findByUsername("5");
        System.out.println(likeResult);
    }


}

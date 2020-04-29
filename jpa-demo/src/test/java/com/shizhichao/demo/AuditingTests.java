package com.shizhichao.demo;


import com.shizhichao.demo.entity.RoleEntity;
import com.shizhichao.demo.respository.RoleRepository;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuditingTests {
    @Autowired
    private RoleRepository roleRepository;

    @Test
//    @Transactional
    public void test(){
        //添加
       /* RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("test");
        RoleEntity role = roleRepository.save(roleEntity);*/
        //修改
        roleRepository.updateNameById(4,"运营");
    }
}

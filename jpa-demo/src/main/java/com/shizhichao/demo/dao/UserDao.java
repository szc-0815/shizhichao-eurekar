package com.shizhichao.demo.dao;

import com.shizhichao.demo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository 持久层注解
 */
@Repository
public class UserDao {
    @Autowired
    private EntityManager entityManager;


    //需要加Transactional的事务注解
    @Transactional
    public UserEntity save(UserEntity userEntity){
        UserEntity user = entityManager.merge(userEntity);
        return user;
    }

    // 按主键查询
    public UserEntity findById(Integer id){
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        return userEntity;
    }
    //按Id删除 ,删除对象，先查询，删除的对象是受Jpa管理的对象
    @Transactional
    public void deleteById(Integer id){
        UserEntity userEntity = findById(id);
        entityManager.remove(userEntity);
    }


    public List<UserEntity> findAll(){
        Query query = entityManager.createQuery("from UserEntity u");
        return query.getResultList();
    }


    //用户名称模糊查询
    public List<UserEntity> findByUsername(String username){
        Query query = entityManager.createQuery("from UserEntity u where u.username like :username");
        //拼接%，实现模糊查询
        String likeStr = "%".concat(username).concat("%");
        query.setParameter("username",likeStr);
        return query.getResultList();
    }
}

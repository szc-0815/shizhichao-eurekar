package com.shizhichao.demo.respository;


import com.shizhichao.demo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {


    //自定义方法，是以findBy开始的方法
    List<UserEntity> findByUsername(String username);
    //模糊查询
    List<UserEntity> findByUsernameLike(String username);
    //排序
    List<UserEntity> findByUsernameLikeOrderByIdDesc(String username);

    //分页，分页的时候可以指定排序条件
    Page<UserEntity> findByUsername(String username, Pageable pageable);
    //排序
    //List<UserEntity> findByUsername(String username, Sort sort);







    //自定义删除方法
    @Transactional
    int deleteByUsername(String username);
    @Transactional
    int deleteByUsernameAndSex(String username,Integer sex);
    @Transactional
    int deleteBySexIsNull();

    //注解方法
    //?1,?2是方法的参数顺序
    @Query("select u from UserEntity u where u.username=?1 and u.password=?2")
    List<UserEntity> queryByUsernameAndPassword(String username,String password);
    //原生SQL
    @Query(value = "select * from t_user where username=?1 and password=?2",nativeQuery = true)
    List<UserEntity> selectByUsernameAndPassword(String username,String password);
    //Param参数注解
    @Query("select u from UserEntity u where u.username=:uname and u.password=:pwd")
    List<UserEntity> queryByUsernameAndPasswordByParam(@Param("uname") String username, @Param("pwd") String password);
    //Param参数注解,分页排序
    @Query("select u from UserEntity u where u.username=:uname and u.password=:pwd")
    List<UserEntity> queryByUsernameAndPasswordByParam(@Param("uname") String username,@Param("pwd") String password,Pageable pageable);
    //通过Query注解，更新表字段
    @Modifying
    @Transactional
    @Query("update UserEntity u set u.password=:pwd where id=:id")
    int updatePsswordById(@Param("id") Integer id,@Param("pwd") String pwd);







    Page<UserEntity> findAll(Specification<UserEntity> specification, Pageable pageable);
}

package com.shizhichao.demo;



import com.shizhichao.demo.entity.UserEntity;
import com.shizhichao.demo.respository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRespositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save(){

        //单个的添加
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("骚博");
        userEntity.setPassword("123");
        userEntity.setSex(1);
        userEntity.setCreateTime(new Date());
        //insert或update，如果主键Id有值，就修改操作，否则就新增。
        UserEntity u = userRepository.save(userEntity);






        //        进行批量保存，添加多个
        List<UserEntity> userlist = new ArrayList<>();
        UserEntity u1 = new UserEntity();
        u1.setUsername("张恒");
        u1.setPassword("456");
        userEntity.setSex(1);
        u1.setCreateTime(new Date());
        userlist.add(u1);
        UserEntity u2 = new UserEntity();
        u2.setUsername("甲醛");
        u2.setPassword("789");
        userEntity.setSex(2);
        u2.setCreateTime(new Date());
        userlist.add(u2);
        Iterable<UserEntity> userEntities = userRepository.saveAll(userlist);
        System.out.println(userEntities);




    }
    //修改，，，进行的先查询，，可以查找到的就进行修改，，没有，，就进行添加的操作
    @Test
    public void update(){
        UserEntity userEntity1 = userRepository.findById(6).get();
        userEntity1.setSex(2);
        //insert或update，如果主键Id有值，就修改操作，否则就新增。
        userRepository.save(userEntity1);
    }




    @Test
    public void delete(){
        //单删
        //删除时候，如果实体不存在，会抛异常
        //首先是判断是否存在
        boolean b = userRepository.existsById(10);
        if(b){
            //开始进行删除
            userRepository.deleteById(10);
        }






        //批量删除的时候，需要先查询对象。
        UserEntity userEntity = userRepository.findById(11).get();
        UserEntity userEntity1 = userRepository.findById(12).get();
        List<UserEntity> userlist = new ArrayList<>();
        userlist.add(userEntity);
        userlist.add(userEntity1);
        userRepository.deleteAll(userlist);
    }





    /*进行的是多个根据id进行的查询*/
    @Test
    public void query(){
        List<Integer> IdList = new ArrayList<>();
        IdList.add(4);
        IdList.add(5);
        IdList.add(6);
        Iterable<UserEntity> allById = userRepository.findAllById(IdList);
        System.out.println(allById);
    }





    //Sort排序对象，它很多个静态方法来构建Sort。
    @Test
    public void  sort(){


        //sort用来进行排序，，知识根据一个字段进行排序
        Sort sort = Sort.by(Sort.Direction.ASC,"username");
        Iterable<UserEntity> all = userRepository.findAll(sort);
        System.out.println(all);



        //多个字段排序，，，
        Sort.Order usernameOrder = Sort.Order.desc("username");
        Sort.Order idOrder = Sort.Order.desc("id");
        Sort sort1 = Sort.by(usernameOrder, idOrder);
        Iterable<UserEntity> all1 = userRepository.findAll(sort1);
        System.out.println(all1);



    }

    @Test
    public void  pageAndSorting(){
        //用PageRequest构建分页的对象，page开始页数，它从0开始的，0代表第一页。

        //进行分页初始化，page开始页数为0，size每页的页数为2
      /*  Pageable pageable = PageRequest.of(0,2);
        Page<UserEntity> page = userRepository.findAll(pageable);
        System.out.println("总页数"+page.getTotalPages());
        System.out.println("总条数"+page.getTotalElements());
        System.out.println("分页数据"+page.getContent());
*/



        //多个字段排序，，外加分页显示
        Sort.Order usernameOrder = Sort.Order.desc("username");
        Sort.Order idOrder = Sort.Order.desc("id");
        Sort sort1 = Sort.by(usernameOrder, idOrder);
        //上面的是排序，，下面的是分页操作
        Pageable pageable1 = PageRequest.of(0,2,sort1);


        // userRepository自带的方法查询
        Page<UserEntity> all = userRepository.findAll(pageable1);
        System.out.println(all);
    }

    @Test
    public void  jpaRepoistroy(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("骚博");
        userEntity.setSex(2);
        //Example是一个接口，它用静态方法来Example，按照Example指定条件来查询。
        Example<UserEntity> example = Example.of(userEntity);
        List<UserEntity> all = userRepository.findAll(example);


        //在example查询的基础，还可以进行分页和排序。
//        List<UserEntity> all = userRepository.findAll(example，pageable);
        System.out.println(all);
    }
    //自定义查询
    @Test
    public void selfquery(){
        //按username查询
        List<UserEntity> sao = userRepository.findByUsername("骚博");
        System.out.println(sao);
        //模糊查询
        List<UserEntity> saoLike = userRepository.findByUsernameLike("%".concat("骚").concat("%"));
        System.out.println(saoLike);
        //模糊查询+排序
        List<UserEntity> saoLikeSort = userRepository.findByUsernameLikeOrderByIdDesc("%".concat("博").concat("%"));
        System.out.println(saoLikeSort);

        Pageable pageable = PageRequest.of(0,2);
        Page<UserEntity> pageInfo = userRepository.findByUsername("骚博", pageable);
        System.out.println(pageInfo);
    }















    @Test
    public void testSelfDelete(){
        int i = userRepository.deleteByUsername("张三");
        System.out.println(i);

        int i1 = userRepository.deleteBySexIsNull();
        System.out.println(i1);

        int i3 = userRepository.deleteByUsernameAndSex("张三3", 2);
        System.out.println(i3);

    }

    @Test
    public void queryForHSql(){
        List<UserEntity> q1 = userRepository.queryByUsernameAndPassword("张恒", "2323443234");
        System.out.println(q1);
        List<UserEntity> q2 = userRepository.selectByUsernameAndPassword("张恒狗", "2323443234");
        System.out.println(q2);
        List<UserEntity> q3 = userRepository.queryByUsernameAndPasswordByParam("张恒黑狗", "2323443234");
        System.out.println(q3);
        Pageable pageable = PageRequest.of(0,1);
        List<UserEntity> q4 = userRepository.queryByUsernameAndPasswordByParam("张恒老狗", "2323443234",pageable);
        System.out.println(q4);
    }
    @Test
    public void updateByQuery(){
        int i = userRepository.updatePsswordById(7, "123456");
        System.out.println(i);
    }

























}

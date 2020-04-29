package com.shizhichao.demo.controller;

import com.shizhichao.demo.entity.UserEntity;
import com.shizhichao.demo.respository.OrderRepository;
import com.shizhichao.demo.respository.RoleRepository;
import com.shizhichao.demo.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/jpa/")
public class JpaController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 分页接口
     */
    @RequestMapping("getPageInfo")
    public Page<UserEntity> getPageInfo(UserEntity userEntity, @RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "3") Integer pageSize){
        //分页
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        //构建Specification
        Specification<UserEntity> specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                //保存多个查询条件
                List<Predicate> predicates = new ArrayList<>();
                //查询字段不为空的时候，添加条件
                if(userEntity.getUsername()!=null){
                    //构建条件
                    Predicate predicate = cb.like(root.get("username"),"%".concat(userEntity.getUsername()).concat("%"));
                    //添加到predicates
                    predicates.add(predicate);
                }
                //返回的接口
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return  userRepository.findAll(specification,pageable);
//        return userRepository.findAll(pageable);
    }
    @RequestMapping("order")
    public Object getOrderAll(){
        return orderRepository.findAll();
    }

    @RequestMapping("role")
    public Object getRoleAll(){
        return roleRepository.findAll();
    }
}

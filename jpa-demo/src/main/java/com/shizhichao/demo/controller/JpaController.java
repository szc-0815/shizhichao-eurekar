package com.shizhichao.demo.controller;

import com.shizhichao.demo.entity.OrderEntity;
import com.shizhichao.demo.entity.UserEntity;
import com.shizhichao.demo.respository.OrderRepository;
import com.shizhichao.demo.respository.RoleRepository;
import com.shizhichao.demo.respository.UserRepository;
import com.shizhichao.demo.service.OrderService;
import com.shizhichao.demo.service.UserService;
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

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;



    /**
     * 分页接口
     */
    @RequestMapping("user")
    public Page<UserEntity> getPageInfo(UserEntity userEntity, @RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "3") Integer pageSize){


        return  userService.getPageInfo(userEntity,pageNum,pageSize);

    }
    @RequestMapping("order")
    public Page<OrderEntity> order(OrderEntity orderEntity, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        return orderService.getPageInfo(orderEntity,pageNum,pageSize);
    }

    @RequestMapping("role")
    public Object getRoleAll(){
        return roleRepository.findAll();
    }
}

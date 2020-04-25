package com.shizhichao.order.controller;


import com.shizhichao.order.feign.UserFeignClient;
import com.shizhichao.order.model.Order;
import com.shizhichao.order.service.OrderService;
import com.shizhichao.userInterface.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order/")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserFeignClient userFeignClient;

    @RequestMapping("getOrderById")
    public Order getOrderById(@RequestParam("id") Integer id){

        Order order = orderService.getOrderById(id);
        System.err.println("------------------------------------------------"+order);
        User user = new User();
        user.setId(order.getId());
        User user1 = userFeignClient.getUserByUser(user);
        log.info("user:{}",user1);
        order.setUsername(user1.getUsername());
        return order;
    }


    @RequestMapping("getOrdernameById")
    public String getOrderNoById(@RequestParam("id") Integer id){

        return orderService.getOrdernameById(id);
    }

}

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
        //方式一 使用ribbon+restTemplate 实现负载均衡 以及 服务间调用
        //根据oid查询order对象
        Order order = orderService.getOrderById(id);
        System.err.println("------------------------------------------------"+order);
        //根据orders对象里面的uid查询用户名
        //User user = restTemplate.getForObject("http://service-user/user/getUserById?id=" + order.getUserId(), User.class);
        //方式二  使用feign的方式
        //User user = userFeignClient.getUserById(order.getUserId());
        //实现 服务间调用对象传参
        User user = new User();
        user.setId(15);
        User user1 = userFeignClient.getUserByUser(user);
        log.info("user:{}",user1);
        order.setId(id);
        order.setUserId(user1.getId());
        order.setUsername(user1.getUsername());
        return order;
    }


    @RequestMapping("getOrdernameById")
    public String getOrderNoById(@RequestParam("id") Integer id){

        return orderService.getOrdernameById(id);
    }

}

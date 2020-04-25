package com.shizhichao.order.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shizhichao.order.model.Order;



public interface OrderService extends IService<Order> {
    Order getOrderById(Integer id);


    String getOrdernameById(Integer id);
}

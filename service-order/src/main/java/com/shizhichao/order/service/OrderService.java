package com.shizhichao.order.service;


import com.shizhichao.order.model.Order;
import org.springframework.stereotype.Service;


public interface OrderService {
    Order getOrderById(Integer id);


    String getOrdernameById(Integer id);
}

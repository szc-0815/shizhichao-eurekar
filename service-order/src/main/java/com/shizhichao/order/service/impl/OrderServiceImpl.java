package com.shizhichao.order.service.impl;



import com.shizhichao.order.model.Order;
import com.shizhichao.order.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {



    @Override
    public Order getOrderById(Integer id) {

        Order order = new Order();
        order.setId(id);
        order.setUserId(1);
        order.setOrderno(System.currentTimeMillis()+"");
        return order;
    }

    @Override
    public String getOrdernameById(Integer id) {
        return null;
    }

}

package com.shizhichao.order.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shizhichao.order.mapper.OrderMapper;
import com.shizhichao.order.model.Order;
import com.shizhichao.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderService orderService;

    @Override
    public Order getOrderById(Integer id) {


        Order order = orderService.getById(id);
        order.setOrderno(System.currentTimeMillis()+"");
        return order;
    }

    @Override
    public String getOrdernameById(Integer id) {
        return null;
    }


}

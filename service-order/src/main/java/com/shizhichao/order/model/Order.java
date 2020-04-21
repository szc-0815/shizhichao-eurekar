package com.shizhichao.order.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order {
    private Integer id;
    private String orderno;
    private Integer userId;
    private String username;
}

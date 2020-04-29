package com.shizhichao.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

//Entity注解和数据表有映射关系
//如果数据库表和实体类不一致，需要table注解，指定表名
@Data
@Entity
@Table(name = "t_order",catalog = "zg5_1708d",schema = "zg5_1708d")
public class OrderEntity {
    /** 主键的生成策略： IDENTITY、AUTO **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //订单编号
    private String orderNo;
    //用户Id
    private Integer userId;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //一对一关系，JoinColumn.name指定关联字段，referencedColumnName是当前实体主键，则可以省略。
    // insertable、updatable=false,新增或修改的时候，限制更新关联表，用到JoinColumn是都指定false
   /* @OneToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "userId"*//*,referencedColumnName = "id"*//*,insertable = false,updatable = false)
    private UserEntity user;*/

    //忽略UserEntity的orderList属性
    @JsonIgnoreProperties("orderList")
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "userId"/*,referencedColumnName = "id"*/,insertable = false,updatable = false)
    private UserEntity user;



}

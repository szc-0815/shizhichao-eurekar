package com.shizhichao.demo.entity;

import com.shizhichao.demo.common.DeleteEnum;
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
@Table(name = "t_user",catalog = "zg5_1708d",schema = "zg5_1708d")
public class UserEntity {
    /** 主键的生成策略： IDENTITY、AUTO **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //没有被注解的属性，在建表也会生成对应的字段。
    private String username;
    private String password;
    //字段精度设置：precision长度，scale小数的位数
    @Column(precision = 10,scale = 2,columnDefinition = "double(10,2) DEFAULT NULL COMMENT '积分'")
    private double score;

    private Integer sex;

    private Integer classesId;
    //非数据的映射字段，加Transient注解
    @Transient
    private String classesName;

    @Transient
    private String orderNo;

    //枚举
    @Enumerated(EnumType.ORDINAL)
    private DeleteEnum isDelete;
    //大字段lob
    @Lob
    private String content;

    //按java命名规范，在表里生成create_time字段
    private Date createTime;
    //指定时间的精度,默认是datetime
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

}

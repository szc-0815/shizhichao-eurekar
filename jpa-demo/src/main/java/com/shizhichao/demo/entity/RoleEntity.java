package com.shizhichao.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//Entity注解和数据表有映射关系
//如果数据库表和实体类不一致，需要table注解，指定表名
@Data
@Entity
@Table(name = "t_role",catalog = "zg5_1708d",schema = "zg5_1708d")
public class RoleEntity {
    /** 主键的生成策略： IDENTITY、AUTO **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //角色名称
    private String name;

    //多对多关系
    //JoinTable name是关系表的表名 joinColumns是主表在关系表对应的列，inverseJoinColumns从表对应的列
    @JsonIgnoreProperties("roleList")
    @ManyToMany
    @JoinTable(name = "t_user_role_relation",joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> userList;
}

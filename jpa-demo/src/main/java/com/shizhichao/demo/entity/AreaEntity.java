package com.shizhichao.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//Entity注解和数据表有映射关系
//如果数据库表和实体类不一致，需要table注解，指定表名
@Data
@Entity
@Table(name = "t_area",catalog = "zg5_1708d",schema = "zg5_1708d")
public class AreaEntity implements Serializable {
    /** 主键的生成策略： IDENTITY、AUTO **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //角色名称
    private String name;
    //父Id
    private Integer pid;

   /* @JsonIgnoreProperties("areaList")
    @OneToMany(mappedBy = "pid")
    private List<AreaEntity> areaList;*/

   @Transient
   private List<AreaEntity> areaList;

}

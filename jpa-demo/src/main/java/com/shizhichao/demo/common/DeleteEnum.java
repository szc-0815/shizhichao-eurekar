package com.shizhichao.demo.common;

import org.springframework.context.annotation.Primary;

/**
 * 软删除的枚举类
 */
public enum DeleteEnum {
    YES(1),NO(0);

    private Integer value;

    DeleteEnum(Integer value) {
        this.value = value;
    }
}

package com.shizhichao.demo.respository;


import com.shizhichao.demo.entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AreaRepository extends JpaRepository<AreaEntity,Integer> {
    /**
     * 根据父Id，查询城市
     * @param pid
     * @return
     */
    List<AreaEntity> findByPid(Integer pid);
}

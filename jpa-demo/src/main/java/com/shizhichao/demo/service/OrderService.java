package com.shizhichao.demo.service;


import com.shizhichao.demo.entity.OrderEntity;
import com.shizhichao.demo.respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Page<OrderEntity> getPageInfo(OrderEntity orderEntity, Integer pageNum, Integer pageSize){
        Specification<OrderEntity> specification = new Specification<OrderEntity>() {
            @Override
            public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(orderEntity.getUsername()!=null){
                    Predicate usernamePredicate = cb.like(root.get("user").get("username"), "%".concat(orderEntity.getUsername()).concat("%"));
                    predicates.add(usernamePredicate);
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return orderRepository.findAll(specification,pageable);
    }
}

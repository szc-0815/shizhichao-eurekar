package com.shizhichao.demo.service;


import com.shizhichao.demo.entity.AreaEntity;
import com.shizhichao.demo.respository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaService {
    @Autowired
    private AreaRepository areaRepository;

    public List<AreaEntity> getTree(){
        /*return  areaRepository.findByPid(1);*/
        //返回的结果
        List<AreaEntity> proviceList = new ArrayList<>();
        //一次从数据查询所有数据
        List<AreaEntity> all = areaRepository.findAll();
        //从all、查找省的数据
        all.forEach(provice->{
            if(provice.getPid()==1){
                proviceList.add(provice);
            }
        });
        //遍历省，查找市的数据
        proviceList.forEach(provice->{
            //查找省下面的所有市
            List<AreaEntity> cityList = new ArrayList<>();
            all.forEach(ctiy->{
                if(ctiy.getPid().intValue()==provice.getId().intValue()){
                    cityList.add(ctiy);
                    //查找市下面的所有区
                    List<AreaEntity> areaList = new ArrayList<>();
                    all.forEach(area->{
                        if(area.getPid().intValue()==ctiy.getId().intValue()){
                            areaList.add(area);
                        }
                    });
                    //设置区
                    ctiy.setAreaList(areaList);
                }
            });
            //设置城市
            provice.setAreaList(cityList);
        });
        return proviceList;
    }



}

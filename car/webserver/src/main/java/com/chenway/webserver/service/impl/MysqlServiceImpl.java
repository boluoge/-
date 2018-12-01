package com.chenway.webserver.service.impl;

import com.chenway.webserver.bean.*;
import com.chenway.webserver.dao.*;
import com.chenway.webserver.service.MysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MysqlServiceImpl implements MysqlService {

    @Autowired
    private TFByKilometerJPA tfByKilometerJPA;
    @Autowired
    private TFByCountJPA tfByCountJPA;
    @Autowired
    private TFByLocationJPA tfByLocationJPA;
    @Autowired
    private TFBySpeedJPA tfBySpeedJPA;
    @Autowired
    private CategoryStatusNumJPA categoryStatusNumJPA;
    @Autowired
    private SpeedNumJPA speedNumJPA;
    @Autowired
    private CarKilometerNumJPA carKilometerNumJPA;
    @Autowired
    private CarLogJPA carLogJPA;

    @Override
    public List<TFByKilometer> top5ByKilometer() {
        return tfByKilometerJPA.findAll(new Sort(Sort.Direction.DESC,"kilometer"));
    }

    @Override
    public List<TFByCount> top5ByCount() {
        return tfByCountJPA.findAll(new Sort(Sort.Direction.DESC,"count"));
    }

    @Override
    public List<TFBySpeed> top5BySpeed() {
        return tfBySpeedJPA.findAll(new Sort(Sort.Direction.DESC,"speed"));
    }

    @Override
    public List<TFByLocation> top5ByLocation() {
        return tfByLocationJPA.findAll(new Sort(Sort.Direction.DESC,"num"));
    }

    @Override
    public List<CategoryStatusNum> findCSN() {
        List<CategoryStatusNum> all = categoryStatusNumJPA.findAll();
        for(CategoryStatusNum categoryStatusNum:all){
            if(categoryStatusNum.getStatu().equals("0")){
                categoryStatusNum.setStatu("行驶" );
            }if(categoryStatusNum.getStatu().equals("1")){
                categoryStatusNum.setStatu("临时停车");
            }if(categoryStatusNum.getStatu().equals("2")){
                categoryStatusNum.setStatu("停车");
            }
        }
        return all;
    }

    @Override
    public List<SpeedNum> findSN() {
        return speedNumJPA.findAll();
    }

    @Override
    public List<CarKilometerNum> findCKN() {
        return carKilometerNumJPA.findAll();
    }

    @Override
    public List<CarLog> findLastLocation() {

        return carLogJPA.findLastLocation();
    }

    @Override
    public List<CarLog> findCategoryNum() {

        return carLogJPA.findCategoryNum();
    }
}

package com.chenway.webserver.service;

import com.chenway.webserver.bean.*;

import java.util.List;

public interface MysqlService {

  public List<TFByKilometer> top5ByKilometer();

  public List<TFByCount> top5ByCount();

  public List<TFBySpeed> top5BySpeed();

  public List<TFByLocation> top5ByLocation();

  public List<CategoryStatusNum> findCSN();

  public List<SpeedNum> findSN();

  public List<CarKilometerNum> findCKN();

  public List<CarLog> findLastLocation();

  public List<CarLog> findCategoryNum();
}

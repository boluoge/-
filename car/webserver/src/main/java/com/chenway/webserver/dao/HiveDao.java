package com.chenway.webserver.dao;

import java.util.List;
import java.util.Map;

public interface HiveDao {

    public List<Map<String, Object>> queryAllData();

    public List<Map<String, Object>> queryDataByMinute(String minute);

    public List<Map<String, Object>> queryDataByHour(String hour);

    public List<Map<String, Object>> queryDataByDay(String day);

    public List<Map<String, Object>> queryDataByMonth(String month);

    public List<Map<String, Object>> queryDataByYear(String year);

    public List<Map<String, Object>> queryCategoryNumber();

    public List<Map<String, Object>> queryCarNumber();

    public List<Map<String, Object>> queryHistoryCars();
}

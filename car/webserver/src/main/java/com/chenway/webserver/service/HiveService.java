package com.chenway.webserver.service;

import java.util.List;
import java.util.Map;

public interface HiveService {
    public String queryAllData();

    public String queryDataByMinute(String minute);

    public String queryDataByHour(String hour);

    public String queryDataByDay(String day);

    public String queryDataByMonth(String month);

    public String queryDataByYear(String year);

    public String queryCategoryNumber();

    public String queryCarNumber();

    public String queryHistoryCars();
}

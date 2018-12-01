package com.chenway.webserver.dao.impl;

import com.chenway.webserver.dao.HiveDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "hiveDao")
public class HiveDaoImpl implements HiveDao {

    @Autowired
    @Qualifier("hiveJdbcTemplate")
    private JdbcTemplate hiveJdbcTemplate;

    @Override
    public List<Map<String, Object>> queryAllData() {
        return hiveJdbcTemplate.queryForList("select * from carlogs");
      //  return hiveJdbcTemplate.toString();
    }

    @Override
    public List<Map<String, Object>> queryDataByMinute(String minute) {
        return hiveJdbcTemplate.queryForList("select * from carlogs where minute ="+minute);

    }

    @Override
    public List<Map<String, Object>> queryDataByHour(String hour) {
        return hiveJdbcTemplate.queryForList("select * from carlogs where hour ="+hour);
    }

    @Override
    public List<Map<String, Object>> queryDataByDay(String day) {
        return hiveJdbcTemplate.queryForList("select  * from carlogs where day="+day);
    }

    @Override
    public List<Map<String, Object>> queryDataByMonth(String month) {
        return hiveJdbcTemplate.queryForList("select * from carlogs where month ="+month);
    }

    @Override
    public List<Map<String, Object>> queryDataByYear(String year) {
        return hiveJdbcTemplate.queryForList("select * from carlogs where year="+year);
    }

    @Override
    public List<Map<String, Object>> queryCategoryNumber() {
        return hiveJdbcTemplate.queryForList("select category,count(*) from carlogs group by category");
    }

    @Override
    public List<Map<String, Object>> queryCarNumber() {
        return hiveJdbcTemplate.queryForList("select count(distinct carid) from carlogs ");
    }

    @Override
    public List<Map<String, Object>> queryHistoryCars() {
        return hiveJdbcTemplate.queryForList("select count(*) from carlogs");
    }


}

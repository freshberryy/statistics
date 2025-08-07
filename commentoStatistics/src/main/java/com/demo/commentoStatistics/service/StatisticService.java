package com.demo.commentoStatistics.service;

import com.demo.commentoStatistics.dao.StatisticMapper;
import com.demo.commentoStatistics.dto.YearCountDto;
import com.demo.commentoStatistics.dto.YearMonthCountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {


    @Autowired
    StatisticMapper statisticMapper;

    public YearCountDto getYearLogins(String year){

        return statisticMapper.selectYearLogin(year);
    }

    public YearMonthCountDto getYearMonthLogins(String year, String month){

        return statisticMapper.selectYearMonthLogin(year+month);
    }



}

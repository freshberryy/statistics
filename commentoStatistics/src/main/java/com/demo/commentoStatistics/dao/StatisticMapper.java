package com.demo.commentoStatistics.dao;

import com.demo.commentoStatistics.dto.YearCountDto;
import com.demo.commentoStatistics.dto.YearMonthCountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticMapper {

    YearCountDto selectYearLogin(String year);
    YearMonthCountDto selectYearMonthLogin(String yearMonth);
}

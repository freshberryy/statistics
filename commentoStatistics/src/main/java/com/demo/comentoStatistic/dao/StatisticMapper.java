package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.vo.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Mapper도 빈으로 처리된다.
@Mapper
public interface StatisticMapper {

    List<SessionCountByMonthVO> sessionCountByMonth();

    List<SessionCountByDayVO> sessionCountByDay();

    LoginCountAvgByDayVO loginCountAvgByDay();

    int loginCountExceptHoliday(@Param("holidays") List<String> holidays);

    List<LoginCountByOrganMonthVO> loginCountByOrganMonth();
}

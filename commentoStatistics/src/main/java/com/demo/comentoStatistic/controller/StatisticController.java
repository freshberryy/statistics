package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.dao.StatisticMapper;
import com.demo.comentoStatistic.service.HolidayService;
import com.demo.comentoStatistic.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats/count")
public class StatisticController {

    @Autowired
    private StatisticMapper statisticMapper;
    @Autowired
    private HolidayService holidayService;

    @GetMapping("/session/month")
    public List<SessionCountByMonthVO> getSessionCountByMonth() {
        return statisticMapper.sessionCountByMonth();
    }

    @GetMapping("/session/day")
    public List<SessionCountByDayVO> getSessionCountByDay() {
        return statisticMapper.sessionCountByDay();
    }

    @GetMapping("/login/avg")
    public LoginCountAvgByDayVO getLoginCountAvgByDay() {
        return statisticMapper.loginCountAvgByDay();
    }

    @GetMapping("/login-except-holiday/{year}")
    public int getLoginCountExceptHoliday(@PathVariable int year) {
        List<String> holidays = holidayService.getHolidays(year);
        return statisticMapper.loginCountExceptHoliday(holidays);
    }

    @GetMapping("/login/organ-month")
    public List<LoginCountByOrganMonthVO> getLoginCountByOrganMonth(){
        return statisticMapper.loginCountByOrganMonth();
    }


}
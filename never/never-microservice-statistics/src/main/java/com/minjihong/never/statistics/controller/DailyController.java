package com.minjihong.never.statistics.controller;


import com.minjihong.never.common.vo.R;
import com.minjihong.never.statistics.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-15
 */
@RestController
@RequestMapping("/statistics/daily")
@CrossOrigin
public class DailyController {

    private DailyService dailyService;

    @Autowired
    public DailyController(DailyService dailyService) {
        this.dailyService = dailyService;
    }

    /**
     * 获取某一天注册人数
     *
     * @param day
     * @return
     */
    @GetMapping("getRegisterNum/{day}")
    public R getRegisterNum(@PathVariable("day") String day) {
        dailyService.getRegisterNum(day);
        return R.ok();
    }

    /**
     * 获取统计数据返回给echarts
     *
     * @param type  获取什么类型的数据
     * @param begin 开始时间
     * @param end   结束时间
     * @return
     */
    @GetMapping("getCountData/{type}/{begin}/{end}")
    public R getDataCount(@PathVariable("type") String type,
                          @PathVariable("begin") String begin,
                          @PathVariable("end") String end) {
        Map<String, Object> map = dailyService.getDataCount(type, begin, end);

        return R.ok().data(map);
    }

}


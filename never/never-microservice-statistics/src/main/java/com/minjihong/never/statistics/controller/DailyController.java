package com.minjihong.never.statistics.controller;


import com.minjihong.never.common.vo.R;
import com.minjihong.never.statistics.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("getRegisterNum/{day}")
    public R getRegisterNum(@PathVariable("day") String day) {
        dailyService.getRegisterNum(day);
        return R.ok();
    }

}


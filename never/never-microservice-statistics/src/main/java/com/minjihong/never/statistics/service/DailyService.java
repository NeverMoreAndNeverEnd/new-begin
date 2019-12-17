package com.minjihong.never.statistics.service;

import com.minjihong.never.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-15
 */
public interface DailyService extends IService<Daily> {

    void getRegisterNum(String day);

    Map<String, Object> getDataCount(String type, String begin, String end);
}

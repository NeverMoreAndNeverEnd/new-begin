package com.minjihong.never.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minjihong.never.common.vo.R;
import com.minjihong.never.statistics.client.UcenterClient;
import com.minjihong.never.statistics.entity.Daily;
import com.minjihong.never.statistics.mapper.DailyMapper;
import com.minjihong.never.statistics.service.DailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-15
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    private UcenterClient ucenterClient;

    @Autowired
    public DailyServiceImpl(UcenterClient ucenterClient) {
        this.ucenterClient = ucenterClient;
    }

    @Override
    public void getRegisterNum(String day) {

        //删除已存在的统计对象
        QueryWrapper<Daily> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.eq("date_calculated", day);
        baseMapper.delete(dayQueryWrapper);

        //获取统计信息
        R r = ucenterClient.registerCount(day);
        Integer registerNum = (Integer) r.getData().get("countRegister");
        //System.out.println(count);

        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO
        //创建统计对象
        Daily daily = new Daily();
        daily.setRegisterNum(registerNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);
        baseMapper.insert(daily);

    }

    @Override
    public Map<String, Object> getDataCount(String type, String begin, String end) {

        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        //wrapper.ge(),wrapper.le()
        wrapper.between("date_calculated", begin, end);
        wrapper.select("date_calculated", type);
        List<Daily> dailyList = baseMapper.selectList(wrapper);

        List<String> timeList = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();
        for (Daily daily : dailyList) {
            String dateCalculated = daily.getDateCalculated();
            timeList.add(dateCalculated);
            switch (type) {
                case "login_num":
                    dataList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    dataList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    dataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    dataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("timeList", timeList);
        map.put("dataList", dataList);
        return map;
    }
}

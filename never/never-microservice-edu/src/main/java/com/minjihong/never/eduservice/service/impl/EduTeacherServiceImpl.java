package com.minjihong.never.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minjihong.never.eduservice.entity.EduTeacher;
import com.minjihong.never.eduservice.entity.query.QueryTeacher;
import com.minjihong.never.eduservice.mapper.EduTeacherMapper;
import com.minjihong.never.eduservice.service.EduTeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author jihong.min
 * @since 2019-11-27
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageListCondition(Page<EduTeacher> teacherPage, QueryTeacher queryTeacher) {

        if (queryTeacher == null) {
            baseMapper.selectPage(teacherPage, null);
            return;
        }

        String name = queryTeacher.getName();
        String level = queryTeacher.getLevel();
        String begin = queryTeacher.getBegin();
        String end = queryTeacher.getEnd();

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            wrapper.like("name", name);
        }
        if (StringUtils.isNotEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (StringUtils.isNotEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (StringUtils.isNotEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        baseMapper.selectPage(teacherPage, wrapper);
    }

    @Override
    public boolean deleteTeacherById(String id) {
        int i = baseMapper.deleteById(id);
        return  i >0 ;
    }
}

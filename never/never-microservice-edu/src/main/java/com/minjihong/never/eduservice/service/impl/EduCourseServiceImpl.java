package com.minjihong.never.eduservice.service.impl;

import com.minjihong.never.eduservice.entity.EduCourse;
import com.minjihong.never.eduservice.entity.EduCourseDescription;
import com.minjihong.never.eduservice.entity.form.CourseInfoForm;
import com.minjihong.never.eduservice.exception.EduException;
import com.minjihong.never.eduservice.mapper.EduCourseMapper;
import com.minjihong.never.eduservice.service.EduCourseDescriptionService;
import com.minjihong.never.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-04
 */
@Transactional
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String insertCourseInfo(CourseInfoForm courseInfoForm) {
        //添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus(EduCourse.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0) {
            throw new EduException(20001, "课程信息保存失败!");
        }
        //添加课程描述
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        if (!save) {
            throw new EduException(20001, "课程描述信息保存失败!");
        }
        return eduCourse.getId();
    }
}

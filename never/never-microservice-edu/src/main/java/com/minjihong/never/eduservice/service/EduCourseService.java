package com.minjihong.never.eduservice.service;

import com.minjihong.never.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.minjihong.never.eduservice.entity.form.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-04
 */
public interface EduCourseService extends IService<EduCourse> {

    String insertCourseInfo(CourseInfoForm courseInfoForm);
}

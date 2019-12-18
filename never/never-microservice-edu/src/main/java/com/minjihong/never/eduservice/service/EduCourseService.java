package com.minjihong.never.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minjihong.never.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.minjihong.never.eduservice.entity.form.CourseInfoForm;
import com.minjihong.never.eduservice.entity.query.QueryCourse;
import com.minjihong.never.eduservice.entity.vo.CoursePublishVo;
import com.minjihong.never.eduservice.entity.vo.CourseWebInfo;

import java.util.List;
import java.util.Map;

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

    CourseInfoForm getCourseById(String id);

    boolean updateCourseInfoById(CourseInfoForm courseInfoForm);

    void pageQuery(Page<EduCourse> coursePage, QueryCourse queryCourse);

    boolean removeCourseById(String id);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    List<EduCourse> getCourseListByTeacherId(String id);

    Map<String, Object> getFrontTeacherList(Page<EduCourse> eduCoursePage);

    CourseWebInfo selectInfoWebById(String id);
}

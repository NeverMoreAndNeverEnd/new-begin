package com.minjihong.never.eduservice.controller;


import com.minjihong.never.common.vo.R;
import com.minjihong.never.eduservice.entity.form.CourseInfoForm;
import com.minjihong.never.eduservice.service.EduCourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-04
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("add")
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        String courseId = eduCourseService.insertCourseInfo(courseInfoForm);
        return StringUtils.isNotEmpty(courseId) ? R.ok().data("courseId", courseId) : R.error().message("保存失败");
    }

}


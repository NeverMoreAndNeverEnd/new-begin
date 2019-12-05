package com.minjihong.never.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minjihong.never.common.vo.R;
import com.minjihong.never.eduservice.entity.EduCourse;
import com.minjihong.never.eduservice.entity.form.CourseInfoForm;
import com.minjihong.never.eduservice.entity.query.QueryCourse;
import com.minjihong.never.eduservice.service.EduCourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 新增课程信息
     *
     * @param courseInfoForm
     * @return
     */
    @PostMapping("add")
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        String courseId = eduCourseService.insertCourseInfo(courseInfoForm);
        return StringUtils.isNotEmpty(courseId) ? R.ok().data("courseId", courseId) : R.error().message("保存失败");
    }

    /**
     * 根据id查询课程信息
     *
     * @param id
     * @return
     */
    @GetMapping("getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable("id") String id) {
        CourseInfoForm courseInfoForm = eduCourseService.getCourseById(id);
        return R.ok().data("item", courseInfoForm);
    }

    /**
     * 根据id更新课程信息
     *
     * @param id
     * @param courseInfoForm
     * @return
     */
    @PutMapping("updateCourseInfo/{id}")
    public R updateCourseInfo(@PathVariable("id") String id, @RequestBody CourseInfoForm courseInfoForm) {
        boolean flag = eduCourseService.updateCourseInfoById(courseInfoForm);
        return flag ? R.ok().data("courseId", id) : R.error();
    }


    @PostMapping ("pageList/{page}/{limit}")
    public R pageQuery(@PathVariable("page") Long page, @PathVariable("limit") Long limit,
                       @RequestBody(required = false) QueryCourse queryCourse) {
        Page<EduCourse> coursePage = new Page<>(page, limit);
        eduCourseService.pageQuery(coursePage,queryCourse);
        List<EduCourse> records = coursePage.getRecords();
        long total = coursePage.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }


}


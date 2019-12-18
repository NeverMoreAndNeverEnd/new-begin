package com.minjihong.never.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minjihong.never.common.vo.R;
import com.minjihong.never.eduservice.entity.EduCourse;
import com.minjihong.never.eduservice.entity.EduTeacher;
import com.minjihong.never.eduservice.service.EduCourseService;
import com.minjihong.never.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/front/teacher")
@CrossOrigin
public class FrontEduTeacherController {

    private EduTeacherService eduTeacherService;

    private EduCourseService eduCourseService;

    @Autowired
    public FrontEduTeacherController(EduTeacherService eduTeacherService, EduCourseService eduCourseService) {
        this.eduTeacherService = eduTeacherService;
        this.eduCourseService = eduCourseService;
    }

    /**
     * 分页获取讲师列表
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("{page}/{limit}")
    public R getFrontTeacherListPage(@PathVariable("page") Long page,
                                     @PathVariable("limit") Long limit) {
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        Map<String, Object> map = eduTeacherService.getFrontTeacherList(teacherPage);
        return R.ok().data(map);
    }

    /**
     * 根据id查询讲师详情和所讲课程
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public R getTeacherInfoAndCourseId(@PathVariable("id") String id) {
        // 查询讲师详情
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        // 查询讲师所讲课程
        List<EduCourse> courses = eduCourseService.getCourseListByTeacherId(id);
        return R.ok().data("eduTeacher", eduTeacher).data("courseList", courses);
    }
}

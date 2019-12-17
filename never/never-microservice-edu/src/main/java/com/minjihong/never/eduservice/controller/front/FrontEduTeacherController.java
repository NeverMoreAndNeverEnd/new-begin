package com.minjihong.never.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minjihong.never.common.vo.R;
import com.minjihong.never.eduservice.entity.EduTeacher;
import com.minjihong.never.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/eduservice/front/teacher")
@CrossOrigin
public class FrontEduTeacherController {

    private EduTeacherService eduTeacherService;

    @Autowired
    public FrontEduTeacherController(EduTeacherService eduTeacherService) {
        this.eduTeacherService = eduTeacherService;
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
}

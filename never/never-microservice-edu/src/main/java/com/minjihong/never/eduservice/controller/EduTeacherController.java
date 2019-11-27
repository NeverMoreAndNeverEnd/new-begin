package com.minjihong.never.eduservice.controller;


import com.minjihong.never.eduservice.entity.EduTeacher;
import com.minjihong.never.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author jihong.min
 * @since 2019-11-27
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    /**
     * 查询所有老师
     * @return
     */
    @GetMapping(value = "listAll")
    public List<EduTeacher> getAllTeachers(){
        return teacherService.list(null);
    }

    @DeleteMapping("delete/{id}")
    public boolean deleteTeacher(@PathVariable String id){
        boolean b = teacherService.removeById(id);
        return b;
    }

}


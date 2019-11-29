package com.minjihong.never.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minjihong.never.common.vo.R;
import com.minjihong.never.eduservice.entity.EduTeacher;
import com.minjihong.never.eduservice.entity.query.QueryTeacher;
import com.minjihong.never.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
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
@CrossOrigin(origins = "*", maxAge = 3600)
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    /**
     * 查询所有老师
     *
     * @return
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping(value = "listAll")
    public R getAllTeachers() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除讲师")
    @DeleteMapping("delete/{id}")
    public R deleteTeacher(@PathVariable String id) {
        boolean b = teacherService.removeById(id);
        return R.ok();
    }

    /**
     * 简单分页查询
     *
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("list/{page}/{limit}")
    public R pageListAllTeachers(@PathVariable Long page, @PathVariable Long limit) {
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        teacherService.page(teacherPage, null);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    /**
     * 分页多条件查询
     *
     * @param page
     * @param limit
     * @param queryTeacher
     * @return
     */
    @ApiOperation(value = "分页条件查询讲师列表")
    @PostMapping("codPageList/{page}/{limit}")
    public R pageListConditionsTeachers(@PathVariable Long page, @PathVariable Long limit,
                                        @RequestBody(required = false) QueryTeacher queryTeacher) {
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        teacherService.pageListCondition(teacherPage, queryTeacher);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }


    /**
     * 新增教师信息
     *
     * @param eduTeacher
     * @return
     */
    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody(required = false) EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 根据id查询教师信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("getTeacherInfo/{id}")
    public R getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("item", eduTeacher);
    }

    /**
     * 根据id更新教师信息
     *
     * @param id
     * @param eduTeacher
     * @return
     */
    @ApiOperation(value = "根据id新增讲师")
    @PutMapping("updateTeacherInfo/{id}")
    public R updateTeacher(@PathVariable String id, @RequestBody(required = false) EduTeacher eduTeacher) {
        eduTeacher.setId(id);
        boolean b = teacherService.updateById(eduTeacher);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }


}


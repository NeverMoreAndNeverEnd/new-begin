package com.minjihong.never.eduservice.controller;


import com.minjihong.never.common.vo.R;
import com.minjihong.never.eduservice.entity.EduSubject;
import com.minjihong.never.eduservice.entity.vo.SubjectNestedVo;
import com.minjihong.never.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-03
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    // 通过上传Excel文件获取文件内容
    @PostMapping("import")
    public R importExcelSubject(@RequestParam("file") MultipartFile file) {
        List<String> msg = subjectService.importSubject(file);
        if (msg.size() == 0) {
            return R.ok().message("导入excel数据成功");
        } else {
            return R.error().message("部分数据导入excel数据失败").data("msgList", msg);
        }
    }

    @GetMapping("list")
    public R listSubjectVo() {
        List<SubjectNestedVo> list = subjectService.nestedList();
        return R.ok().data("items", list);
    }

    @DeleteMapping("delete/{id}")
    public R deleteById(@PathVariable("id") String id) {
        boolean flag = subjectService.deleteSubjectById(id);
        return flag ? R.ok() : R.error();
    }

    // 添加一级分类
    @PostMapping("addOneLevel")
    public R addOneLevel(@RequestBody EduSubject eduSubject) {
        boolean flag = subjectService.addOneLevel(eduSubject);
        return flag ? R.ok() : R.error();
    }

    // 添加二级分类
    @PostMapping("addTwoLevel")
    public R addTwoLevel(@RequestBody EduSubject eduSubject) {
        boolean flag = subjectService.addTwoLevel(eduSubject);
        return flag ? R.ok() : R.error();
    }

}


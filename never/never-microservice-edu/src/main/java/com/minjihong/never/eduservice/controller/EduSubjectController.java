package com.minjihong.never.eduservice.controller;


import com.minjihong.never.common.vo.R;
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
        if (msg.size() < 0) {
            return R.ok().message("导入excel数据成功");
        }else {
            return R.error().message("导入excel数据失败").data("msgList",msg);
        }
    }

}


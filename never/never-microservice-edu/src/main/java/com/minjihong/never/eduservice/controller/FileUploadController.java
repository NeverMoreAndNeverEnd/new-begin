package com.minjihong.never.eduservice.controller;

import com.minjihong.never.common.vo.R;
import com.minjihong.never.eduservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/eduservice/oss")
@RestController
@CrossOrigin
public class FileUploadController {

    @Autowired
    private FileService fileService;

    //上传讲师头像方法
    @PostMapping("upload")
    public R uploadTeacherImg(@RequestParam("file") MultipartFile file) {
        String upload = fileService.upload(file);
        return R.ok().data("url", upload).message("文件上传成功!");

    }

}

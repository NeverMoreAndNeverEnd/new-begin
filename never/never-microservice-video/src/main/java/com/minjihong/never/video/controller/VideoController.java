package com.minjihong.never.video.controller;

import com.minjihong.never.common.vo.R;
import com.minjihong.never.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("video")
public class VideoController {

    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }


    @PostMapping("upload")
    public R uploadVideo(@RequestParam("file") MultipartFile file) {
        String videoId = videoService.uploadVideo(file);
        return R.ok().data("videoId", videoId).message("视频上传成功!");
    }

    @DeleteMapping("delete/{videoId}")
    public R deleteVideo(@PathVariable("videoId") String videoId) {
        videoService.removeVideo(videoId);
        return R.ok();
    }


}

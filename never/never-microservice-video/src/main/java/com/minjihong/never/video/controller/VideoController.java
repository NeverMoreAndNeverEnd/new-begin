package com.minjihong.never.video.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.minjihong.never.common.vo.R;
import com.minjihong.never.video.service.VideoService;
import com.minjihong.never.video.util.AliyunVideoSDKUtils;
import com.minjihong.never.video.util.ConstantPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @DeleteMapping("delete/videoList")
    public R deleteVideoList(@RequestParam("videoList") List<String> videoList) {
        videoService.removeVideoList(videoList);
        return R.ok();
    }

    /**
     * 获取播放凭证
     *
     * @param vid
     * @return
     */
    @GetMapping("getPlayAuth/{vid}")
    public R getPlayAutoId(@PathVariable("vid") String vid) {

        try {
            //获取阿里云存储相关常量
            String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
            String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
            //初始化
            DefaultAcsClient client = AliyunVideoSDKUtils.initVodClient(accessKeyId, accessKeySecret);
            //请求
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(vid);
            //响应
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            //得到播放凭证
            String playAuth = response.getPlayAuth();
            //返回结果
            return R.ok().message("获取凭证成功").data("playAuth", playAuth);
        } catch (ClientException e) {
            e.printStackTrace();
            return R.error();
        }

    }


}

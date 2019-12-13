package com.minjihong.never.eduservice.client;

import com.minjihong.never.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient("never-video")
public interface VodClient {

    @DeleteMapping("video/delete/{videoId}")
    public R removeVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping("video/delete/videoList")
    public R removeVideoList(@RequestParam("videoList") List<String> videoList);
}

package com.minjihong.never.statistics.client;

import com.minjihong.never.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("never-ucenter")
@Component
public interface UcenterClient {

    @GetMapping("/ucenter/member/countRegisterNum/{day}")
    public R registerCount(@PathVariable("day") String day);
}

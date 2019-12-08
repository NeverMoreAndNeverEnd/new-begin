package com.minjihong.never.eduservice.controller;


import com.minjihong.never.common.vo.R;
import com.minjihong.never.eduservice.entity.form.VideoInfoForm;
import com.minjihong.never.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-06
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    /**
     * 新增video
     *
     * @param videoInfoForm
     * @return
     */
    @PostMapping("add")
    public R saveVideo(@RequestBody VideoInfoForm videoInfoForm) {
        boolean flag = eduVideoService.saveVideoInfo(videoInfoForm);
        return flag ? R.ok() : R.error();
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("list/{id}")
    public R listVideoById(@PathVariable("id") String id) {
        VideoInfoForm videoInfoForm = eduVideoService.getVideoById(id);
        return R.ok().data("item", videoInfoForm);
    }

    /**
     * 根据id更新
     *
     * @param id
     * @param videoInfoForm
     * @return
     */
    @PutMapping("update/{id}")
    public R updateVideoById(@PathVariable String id, @RequestBody VideoInfoForm videoInfoForm) {
        videoInfoForm.setId(id);
        boolean flag = eduVideoService.updateVideoById(videoInfoForm);
        return flag ? R.ok() : R.error();
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R deleteVideoById(@PathVariable("id") String id) {
        boolean flag = eduVideoService.deleteById(id);
        return flag ? R.ok() : R.error();
    }

}


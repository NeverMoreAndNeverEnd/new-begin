package com.minjihong.never.eduservice.service;

import com.minjihong.never.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.minjihong.never.eduservice.entity.form.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-06
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean removeByCourseId(String courseId);

    boolean getCountByChapterId(String chapterId);

    boolean saveVideoInfo(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoById(String id);

    boolean updateVideoById(VideoInfoForm videoInfoForm);

    boolean deleteById(String id);
}

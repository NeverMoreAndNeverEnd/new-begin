package com.minjihong.never.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minjihong.never.eduservice.client.VodClient;
import com.minjihong.never.eduservice.entity.EduVideo;
import com.minjihong.never.eduservice.entity.form.VideoInfoForm;
import com.minjihong.never.eduservice.exception.EduException;
import com.minjihong.never.eduservice.mapper.EduVideoMapper;
import com.minjihong.never.eduservice.service.EduVideoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-06
 */
@Service
@Transactional
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    private VodClient vodClient;

    @Autowired
    public EduVideoServiceImpl(VodClient vodClient) {
        this.vodClient = vodClient;
    }

    @Override
    public boolean removeByCourseId(String courseId) {
        //删除云端视频
        //根据id查询所有视频列表
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(queryWrapper);

        //获取所有云端视频id
        List<String> videoSourceIdList = new ArrayList<>();
        for (EduVideo eduVideo : eduVideos) {
            String videoSourceId = eduVideo.getVideoSourceId();
            if (StringUtils.isNotEmpty(videoSourceId)) {
                videoSourceIdList.add(videoSourceId);
            }
        }
        //批量删除
        if (videoSourceIdList.size() > 0) {
            vodClient.removeVideoList(videoSourceIdList);
        }

        //删除video表记录
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        int delete = baseMapper.delete(wrapper);
        return delete > 0;
    }

    @Override
    public boolean getCountByChapterId(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        Integer count = baseMapper.selectCount(wrapper);
        return null != count && count > 0;
    }

    @Override
    public boolean saveVideoInfo(VideoInfoForm videoInfoForm) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm, eduVideo);
        boolean save = this.save(eduVideo);
        return save;
    }

    @Override
    public VideoInfoForm getVideoById(String id) {
        EduVideo video = this.getById(id);
        if (video == null) {
            throw new EduException(20001, "获取视频信息失败!");
        }
        VideoInfoForm videoInfoForm = new VideoInfoForm();
        BeanUtils.copyProperties(video, videoInfoForm);
        return videoInfoForm;
    }

    @Override
    public boolean updateVideoById(VideoInfoForm videoInfoForm) {
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm, video);
        boolean b = this.updateById(video);
        return b;
    }

    @Override
    public boolean deleteById(String id) {
        EduVideo video = this.getById(id);
        String videoSourceId = video.getVideoSourceId();
        // 删除云端视频
        if (StringUtils.isNotEmpty(videoSourceId)) {
            vodClient.removeVideo(videoSourceId);
        }
        boolean b = this.removeById(id);
        return b;
    }
}

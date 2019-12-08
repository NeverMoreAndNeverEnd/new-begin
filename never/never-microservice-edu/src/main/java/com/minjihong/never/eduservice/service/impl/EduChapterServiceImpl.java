package com.minjihong.never.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.minjihong.never.eduservice.entity.EduChapter;
import com.minjihong.never.eduservice.entity.EduVideo;
import com.minjihong.never.eduservice.entity.vo.ChapterVo;
import com.minjihong.never.eduservice.entity.vo.VideoVo;
import com.minjihong.never.eduservice.exception.EduException;
import com.minjihong.never.eduservice.mapper.EduChapterMapper;
import com.minjihong.never.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minjihong.never.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-06
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> nestedListByCourseId(String courseId) {
        List<ChapterVo> chapterVos = new ArrayList<>();

        //查询章节信息
        QueryWrapper<EduChapter> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id", courseId);
        wrapper1.orderByAsc("sort", "id");
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper1);

        //查询视频信息
        QueryWrapper<EduVideo> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("course_id", courseId);
        wrapper2.orderByAsc("sort", "id");
        List<EduVideo> eduVideos = eduVideoService.list(wrapper2);

        //填充vo数据
        for (EduChapter chapter : eduChapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVos.add(chapterVo);

            List<VideoVo> videoVos = new ArrayList<>();

            for (EduVideo video : eduVideos) {
                if (chapter.getId().equals(video.getChapterId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVos.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVos);
        }

        return chapterVos;
    }

    @Override
    public boolean removeByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        int delete = baseMapper.delete(wrapper);
        return delete > 0;
    }

    @Override
    public boolean removeChapterById(String id) {
        boolean b = eduVideoService.getCountByChapterId(id);
        if (b) {
            throw new EduException(20001, "该章节下有视频课程,请先删除视频课程");
        }
        boolean b1 = this.removeById(id);
        return b1;
    }
}

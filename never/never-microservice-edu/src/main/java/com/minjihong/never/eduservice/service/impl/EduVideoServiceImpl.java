package com.minjihong.never.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.minjihong.never.eduservice.entity.EduVideo;
import com.minjihong.never.eduservice.mapper.EduVideoMapper;
import com.minjihong.never.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-06
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public boolean removeByCourseId(String courseId) {
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
}

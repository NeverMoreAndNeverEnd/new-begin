package com.minjihong.never.eduservice.service;

import com.minjihong.never.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.minjihong.never.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-06
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> nestedListByCourseId(String courseId);

    boolean removeByCourseId(String courseId);

    boolean removeChapterById(String id);
}

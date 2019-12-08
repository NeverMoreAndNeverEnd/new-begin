package com.minjihong.never.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minjihong.never.common.constants.PriceConstants;
import com.minjihong.never.eduservice.entity.EduCourse;
import com.minjihong.never.eduservice.entity.EduCourseDescription;
import com.minjihong.never.eduservice.entity.form.CourseInfoForm;
import com.minjihong.never.eduservice.entity.query.QueryCourse;
import com.minjihong.never.eduservice.entity.vo.CoursePublishVo;
import com.minjihong.never.eduservice.exception.EduException;
import com.minjihong.never.eduservice.mapper.EduCourseMapper;
import com.minjihong.never.eduservice.service.EduChapterService;
import com.minjihong.never.eduservice.service.EduCourseDescriptionService;
import com.minjihong.never.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minjihong.never.eduservice.service.EduVideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-04
 */
@Transactional
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public String insertCourseInfo(CourseInfoForm courseInfoForm) {
        //添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus(EduCourse.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0) {
            throw new EduException(20001, "课程信息保存失败!");
        }
        //添加课程描述
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        if (!save) {
            throw new EduException(20001, "课程描述信息保存失败!");
        }
        return eduCourse.getId();
    }

    @Override
    public CourseInfoForm getCourseById(String id) {
        //查询课程基本信息
        EduCourse eduCourse = this.getById(id);
        if (eduCourse == null) {
            throw new EduException(20001, "没有查询到课程信息!");
        }
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse, courseInfoForm);
        //查询课程描述
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        courseInfoForm.setDescription(eduCourseDescription.getDescription());

        //设置显示精度
        courseInfoForm.setPrice(courseInfoForm.getPrice().setScale(PriceConstants.DISPLAY_SCALE, BigDecimal.ROUND_FLOOR));

        return courseInfoForm;
    }

    @Override
    public boolean updateCourseInfoById(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        boolean b = this.updateById(eduCourse);
        if (!b) {
            throw new EduException(20001, "修改课程信息失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        boolean b1 = eduCourseDescriptionService.updateById(eduCourseDescription);
        if (!b1) {
            throw new EduException(20001, "修改课程描述信息失败");
        }

        return b1;
    }

    @Override
    public void pageQuery(Page<EduCourse> coursePage, QueryCourse queryCourse) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        if (queryCourse == null) {
            baseMapper.selectPage(coursePage, wrapper);
            return;
        }
        String title = queryCourse.getTitle();
        String teacherId = queryCourse.getTeacherId();
        String subjectId = queryCourse.getSubjectId();
        String subjectParentId = queryCourse.getSubjectParentId();

        if (StringUtils.isNotEmpty(title)) {
            wrapper.like("title", title);
        }
        if (StringUtils.isNotEmpty(teacherId)) {
            wrapper.eq("teacher_id", teacherId);
        }
        if (StringUtils.isNotEmpty(subjectId)) {
            wrapper.ge("subject_id", subjectId);
        }
        if (StringUtils.isNotEmpty(subjectParentId)) {
            wrapper.ge("subject_parent_id", subjectParentId);
        }

        baseMapper.selectPage(coursePage, wrapper);

    }

    @Override
    public boolean removeCourseById(String id) {

        //删除video
        eduVideoService.removeByCourseId(id);
        //删除chapter
        eduChapterService.removeByCourseId(id);
        //删除课程描述
        eduCourseDescriptionService.removeByCourseId(id);

        boolean b = this.removeById(id);
        return b;
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {

        return baseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus(EduCourse.COURSE_NORMAL);
        eduCourse.setId(id);
        boolean b = this.updateById(eduCourse);
        return b;
    }
}

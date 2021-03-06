package com.minjihong.never.eduservice.mapper;

import com.minjihong.never.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minjihong.never.eduservice.entity.vo.CoursePublishVo;
import com.minjihong.never.eduservice.entity.vo.CourseWebInfo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-04
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo selectCoursePublishVoById(String id);

    CourseWebInfo getCourseWebInfoById(String id);

}

package com.minjihong.never.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.minjihong.never.eduservice.entity.EduTeacher;
import com.minjihong.never.eduservice.entity.query.QueryTeacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author jihong.min
 * @since 2019-11-27
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageListCondition(Page<EduTeacher> teacherPage, QueryTeacher queryTeacher);
}

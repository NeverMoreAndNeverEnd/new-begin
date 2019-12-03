package com.minjihong.never.eduservice.service;

import com.minjihong.never.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.minjihong.never.eduservice.entity.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-03
 */
public interface EduSubjectService extends IService<EduSubject> {

    List<String> importSubject(MultipartFile file);

    List<SubjectNestedVo> nestedList();

    boolean deleteSubjectById(String id);

    boolean addOneLevel(EduSubject eduSubject);

    boolean addTwoLevel(EduSubject eduSubject);
}

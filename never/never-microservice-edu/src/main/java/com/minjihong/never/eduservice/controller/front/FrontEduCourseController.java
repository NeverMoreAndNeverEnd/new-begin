package com.minjihong.never.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minjihong.never.common.vo.R;
import com.minjihong.never.eduservice.entity.EduCourse;
import com.minjihong.never.eduservice.entity.vo.ChapterVo;
import com.minjihong.never.eduservice.entity.vo.CourseWebInfo;
import com.minjihong.never.eduservice.service.EduChapterService;
import com.minjihong.never.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/front/course")
public class FrontEduCourseController {

    private EduCourseService eduCourseService;

    private EduChapterService eduChapterService;

    @Autowired
    public FrontEduCourseController(EduCourseService eduCourseService, EduChapterService eduChapterService) {
        this.eduCourseService = eduCourseService;
        this.eduChapterService = eduChapterService;
    }

    /**
     * 分页获取课程列表
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("{page}/{limit}")
    public R getFrontCourseListPage(@PathVariable("page") Long page,
                                    @PathVariable("limit") Long limit) {
        Page<EduCourse> eduCoursePage = new Page<>(page, limit);
        Map<String, Object> map = eduCourseService.getFrontTeacherList(eduCoursePage);
        return R.ok().data(map);
    }

    /**
     * 根据id查询讲师详情和所讲课程章节,以及所有小结
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public R getCourseById(@PathVariable("id") String id) {
        //根据课程id查询信息,包含 课程基本信息,课程描述,讲师信息,分类信息
        CourseWebInfo courseWebInfo = eduCourseService.selectInfoWebById(id);
        //根据课程id查询所有章节,章节中所有小结
        List<ChapterVo> chapterVos = eduChapterService.nestedListByCourseId(id);

        return R.ok().data("courseInfo", courseWebInfo).data("chapterVideoList", chapterVos);
    }

}

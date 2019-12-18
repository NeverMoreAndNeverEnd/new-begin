package com.minjihong.never.eduservice.controller;


import com.minjihong.never.common.vo.R;
import com.minjihong.never.eduservice.entity.EduChapter;
import com.minjihong.never.eduservice.entity.vo.ChapterVo;
import com.minjihong.never.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-06
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    private EduChapterService eduChapterService;

    @Autowired
    public EduChapterController(EduChapterService eduChapterService) {
        this.eduChapterService = eduChapterService;
    }

    /**
     * 根据课程id查询章节和小结
     *
     * @param courseId
     * @return
     */
    @GetMapping("list/{courseId}")
    public R nestedListByCourseId(@PathVariable("courseId") String courseId) {
        List<ChapterVo> list = eduChapterService.nestedListByCourseId(courseId);
        return R.ok().data("items", list);
    }

    /**
     * 新增章节
     *
     * @param eduChapter
     * @return
     */
    @PostMapping("add")
    public R save(@RequestBody EduChapter eduChapter) {
        boolean save = eduChapterService.save(eduChapter);
        return save ? R.ok() : R.error();
    }


    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("listById/{id}")
    public R getChapterById(@PathVariable("id") String id) {
        EduChapter chapter = eduChapterService.getById(id);
        return chapter != null ? R.ok().data("item", chapter) : R.error();
    }

    /**
     * 根据id更新
     *
     * @param id
     * @param eduChapter
     * @return
     */
    @PutMapping("update/{id}")
    public R updateChapterById(@PathVariable("id") String id, @RequestBody EduChapter eduChapter) {
        eduChapter.setId(id);
        boolean b = eduChapterService.updateById(eduChapter);
        return b ? R.ok() : R.error();
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public R deleteChapterById(@PathVariable("id") String id) {
        boolean b = eduChapterService.removeChapterById(id);
        return b ? R.ok().message("删除成功!") : R.error().message("删除失败");
    }


}


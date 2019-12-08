import request from '@/utils/request'

const apiurl = '/eduservice/chapter/'

export default {
// 获取章节详情
  getChapterNestedList(courseId) {
    return request({
      url: `${apiurl}/list/${courseId}`,
      method: 'get'
    })
  },
  // 新增
  addChapter(chapter) {
    return request({
      url: `${apiurl}/add/`,
      method: 'post',
      data: chapter
    })
  },
  // 根据id查询
  getChapterById(id) {
    return request({
      url: `${apiurl}/listById/${id}`,
      method: 'get'
    })
  },
  // 根据id更新
  updateChapterById(chapter) {
    return request({
      url: `${apiurl}/update/${chapter.id}`,
      method: 'put',
      data: chapter
    })
  },
  // 根据id删除
  deleteChapterById(id) {
    return request({
      url: `${apiurl}/delete/${id}`,
      method: 'delete'
    })
  }
}

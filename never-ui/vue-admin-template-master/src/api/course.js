import request from '@/utils/request'

const apiurl = '/eduservice/course/'

export default {
  // 添加课程信息
  addCourseInfo(courseInfo) {
    return request({
      url: `${apiurl}/add`,
      method: 'post',
      data: courseInfo
    })
  },
  // 根据id查询
  getCourseInfoById(id) {
    return request({
      url: `${apiurl}/getCourseInfo/${id}`,
      method: 'get'
    })
  },
  // 根据id修改
  updateCourseInfoById(courseInfo) {
    return request({
      url: `${apiurl}/updateCourseInfo/${courseInfo.id}`,
      method: 'put',
      data: courseInfo
    })
  },
  // 分页条件查询的方法
  getCoursePageList(page, limit, searchObj) {
    return request({
      url: `${apiurl}/pageList/${page}/${limit}`,
      method: 'post',
      data: searchObj
    })
  },
  // 根据id删除
  deleteCourseInfoById(id) {
    return request({
      url: `${apiurl}/delete/${id}`,
      method: 'delete'
    })
  }
}

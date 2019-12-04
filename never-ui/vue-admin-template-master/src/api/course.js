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
  }
}

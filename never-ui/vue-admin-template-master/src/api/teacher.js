import request from '@/utils/request'

const apiurl = '/eduservice/teacher/'
export default {
  // 分页条件查询的方法
  getTeacherPageList(page, limit, searchObj) {
    return request({
      // url: '/eduservice/teacher/codPageList/' + page + '/' + limit,
      url: `${apiurl}/codPageList/${page}/${limit}`,
      method: 'post',
      // 如果是jason格式,用data,如果不是,用params
      data: searchObj
    })
  },
  // 根据id删除的方法
  deleteTeacherById(id) {
    return request({
      url: '/eduservice/teacher/delete/' + id,
      method: 'delete'
    })
  },
  // 新增方法
  addTeacher(teacher) {
    return request({
      url: '/eduservice/teacher/addTeacher',
      method: 'post',
      // 如果是jason格式,用data,如果不是,用params
      data: teacher
    })
  },
  // 根据id查询
  getTeacherById(id) {
    return request({
      url: '/eduservice/teacher/getTeacherInfo/' + id,
      method: 'get'
    })
  },
  // 修改讲师
  updateTeacher(id, teacher) {
    return request({
      url: '/eduservice/teacher/updateTeacherInfo/' + id,
      method: 'put',
      data: teacher
    })
  }
}

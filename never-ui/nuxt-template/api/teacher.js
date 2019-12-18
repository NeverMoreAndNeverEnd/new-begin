import request from '@/utils/request'

const api_name = '/eduservice/front/teacher'

export default {

  // 获取分页讲师列表
  getPageList(page, limit) {
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'get'
    })
  },

  getTeacherInfoId(id) {
    return request({
      url: `${api_name}/${id}`,
      method: 'get'
    })
  }

}

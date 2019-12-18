import request from '@/utils/request'

const api_name = '/eduservice/front/course'

export default {

  // 获取分页讲师列表
  getCourseListPage(page, limit) {
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'get'
    })
  },
  getCourseInfoId(id) {
    return request({
      url: `${api_name}/${id}`,
      method: 'get'
    })
  }

}

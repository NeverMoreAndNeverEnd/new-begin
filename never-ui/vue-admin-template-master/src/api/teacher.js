// eslint-disable-next-line no-unused-vars
import request from '@/utils/request'

export default {
  // 分页条件查询的方法
  getTeacherPageList(page, limit, searchObj) {
    return request({
      url: '/eduservice/teacher/codPageList/' + page + '/' + limit,
      method: 'post',
      // 如果是jason格式,用data,如果不是,用params
      data: searchObj
    })
  }
}

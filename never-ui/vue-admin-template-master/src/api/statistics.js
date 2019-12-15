import request from '@/utils/request'

const apiurl = '/statistics/daily'

export default {
  // 获取统计数据
  createStatistics(day) {
    return request({
      url: `${apiurl}/getRegisterNum/${day}`,
      method: 'get'
    })
  }
}

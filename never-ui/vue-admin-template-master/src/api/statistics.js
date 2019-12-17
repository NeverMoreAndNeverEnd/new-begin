import request from '@/utils/request'

const apiurl = '/statistics/daily'

export default {
  // 获取统计数据
  createStatistics(day) {
    return request({
      url: `${apiurl}/getRegisterNum/${day}`,
      method: 'get'
    })
  },
  // 获取时间范围内统计数据
  getCountData(searchObj) {
    return request({
      url: `${apiurl}/getCountData/${searchObj.type}/${searchObj.begin}/${searchObj.end}`,
      method: 'get'
    })
  }

}

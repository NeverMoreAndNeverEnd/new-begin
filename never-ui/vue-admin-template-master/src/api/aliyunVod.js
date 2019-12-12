import request from '@/utils/request'

const apiurl = '/video/'
export default {
  // 根据id删除
  deleteAliyunVideoInfoById(id) {
    return request({
      url: `${apiurl}/delete/${id}`,
      method: 'delete'
    })
  }
}

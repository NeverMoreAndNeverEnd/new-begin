import request from '@/utils/request'

const apiurl = '/eduservice/video/'
export default {
  // 新增
  addVideoInfo(videoInfo) {
    return request({
      url: `${apiurl}/add`,
      method: 'post',
      data: videoInfo
    })
  },
  // 根据id查询
  getVideoInfoById(id) {
    return request({
      url: `${apiurl}/list/${id}`,
      method: 'get'
    })
  },
  // 根据id更新
  updateVideoInfoById(videoInfo) {
    return request({
      url: `${apiurl}/update/${videoInfo.id}`,
      method: 'put',
      data: videoInfo
    })
  },
  // 根据id删除
  deleteVideoInfoById(id) {
    return request({
      url: `${apiurl}/delete/${id}`,
      method: 'delete'
    })
  }

}

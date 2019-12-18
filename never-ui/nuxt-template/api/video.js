import request from '@/utils/request'

const api_name = '/video/'

export default {

  //获取播放凭证
  getVideoPlayAuth(vid) {
    return request({
      url: `${api_name}/getPlayAuth/${vid}`,
      method: 'get'
    })
  }

}

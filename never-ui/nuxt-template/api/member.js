import request from '@/utils/request'

const api_name = '/ucenter/member'

export default {

  getInfoByToken(token) {
    return request({
      url: `${api_name}/info/${token}`,
      method: 'post'
    })
  }

}

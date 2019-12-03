import request from '@/utils/request'

const apiurl = '/eduservice/subject/'

export default {
  // 获取树形结构
  getNestedTreeList() {
    return request({
      url: `${apiurl}/list`,
      method: 'get'
    })
  },
  // 根据id删除
  removeSubjectById(id) {
    return request({
      url: `${apiurl}/delete/${id}`,
      method: 'delete'
    })
  },
  // 添加一级分类
  addSubjectOneLevel(subject) {
    return request({
      url: `${apiurl}/addOneLevel`,
      method: 'post',
      data: subject
    })
  },
  // 添加二级分类
  addSubjectTwoLevel(subject) {
    return request({
      url: `${apiurl}/addTwoLevel`,
      method: 'post',
      data: subject
    })
  }
}

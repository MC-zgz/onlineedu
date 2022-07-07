import request from '@/utils/request'

export default {
  //首页展示课程信息、讲师信息
  getCourseAndTeacherList () {
    return request({
      url: `/eduservice/index/getCourseAndTeacherList`,
      method: 'get'
    })
  }
}

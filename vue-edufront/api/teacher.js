import request from '@/utils/request'

export default {
  //分页查询讲师列表
  getTeacherApiByPage (current, limit) {
    return request({
      url: `/eduservice/teacherapi/getTeacherApiByPage/${current}/${limit}`,
      method: 'get'
    })
  },
  //前台名师详情
  getTeacherCourseById (id) {
    return request({
      url: `/eduservice/teacherapi/getTeacherCourseById/${id}`,
      method: 'get'
    })
  },
}

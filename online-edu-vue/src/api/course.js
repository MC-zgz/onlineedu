import request from '@/utils/request'

export default {
  //添加课程信息
  addCourseInfo (courseInfoForm) {
    return request({
      url: `/eduservice/edu-course/addCourseInfo`,
      method: 'post',
      data: courseInfoForm
    })
  },
  //根据id查询课程信息
  getCourseInfoById (id) {
    return request({
      url: `/eduservice/edu-course/getCourseInfoById/${id}`,
      method: 'get'
    })
  },
  //修改课程信息
  updateCourseInfo (courseInfoForm) {
    return request({
      url: `/eduservice/edu-course/updateCourseInfo`,
      method: 'post',
      data: courseInfoForm
    })
  },
  //根据课程id查询课程发布信息
  getCoursePublishById (id) {
    return request({
      url: `/eduservice/edu-course/getCoursePublishById/${id}`,
      method: 'get'
    })
  },
  //根据课程id发布课程
  publishCourse (id) {
    return request({
      url: `/eduservice/edu-course/publishCourse/${id}`,
      method: 'post'
    })
  },
  //根据课程id查询课程发布信息
  getAllCourse () {
    return request({
      url: `/eduservice/edu-course/getAllCourse`,
      method: 'get'
    })
  },
  //根据id删除课程相关信息
  delCourseInfo (id) {
    return request({
      url: `/eduservice/edu-course/delCourseInfo/${id}`,
      method: 'delete'
    })
  },


}

import request from '@/utils/request'

export default {
  //查询所有课程分类
  getAllSubject () {
    return request({
      url: `/eduservice/edu-subject/subjectList`,
      method: 'get'
    })
  },

}

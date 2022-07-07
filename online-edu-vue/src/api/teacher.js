import request from '@/utils/request'

export default {
  //带条件分页查询教师列表
  getTeacherByPageVo (current, limit, teacherQuery) {
    return request({
      url: `/eduservice/edu-teacher/getTeacherByPageVo/${current}/${limit}`,
      method: 'post',
      data: teacherQuery  //转化json传递
    })
  },
  //根据id删除讲师
  deleteById (id) {
    return request({
      url: `/eduservice/edu-teacher/delete/${id}`,
      method: 'delete',
    })
  },
  //添加讲师
  addTeacher (eduTeacher) {
    return request({
      url: `/eduservice/edu-teacher/addTeacher`,
      method: 'post',
      data: eduTeacher
    })
  },
  //
  //根据id查询讲师
  getAllById (id) {
    return request({
      url: `/eduservice/edu-teacher/getAllById/${id}`,
      method: 'get',
    })
  },
  //修改讲师
  updateTeacher (eduTeacher) {
    return request({
      url: `/eduservice/edu-teacher/updateTeacher`,
      method: 'post',
      data: eduTeacher
    })
  },
  //所有讲师列表
  getAllTeacher () {
    return request({
      url: `/eduservice/edu-teacher/getAll`,
      method: 'get'
    })
  }
}

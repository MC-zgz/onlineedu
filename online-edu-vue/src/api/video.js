import request from '@/utils/request'

export default {

  //添加小节
  addVideo (eduVideo) {
    return request({
      url: `/eduservice/edu-video/addVideo`,
      method: 'post',
      data: eduVideo
    })
  },
  //根据id删除小节
  delVideo (id) {
    return request({
      url: `/eduservice/edu-video/delVideo/${id}`,
      method: 'delete'
    })
  },
  //根据id查询小节
  getVideoById (id) {
    return request({
      url: `/eduservice/edu-video/getVideoById/${id}`,
      method: 'get'
    })
  },
  //修改小节
  updVideo (eduVideo) {
    return request({
      url: `/eduservice/edu-video/updVideo`,
      method: 'post',
      data: eduVideo
    })
  },

}

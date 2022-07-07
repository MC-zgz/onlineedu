import request from '@/utils/request'

export default {
  //用户注册
  register (registerVo) {
    return request({
      url: `/ucenter/member/register`,
      method: 'post',
      data: registerVo
    })
  },

  //短信发送
  send () {
    return request({
      url: `/edumsm/sms/send/${phone}`,
      method: 'get'
    })
  }
}
import request from '@/utils/request'

export default {
  //查询所有banner信息
  getAllBanner () {
    return request({
      url: `/cmsservice/crm-banner/getAllBanner`,
      method: 'get'
    })
  }
}

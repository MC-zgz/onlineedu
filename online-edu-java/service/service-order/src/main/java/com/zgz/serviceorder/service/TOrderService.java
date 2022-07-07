package com.zgz.serviceorder.service;

import com.zgz.serviceorder.pojo.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author willie
 * @since 2021-12-06
 */
public interface TOrderService extends IService<TOrder> {

    String createOrder(String courseId, String memberId);
}

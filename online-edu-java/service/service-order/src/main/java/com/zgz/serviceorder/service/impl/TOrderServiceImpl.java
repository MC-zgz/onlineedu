package com.zgz.serviceorder.service.impl;

import com.zgz.commonutils.vo.CourseWebVoForOrder;
import com.zgz.commonutils.vo.MemberForOrder;
import com.zgz.servicebase.handler.OnlineEduException;
import com.zgz.serviceorder.client.EduClient;
import com.zgz.serviceorder.client.UcenterClient;
import com.zgz.serviceorder.pojo.TOrder;
import com.zgz.serviceorder.mapper.TOrderMapper;
import com.zgz.serviceorder.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgz.serviceorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author willie
 * @since 2021-12-06
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        //1.跨模块获取课程信息
        CourseWebVoForOrder courseInfoForOrder = eduClient.getCourseInfoForOrder(courseId);
        //1.1进行校验
        if(courseInfoForOrder == null){
            throw new OnlineEduException(20001,"获取课程信息失败！");
        }
        //2.跨模块获取用户信息
        MemberForOrder ucenterForOrder = ucenterClient.getUcenterForOrder(memberId);
        //1.1进行校验
        if(ucenterForOrder == null){
            throw new OnlineEduException(20001,"获取用户信息失败！");
        }
        //3.生成订单编号
        String orderNo = OrderNoUtil.getOrderNo();
        //4.封装数据  存入数据库
        TOrder tOrder = new TOrder();
        tOrder.setOrderNo(orderNo);
        tOrder.setCourseId(courseId);
        tOrder.setCourseTitle(courseInfoForOrder.getTitle());
        tOrder.setCourseCover(courseInfoForOrder.getCover());
        tOrder.setTeacherName(courseInfoForOrder.getTeacherName());
        tOrder.setTotalFee(courseInfoForOrder.getPrice());
        tOrder.setMemberId(memberId);
        tOrder.setMobile(ucenterForOrder.getMobile());
        tOrder.setNickname(ucenterForOrder.getNickname());
        tOrder.setStatus(0);//未支付
        tOrder.setPayType(3);//1：微信 2：支付宝 3 其他
        baseMapper.insert(tOrder);
        return orderNo;
    }
}

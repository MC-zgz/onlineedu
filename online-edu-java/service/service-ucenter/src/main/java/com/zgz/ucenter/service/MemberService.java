package com.zgz.ucenter.service;

import com.zgz.ucenter.pojo.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zgz.ucenter.pojo.Vo.LoginVo;
import com.zgz.ucenter.pojo.Vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author willie
 * @since 2021-11-30
 */
public interface MemberService extends IService<Member> {

    void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    Integer countRegister(String day);
}

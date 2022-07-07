package com.zgz.ucenter.mapper;

import com.zgz.ucenter.pojo.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author willie
 * @since 2021-11-30
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer countRegister(String day);
}

package com.zgz.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgz.commonutils.utils.JwtUtils;
import com.zgz.ucenter.pojo.Vo.LoginVo;
import com.zgz.ucenter.utils.MD5;
import com.zgz.servicebase.handler.OnlineEduException;
import com.zgz.ucenter.pojo.Member;
import com.zgz.ucenter.mapper.MemberMapper;
import com.zgz.ucenter.pojo.Vo.RegisterVo;
import com.zgz.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author willie
 * @since 2021-11-30
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void register(RegisterVo registerVo) {
        //1 获取参数，验空
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
//        String code = registerVo.getCode();
        String password = registerVo.getPassword();
        if(StringUtils.isEmpty(nickname)||StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new OnlineEduException(20001,"注册信息缺失");
        }
        //2 验证手机号是否重复
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count>0){
            throw new OnlineEduException(20001,"手机号重复");
        }
//        //3 验证短信验证码
//        String redisCode = redisTemplate.opsForValue().get(mobile);
//        if(!code.equals(redisCode)){
//            throw new GuliException(20001,"验证码错误");
//        }

        //4 使用MD5加密密码
        String md5Password = MD5.encrypt(password);
        //5 补充信息后插入数据库
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPassword(md5Password);
        member.setAvatar("https://zgz-online-education-file.oss-cn-guangzhou.aliyuncs.com/user.jpg");
        member.setIsDisabled(false);
        baseMapper.insert(member);

    }

    @Override
    public String login(LoginVo loginVo) {
        //1 获取参数，验空
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new OnlineEduException(20001,"手机号或密码不能为空");
        }
        //2 根据手机号获取用户信息
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Member member = baseMapper.selectOne(queryWrapper);
        if(member==null){
            throw new OnlineEduException(20001,"手机号或密码有误");
        }
        //3 密码加密后验证密码
        String md5Password = MD5.encrypt(password);
        if(!md5Password.equals(member.getPassword())){
            throw new OnlineEduException(20001,"手机号或密码有误");
        }
        //4生成token字符串
        String token = JwtUtils.getJwtToken(member.getId(),member.getNickname());

        return token;

    }

    @Override
    public Integer countRegister(String day) {
        Integer count = baseMapper.countRegister(day);
        return count;
    }
}

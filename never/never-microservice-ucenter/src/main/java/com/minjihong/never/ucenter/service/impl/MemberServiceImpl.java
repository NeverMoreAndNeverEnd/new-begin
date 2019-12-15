package com.minjihong.never.ucenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minjihong.never.ucenter.entity.Member;
import com.minjihong.never.ucenter.mapper.MemberMapper;
import com.minjihong.never.ucenter.service.MemberService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-15
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {


    @Override
    public Integer countRegisterNum(String day) {

        return baseMapper.countRegisterNum(day);
    }
}

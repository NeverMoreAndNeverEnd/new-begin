package com.minjihong.never.ucenter.service;

import com.minjihong.never.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-15
 */
public interface MemberService extends IService<Member> {

    Integer countRegisterNum(String day);

    Member getByOpenid(String openid);
}

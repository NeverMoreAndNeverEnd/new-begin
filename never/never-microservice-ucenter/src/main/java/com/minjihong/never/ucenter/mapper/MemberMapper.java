package com.minjihong.never.ucenter.mapper;

import com.minjihong.never.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-15
 */
@Component
public interface MemberMapper extends BaseMapper<Member> {

    Integer countRegisterNum(String day);

}

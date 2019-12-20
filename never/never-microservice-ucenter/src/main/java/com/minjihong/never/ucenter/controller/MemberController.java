package com.minjihong.never.ucenter.controller;


import com.minjihong.never.common.vo.R;
import com.minjihong.never.ucenter.entity.vo.LoginInfoVo;
import com.minjihong.never.ucenter.service.MemberService;
import com.minjihong.never.ucenter.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author jihong.min
 * @since 2019-12-15
 */
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    /**
     * 统计某一天注册人数
     *
     * @param day
     * @return
     */
    @GetMapping("countRegisterNum/{day}")
    public R countRegisterNum(@PathVariable("day") String day) {
        Integer num = memberService.countRegisterNum(day);
        return R.ok().data("countRegister", num);
    }

    @PostMapping("info/{token}")
    public R getInfoByToken(@PathVariable String token){
        Claims claims = JwtUtils.checkJwt(token);
        String nickname = (String)claims.get("nickname");
        String avatar = (String)claims.get("avatar");
        String id = (String)claims.get("id");
        LoginInfoVo loginInfoVo = new LoginInfoVo();
        loginInfoVo.setId(id);
        loginInfoVo.setAvatar(avatar);
        loginInfoVo.setNickname(nickname);
        return R.ok().data("loginInfo", loginInfoVo);
    }


}


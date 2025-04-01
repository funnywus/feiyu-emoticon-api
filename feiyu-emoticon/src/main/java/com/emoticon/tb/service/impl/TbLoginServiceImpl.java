package com.emoticon.tb.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.emoticon.common.config.WeXinConfig;
import com.emoticon.common.constant.Constants;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.domain.model.LoginBody;
import com.emoticon.common.utils.SecurityUtils;
import com.emoticon.tb.domain.TbUser;
import com.emoticon.tb.service.ITbLoginService;
import com.emoticon.tb.service.ITbUserService;
import lombok.AllArgsConstructor;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWechatMiniProgramRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * emoticon-framework登录注册服务实现类
 */
@AllArgsConstructor
@Service
public class TbLoginServiceImpl implements ITbLoginService {

    private final ITbUserService iTbUserService;
    private final WeXinConfig weXinConfig;

    /**
     * 登录
     */
    @Override
    public AjaxResult login(LoginBody loginBody) {
        String username = loginBody.getUsername();
        String password = loginBody.getPassword();

        // 根据用户名查询用户
        TbUser user = iTbUserService.selectFlowerUserByUserName(username);
        if (user == null) {
            return AjaxResult.error("用户不存在");
        }

        // 验证密码
        if (!SecurityUtils.matchesPassword(password, user.getPassword())) {
            return AjaxResult.error("密码错误");
        }

        // 使用sa-token登录，使用userId作为登录标识
        StpUtil.login(user.getUserId());

        AjaxResult ajax = AjaxResult.success();
        ajax.put(Constants.TOKEN, StpUtil.getTokenValue());

        return ajax;
    }

    /**
     * 注册
     */
    @Override
    public AjaxResult register(LoginBody loginBody) {
        String username = loginBody.getUsername();
        String password = loginBody.getPassword();

        // 检查用户名是否已存在
        if (iTbUserService.selectFlowerUserByUserName(username) != null) {
            return AjaxResult.error("用户名已存在");
        }

        // 创建新用户
        TbUser user = new TbUser();
        user.setUserName(username);
        user.setNickName(username);
        // 加密密码
        user.setPassword(SecurityUtils.encryptPassword(password));

        // 保存用户
        if (iTbUserService.insertFlowerUser(user) > 0) {
            return AjaxResult.success("注册成功");
        }

        return AjaxResult.error("注册失败，请稍后重试");
    }

    /**
     * 获取登录用户信息
     */
    @Override
    public AjaxResult getInfo() {
        if (!StpUtil.isLogin()) {
            return AjaxResult.error("未登录");
        }

        // 获取当前登录用户ID
        Long userId = StpUtil.getLoginIdAsLong();
        // 查询用户信息
        TbUser user = iTbUserService.selectUserById(userId);
        if (user == null) {
            return AjaxResult.error("用户不存在");
        }

        // 清除敏感信息
        user.setPassword(null);

        return AjaxResult.success("获取信息成功", user);
    }

    /**
     * 退出登录
     */
    @Override
    public AjaxResult logout() {
        StpUtil.logout();
        return AjaxResult.success("退出成功");
    }

    /**
     * 微信一键登录
     * @param callback
     * @return
     */
    @Override
    public AjaxResult wxMiniProgramLogin(AuthCallback callback) {
        AuthRequest authRequest = new AuthWechatMiniProgramRequest(AuthConfig.builder()
                .clientId(weXinConfig.getAppId())
                .clientSecret(weXinConfig.getSecret())
                .ignoreCheckRedirectUri(true)
                .ignoreCheckState(true)
                .build());

        AuthResponse<AuthUser> response = authRequest.login(callback);
        if (!response.ok()) {
            return AjaxResult.error("微信授权失败");
        }

        AuthUser wxUser = response.getData();
        System.out.println("返回信息");
        System.out.println(JSON.toJSONString(wxUser));
        String openid = wxUser.getToken().getOpenId();  // 获取微信openid

        // 根据openid查询用户
        TbUser user = iTbUserService.selectFlowerUserByOpenid(openid);
        AjaxResult ajax = AjaxResult.success("登录成功");

        if (user == null) {
            // 用户不存在，创建新用户
            user = new TbUser();
            user.setOpenid(openid);
            user.setUserName("微信用户");
            user.setNickName("微信用户");
            user.setLoginDate(new Date());
            if (iTbUserService.insertFlowerUser(user) <= 0) {
                return AjaxResult.error("用户创建失败");
            }
            ajax.put("isNewUser", true);
        } else {
            user.setLoginDate(new Date());
            iTbUserService.updateFlowerUser(user);
        }

        // 使用sa-token登录
        StpUtil.login(user.getUserId());

        ajax.put(Constants.TOKEN, StpUtil.getTokenValue());
        ajax.put("openid", user.getOpenid());

        return ajax;
    }
}

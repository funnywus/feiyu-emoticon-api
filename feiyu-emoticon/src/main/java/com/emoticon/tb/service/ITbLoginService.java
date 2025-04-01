package com.emoticon.tb.service;

import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.domain.model.LoginBody;
import me.zhyd.oauth.model.AuthCallback;

/**
 * 登录注册服务接口
 */
public interface ITbLoginService {

    /**
     * 登录
     *
     * @param loginBody 登录信息
     * @return 登录结果
     */
    public AjaxResult login(LoginBody loginBody);

    /**
     * 注册
     *
     * @param loginBody 注册信息
     * @return 注册结果
     */
    public AjaxResult register(LoginBody loginBody);

    /**
     * 获取登录用户信息
     *
     * @return 用户信息
     */
    public AjaxResult getInfo();

    /**
     * 退出登录
     *
     * @return 退出结果
     */
    public AjaxResult logout();

    AjaxResult wxMiniProgramLogin(AuthCallback callback);

}

package com.emoticon.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 微信小程序信息
 * @Author: funnywus
 * @Date: 2025/3/21 23:59
 */
@Data
@Component
@ConfigurationProperties(prefix = "wexin")
public class WeXinConfig {

    /**
     * 小程序appId
     */
    private String appId;

    /**
     * 小程序secret
     */
    private String secret;
}

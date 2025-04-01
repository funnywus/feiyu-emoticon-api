package com.emoticon.common.utils;

import java.util.UUID;

/**
 * ID生成工具类
 */
public class IdUtil {

    /**
     * 生成格式化的ID
     * 格式: yyyyMMddHHmmssSSS_UUID前8位
     * 示例: 20250326221649463_298cec17
     *
     * @return 格式化的ID字符串
     */
    public static String generateFormattedId() {
        // 获取当前时间戳部分 (17位)
        String timestamp = DateUtils.dateTimeNow("yyyyMMddHHmmssSSS");

        // 获取UUID并截取前8位
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String shortUuid = uuid.substring(0, 8);

        // 组合时间戳和UUID前8位
        return timestamp + "_" + shortUuid;
    }
}

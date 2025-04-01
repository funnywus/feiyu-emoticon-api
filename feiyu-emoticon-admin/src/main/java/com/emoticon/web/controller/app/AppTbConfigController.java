package com.emoticon.web.controller.app;

import cn.dev33.satoken.annotation.SaIgnore;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.tb.domain.TbConfig;
import com.emoticon.tb.service.ITbConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 全局配置Controller
 *
 * @author feiyu
 * @date 2025-03-22
 */

@RestController
@RequestMapping("/app/tb/config")
public class AppTbConfigController extends BaseController {
    @Autowired
    private ITbConfigService tbConfigService;

    /**
     * 获取全局配置详细信息
     */
    @SaIgnore
    @GetMapping()
    public AjaxResult getInfo() {
        List<TbConfig> list = tbConfigService.selectTbConfigList(null);
        if (list.size() > 0) {
            return success(list.get(0));
        }
        return success();
    }

}

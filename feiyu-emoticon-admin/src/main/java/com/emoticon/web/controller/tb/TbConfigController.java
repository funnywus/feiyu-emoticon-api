package com.emoticon.web.controller.tb;

import com.emoticon.common.annotation.Log;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.page.TableDataInfo;
import com.emoticon.common.enums.BusinessType;
import com.emoticon.common.utils.poi.ExcelUtil;
import com.emoticon.tb.domain.TbConfig;
import com.emoticon.tb.service.ITbConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 全局配置Controller
 *
 * @author feiyu
 * @date 2025-03-22
 */

@RestController
@RequestMapping("/tb/config")
public class TbConfigController extends BaseController {
    @Autowired
    private ITbConfigService tbConfigService;

    /**
     * 查询全局配置列表
     */
    @PreAuthorize("@ss.hasPermi('tb:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbConfig tbConfig) {
        startPage();
        List<TbConfig> list = tbConfigService.selectTbConfigList(tbConfig);
        return getDataTable(list);
    }

    /**
     * 导出全局配置列表
     */
    @PreAuthorize("@ss.hasPermi('tb:config:export')")
    @Log(title = "全局配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbConfig tbConfig) {
        List<TbConfig> list = tbConfigService.selectTbConfigList(tbConfig);
        ExcelUtil<TbConfig> util = new ExcelUtil<TbConfig>(TbConfig.class);
        util.exportExcel(response, list, "全局配置数据");
    }

    /**
     * 获取全局配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('tb:config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(tbConfigService.selectTbConfigById(id));
    }

    /**
     * 新增全局配置
     */
    @PreAuthorize("@ss.hasPermi('tb:config:add')")
    @Log(title = "全局配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbConfig tbConfig) {
        return toAjax(tbConfigService.insertTbConfig(tbConfig));
    }

    /**
     * 修改全局配置
     */
    @PreAuthorize("@ss.hasPermi('tb:config:edit')")
    @Log(title = "全局配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbConfig tbConfig) {
        return toAjax(tbConfigService.updateTbConfig(tbConfig));
    }

    /**
     * 删除全局配置
     */
    @PreAuthorize("@ss.hasPermi('tb:config:remove')")
    @Log(title = "全局配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tbConfigService.deleteTbConfigByIds(ids));
    }
}

package com.emoticon.web.controller.tb;

import com.emoticon.common.annotation.Log;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.page.TableDataInfo;
import com.emoticon.common.enums.BusinessType;
import com.emoticon.common.utils.poi.ExcelUtil;
import com.emoticon.tb.domain.TbWelfare;
import com.emoticon.tb.service.ITbWelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 福利列表Controller
 *
 * @author feiyu
 * @date 2025-03-22
 */
@RestController
@RequestMapping("/tb/welfare")
public class TbWelfareController extends BaseController
{
    @Autowired
    private ITbWelfareService tbWelfareService;

    /**
     * 查询福利列表列表
     */
    @PreAuthorize("@ss.hasPermi('tb:welfare:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbWelfare tbWelfare)
    {
        startPage();
        List<TbWelfare> list = tbWelfareService.selectTbWelfareList(tbWelfare);
        return getDataTable(list);
    }

    /**
     * 导出福利列表列表
     */
    @PreAuthorize("@ss.hasPermi('tb:welfare:export')")
    @Log(title = "福利列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbWelfare tbWelfare)
    {
        List<TbWelfare> list = tbWelfareService.selectTbWelfareList(tbWelfare);
        ExcelUtil<TbWelfare> util = new ExcelUtil<TbWelfare>(TbWelfare.class);
        util.exportExcel(response, list, "福利列表数据");
    }

    /**
     * 获取福利列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('tb:welfare:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tbWelfareService.selectTbWelfareById(id));
    }

    /**
     * 新增福利列表
     */
    @PreAuthorize("@ss.hasPermi('tb:welfare:add')")
    @Log(title = "福利列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbWelfare tbWelfare)
    {
        return toAjax(tbWelfareService.insertTbWelfare(tbWelfare));
    }

    /**
     * 修改福利列表
     */
    @PreAuthorize("@ss.hasPermi('tb:welfare:edit')")
    @Log(title = "福利列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbWelfare tbWelfare)
    {
        return toAjax(tbWelfareService.updateTbWelfare(tbWelfare));
    }

    /**
     * 删除福利列表
     */
    @PreAuthorize("@ss.hasPermi('tb:welfare:remove')")
    @Log(title = "福利列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbWelfareService.deleteTbWelfareByIds(ids));
    }
}

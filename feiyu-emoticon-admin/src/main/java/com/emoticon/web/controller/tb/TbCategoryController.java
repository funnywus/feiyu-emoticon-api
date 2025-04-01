package com.emoticon.web.controller.tb;

import com.emoticon.common.annotation.Log;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.page.TableDataInfo;
import com.emoticon.common.enums.BusinessType;
import com.emoticon.common.utils.poi.ExcelUtil;
import com.emoticon.tb.domain.TbCategory;
import com.emoticon.tb.service.ITbCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 表情包分类Controller
 *
 * @author feiyu
 * @date 2025-03-22
 */
@RestController
@RequestMapping("/tb/category")
public class TbCategoryController extends BaseController
{
    @Autowired
    private ITbCategoryService tbCategoryService;

    /**
     * 查询表情包分类列表
     */
    @PreAuthorize("@ss.hasPermi('tb:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbCategory tbCategory)
    {
        startPage();
        List<TbCategory> list = tbCategoryService.selectTbCategoryList(tbCategory);
        return getDataTable(list);
    }

    /**
     * 导出表情包分类列表
     */
    @PreAuthorize("@ss.hasPermi('tb:category:export')")
    @Log(title = "表情包分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbCategory tbCategory)
    {
        List<TbCategory> list = tbCategoryService.selectTbCategoryList(tbCategory);
        ExcelUtil<TbCategory> util = new ExcelUtil<TbCategory>(TbCategory.class);
        util.exportExcel(response, list, "表情包分类数据");
    }

    /**
     * 获取表情包分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('tb:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(tbCategoryService.selectTbCategoryById(id));
    }

    /**
     * 新增表情包分类
     */
    @PreAuthorize("@ss.hasPermi('tb:category:add')")
    @Log(title = "表情包分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbCategory tbCategory)
    {
        return toAjax(tbCategoryService.insertTbCategory(tbCategory));
    }

    /**
     * 修改表情包分类
     */
    @PreAuthorize("@ss.hasPermi('tb:category:edit')")
    @Log(title = "表情包分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbCategory tbCategory)
    {
        return toAjax(tbCategoryService.updateTbCategory(tbCategory));
    }

    /**
     * 删除表情包分类
     */
    @PreAuthorize("@ss.hasPermi('tb:category:remove')")
    @Log(title = "表情包分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tbCategoryService.deleteTbCategoryByIds(ids));
    }
}

package com.emoticon.web.controller.tb;

import com.emoticon.common.annotation.Log;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.page.TableDataInfo;
import com.emoticon.common.enums.BusinessType;
import com.emoticon.common.utils.poi.ExcelUtil;
import com.emoticon.tb.domain.TbStickerResource;
import com.emoticon.tb.service.ITbStickerResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 表情包资源Controller
 *
 * @author feiyu
 * @date 2025-03-23
 */
@RestController
@RequestMapping("/tb/stickerResource")
public class TbStickerResourceController extends BaseController
{
    @Autowired
    private ITbStickerResourceService tbStickerResourceService;

    /**
     * 查询表情包资源列表
     */
    @PreAuthorize("@ss.hasPermi('tb:resource:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbStickerResource tbStickerResource)
    {
        startPage();
        List<TbStickerResource> list = tbStickerResourceService.selectTbStickerResourceList(tbStickerResource);
        return getDataTable(list);
    }

    /**
     * 导出表情包资源列表
     */
    @PreAuthorize("@ss.hasPermi('tb:resource:export')")
    @Log(title = "表情包资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbStickerResource tbStickerResource)
    {
        List<TbStickerResource> list = tbStickerResourceService.selectTbStickerResourceList(tbStickerResource);
        ExcelUtil<TbStickerResource> util = new ExcelUtil<TbStickerResource>(TbStickerResource.class);
        util.exportExcel(response, list, "表情包资源数据");
    }

    /**
     * 获取表情包资源详细信息
     */
    @PreAuthorize("@ss.hasPermi('tb:resource:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(tbStickerResourceService.selectTbStickerResourceById(id));
    }

    /**
     * 新增表情包资源
     */
    @PreAuthorize("@ss.hasPermi('tb:resource:add')")
    @Log(title = "表情包资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbStickerResource tbStickerResource)
    {
        return toAjax(tbStickerResourceService.insertTbStickerResource(tbStickerResource));
    }

    /**
     * 修改表情包资源
     */
    @PreAuthorize("@ss.hasPermi('tb:resource:edit')")
    @Log(title = "表情包资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbStickerResource tbStickerResource)
    {
        return toAjax(tbStickerResourceService.updateTbStickerResource(tbStickerResource));
    }

    /**
     * 删除表情包资源
     */
    @PreAuthorize("@ss.hasPermi('tb:resource:remove')")
    @Log(title = "表情包资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tbStickerResourceService.deleteTbStickerResourceByIds(ids));
    }

    /**
     * 批量添加资源到合集
     */
    @Log(title = "表情包批量添加资源到合集", businessType = BusinessType.INSERT)
    @Transactional
    @PostMapping("/batchAdd")
    public AjaxResult batchAddToSticker(
            @RequestParam String categoryId,
            @RequestParam String stickerName,
                                       @RequestBody List<TbStickerResource> resources) {
        return toAjax(tbStickerResourceService.batchInsertTbStickerResource(categoryId, stickerName, resources));
    }
}

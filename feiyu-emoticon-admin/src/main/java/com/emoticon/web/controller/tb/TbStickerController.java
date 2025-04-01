package com.emoticon.web.controller.tb;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emoticon.common.annotation.Log;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.enums.BusinessType;
import com.emoticon.tb.domain.TbSticker;
import com.emoticon.tb.service.ITbStickerService;
import com.emoticon.common.utils.poi.ExcelUtil;
import com.emoticon.common.core.page.TableDataInfo;

/**
 * 表情包Controller
 *
 * @author feiyu
 * @date 2025-03-22
 */
@RestController
@RequestMapping("/tb/sticker")
public class TbStickerController extends BaseController
{
    @Autowired
    private ITbStickerService tbStickerService;

    /**
     * 查询表情包列表
     */
    @PreAuthorize("@ss.hasPermi('tb:sticker:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbSticker tbSticker)
    {
        startPage();
        List<TbSticker> list = tbStickerService.selectTbStickerList(tbSticker);
        return getDataTable(list);
    }

    /**
     * 导出表情包列表
     */
    @PreAuthorize("@ss.hasPermi('tb:sticker:export')")
    @Log(title = "表情包", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbSticker tbSticker)
    {
        List<TbSticker> list = tbStickerService.selectTbStickerList(tbSticker);
        ExcelUtil<TbSticker> util = new ExcelUtil<TbSticker>(TbSticker.class);
        util.exportExcel(response, list, "表情包数据");
    }

    /**
     * 获取表情包详细信息
     */
    @PreAuthorize("@ss.hasPermi('tb:sticker:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(tbStickerService.selectTbStickerById(id));
    }

    /**
     * 新增表情包
     */
    @PreAuthorize("@ss.hasPermi('tb:sticker:add')")
    @Log(title = "表情包", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbSticker tbSticker)
    {
        return toAjax(tbStickerService.insertTbSticker(tbSticker));
    }

    /**
     * 修改表情包
     */
    @PreAuthorize("@ss.hasPermi('tb:sticker:edit')")
    @Log(title = "表情包", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbSticker tbSticker)
    {
        return toAjax(tbStickerService.updateTbSticker(tbSticker));
    }

    /**
     * 删除表情包
     */
    @PreAuthorize("@ss.hasPermi('tb:sticker:remove')")
    @Log(title = "表情包", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tbStickerService.deleteTbStickerByIds(ids));
    }
}

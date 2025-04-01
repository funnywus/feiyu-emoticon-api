package com.emoticon.web.controller.app;

import com.emoticon.common.annotation.Log;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.page.TableDataInfo;
import com.emoticon.common.enums.BusinessType;
import com.emoticon.common.utils.poi.ExcelUtil;
import com.emoticon.tb.domain.TbSticker;
import com.emoticon.tb.service.ITbStickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 表情包Controller
 *
 * @author feiyu
 * @date 2025-03-22
 */
@RestController
@RequestMapping("/app/tb/sticker")
public class AppTbStickerController extends BaseController
{
    @Autowired
    private ITbStickerService tbStickerService;

    /**
     * 查询表情包列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbSticker tbSticker)
    {
        startPage();
        tbSticker.setStatus(1);
        List<TbSticker> list = tbStickerService.selectAppTbStickerList(tbSticker);
        return getDataTable(list);
    }

    /**
     * 导出表情包列表
     */
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
     *
     * @param id 表情包 id
     * @return
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(tbStickerService.selectAppTbSticker(id));
    }

    /**
     * 新增表情包
     */
    @Log(title = "表情包", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbSticker tbSticker)
    {
        return toAjax(tbStickerService.insertTbSticker(tbSticker));
    }

    /**
     * 修改表情包
     */
    @Log(title = "表情包", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbSticker tbSticker)
    {
        return toAjax(tbStickerService.updateTbSticker(tbSticker));
    }

    /**
     * 删除表情包
     */
    @Log(title = "表情包", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tbStickerService.deleteTbStickerByIds(ids));
    }
}

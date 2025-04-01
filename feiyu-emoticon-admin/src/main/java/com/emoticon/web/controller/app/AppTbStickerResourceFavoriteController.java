package com.emoticon.web.controller.app;

import com.emoticon.common.annotation.Log;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.page.TableDataInfo;
import com.emoticon.common.enums.BusinessType;
import com.emoticon.common.utils.poi.ExcelUtil;
import com.emoticon.tb.domain.TbStickerResourceFavorite;
import com.emoticon.tb.service.ITbStickerResourceFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 表情包资源收藏Controller
 *
 * @author feiyu
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/app/tb/stickerResourceFavorite")
public class AppTbStickerResourceFavoriteController extends BaseController
{
    @Autowired
    private ITbStickerResourceFavoriteService tbStickerResourceFavoriteService;

    /**
     * 查询表情包资源收藏列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbStickerResourceFavorite tbStickerResourceFavorite)
    {
        startPage();
        List<TbStickerResourceFavorite> list = tbStickerResourceFavoriteService.selectTbStickerResourceFavoriteList(tbStickerResourceFavorite);
        return getDataTable(list);
    }

    /**
     * 导出表情包资源收藏列表
     */
    @Log(title = "表情包资源收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbStickerResourceFavorite tbStickerResourceFavorite)
    {
        List<TbStickerResourceFavorite> list = tbStickerResourceFavoriteService.selectTbStickerResourceFavoriteList(tbStickerResourceFavorite);
        ExcelUtil<TbStickerResourceFavorite> util = new ExcelUtil<TbStickerResourceFavorite>(TbStickerResourceFavorite.class);
        util.exportExcel(response, list, "表情包资源收藏数据");
    }

    /**
     * 获取表情包资源收藏详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(tbStickerResourceFavoriteService.selectTbStickerResourceFavoriteById(id));
    }

    /**
     * 新增表情包资源收藏
     */
    @Log(title = "表情包资源收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbStickerResourceFavorite tbStickerResourceFavorite)
    {
        return toAjax(tbStickerResourceFavoriteService.insertTbStickerResourceFavorite(tbStickerResourceFavorite));
    }

    /**
     * 修改表情包资源收藏
     */
    @Log(title = "表情包资源收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbStickerResourceFavorite tbStickerResourceFavorite)
    {
        return toAjax(tbStickerResourceFavoriteService.updateTbStickerResourceFavorite(tbStickerResourceFavorite));
    }

    /**
     * 删除表情包资源收藏
     */
    @Log(title = "表情包资源收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tbStickerResourceFavoriteService.deleteTbStickerResourceFavoriteByIds(ids));
    }
}

package com.emoticon.web.controller.app;

import com.emoticon.common.annotation.Log;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.page.TableDataInfo;
import com.emoticon.common.enums.BusinessType;
import com.emoticon.common.utils.poi.ExcelUtil;
import com.emoticon.tb.domain.TbStickerResource;
import com.emoticon.tb.domain.TbStickerResourceFavorite;
import com.emoticon.tb.service.ITbStickerResourceFavoriteService;
import com.emoticon.tb.service.ITbStickerResourceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 表情包资源Controller
 *
 * @author feiyu
 * @date 2025-03-23
 */
@AllArgsConstructor
@RestController
@RequestMapping("/app/tb/sticker/resource")
public class AppTbStickerResourceController extends BaseController
{
    private final ITbStickerResourceService tbStickerResourceService;
    private final ITbStickerResourceFavoriteService stickerResourceFavoriteService;

    /**
     * 查询表情包资源列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbStickerResource tbStickerResource)
    {
        startPage();
        tbStickerResource.setStatus(1);
        List<TbStickerResource> list = tbStickerResourceService.selectAppTbStickerResourceList(tbStickerResource);
        return getDataTable(list);
    }

    /**
     * 导出表情包资源列表
     */
    @Log(title = "表情包资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbStickerResource tbStickerResource)
    {
        List<TbStickerResource> list = tbStickerResourceService.selectAppTbStickerResourceList(tbStickerResource);
        ExcelUtil<TbStickerResource> util = new ExcelUtil<TbStickerResource>(TbStickerResource.class);
        util.exportExcel(response, list, "表情包资源数据");
    }

    /**
     * 获取表情包资源详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(tbStickerResourceService.selectTbStickerResourceById(id));
    }

    /**
     * 新增表情包资源
     */
    @Log(title = "表情包资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbStickerResource tbStickerResource)
    {
        return toAjax(tbStickerResourceService.insertTbStickerResource(tbStickerResource));
    }

    /**
     * 修改表情包资源
     */
    @Log(title = "表情包资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbStickerResource tbStickerResource)
    {
        return toAjax(tbStickerResourceService.updateTbStickerResource(tbStickerResource));
    }

    /**
     * 删除表情包资源
     */
    @Log(title = "表情包资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tbStickerResourceService.deleteTbStickerResourceByIds(ids));
    }

    /**
     * 增加分享统计
     */
    @PutMapping("/share/{id}")
    public AjaxResult increaseShareCount(@PathVariable("id") String id)
    {
        return toAjax(tbStickerResourceService.increaseShareCount(id));
    }

    /**
     * 增加下载统计
     */
    @PutMapping("/download/{id}")
    public AjaxResult increaseDownloadCount(@PathVariable("id") String id)
    {
        return toAjax(tbStickerResourceService.increaseDownloadCount(id));
    }

    /**
     * 切换表情包收藏状态（收藏/取消收藏）
     */
    @PutMapping("/favorite/{id}")
    public AjaxResult toggleFavorite(@PathVariable("id") String id)
    {
        return toAjax(stickerResourceFavoriteService.toggleFavoriteStatus(id));
    }

    /**
     * 获取收藏的表情包资源列表
     */
    @GetMapping("/favorite/list")
    public TableDataInfo favoriteList(TbStickerResourceFavorite stickerResourceFavorite)
    {
        startPage();
        List<TbStickerResource> list = stickerResourceFavoriteService.selectFavoriteResourceList(stickerResourceFavorite);
        return getDataTable(list);
    }

}

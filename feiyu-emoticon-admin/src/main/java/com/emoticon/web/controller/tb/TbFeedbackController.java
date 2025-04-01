package com.emoticon.web.controller.tb;

import com.emoticon.common.annotation.Log;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.page.TableDataInfo;
import com.emoticon.common.enums.BusinessType;
import com.emoticon.common.utils.poi.ExcelUtil;
import com.emoticon.tb.domain.TbFeedback;
import com.emoticon.tb.domain.TbUser;
import com.emoticon.tb.service.ITbFeedbackService;
import com.emoticon.tb.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 意见反馈Controller
 *
 * @author emoticon
 * @date 2025-03-11
 */
@RestController
@RequestMapping("/tb/feedback")
public class TbFeedbackController extends BaseController
{
    @Autowired
    private ITbFeedbackService tbFeedbackService;
    @Autowired
    private ITbUserService tbUserService;

    /**
     * 查询意见反馈列表
     */
    @PreAuthorize("@ss.hasPermi('tb:feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbFeedback tbFeedback)
    {
        startPage();
        List<TbFeedback> list = tbFeedbackService.selectTbFeedbackList(tbFeedback);
        handleData(list);
        return getDataTable(list);
    }

    private void handleData(List<TbFeedback> list) {
        if (list != null && list.size() > 0) {
            for (TbFeedback tbFeedback : list) {
                Long userId = tbFeedback.getUserId();
                TbUser tbUser = tbUserService.selectUserById(userId);
                if (tbUser != null) {
                    tbFeedback.setUserName(tbUser.getUserName());
                }
            }
        }

    }

    /**
     * 导出意见反馈列表
     */
    @PreAuthorize("@ss.hasPermi('tb:feedback:export')")
    @Log(title = "意见反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbFeedback tbFeedback)
    {
        List<TbFeedback> list = tbFeedbackService.selectTbFeedbackList(tbFeedback);
        handleData(list);
        ExcelUtil<TbFeedback> util = new ExcelUtil<TbFeedback>(TbFeedback.class);
        util.exportExcel(response, list, "意见反馈数据");
    }

    /**
     * 获取意见反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('tb:feedback:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tbFeedbackService.selectTbFeedbackById(id));
    }

    /**
     * 新增意见反馈
     */
    @PreAuthorize("@ss.hasPermi('tb:feedback:add')")
    @Log(title = "意见反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbFeedback tbFeedback)
    {
        return toAjax(tbFeedbackService.insertTbFeedback(tbFeedback));
    }

    /**
     * 修改意见反馈
     */
    @PreAuthorize("@ss.hasPermi('tb:feedback:edit')")
    @Log(title = "意见反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbFeedback tbFeedback)
    {
        return toAjax(tbFeedbackService.updateTbFeedback(tbFeedback));
    }

    /**
     * 删除意见反馈
     */
    @PreAuthorize("@ss.hasPermi('tb:feedback:remove')")
    @Log(title = "意见反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbFeedbackService.deleteTbFeedbackByIds(ids));
    }
}

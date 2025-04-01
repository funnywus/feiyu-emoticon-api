package com.emoticon.web.controller.app;

import cn.dev33.satoken.stp.StpUtil;
import com.emoticon.common.annotation.Log;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.page.TableDataInfo;
import com.emoticon.common.enums.BusinessType;
import com.emoticon.common.utils.poi.ExcelUtil;
import com.emoticon.tb.domain.TbFeedback;
import com.emoticon.tb.service.ITbFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/app/tb/feedback")
public class AppTbFeedbackController extends BaseController
{
    @Autowired
    private ITbFeedbackService tbFeedbackService;

    /**
     * 查询意见反馈列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbFeedback tbFeedback)
    {
        startPage();
        List<TbFeedback> list = tbFeedbackService.selectTbFeedbackList(tbFeedback);
        return getDataTable(list);
    }

    /**
     * 导出意见反馈列表
     */
    @Log(title = "意见反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbFeedback tbFeedback)
    {
        List<TbFeedback> list = tbFeedbackService.selectTbFeedbackList(tbFeedback);
        ExcelUtil<TbFeedback> util = new ExcelUtil<TbFeedback>(TbFeedback.class);
        util.exportExcel(response, list, "意见反馈数据");
    }


    /**
     * 新增意见反馈
     */
    @Log(title = "意见反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbFeedback tbFeedback)
    {
        tbFeedback.setUserId(StpUtil.getLoginIdAsLong());
        return toAjax(tbFeedbackService.insertTbFeedback(tbFeedback));
    }
}

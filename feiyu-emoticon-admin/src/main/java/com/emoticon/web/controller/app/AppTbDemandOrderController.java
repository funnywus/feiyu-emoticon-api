package com.emoticon.web.controller.app;

import cn.dev33.satoken.stp.StpUtil;
import com.emoticon.common.annotation.Log;
import com.emoticon.common.core.controller.BaseController;
import com.emoticon.common.core.domain.AjaxResult;
import com.emoticon.common.core.page.TableDataInfo;
import com.emoticon.common.enums.BusinessType;
import com.emoticon.common.utils.poi.ExcelUtil;
import com.emoticon.tb.domain.TbDemandOrder;
import com.emoticon.tb.service.ITbDemandOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 需求订单Controller
 *
 * @author emoticon
 * @date 2025-03-05
 */
@RestController
@RequestMapping("/app/tb/demandOrder")
public class AppTbDemandOrderController extends BaseController
{
    @Autowired
    private ITbDemandOrderService tbDemandOrderService;

    /**
     * 查询需求订单列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbDemandOrder tbDemandOrder)
    {
        startPage();
        List<TbDemandOrder> list = tbDemandOrderService.selectTbDemandOrderList(tbDemandOrder);
        return getDataTable(list);
    }

    /**
     * 查询需求订单列表-已发布状态
     */
    @GetMapping("/publishList")
    public TableDataInfo publishList(TbDemandOrder tbDemandOrder)
    {
        startPage();
        tbDemandOrder.setStatus("1");
        List<TbDemandOrder> list = tbDemandOrderService.selectTbDemandOrderList(tbDemandOrder);
        return getDataTable(list);
    }

    /**
     * 查询我已发布的订单
     */
    @GetMapping("/publishedList")
    public TableDataInfo publishedList(TbDemandOrder tbDemandOrder) {
        startPage();
        // 设置当前登录用户ID
        tbDemandOrder.setUserId(StpUtil.getLoginIdAsLong());
        List<TbDemandOrder> list = tbDemandOrderService.selectTbDemandOrderList(tbDemandOrder);
        return getDataTable(list);
    }

    /**
     * 查询我已接收的订单
     */
    @GetMapping("/receivedList")
    public TableDataInfo receivedList(TbDemandOrder tbDemandOrder) {
        startPage();
        // 设置当前登录用户ID
        tbDemandOrder.setReceiverUserId(StpUtil.getLoginIdAsLong());
        List<TbDemandOrder> list = tbDemandOrderService.selectTbDemandOrderList(tbDemandOrder);
        return getDataTable(list);
    }

    /**
     * 导出需求订单列表
     */
    @Log(title = "需求订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbDemandOrder tbDemandOrder)
    {
        List<TbDemandOrder> list = tbDemandOrderService.selectTbDemandOrderList(tbDemandOrder);
        ExcelUtil<TbDemandOrder> util = new ExcelUtil<TbDemandOrder>(TbDemandOrder.class);
        util.exportExcel(response, list, "需求订单数据");
    }

    /**
     * 获取需求订单详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(tbDemandOrderService.selectTbDemandOrderById(id));
    }


    /**
     * 修改需求订单
     */
    @Log(title = "需求订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbDemandOrder tbDemandOrder)
    {
        return toAjax(tbDemandOrderService.updateTbDemandOrder(tbDemandOrder));
    }

    /**
     * 删除需求订单
     */
    @Log(title = "需求订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tbDemandOrderService.deleteTbDemandOrderByIds(ids));
    }

    /**
     * 新增订单
     */
    @PostMapping
    public AjaxResult add(@RequestBody TbDemandOrder tbDemandOrder) {
        // 设置当前登录用户ID
        tbDemandOrder.setUserId(StpUtil.getLoginIdAsLong());
        Object s = tbDemandOrderService.addTbDemandOrder(tbDemandOrder);
        return success(s);
    }


    /**
     * 支付订单
     */
    @PutMapping("/pay/{orderId}")
    public AjaxResult pay(@PathVariable String orderId) {
        // 先查询订单信息
        TbDemandOrder existOrder = tbDemandOrderService.selectTbDemandOrderById(orderId);
        if (existOrder == null) {
            return error("订单不存在");
        }
        if (existOrder.getStatus().equals("1")) {

        }

        // 验证是否是当前用户的订单
        if (!existOrder.getUserId().equals(StpUtil.getLoginIdAsLong())) {
            return error("无权操作此订单");
        }

        // 检查订单状态，只有未支付状态(0)才能支付
        if (!"0".equals(existOrder.getStatus())) {
            return error("订单状态不正确，无法支付");
        }

        TbDemandOrder order = new TbDemandOrder();
        order.setId(existOrder.getId());
        order.setUserId(StpUtil.getLoginIdAsLong());
        order.setStatus("1"); // 设置为已支付状态
//        order.setPayTime(new Date()); // 设置支付时间
        return toAjax(tbDemandOrderService.updateTbDemandOrder(order));
    }

    /**
     * 接单
     */
    @PutMapping("/receive/{orderId}")
    public AjaxResult receive(@PathVariable String orderId) {
        // 先查询订单信息
        TbDemandOrder existOrder = tbDemandOrderService.selectTbDemandOrderById(orderId);
        if (existOrder == null) {
            return error("订单不存在");
        }

        // 检查订单状态，只有已发布状态(1)才能接单
        if (!"1".equals(existOrder.getStatus())) {
            return error("订单状态不正确，无法接单");
        }

        // 不能接自己发布的订单
        if (existOrder.getUserId().equals(StpUtil.getLoginIdAsLong())) {
            return error("不能接自己发布的订单");
        }

        TbDemandOrder order = new TbDemandOrder();
        order.setId(existOrder.getId());
        order.setReceiverUserId(StpUtil.getLoginIdAsLong());
        order.setStatus("2"); // 设置为已接单状态
        return toAjax(tbDemandOrderService.updateTbDemandOrder(order));
    }

    /**
     * 完成订单
     */
    @PutMapping("/complete/{orderId}")
    public AjaxResult complete(@PathVariable String orderId) {
        // 先查询订单信息
        TbDemandOrder existOrder = tbDemandOrderService.selectTbDemandOrderById(orderId);
        if (existOrder == null) {
            return error("订单不存在");
        }

        // 检查订单状态，只有已接单状态(2)才能完成
        if (!"2".equals(existOrder.getStatus())) {
            return error("订单状态不正确，无法完成");
        }

        // 验证是否是接单用户
        if (!existOrder.getReceiverUserId().equals(StpUtil.getLoginIdAsLong())) {
            return error("只有接单人才能完成订单");
        }

        TbDemandOrder order = new TbDemandOrder();
        order.setId(existOrder.getId());
        order.setStatus("3"); // 设置为已完成状态
        return toAjax(tbDemandOrderService.updateTbDemandOrder(order));
    }

}

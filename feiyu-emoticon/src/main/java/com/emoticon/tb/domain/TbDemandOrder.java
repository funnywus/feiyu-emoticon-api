package com.emoticon.tb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.emoticon.common.annotation.Excel;
import com.emoticon.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 需求订单对象 tb_demand_order
 *
 * @author emoticon
 * @date 2025-03-05
 */
public class TbDemandOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private String id;

    /** 用户 id */
    @Excel(name = "用户 id")
    private Long userId;

    /** 接单人 id */
    @Excel(name = "接单人 id")
    private Long receiverUserId;

    /** 接单人手机号 */
    @Excel(name = "接单人手机号")
    private String receiverUserPhone;

    /** 接单人名字 */
    @Excel(name = "接单人名字")
    private String receiverUserName;

    /** 订单类型（1代取 2代购） */
    @Excel(name = "订单类型", readConverterExp = "1=代取,2=代购")
    private String type;

    /** 收货人姓名 */
    @Excel(name = "收货人姓名")
    private String receiverName;

    /** 收货人电话 */
    @Excel(name = "收货人电话")
    private String receiverPhone;

    /** 收货人地址 */
    @Excel(name = "收货人地址")
    private String receiverAddress;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal money;

    /** 需求内容 */
    @Excel(name = "需求内容")
    private String content;

    /** 0=代支付,1:已发布,2=已接单,3=已完成,4=已评价 */
    @Excel(name = "0=代支付,1:已发布,2=已接单,3=已完成,4=已评价")
    private String status;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String evaluate;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    private Long addressId;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setReceiverUserId(Long receiverUserId)
    {
        this.receiverUserId = receiverUserId;
    }

    public Long getReceiverUserId()
    {
        return receiverUserId;
    }
    public void setReceiverUserPhone(String receiverUserPhone) {
        this.receiverUserPhone = receiverUserPhone;
    }

    public String getReceiverUserPhone() {
        return receiverUserPhone;
    }

    public void setReceiverUserName(String receiverUserName) {
        this.receiverUserName = receiverUserName;
    }

    public String getReceiverUserName() {
        return receiverUserName;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setReceiverName(String receiverName)
    {
        this.receiverName = receiverName;
    }

    public String getReceiverName()
    {
        return receiverName;
    }
    public void setReceiverPhone(String receiverPhone)
    {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPhone()
    {
        return receiverPhone;
    }
    public void setReceiverAddress(String receiverAddress)
    {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverAddress()
    {
        return receiverAddress;
    }
    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public BigDecimal getMoney()
    {
        return money;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setEvaluate(String evaluate)
    {
        this.evaluate = evaluate;
    }

    public String getEvaluate()
    {
        return evaluate;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setPayTime(Date payTime)
    {
        this.payTime = payTime;
    }

    public Date getPayTime()
    {
        return payTime;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "TbDemandOrder{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", receiverUserId=" + receiverUserId +
                ", receiverUserPhone='" + receiverUserPhone + '\'' +
                ", receiverUserName='" + receiverUserName + '\'' +
                ", type='" + type + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", money=" + money +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", evaluate='" + evaluate + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", payTime=" + payTime +
                ", addressId=" + addressId +
                '}';
    }
}

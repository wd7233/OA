package rml.model;

import java.util.Date;

public class ScalpingOrder
{
    private Integer id;
    
    private String goodNumber;
    
    private String orderId;
    
    private String consignee;
    
    private String phone;
    
    private Double skuPirce;
    
    private Date createTime;
    
    private Integer type;
    
    private Integer state;
    
    /***********************/
    private String goodName;
    
    private Date orderTime;
    
    private String afterState;
    
    public String getAfterState()
    {
        return afterState;
    }
    
    public void setAfterState(String afterState)
    {
        this.afterState = afterState;
    }
    
    public Date getOrderTime()
    {
        return orderTime;
    }
    
    public void setOrderTime(Date orderTime)
    {
        this.orderTime = orderTime;
    }
    
    public String getGoodName()
    {
        return goodName;
    }
    
    public void setGoodName(String goodName)
    {
        this.goodName = goodName;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getGoodNumber()
    {
        return goodNumber;
    }
    
    public void setGoodNumber(String goodNumber)
    {
        this.goodNumber = goodNumber == null ? null : goodNumber.trim();
    }
    
    public String getOrderId()
    {
        return orderId;
    }
    
    public void setOrderId(String orderId)
    {
        this.orderId = orderId == null ? null : orderId.trim();
    }
    
    public String getConsignee()
    {
        return consignee;
    }
    
    public void setConsignee(String consignee)
    {
        this.consignee = consignee == null ? null : consignee.trim();
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone == null ? null : phone.trim();
    }
    
    public Double getSkuPirce()
    {
        return skuPirce;
    }
    
    public void setSkuPirce(Double skuPirce)
    {
        this.skuPirce = skuPirce;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public Integer getType()
    {
        return type;
    }
    
    public void setType(Integer type)
    {
        this.type = type;
    }
    
    public Integer getState()
    {
        return state;
    }
    
    public void setState(Integer state)
    {
        this.state = state;
    }
}
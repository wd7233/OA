package rml.model;

import java.util.Date;

public class OrderDetail
{
    private Integer id;
    
    private String orderId;
    
    private String shopNumber;
    
    private Date dealTime;
    
    private String goodName;
    
    private Integer count;
    
    private String goodDetail;
    
    private String message;
    
    private Double sellPrice;
    
    private Double buyPrice;
    
    private Double commission;
    
    private String remake;
    
    private String tbId;
    
    private Integer type;
    
    private Date createTime;
    
    private String shopName;
    
    private Double editPrice;
    
    private Integer isEditPrice;
    private Integer editPriceType;
    private String staffName;
    
    public Integer getEditPriceType()
    {
        return editPriceType;
    }

    public void setEditPriceType(Integer editPriceType)
    {
        this.editPriceType = editPriceType;
    }

    public String getStaffName()
    {
        return staffName;
    }
    
    public void setStaffName(String staffName)
    {
        this.staffName = staffName;
    }
    
    public Integer getIsEditPrice()
    {
        return isEditPrice;
    }
    
    public void setIsEditPrice(Integer isEditPrice)
    {
        this.isEditPrice = isEditPrice;
    }
    
    public Double getEditPrice()
    {
        return editPrice;
    }
    
    public void setEditPrice(Double editPrice)
    {
        this.editPrice = editPrice;
    }
    
    public String getShopName()
    {
        return shopName;
    }
    
    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getOrderId()
    {
        return orderId;
    }
    
    public void setOrderId(String orderId)
    {
        this.orderId = orderId == null ? null : orderId.trim();
    }
    
    public String getShopNumber()
    {
        return shopNumber;
    }
    
    public void setShopNumber(String shopNumber)
    {
        this.shopNumber = shopNumber == null ? null : shopNumber.trim();
    }
    
    public Date getDealTime()
    {
        return dealTime;
    }
    
    public void setDealTime(Date dealTime)
    {
        this.dealTime = dealTime;
    }
    
    public String getGoodName()
    {
        return goodName;
    }
    
    public void setGoodName(String goodName)
    {
        this.goodName = goodName == null ? null : goodName.trim();
    }
    
    public Integer getCount()
    {
        return count;
    }
    
    public void setCount(Integer count)
    {
        this.count = count;
    }
    
    public String getGoodDetail()
    {
        return goodDetail;
    }
    
    public void setGoodDetail(String goodDetail)
    {
        this.goodDetail = goodDetail == null ? null : goodDetail.trim();
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message == null ? null : message.trim();
    }
    
    public Double getSellPrice()
    {
        return sellPrice;
    }
    
    public void setSellPrice(Double sellPrice)
    {
        this.sellPrice = sellPrice;
    }
    
    public Double getBuyPrice()
    {
        return buyPrice;
    }
    
    public void setBuyPrice(Double buyPrice)
    {
        this.buyPrice = buyPrice;
    }
    
    public Double getCommission()
    {
        return commission;
    }
    
    public void setCommission(Double commission)
    {
        this.commission = commission;
    }
    
    public String getRemake()
    {
        return remake;
    }
    
    public void setRemake(String remake)
    {
        this.remake = remake == null ? null : remake.trim();
    }
    
    public String getTbId()
    {
        return tbId;
    }
    
    public void setTbId(String tbId)
    {
        this.tbId = tbId == null ? null : tbId.trim();
    }
    
    public Integer getType()
    {
        return type;
    }
    
    public void setType(Integer type)
    {
        this.type = type;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}
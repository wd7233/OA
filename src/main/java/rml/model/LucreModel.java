package rml.model;

import java.util.List;

public class LucreModel
{
    // 客服名称
    private String userName;
    
    private Integer userId;
    
    // 店铺
    private List<Shop> ShopList;
    
    // 退款数量
    private Integer backCnt;
    
    // 退款率
    private String backRate;
    
    // 待解决订单
    private Integer proCnt;
    
    // 涨价订单
    private Integer editCnt;
    
    // 利润
    private Double lucre;
    
    // 订单数量
    private Integer orderCnt;
    
    // 奖金
    private Integer bonus;
    
    // 绩效
    private Integer achievements;
    
    // 工资
    private Integer money;
    
    public Integer getBackCnt()
    {
        return backCnt;
    }
    
    public void setBackCnt(Integer backCnt)
    {
        this.backCnt = backCnt;
    }
    
    public String getBackRate()
    {
        return backRate;
    }
    
    public void setBackRate(String backRate)
    {
        this.backRate = backRate;
    }
    
    public Integer getProCnt()
    {
        return proCnt;
    }
    
    public void setProCnt(Integer proCnt)
    {
        this.proCnt = proCnt;
    }
    
    public Integer getEditCnt()
    {
        return editCnt;
    }
    
    public void setEditCnt(Integer editCnt)
    {
        this.editCnt = editCnt;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public Integer getUserId()
    {
        return userId;
    }
    
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }
    
    public List<Shop> getShopList()
    {
        return ShopList;
    }
    
    public void setShopList(List<Shop> shopList)
    {
        ShopList = shopList;
    }
    
    public Double getLucre()
    {
        return lucre;
    }
    
    public void setLucre(Double lucre)
    {
        this.lucre = lucre;
    }
    
    public Integer getOrderCnt()
    {
        return orderCnt;
    }
    
    public void setOrderCnt(Integer orderCnt)
    {
        this.orderCnt = orderCnt;
    }
    
    public Integer getBonus()
    {
        return bonus;
    }
    
    public void setBonus(Integer bonus)
    {
        this.bonus = bonus;
    }
    
    public Integer getAchievements()
    {
        return achievements;
    }
    
    public void setAchievements(Integer achievements)
    {
        this.achievements = achievements;
    }
    
    public Integer getMoney()
    {
        return money;
    }
    
    public void setMoney(Integer money)
    {
        this.money = money;
    }
    
}

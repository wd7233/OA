package rml.model;

public class Shop
{
    private Integer id;
    
    private String number;
    
    private String name;
    
    private String category;
    
    private String account;
    
    private String password;
    
    private String userName;
    
    private String userCard;
    
    private String inCode;
    
    private Integer staffId;
    
    private Integer goodsnum;
    
    private Integer isopen;
    
    private Integer isvtd;
    
    private String staffName;
    
    private String cardno;
    
    public String getCardno()
    {
        return cardno;
    }
    
    public void setCardno(String cardno)
    {
        this.cardno = cardno;
    }
    
    public String getStaffName()
    {
        return staffName;
    }
    
    public void setStaffName(String staffName)
    {
        this.staffName = staffName;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getNumber()
    {
        return number;
    }
    
    public void setNumber(String number)
    {
        this.number = number == null ? null : number.trim();
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }
    
    public String getCategory()
    {
        return category;
    }
    
    public void setCategory(String category)
    {
        this.category = category == null ? null : category.trim();
    }
    
    public String getAccount()
    {
        return account;
    }
    
    public void setAccount(String account)
    {
        this.account = account == null ? null : account.trim();
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password == null ? null : password.trim();
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName == null ? null : userName.trim();
    }
    
    public String getUserCard()
    {
        return userCard;
    }
    
    public void setUserCard(String userCard)
    {
        this.userCard = userCard == null ? null : userCard.trim();
    }
    
    public String getInCode()
    {
        return inCode;
    }
    
    public void setInCode(String inCode)
    {
        this.inCode = inCode == null ? null : inCode.trim();
    }
    
    public Integer getGoodsnum()
    {
        return goodsnum;
    }
    
    public void setGoodsnum(Integer goodsnum)
    {
        this.goodsnum = goodsnum;
    }
    
    public Integer getIsopen()
    {
        return isopen;
    }
    
    public void setIsopen(Integer isopen)
    {
        this.isopen = isopen;
    }
    
    public Integer getIsvtd()
    {
        return isvtd;
    }
    
    public void setIsvtd(Integer isvtd)
    {
        this.isvtd = isvtd;
    }
    
    public Integer getStaffId()
    {
        return staffId;
    }
    
    public void setStaffId(Integer staffId)
    {
        this.staffId = staffId;
    }
    
}
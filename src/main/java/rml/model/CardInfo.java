package rml.model;

public class CardInfo
{
    private Integer id;
    
    private String name;
    
    private String cardno;
    
    private String userNo;
    
    private String cardLocation;
    
    private String cardPhone;
    
    private String cardPwd;
    
    private String shopName;

    private String shopNumber;

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
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
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }
    
    public String getCardno()
    {
        return cardno;
    }
    
    public void setCardno(String cardno)
    {
        this.cardno = cardno == null ? null : cardno.trim();
    }
    
    public String getUserNo()
    {
        return userNo;
    }
    
    public void setUserNo(String userNo)
    {
        this.userNo = userNo == null ? null : userNo.trim();
    }
    
    public String getCardLocation()
    {
        return cardLocation;
    }
    
    public void setCardLocation(String cardLocation)
    {
        this.cardLocation = cardLocation == null ? null : cardLocation.trim();
    }
    
    public String getCardPhone()
    {
        return cardPhone;
    }
    
    public void setCardPhone(String cardPhone)
    {
        this.cardPhone = cardPhone == null ? null : cardPhone.trim();
    }
    
    public String getCardPwd()
    {
        return cardPwd;
    }
    
    public void setCardPwd(String cardPwd)
    {
        this.cardPwd = cardPwd == null ? null : cardPwd.trim();
    }
}
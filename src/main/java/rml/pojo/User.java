package rml.pojo;


public class User
{
    private int id;
    private int subscribe; 
    private String openid;
    private String nickname;
    private String createTime;
    private String wxId;
    private String sex;
    private String language;
    private String province;
    private String country;
    private String headimgurl;
    private String subscribe_time;
    private int type;
    private String city;
    private String unionid;
    
    public String getUnionid()
    {
        return unionid;
    }
    public void setUnionid(String unionid)
    {
        this.unionid = unionid;
    }
    public String getCity()
    {
        return city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }
    public int getType()
    {
        return type;
    }
    public void setType(int type)
    {
        this.type = type;
    }
    public int getSubscribe()
    {
        return subscribe;
    }
    public void setSubscribe(int subscribe)
    {
        this.subscribe = subscribe;
    }
    public String getOpenid()
    {
        return openid;
    }
    public void setOpenid(String openid)
    {
        this.openid = openid;
    }
    public String getNickname()
    {
        return nickname;
    }
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }
    public String getSex()
    {
        return sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    public String getLanguage()
    {
        return language;
    }
    public void setLanguage(String language)
    {
        this.language = language;
    }
    public String getProvince()
    {
        return province;
    }
    public void setProvince(String province)
    {
        this.province = province;
    }
    public String getCountry()
    {
        return country;
    }
    public void setCountry(String country)
    {
        this.country = country;
    }
    public String getHeadimgurl()
    {
        return headimgurl;
    }
    public void setHeadimgurl(String headimgurl)
    {
        this.headimgurl = headimgurl;
    }
    public String getSubscribe_time()
    {
        return subscribe_time;
    }
    public void setSubscribe_time(String subscribe_time)
    {
        this.subscribe_time = subscribe_time;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getWxId()
    {
        return wxId;
    }
    public void setWxId(String wxId)
    {
        this.wxId = wxId;
    }
    public String getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    @Override
    public String toString()
    {
        return "User [id=" + id + ", subscribe=" + subscribe + ", openid=" + openid + ", nickname=" + nickname + ", createTime=" + createTime + ", wxId=" + wxId + ", sex=" + sex + ", language="
            + language + ", province=" + province + ", country=" + country + ", headimgurl=" + headimgurl + ", subscribe_time=" + subscribe_time + ", type=" + type + ", city=" + city + ", unionid="
            + unionid + "]";
    }
    
}

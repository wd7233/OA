package rml.model;

import java.util.Date;

public class CmzDanmu
{
    
    private Integer id;
    
    private String openId;
    
    private String content;
    
    private Integer isPass;
    private Date createTime;
    
    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getOpenId()
    {
        return openId;
    }
    
    public void setOpenId(String openId)
    {
        this.openId = openId;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
    public Integer getIsPass()
    {
        return isPass;
    }
    
    public void setIsPass(Integer isPass)
    {
        this.isPass = isPass;
    }
    
}

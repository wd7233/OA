package rml.model;

import java.util.Date;

public class Staff
{
    private Integer id;
    
    private String account;
    
    private String pwd;
    
    private Integer sex;
    
    private String name;
    
    private String telephone;
    
    private Integer role;
    
    private Date createTime;
    
    private Integer companyId;
    
    private Integer superId;
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getAccount()
    {
        return account;
    }
    
    public void setAccount(String account)
    {
        this.account = account == null ? null : account.trim();
    }
    
    public String getPwd()
    {
        return pwd;
    }
    
    public void setPwd(String pwd)
    {
        this.pwd = pwd == null ? null : pwd.trim();
    }
    
    public Integer getSex()
    {
        return sex;
    }
    
    public void setSex(Integer sex)
    {
        this.sex = sex;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }
    
    public String getTelephone()
    {
        return telephone;
    }
    
    public void setTelephone(String telephone)
    {
        this.telephone = telephone == null ? null : telephone.trim();
    }
    
    public Integer getRole()
    {
        return role;
    }
    
    public void setRole(Integer role)
    {
        this.role = role;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Integer getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(Integer companyId)
    {
        this.companyId = companyId;
    }

    public Integer getSuperId()
    {
        return superId;
    }

    public void setSuperId(Integer superId)
    {
        this.superId = superId;
    }
    
}
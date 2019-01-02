package rml.model;

import java.util.Date;

public class Company {
    private Integer id;

    private String companyName;

    private String companyAccount;

    private String companyPwd;

    private String leadyName;

    private Integer telephone;

    private Integer leadyQq;

    private String location;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyAccount() {
        return companyAccount;
    }

    public void setCompanyAccount(String companyAccount) {
        this.companyAccount = companyAccount == null ? null : companyAccount.trim();
    }

    public String getCompanyPwd() {
        return companyPwd;
    }

    public void setCompanyPwd(String companyPwd) {
        this.companyPwd = companyPwd == null ? null : companyPwd.trim();
    }

    public String getLeadyName() {
        return leadyName;
    }

    public void setLeadyName(String leadyName) {
        this.leadyName = leadyName == null ? null : leadyName.trim();
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public Integer getLeadyQq() {
        return leadyQq;
    }

    public void setLeadyQq(Integer leadyQq) {
        this.leadyQq = leadyQq;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
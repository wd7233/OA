package rml.model;

import java.util.Date;

public class SpecialOrderSupple {
    private Integer id;

    private String orderId;

    private String message;

    private Double supplePrice;

    private String suppleCourierNumber;

    private String suppleCourierCompany;

    private Date suppleCourierTime;

    private Integer state;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Double getSupplePrice() {
        return supplePrice;
    }

    public void setSupplePrice(Double supplePrice) {
        this.supplePrice = supplePrice;
    }

    public String getSuppleCourierNumber() {
        return suppleCourierNumber;
    }

    public void setSuppleCourierNumber(String suppleCourierNumber) {
        this.suppleCourierNumber = suppleCourierNumber == null ? null : suppleCourierNumber.trim();
    }

    public String getSuppleCourierCompany() {
        return suppleCourierCompany;
    }

    public void setSuppleCourierCompany(String suppleCourierCompany) {
        this.suppleCourierCompany = suppleCourierCompany == null ? null : suppleCourierCompany.trim();
    }

    public Date getSuppleCourierTime() {
        return suppleCourierTime;
    }

    public void setSuppleCourierTime(Date suppleCourierTime) {
        this.suppleCourierTime = suppleCourierTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
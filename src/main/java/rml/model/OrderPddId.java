package rml.model;

import java.util.Date;

public class OrderPddId {
    private Integer id;

    private String orderId;

    private String pddId;

    private Double pddPrice;

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

    public String getPddId() {
        return pddId;
    }

    public void setPddId(String pddId) {
        this.pddId = pddId == null ? null : pddId.trim();
    }

    public Double getPddPrice() {
        return pddPrice;
    }

    public void setPddPrice(Double pddPrice) {
        this.pddPrice = pddPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
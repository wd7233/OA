package rml.model;

public class CardAmountInfo extends CardInfo {

    //金额
    private Double amount;

    private Byte isWithdraw;

    public Byte getIsWithdraw() {
        return isWithdraw;
    }

    public void setIsWithdraw(Byte isWithdraw) {
        this.isWithdraw = isWithdraw;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

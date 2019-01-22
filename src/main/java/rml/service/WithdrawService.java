package rml.service;

import rml.model.CardAmountInfo;
import rml.model.Withdraw;
import rml.model.WithdrawName;

import java.util.List;

public interface WithdrawService {

    Integer save(Withdraw withdraw);

    List<CardAmountInfo> getCardAmountSum();

    Integer withdrawedById(Integer id);
    
    Integer withdrawed(String name);

    List<WithdrawName> userNoList(String userName);

    Integer withdrawedAll();
}

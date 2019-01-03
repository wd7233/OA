package rml.dao;

import java.util.List;
import rml.model.Withdraw;
import rml.model.WithdrawName;

public interface WithdrawMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Withdraw record);

    Withdraw selectByPrimaryKey(Integer id);

    List<Withdraw> selectAll();

    int updateByPrimaryKey(Withdraw record);

    Integer updateByShopNumber(String shopNumber);

    Double selectAmountSum(String userName);

    List<WithdrawName> listWithdrawName(String userName);

    Integer updateAll();
}
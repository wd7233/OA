package rml.dao;

import java.util.List;
import rml.model.Withdraw;

public interface WithdrawMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Withdraw record);

    Withdraw selectByPrimaryKey(Integer id);

    List<Withdraw> selectAll();

    int updateByPrimaryKey(Withdraw record);
}
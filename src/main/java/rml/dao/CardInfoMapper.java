package rml.dao;

import java.util.List;

import rml.model.CardAmountInfo;
import rml.model.CardInfo;

public interface CardInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CardInfo record);

    CardInfo selectByPrimaryKey(Integer id);

    List<CardInfo> selectAll();

    int updateByPrimaryKey(CardInfo record);
    
    List<CardInfo> selectByUserId(Integer userId);

    List<CardAmountInfo> selectAllSum();
}
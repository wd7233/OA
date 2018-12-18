package rml.service;

import java.util.List;

import rml.model.CardInfo;

public interface CardInfoServiceI
{
    
    int deleteByPrimaryKey(Integer id);

    int insert(CardInfo record);

    CardInfo selectByPrimaryKey(Integer id);

    List<CardInfo> selectAll();

    int updateByPrimaryKey(CardInfo record);
    List<CardInfo> selectByUserId(Integer userId);

}

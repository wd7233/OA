package rml.service;

import java.util.List;

import rml.model.CardInfo;
import rml.model.SpecialOrderSupple;

public interface SpecialOrderSuppleServiceI
{
    int deleteByPrimaryKey(Integer id);

    int insert(SpecialOrderSupple record);

    SpecialOrderSupple selectByPrimaryKey(Integer id);

    List<SpecialOrderSupple> selectAll();

    int updateByPrimaryKey(SpecialOrderSupple record);
    
    SpecialOrderSupple selectByOrderId(String orderId);

}

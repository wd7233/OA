package rml.service;

import java.util.List;

import rml.model.EditPrice;

public interface EditPriceServiceI
{
    
    int deleteByPrimaryKey(Integer id);

    int insert(EditPrice record);

    EditPrice selectByPrimaryKey(Integer id);

    List<EditPrice> selectAll();

    int updateByPrimaryKey(EditPrice record);
    
    EditPrice selectByOrderId(String orderId);
}

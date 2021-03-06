package rml.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import rml.model.ScalpingOrder;
import rml.model.SpecialOrder;

public interface SpecialOrderMapper
{
    int deleteByPrimaryKey(Integer id);
    
    int insert(SpecialOrder record);
    
    SpecialOrder selectByPrimaryKey(Integer id);
    
    List<SpecialOrder> selectAll();
    
    int updateByPrimaryKey(SpecialOrder record);
    
    List<SpecialOrder> selectOrderListByUser(Map<String, Object> map);
    
    SpecialOrder selectByOrderId(String orderId);
    
    int deleteByOrderId(String orderId);
    
    List<SpecialOrder> selectSkuCount(Map<String, Object> map);
    List<SpecialOrder> selectSkuNumCount(Map<String, Object> map);
}
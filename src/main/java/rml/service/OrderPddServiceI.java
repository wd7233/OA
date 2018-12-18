package rml.service;

import java.util.List;

import rml.model.OrderPddId;

public interface OrderPddServiceI
{
    int deleteByPrimaryKey(Integer id);
    
    int insert(OrderPddId record);
    
    OrderPddId selectByPrimaryKey(Integer id);
    
    List<OrderPddId> selectAll();
    
    int updateByPrimaryKey(OrderPddId record);
    
    OrderPddId selectByOrderId(String orderId);
}

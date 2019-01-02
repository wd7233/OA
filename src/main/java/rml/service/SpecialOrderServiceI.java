package rml.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import rml.model.OrderDetail;
import rml.model.SpecialOrder;

public interface SpecialOrderServiceI
{
int deleteByPrimaryKey(Integer id);
    
    int insert(SpecialOrder record);
    
    SpecialOrder selectByPrimaryKey(Integer id);
    List<SpecialOrder> selectAll();
    
    int updateByPrimaryKey(SpecialOrder record);
    
    List<SpecialOrder> selectOrderListByUser(Date startTime, Date endTime, Integer staffId, String keyWord, String orderState,String afterState);

    SpecialOrder selectByOrderId(String orderId);




}

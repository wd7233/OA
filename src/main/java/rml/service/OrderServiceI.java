package rml.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import rml.model.OrderDetail;

public interface OrderServiceI
{
    int deleteByPrimaryKey(Integer id);
    
    int insert(OrderDetail record);
    
    OrderDetail selectByPrimaryKey(Integer id);
    
    List<OrderDetail> selectAll();
    
    int updateByPrimaryKey(OrderDetail record);
    
    OrderDetail selectByOrderId(String orderId);
    List<OrderDetail> selectOrderByTime(Date startTime,Date endTime,String shopNumber,Integer type);
    List<OrderDetail> selectOrderByUser(Date startTime,Date endTime,String shopNumber,String keyWord);
    List<OrderDetail> selectOrderForLucre(Date startTime,Date endTime,String shopNumber);
    List<OrderDetail> selectOrderList(Date startTime,Date endTime,String keyWord,Integer staffId,Integer orderType);
    List<OrderDetail> selectOrderListByUser(Date startTime,Date endTime,Integer staffId,String keyWord,Integer orderType);
    Integer selectCountByUser(Date startTime,Date endTime,Integer staffId,Integer type);
    Integer selectEditCount(Date startTime, Date endTime, Integer staffId);
}

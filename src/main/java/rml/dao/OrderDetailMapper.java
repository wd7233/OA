package rml.dao;

import java.util.List;
import java.util.Map;

import rml.model.OrderDetail;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer id);

    List<OrderDetail> selectAll();

    int updateByPrimaryKey(OrderDetail record);
    OrderDetail selectByOrderId(String orderId);
    List<OrderDetail> selectOrderByTime(Map<String,Object> map);
    List<OrderDetail> selectOrderForLucre(Map<String,Object> map);
    List<OrderDetail> selectOrderList(Map<String,Object> map);
    List<OrderDetail> selectOrderByUser(Map<String,Object> map);
    List<OrderDetail> selectOrderListByUser(Map<String,Object> map);
    Integer selectCountByUser(Map<String,Object> map);
    Integer selectEditCount(Map<String,Object> map);
    

}
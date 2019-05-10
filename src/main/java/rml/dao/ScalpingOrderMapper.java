package rml.dao;

import java.util.List;
import java.util.Map;

import rml.model.ScalpingOrder;
import rml.model.SpPingjia;

public interface ScalpingOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScalpingOrder record);

    ScalpingOrder selectByPrimaryKey(Integer id);

    List<ScalpingOrder> selectAll();

    int updateByPrimaryKey(ScalpingOrder record);
    List<ScalpingOrder> selectOrder(Map<String, Object> map);
    public ScalpingOrder selectByOrderId(String orderId);
    
}
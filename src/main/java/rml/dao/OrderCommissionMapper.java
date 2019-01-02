package rml.dao;

import java.util.List;
import rml.model.OrderCommission;

public interface OrderCommissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderCommission record);

    OrderCommission selectByPrimaryKey(Integer id);

    List<OrderCommission> selectAll();

    int updateByPrimaryKey(OrderCommission record);
    
    OrderCommission selectByOrderId(String orderId);
}
package rml.dao;

import java.util.List;
import rml.model.OrderType;

public interface OrderTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderType record);

    OrderType selectByPrimaryKey(Integer id);

    List<OrderType> selectAll();

    int updateByPrimaryKey(OrderType record);
}
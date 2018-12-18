package rml.service;

import java.util.List;

import rml.model.OrderType;

public interface OrderTypeServiceI
{
    int insert(OrderType record);

    List<OrderType> selectAll();
}

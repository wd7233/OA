package rml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.OrderTypeMapper;
import rml.model.OrderType;

@Service("orderTypeService")
public class OrderTypeServiceImpl implements OrderTypeServiceI
{
    @Autowired
    private OrderTypeMapper orderTypeMapper;
    
    @Override
    public int insert(OrderType record)
    {
        return orderTypeMapper.insert(record);
    }
    
    @Override
    public List<OrderType> selectAll()
    {
        return orderTypeMapper.selectAll();
    }
    
}

package rml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.EditPriceMapper;
import rml.dao.GoodsMapper;
import rml.dao.OrderCommissionMapper;
import rml.dao.OrderPddIdMapper;
import rml.model.EditPrice;
import rml.model.Goods;
import rml.model.OrderCommission;
import rml.model.OrderPddId;

@Service("orderpddService")
public class OrderPddServiceImpl implements OrderPddServiceI{

	@Autowired
	private OrderPddIdMapper orderPddIdMapper;

    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return orderPddIdMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderPddId record)
    {
        // TODO Auto-generated method stub
        return orderPddIdMapper.insert(record);
    }

    @Override
    public OrderPddId selectByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return orderPddIdMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderPddId> selectAll()
    {
        // TODO Auto-generated method stub
        return orderPddIdMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(OrderPddId record)
    {
        // TODO Auto-generated method stub
        return orderPddIdMapper.updateByPrimaryKey(record);
    }

    @Override
    public OrderPddId selectByOrderId(String orderId)
    {
        // TODO Auto-generated method stub
        return orderPddIdMapper.selectByOrderId(orderId);
    }



}

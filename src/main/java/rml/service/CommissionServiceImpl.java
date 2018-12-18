package rml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.EditPriceMapper;
import rml.dao.GoodsMapper;
import rml.dao.OrderCommissionMapper;
import rml.model.EditPrice;
import rml.model.Goods;
import rml.model.OrderCommission;

@Service("commissionService")
public class CommissionServiceImpl implements CommissionServiceI{

	@Autowired
	private OrderCommissionMapper orderCommissionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return orderCommissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderCommission record)
    {
        // TODO Auto-generated method stub
        return orderCommissionMapper.insert(record);
    }

    @Override
    public OrderCommission selectByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return orderCommissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderCommission> selectAll()
    {
        // TODO Auto-generated method stub
        return orderCommissionMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(OrderCommission record)
    {
        // TODO Auto-generated method stub
        return orderCommissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public OrderCommission selectByOrderId(String orderId)
    {
        // TODO Auto-generated method stub
        return orderCommissionMapper.selectByOrderId(orderId);
    }


}

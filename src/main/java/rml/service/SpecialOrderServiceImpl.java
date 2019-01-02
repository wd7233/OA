package rml.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.SpecialOrderMapper;
import rml.model.OrderDetail;
import rml.model.SpecialOrder;

@Service("specialOrderService")
public class SpecialOrderServiceImpl implements SpecialOrderServiceI{

	@Autowired
	private SpecialOrderMapper specialOrderMapper;

    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return specialOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SpecialOrder record)
    {
        return specialOrderMapper.insert(record);
    }

    @Override
    public SpecialOrder selectByPrimaryKey(Integer id)
    {
        return specialOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SpecialOrder> selectAll()
    {
        return specialOrderMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SpecialOrder record)
    {
        return specialOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SpecialOrder> selectOrderListByUser(Date startTime, Date endTime, Integer staffId, String keyWord, String orderState)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("staffId", staffId);
        map.put("orderState", orderState);
        map.put("keyWord", keyWord);
        return specialOrderMapper.selectOrderListByUser(map);
    }

    @Override
    public SpecialOrder selectByOrderId(String orderId)
    {
        return specialOrderMapper.selectByOrderId(orderId);
    }



}

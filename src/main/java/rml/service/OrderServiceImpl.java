package rml.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.OrderDetailMapper;
import rml.model.OrderDetail;

@Service("orderService")
public class OrderServiceImpl implements OrderServiceI{

	@Autowired
	private OrderDetailMapper orderMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return orderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderDetail record) {
		// TODO Auto-generated method stub
		return orderMapper.insert(record);
	}

	@Override
	public OrderDetail selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return orderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<OrderDetail> selectAll() {
		// TODO Auto-generated method stub
		return orderMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(OrderDetail record) {
		// TODO Auto-generated method stub
		return orderMapper.updateByPrimaryKey(record);
	}

    @Override
    public OrderDetail selectByOrderId(String orderId)
    {
        // TODO Auto-generated method stub
        return orderMapper.selectByOrderId(orderId);
    }

    @Override
    public List<OrderDetail> selectOrderByTime(Date startTime,Date endTime,String shopNumber,Integer type)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("shopNumber", shopNumber);
        map.put("type", type);
        return orderMapper.selectOrderByTime(map);
    }

    @Override
    public List<OrderDetail> selectOrderForLucre(Date startTime, Date endTime,String shopNumber)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("shopNumber", shopNumber);
        return orderMapper.selectOrderForLucre(map);
    }

    @Override
    public List<OrderDetail> selectOrderList(Date startTime, Date endTime, String keyWord,Integer staffId,Integer orderType)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("keyWord", keyWord);
        map.put("staffId", staffId);
        map.put("orderType", orderType);
        return orderMapper.selectOrderList(map);
    }

    @Override
    public List<OrderDetail> selectOrderByUser(Date startTime, Date endTime, String shopNumber, String keyWord)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("keyWord", keyWord);
        map.put("shopNumber", shopNumber);
        return orderMapper.selectOrderByUser(map);
    }

    @Override
    public List<OrderDetail> selectOrderListByUser(Date startTime, Date endTime, Integer staffId, String keyWord, Integer orderType)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("staffId", staffId);
        map.put("orderType", orderType);
        map.put("keyWord", keyWord);
        return orderMapper.selectOrderListByUser(map);
    }

    @Override
    public Integer selectCountByUser(Date startTime, Date endTime, Integer staffId, Integer type)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("staffId", staffId);
        map.put("type", type);      
        return orderMapper.selectCountByUser(map);
    }

    @Override
    public Integer selectEditCount(Date startTime, Date endTime, Integer staffId)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("staffId", staffId);
        return orderMapper.selectEditCount(map);
    }



}

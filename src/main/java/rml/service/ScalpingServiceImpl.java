package rml.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.CardInfoMapper;
import rml.dao.EditPriceMapper;
import rml.dao.GoodsMapper;
import rml.dao.ScalpingOrderMapper;
import rml.model.CardInfo;
import rml.model.EditPrice;
import rml.model.Goods;
import rml.model.ScalpingOrder;
import rml.util.DateUtil;

@Service("scalpingService")
public class ScalpingServiceImpl implements ScalpingServiceI{

	@Autowired
	private ScalpingOrderMapper scalpingOrderMapper;

    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return scalpingOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ScalpingOrder record)
    {
        // TODO Auto-generated method stub
        return scalpingOrderMapper.insert(record);
    }

    @Override
    public ScalpingOrder selectByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return scalpingOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ScalpingOrder> selectAll()
    {
        // TODO Auto-generated method stub
        return scalpingOrderMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ScalpingOrder record)
    {
        // TODO Auto-generated method stub
        return scalpingOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ScalpingOrder> selectOrder(String startTime, String endTime, String goodNumber,String keyWord)
    {
        startTime = startTime+ " 00:00:00";
        endTime = endTime+ " 23:59:59";
        Map<String,Object> map = new HashMap<String,Object>();
        if (!StringUtils.isEmpty(keyWord))
        {
            startTime = "2018-01-01 00:00:00";
            endTime = "2021-12-31 23:59:59";
        }
        map.put("startTime",  StringUtils.isEmpty(startTime) ? DateUtil.getYesterdayStart() : DateUtil.strToDateLong(startTime, "yy-MM-dd HH:mm:ss"));
        map.put("endTime", StringUtils.isEmpty(endTime) ? DateUtil.getYesterdayEnd() : DateUtil.strToDateLong(endTime, "yy-MM-dd HH:mm:ss"));
        map.put("goodNumber", goodNumber);
        map.put("keyWord", keyWord);
        return scalpingOrderMapper.selectOrder(map);
    }
    @Override
    public ScalpingOrder selectByOrderId(String orderId)
    {
        // TODO Auto-generated method stub
        return scalpingOrderMapper.selectByOrderId(orderId);
    }


}

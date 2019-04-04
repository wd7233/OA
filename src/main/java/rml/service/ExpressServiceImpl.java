package rml.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.CardInfoMapper;
import rml.dao.EditPriceMapper;
import rml.dao.ExpressMapper;
import rml.dao.GoodWeightMapper;
import rml.dao.GoodsMapper;
import rml.model.CardInfo;
import rml.model.EditPrice;
import rml.model.Express;
import rml.model.GoodWeight;
import rml.model.Goods;

@Service("expressService")
public class ExpressServiceImpl implements ExpressServiceI
{
    
    @Autowired
    private ExpressMapper expressMapper;
    
    @Autowired
    private GoodWeightMapper goodWeightMapper;
    
    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        return expressMapper.deleteByPrimaryKey(id);
    }
    
    @Override
    public int insert(Express record)
    {
        return expressMapper.insert(record);
    }
    
    @Override
    public Express selectByPrimaryKey(Integer id)
    {
        return expressMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public List<Express> selectAll()
    {
        return expressMapper.selectAll();
    }
    
    @Override
    public int updateByPrimaryKey(Express record)
    {
        return expressMapper.updateByPrimaryKey(record);
    }
    
    @Override
    public Express selectPrice(String weight, String province, Integer type)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("weight", weight);
        map.put("province", province);
        map.put("type", type);
        return expressMapper.selectPrice(map);
    }
    
    @Override
    public Express selectPriceBySku(String sku, String province, Integer type)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sku", sku);
        map.put("province", province);
        map.put("type", type);
        return expressMapper.selectPriceBySku(map);
    }
    
    @Override
    public GoodWeight selectWeightBySku(String sku)
    {
        GoodWeight goodWeight = goodWeightMapper.selectWeightBySku(sku);
        return goodWeight;
    }
    
}

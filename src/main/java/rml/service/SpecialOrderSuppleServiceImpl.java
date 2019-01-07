package rml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.CardInfoMapper;
import rml.dao.EditPriceMapper;
import rml.dao.GoodsMapper;
import rml.dao.SpecialOrderSuppleMapper;
import rml.model.CardInfo;
import rml.model.EditPrice;
import rml.model.Goods;
import rml.model.SpecialOrderSupple;

@Service("specialOrderSuppleService")
public class SpecialOrderSuppleServiceImpl implements SpecialOrderSuppleServiceI{

    @Autowired
    private SpecialOrderSuppleMapper specialOrderSuppleMapper;
    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return specialOrderSuppleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SpecialOrderSupple record)
    {
        // TODO Auto-generated method stub
        return specialOrderSuppleMapper.insert(record);
    }

    @Override
    public SpecialOrderSupple selectByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return specialOrderSuppleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SpecialOrderSupple> selectAll()
    {
        // TODO Auto-generated method stub
        return specialOrderSuppleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SpecialOrderSupple record)
    {
        // TODO Auto-generated method stub
        return specialOrderSuppleMapper.updateByPrimaryKey(record);
    }

    @Override
    public SpecialOrderSupple selectByOrderId(String orderId)
    {
        // TODO Auto-generated method stub
        return specialOrderSuppleMapper.selectByOrderId(orderId);
    }



}

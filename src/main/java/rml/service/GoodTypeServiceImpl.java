package rml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.GoodTypeMapper;
import rml.model.GoodType;

@Service("goodTypeService")
public class GoodTypeServiceImpl implements GoodTypeServiceI
{
    
    @Autowired
    private GoodTypeMapper goodTypeMapper;
    
    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public int insert(GoodType record)
    {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public GoodType selectByPrimaryKey(Integer id)
    {
        return goodTypeMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public List<GoodType> selectAll()
    {
        return goodTypeMapper.selectAll();
    }
    
    @Override
    public int updateByPrimaryKey(GoodType record)
    {
        // TODO Auto-generated method stub
        return 0;
    }
    
}

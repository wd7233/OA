package rml.dao;

import java.util.List;
import java.util.Map;

import rml.model.Express;

public interface ExpressMapper
{
    int deleteByPrimaryKey(Integer id);
    
    int insert(Express record);
    
    Express selectByPrimaryKey(Integer id);
    
    List<Express> selectAll();
    
    int updateByPrimaryKey(Express record);
    
    Express selectPrice(Map<String, Object> map);
    
    Express selectPriceBySku(Map<String, Object> map);
}
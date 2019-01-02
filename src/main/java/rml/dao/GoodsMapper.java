package rml.dao;

import java.util.List;
import rml.model.Goods;

public interface GoodsMapper
{
    int deleteByPrimaryKey(Integer id);
    
    int insert(Goods record);
    
    Goods selectByPrimaryKey(Integer id);
    
    List<Goods> selectAll();
    
    int updateByPrimaryKey(Goods record);
    
    int seletcByGoodId(String goodId);
    
    List<Goods> selectRandGoods();
    
    List<Goods> seletcByStaffId(Integer staffId);
    
    List<Goods> seletcByType(Integer goodType);
}
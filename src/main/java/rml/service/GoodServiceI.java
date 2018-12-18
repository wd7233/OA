package rml.service;

import java.util.List;

import rml.model.Goods;

public interface GoodServiceI
{
    
    int insert(Goods goods);
    List<Goods>  selectRandGoods();
    int seletcByGoodId(String id);
    List<Goods> seletcByStaffId(Integer staffId);
    List<Goods> selectAll();
    int deleteByPrimaryKey(Integer id);
    List<Goods> seletcByType(Integer goodType);
}

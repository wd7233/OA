package rml.service;

import java.util.List;

import rml.model.GoodType;
import rml.model.Goods;

public interface GoodTypeServiceI
{
    int deleteByPrimaryKey(Integer id);

    int insert(GoodType record);

    GoodType selectByPrimaryKey(Integer id);

    List<GoodType> selectAll();

    int updateByPrimaryKey(GoodType record);
}

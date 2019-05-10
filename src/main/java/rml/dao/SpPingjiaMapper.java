package rml.dao;

import java.util.List;

import rml.model.SpPingjia;


public interface SpPingjiaMapper
{
    List<SpPingjia> selectRand(String good);
    int insert(SpPingjia sp);
   int updateByPrimaryKey(SpPingjia sp);
}
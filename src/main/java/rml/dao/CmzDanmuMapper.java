package rml.dao;

import java.util.List;

import rml.model.CmzDanmu;
import rml.model.Shop;


public interface CmzDanmuMapper
{
    List<CmzDanmu> selectAll();
    
    List<CmzDanmu> selectTenMin();
    List<CmzDanmu> selectRand();
    int updateByPrimaryKey(CmzDanmu record); 
    int insert(CmzDanmu cdm);
}
package rml.service;

import java.util.List;

import rml.model.CmzDanmu;

public interface CmzServiceI
{
    
    List<CmzDanmu> selectAll();
    List<CmzDanmu> selectTenMin();
    List<CmzDanmu> selectRand();
    int updateByPrimaryKey(CmzDanmu record); 
    int insert(CmzDanmu cdm);


}

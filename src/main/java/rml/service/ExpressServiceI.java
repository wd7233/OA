package rml.service;

import java.util.List;

import rml.model.CardInfo;
import rml.model.Express;
import rml.model.GoodWeight;

public interface ExpressServiceI
{
    
    int deleteByPrimaryKey(Integer id);

    int insert(Express record);

    Express selectByPrimaryKey(Integer id);

    List<Express> selectAll();

    int updateByPrimaryKey(Express record);
    Express selectPrice (String weight,String province,Integer type);
    Express selectPriceBySku(String sku,String province,Integer type);
    GoodWeight  selectWeightBySku(String sku);
}

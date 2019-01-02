package rml.dao;

import java.util.List;
import java.util.Map;

import rml.model.Shop;

public interface ShopMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shop record);

    Shop selectByPrimaryKey(Integer id);

    List<Shop> selectAll();

    int updateByPrimaryKey(Shop record);
    
    List<Shop>  selectByUser(Integer userId);
	Shop getByID(Integer id);
	Shop selectByNumber(String number);
	  //查询已经分配的店铺
    List<Shop> selectHasStaff();
    //查詢需要授權的店鋪
   List<Shop> selectNeedVtd();
   List<Shop> selectByKeyword(Map<String,Object> paramMap);
   Shop selectByAccount(String account);

    List<String> selectShopNumByStaff(String name);
}
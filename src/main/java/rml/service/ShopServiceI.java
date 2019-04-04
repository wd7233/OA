package rml.service;

import java.util.List;

import rml.model.Shop;

public interface ShopServiceI {

	List<Shop> selectAll();
	List<Shop>  selectByUser(Integer userId);
	Shop getByID(Integer id);
	Shop getByNumber(String number);
	int updateShop(Shop shop);
	int addShop(Shop shop);
	  //查询已经分配的店铺
    List<Shop> selectHasStaff();
    List<Shop> selectNeedVtd();
    List<Shop> selectByKeyword(Integer staffId , String keyWord);
    Shop selectByAccount(String account);
    Shop selectByName(String shopName);
}

package rml.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.ShopMapper;
import rml.dao.StaffMapper;
import rml.model.Shop;

@Service("shopService")
public class ShopServiceImpl implements ShopServiceI{

	@Autowired
	private ShopMapper shopMapper;


    @Override
    public List<Shop> selectAll()
    {
        // TODO Auto-generated method stub
        return shopMapper.selectAll();
    }

    @Override
    public List<Shop> selectByUser(Integer userId)
    {
        // TODO Auto-generated method stub
        return shopMapper.selectByUser(userId);
    }

    @Override
    public Shop getByID(Integer id)
    {
        // TODO Auto-generated method stub
        return shopMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateShop(Shop shop)
    {
        return shopMapper.updateByPrimaryKey(shop);
    }

    @Override
    public int addShop(Shop shop)
    {
        return shopMapper.insert(shop);
    }

    @Override
    public Shop getByNumber(String number)
    {
        // TODO Auto-generated method stub
        return shopMapper.selectByNumber(number);
    }

	@Override
	public List<Shop> selectHasStaff() {
		// TODO Auto-generated method stub
		return shopMapper.selectHasStaff();
	}

	@Override
	public List<Shop> selectNeedVtd() {
		// TODO Auto-generated method stub
		return shopMapper.selectNeedVtd();
	}

    @Override
    public List<Shop> selectByKeyword(Integer staffId ,String keyWord)
    {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("staffId", staffId);
        paramMap.put("keyWord", keyWord);
        return shopMapper.selectByKeyword(paramMap);
    }

    @Override
    public Shop selectByAccount(String account)
    {
        return shopMapper.selectByAccount(account);
    }

    @Override
    public Shop selectByName(String shopName)
    {
        return shopMapper.selectByName(shopName);
    }
		

}

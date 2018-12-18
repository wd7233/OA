package rml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.CardInfoMapper;
import rml.dao.EditPriceMapper;
import rml.dao.GoodsMapper;
import rml.model.CardInfo;
import rml.model.EditPrice;
import rml.model.Goods;

@Service("cardInfoService")
public class CardInfoServiceImpl implements CardInfoServiceI{

	@Autowired
	private CardInfoMapper cardInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return cardInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CardInfo record)
    {
        // TODO Auto-generated method stub
        return cardInfoMapper.insert(record);
    }

    @Override
    public CardInfo selectByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return cardInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CardInfo> selectAll()
    {
        // TODO Auto-generated method stub
        return cardInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(CardInfo record)
    {
        // TODO Auto-generated method stub
        return cardInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CardInfo> selectByUserId(Integer userId)
    {
        // TODO Auto-generated method stub
        return cardInfoMapper.selectByUserId(userId);
    }


}

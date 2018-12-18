package rml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.GoodsMapper;
import rml.model.Goods;

@Service("goodService")
public class GoodServiceImpl implements GoodServiceI{

	@Autowired
	private GoodsMapper goodsMapper;

    @Override
    public int insert(Goods goods)
    {
        return goodsMapper.insert(goods);
    }

    @Override
    public List<Goods> selectRandGoods()
    {
        return goodsMapper.selectRandGoods();
    }

    @Override
    public int seletcByGoodId(String id)
    {
        return goodsMapper.seletcByGoodId(id);
    }

    @Override
    public List<Goods> seletcByStaffId(Integer staffId)
    {
        return goodsMapper.seletcByStaffId(staffId);
    }

	@Override
	public List<Goods> selectAll() {
		return goodsMapper.selectAll();
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return goodsMapper.deleteByPrimaryKey(id);
	}

    @Override
    public List<Goods> seletcByType(Integer goodType)
    {
        return goodsMapper.seletcByType(goodType);
    }


}

package rml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.EditPriceMapper;
import rml.dao.GoodsMapper;
import rml.model.EditPrice;
import rml.model.Goods;

@Service("editPriceService")
public class EditPriceServiceImpl implements EditPriceServiceI{

	@Autowired
	private EditPriceMapper editPriceMapper;

    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return editPriceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(EditPrice record)
    {
        // TODO Auto-generated method stub
        return editPriceMapper.insert(record);
    }

    @Override
    public EditPrice selectByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return editPriceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EditPrice> selectAll()
    {
        // TODO Auto-generated method stub
        return editPriceMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(EditPrice record)
    {
        return editPriceMapper.updateByPrimaryKey(record);
    }

    @Override
    public EditPrice selectByOrderId(String orderId)
    {
        return editPriceMapper.selectByOrderId(orderId);
    }

}

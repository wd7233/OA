package rml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.CmzDanmuMapper;
import rml.model.CmzDanmu;

@Service("CmzServiceImpl")
public class CmzServiceImpl implements CmzServiceI
{
    
    @Autowired
    private CmzDanmuMapper cmzDanmuMapper;
    
    @Override
    public List<CmzDanmu> selectAll()
    {
        return cmzDanmuMapper.selectAll();
    }

    @Override
    public List<CmzDanmu> selectTenMin()
    {
        // TODO Auto-generated method stub
        return cmzDanmuMapper.selectTenMin();
    }

    @Override
    public List<CmzDanmu> selectRand()
    {
        // TODO Auto-generated method stub
        return cmzDanmuMapper.selectRand();
    }

    @Override
    public int updateByPrimaryKey(CmzDanmu record)
    {
        // TODO Auto-generated method stub
        return cmzDanmuMapper.updateByPrimaryKey(record);
    }

    @Override
    public int insert(CmzDanmu cdm)
    {
        return cmzDanmuMapper.insert(cdm);
    }
    
}

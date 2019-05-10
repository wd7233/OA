package rml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.CmzDanmuMapper;
import rml.dao.OaMenuMapper;
import rml.model.CmzDanmu;
import rml.model.OaMenu;

@Service("MenuServiceImpl")
public class MenuServiceImpl implements MenuServiceI
{
    
    @Autowired
    private OaMenuMapper menuMapper;

    @Override
    public List<OaMenu> selectParentMenu(Integer userId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OaMenu> selectMenu(Integer parentId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OaMenu> selectAllMenu(Integer userId)
    {
        List<OaMenu> menuParent = menuMapper.selectParentMenu(userId);
        for(OaMenu m :menuParent) 
        {
            List<OaMenu>  ms =   menuMapper.selectMenu(m.getId());
            m.setMenus(ms);
        }
        return menuParent;
    }
    
}

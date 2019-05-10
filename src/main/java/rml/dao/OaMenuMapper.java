package rml.dao;

import java.util.List;
import rml.model.OaMenu;

public interface OaMenuMapper
{
    int deleteByPrimaryKey(Integer id);
    
    int insert(OaMenu record);
    
    OaMenu selectByPrimaryKey(Integer id);
    
    List<OaMenu> selectAll();
    
    int updateByPrimaryKey(OaMenu record);
    
    List<OaMenu> selectParentMenu(Integer userId);
    
    List<OaMenu> selectMenu(Integer parentId);
}
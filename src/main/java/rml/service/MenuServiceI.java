package rml.service;

import java.util.List;

import rml.model.OaMenu;

public interface MenuServiceI
{
    
    List<OaMenu> selectParentMenu(Integer userId);
    
    List<OaMenu> selectMenu(Integer parentId);
    List<OaMenu> selectAllMenu(Integer userId);
}

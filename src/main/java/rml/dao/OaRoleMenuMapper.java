package rml.dao;

import java.util.List;
import rml.model.OaRoleMenu;

public interface OaRoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaRoleMenu record);

    OaRoleMenu selectByPrimaryKey(Integer id);

    List<OaRoleMenu> selectAll();

    int updateByPrimaryKey(OaRoleMenu record);
}
package rml.dao;

import java.util.List;
import rml.model.OaUserRole;

public interface OaUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaUserRole record);

    OaUserRole selectByPrimaryKey(Integer id);

    List<OaUserRole> selectAll();

    int updateByPrimaryKey(OaUserRole record);
}
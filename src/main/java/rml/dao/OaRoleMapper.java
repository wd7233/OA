package rml.dao;

import java.util.List;
import rml.model.OaRole;

public interface OaRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaRole record);

    OaRole selectByPrimaryKey(Integer id);

    List<OaRole> selectAll();

    int updateByPrimaryKey(OaRole record);
}
package rml.dao;

import java.util.List;
import rml.model.CompanyUser;

public interface CompanyUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyUser record);

    CompanyUser selectByPrimaryKey(Integer id);

    List<CompanyUser> selectAll();

    int updateByPrimaryKey(CompanyUser record);
}
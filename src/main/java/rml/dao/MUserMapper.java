package rml.dao;

import java.util.List;
import rml.model.MUser;

public interface MUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(MUser record);

    MUser selectByPrimaryKey(String id);

    List<MUser> selectAll();

    int updateByPrimaryKey(MUser record);
}
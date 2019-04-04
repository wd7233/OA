package rml.dao;

import java.util.List;
import rml.model.KeywordPlan;

public interface KeywordPlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KeywordPlan record);

    KeywordPlan selectByPrimaryKey(Integer id);

    List<KeywordPlan> selectAll();

    int updateByPrimaryKey(KeywordPlan record);
}
package rml.dao;

import java.util.List;
import rml.model.ScalpingPlan;

public interface ScalpingPlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScalpingPlan record);

    ScalpingPlan selectByPrimaryKey(Integer id);

    List<ScalpingPlan> selectAll();

    int updateByPrimaryKey(ScalpingPlan record);
}
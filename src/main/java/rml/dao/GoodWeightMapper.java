package rml.dao;

import java.util.List;
import rml.model.GoodWeight;

public interface GoodWeightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodWeight record);

    GoodWeight selectByPrimaryKey(Integer id);

    List<GoodWeight> selectAll();

    int updateByPrimaryKey(GoodWeight record);
}
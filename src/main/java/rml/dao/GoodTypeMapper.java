package rml.dao;

import java.util.List;
import rml.model.GoodType;

public interface GoodTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodType record);

    GoodType selectByPrimaryKey(Integer id);

    List<GoodType> selectAll();

    int updateByPrimaryKey(GoodType record);
}
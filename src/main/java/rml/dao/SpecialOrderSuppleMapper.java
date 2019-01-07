package rml.dao;

import java.util.List;
import rml.model.SpecialOrderSupple;

public interface SpecialOrderSuppleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpecialOrderSupple record);

    SpecialOrderSupple selectByPrimaryKey(Integer id);

    List<SpecialOrderSupple> selectAll();

    int updateByPrimaryKey(SpecialOrderSupple record);
    SpecialOrderSupple selectByOrderId(String orderId);
}
package rml.dao;

import java.util.List;
import rml.model.EditPrice;

public interface EditPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EditPrice record);

    EditPrice selectByPrimaryKey(Integer id);

    List<EditPrice> selectAll();
    
    EditPrice selectByOrderId(String orderId);
    int updateByPrimaryKey(EditPrice record);
}
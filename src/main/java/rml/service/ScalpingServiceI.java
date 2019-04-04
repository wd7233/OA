package rml.service;

import java.util.List;

import rml.model.ScalpingOrder;

public interface ScalpingServiceI
{
    int deleteByPrimaryKey(Integer id);

    int insert(ScalpingOrder record);

    ScalpingOrder selectByPrimaryKey(Integer id);

    List<ScalpingOrder> selectAll();

    int updateByPrimaryKey(ScalpingOrder record);
    public ScalpingOrder selectByOrderId(String orderId);
    List<ScalpingOrder> selectOrder(String startTime, String endTime, String goodNumber,String keyWord);
}

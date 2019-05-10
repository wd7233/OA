package rml.service;

import java.util.List;

import rml.model.ScalpingOrder;
import rml.model.SpPingjia;

public interface ScalpingServiceI
{
    int deleteByPrimaryKey(Integer id);

    int insert(ScalpingOrder record);

    ScalpingOrder selectByPrimaryKey(Integer id);

    List<ScalpingOrder> selectAll();

    int updateByPrimaryKey(ScalpingOrder record);
    public ScalpingOrder selectByOrderId(String orderId);
    List<ScalpingOrder> selectOrder(String startTime, String endTime, String goodNumber,String keyWord);
    //根据商品类型，随机获取一条评价
    SpPingjia getSpPingjia(String good);
    int insetrPingjia(SpPingjia sp);
    int updateByPrimaryKey(SpPingjia sp);
}   

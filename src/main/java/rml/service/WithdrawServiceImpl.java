package rml.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import rml.dao.CardInfoMapper;
import rml.dao.ShopMapper;
import rml.dao.WithdrawMapper;
import rml.model.CardAmountInfo;
import rml.model.CardInfo;
import rml.model.Withdraw;
import rml.model.WithdrawName;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    private WithdrawMapper withdrawMapper;

    @Autowired
    private CardInfoMapper cardInfoMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public Integer save(Withdraw withdraw) {
        Date date = new Date();
        withdraw.setIsWithdraw((byte) 0);
        withdraw.setCreateTime(date);
        withdraw.setUpdateTime(date);
        return withdrawMapper.insert(withdraw);
    }

    @Override
    public List<CardAmountInfo> getCardAmountSum() {
        List<CardAmountInfo> listResult = new ArrayList<CardAmountInfo>();
        List<CardInfo> list = cardInfoMapper.selectAll();
        for (CardInfo cardInfo : list){
            Double amount = withdrawMapper.selectAmountSum(cardInfo.getName());
            CardAmountInfo cardAmountInfo = new CardAmountInfo();
            BeanUtils.copyProperties(cardInfo,cardAmountInfo);
            cardAmountInfo.setAmount(amount);
            listResult.add(cardAmountInfo);
        }
        return listResult;
    }

    @Override
    @Transactional
    public Integer withdrawed(String name) {
        //查询店铺名称
        List<String> list = shopMapper.selectShopNumByStaff(name);
        if (CollectionUtils.isEmpty(list)){
            return 1;
        }
        for (String shopNumber : list){
            withdrawMapper.updateByShopNumber(shopNumber);
        }
        return 1;
    }

    @Override
    public List<WithdrawName> userNoList(String userName) {
        String selStr= null;
        try {
            selStr = java.net.URLDecoder.decode(userName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return withdrawMapper.listWithdrawName(selStr);
    }

    @Override
    public Integer withdrawedAll() {
        return withdrawMapper.updateAll();
    }
}

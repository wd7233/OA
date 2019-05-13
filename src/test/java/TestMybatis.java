import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import rml.model.Express;
import rml.model.GoodWeight;
import rml.model.MUser;
import rml.service.ExpressServiceI;
import rml.service.MUserServiceI;
import rml.util.HttpClientUtil;
import rml.util.HttpURLConnectionUtil;

@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml"})
public class TestMybatis
{

    private static final Logger logger = Logger.getLogger(TestMybatis.class);

    @Autowired
    private MUserServiceI muserService;
    @Autowired
    private ExpressServiceI expressService;
    

    // public MUserServiceI getMuserService() {
    // return muserService;
    // }
    //
    // @Autowired
    // public void setMuserService(MUserServiceI muserService) {
    // this.muserService = muserService;
    // }

    @Test
    public void test1()
    {

        List<MUser> list = muserService.selectAll();
        logger.info(JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss"));
    }
    private String selectExpress(String content)
    {
        String str = "";
        if (content.split("，").length < 3 && content.split("，")[1].length() <3)
        {
            String province = content.split("，")[0];
            String weight = content.split("，")[1];
            Express baishiExpress = expressService.selectPrice(weight, province, 0);
            Express youzhengExpress = expressService.selectPrice(weight, province, 1);
            // Express shengtongExpress = expressService.selectPrice(weight, province, 2);
            Express yuantongExpress = expressService.selectPrice(weight, province, 3);
            String yzPrice = youzhengExpress == null ? "-" : youzhengExpress.getPrice();
            String bsPrice = baishiExpress == null ? "-" : baishiExpress.getPrice();
            String ytPrice = yuantongExpress == null ? "-" : yuantongExpress.getPrice();
            str += "省份：" + province + ", 重量：" + weight + "\n";
            str += "邮政：" + yzPrice + "\n" + "百世：" + bsPrice + "\n" + "圆通：" + ytPrice;
        }
        else
        {
            String province = content.split("，")[0];
            String sku = content.split("，")[1];
            int cnt = content.split("，").length == 3 ? Integer.parseInt(content.split("，")[2]) : 1;
            GoodWeight g  = expressService.selectWeightBySku(sku);
            String weightDB =   g == null?"0":  g.getWeight();
            Double weightDouble = Math.ceil(Double.parseDouble(weightDB) * cnt);
            String weight = weightDouble.intValue() + "";
            Express baishiExpress = expressService.selectPrice(weight, province, 0);
            Express youzhengExpress = expressService.selectPrice(weight, province, 1);
            // Express shengtongExpress = expressService.selectPrice(weight, province, 2);
            Express yuantongExpress = expressService.selectPrice(weight, province, 3);
            String yzPrice = youzhengExpress == null ? "-" : youzhengExpress.getPrice();
            String bsPrice = baishiExpress == null ? "-" : baishiExpress.getPrice();
            String ytPrice = yuantongExpress == null ? "-" : yuantongExpress.getPrice();
            str += "省份：" + province + ", 重量：" + weight + "\n";
            str += "邮政：" + yzPrice + "\n" + "百世：" + bsPrice + "\n" + "圆通：" + ytPrice;
        
            
        }
        return str;
    }
    @Test
    public void getPrice()
    {
        String result = selectExpress("湖南，46112，2");
        System.out.println(result);
    }
    @Test
    public void test2()
    {

        MUser muser = new MUser();
        muser.setId("0000");
        muser.setName("aaaa");
        muser.setAge(1234);
        muser.setAddress("ABCD");
        int i = muserService.insert(muser);
        logger.info(JSON.toJSONStringWithDateFormat("add " + i, "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void test3()
    {

        MUser muser = new MUser();
        muser.setId("1");
        muser.setName("bbbb");
        muser.setAge(1234);
        muser.setAddress("ABCD");
        int i = muserService.update(muser);
        logger.info(JSON.toJSONStringWithDateFormat("update " + i, "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void test4()
    {

        MUser muser = new MUser();
        muser.setId("1");
        muser.setName("bbbb");
        muser.setAge(1234);
        muser.setAddress("ABCD");
        int i = muserService.delete("0000");
        logger.info(JSON.toJSONStringWithDateFormat("delete " + i, "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void MenuTest()
    {
        try
        {
            String token = HttpURLConnectionUtil.getToken();
            // 将菜单对象转换成json字符串
            String jsonMenu = new JSONObject(getMenu()).toString();
            System.out.println(jsonMenu.toString());
            JSONObject jsonObject = HttpClientUtil.httpRequest("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token, "POST", jsonMenu);
            if (jsonObject != null)
            {
                System.out.println(jsonObject.toString());
            }
        }
        catch (ParseException | IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 组装菜单数据
     *
     * @return
     */
    private static Menu getMenu()
    {
        String userUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
        String url = "http://www.crong.vip/xx_QAQ/map/showLocation";
        userUrl += "?appid=wx199a341daf297b85";
        userUrl += "&redirect_uri=" + url;
        System.out.println("redirect_uri：" + url);
        userUrl += "&response_type=" + "code";
        // userUrl += "&scope=" + "snsapi_userinfo";
        userUrl += "&scope=" + "snsapi_base";
        userUrl += "&state=" + "1";
        userUrl += "#wechat_redirect";
        System.out.println("userUrl：" + userUrl);

        CommonButton btn11 = new CommonButton();
        btn11.setName("理牌技巧");
        btn11.setType("view");
        btn11.setUrl("http://www.crong.vip/xx_QAQ/Price/savePrice");

        CommonButton btn12 = new CommonButton();
        btn12.setName("舍牌视角");
        btn12.setType("view");
        btn12.setUrl("http://www.crong.vip/xx_QAQ/Price/getPrice");

        CommonButton btn13 = new CommonButton();
        btn13.setName("听牌技巧");
        btn13.setType("view");
        btn13.setUrl(userUrl);

        CommonButton btn21 = new CommonButton();
        btn21.setName("诸葛麻将");
        btn21.setType("pic_sysphoto");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();

        btn22.setName("博士说牌");
        btn22.setType("view");
        btn22.setUrl("http://www.crong.vip/xx_QAQ/map/showLocation");

        CommonButton btn23 = new CommonButton();
        btn23.setName("赛事回放");
        btn23.setType("pic_sysphoto");
        btn23.setKey("23");


        CommonButton btn31 = new CommonButton();
        btn31.setName("棋牌室-麻将机麻将");
        btn31.setUrl("http://www.crong.vip/xx_QAQ/gps3.jsp");
        btn31.setType("view");

        CommonButton btn32 = new CommonButton();
        btn32.setName("家用-手搓麻将");
        btn32.setType("view");
        btn32.setUrl("http://mp.weixin.qq.com/s/eFJoOvC1f7z6pPhm7NSLfw");

        CommonButton btn33 = new CommonButton();
        btn33.setName("定制-卡通麻将");
        btn33.setType("view");
        btn33.setUrl("http://mp.weixin.qq.com/s/ck5F-ILcR8m-pLxx-Q5nLQ");

        /**
         * 微信： mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
         */

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("麻将技巧");
        // 一级下有4个子菜单
        mainBtn1.setSub_button(new CommonButton[] {btn11, btn12, btn13});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("麻将视频");
        mainBtn2.setSub_button(new CommonButton[] {btn21, btn22, btn23});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("麻将商城");
        mainBtn3.setSub_button(new CommonButton[] {btn31, btn32, btn33});

        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] {mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }
    public static void main(String[] args)
    {
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(new Date());
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(new Date());
        calEnd.add(Calendar.MONTH, 2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while (calEnd.getTime().after(calBegin.getTime()))  {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
           System.out.println(sdf.format(calBegin.getTime()));
        }
    }
}

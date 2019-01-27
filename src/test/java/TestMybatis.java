import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import rml.model.MUser;
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
        btn11.setName("提交价格");
        btn11.setType("view");
        btn11.setUrl("http://www.crong.vip/xx_QAQ/Price/savePrice");
        
        CommonButton btn12 = new CommonButton();
        btn12.setName("查询价格");
        btn12.setType("view");
        btn12.setUrl("http://www.crong.vip/xx_QAQ/Price/getPrice");
        
        CommonButton btn13 = new CommonButton();
        btn13.setName("圈子搜搜");
        btn13.setType("view");
        btn13.setUrl(userUrl);
        
        CommonButton btn21 = new CommonButton();
        btn21.setName("宠物识别");
        btn21.setType("pic_sysphoto");
        btn21.setKey("21");
        
        CommonButton btn22 = new CommonButton();
        
        btn22.setName("朋友圈");
        btn22.setType("view");
        btn22.setUrl("http://www.crong.vip/xx_QAQ/map/showLocation");
        
        CommonButton btn23 = new CommonButton();
        btn23.setName("文字识别");
        btn23.setType("pic_sysphoto");
        btn23.setKey("23");
        
        CommonButton btn24 = new CommonButton();
        btn24.setName("人脸识别");
        btn24.setType("pic_sysphoto");
        btn24.setKey("24");
        
        CommonButton btn25 = new CommonButton();
        btn25.setName("扫一扫");
        btn25.setType("scancode_push");
        btn25.setKey("25");
        
        CommonButton btn31 = new CommonButton();
        btn31.setName("地理位置3");
        btn31.setUrl("http://www.crong.vip/xx_QAQ/gps3.jsp");
        btn31.setType("view");
        
        CommonButton btn32 = new CommonButton();
        btn32.setName("星座指南");
        btn32.setType("view");
        btn32.setUrl("http://mp.weixin.qq.com/s/eFJoOvC1f7z6pPhm7NSLfw");
        
        CommonButton btn33 = new CommonButton();
        btn33.setName("幽默笑话");
        btn33.setType("view");
        btn33.setUrl("http://mp.weixin.qq.com/s/ck5F-ILcR8m-pLxx-Q5nLQ");
        
        /**
         * 微信： mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
         */
        
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("生活助手");
        // 一级下有4个子菜单
        mainBtn1.setSub_button(new CommonButton[] {btn11, btn12, btn13});
        
        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("智能服务");
        mainBtn2.setSub_button(new CommonButton[] {btn21, btn22, btn23, btn24, btn25});
        
        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多体验");
        mainBtn3.setSub_button(new CommonButton[] {btn31, btn32, btn33});
        
        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] {mainBtn1, mainBtn2, mainBtn3});
        
        return menu;
    }
    
}

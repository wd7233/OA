<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta content="telephone=no" name="format-detection"/>
    <meta name="robots" content="all">
    <title>逆天改命，就看这签！</title><script> window._imgBtopsHost = ""</script>
    <link href="<%=path %>/year/css/base.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/year/css/index.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<%=path %>/year/css/index2.css">
    <script src="<%=path %>/year/js/jquery-1.10.2.min.js"></script>
    <script src="<%=path %>/year/js/base.v1.6.js"></script>
    <script src="<%=path %>/year/js/three.min.js"></script>
    <script src="<%=path %>/year/js/deviceori.js"></script>
    <script src="<%=path %>/year/js/html2tocanvas.js"></script>
    <script src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
    <script>
              
    </script>
</head>
<body>
<div style="font-family:'myFont';color: white; overflow: hidden; height: 0; width: 0; ">
    想知道&鸡年你的王者运势将会如何？&召唤师，不妨来抽一签？长按保存 长按扫描二维码，查看你的新年运势
正在解签
    <div class="result_desc"><p>
        背负着队伍成败的命脉<br>
        <i>宜出肉装抗伤害<br>
            忌打输出易崩盘<br></i>
        千错万错，出肉一定对<br>
        <span>坦克</span>打团照脸撸就行
    </p>
    </div>
    <div class="result_desc"><p>
        <i>请叫我</i><span>中路杀神</span><br>
        宜刷兵，刷野，支援<br>
        忌盲目游走，<i>乱带节奏</i><br>
        刷兵攒经济，团战火力全开<br>
        <i>新的一年，我是大刷子</i>

    </p>
    </div>
    <div class="result_desc"><p>
        峡谷<span>荒野求生</span><br>
        <i>宜胆大心细，意识至上</i><br>
        忌到对面浪调戏红蓝CP<br>
        <i>节奏大师带飞队友</i><br>
        战局胜负手，打野是关键


    </p>
    </div>
    <div class="result_desc"><p>
        <span>团队输出</span>的灵魂核心<br>
        <i>宜稳定发育，受队友照顾<br>
            忌人群中尬舞，全局梦游</i><br>
        拯救世界的重任全靠你<br>
        不是所有的<span>AD</span>都能叫<span>C</span>


    </p>
    </div>
    <div class="result_desc"><p>
        <i>峡谷中的保姆</i><br>
        宜照顾射手健康成长<br>
        <i>忌出输出独自偷欢</i><br>
        忍辱负重，<span>辅助</span>当先<br>
        小小身材<span>大力量</span>


    </p>
    </div>
    <div class="result_desc"><p>
        游走刀锋边缘<br>
        宜<span>秀</span>，装完就跑<br>
        忌1V5，出师未捷身先死<br>
        <span>一套爆发</span>杀遍全场<br>
        <i>刺客的尊严由我守护</i>

    </p>
    </div>
    <div class="result_desc"><p>
        一张<i>嘴</i>，成就一个<i>王者</i><br>
        <i>宜温柔指挥作战<br>
            忌上头怒喷队友<br></i>
        有实力懂指挥才是<span>最强</span><br>
        千万别当<span>嘴强</span>哟


    </p>
    </div>
    <div class="result_desc"><p>
        <span>躺</span>着都能<span>赢</span><br>
        宜找个粗壮的<span>大腿</span>抱<br>
        忌送人头，抢资源<br>
        <i>王者里的不解之谜<br>
            躺着都能上王者</i>


    </p>
    </div>
</div>
<div class="js_musicctr musicimg">
    <audio src="<%=path %>/year/bg.mp3" id="music" loop="loop"></audio>
    <img class="roai" src="<%=path %>/year/images/musicon.png" alt="">
    <img src="<%=path %>/year/images/musicoff.png" style="display: none;" alt="">
</div>
<div class="back page page1" style="display: block">
    <div class="people">
        <img src="<%=path %>/year/images/loading.png">
        <p id="loadNumber">0%</p>
    </div>
</div>
<script type="text/templete" id="htmlbody">
<div class=" back page page2" style="display: none;">
    <div class="paper">
        <div class="main">
            <img src="<%=path %>/year/images/kuang.png">
            <div class="Firework">
                <div class="small_fire ani_big"><img src="<%=path %>/year/images/icon5.png"/></div>
                <div class="big_fire ani_big2"><img src="<%=path %>/year/images/icon6.png"/></div>
            </div>
            <div class="Firework">
                <div class="rightsmall_fire ani_big"><img src="<%=path %>/year/images/icon5.png"/></div>
                <div class="right big_fire ani_big2"><img src="<%=path %>/year/images/icon6.png"/></div>
            </div>
            <div class="desc">
                <p class="temple">想知道&鸡年你的王者运势将会如何？&召唤师，不妨来抽一签？</p>
                <p class="target"></p>
            </div>
            <div class="lantern">
                <div class="small_fire ani_shark1"><img src="<%=path %>/year/images/icon3.png"/></div>
                <div class="big_fire ani_shark2"><img src="<%=path %>/year/images/icon4.png"/></div>
            </div>
            <div class="bing">
                <div class="small_fire ani_shark1"><img src="<%=path %>/year/images/icon1.png"/></div>
                <div class="big_fire ani_shark2"><img src="<%=path %>/year/images/icon2.png"/></div>
            </div>
            <div class="keys">
                <div class="ani_big3">
                    <img src="<%=path %>/year/images/button.png">
                </div>
            </div>

        </div>
    </div>
    <div class="cloud">
        <div>
            <img src="<%=path %>/year/images/cloud.png">
        </div>
    </div>
</div>
<div class="backthree page page3" style="display: none">
    <div class="main">

        <div class="interact">
            <img src="<%=path %>/year/images/tip1.png">
            <img style="display: none;" src="<%=path %>/year/images/tip2.png">
            <img style="display: none;" src="<%=path %>/year/images/tip3.png">
        </div>

        <div class="bgfront"><img src="<%=path %>/year/images/page3bg_front.png" alt=""></div>
    </div>
</div>
<div class="page page4" style="display: none">
    <canvas id="canv" class="eve"></canvas>
    <div class="gamebor"><img src="<%=path %>/year/images/page4bg1.png" alt="">
        <div class="bgfront"><img src="<%=path %>/year/images/page3bg_front.png" alt="">
        </div>
    </div>
    <div class="videobg">
        <video autoplay="autoplay" id="videoBox"
        x5-video-player-type="h5" x5-video-player-fullscreen="false"
        ></video>
    </div>
    <div class="threecont" id="touchArea">
    </div>
    <div class="mark"><img class="markout" src="<%=path %>/year/img/game_mao.png" alt="">
        <div class="markinner">
            <img src="<%=path %>/year/img/game_mao1.png" alt="">
        </div>
    </div>
      <p class="marktips">正在解签...</p>
</div>
<div class="backfive page page5" style="display: none">
<div class="shartip"><img src="<%=path %>/year/images/shartip.png" alt="">
<div class="mark"></div>

</div>
    <div class="main">
        <div class="frame">
        <img src="<%=path %>/year/images/game_kuang.png"></div>
        <img class="finalresult2" alt="">
        <img class="finalresult" alt="">
        <div class="hidepan">
        <div class="showresult">
            <div class="result">
            <img src="<%=path %>/year/images/end_result_t.png"/></div>
                       <div class="result_desc"><p>
            背负着队伍成败的命脉<br>
<i>宜出肉装抗伤害<br>
忌打输出易崩盘<br></i>
千错万错，出肉一定对<br>
<span>坦克</span>打团照脸撸就行
            </p>
            </div>
            <div class="result_desc"><p>
              <i>请叫我</i><span>中路杀神</span><br>
宜刷兵，刷野，支援<br>
忌盲目游走，<i>乱带节奏</i><br>
刷兵攒经济，团战火力全开<br>
<i>新的一年，我是大刷子</i>

            </p>
            </div>
            <div class="result_desc"><p>
             峡谷<span>荒野求生</span><br>
<i>宜胆大心细，意识至上</i><br>
忌到对面浪调戏红蓝<text class="sonti">CP</text><br>
<i>节奏大师带飞队友</i><br>
战局胜负手，打野是关键


            </p>
            </div>
            <div class="result_desc"><p>
              <span>团队输出</span>的灵魂核心<br>
<i>宜稳定发育，受队友照顾<br>
忌人群中尬舞，全局梦游</i><br>
拯救世界的重任全靠你<br>
不是所有的<span><text class="sonti">AD</text>&nbsp;</span>都能叫<span> <text class="sonti">C</text></span>


            </p>
            </div>
            <div class="result_desc"><p>
             <i>峡谷中的保姆</i><br>
宜照顾射手健康成长<br>
<i>忌出输出独自偷欢</i><br>
忍辱负重，<span>辅助</span>当先<br>
小小身材<span>大力量</span>


            </p>
            </div>
            <div class="result_desc"><p>
             游走刀锋边缘<br>
宜<span>秀</span>，装完就跑<br>
忌<text class="sonti">1V5</text>，出师未捷身先死<br>
<span>一套爆发</span>杀遍全场<br>
<i>刺客的尊严由我守护</i>

            </p>
            </div>
            <div class="result_desc"><p>
             一张<i>嘴</i>，成就一个<i>王者</i><br>
<i>宜温柔指挥作战<br>
忌上头怒喷队友<br></i>
有实力懂指挥才是<span>最强</span><br>
千万别当<span>嘴强</span>哟


            </p>
            </div>
            <div class="result_desc"><p>
           <span>躺</span>着都能<span>赢</span><br>
宜找个粗壮的<span>大腿</span>抱<br>
忌送人头，抢资源<br>
<i>王者里的不解之谜<br>
躺着都能上王者</i>

            </p>
            </div>

            <div class="qrcode"><img src="<%=path %>/year/images/qrcode.png" alt=""></div>
            <div class="savetip">长按保存</div>
             <div class="savetip2" >长按扫描二维码，查看新年运势</div>
        </div>
        </div>
        <div class="end_button"><img src="<%=path %>/year/images/end_button_1.png"/></div>
        <div class="end_enjoybutton"><img src="<%=path %>/year/images/end_button.png"/></div>
    </div>
</div>
</script>
<script>
    var _mtac = {};
    (function () {
        var mta = document.createElement("script");
        mta.src = "https://pingjs.qq.com/h5/stats.js?v2.0.2";
        mta.setAttribute("name", "MTAH5");
        mta.setAttribute("sid", "500390674");
        mta.setAttribute("cid", "500390676");
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(mta, s);
    })();
</script>

<script src="<%=path %>/year/js/index.js"></script>
<script src="<%=path %>/year/js/myJs.js"></script>
</body>
</html>
/**
 * Created by Administrator on 2016/5/10.
 */
window.getImgHost = function () {
    if (window._imgBtopsHost) {
        return window._imgBtopsHost;
    } else {
        return "http://" + location.host;
    }
}
function getUrlParam(identifier) {		//获取url上面的id  id为字符串
    var reg = new RegExp("(^|&)" + identifier + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return decodeURIComponent(r[2]);
    return null; //返回参数值
}

function ifAndroid() {
    var u = navigator.userAgent;
	
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    return isAndroid;
}
function getCookie(cName, url) {     //获取openId
    if (document.cookie.length > 0) {
        cStart = document.cookie.indexOf(cName + "=");
        if (cStart != -1) {
            cStart = cStart + cName.length + 1;
            cEnd = document.cookie.indexOf(";", cStart);
            if (cEnd == -1) cEnd = document.cookie.length;
            return decodeURIComponent(document.cookie.substring(cStart, cEnd));
        } else {
            window.location.href = url;
        }
    } else {
        window.location.href = url;
    }
}
/*	getCookie使用方法
 var openId = getCookie('user');
 var userName = getCookie('userName');
 */

HTMLImageElement.prototype.myonload = function (callback) {		//判断图片是否加载完成
    if (this.complete) {
        callback()
    } else {
        this.onload = callback;
    }
};

function validationPhone(phoneNumber) {		//验证手机号码
    var reg = /^0?1[0-9]\d{9}$/;
    if (reg.test(phoneNumber)) {
        return true;
    } else {
        return false;
    }
}


/*
 var shareData = {
 describe:'',		//描述
 title:'',			//title
 link:'',			//link
 apiHost:'http://open.btops.cn/api/WechatCommon',			//apiHost
 img_url:'',			//分享图片
 callBack:function(){};	//ready后回调
 };
 */
function Share(shareData) {			//分享接口
    var JsUrl = {Url: window.location.href}
    var jsApiList = ['checkJsApi', 'onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo', 'onMenuShareQZone', 'hideMenuItems', 'showMenuItems', 'hideAllNonBaseMenuItem', 'showAllNonBaseMenuItem', 'translateVoice', 'startRecord', 'stopRecord', 'onRecordEnd', 'playVoice', 'pauseVoice', 'stopVoice', 'uploadVoice', 'downloadVoice', 'chooseImage', 'previewImage', 'uploadImage', 'downloadImage', 'getNetworkType', 'openLocation', 'getLocation', 'hideOptionMenu', 'showOptionMenu', 'closeWindow', 'scanQRCode', 'chooseWXPay', 'openProductSpecificView', 'addCard', 'chooseCard', 'openCard'];
    if (Share.play != true) {
        $.ajax({
            type: 'POST',
            url: shareData.apiHost,
            data: JsUrl,
            success: function (msg) {
                wx.config({
                    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                    appId: msg.Data.appid, // 必填，公众号的唯一标识
                    timestamp: msg.Data.timestamp, // 必填，生成签名的时间戳
                    nonceStr: msg.Data.noncestr, // 必填，生成签名的随机串
                    signature: msg.Data.signature,// 必填，签名，见附录1
                    jsApiList: jsApiList // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                });
                wxReady();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
        Share.play = true;
    } else {
        wxReady();
    }
    function wxReady() {
        wx.ready(function () {
            if (typeof(shareData.callBack) == 'function') {
                if (shareData.isCallBack != true) {		//回掉函数  只执行一次
                    shareData.callBack();
                    shareData.isCallBack = true;
                }
            }
            //转发朋友圈
            wx.onMenuShareTimeline({
                title: shareData.friendtitle,
                link: shareData.link,
                imgUrl: shareData.img_url, // 分享图标
                img_width: "300",
                img_height: "300",
                trigger: function () {
                },
                success: function () {
                    MtaH5.clickStat('toShare');
                    // _hmt.push(['_trackEvent', "share", "分享朋友圈"]);
                },
                cancel: function () {
                }
            });
            //分享给朋友
            wx.onMenuShareAppMessage({
                title: shareData.title, // 分享标题
                desc: shareData.describe, // 分享描述
                link: shareData.link, // 分享链接
                imgUrl: shareData.img_url, // 分享图标
                img_width: "300",
                img_height: "300",
                trigger: function () {
                },
                success: function () {
                    MtaH5.clickStat('toShareFriend');
                    //_hmt.push(['_trackEvent', "share", "分享朋友"]);
                },
                cancel: function () {
                }
            });
        });
    }
}

//requestAnimationFrame 兼容写法，不支持的浏览器时会自动换成Timeout
(function () {
    var lastTime = 0;
    var vendors = ['webkit', 'moz'];
    for (var x = 0; x < vendors.length && !window.requestAnimationFrame; ++x) {
        window.requestAnimationFrame = window[vendors[x] + 'RequestAnimationFrame'];
        window.cancelAnimationFrame =
            window[vendors[x] + 'CancelAnimationFrame'] || window[vendors[x] + 'CancelRequestAnimationFrame'];
    }

    if (!window.requestAnimationFrame)
        window.requestAnimationFrame = function (callback, element) {
            var currTime = new Date().getTime();
            var timeToCall = Math.max(0, 16 - (currTime - lastTime));
            var id = window.setTimeout(function () {
                    callback(currTime + timeToCall);
                },
                timeToCall);
            lastTime = currTime + timeToCall;
            return id;
        };

    if (!window.cancelAnimationFrame)
        window.cancelAnimationFrame = function (id) {
            clearTimeout(id);
        };
}());


//添加在jquery库的下面
HTMLElement.prototype.fastClick = function (callBack) {		//原生快速点击事件
    this.addEventListener('touchstart', function () {
        this.isMove = false;
    });
    this.addEventListener('touchmove', function () {
        this.isMove = true;
    });
    this.addEventListener('touchend', function () {
        if (!this.isMove) {
            callBack.apply(this);
        }
    });
};
$.fn.fastClick = function (callBack) {						//拓展jq快速点击
    for (var i = 0; i < this.length; i++) {
        this[i].fastClick(callBack);
    }
};
var isLoadingE = false;

//强制横屏 必须设置在 class= viewport 下 base.css 必须引入
layScreen();
function layScreen() {
    var initHeight = window.innerHeight;
    var initWidth = window.innerWidth;
    var isLay = initWidth > initHeight ? true : false;
    $(window).resize(function () {
        initHeight = window.innerHeight;
        initWidth = window.innerWidth;
        isLay = initWidth > initHeight ? true : false;
        laydown();
    });
    laydown();
    function laydown() {
        if (!isLay) {
            $('#popE').show();
            $(".viewport").css({
                width: initHeight,
                height: initWidth,
                transform: "matrix(0, 1, -1, 0, " + initWidth + ", 0)"
            });
        } else {
            $('#popE').hide();
            $(".viewport").css({
                width: initWidth,
                height: initHeight,
                transform: "matrix(1, 0, 0,1,0, 0)"
            });
        }
    }
}
(function($) {
    $.fn.typewriter = function() {
        this.each(function() {
            var $ele = $(this), str = $ele.html(), progress = 0;
            $ele.html('');
            var timer = setInterval(function() {
                var current = str.substr(progress, 1);
                if (current == '<') {
                    progress = str.indexOf('>', progress) + 1;
                } else {
                    progress++;
                }
                $ele.html(str.substring(0, progress) + (progress & 1 ? '' : ''));
                if (progress >= str.length) {
                    clearInterval(timer);
                }
            },80); //250为字体出现的速度
        });
        return this;
    };
})(jQuery);
//洗牌算法
function shuffle(arr){
    var length = arr.length,
        temp,
        random;
    while(0 != length){
        random = Math.floor(Math.random() * length)
        length--;
        // swap
        temp = arr[length];
        arr[length] = arr[random];
        arr[random] = temp;
    }
    return arr;
}

function IfLocalhost(){
if(location.host.indexOf("localhost")!=-1||location.host.indexOf("192.168.1.")!=-1){
    return true;
}
    return false;
}
function IfDebug(){
    return getUrlParam("isdebug")?true:false;
}


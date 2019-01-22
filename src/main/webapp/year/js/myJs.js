var win_w = document.documentElement.clientWidth;
var win_h = document.documentElement.clientHeight;

var urls = [
    '../img/bg.right.jpg',
    '../img/bg.left.jpg',
    '../img/bg.top.jpg',
    '../img/bg.bottom.jpg',
    '../img/bg.front.jpg',
    '../img/bg.back.jpg'
];
var texture = new THREE.CubeTextureLoader()
    .load(urls, function () {
            // output the text to the console
        setTimeout(function () {
            isLoaded=true;
            $('#loadNumber').text('99%');

        }, 800)
            setTimeout(function () {
                if(IfDebug()){
                    showPage(2);
                }else{
                    showPage(2);
                }
            }, 1000)
        }
    );
var isPlay = true;
var firecrackers = $("#music")[0];
contrMusic();
function contrMusic() {
    document.addEventListener('DOMContentLoaded', function () {
        function audioAutoPlay() {
            if (isPlay)firecrackers.play();
            document.addEventListener("WeixinJSBridgeReady", function () {
                if (isPlay)firecrackers.play();
            }, false);
        }

        audioAutoPlay();
    });
}

var isLoaded=false;
function loadIngAnimation() { //加载百分比
    var num = 0;
    var aImg = document.getElementsByTagName('img');
    for (var i = 0; i < aImg.length; i++) {
        if (aImg[i]) {
            aImg[i].myonload(function () {
                if(isLoaded){
                    return;
                }
                num++;
                var str = Math.floor(num / aImg.length * 100);
                $('#loadNumber').text(Math.min(str, 99) + '%');

            });
        }
    }
}

function showPage(id) { //显示page页面
   if(id===2&&$('.page' + id).length===0){
   	setTimeout(function(){
   		showPage(2)
   	},1000);
   	return;
   }
   $('.page' + id).show().siblings('.page').hide();
    
    if (id == 1) {

    } else if (id == 2) {
        Page_B_Ani();
    } else if (id == 3) {
        Page_C_Ani();
    } else if (id == 4) {

        var game = new PanoramaGame(function (item) {
            setTimeout(function(){
                $("#videoBox").hide();
                var video = $("#videoBox")[0];
                video.pause();
                video.src="x";

            },10);
            showPage(5)
        }, texture);
    }
    else if (id == 5) {
        var len = $(".showresult .result_desc").length;
        var item = Math.floor(Math.random() * len);
        if (item <= 0 || item >= len) {
            item = 1;
        }
        $(".showresult .result_desc").eq(item).show();
        //$(".showresult").css("-webkit-transform","scale(2)");
        setTimeout(function () {

            $(".finalresult")[0].addEventListener("touchstart",function(){
                //$(".finalresult").hide();



            })
            html2canvas($(".showresult"), {
                useCORS: true,

                width: win_w * 4, height: win_h * 4,
                onrendered: function (cvs) {
                    var url = cvs.toDataURL();
                    $(".finalresult").attr("src", url);
                    $(".finalresult").show();
                    $(".savetip").hide();
                    $(".savetip2").show();
                    $(".showresult").css("background","#fff");
                    $(".showresult .result img").attr("src","images/end_result2.png")
                    html2canvas($(".showresult"), {
                        useCORS: true,

                        width: win_w * 4, height: win_h * 4,
                        onrendered: function (cvs) {
                            var url = cvs.toDataURL("image/png");
                            $(".finalresult2").attr("src", url).show();
                            $(".showresult").hide();
                        }
                    });
                }
            });


        }, 100)
    }
}

function Page_C_Ani() {
    var i = 1;
    var tmpint = setInterval(function () {
        $(".interact img").hide();
        $(".interact img").eq(i).show();
        i++;
        if (i > 3) {
            clearInterval(tmpint);
            //CreateGame
            showPage(4)
        }
    }, 2000)

}
function Page_B_Ani() {
    var text = $(".desc .temple").text();
    var p = $(".desc .target");
    p.empty();
    var ti = 0;
    var Ina = setInterval(function () {
        var item = text[ti];
        if (item) {
            item = item.replace("&", "<br>");
            p.append(item);
        } else {
            clearInterval(Ina)
        }
        ti++;
    }, 100)
}

//loadIngAnimation();
var isVideoSucceed = false;
showPage(1);
$("body").append($("#htmlbody").html());
loadIngAnimation();
$(".eve").height(window.innerHeight);
$(".eve").width(window.innerWidth);
$(".page").height(win_h);
$('.keys').click(function () {
    showPage(3);
});
if (ifAndroid()) {
	
    CreateVideoBackground(function (isSuccss) {
        isVideoSucceed = isSuccss;
    });
}
$(".shartip").click(function () {
    $(".shartip").hide();
});
$(".end_enjoybutton").click(function () {
    $(".shartip").show();
});
$(".end_button").click(function () {
    location.href = "/SmallProgram_Front/goodsign/index.html?" + Math.random()
})
$(".js_musicctr").click(function () {
    $(".js_musicctr img").hide();
    isPlay = isPlay ? false : true;
    if (isPlay) {
        $("#music")[0].play();
        $(".js_musicctr img").eq(0).show();
    } else {
        $("#music")[0].pause();
        $(".js_musicctr img").eq(1).show();
    }

});
var imgurl = "https://sslapi.btops.com.cn/SmallProgram_Front/goodsign/images/shar.jpg";
var shareData = {
    describe: '抽了这个签，新年开黑不翻车......',		//描述
    friendtitle: '王者荣耀 | 逆天改命，就看这签！',
    title: '王者荣耀 | 逆天改命，就看这签！',			//title
    link: location.href,			//link
    apiHost: '/api/WechatCommon',			//apiHost
    img_url: imgurl,			//分享图片
    callBack: function () {
    }	//ready后回调
};

    $(".page2")[0]
        .addEventListener("touchmove", function (e) {
            e.preventDefault();
        })
Share(shareData);

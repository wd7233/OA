/**
 * Created by rhythm on 2017/1/17.
 */

function PanoramaGame(showNotefn, texture) {
    var _self = this;
    var scene = new THREE.Scene();
    var camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
    var renderer = new THREE.WebGLRenderer({alpha: true});
    // 纹理图像的url

    _self.markItem = null;
    var itemGroup = [];
    var itemPoi = [];
    renderer.setSize(window.innerWidth, window.innerHeight);

    var light = new THREE.AmbientLight(0xffffff); //模拟漫反射光源
    light.position.set(600, 1000, 800); //使用Ambient Light时可以忽略方向和角度，只考虑光源的位置
    scene.add(light);
    // 打包成我们需要的对象

    texture.format = THREE.RGBFormat;

    var spriteMap = new THREE.TextureLoader().load("../img/item.png");
    var spriteMaterial = new THREE.SpriteMaterial({map: spriteMap, color: 0xffffff});


    function CreateItem(scene, x, y, z) {
        var sprite = new THREE.Sprite(spriteMaterial);
        sprite.position.x = x;
        sprite.position.y = y;
        sprite.position.z = z;

        console.log("z:" + z)
        itemGroup.push(sprite);
        scene.add(sprite);
    }

var r =4;
    for (var i = -r; i < r; i += 3) {
        for (var j = -r; j < r; j += 3) {
            itemPoi.push({x: i, y: j});
        }
    }
    itemPoi = shuffle(itemPoi);
    for (var k = 0; k < 5; k++) {
        var item = itemPoi.pop();
        CreateItem(scene, item.x, item.y, getZ(item.x,item.y,r));
        CreateItem(scene, item.x, item.y, -getZ(item.x,item.y,r));
    }

    function getZ(x, y, r) {
        return Math.sqrt(Math.abs(Math.pow(x, 2) + Math.pow(y, 2) - Math.pow(r, 2)));
    }

    scene.background = texture;
    camera.position.z = 0;
    camera.position.x = 0;
    camera.position.y = 0;
    $(".threecont").append(renderer.domElement);
    var Devices = new THREE.DeviceOrientationControls(camera);
    Devices.connect();
    _self.timecont= 0;
    _self.render = function () {
        if (isVideoSucceed) {
            scene.background = null;
        }
        Devices.update();
        _self.markItem = null;
        for (var i = 0; i < itemGroup.length; i++) {
            var item = itemGroup[i];
            var y = camera.getWorldDirection();
            var b1 = item.getWorldPosition();
            dt1 = y.angleTo(b1);
            var angle = 180 / Math.PI * dt1;
            if (angle < 10 && item.visible) {
                _self.markItem = item;
            }
        }
        if (_self.markItem) {
            if(!_self.timecont){
                _self.timecont = setTimeout(function(){
                	_self.timecont=0;
                longPress()
                },2000)
            }else{
            }
            $(".markinner").show();
            $(".marktips").show();
        } else {
            if(_self.timecont){
               clearTimeout(_self.timecont);
               _self.timecont=0;
            }else{
            }
            $(".markinner").hide();
            $(".marktips").hide();
        }
        requestAnimationFrame(_self.render);
        renderer.render(scene, camera);
    }

    _self.touchEvent = function (camera) {
        var stX = 0;
        var stY = 0;
        var lastRotation;
        $(".threecont")[0].addEventListener("touchstart", function (e) {
            var t = e.touches[0];
            stX = t.clientX;
            stY = t.clientY;
            lastRotation = camera.rotation;
        })
        $(".threecont")[0].addEventListener("touchmove", function (e) {
            var t = e.touches[0];
            var moveX = t.clientX;
            var moveY = t.clientY;
            if (stX && stY) {
                camera.rotation.y = lastRotation.y + (stX - moveX) * 0.01;
                camera.rotation.x = lastRotation.x + (stY - moveY) * 0.01;
            }
            stX = t.clientX;
            stY = t.clientY;
        })
        $(".threecont")[0].addEventListener("touchend", function (e) {
            stX = 0;
            stY = 0;
            lastRotation = null;
        })


        document.addEventListener("touchmove", function (e) {
            e.preventDefault();
        })
    }
    function longPress() {
        if (_self.markItem) {
            _self.markItem.visible = false;
            scene.remove(_self.markItem);
        }
        showNotefn(_self.markItem);
    }

    _self.bindLongPress = function ($touchArea, callback) {
        var timeOutEvent = 0;

        $(function () {
            $touchArea.on({
                touchstart: function (e) {
                    $(".markinner img").addClass("speedAni");
                    timeOutEvent = setTimeout(function () {
                            timeOutEvent = 0;
                            callback();
                        },
                        1000);
                    e.preventDefault();
                },
                touchmove: function () {
                    //$(".markinner img").removeClass("speedAni");
                    //clearTimeout(timeOutEvent);
                    //timeOutEvent = 0;
                },
                touchend: function () {
                    clearTimeout(timeOutEvent);
                    if (timeOutEvent != 0) {
                        console.log("点击");
                    }
                    return false;
                }
            })
        });
    }

   // _self.bindLongPress($(".page4"), longPress);

    document.getElementById("canv")
        .addEventListener("touchstart", function (e) {
            e.preventDefault();
        })
     _self.touchEvent(camera);
    _self.render();
}


function CreateVideoBackground(callback) {
    $(".page3").height(window.innerHeight + 200);
    $(".page3").width(window.innerWidth);
    $(".videobg").height(window.innerHeight + 200);
    $(".videobg").width(window.innerWidth);
    $("video").show();
    var video = document.querySelector('video');


    var audio, audioType;
    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;
    window.URL = window.URL || window.webkitURL || window.mozURL || window.msURL;
    var exArray = []; //存储设备源ID
    if (navigator.getUserMedia) {
        // MediaStreamTrack.getSources(function (sourceInfos) {
            // for (var i = 0; i != sourceInfos.length; ++i) {
                // var sourceInfo = sourceInfos[i];
                // //这里会遍历audio,video，所以要加以区分
                // if (sourceInfo.kind === 'video') {
                    // exArray.push(sourceInfo.id);
                // }
            // }

        // });
    }
    $('.attr').html(exArray);
    $('.keys').click(function () {
        getMedia();
    });


    function getMedia() {

        $('.attr').html("getMedia()");
        navigator.getUserMedia({ audio: true, video: { facingMode:  "environment"  } }, successFunc, errorFunc);
    }

    function successFunc(stream) {
        $('.attr').html("successFunc()");
        video.src = window.URL && window.URL.createObjectURL(stream) || stream;
        video.onloadedmetadata = function (e) {
            // Do something with the video here.
            callback(true);
        };

    }

    function errorFunc() {
        callback(false);

    }
}

$(function(){
    initMap();

});
//地图界面高度设置



//加载地图
function initMap(){


// 百度地图API功能
    var map = new BMap.Map("map_div");    // 创建Map实例
    map.centerAndZoom(new BMap.Point(113.23	,23.16), 11);  // 初始化地图,设置中心点坐标和地图级别
    //添加地图类型控件
    var size1 = new BMap.Size(10, 50);
    map.addControl(new BMap.MapTypeControl({
        offset: size1,
        mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP,

        ]}));

    $.ajax({
        url: "/car/mysql/findLastLocation",//请求地址 top5bykilometer
        type: "POST",
        dataType: "json",
        scriptCharset: "utf-8",
        success: function (data) {//data是默认的，接收前台返回的数据
            for (var i = 0; i < data.length; i++) {
                var point = new BMap.Point(data[i].latitude, data[i].longitude);
                var marker = new BMap.Marker(point);
                map.addOverlay(marker);  //将标注点添加到地图上
                //添加监听事件
                (function () {
                    var thePoint = data[i];
                    marker.addEventListener("click",
                        //显示信息的方法
                        function () {
                            showInfo(this, thePoint);
                        });
                })();
            }
        }
    });

    // 随机向地图添加25个标注
    // var bounds = map.getBounds();
    // var sw = bounds.getSouthWest();
    // var ne = bounds.getNorthEast();
    // var lngSpan = Math.abs(sw.lng - ne.lng);
    // var latSpan = Math.abs(ne.lat - sw.lat);
    // for (var i = 0; i < 25; i ++) {
    //     var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
    //     addMarker(point);
    // };

    map.setCurrentCity("广州");          // 设置地图显示的城市 此项是必须设置的
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    //设备地图颜色
    var mapStyle={
        style:"midnight"
    };
    map.setMapStyle(mapStyle);


//加载城市控件
    var size = new BMap.Size(10, 50);
    map.addControl(new BMap.CityListControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        offset: size,


    }));
}

function showInfo(thisMarker,point) {
    //获取点的信息
    var sContent =
        '<ul class="map_marker_ul">'
        +'<li class="map_marker_ul_li">'
        +'<span class="map_marker_ul_span">车牌号：</span>' + point.carid + '</li>'
        +'<li class="map_marker_ul_li">'
        +'<span class="map_marker_ul_span">车类型：</span>' + point.category + '</li>'
        // +'<li style="line-height: 26px;font-size: 15px;"><span style="width: 50px;display: inline-block;">查看：</span><a href="'+point.url+'">详情</a></li>'
        +'<li class="map_marker_ul_li">'
        +'<span class="map_marker_ul_span">位置：</span>' + point.location + '</li>'
        +'</ul>';
    var infoWindow = new BMap.InfoWindow(sContent); //创建信息窗口对象
    thisMarker.openInfoWindow(infoWindow); //图片加载完后重绘infoWindow
}


/**
 * Created by 30947 on 2018/7/20.
 */
$(function(){
    getHt();
    initMap();
    mapActive();
    char31();
    page();
    mapRestList();
    rightChange();
    char32();
    onc();
    move();

});
//获取div的高度
function getHt(){
   var all_height=$(window).height();
   var div_height=all_height-84;
    $("#car_control").css("height",div_height+"px");


}
//加载地图
function initMap(){
// 百度地图API功能
    var map = new BMap.Map("map_box");    // 创建Map实例
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

    // 编写自定义函数,创建标注
    // function addMarker(point){
    //     var marker = new BMap.Marker(point);
    //     map.addOverlay(marker);
    // }
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
    ////设备地图颜色
    //var mapStyle={
    //    style:"midnight"
    //};
    //map.setMapStyle(mapStyle);


//加载城市控件
    var size = new BMap.Size(10, 50);
    map.addControl(new BMap.CityListControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        offset: size
    }));
}
//工具条点击效果
function mapActive(){
    $(".map_top>ul>li").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
        $(this).find("a").addClass("active");
        $(this).find("a").parents("li").siblings().find("a").removeClass("active");
    })
}
//统计分析图
function char31(){
    var myChart = echarts.init($("#char31")[0]);

    var option = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'right',
            textStyle : {
                color : '#ffffff',

            },
            // data:['客运车','危险品车','网约车','学生校车']
            data:[]
        },

        calculable : false,
        series : [
            {
                name:'车类型',
                type:'pie',
                radius : ['40%', '70%'],
                itemStyle : {
                    normal : {
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    },
                    emphasis : {
                        label : {
                            show : true,
                            position : 'center',
                            textStyle : {
                                fontSize : '20',
                                fontWeight : 'bold'
                            }
                        }
                    }
                },
                data:[
                    // {value:310, name:'客运车'},
                    // {value:310, name:'危险品车'},
                    // {value:234, name:'网约车'},
                    // {value:135, name:'学生校车'}
                ]
            }
        ]
    };

    $.ajax({
        url: "/car/mysql/findCategoryNum",//请求地址
        type: "POST",
        dataType: "json",
        scriptCharset:"utf-8",
        success: function(data) {//data是默认的，接收前台返回的数据
            console.log(data);
            for(var i=0; i<data.length; i++){
                option.legend.data.push(data[i].category);
                option.series[0].data.push({value:data[i].statu, name:data[i].category});
            }
            myChart.setOption(option);
        }
    });
    window.addEventListener('resize', function () {myChart.resize();})
}
function page(){
    $("#page").Page({
        totalPages: 9,//分页总数
        liNums: 1,//分页的数字按钮数(建议取奇数)
        activeClass: 'activP', //active 类样式定义
        callBack : function(page){
            //console.log(page)
        }
    });
}
//专题图点击
function mapRestList(){
    $(".map_work>ul>li").click(function(){
        $(".map_work>ul").hide();
        $(".map_resList").show();
    })
}
//返回
function back(){
    $(".map_work>ul").show();
    $(".map_resList").hide();
}
//右侧功能界面切换
function rightChange(){
    $(".map_right_top>ul>li").click(function(){
        var ins=$(this).index();
        $(this).addClass("li_active").siblings().removeClass("li_active");
        $(".map_con .map_con_div").eq(ins).show().siblings().hide();
    })
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

function char32() {
    var str= "";
    $.ajax({
        url: "/car/mysql/top5ByKilometer",//请求地址 top5bykilometer
        type: "POST",
        dataType: "json",
        scriptCharset:"utf-8",
        success: function(data) {//data是默认的，接收前台返回的数据
            for(var j=0;j<data.length;j++){
                str=str+"<tr><td>"+(j+1)+"</td><td>"+data[j].carid+"</td><td>"+data[j].kilometer+"</td></tr>";
            }
            document.getElementById("top5bykilometer2").innerHTML=
                str

        }
    });
}
function onc() {
    $("#queryCaridBtn").click(function () {
        var carid = document.getElementById("inputCarid").value;
        $.ajax({
            url: "/car/hbase/getResultScannerByRowFilter?carid="+carid,//请求地址 top5bykilometer
            type: "POST",
            dataType: "json",
            scriptCharset: "utf-8",
            success: function (data) {//data是默认的，接收前台返回的数据

                var str = "<ul>";
                for(var i=0;i<data.length;i++){
                    console.log(data[i].rowkey+data[i].location+data[i].speed);
                    var timeindex = data[0].rowkey.indexOf(",2018");
                    //
                    str = str+"<li><div style='color: #ffffff;'>"+data[i].rowkey.substr(3,7)+"  "+data[i].rowkey.substr(timeindex,15).replace(",","")+"  "+data[i].location+data[i].speed+"</div></li></br>"
                }
                str = str+"</ul>";

                document.getElementById("queryCaidList").innerHTML = str;
            }
        });
    });


}

function move() {
    $("#queryCaridMove").click(function carmove() {
        var lts = [];
        var carid = document.getElementById("inputCarid").value;
        $.ajax({
            url: "/car/hbase/getResultScannerByRowFilter?carid=" + carid,//请求地址 top5bykilometer
            type: "POST",
            dataType: "json",
            scriptCharset: "utf-8",
            success: function (data) {//data是默认的，接收前台返回的数据
                for (var i = 0; i < data.length; i++) {
                    lts.push([data[i].rowkey.split(",")[3], data[i].rowkey.split(",")[2]]);

                }
                console.log("lts:------>" + lts);
                // var abc = $(data);
                var chartData = [];
                $.each(lts, function (item, value) {
                    chartData.push(new BMap.Point(value[0], value[1]));
                });
                console.log("charData:------>"+chartData);
                for (var i = 0; i < chartData.length - 1; i++) {
                    var startPoint = chartData[i];
                    var endPoint = chartData[i + 1];
                    showPath(startPoint, endPoint, i == 0, i == chartData.length - 2);
                }
            }
        });

    })
}

// 两个坐标点连线
function showPath(startPoint, EndPoint,displayStartIcon,displayEndIcon){
    // 百度地图API功能
    var map = new BMap.Map("map_box");    // 创建Map实例
    map.centerAndZoom(new BMap.Point(113.23	,23.16), 11);  // 初始化地图,设置中心点坐标和地图级别
    //添加地图类型控件
    var size1 = new BMap.Size(10, 50);
    map.addControl(new BMap.MapTypeControl({
        offset: size1,
        mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP,

        ]}));

    map.setCurrentCity("广州");          // 设置地图显示的城市 此项是必须设置的
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放


//加载城市控件
    var size = new BMap.Size(10, 50);
    map.addControl(new BMap.CityListControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        offset: size
    }));

    var walking = null;
    if(displayStartIcon && !displayEndIcon){ // 第一个起点只展示起点图标
        walking = new BMap.DrivingRoute(map, { renderOptions: { map: map, autoViewport: true },onMarkersSet:function(routes) {map.removeOverlay(routes[1].marker);}});
    }else if(!displayStartIcon && !displayEndIcon){//中间的起点终点不展示起点、终点图标
        walking = new BMap.DrivingRoute(map, { renderOptions: { map: map, autoViewport: true },onMarkersSet:function(routes) {map.removeOverlay(routes[0].marker);map.removeOverlay(routes[1].marker);}});
    }else{// 最后一个终点只展示终点图标
        walking = new BMap.DrivingRoute(map, { renderOptions: { map: map, autoViewport: true },onMarkersSet:function(routes) {map.removeOverlay(routes[0].marker);}});
    }

    walking.search(startPoint, EndPoint);
}

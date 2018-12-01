<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>地图界面</title>
    <link rel="stylesheet" href="/resources/js/bstable/css/bootstrap.min.css">
    <link href="/resources/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="/resources/js/jQueryPage/src/jquery.page.css">
    <link rel="stylesheet" href="/resources/css/base.css">
</head>
<body>
<!--顶部-->
<header class="header left">
    <div class="left nav">
        <ul>
            <li ><i class="nav_1"></i><a href="/car/jsp/index">数据概览</a> </li>
            <li><i class="nav_2"></i><a href="/car/jsp/carContrl">车辆监控</a> </li>
            <li class="nav_active"><i class="nav_3"></i><a href="/car/jsp/map">地图界面</a> </li>



        </ul>
    </div>
    <div class="header_center left">
        <h2><strong>xx区智慧交通服务平台</strong></h2>

    </div>
    <div class="right nav text_right">
        <ul> <li><i class="nav_7"></i><a href="/car/jsp/static">查询统计</a> </li>
            <li><i class="nav_8"></i><a href="/car/jsp/message">信息录入</a> </li>
            <%--<li><i class="nav_4"></i><a href="/car/jsp/table1">表格界面</a> </li>--%>
        </ul>
    </div>
    <!--<nav class="left nav">-->
    <!--<ul>-->
    <!--<li class="nav_active"><i class="nav_1"></i><a href="index.jsp">数据概览</a> </li>-->
    <!--<li><i class="nav_2"></i><a href="carContrl.jsp">车辆监控</a> </li>-->
    <!--<li><i class="nav_3"></i><a href="map.jsp">地图界面</a> </li>-->
    <!--<li><i class="nav_4"></i><a href="javascript:void(0)">表格界面</a><ul class="li_ul">-->
    <!--<li><a href="table1.jsp">表格一</a> </li>-->
    <!--<li><a href="table2.jsp">表格二</a> </li>-->

    <!--</ul> </li>-->
    <!--&lt;!&ndash;<li><i class="nav_5"></i><a href="#">车载视频</a> </li>&ndash;&gt;-->
    <!--&lt;!&ndash;<li><i class="nav_6"></i><a href="#">视频监控</a> </li>&ndash;&gt;-->
    <!--<li><i class="nav_7"></i><a href="static.jsp">查询统计</a> </li>-->
    <!--<li><i class="nav_8"></i><a href="message.jsp">信息录入</a> </li>-->
    <!--</ul>-->
    <!--</nav>-->
</header>
<!--内容部分-->
<div class="map_con left" id="car_control">
    <!--左侧地图-->
  <div class="left map_left"  >
      <div  class="map_box" id="map_box"></div>
      <%--<div class="map_top">--%>
          <%--<ul>--%>
              <%--<li class="active"><i class="glyphicon glyphicon-pencil"></i><a href="javascript:void (0)" class="active">图层选择</a> </li>--%>
              <%--<li><i class="glyphicon glyphicon-zoom-in"></i><a href="javascript:void (0)">缩小</a> </li>--%>
              <%--<li><i class="glyphicon glyphicon-zoom-out"></i><a href="javascript:void (0)">放大</a> </li>--%>
              <%--<li><i class="glyphicon glyphicon-screenshot"></i><a href="javascript:void (0)">对比</a> </li>--%>
              <%--<li><i class="glyphicon glyphicon-search"></i><a href="javascript:void (0)">搜索</a> </li>--%>
              <%--<li><i class="glyphicon glyphicon-wrench"></i><a href="javascript:void (0)">工具</a> </li>--%>
              <%--<li><i class="glyphicon glyphicon-fullscreen"></i><a href="javascript:void (0)">全屏</a> </li>--%>
          <%--</ul>--%>
      <%--</div>--%>
      <%--<div class="map_select">--%>
          <%--<p><input type="checkbox"><span>人员</span></p>--%>
          <%--<p><input type="checkbox"><span>车辆</span></p>--%>
          <%--<p><input type="checkbox"><span>房屋</span></p>--%>
          <%--<p><input type="checkbox"><span>摄像头</span></p>--%>
      <%--</div>--%>

  </div>

    <!--右侧功能栏-->
    <div class="right map_right ">
        <div class="map_right_top">
            <ul><li class="li_active">预警信息</li>
                <li>分类搜索</li>
                <li>专题信息</li></ul>
        </div>
        <div class="map_con">
            <div class="map_con_div" style="display: block" >
                <div class="map_chart">

                    <p id="char31" class="p_chart"></p>
                </div>
                <div class="div_any_child">
                    <!--<div class="div_any_title"><img src="img/title_4.png">行驶时长排名前5位 </div>-->
                    <div class="table_p">
                        <table>
                            <thead><tr>
                                <th>排名</th>
                                <th>车牌号</th>
                                <th>里程数</th>
                            </tr>
                            </thead>
                            <tbody id="top5bykilometer2">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="map_con_div" >
                <p>
                    <input id="inputCarid" type="text" placeholder="输入车牌号/粤A1E90D" class="carNo_input"><input id="queryCaridBtn" type="button" class="find_but" /><input id="queryCaridMove" type="button" class="find_but"  style=" background-image: url(/resources/img/find01.png); "/>
                </p>
                <div class="map_work" id="queryCaidList">
                    <%--<ul>--%>
                        <%--<li>--%>
                            <%--<div class="img_div img_div01"></div>--%>
                            <%--<div class="img_div_text">便民服务</div>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<div class="img_div img_div02"></div>--%>
                            <%--<div class="img_div_text">便民服务</div>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<div class="img_div img_div01"></div>--%>
                            <%--<div class="img_div_text">便民服务</div>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<div class="img_div img_div01"></div>--%>
                            <%--<div class="img_div_text">便民服务</div>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<div class="img_div img_div01"></div>--%>
                            <%--<div class="img_div_text">便民服务</div>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<div class="img_div img_div01"></div>--%>
                            <%--<div class="img_div_text">便民服务</div>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<div class="img_div img_div01"></div>--%>
                            <%--<div class="img_div_text">便民服务</div>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<div class="img_div img_div01"></div>--%>
                            <%--<div class="img_div_text">便民服务</div>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<div class="img_div img_div01"></div>--%>
                            <%--<div class="img_div_text">便民服务</div>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                    <div class="map_resList">
                        <p class="set_list"><i class="list_i"></i> 查询结果：<span class="right"><i class="glyphicon glyphicon-backward back_i"></i><a href="javascript:void(0)" onclick="back()">返回 </a> </span></p>
                        <%--<ul>--%>
                            <%--<li><p><strong>爱尚美术馆</strong><span class="right"><a href="javascript:void(0)" title="视频"><img src="/img/nav_5.png"> </a><a href="javascript:void(0)" title="定位"><img src="img/find01.png"> </a> </span></p>--%>
                            <%--<p>四川省成都市高新区北美一路31号</p></li>--%>
                            <%--<li><p><strong>爱尚美术馆</strong><span class="right"><a href="javascript:void(0)" title="视频"><img src="img/nav_5.png"> </a><a href="javascript:void(0)" title="定位"><img src="img/find01.png"> </a> </span></p>--%>
                                <%--<p>四川省成都市高新区北美一路31号</p></li>--%>
                            <%--<li><p><strong>爱尚美术馆</strong><span class="right"><a href="javascript:void(0)" title="视频"><img src="img/nav_5.png"> </a><a href="javascript:void(0)" title="定位"><img src="img/find01.png"> </a> </span></p>--%>
                                <%--<p>四川省成都市高新区北美一路31号</p></li>--%>
                            <%--<li><p><strong>爱尚美术馆</strong><span class="right"><a href="javascript:void(0)" title="视频"><img src="img/nav_5.png"> </a><a href="javascript:void(0)" title="定位"><img src="img/find01.png"> </a> </span></p>--%>
                                <%--<p>四川省成都市高新区北美一路31号</p></li>--%>
                            <%--<li><p><strong>爱尚美术馆</strong><span class="right"><a href="javascript:void(0)" title="视频"><img src="img/nav_5.png"> </a><a href="javascript:void(0)" title="定位"><img src="img/find01.png"> </a> </span></p>--%>
                                <%--<p>四川省成都市高新区北美一路31号</p></li>--%>
                        <%--</ul>--%>
                        <div id="page"></div>
                    </div>
                </div>
            </div>
            <div class="map_con_div">
                <div class=" bow_shadow">
                    <p>
                        <input type="text" placeholder="输入关键字" class="carNo_input"><input type="button" class="find_but"/>
                    </p>

                    <p class="set_list"><i class="list_i"></i> 专题列表：</p>
                    <p ><ul id="treeDemo" class="ztree"></ul></p>
                </div>
            </div>

        </div>

    </div>

</div>
<script src="/resources/js/jquery/jQuery-2.2.0.min.js"></script>
<script src="/resources/js/base.js"></script>
<script src="/resources/js/echarts-all.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=VFWsApkI2hKPcxTkvmQdEnrioDWFQrmG&callback"></script>
<script src="/resources/js/jQueryPage/src/jquery.page.js"></script>
<script src="/resources/js/map_control.js"></script>
<script src="/resources/js/ztree/jquery.ztree.all-3.5.js"></script>
<script src="/resources/js/listTree.js"></script>
</body>
</html>
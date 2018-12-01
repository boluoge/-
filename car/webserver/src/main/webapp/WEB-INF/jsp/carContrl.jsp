<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>车辆监控</title>
    <link rel="stylesheet" href="/resources/css/base.css">
    <link href="/resources/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />

</head>
<style>

</style>
<body>
<!--顶部-->
<header class="header left">
    <div class="left nav">
        <ul>
            <li ><i class="nav_1"></i><a href="/car/jsp/index">数据概览</a> </li>
            <li class="nav_active"><i class="nav_2"></i><a href="/car/jsp/carContrl">车辆监控</a> </li>
            <li><i class="nav_3"></i><a href="/car/jsp/map">地图界面</a> </li>
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
<div class="con1 left" id="car_control">
<div class="left car_left">
    <div class="left_up bow_shadow">
        <p>
            <input type="text" placeholder="输入车牌号" class="carNo_input"><input type="button" class="find_but"/>
        </p>

        <p class="set_list"><i class="list_i"></i> 设备列表：</p>
        <p ><ul id="treeDemo" class="ztree"></ul></p>
    </div>
    <div class="left_down bow_shadow">
<div class="text_center"><a href="javascript:void (0)" class="tab_a tab_aActive">基本信息</a><a href="javascript:void (0)" class="tab_a">云台控制</a> </div>
        <div class="car_content">
            <p><span>车牌号：</span><span>K89076</span></p>
            <p><span>驾驶人姓名：</span><span>K89076</span></p>
            <p><span>车辆品牌：</span><span>K89076</span></p>
            <p><span>车辆型号：</span><span>K89076</span></p>
            <p><span>所属公司：</span><span>K89076xxxxxxxxxxxxxxxxxx</span></p>
            <p><span>行驶里程：</span><span>xxxxx</span></p>
            <p><span>状态：</span><span>xxxxx</span></p>
            <p><span>所在位置：</span><span>xxxxxxxxxxxxx</span></p>
        </div>
    </div>
</div>
    <div class="left car_center">
        <video controls="controls" ></video>
        <video controls="controls" class="magin_left"></video>
        <video controls="controls" class="magin_top"></video>
        <video controls="controls" class="magin_top magin_left"></video>
    </div>
    <div class="right car_right" id="map"></div>



</div>
<script src="/resources/js/jquery/jQuery-2.2.0.min.js"></script>
<script src="/resources/js/echarts-all.js"></script>
<script src="/resources/js/base.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5ieMMexWmzB9jivTq6oCRX9j&callback"></script>
<script src="/resources/js/car_control.js"></script>
<script src="/resources/js/ztree/jquery.ztree.all-3.5.js"></script>
<script src="/resources/js/listTree.js"></script>

</body>
</html>
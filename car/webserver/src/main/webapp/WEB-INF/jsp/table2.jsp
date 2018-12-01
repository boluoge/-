<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>表格一</title>
    <link rel="stylesheet" href="/resources/js/bstable/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/resources/js/bstable/css/bootstrap-table.css"/>
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
            <li class="nav_active"><i class="nav_1"></i><a href="/car/jsp/index">数据概览</a> </li>
            <li><i class="nav_2"></i><a href="/car/jsp/carContrl">车辆监控</a> </li>
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
    <div class="center_table">
<div class="table_find">
    <p>  <span class="span_left"><label>关键字：</label><input type="text" placeholder="输入关键字"><button class="find_but1"></button></span></p>

</div>

        <div class="table_div" style="margin-top: 0">
                            <p class="table_but"><span class="right"><a href="javascript:void(0)" onclick="add1()"><i class="glyphicon glyphicon-plus"></i>新增</a><a href="javascript:void(0)"><i class="glyphicon glyphicon-remove"></i> 删除</a><a href="javascript:void(0)"><i class="glyphicon glyphicon-print"></i>打印</a><a href="javascript:void(0)"><i class="glyphicon glyphicon-export"></i>导出</a> </span></p>
            <p><table id="table" class="table2_style" border="0"></table></p>
        </div>
    </div>




</div>





<script src="/resources/js/jquery/jQuery-2.2.0.min.js"></script>
<script src="/resources/js/base.js"></script>
<script src="/resources/js/table1.js"></script>
<script src="/resources/js/ztree/jquery.ztree.all-3.5.js"></script>
<script src="/resources/js/listTree.js"></script>
<script src="/resources/js/bstable/js/bootstrap.min.js"></script>
<script src="/resources/js/bstable/js/bootstrap-table.js"></script>
<script src="/resources/js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script src="/resources/js/layer_v2.1/layer/layer.js"></script>

</body>
</html>
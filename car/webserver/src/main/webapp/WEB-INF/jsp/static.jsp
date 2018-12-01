<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>统计分析界面</title>
    <link rel="stylesheet" href="/resources/css/base.css">
</head>
<body>
<!--顶部-->
<header class="header left">
    <div class="left nav">
        <ul>
            <li ><i class="nav_1"></i><a href="/car/jsp/index">数据概览</a> </li>
            <li><i class="nav_2"></i><a href="/car/jsp/carContrl">车辆监控</a> </li>
            <li><i class="nav_3"></i><a href="/car/jsp/map">地图界面</a> </li>

        </ul>
    </div>
    <div class="header_center left">
        <h2><strong>xx区智慧交通服务平台</strong></h2>

    </div>
    <div class="right nav text_right">
        <ul> <li class="nav_active"><i class="nav_7"></i><a href="/car/jsp/static">查询统计</a> </li>
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
<div class="con left">
    <!--选择时间-->
    <div class="static_top left">
        <i></i><span>统计概况</span>
    </div>
    <!--数据总概-->
    <div class="stiatic_top_con left">

<table>
    <tr>
        <td class="labe_td">总概：</td>
        <td colspan="3" class="sky">拥有车辆数123456台，车辆整体情况比较良好，但违法纪律数也很多，应该着重改善驾驶人员交通法律法规教育！</td>
    </tr>
    <tr>
        <td class="labe_td">新进车辆：</td>
        <td>33987台</td>
        <td class="labe_td">违法车辆：</td>
        <td class="org">33987台</td>
    </tr>
    <tr>
        <td class="labe_td">报废车辆：</td>
        <td class="red">33987台</td>
        <td class="labe_td">报废车辆：</td>
        <td>33987台</td>
    </tr>
</table>

    </div>
    <!--统计分析图-->
    <div class="static_top left">
        <i></i><span>每日统计分析</span>
    </div>
    <div class="div_any">
        <div class="left div_any03">
            <div class="div_any_child01 left">
                <div class="div_any_title"><img src="/resources/img/title_1.png">车辆类型统计 </div>
                <p id="char1" class="p_chart"></p>
                <div class="char_table"><div class="table_p table_p01">
                    <table>
                        <thead><tr>
                            <th>排名</th>
                            <th>车牌号</th>
                            <th>里程数（km）</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr><td>1</td><td>粤A03544</td><td>8527</td></tr>
                        <tr><td>2</td><td>粤AF67T3</td><td>8178</td></tr>
                        <tr><td>3</td><td>粤AI1871</td><td>7883</td></tr>
                        <tr><td>4</td><td>京A12345</td><td>6550</td></tr>
                        <tr><td>5</td><td>粤B531SZ</td><td>4999</td></tr>
                        </tbody>
                    </table>
                </div></div>

            </div>
            <div class="div_any_child01 left">
                <div class="div_any_title"><img src="/resources/img/title_2.png">车辆状态统计 </div>
                <p id="char2" class="p_chart"></p>
                <div class="char_table"><div class="table_p table_p01">
                    <table>
                        <thead><tr>
                            <th>排名</th>
                            <th>车牌号</th>
                            <th>里程数（km）</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr><td>1</td><td>粤A03544</td><td>8527</td></tr>
                        <tr><td>2</td><td>粤AF67T3</td><td>8178</td></tr>
                        <tr><td>3</td><td>粤AI1871</td><td>7883</td></tr>
                        <tr><td>4</td><td>京A12345</td><td>6550</td></tr>
                        <tr><td>5</td><td>粤B531SZ</td><td>4999</td></tr>
                        </tbody>
                    </table>
                </div></div>
            </div>
        </div>

        <div class="static_top left">
            <i></i><span>每月统计分析</span>
        </div>
        <div class="right div_any03">
            <div class="div_any_child01 left">
                <div class="div_any_title"><img src="/resources/img/title_4.png">车辆行驶统计 </div>
                <p id="char3" class="p_chart"></p>
                <div class="char_table"><div class="table_p table_p01">
                    <table>
                        <thead><tr>
                            <th>排名</th>
                            <th>车牌号</th>
                            <th>里程数（km）</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr><td>1</td><td>粤A03544</td><td>8527</td></tr>
                        <tr><td>2</td><td>粤AF67T3</td><td>8178</td></tr>
                        <tr><td>3</td><td>粤AI1871</td><td>7883</td></tr>
                        <tr><td>4</td><td>京A12345</td><td>6550</td></tr>
                        <tr><td>5</td><td>粤B531SZ</td><td>4999</td></tr>
                        </tbody>
                    </table>
                </div></div>
            </div>
            <div class="div_any_child01 left">
                <div class="div_any_title"><img src="/resources/img/title_5.png">车辆报警统计 </div>
                <p id="char4" class="p_chart"></p>
                <div class="char_table"><div class="table_p table_p01">
                    <table>
                        <thead><tr>
                            <th>排名</th>
                            <th>车牌号</th>
                            <th>里程数（km）</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr><td>1</td><td>粤A03544</td><td>8527</td></tr>
                        <tr><td>2</td><td>粤AF67T3</td><td>8178</td></tr>
                        <tr><td>3</td><td>粤AI1871</td><td>7883</td></tr>
                        <tr><td>4</td><td>京A12345</td><td>6550</td></tr>
                        <tr><td>5</td><td>粤B531SZ</td><td>4999</td></tr>
                        </tbody>
                    </table>
                </div></div>
            </div>
        </div>
        <%--<div class="static_top left">--%>
            <%--<i></i><span>统计分析一</span>--%>
        <%--</div>--%>

        <%--<div class="right div_any03">--%>

            <%--<div class="div_any_child01 left div_any_child01_wh">--%>
                <%--<div class="div_any_title"><img src="/resources/img/title_5.png">车辆报警统计 </div>--%>
                <%--<p id="char5" class="p_chart"></p>--%>
                <%--<div class="char_table"><div class="table_p table_p01">--%>
                    <%--<table>--%>
                        <%--<thead><tr>--%>
                            <%--<th>排名</th>--%>
                            <%--<th>车牌号</th>--%>
                            <%--<th>里程数（km）</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                        <%--<tr><td>1</td><td>京A12345</td><td>134.2</td></tr>--%>
                        <%--<tr><td>1</td><td>京A12345</td><td>134.2</td></tr>--%>
                        <%--<tr><td>1</td><td>京A12345</td><td>134.2</td></tr>--%>
                        <%--<tr><td>1</td><td>京A12345</td><td>134.2</td></tr>--%>
                        <%--<tr><td>1</td><td>京A12345</td><td>134.2</td></tr>--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                <%--</div></div>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>


</div>
<script src="/resources/js/jquery/jQuery-2.2.0.min.js"></script>
<script src="/resources/js/echarts-all.js"></script>
<script src="/resources/js/base.js"></script>
<script src="/resources/js/static.js"></script>

</body>
</html>
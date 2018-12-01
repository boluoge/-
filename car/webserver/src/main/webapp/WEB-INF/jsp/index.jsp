<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页界面</title>
    <link rel="stylesheet" href="/resources/css/base.css">

</head>
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

</header>
<!--内容部分-->
<div class="con left">
    <!--选择时间-->
    <div class="select_time">
        <div class="static_top left">
            <i></i><span>总体概况</span>
        </div>
    </div>
    <!--数据总概-->
    <div class="con_div">
        <div class="con_div_text left">
            <div class="con_div_text01 left">
                <img src="/resources/img/info_1.png" class="left text01_img"/>
                <div class="left text01_div" id="carnum">
                </div>
            </div>
            <div class="con_div_text01 right">
                <img src="/resources/img/info_2.png" class="left text01_img"/>
                <div class="left text01_div" id="usecarnum">
                </div>
            </div>
        </div>
        <div class="con_div_text left">
            <div class="con_div_text01 left">
                <img src="/resources/img/info_4.png" class="left text01_img"/>
                <div class="left text01_div" id="kilometernum">
                </div>
            </div>
            <div class="con_div_text01 right">
                <img src="/resources/img/info_5.png" class="left text01_img"/>
                <div class="left text01_div" id="avgkilometer">
                </div>
            </div>
        </div>
        <div class="con_div_text left">

            <div class="con_div_text01 left">
                <img src="/resources/img/info_6.png" class="left text01_img"/>
                <div class="left text01_div">
                    <p>行驶时长总数(s)</p>
                    <p class="org">126</p>
                </div>
            </div>
            <div class="con_div_text01 right">
                <img src="/resources/img/info_7.png" class="left text01_img"/>
                <div class="left text01_div">
                    <p>行驶ihfj平均数(s)</p>
                    <p class="org">256</p>
                </div>
            </div>
        </div>
    </div>
    <!--统计分析图-->
    <div class="div_any">
        <div class="left div_any01">
            <div class="div_any_child">
                <div class="div_any_title"><img src="/resources/img/title_1.png">车辆类型统计 </div>
                <p id="char1" class="p_chart"></p>
            </div>
            <div class="div_any_child">
                <div class="div_any_title"><img src="/resources/img/title_2.png">车辆状态统计 </div>
                <p id="char2" class="p_chart"></p>
            </div>
        </div>
        <div class="div_any02 left ">
            <div class="div_any_child div_height">
                <div class="div_any_title any_title_width"><img src="/resources/img/title_3.png">车辆行驶地图 </div>
                <div id="map_div"></div>
            </div>
        </div>
        <div class="right div_any01">
            <div class="div_any_child">
                <div class="div_any_title"><img src="/resources/img/title_4.png">车辆行驶统计 </div>
                <p id="char3" class="p_chart"></p>
            </div>
            <div class="div_any_child">
                <div class="div_any_title"><img src="/resources/img/title_5.png">车辆报警统计 </div>
                <p id="char4" class="p_chart"></p>
            </div>
        </div>
    </div>
    <!--分析表格-->
    <div class="div_table">
        <div class="left div_table_box">
            <div class="div_any_child">
                <div class="div_any_title"><img src="/resources/img/title_4.png">行驶里程排名前5位 </div>
                <div class="table_p">
                    <table>
               <thead><tr>
                   <th>排名</th>
                   <th>车牌号</th>
                   <th>里程数（km）</th>
               </tr>
               </thead>
                <tbody id="top5bykilometer">
                <%--<tr><td>1</td><td>京A12345</td><td>134.2</td></tr>--%>
                <%--<tr><td>1</td><td>京A12345</td><td>134.2</td></tr>--%>
                <%--<tr><td>1</td><td>京A12345</td><td>134.2</td></tr>--%>
                <%--<tr><td>1</td><td>京A12345</td><td>134.2</td></tr>--%>
                <%--<tr><td>1</td><td>京A12345</td><td>134.2</td></tr>--%>


                </tbody>
            </table>
                </div>

            </div>
        </div>
        <div class="left div_table_box">
            <div class="div_any_child">
                <div class="div_any_title"><img src="/resources/img/title_4.png">行驶次数车辆前5位 </div>
                <div class="table_p">
                    <table>
                        <thead><tr>
                            <th>排名</th>
                            <th>车牌号</th>
                            <th>次数（km）</th>
                        </tr>
                        </thead>
                        <tbody id="top5ByCount">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="left div_table_box">
            <div class="div_any_child">
                <div class="div_any_title"><img src="/resources/img/title_4.png">行驶最高时速前5位 </div>
                <div class="table_p">
                    <table>
                        <thead><tr>
                            <th>排名</th>
                            <th>车牌号</th>
                            <th>时速（km）</th>
                        </tr>
                        </thead>
                        <tbody id="top5BySpeed">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="left div_table_box">
            <div class="div_any_child">
                <div class="div_any_title"><img src="/resources/img/title_4.png">区域内车辆排行前5位 </div>
                <div class="table_p">
                    <table>
                        <thead><tr>
                            <th>排名</th>
                            <th>区域名</th>
                            <th>车辆数</th>
                        </tr>
                        </thead>
                        <tbody id="top5ByLocation">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/resources/js/jquery/jQuery-2.2.0.min.js"></script>
<script src="/resources/js/echarts-all.js"></script>
<script src="/resources/js/base.js"></script>
<script src="/resources/js/index.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=VFWsApkI2hKPcxTkvmQdEnrioDWFQrmG&callback"></script>
<script src="/resources/js/map.js"></script>
</body>
</html>
<!--5ieMMexWmzB9jivTq6oCRX9j-->
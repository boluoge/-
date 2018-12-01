/**
 * Created by 30947 on 2018/7/18.
 */
$(function(){

    char1();
    char2();
    char3();
    char4();
    char5();
    char7();
    char8();
    char9();
    char10();
    // 定时请求：
    $(function(){

                setInterval(char1, 60000);
            })
});

//统计分析图
function char1() {
    var myChart = echarts.init($("#char1")[0]);
    console.log("开始")
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

function char2() {

    var myChart = echarts.init($("#char2")[0]);

    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {show:'true',borderWidth:'0'},
        legend: {
            data:['行驶', '临时停车','停车'],
            textStyle : {
                color : '#ffffff',

            }
        },

        calculable :false,
        xAxis : [
            {
                type : 'value',
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                splitLine:{
                    lineStyle:{
                        color:['#f2f2f2'],
                        width:0,
                        type:'solid'
                    }
                }

            }
        ],
        yAxis : [
            {
                type : 'category',
                data : ['客运车','危险品车','网约车','学生校车'],
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                splitLine:{
                    lineStyle:{
                        width:0,
                        type:'solid'
                    }
                }
            }
        ],
        series : [
            {
                name:'行驶',
                type:'bar',
                stack: '总量',
                itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                data:[]
            },
            {
                name:'临时停车',
                type:'bar',
                stack: '总量',
                itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                data:[]
            },

            {
                name:'停车',
                type:'bar',
                stack: '总量',
                itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                data:[]
            }

        ]
    };

    $.ajax({
        url: "/car/mysql/findCSN",//请求地址
        type: "POST",
        dataType: "json",
        scriptCharset:"utf-8",
        success: function(data) {//data是默认的，接收前台返回的数据
            for(var i=0; i<data.length; i++){

              //  option.series[0].data.push({value:data[i].c1, name:data[i].category});
               if(data[i].statu =="行驶") {
                   option.series[0].data.push(data[i].num);
               }else if(data[i].statu=="临时停车"){
                   option.series[1].data.push(data[i].num);
            }else if(data[i].statu=="停车"){
                   option.series[2].data.push(data[i].num);
               }

            }
            myChart.setOption(option);
        }
    });
    window.addEventListener('resize', function () {myChart.resize();})

}

function char3() {

    var myChart = echarts.init($("#char3")[0]);

    var option = {
        legend: {
            data:['车辆行驶数量'],
            textStyle : {
                color : '#ffffff',

            }
        },
        grid: {show:'true',borderWidth:'0'},

        calculable : false,
        tooltip : {
            trigger: 'axis',
            formatter: "Temperature : <br/>{b}km : {c}°C"
        },
        xAxis : [
            {
                type : 'value',
                axisLabel : {
                    formatter: '{value}',
                    textStyle: {
                        color: '#fff'
                    }
                },

                splitLine:{
                    lineStyle:{
                        width:0,
                        type:'solid'
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'category',
                axisLine : {onZero: false},
                axisLabel : {
                    formatter: '{value} km/h',
                    textStyle: {
                        color: '#fff'
                    }
                },
                splitLine:{
                    lineStyle:{
                        width:0,
                        type:'solid'
                    }
                },
                boundaryGap : false,
                data : ['0', '50', '100', '150', '200', '250', '300']
            }
        ],
        series : [
            {
                name:'车辆行驶数量',
                type:'line',
                smooth:true,
                itemStyle: {
                    normal: {
                        lineStyle: {
                            shadowColor : 'rgba(0,0,0,0.4)'
                        }
                    }
                },
                data:[]
            }
        ]
    };
    $.ajax({
        url: "/car/mysql/findSN",//请求地址
        type: "POST",
        dataType: "json",
        scriptCharset:"utf-8",
        success: function(data) {//data是默认的，接收前台返回的数据
            for(var i=0; i<data.length; i++) {
                option.series[0].data.push(data[i].num);
            }
            myChart.setOption(option);
        }
    });
    window.addEventListener('resize', function () {myChart.resize();})

}
function char4() {

    var myChart = echarts.init($("#char4")[0]);

    option = {
        grid: {show:'true',borderWidth:'0'},
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },

            formatter: function (params) {
                var tar = params[0];
                return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
            }
        },

        xAxis : [
            {
                type : 'category',
                splitLine: {show:false},
                data : ['客运车','危险品车','网约车','学生校车'],
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                }

            }
        ],
        yAxis : [
            {
                type : 'value',
                splitLine: {show:false},
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                }
            }
        ],
        series : [

            {
                name:'报警数量',
                type:'bar',
                stack: '总量',
                itemStyle : { normal: {label : {show: true, position: 'inside'}}},
                data:[2900, 1200, 300, 200, 900, 300]
            }
        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () {myChart.resize();})

}

//车辆总数
function char5() {

    $.ajax({
        url: "/car/mysql/findCKN",//请求地址
        type: "POST",
        dataType: "json",
        scriptCharset:"utf-8",
        success: function(data) {//data是默认的，接收前台返回的数据
            console.log(data);
            //[{"carnum":20,"usecarnum":74,"kilometernum":300266.0,"avgkilometer":4057.65}]
                document.getElementById('carnum').innerHTML =
                    "<p>车辆总数(辆)</p>" +
                    "<p>" + data[0].carnum + "</p>";

                document.getElementById('usecarnum').innerHTML =
                    "<p>车辆使用数(辆)</p>" +
                    "<p>" + data[0].usecarnum + "</p>";

                document.getElementById('kilometernum').innerHTML =
                    "<p>行驶里程总数(km)</p>" +
                    "<p>" + data[0].kilometernum + "</p>";

                document.getElementById('avgkilometer').innerHTML =
                    "<p>行驶里程平均数(km)</p>" +
                    "<p>" + data[0].avgkilometer + "</p>";
            }

    });
}


function char7() {
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
            document.getElementById("top5bykilometer").innerHTML=
               str

        }
    });
}

function char8() {
    var str= "";
    $.ajax({
        url: "/car/mysql/top5ByCount",//请求地址 top5bykilometer
        type: "POST",
        dataType: "json",
        scriptCharset:"utf-8",
        success: function(data) {//data是默认的，接收前台返回的数据
            for(var j=0;j<data.length;j++){
                str=str+"<tr><td>"+(j+1)+"</td><td>"+data[j].carid+"</td><td>"+data[j].count+"</td></tr>";
            }
            document.getElementById("top5ByCount").innerHTML=
                str

        }
    });
}

function char9() {
    var str= "";
    $.ajax({
        url: "/car/mysql/top5BySpeed",//请求地址
        type: "POST",
        dataType: "json",
        scriptCharset:"utf-8",
        success: function(data) {//data是默认的，接收前台返回的数据
            for(var j=0;j<data.length;j++){
                str=str+"<tr><td>"+(j+1)+"</td><td>"+data[j].carid+"</td><td>"+data[j].speed+"</td></tr>";
            }
            document.getElementById("top5BySpeed").innerHTML=
                str

        }
    });
}

function char10() {
    var str= "";
    $.ajax({
        url: "/car/mysql/top5ByLocation",//请求地址
        type: "POST",
        dataType: "json",
        scriptCharset:"utf-8",
        success: function(data) {//data是默认的，接收前台返回的数据
            for(var j=0;j<data.length;j++){
                str=str+"<tr><td>"+(j+1)+"</td><td>"+data[j].location+"</td><td>"+data[j].num+"</td></tr>";
            }
            document.getElementById("top5ByLocation").innerHTML=
                str

        }
    });
}




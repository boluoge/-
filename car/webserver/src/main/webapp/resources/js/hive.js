function queryCategoryNum() {
    $("#username").click(function () {
        $.ajax({
            url: "/car/hive/queryCategoryNumber",//请求地址
            type: "POST",
            dataType: "json",
            success: function (data) {//data是默认的，接收前台返回的数据
                //alert("success");
                $.each(data, function (index, element) {//循环
                    $("#tr").append("<th>" + element.userid + "</th>");
                    $("#tr").append("<th>" + element.username + "</th>");
                    $("#tr").append("<th>" + element.password + "</th>");
                    // alert(data[0].areaName);////alert(data.row[0].areaName);


                })
            }
        });
    });
}
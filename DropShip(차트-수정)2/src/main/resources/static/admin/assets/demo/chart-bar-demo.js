//// Set new default font family and font color to mimic Bootstrap's default styling
//Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
//Chart.defaults.global.defaultFontColor = '#292b2c';
//
//// 매출
//
//$(document).ready(function() {
//    // start both charts automatically
//    $.when(chartBtn(), chartBtn2()).done(function() {
//        // code to execute when both charts are loaded
//    });
//
//    // #period, depending on option value in select
//    $("#period, #period2").on("change", function() {
//        $.when(chartBtn(), chartBtn2()).done(function() {
//            // code to execute when both charts are reloaded
//        });
//    });
//});
//
//    function chartBtn2(){
//        //데이터 불러와줌 
//        $.ajax({
//            url:"/adminchartData2",
//            type:"get",
//            dataType:"json",
//            data:{"period2":$("#period2").val()},
//            success:function(map){
//                // update chart data
//                updateChart(map);
//            },
//            error:function(){
//                alert("failed");
//            }
//        });
//    }
//
//    //그리기 함수
//    function updateChart(map){
//        const ctx = document.getElementById('myBarChart');
//        new Chart(ctx, {
//            type: 'bar',
//            data: {
////                labels: ["가로축","가로축2","가로축2","가로축2"], // 가로축 이름
//                labels: map.orderMonth, // 가로축 이름
//                datasets: [{
//                    label: $("#period2").val(), // 차트 이름
////                    label: data.memberTitle, // 차트 이름
////                    data: [33,44,53,12], // 데이터 값
//				    backgroundColor: "rgba(2,117,216,1)",
//      				borderColor: "rgba(2,117,216,1)",
//                    data: map.totalPrice, // 데이터 값
//                    borderWidth: 1
//                   
//                }]
//            },
//            options: {
//                scales: {
//                    y: {
//                        beginAtZero: true
//                    }
//                }
//            }
//        });
//    }
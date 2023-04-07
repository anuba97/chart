<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<title>chart</title>
		<style>
			#graph{width:80%; height:500px; margin:30px auto; border: 1px solid black;}
		</style>
		<script>
			function chartBtn(){
				//데이터 불러와줌 
				$.ajax({
			    		url:"/chartData",
			    		type:"get",
			    		dataType:"json",
			    		data:{"period":$("#period").val()},
			    		success:function(data){ 
			    			alert("성공");
			    			chartDataSend(data);
			    			
			    		},
			    		error:function(){
			    			alert("실패");
			    		}
				});
				}
				//그리기 함수
				
				function chartDataSend(data){
					const ctx = document.getElementById('myChart');
					  new Chart(ctx, {
					    type: 'bar',
					    data: {
					      labels: data.memberLabel,  // 가로축 이름)
					      datasets: [{
					        label: data.memberTitle, // 차트 이름
					        data: data.memberData,   // 데이터 값
					        borderWidth: 1
					      }]
					    },
					    options: {
					      scales: {
					        y: {
					          beginAtZero: true
					        }
					      }
					    }
					  });
					}
		</script>
	</head>
	<body>
		<select name="period" id="period">
			<option value="daily">일별 회원가입</option>
			<option value="weekly">주별 회원가입</option>
			<option value="monthly">월별 회원가입</option>
		</select>
		
		<button type="button" onclick=" chartBtn()">차트그리기</button>
			<div id="graph">
			  <canvas id="myChart"></canvas>
			</div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>DropShip Admin - 주문 상세</title>
    <link rel="shortcut icon" href="admin/img/favicon.ico" />
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link href="admin/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
<script>
	$(function() {
	    $('#order_status').on('change', function() {
	        var orderId = $('#order_id').text();
	        var selectedValue = $(this).val();
	        alert(selectedValue);
	        $.ajax({
	            type: 'POST',
	            url: '/updateOrderStatus',
	            data: {
	                id : orderId,
	            	order_status: selectedValue
	            },
	            success: function(success) {
	            	if(success == 1){
	                	alert("처리 상태가 변경되었습니다.")
	            	}else{
            		 	alert("처리 상태 변경이 실패했습니다.")
	            	}
	            },
	            error: function(jqXHR, textStatus, errorThrown) {
	                alert("처리 데이터 받아오기가 실패했습니다.")
	            }
	        });
	    });
	});
</script>

</head>

<body class="sb-nav-fixed">
    <!-- navBar 부분 시작 -->
    <%@ include file ="include/navBar.jsp" %>
    <!-- navBar 부분 끝 -->
    <div id="layoutSidenav">
        <div id="layoutSidenav_nav">
            <!-- sideMenu 부분 시작 -->
            <%@ include file ="include/sideMenu.jsp" %>
            <!-- sideMenu 부분 끝 -->
        </div>
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">주문 현황</h1>
                    <div class="card mb-4">
                        <div class="card-body">DropShip 고객 주문 현황 상세 확인 페이지입니다. 주문 상태에 따라 진행해 주세요.</div>
                    </div>
                </div>
                <form action="order_condition" name="order_condition" method="post">
                    <table class="admin_customerTable">
                        <colgroup>
                            <col width="30%">
                            <col width="70%">
                        </colgroup>
                        <tr>
                            <th>주문 고유번호</th>
                            <td id="order_id">
                                ${order_DetailVo.id}
                            </td>
                        </tr>
                        <tr>
                            <th>주문자 아이디</th>
                            <td>
                                ${order_DetailVo.order_member_id}
                            </td>
                        </tr>
                        <tr>
                            <th>주문자</th>
                            <td>
                                ${order_DetailVo.member_name}
                            </td>
                        </tr>
                        <tr>
                            <th>주문 상품</th>
                            <td>
                                ${order_DetailVo.work_name}
                            </td>
                        </tr>
                        <tr>
                            <th>주문 옵션</th>
                            <td>
                                ${order_DetailVo.option_id}
                            </td>
                        </tr>
                        <tr>
                            <th>결제 금액</th>
                            <td>
                                <fmt:formatNumber value="${order_DetailVo.final_price}" type="number"/> 원
                            </td>
                        </tr>
                        <tr>
                            <th>주문 날짜</th>
                            <td>
                                ${order_DetailVo.order_date}
                            </td>
                        </tr>
                        <tr>
                            <th>배송지 주소</th>
                            <td>
                                ${order_DetailVo.delivery_road}${order_DetailVo.delivery_address}
                            </td>
                        </tr>
                        <tr>
                            <th>받는 사람 이름</th>
                            <td>
                                ${order_DetailVo.delivery_name}
                            </td>
                        </tr>
                        <tr>
                            <th>받는 사람 휴대폰번호</th>
                            <td>
                                ${order_DetailVo.delivery_phone}
                            </td>
                        </tr>
                        <tr>
                            <th>배송 고유번호(송장)</th>
                            <td>
                                ${order_DetailVo.id}
                            </td>
                        </tr>
                        <tr>
                            <th>처리 상태</th>
                            <td>
                                <select name="order_status" id="order_status">
                                    <option value="0" <c:if test="${order_DetailVo.order_status == '0'}">selected</c:if>>0. 입금 확인 중</option>
                                    <option value="1" <c:if test="${order_DetailVo.order_status == '1'}">selected</c:if>>1. 입금 완료</option>
                                    <option value="2" <c:if test="${order_DetailVo.order_status == '2'}">selected</c:if>>2. 상품 준비 중</option>
                                    <option value="3" <c:if test="${order_DetailVo.order_status == '3'}">selected</c:if>>3. 배송 중</option>
                                    <option value="4" <c:if test="${order_DetailVo.order_status == '4'}">selected</c:if>>4. 배송 완료</option>
                                    <option value="5" <c:if test="${order_DetailVo.order_status == '5'}">selected</c:if>>5. 주문 취소</option>
                                    <option value="6" <c:if test="${order_DetailVo.order_status == '6'}">selected</c:if>>6. 환불 완료</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>안내 사항</th>
                            <td style="color:red; font-weight:bold;">
                                처리 상태를 변경하여 주문 단계를 처리할 수 있습니다.
                            </td>
                        </tr>
                    </table>
                </form><br>
                <div class="admin_noticeBoard_div">
<!--                     <button type="button" class="admin_noticeBoard_button" onClick="location.href=''" style="margin: 0 0 0 180px; color:red; border-radius:5px;">주문 수정</button> -->
                    <button type="button" class="admin_noticeBoard_button" onClick="location.href='admin_orderList'" style="margin: 0 0 0 250px; border-radius:5px;">주문 리스트</button>
                </div>
            </main>
            <footer class="py-4 bg-light mt-auto">
                <div class="container-fluid px-4">
                    <div class="d-flex align-items-center justify-content-between small">
                        <div class="text-muted">Copyright &copy; Team DropShip ADMIN Project</div>
                        <div></div>
                    </div>
                </div>
            </footer>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="admin/js/scripts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="admin/js/datatables-simple-demo.js"></script>
</body>

</html>
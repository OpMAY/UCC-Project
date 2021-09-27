<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-07-13
  Time: 오후 1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>후원 관리</title>
    <!-- core:css -->
    <link rel="stylesheet" href="../assets/vendors/core/core.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <link rel="stylesheet" href="../assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="../assets/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
    <!-- end plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="../assets/fonts/feather-font/css/iconfont.css">
    <link rel="stylesheet" href="../assets/vendors/flag-icon-css/css/flag-icon.min.css">
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="../assets/css/demo_1/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="../assets/images/favicon.png"/>

</head>
<body>
<div class="main-wrapper">
    <!-- partial:partials/_sidebar.jsp -->
    <jsp:include page="partials/_sidebar.jsp" flush="true"></jsp:include>
    <!-- partial -->

    <div class="page-wrapper">
        <!-- partial:partials/_navbar.jsp -->
        <jsp:include page="partials/_navbar.jsp" flush="true"></jsp:include>
        <!-- partial -->
        <div class="page-content">

            <nav class="page-breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item" style="color: #baa2fc">후원 관리</li>
                </ol>
            </nav>

            <div class="alert alert-primary" style="white-space: pre-wrap;" role="alert">
                후원에 관련된 안내 메세지 정해주세요.
            </div>

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large">후원 관리
                            </h6>
                            <div class="table-responsive">
                                <table id="dataTableExample" class="table" style="table-layout: fixed">
                                    <thead>
                                    <tr>
                                        <th width="10px">#</th>
                                        <th width="30px">후원한 사용자</th>
                                        <th width="30px">후원받은 사용자</th>
                                        <th width="30px">후원 종류</th>
                                        <th width="60px">후원 일자</th>
                                        <th width="30px">승인 여부</th>
                                        <th width="70px">금액</th>
                                        <th width="40px">자세히 보기</th>
                                        <th width="50px">송금 처리</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="sponList" value="${sponList}"/>
                                    <c:forEach var="i" begin="1" end="${sponList.size()}">
                                    <tr>
                                        <td>${i}</td>
                                        <td class="overflow-hidden"
                                            style="text-overflow: ellipsis">
                                            ${sponList[i-1].user_name}
                                        </td>
                                        <td class="overflow-hidden"
                                            style="text-overflow: ellipsis">
                                                ${sponList[i-1].artist_name}
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${sponList[i-1].type == 'Board Spon'}">
                                                    게시글 후원
                                                </c:when>
                                                <c:when test="${sponList[i-1].type == 'Artist Spon'}">
                                                    아티스트 후원
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td class="overflow-hidden"
                                            style="text-overflow: ellipsis">
                                                ${sponList[i-1].spon_date}
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${sponList[i-1].status == true}">
                                                    승인
                                                </c:when>
                                                <c:when test="${sponList[i-1].status == false}">
                                                    미승인
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td class="overflow-hidden"
                                            style="text-overflow: ellipsis">
                                            ${sponList[i-1].price}원
                                        </td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="location.href='/admin/spon_detail.do?spon_no=${sponList[i-1].spon_no}'">
                                                <i class="btn-icon-prepend" data-feather="search"></i>
                                                보기
                                            </button>
                                        </td>
                                        <td>

                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="if(confirm('${sponList[i-1].artist_name}님께 후원 금액을 송금 처리하시겠습니까?')){sendSpon(${sponList[i-1].spon_no});} else {return false;}"
                                                    <c:if test="${sponList[i-1].purchase_status == true}">disabled</c:if>>
                                                <i class="btn-icon-prepend" data-feather="send"></i>
                                                처리하기
                                            </button>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- core:js -->
<script src="../assets/vendors/core/core.js"></script>
<!-- endinject -->
<!-- plugin js for this page -->
<script src="../assets/vendors/chartjs/Chart.min.js"></script>
<script src="../assets/vendors/jquery.flot/jquery.flot.js"></script>
<script src="../assets/vendors/jquery.flot/jquery.flot.resize.js"></script>
<script src="../assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
<script src="../assets/vendors/apexcharts/apexcharts.min.js"></script>
<script src="../assets/vendors/progressbar.js/progressbar.min.js"></script>
<script src="../assets/vendors/datatables.net/jquery.dataTables.js"></script>
<script src="../assets/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
<!-- end plugin js for this page -->
<!-- inject:js -->
<script src="../assets/vendors/feather-icons/feather.min.js"></script>
<script src="../assets/js/template.js"></script>
<script src="../assets/js/inspect.js"></script>
<!-- endinject -->
<!-- custom js for this page -->
<script src="../assets/js/dashboard.js"></script>
<script src="../assets/js/datepicker.js"></script>
<script src="../assets/js/data-table.js"></script>
<!-- end custom js for this page -->
<script>
    function sendSpon(spon_no) {
        let data = {"spon_no": spon_no};
        $.ajax({
            type: 'POST',
            url: '/admin/spon_send.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("후원 금액 전송 처리하였습니다.");
                window.location.reload();
            } else if (result === 2) {
                alert("승인된 후원 결제 내역만 송금처리 할 수 있습니다.");
            } else {
                alert("알 수 없는 오류 발생. 관리자에게 문의하세요.");
                window.location.reload();
            }
        }).fail(function (error) {
            alert(error);
            window.location.reload();
        })
    }
</script>
</body>
</html>

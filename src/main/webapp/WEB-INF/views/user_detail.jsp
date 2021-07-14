<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-07-20
  Time: 오후 2:47
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
    <title>회원 관리 - 사용자 상세</title>
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

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large">사용자 상세</h6>
                            <c:set var="User" value="${User}"/>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <h6 class="card-title">사용자 정보</h6>
                                            <div id="content">
                                                <ul class="timeline">
                                                    <li class="event" data-date="이름">
                                                        <h3>${User.name}</h3>
                                                    </li>
                                                    <li class="event" data-date="소셜 로그인">
                                                        <h3>${User.sns}</h3>
                                                    </li>
                                                    <li class="event" data-date="아티스트 전환 여부">
                                                        <h3>
                                                            <c:choose>
                                                                <c:when test="${User._artist == false}">유저</c:when>
                                                                <c:when test="${User._artist == true}">아티스트</c:when>
                                                            </c:choose>
                                                        </h3>
                                                    </li>
                                                    <li class="event" data-date="생성일자">
                                                        <h3>${User.reg_date}</h3>
                                                    </li>
                                                    <li class="event" data-date="총 후원 금액">
                                                        <h3>5000원</h3>
                                                    </li>
                                                    <li class="event" data-date="경고 횟수">
                                                        <h3>0회</h3>
                                                    </li>
                                                    <li class="event" data-date="사용 정지 기간">
                                                        <h3>-</h3>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 justify-content-center d-flex">
                                        <button class="btn btn-outline-primary">
                                        정지
                                    </button>
                                </div>
                                <div class="col-md-6 justify-content-center d-flex">
                                    <button class="btn btn-secondary" onclick="window.history.back()">
                                        돌아가기
                                    </button>
                                </div>
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
<!-- endinject -->
<!-- custom js for this page -->
<script src="../assets/js/dashboard.js"></script>
<script src="../assets/js/datepicker.js"></script>
<script src="../assets/js/data-table.js"></script>
<!-- end custom js for this page -->
</body>
</html>

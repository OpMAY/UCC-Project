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
    <title>회원 관리 - 아티스트</title>
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
                    <li class="breadcrumb-item" style="color: #baa2fc">회원관리</li>
                    <li class="breadcrumb-item active" aria-current="page">아티스트</li>
                </ol>
            </nav>

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large"><a href="/admin/users.do">사용자</a> 아티스트 </h6>
                            <div class="table-responsive">
                                <table id="dataTableExample" class="table">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>아티스트 명</th>
                                        <th>팬콕 수</th>
                                        <th>전환 일자</th>
                                        <th>포트폴리오</th>
                                        <th>게시글</th>
                                        <th>크라우드</th>
                                        <th>댓글 내역</th>
                                        <th>자세히 보기</th>
                                        <th>사용 정지</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="i" begin="1" end="${artistList.size()}">
                                        <tr>
                                            <td>${i}</td>
                                            <td>${artistList[i-1].artist_name}</td>
                                            <td>${artistList[i-1].fan_number}</td>
                                            <td>${artistList[i-1].reg_date}</td>
                                            <td>
                                                <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0" onclick="location.href='/admin/portfolio.do?artist_no=${artistList[i-1].artist_no}&type=all'">
                                                    <i class="btn-icon-prepend" data-feather="search"></i>
                                                    보기
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0" onclick="location.href='/admin/board.do?artist_no=${artistList[i-1].artist_no}'">
                                                    <i class="btn-icon-prepend" data-feather="search"></i>
                                                    보기
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0" onclick="location.href='/admin/artist_loudsourcing.do?artist_no=${artistList[i-1].artist_no}'">
                                                    <i class="btn-icon-prepend" data-feather="search"></i>
                                                    보기
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0" onclick="location.href='/admin/comments.do?user_no=${artistList[i-1].user_no}'">
                                                    <i class="btn-icon-prepend" data-feather="search"></i>
                                                    보기
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0" onclick="location.href='/admin/artist_detail.do?artist_no=${artistList[i-1].artist_no}'">
                                                    <i class="btn-icon-prepend" data-feather="search"></i>
                                                    보기
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0" onclick="openWindowPopBan('/admin/penalty.do?user_no=${artistList[i-1].user_no}','회원 정지')">
                                                    <i class="btn-icon-prepend" data-feather="x-square"></i>
                                                    정지
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
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
    function openWindowPopBan(url, name){
        let options = 'top=10, left=10, width=720, height=1040, status=1, scrollbars=1, resizable=1, menubar=0, fullscreen=0, location=0';
        window.open(url, name, options);
    }
</script>
</body>
</html>

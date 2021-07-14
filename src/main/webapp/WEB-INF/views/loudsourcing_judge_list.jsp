<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-07-22
  Time: 오후 7:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>크라우드 관리 - 심사</title>
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
                    <li class="breadcrumb-item" style="color: #baa2fc">크라우드 관리</li>
                    <li class="breadcrumb-item active" aria-current="page">심사</li>
                </ol>
            </nav>

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large"><a
                                    href="${pageContext.request.contextPath}/admin/loudsourcing_recruitment.do">
                                모집 </a><a
                                    href="${pageContext.request.contextPath}/admin/loudsourcing_process.do">
                                진행 </a>심사 <a
                                    href="${pageContext.request.contextPath}/admin/loudsourcing_end.do">
                                종료 </a></h6>
                            <div class="table-responsive">
                                <table id="dataTableExample" class="table">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>공모전 이름</th>
                                        <th>상태</th>
                                        <th>자동 선정 인원</th>
                                        <th>총 기간</th>
                                        <th>진행 기간</th>
                                        <th>심사 시작 일자</th>
                                        <th>참여 인원 리스트</th>
                                        <th>자세히 보기</th>
                                        <th>삭제</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="i" begin="1" end="${loudsourcingList.size()}">
                                        <tr>
                                            <td>
                                                ${i}
                                            </td>
                                            <td>
                                                ${loudsourcingList[i-1].name}
                                            </td>
                                            <td>
                                                심사 중
                                            </td>
                                            <td>
                                                ${loudsourcingList[i-1].selected_artist_num}
                                            </td>
                                            <td>
                                                ${loudsourcingList[i-1].start_date}~${loudsourcingList[i-1].end_date}
                                            </td>
                                            <td>
                                                ${loudsourcingList[i-1].process_start_date}~${loudsourcingList[i-1].process_end_date}
                                            </td>
                                            <td>
                                                ${loudsourcingList[i-1].judge_date}
                                            </td>
                                            <td>
                                                <button type="button"
                                                        class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                        onclick="location.href='/admin/portfolio_detail.do?portfolio_no=${portfolioList[i-1].portfolio_no}'">
                                                    <i class="btn-icon-prepend" data-feather="search"></i>
                                                    보기
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button"
                                                        class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                        onclick="location.href='/admin/portfolio_detail.do?portfolio_no=${portfolioList[i-1].portfolio_no}'">
                                                    <i class="btn-icon-prepend" data-feather="search"></i>
                                                    보기
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button"
                                                        class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0" onclick="if(confirm('정말 삭제 하시겠습니까?')){DeletePortfolio(${portfolioList[i-1].portfolio_no});} else {return false;}">
                                                    <i class="btn-icon-prepend" data-feather="trash"></i>
                                                    삭제
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
<!-- endinject -->
<!-- custom js for this page -->
<script src="../assets/js/dashboard.js"></script>
<script src="../assets/js/datepicker.js"></script>
<script src="../assets/js/data-table.js"></script>
<!-- end custom js for this page -->
</body>
</html>

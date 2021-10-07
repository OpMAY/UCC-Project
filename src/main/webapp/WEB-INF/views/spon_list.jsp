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
    <link rel="stylesheet" href="/assets/vendors/core/core.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <link rel="stylesheet" href="/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="/assets/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
    <!-- end plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="/assets/fonts/feather-font/css/iconfont.css">
    <link rel="stylesheet" href="/assets/vendors/flag-icon-css/css/flag-icon.min.css">
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="/assets/css/demo_1/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="/assets/images/favicon.png"/>

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


            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large"><c:choose><c:when
                                    test="${status == 'purchase'}">결제 오류 <a style="margin-left: 10px" href="${pageContext.request.contextPath}/admin/spon/apply.do">미승인 </a><a style="margin-left: 10px" href="${pageContext.request.contextPath}/admin/spon/send.do">미정산 </a><a style="margin-left: 10px" href="${pageContext.request.contextPath}/admin/spon/complete.do">완료 </a>
                            </c:when><c:when test="${status == 'apply'}"><a style="margin-right: 10px"
                                                                        href="${pageContext.request.contextPath}/admin/spon/purchase.do">
                                결제오류 </a>미승인 <a style="margin-left: 10px"
                                                 href="${pageContext.request.contextPath}/admin/spon/send.do">
                                미정산 </a><a style="margin-left: 10px"
                                           href="${pageContext.request.contextPath}/admin/spon/complete.do">
                                완료 </a></c:when><c:when
                                    test="${status == 'send'}"><a style="margin-right: 10px"
                                                                 href="${pageContext.request.contextPath}/admin/spon/purchase.do">
                                결제오류 </a><a style="margin-right: 10px"
                                             href="${pageContext.request.contextPath}/admin/spon/apply.do">
                                미승인 </a>미정산 <a style="margin-left: 10px"
                                               href="${pageContext.request.contextPath}/admin/spon/complete.do">
                                완료 </a></c:when><c:when
                                    test="${status == 'complete'}"><a style="margin-right: 10px"
                                                                href="${pageContext.request.contextPath}/admin/spon/purchase.do">
                                결제오류 </a><a style="margin-right: 10px"
                                             href="${pageContext.request.contextPath}/admin/spon/apply.do">
                                미승인 </a><a style="margin-right: 10px"
                                           href="${pageContext.request.contextPath}/admin/spon/send.do">
                                미정산 </a>완료 </c:when></c:choose></h6>
                            <div class="table-responsive">
                                <table id="dataTableExample" class="table" style="table-layout: fixed">
                                    <thead>
                                    <tr>
                                        <th width="10px">#</th>
                                        <th width="30px">후원한 사용자</th>
                                        <th width="30px">후원받은 사용자</th>
                                        <th width="30px">결제 여부</th>
                                        <th width="30px">승인 여부</th>
                                        <th width="30px">입금 여부</th>
                                        <th width="70px">금액</th>
                                        <th width="60px">후원 일자</th>
                                        <th width="40px">자세히 보기</th>
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
                                                <c:when test="${sponList[i-1].verify_status == 0}">
                                                    O
                                                </c:when>
                                                <c:when test="${sponList[i-1].verify_status == 1}">
                                                    취소됨
                                                </c:when>
                                                <c:when test="${sponList[i-1].verify_status == 2}">
                                                    보류 중
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${sponList[i-1].status == true}">
                                                    O
                                                </c:when>
                                                <c:when test="${sponList[i-1].status == false}">
                                                    X
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${sponList[i-1].purchase_status == true}">
                                                    O
                                                </c:when>
                                                <c:when test="${sponList[i-1].purchase_status == false}">
                                                    X
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                                ${sponList[i-1].price}
                                        </td>
                                        <td class="overflow-hidden"
                                            style="text-overflow: ellipsis">
                                                ${sponList[i-1].spon_date}
                                        </td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="location.href='/admin/spon_detail.do?spon_no=${sponList[i-1].spon_no}&prevStatus=${status}'">
                                                <i class="btn-icon-prepend" data-feather="search"></i>
                                                보기
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
        <!-- partial:partials/_footer.jsp -->
        <jsp:include page="partials/_footer.jsp" flush="true"></jsp:include>
        <!-- partial -->
    </div>
</div>

<!-- core:js -->
<script src="/assets/vendors/core/core.js"></script>
<!-- endinject -->
<!-- plugin js for this page -->
<script src="/assets/vendors/chartjs/Chart.min.js"></script>
<script src="/assets/vendors/jquery.flot/jquery.flot.js"></script>
<script src="/assets/vendors/jquery.flot/jquery.flot.resize.js"></script>
<script src="/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
<script src="/assets/vendors/apexcharts/apexcharts.min.js"></script>
<script src="/assets/vendors/progressbar.js/progressbar.min.js"></script>
<script src="/assets/vendors/datatables.net/jquery.dataTables.js"></script>
<script src="/assets/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
<!-- end plugin js for this page -->
<!-- inject:js -->
<script src="/assets/vendors/feather-icons/feather.min.js"></script>
<script src="/assets/js/template.js"></script>
<script src="/assets/js/inspect.js"></script>
<!-- endinject -->
<!-- custom js for this page -->
<script src="/assets/js/dashboard.js"></script>
<script src="/assets/js/datepicker.js"></script>
<script src="/assets/js/data-table.js"></script>
<!-- end custom js for this page -->
<script>

</script>
</body>
</html>

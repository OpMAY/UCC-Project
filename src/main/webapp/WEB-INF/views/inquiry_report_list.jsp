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
    <title>문의 내역</title>
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
                    <li class="breadcrumb-item" style="color: #baa2fc">문의</li>
                    <li class="breadcrumb-item active" aria-current="page">신고 문의
                </ol>
            </nav>

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large"><a
                                    href="${pageContext.request.contextPath}/admin/inquiry_loudsourcing.do">크라우드</a> 신고
                                <a href="${pageContext.request.contextPath}/admin/inquiry_normal.do">
                                    기타 </a>
                            </h6>
                            <div class="table-responsive">
                                <table id="dataTableExample" class="table" style="table-layout: fixed">
                                    <thead>
                                    <tr>
                                        <th width="10px">#</th>
                                        <th width="30px">작성자</th>
                                        <th width="100px">신고 대상</th>
                                        <th width="150px">문의 내용</th>
                                        <th width="30px">답변 상태</th>
                                        <th width="60px">등록 일자</th>
                                        <th width="50px">자세히 보기</th>
                                        <th width="50px">삭제</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="inquiryList" value="${inquiryList}"/>
                                    <c:forEach var="i" begin="1" end="${inquiryList.size()}">
                                    <tr>
                                        <td>${i}</td>
                                        <td class="overflow-hidden"
                                            style="text-overflow: ellipsis">${inquiryList[i-1].user_name}</td>
                                        <td class="overflow-hidden"
                                            style="text-overflow: ellipsis">
                                            <c:choose>
                                                <c:when test="${inquiryList[i-1].reported_user_name == null}">
                                                    삭제된 유저
                                                </c:when>
                                                <c:when test="${inquiryList[i-1].reported_user_name != null}">
                                                    ${inquiryList[i-1].reported_user_name}
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td class="overflow-hidden"
                                            style="text-overflow: ellipsis">${inquiryList[i-1].content}</td>
                                        <td class="overflow-hidden"
                                            style="text-overflow: ellipsis">
                                            <c:choose>
                                                <c:when test="${inquiryList[i-1]._answered == true}">답변</c:when>
                                                <c:when test="${inquiryList[i-1]._answered == false}">미답변</c:when>
                                            </c:choose>
                                        </td>
                                        <td class="overflow-hidden"
                                            style="text-overflow: ellipsis">${inquiryList[i-1].reg_date}</td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="location.href='/admin/inquiry_detail.do?inquiry_no=${inquiryList[i-1].inquiry_no}'">
                                                <i class="btn-icon-prepend" data-feather="search"></i>
                                                보기
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="if(confirm('${i}번째 문의를 삭제하시겠습니까?')){deleteInquiry(${inquiryList[i-1].inquiry_no});} else {return false;}">
                                                <i class="btn-icon-prepend" data-feather="x-square"></i>
                                                삭제
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
    function deleteInquiry(inquiry_no) {
        let data = {"inquiry_no": inquiry_no};
        $.ajax({
            type: 'POST',
            url: '/admin/inquiry_delete.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("문의 삭제 완료");
                window.location.reload();
            } else {
                alert("알 수 없는 오류 발생");
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

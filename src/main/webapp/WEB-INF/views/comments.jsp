<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-07-20
  Time: 오후 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    request.setAttribute("status1", request.getHeader("status"));
    request.setAttribute("status2", response.getHeader("status"));
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>회원 관리 - 댓글 내역</title>
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
                            <h6 class="card-title" style="font-size: x-large">댓글 내역</h6>
                            <div class="table-responsive">
                                <table id="dataTableExample" class="table">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>분류</th>
                                        <th>유저 / 아티스트 명</th>
                                        <th>댓글 내용</th>
                                        <th>등록 일자</th>
                                        <th>비공개 처리</th>
                                        <th>삭제</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="i" begin="1" end="${commentList.size()}">
                                    <tr>
                                        <td>${i}</td>
                                        <td>${commentList[i-1].type}</td>
                                        <td>${commentList[i-1].writer_name}</td>
                                        <td>
                                                ${commentList[i-1].content}
                                        </td>
                                        <td>${commentList[i-1].reg_date}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${commentList[i-1]._private == false}">
                                                    <button type="button"
                                                            class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                            onclick="if(confirm('정말 비공개 처리하시겠습니까?')){changeComment(${commentList[i-1].comment_no}, '${commentList[i-1].type}');} else {return false;}">
                                                        <i class="btn-icon-prepend" data-feather="trash"></i>
                                                        비공개
                                                    </button>
                                                </c:when>
                                                <c:when test="${commentList[i-1]._private == true}">
                                                    <button type="button"
                                                            class="btn btn-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                            onclick="if(confirm('정말 비공개 해제 처리하시겠습니까?')){changeComment(${commentList[i-1].comment_no}, '${commentList[i-1].type}');} else {return false;}">
                                                        <i class="btn-icon-prepend" data-feather="trash"></i>
                                                        비공개 해제
                                                    </button>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="if(confirm('정말 삭제하시겠습니까?')){deleteComment(${commentList[i-1].comment_no}, '${commentList[i-1].type}');} else {return false;}">
                                                <i class="btn-icon-prepend" data-feather="trash"></i>
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

<script>
    function deleteComment(comment_no, type) {
        const data = {"comment_no": comment_no, "type": type};
        $.ajax({
            type: 'POST',
            url: '/admin/comment/delete.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 1) {
                alert("댓글 삭제 완료");
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

    function changeComment(comment_no, type) {
        const data = {"comment_no": comment_no, "type": type};
        $.ajax({
            type: 'POST',
            url: '/admin/comment/private.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 1) {
                alert("댓글 비공개 완료");
                window.location.reload();
            } else if (result === 2) {
                alert("댓글 비공개 해제 완료");
                window.location.reload();
            } else {
                alert("알 수 없는 오류 발생");
                window.location.reload();
            }
        }).fail(function (error) {
            console.log(error);
            alert(error);
            window.location.reload();
        })
    }
</script>
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

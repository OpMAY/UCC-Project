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
    <title>회원 관리 - 사용자</title>
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
                    <li class="breadcrumb-item active" aria-current="page">사용자</li>
                </ol>
            </nav>

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large">사용자<a
                                    href="${pageContext.request.contextPath}/admin/artists.do"> 아티스트 </a></h6>
                            <div class="table-responsive">
                                <table id="dataTableExample" class="table">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>사용자 명</th>
                                        <th>소셜 로그인</th>
                                        <th>아티스트 전환</th>
                                        <th>생성 일자</th>
                                        <th>댓글 내역</th>
                                        <th>자세히 보기</th>
                                        <th>사용 정지</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="UserList" value="${UserList}"/>
                                    <c:forEach var="i" begin="1" end="${UserList.size()}">
                                    <tr>
                                        <td>${i}</td>
                                        <td>${UserList[i-1].name}</td>
                                        <td>${UserList[i-1].sns}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${UserList[i-1]._artist == false}">유저</c:when>
                                                <c:when test="${UserList[i-1]._artist == true}">아티스트</c:when>
                                            </c:choose>
                                        </td>
                                        <td>${UserList[i-1].reg_date}</td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0" onclick="location.href='/admin/comments.do?user_no=${UserList[i-1].user_no}'">
                                                <i class="btn-icon-prepend" data-feather="search"></i>
                                                보기
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0" onclick="location.href='/admin/user_detail.do?user_no=${UserList[i-1].user_no}'">
                                                <i class="btn-icon-prepend" data-feather="search"></i>
                                                보기
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    data-toggle="modal" data-target="#user_ban_modal">
                                                <i class="btn-icon-prepend" data-feather="x-square"></i>
                                                정지
                                            </button>
                                            <div class="modal fade" id="user_ban_modal" tabindex="-1" role="dialog"
                                                 aria-labelledby="user_ban_modal" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">회원 정지 관리</h5>
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                    aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="row justify-content-around">
                                                                <div class="label col-4">
                                                                    <label for="ban-start-date${i}" class="col-form-label">정지 시작 일</label>
                                                                </div>
                                                                <div class="label col-4">
                                                                    <label for="ban-end-date${i}" class="col-form-label">해제일</label>
                                                                </div>
                                                            </div>
                                                            <div class="row justify-content-around">
                                                                <div class="input-group date datepicker col-4 ban-date" id="ban-start-date${i}">
                                                                    <input type="text" class="form-control" onchange="test(this)" id="ban-start-date-text${i}"><span class="input-group-addon"><i data-feather="calendar"></i></span>
                                                                </div>
                                                                <div class="input-group date datepicker col-4 ban-date-after" id="ban-end-date${i}">
                                                                    <input type="text" disabled class="form-control" id="ban-end-date-text${i}"><span class="input-group-addon"><i data-feather="calendar"></i></span>
                                                                </div>
                                                            </div>
                                                            <div class="form-group" id="day-check-form">
                                                                <div class="label">
                                                                    <label for="day-check-form" class="col-form-label" style="font-size: 15px">정지 기간</label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <label class="form-check-label">
                                                                        <input type="radio" class="form-check-input" name="optionsRadios" id="ban-3days" value="option1">
                                                                        3일 정지
                                                                    </label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <label class="form-check-label">
                                                                        <input type="radio" class="form-check-input" name="optionsRadios" id="ban-7days" value="option1">
                                                                        7일 정지
                                                                    </label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <label class="form-check-label">
                                                                        <input type="radio" class="form-check-input" name="optionsRadios" id="ban-15days" value="option1">
                                                                        15일 정지
                                                                    </label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <label class="form-check-label">
                                                                        <input type="radio" class="form-check-input" name="optionsRadios" id="ban-30days" value="option1">
                                                                        30일 정지
                                                                    </label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <label class="form-check-label">
                                                                        <input type="radio" class="form-check-input" name="optionsRadios" id="ban-90days" value="option1">
                                                                        90일 정지
                                                                    </label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <label class="form-check-label">
                                                                        <input type="radio" class="form-check-input" name="optionsRadios" id="ban-perm" value="option1">
                                                                        영구 정지
                                                                    </label>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="ban-message-text"
                                                                       class="col-form-label" style="font-size: 15px">정지 사유</label>
                                                                <textarea class="form-control"
                                                                          id="ban-message-text" rows="8" style="line-height: 150%"></textarea>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-primary">보내기</button>
                                                            <button type="button" class="btn btn-secondary"
                                                                    data-dismiss="modal">취소
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
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
    function test(element){
        $('#ban-end-date-text1').val(
            element.value
        )
    }
</script>
</body>
</html>

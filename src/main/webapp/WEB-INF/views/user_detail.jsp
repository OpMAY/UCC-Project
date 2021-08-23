<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-07-20
  Time: 오후 2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                            <div class="row justify-content-center">
                                <div class="col-md-10">
                                    <div class="card" style="background-color: #FFFFFf; border-radius: 1.5%">
                                        <div class="row mt-3 justify-content-center">
                                            <div class="col-md-5">
                                                <label class="label" style="font-size: larger">
                                                    프로필 이미지
                                                </label>
                                                <div style="width: 480px; height: 320px; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
                                                     class="d-flex justify-content-center">
                                                    <img class="img-fluid" src="${User.profile_img}"
                                                         style="height: 100%"
                                                         onerror="this.src='https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/fan_main_img_basic.png'">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="artist-name" style="font-size: large">
                                                    사용자 명
                                                </label>
                                                <textarea class="form-control" id="artist-name" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${User.name}</textarea>
                                            </div>
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="user-sns" style="font-size: large">
                                                    소셜 로그인
                                                </label>
                                                <textarea class="form-control" id="user-sns" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${User.sns}</textarea>
                                            </div>
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="reg-date" style="font-size: large">
                                                    아티스트 전환
                                                </label>
                                                <textarea class="form-control" id="reg-date" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled><c:choose><c:when
                                                        test="${User._artist == true}">아티스트</c:when><c:when
                                                        test="${User._artist == false}">유저</c:when></c:choose></textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="bank-name" style="font-size: large">
                                                    생성 일자
                                                </label>
                                                <textarea class="form-control" id="bank-name" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${User.reg_date}</textarea>
                                            </div>
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="bank-owner" style="font-size: large">
                                                    총 후원 금액
                                                </label>
                                                <textarea class="form-control" id="bank-owner" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled><fmt:formatNumber value="${spon_amount}"
                                                                                     type="number"/>원</textarea>
                                            </div>
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="bank-account" style="font-size: large">
                                                    누적 정지 횟수
                                                </label>
                                                <textarea class="form-control" id="bank-account" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${penalty_num}</textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-center">
                                            <div class="col-md-5">
                                                <label class="label d-flex" for="hashtags" style="font-size: large">
                                                    정지 기간
                                                </label>
                                                <textarea class="form-control" id="hashtags" rows="1"
                                                          style="line-height: 150%; font-size: large;"
                                                          disabled><c:choose><c:when
                                                        test="${penalty == null}">-</c:when><c:when
                                                        test="${penalty != null}">${penalty.penalty_start_date}~${penalty.penalty_end_date}</c:when></c:choose></textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-4 mb-3 justify-content-around">
                                            <div class="col-md-6 justify-content-center d-flex">
                                                <button type="button" class="btn btn-outline-primary"
                                                        onclick="openWindowPopBan('/admin/penalty.do?user_no=${User.user_no}','회원 정지')">
                                                    사용 정지
                                                </button>
                                            </div>
                                            <div class="col-md-6 justify-content-center d-flex">
                                                <button class="btn btn-secondary"
                                                        onclick="location.href='/admin/users.do'">
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
        function openWindowPopBan(url, name) {
            let options = 'top=10, left=10, width=720, height=1040, status=1, scrollbars=1, resizable=1, menubar=0, fullscreen=0, location=0';
            window.open(url, name, options);
        }
    </script>
</body>
</html>

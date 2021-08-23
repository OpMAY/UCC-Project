<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-07-20
  Time: 오후 9:06
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
    <title>크라우드 관리 - 참여 아티스트 및 출품작</title>
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
                            <c:set var="Artist" value="${Artist}"/>
                            <div class="row justify-content-center">
                                <div class="col-md-10">
                                    <div class="card" style="background-color: #FFFFFf; border-radius: 1.5%">
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-5">
                                                <label class="label" style="font-size: larger">
                                                    프로필 이미지
                                                </label>
                                                <div style="width: 480px; height: 320px; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
                                                     class="d-flex justify-content-center">
                                                    <img class="img-fluid" src="${Artist.artist_profile_img}"
                                                         style="height: 100%">
                                                </div>
                                            </div>
                                            <div class="col-md-5">
                                                <label class="label" style="font-size: larger">
                                                    배경 이미지
                                                </label>
                                                <div style="width: 480px; height: 320px; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
                                                     class="d-flex justify-content-center">
                                                    <img class="img-fluid" src="${Artist.main_img}"
                                                         style="height: 100%">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row justify-content-center mt-3">
                                            <div class="col-md-11">
                                                <label class="label d-flex" for="artist-explain"
                                                       style="font-size: larger">
                                                    아티스트 소개
                                                </label>
                                                <textarea class="form-control" id="artist-explain" rows="10"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${Artist.explain}</textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="artist-name" style="font-size: large">
                                                    아티스트 명
                                                </label>
                                                <textarea class="form-control" id="artist-name" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${Artist.artist_name}</textarea>
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
                                                    전환 일자
                                                </label>
                                                <textarea class="form-control" id="reg-date" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${Artist.reg_date}</textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="bank-name" style="font-size: large">
                                                    은행 명
                                                </label>
                                                <textarea class="form-control" id="bank-name" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${Artist.bank_name}</textarea>
                                            </div>
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="bank-owner" style="font-size: large">
                                                    예금주 명
                                                </label>
                                                <textarea class="form-control" id="bank-owner" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${Artist.bank_owner}</textarea>
                                            </div>
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="bank-account" style="font-size: large">
                                                    계좌 번호
                                                </label>
                                                <textarea class="form-control" id="bank-account" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${Artist.bank_account}</textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="email" style="font-size: large">
                                                    이메일
                                                </label>
                                                <textarea class="form-control" id="email" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${Artist.email}</textarea>
                                            </div>
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="artist-phone" style="font-size: large">
                                                    연락처
                                                </label>
                                                <textarea class="form-control" id="artist-phone" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${Artist.artist_phone}</textarea>
                                            </div>
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="fankok-number"
                                                       style="font-size: large">
                                                    팬콕 수
                                                </label>
                                                <textarea class="form-control" id="fankok-number" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${Artist.fan_number}</textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-center">
                                            <div class="col-md-11">
                                                <label class="label d-flex" for="hashtags" style="font-size: large">
                                                    해시태그
                                                </label>
                                                <textarea class="form-control" id="hashtags" rows="3"
                                                          style="line-height: 150%; font-size: large;"
                                                          disabled><c:forEach var="i" begin="1"
                                                                              end="${Artist.hashtag_list.size()}">${Artist.hashtag_list[i-1]}  </c:forEach></textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-3 mb-3 justify-content-around">
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="visit-today" style="font-size: large">
                                                    금일 방문수
                                                </label>
                                                <textarea class="form-control" id="visit-today" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${Artist.visit_today}</textarea>
                                            </div>
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="ban-number" style="font-size: large">
                                                    경고 횟수
                                                </label>
                                                <textarea class="form-control" id="ban-number" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>0</textarea>
                                            </div>
                                            <div class="col-md-3">
                                                <label class="label d-flex" for="ban-date"
                                                       style="font-size: large">
                                                    사용 정지 기간
                                                </label>
                                                <textarea class="form-control" id="ban-date" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>-</textarea>
                                            </div>
                                        </div>
                                        <c:if test="${entry == null}">
                                            <div class="row mt-4 mb-3 justify-content-center">
                                                <div class="col-md-10" style="text-align: center">
                                                    <button class="btn btn-secondary"
                                                            onclick="location.href = '/admin/process_apply_list.do?loudsourcing_no=${loudsourcing_no}';">
                                                        돌아가기
                                                    </button>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <c:if test="${entry != null}">
                <div class="row">
                    <div class="col-md-8 grid-margin stretch-card offset-2">
                        <div class="card">
                            <div class="card-body">
                                <h6 class="card-title" style="font-size: x-large">출품작 상세</h6>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="row mt-3">
                                            <div class="col-md-4">
                                                <label class="label d-flex" for="entry-artist_name"
                                                       style="font-size: large">
                                                    작성자
                                                </label>
                                                <textarea class="form-control" id="entry-artist_name" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${entry.artist_name}</textarea>
                                            </div>
                                            <div class="col-md-4">
                                                <label class="label d-flex" for="entry-reg-date"
                                                       style="font-size: large">
                                                    작성 일자
                                                </label>
                                                <textarea class="form-control" id="entry-reg-date" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${entry.reg_date}</textarea>
                                            </div>
                                            <div class="col-md-4">
                                                <label class="label d-flex" for="entry-status" style="font-size: large">
                                                    크라우드 상태
                                                </label>
                                                <textarea class="form-control" id="entry-status" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled><c:choose><c:when
                                                        test="${status == 'process'}">진행 중</c:when><c:when
                                                        test="${status == 'judge'}">심사 중</c:when><c:when
                                                        test="${status == 'end'}">종료</c:when></c:choose></textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-3">
                                            <div class="col-md-4">
                                                <label class="label d-flex" for="entry-vote-number"
                                                       style="font-size: large">
                                                    투표 수
                                                </label>
                                                <textarea class="form-control" id="entry-vote-number" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${entry.vote_number}</textarea>
                                            </div>
                                            <div class="col-md-4">
                                                <label class="label d-flex" for="entry-comment-number"
                                                       style="font-size: large">
                                                    댓글 수
                                                </label>
                                                <textarea class="form-control" id="entry-comment-number" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${entry.comment_number}</textarea>
                                            </div>
                                            <div class="col-md-4">
                                                <label class="label d-flex" for="entry-visit" style="font-size: large">
                                                    조회수
                                                </label>
                                                <textarea class="form-control" id="entry-visit" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${entry.visit}</textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-3">
                                            <div class="col-md-6">
                                                <label class="label" style="font-size: larger">
                                                    동영상
                                                </label>
                                                <div style="position: center">
                                                    <video style="width: 100%; height: auto" width="720"
                                                           id="entry-vod-video" controls>
                                                        <source src="${entry.file}" type="video/mp4">
                                                    </video>
                                                </div>
                                            </div>
                                            <div class="col-md-6" id="entry-content-div">
                                                <label class="label d-flex" for="entry-content-vod"
                                                       style="font-size: large">
                                                    내용
                                                </label>
                                                <textarea class="form-control" id="entry-content-vod" rows="10"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${entry.content}</textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-4 mb-3 justify-content-center">
                                            <div class="col-md-10" style="text-align: center">
                                                <c:choose>
                                                    <c:when test="${status == 'process'}">
                                                        <button class="btn btn-secondary"
                                                                onclick="location.href = '/admin/process_apply_list.do?loudsourcing_no=${loudsourcing_no}';">
                                                            돌아가기
                                                        </button>
                                                    </c:when>
                                                    <c:when test="${status == 'judge'}">
                                                        <c:choose>
                                                            <c:when test="${preSelected == true}">
                                                                <button class="btn btn-secondary"
                                                                        onclick="location.href = '/admin/selected_entry.do?loudsourcing_no=${loudsourcing_no}';">
                                                                    돌아가기
                                                                </button>
                                                            </c:when>
                                                            <c:when test="${preSelected == false}">
                                                                <button class="btn btn-secondary"
                                                                        onclick="location.href = '/admin/unselected_entry.do?loudsourcing_no=${loudsourcing_no}';">
                                                                    돌아가기
                                                                </button>
                                                            </c:when>
                                                        </c:choose>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

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
</body>
</html>

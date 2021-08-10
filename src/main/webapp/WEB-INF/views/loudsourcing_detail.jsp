<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-07-20
  Time: 오후 9:06
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
    <title>크라우드 상세</title>
    <!-- core:css -->
    <link rel="stylesheet" href="../assets/vendors/core/core.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <link rel="stylesheet" href="../assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="../assets/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
    <link rel="stylesheet" href="../assets/vendors/jquery-tags-input/jquery.tagsinput.min.css">
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
                            <h6 class="card-title" style="font-size: x-large">크라우드 상세</h6>
                            <c:set var="Loudsourcing" value="${Loudsourcing}"/>
                            <c:set var="files" value="${files}"/>
                            <div class="row justify-content-center">
                                <div class="col-md-10">
                                    <div class="card" style="background-color: #FFFFFf; border-radius: 1.5%">
                                        <div class="row justify-content-around mt-3">
                                            <div class="col-md-5">
                                                <label class="label d-flex" for="loudsourcing-name"
                                                       style="font-size: larger">
                                                    공모전 이름
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-name" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          disabled>${Loudsourcing.name}</textarea>
                                            </div>
                                            <div class="col-md-5">
                                                <button class="btn btn-outline-primary mt-4"
                                                        style="float:right; font-size: large"
                                                        onclick="openWindowPop('/admin/loudsourcing_advertiser.do?loudsourcing_no=${Loudsourcing.loudsourcing_no}', '광고 발주자 상세정보')">
                                                    광고 발주자 상세정보 보기
                                                </button>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-5">
                                                <label class="label" style="font-size: larger">
                                                    공모전 사진
                                                </label>
                                                <div style="width: 540px; height: 800px; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
                                                     class="d-flex justify-content-center">
                                                    <img class="img-fluid" src="${Loudsourcing.img}"
                                                         onerror="this.src='https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/fan_main_img_basic.png'"
                                                         style="height: 100%; object-fit: contain;">
                                                </div>
                                            </div>
                                            <div class="col-md-5">
                                                <label class="label d-flex" for="loudsourcing-status"
                                                       style="font-size: larger">
                                                    상태
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-status" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          disabled><c:choose><c:when
                                                        test="${Loudsourcing.status == 'recruitment'}">모집</c:when><c:when
                                                        test="${Loudsourcing.status == 'process'}">진행</c:when><c:when
                                                        test="${Loudsourcing.status == 'judge'}">심사</c:when><c:when
                                                        test="${Loudsourcing.status == 'end'}">종료</c:when></c:choose></textarea>
                                                <label class="label d-flex" for="loudsourcing-host"
                                                       style="font-size: larger">
                                                    주최
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-host" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          disabled>${Loudsourcing.host}</textarea>
                                                <label class="label d-flex" for="loudsourcing-reward"
                                                       style="font-size: larger">
                                                    상금
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-reward" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          disabled><fmt:formatNumber value="${Loudsourcing.reward}"
                                                                                     type="number"/>원</textarea>
                                                <label class="label d-flex" for="loudsourcing-hashtag"
                                                       style="font-size: larger">
                                                    해시태그
                                                </label>
                                                <div>
                                                    <input hidden="hidden"/>
                                                    <input name="tags" id="loudsourcing-hashtag"
                                                           value="${Loudsourcing.hashtag}" disabled/>
                                                </div>
                                                <label class="label d-flex" for="total-recruit-number"
                                                       style="font-size: larger">
                                                    총 모집 인원
                                                </label>
                                                <textarea class="form-control" id="total-recruit-number" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          disabled>${Loudsourcing.total_recruitment_number}명</textarea>
                                                <label class="label d-flex" for="total-date"
                                                       style="font-size: larger">
                                                    총 기간
                                                </label>
                                                <textarea class="form-control" id="total-date" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          disabled>${Loudsourcing.start_date}~${Loudsourcing.end_date}</textarea>
                                                <label class="label d-flex" for="recruitment-date"
                                                       style="font-size: larger">
                                                    모집 기간
                                                </label>
                                                <textarea class="form-control" id="recruitment-date" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          disabled>${Loudsourcing.start_date}~${Loudsourcing.recruitment_end_date}</textarea>
                                                <label class="label d-flex" for="process-date"
                                                       style="font-size: larger">
                                                    진행 기간
                                                </label>
                                                <textarea class="form-control" id="process-date" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          disabled>${Loudsourcing.process_start_date}~${Loudsourcing.process_end_date}</textarea>
                                                <label class="label d-flex" for="judge-date"
                                                       style="font-size: larger">
                                                    심사 시작 일자
                                                </label>
                                                <textarea class="form-control" id="judge-date" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          disabled>${Loudsourcing.judge_date}</textarea>
                                                <label class="label d-flex" for="judge-date"
                                                       style="font-size: larger">
                                                    파일
                                                </label>
                                                <div style="background-color: #d9dadb">
                                                    <c:forEach var="i" begin="1" end="${files.size()}">
                                                        <div class="mt-1">
                                                            <a style="font-size: 16px; font-weight: bold; color: #6A6A6A; text-decoration: underline"
                                                               class="d-inline"
                                                               onmouseover="this.style.color='#6FAAF2'"
                                                               onmouseout="this.style.color='#6A6A6A'"
                                                               href="${files[i-1].url}">${files[i-1].name}</a>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-11">
                                                <label class="label d-flex" for="visit-today" style="font-size: large">
                                                    출품 안내
                                                </label>
                                                <textarea class="form-control" id="visit-today" rows="10"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${Loudsourcing.content}</textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-11">
                                                <label class="label d-flex" for="ban-number" style="font-size: large">
                                                    주의 사항
                                                </label>
                                                <textarea class="form-control" id="ban-number" rows="10"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${Loudsourcing.warning}</textarea>
                                            </div>
                                        </div>
                                        <c:choose>
                                            <c:when test="${Loudsourcing.status == 'recruitment'}">
                                                <div class="row mt-4 mb-3 justify-content-around">
                                                    <div class="col-md-6 justify-content-center d-flex">
                                                        <button class="btn btn-outline-primary"
                                                                onclick="location.href='/admin/loudsourcing_edit.do?loudsourcing_no=${Loudsourcing.loudsourcing_no}'">
                                                            수정
                                                        </button>
                                                    </div>
                                                    <div class="col-md-6 justify-content-center d-flex">
                                                        <button class="btn btn-secondary"
                                                                onclick="location.href = document.referrer;">
                                                            돌아가기
                                                        </button>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test="${Loudsourcing.status != 'recruitment'}">
                                                <div class="row mt-4 mb-3 justify-content-center">
                                                    <div class="col-md-10" style="text-align: center">
                                                        <button class="btn btn-secondary"
                                                                onclick="location.href = document.referrer;">
                                                            돌아가기
                                                        </button>
                                                    </div>
                                                </div>
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
    </div>


    <!-- core:js -->
    <script src="../assets/vendors/core/core.js"></script>
    <!-- endinject -->
    <!-- plugin js for this page -->
    <script src="../assets/vendors/chartjs/Chart.min.js"></script>
    <script src="../assets/vendors/jquery.flot/jquery.flot.js"></script>
    <script src="../assets/vendors/jquery.flot/jquery.flot.resize.js"></script>
    <script src="../assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
    <script src="../assets/vendors/jquery-tags-input/jquery.tagsinput.min.js"></script>
    <script src="../assets/vendors/apexcharts/apexcharts.min.js"></script>
    <script src="../assets/vendors/progressbar.js/progressbar.min.js"></script>
    <script src="../assets/vendors/datatables.net/jquery.dataTables.js"></script>
    <script src="../assets/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
    <!-- end plugin js for this page -->
    <!-- inject:js -->
    <script src="../assets/vendors/feather-icons/feather.min.js"></script>
    <script src="../assets/js/template.js"></script>
    <script src="../assets/js/tags-input.js"></script>
    <script src="../assets/js/inspect.js"></script>
    <!-- endinject -->
    <!-- custom js for this page -->
    <script src="../assets/js/dashboard.js"></script>
    <script src="../assets/js/datepicker.js"></script>
    <script src="../assets/js/data-table.js"></script>
    <!-- end custom js for this page -->
</body>
<script>
    function openWindowPop(url, name) {
        let options = 'top=10, left=10, width=720, height=1040, status=1, scrollbars=1, resizable=1, menubar=0, fullscreen=0, location=0';
        window.open(url, name, options);
    }
</script>
</html>

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
    <title>문의 관리</title>
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
                            <h6 class="card-title" style="font-size: x-large">문의 관리</h6>
                            <c:set var="inquiry" value="${inquiry}"/>
                            <c:if test="${inquiry.type == 'loudsourcing'}">
                                <div class="d-flex justify-content-center mb-2" style="color: #5a6780; font-size: large">
                                    크라우드 문의의 경우 개별 연락 후 답변하기를 누르면 해당 유저에게 문의 답변이 자동으로 전송됩니다.<br>
                                    기본 답변 내용 : 크라우드 문의 보내주신 "사용자/아티스트 명"님께 감사드립니다.<br>
                                    보내주신 문의 내용과 첨부파일을 확인했습니다.<br>
                                    몇 일 안으로 입력하신 연락처 혹은 이메일로 연락드리겠습니다.<br>
                                    앞으로도 많은 문의 보내주세요.<br>
                                </div>
                            </c:if>
                            <div class="row justify-content-center">
                                <div class="col-md-10">
                                    <div class="card" style="background-color: #FFFFFf; border-radius: 1.5%">
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-5">
                                                <label class="label d-flex" for="inquiry-user-name"
                                                       style="font-size: large">
                                                    작성자 명
                                                </label>
                                                <textarea class="form-control" id="inquiry-user-name" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${inquiry.user_name}</textarea>
                                            </div>
                                            <div class="col-md-5">
                                                <label class="label d-flex" for="reg-date" style="font-size: large">
                                                    등록 일자
                                                </label>
                                                <textarea class="form-control" id="reg-date" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${inquiry.reg_date}</textarea>
                                            </div>
                                        </div>
                                        <c:choose>
                                            <c:when test="${inquiry.type == 'loudsourcing'}">
                                                <div class="row mt-3 justify-content-around">
                                                    <div class="col-md-5">
                                                        <label class="label d-flex" for="inquiry-user-email"
                                                               style="font-size: large">
                                                            이메일
                                                        </label>
                                                        <textarea class="form-control" id="inquiry-user-email" rows="1"
                                                                  style="line-height: 150%; font-size: large"
                                                                  disabled>${inquiry.email}</textarea>
                                                    </div>
                                                    <div class="col-md-5">
                                                        <label class="label d-flex" for="inquiry-user-phone"
                                                               style="font-size: large">
                                                            연락처
                                                        </label>
                                                        <textarea class="form-control" id="inquiry-user-phone" rows="1"
                                                                  style="line-height: 150%; font-size: large"
                                                                  disabled>${inquiry.phone}</textarea>
                                                    </div>
                                                </div>
                                                <div class="row mt-3 justify-content-center">
                                                    <div class="col-md-11">
                                                        <label class="label d-flex" for="inquiry-loudsourcing-title"
                                                               style="font-size: large">
                                                            제목
                                                        </label>
                                                        <textarea class="form-control" id="inquiry-loudsourcing-title"
                                                                  rows="1"
                                                                  style="line-height: 150%; font-size: large"
                                                                  disabled>${inquiry.title}</textarea>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test="${inquiry.type == 'report'}">
                                                <div class="row mt-3 justify-content-center">
                                                    <div class="col-md-11">
                                                        <label class="label d-flex" for="inquiry-reported-user"
                                                               style="font-size: large">
                                                            신고 대상
                                                        </label>
                                                        <textarea class="form-control" id="inquiry-reported-user"
                                                                  rows="1"
                                                                  style="line-height: 150%; font-size: large"
                                                                  disabled>${inquiry.reported_user_name}</textarea>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test="${inquiry.type == 'normal'}">
                                                <div class="row mt-3 justify-content-center">
                                                    <div class="col-md-11">
                                                        <label class="label d-flex" for="inquiry-normal-title"
                                                               style="font-size: large">
                                                            제목
                                                        </label>
                                                        <textarea class="form-control" id="inquiry-normal-title"
                                                                  rows="1"
                                                                  style="line-height: 150%; font-size: large"
                                                                  disabled>${inquiry.title}</textarea>
                                                    </div>
                                                </div>
                                            </c:when>
                                        </c:choose>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-11">
                                                <label class="label d-flex" for="inquiry-content"
                                                       style="font-size: large">
                                                    문의 내역
                                                </label>
                                                <textarea class="form-control" id="inquiry-content" rows="10"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${inquiry.content}</textarea>
                                            </div>
                                        </div>
                                        <c:choose>
                                            <c:when test="${inquiry.type == 'loudsourcing'}">
                                                <div class="row justify-content-center mt-3">
                                                    <div class="col-md-11" style="background-color: #d9dadb">
                                                        <label class="label d-flex" for="inquiry-content"
                                                               style="font-size: large">
                                                            파일 목록
                                                        </label>
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
                                                <c:choose>
                                                    <c:when test="${inquiry._answered == true}">
                                                        <div class="row mt-3 justify-content-center">
                                                            <div class="col-md-11">
                                                                <label class="label d-flex" for="inquiry-loudsourcing-answer-date"
                                                                       style="font-size: large">
                                                                    답변 일자
                                                                </label>
                                                                <textarea class="form-control" id="inquiry-loudsourcing-answer-date"
                                                                          rows="1"
                                                                          style="line-height: 150%; font-size: large"
                                                                          disabled>${inquiry.answer_date}</textarea>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                </c:choose>
                                            </c:when>
                                            <c:when test="${inquiry.type == 'report'}">
                                                <c:choose>
                                                    <c:when test="${inquiry._answered == true}">
                                                        <div class="row mt-3 justify-content-center">
                                                            <div class="col-md-11">
                                                                <label class="label d-flex" for="inquiry-report-answer-date"
                                                                       style="font-size: large">
                                                                    답변 일자
                                                                </label>
                                                                <textarea class="form-control" id="inquiry-report-answer-date"
                                                                          rows="1"
                                                                          style="line-height: 150%; font-size: large"
                                                                          disabled>${inquiry.answer_date}</textarea>
                                                            </div>
                                                        </div>
                                                        <div class="row mt-3 justify-content-around">
                                                            <div class="col-md-11">
                                                                <label class="label d-flex"
                                                                       for="inquiry-report-answered-content"
                                                                       style="font-size: large">
                                                                    답변 내용
                                                                </label>
                                                                <textarea class="form-control"
                                                                          id="inquiry-report-answered-content" rows="10"
                                                                          style="line-height: 150%; font-size: large"
                                                                          disabled>${inquiry.answer_content}</textarea>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${inquiry._answered == false}">
                                                        <div class="row mt-3 justify-content-around">
                                                            <div class="col-md-11">
                                                                <label class="label d-flex"
                                                                       for="inquiry-report-answer-content"
                                                                       style="font-size: large">
                                                                    답변 내용
                                                                </label>
                                                                <textarea class="form-control"
                                                                          id="inquiry-report-answer-content" rows="10"
                                                                          style="line-height: 150%; font-size: large"
                                                                ></textarea>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                </c:choose>
                                            </c:when>
                                            <c:when test="${inquiry.type == 'normal'}">
                                                <c:choose>
                                                    <c:when test="${inquiry._answered == true}">
                                                        <div class="row mt-3 justify-content-center">
                                                            <div class="col-md-11">
                                                                <label class="label d-flex" for="inquiry-normal-answer-date"
                                                                       style="font-size: large">
                                                                    답변 일자
                                                                </label>
                                                                <textarea class="form-control" id="inquiry-normal-answer-date"
                                                                          rows="1"
                                                                          style="line-height: 150%; font-size: large"
                                                                          disabled>${inquiry.answer_date}</textarea>
                                                            </div>
                                                        </div>
                                                        <div class="row mt-3 justify-content-around">
                                                            <div class="col-md-11">
                                                                <label class="label d-flex"
                                                                       for="inquiry-normal-answered-content"
                                                                       style="font-size: large">
                                                                    답변 내용
                                                                </label>
                                                                <textarea class="form-control"
                                                                          id="inquiry-normal-answered-content" rows="10"
                                                                          style="line-height: 150%; font-size: large"
                                                                          disabled>${inquiry.answer_content}</textarea>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${inquiry._answered == false}">
                                                        <div class="row mt-3 justify-content-around">
                                                            <div class="col-md-11">
                                                                <label class="label d-flex"
                                                                       for="inquiry-normal-answer-content"
                                                                       style="font-size: large">
                                                                    답변 내용
                                                                </label>
                                                                <textarea class="form-control"
                                                                          id="inquiry-normal-answer-content" rows="10"
                                                                          style="line-height: 150%; font-size: large"
                                                                ></textarea>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                </c:choose>
                                            </c:when>
                                        </c:choose>
                                        <div class="row mt-4 mb-3 justify-content-around">
                                            <c:choose>
                                                <c:when test="${inquiry._answered == false}">
                                                    <div class="col-md-6 justify-content-center d-flex">
                                                        <button class="btn btn-outline-primary"
                                                                onclick="if(confirm('답변은 한번 입력 후 수정할 수 없습니다.\n정말 답변하시겠습니까?')){answerInquiry(${inquiry.inquiry_no}, '${inquiry.type}', ${inquiry.user_no})} else {return false;}">
                                                            답변하기
                                                        </button>
                                                    </div>
                                                    <div class="col-md-6 justify-content-center d-flex">
                                                        <c:choose>
                                                            <c:when test="${inquiry.type == 'loudsourcing'}">
                                                                <button class="btn btn-secondary"
                                                                        onclick="location.href='/admin/inquiry_loudsourcing.do'">
                                                                    돌아가기
                                                                </button>
                                                            </c:when>
                                                            <c:when test="${inquiry.type == 'report'}">
                                                                <button class="btn btn-secondary"
                                                                        onclick="location.href='/admin/inquiry_report.do'">
                                                                    돌아가기
                                                                </button>
                                                            </c:when>
                                                            <c:when test="${inquiry.type == 'normal'}">
                                                                <button class="btn btn-secondary"
                                                                        onclick="location.href='/admin/inquiry_normal.do'">
                                                                    돌아가기
                                                                </button>
                                                            </c:when>
                                                        </c:choose>
                                                    </div>
                                                </c:when>
                                                <c:when test="${inquiry._answered == true}">
                                                    <c:choose>
                                                        <c:when test="${inquiry.type == 'loudsourcing'}">
                                                            <button class="btn btn-secondary"
                                                                    onclick="location.href='/admin/inquiry_loudsourcing.do'">
                                                                돌아가기
                                                            </button>
                                                        </c:when>
                                                        <c:when test="${inquiry.type == 'report'}">
                                                            <button class="btn btn-secondary"
                                                                    onclick="location.href='/admin/inquiry_report.do'">
                                                                돌아가기
                                                            </button>
                                                        </c:when>
                                                        <c:when test="${inquiry.type == 'normal'}">
                                                            <button class="btn btn-secondary"
                                                                    onclick="location.href='/admin/inquiry_normal.do'">
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
        function answerInquiry(inquiry_no, type, user_no) {
            let inquiryData;
            if(type === 'loudsourcing'){
                inquiryData = {
                    "inquiry_no" : inquiry_no,
                    "type" : type,
                    "user_no" : user_no
                }
            } else if (type === 'report'){
                inquiryData = {
                    "inquiry_no" : inquiry_no,
                    "type" : type,
                    "answer_content" : $("#inquiry-report-answer-content").val(),
                    "user_no" : user_no
                }
            } else if(type === 'normal'){
                inquiryData = {
                    "inquiry_no" : inquiry_no,
                    "type" : type,
                    "answer_content" : $("#inquiry-normal-answer-content").val(),
                    "user_no" : user_no
                }
            } else {
                alert("잘못된 접근입니다.");
            }
            $.ajax({
                type: 'POST',
                url: '/admin/inquiry_answer.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(inquiryData)
            }).done(function (result) {
                console.log(result);
                if (result === 0) {
                    alert("답변을 완료했습니다.");
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

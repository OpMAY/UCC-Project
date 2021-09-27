<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>관리자 HOME</title>
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

            <div class="d-flex justify-content-between align-items-center flex-wrap grid-margin">
                <div>
                    <h4 class="mb-3 mb-md-0">UCC 관리자 페이지에 오신 것을 환영합니다.</h4>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-7 col-xl-8 stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-baseline mb-2">
                                <h6 class="card-title mb-3">미답변 문의 <i data-feather="alert-circle" data-toggle="tooltip" data-placement="right" data-animation="true" title="미답변 문의 중 가장 오래된 순으로 표시됩니다."></i></h6>
                            </div>
                            <div class="table-responsive">
                                <table id="index-inquiry-table" class="table table-hover mb-0" style="table-layout: fixed">
                                    <thead>
                                    <tr>
                                        <th style="width: 0.625rem">#</th>
                                        <th style="width: 2.5rem">작성자 명</th>
                                        <th style="width: 6.25rem">제목</th>
                                        <th style="width: 2.188rem">답변 상태</th>
                                        <th style="width: 5.625rem">생성일자</th>
                                        <th style="width: 3.438rem">문의 종류</th>
                                        <th style="width: 3.0rem">자세히 보기</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="i" begin="1" end="${InquiryList.size()}">
                                        <c:if test="${i<=6}">
                                            <tr>
                                                <td>${i}</td>
                                                <td class="overflow-hidden"
                                                    style="text-overflow: ellipsis">${InquiryList[i-1].user_name}</td>
                                                <td class="overflow-hidden"
                                                    style="text-overflow: ellipsis">${InquiryList[i-1].title}</td>
                                                <td class="overflow-hidden"
                                                    style="text-overflow: ellipsis">미답변
                                                </td>
                                                <td class="overflow-hidden"
                                                    style="text-overflow: ellipsis">${InquiryList[i-1].reg_date}</td>
                                                <td class="overflow-hidden"
                                                    style="text-overflow: ellipsis">
                                                    <c:choose>
                                                        <c:when test="${InquiryList[i-1].type == 'loudsourcing'}">크라우드 문의</c:when>
                                                        <c:when test="${InquiryList[i-1].type == 'report'}">신고 문의</c:when>
                                                        <c:when test="${InquiryList[i-1].type == 'normal'}">일반 문의</c:when>
                                                    </c:choose>
                                                </td>
                                                <td class="overflow-hidden"
                                                    style="text-overflow: ellipsis">
                                                    <button type="button"
                                                            class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                            onclick="location.href='/admin/inquiry_detail.do?inquiry_no=${InquiryList[i-1].inquiry_no}'">
                                                        <i class="btn-icon-prepend" data-feather="search"></i>
                                                        보기
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5 col-xl-4 stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-baseline">
                                <h6 class="card-title">가입 SNS <i data-feather="alert-circle" data-toggle="tooltip" data-placement="right" data-animation="true" title="설정한 날짜의 SNS 회원가입 인원 수가 표시됩니다."></i></h6>
                                <div class="input-group date datepicker dashboard-date mr-2 mb-2 mb-md-0 d-md-none d-xl-flex"
                                     id="index-date">
                                    <input type="text" class="form-control" name="index-sns-date" id="index-sns-date"><span
                                        class="input-group-addon bg-transparent"><i
                                        data-feather="calendar"></i></span>
                                </div>
                            </div>
                            <div class="table-responsive my-auto">
                                <table class="table table-hover mb-0" id="index-sns-table">
                                    <thead>
                                    <tr>
                                        <th class="pt-0">가입 방식</th>
                                        <th class="pt-0">가입 횟수</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>NAVER</td>
                                        <td>${NaverUser} 회</td>
                                    </tr>
                                    <tr>
                                        <td>KAKAO</td>
                                        <td>${KakaoUser} 회</td>
                                    </tr>
                                    <tr>
                                        <td>GOOGLE</td>
                                        <td>${GoogleUser} 회</td>
                                    </tr>
                                    <tr>
                                        <td>APPLE</td>
                                        <td>${AppleUser} 회</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- row -->

            <div class="row">
                <div class="col-lg-12 col-xl-12 stretch-card" style="padding-top: 20px">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-baseline mb-2">
                                <h6 class="card-title mb-4">최신 크라우드 현황 <i data-feather="alert-circle" data-toggle="tooltip" data-placement="right" data-animation="true" title="모집 상태의 크라우드가 최신 순으로 표시됩니다."></i></h6>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-hover mb-0" id="index-loudsourcing-table" style="table-layout: fixed">
                                    <thead>
                                    <tr>
                                        <th class="pt-0" style="width: 0.625rem" >#</th>
                                        <th class="pt-0" style="width: 9.375rem">공모전 이름</th>
                                        <th class="pt-0" style="width: 3.125rem">상태</th>
                                        <th class="pt-0" style="width: 3.125rem">참여인원</th>
                                        <th class="pt-0" style="width: 9.375rem">총 기간</th>
                                        <th class="pt-0" style="width: 9.375rem">모집 기간</th>
                                        <th class="pt-0" style="width: 9.375rem">진행 기간</th>
                                        <th class="pt-0" style="width: 4rem">참여 인원 리스트</th>
                                        <th class="pt-0" style="width: 4rem">자세히 보기</th>
                                        <th class="pt-0" style="width: 4rem">삭제</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="i" begin="1" end="${LoudsourcingList.size()}">
                                        <c:if test="${i<=6}">
                                            <tr>
                                                <td>${i}</td>
                                                <td class="overflow-hidden"
                                                    style="text-overflow: ellipsis">${LoudsourcingList[i-1].name}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${LoudsourcingList[i-1].status == 'recruitment'}">모집 중</c:when>
                                                        <c:when test="${LoudsourcingList[i-1].status == 'process'}">진행 중</c:when>
                                                        <c:when test="${LoudsourcingList[i-1].status == 'judge'}">심사 중</c:when>
                                                        <c:when test="${LoudsourcingList[i-1].status == 'end'}">종료</c:when>
                                                    </c:choose>
                                                </td>
                                                <td class="overflow-hidden"
                                                    style="text-overflow: ellipsis">${LoudsourcingList[i-1].applied_artist_num}/${LoudsourcingList[i-1].total_recruitment_number}</td>
                                                <td class="overflow-hidden"
                                                    style="text-overflow: ellipsis">${LoudsourcingList[i-1].start_date}
                                                    ~ ${LoudsourcingList[i-1].end_date}</td>
                                                <td class="overflow-hidden"
                                                    style="text-overflow: ellipsis">${LoudsourcingList[i-1].start_date}
                                                    ~ ${LoudsourcingList[i-1].recruitment_end_date}</td>
                                                <td class="overflow-hidden"
                                                    style="text-overflow: ellipsis">${LoudsourcingList[i-1].process_start_date}
                                                    ~ ${LoudsourcingList[i-1].process_end_date}</td>
                                                <td>
                                                    <button type="button"
                                                            class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                            onclick="location.href='/admin/recruitment_apply_list.do?loudsourcing_no=${LoudsourcingList[i-1].loudsourcing_no}'">
                                                        <i class="btn-icon-prepend" data-feather="search"></i>
                                                        보기
                                                    </button>
                                                </td>
                                                <td>
                                                    <button type="button"
                                                            class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                            onclick="location.href='/admin/loudsourcing_detail.do?loudsourcing_no=${LoudsourcingList[i-1].loudsourcing_no}'">
                                                        <i class="btn-icon-prepend" data-feather="search"></i>
                                                        보기
                                                    </button>
                                                </td>
                                                <td>
                                                    <button type="button"
                                                            class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                            onclick="if(confirm('정말 삭제 하시겠습니까?')){DeleteLoudSourcing(${LoudsourcingList[i-1].loudsourcing_no});} else {return false;}">
                                                        <i class="btn-icon-prepend" data-feather="trash"></i>
                                                        삭제
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- row -->

        </div>

        <!-- partial:partials/_footer.jsp -->
        <footer class="footer d-flex flex-column flex-md-row align-items-center justify-content-between">
            <p class="text-muted text-center text-md-left">Copyright © 2020 <a href="https://www.nobleui.com"
                                                                               target="_blank">NobleUI</a>. All rights
                reserved</p>
            <p class="text-muted text-center text-md-left mb-0 d-none d-md-block">Handcrafted With <i
                    class="mb-1 text-primary ml-1 icon-small" data-feather="heart"></i></p>
        </footer>
        <!-- partial -->

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
<script src="../assets/vendors/datatables.net/jquery.dataTables.js"></script>
<script src="../assets/vendors/progressbar.js/progressbar.min.js"></script>
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
<!-- end custom js for this page -->
<script>
    $(document).ready(function() {
        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        });
        $('#index-inquiry-table').DataTable({
            "paging" : false,
            "ordering" : false,
            "lengthChange" : false,
            "searching" : false,
            "scrollX" : false,
            "info" : false
        });
        $('#index-sns-table').DataTable({
            "paging" : false,
            "ordering" : false,
            "lengthChange" : false,
            "searching" : false,
            "scrollX" : false,
            "info" : false
        });
        $('#index-loudsourcing-table').DataTable({
            "paging" : false,
            "ordering" : false,
            "lengthChange" : false,
            "searching" : false,
            "scrollX" : false,
            "info" : false
        });
    });

    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    });
    function DeleteLoudSourcing(loudsourcing_no) {
        let data = {"loudsourcing_no": loudsourcing_no};
        $.ajax({
            type: 'POST',
            url: '/admin/delete_loudsourcing.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("해당 크라우드를 삭제했습니다.");
                window.location.reload();
            } else {
                alert("알 수 없는 오류가 발생했습니다. 관리자에게 문의해주세요.");
                window.location.reload();
            }
        }).fail(function (error) {
            console.log(error);
            alert(error);
            window.location.reload();
        })
    }

    $("#index-sns-date").on("change", function () {
        console.log("value : " + $("#index-sns-date").val());
        GetSnsInfo($("#index-sns-date").val());
    });

    function GetSnsInfo(date) {
        let data = {"date": date};
        $.ajax({
            type: 'POST',
            url: '/admin/main_sns.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log("result : " + result);
            if (result === 'Error') {
                alert("알 수 없는 오류가 발생했습니다. 관리자에게 문의해주세요.");
                window.location.reload();
            } else {
                console.log("naverUser : " + result.naverUser);
                console.log("kakaoUser : " + result.kakaoUser);
                console.log("googleUser : " + result.googleUser);
                console.log("appleUser : " + result.appleUser);
                let sns_table = document.getElementById('index-sns-table');
                sns_table.rows[1].cells[1].innerHTML = result.naverUser + " 회";
                sns_table.rows[2].cells[1].innerHTML = result.kakaoUser + " 회";
                sns_table.rows[3].cells[1].innerHTML = result.googleUser + " 회";
                sns_table.rows[4].cells[1].innerHTML = result.appleUser + " 회";
            }
        }).fail(function (error) {
            console.log(error);
            alert(error);
            window.location.reload();
        })
    }
</script>
</body>
</html>    
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
    <title>크라우드 관리 - 탈퇴 인원 출품작 리스트</title>
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
    <link rel="shortcut icon" href="/assets/images/ucc/ucc_favicon.png"/>

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
                    <li class="breadcrumb-item" style="color: #baa2fc">크라우드</li>
                    <li class="breadcrumb-item active" aria-current="page">탈퇴 인원 출품작 리스트</li>
                </ol>
            </nav>

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large">크라우드 - 탈퇴 인원 출품작 리스트
                            </h6>

                            <div class="table-responsive">
                                <table id="dataTableExample" class="table">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>썸네일</th>
                                        <th>출품작 업로드 일자</th>
                                        <th>자세히 보기</th>
                                        <th>삭제</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="i" begin="1" end="${entryList.size()}">
                                    <tr>
                                        <td>${i}</td>
                                        <td width="320px">
                                            <div style="width: 320px; height: 240px; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
                                                 class="d-flex justify-content-center">
                                                <img class="img" src="${entryList[i-1].thumbnail}"
                                                     onerror="this.src='https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/fan_main_img_basic.png'"
                                                     style="width: 100%; height: 100%; border-radius: 0%">
                                            </div>
                                        </td>
                                        <td>${entryList[i-1].reg_date}</td>
                                        <td >
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="location.href='/admin/loudsourcing/process/detail/entry/unknown.do?loudsourcing_no=${entryList[i-1].loudsourcing_no}&entry_no=${entryList[i-1].entry_no}'">
                                                <i class="btn-icon-prepend" data-feather="search"></i>
                                                보기
                                            </button>
                                        </td>
                                        <td >
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="if(confirm('${i}번째 출품작을 삭제합니다.\n삭제 후 복구는 불가능합니다.\n정말 삭제 하시겠습니까?')){deleteEntryFromList(${entryList[i-1].entry_no});} else {return false;}">
                                                <i class="btn-icon-prepend" data-feather="x-square"></i>
                                                삭제
                                            </button>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <div class="row mt-3 mb-3">
                                <div class="col-md-12">
                                    <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0" style="float: right" onclick="location.href='/admin/loudsourcing/process.do'">
                                        뒤로가기
                                    </button>
                                </div>
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
    function sendProcessAlarm(loudsourcing_no, artist_no) {
        let data = {"loudsourcing_no": loudsourcing_no, "artist_no": artist_no};
        $.ajax({
            type: 'POST',
            url: '/admin/send_process_start_message.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("진행 시작 알림 전송 완료");
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
    function deleteEntryFromList(entry_no) {
        let data = {"entry_no" : entry_no};
        $.ajax({
            type: 'POST',
            url: '/admin/entry_delete.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("출품작 삭제 완료");
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

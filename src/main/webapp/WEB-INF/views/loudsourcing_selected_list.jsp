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
    <title>크라우드 관리 - 선정 인원 리스트</title>
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
                    <li class="breadcrumb-item" style="color: #baa2fc">크라우드</li>
                    <li class="breadcrumb-item active" aria-current="page">선정 인원 리스트</li>
                </ol>
            </nav>

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large">크라우드 - 선정 인원 리스트
                                <c:if test="${artistList.size() > 0}">
                                    <button type="button"
                                            class="btn btn-outline-primary btn-icon-text"
                                            style="float: right; padding-top: 10px; padding-bottom: 10px; margin-bottom: 2px"
                                            onclick="if(confirm('해당 크라우드에서 선정된 아티스트 전원에게\n선정 알림 메세지를 전송합니다\n\n이미 한번 메세지를 전송했어도 다시 전송됩니다.\n\n메세지를 전송하시겠습니까?')){sendSelectedAlarmToAll(${artistList[0].loudsourcing_no});} else {return false;}">
                                        <i class="btn-icon-prepend" data-feather="send"></i>
                                        선정 알림 전체 전송
                                    </button>
                                </c:if>
                            </h6>

                            <div class="table-responsive">
                                <table id="dataTableExample" class="table">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>아티스트 명</th>
                                        <th>연락처</th>
                                        <th>이메일</th>
                                        <th>출품작 업로드 일자</th>
                                        <th>투표 갯수</th>
                                        <th>자세히 보기</th>
                                        <th>선정 알림 보내기</th>
                                        <th>탈락 처리</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="artistList" value="${artistList}"/>
                                    <c:forEach var="i" begin="1" end="${artistList.size()}">
                                    <tr>
                                        <td>${i}</td>
                                        <td>${artistList[i-1].artist_name}</td>
                                        <td>${artistList[i-1].phone}</td>
                                        <td>${artistList[i-1].email}</td>
                                        <td>${artistList[i-1].upload_date}</td>
                                        <td>${artistList[i-1].vote_number}</td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="location.href='/admin/entry_detail.do?loudsourcing_no=${artistList[i-1].loudsourcing_no}&artist_no=${artistList[i-1].artist_no}'">
                                                <i class="btn-icon-prepend" data-feather="search"></i>
                                                보기
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="if(confirm('아티스트 명 : ${artistList[i-1].artist_name}\n\n해당 유저에게 선정 알림을 보내시겠습니까?')){sendSelectedAlarm(${artistList[i-1].loudsourcing_no}, ${artistList[i-1].artist_no});} else {return false;}">
                                                <i class="btn-icon-prepend" data-feather="send"></i>
                                                보내기
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="if(confirm('아티스트 명 : ${artistList[i-1].artist_name}\n\n${i}번째 아티스트의 선정을 취소합니다.\n탈락 처리하여도 크라우드 종료 전까지는 다시 상태를 변동할 수 있습니다..\n탈락 처리 하시겠습니까?')){cancelSelectArtist(${artistList[i-1].loudsourcing_no}, ${artistList[i-1].artist_no});} else {return false;}">
                                                <i class="btn-icon-prepend" data-feather="x-square"></i>
                                                탈락
                                            </button>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <div class="row mt-3 mb-3">
                                <div class="col-md-12">
                                    <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0" style="float: right" onclick="location.href='/admin/loudsourcing_judge.do'">
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
    function cancelSelectArtist(loudsourcing_no, artist_no) {
        let data = {"loudsourcing_no": loudsourcing_no, "artist_no": artist_no};
        $.ajax({
            type: 'POST',
            url: '/admin/change_select.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("탈락 처리 되었습니다.");
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
    function sendSelectedAlarm(loudsourcing_no, artist_no) {
        let data = {"loudsourcing_no": loudsourcing_no, "artist_no": artist_no};
        $.ajax({
            type: 'POST',
            url: '/admin/send_selected_message.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("출품작 선정 알림 전송 완료");
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

    function sendSelectedAlarmToAll(loudsourcing_no) {
        const data = {"loudsourcing_no": loudsourcing_no};
        $.ajax({
            type: 'POST',
            url: '/admin/send_all_selected_message.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("출품작 선정 알림 전체 전송 완료");
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

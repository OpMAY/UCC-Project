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
    <title>크라우드 관리 - 탈퇴 회원 출품작 상세</title>
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

            </div>
                <div class="row">
                    <div class="col-md-8 grid-margin stretch-card offset-2">
                        <div class="card">
                            <div class="card-body">
                                <h6 class="card-title" style="font-size: x-large">출품작 상세</h6>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-5">
                                                <label class="label d-flex" for="entry-reg-date"
                                                       style="font-size: large">
                                                    작성 일자
                                                </label>
                                                <textarea class="form-control" id="entry-reg-date" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${entry.reg_date}</textarea>
                                            </div>
                                            <div class="col-md-5">
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
                                        <div class="row mt-4 mb-3 justify-content-around">
                                            <div class="col-md-5" style="text-align: center">
                                                <button class="btn btn-outline-primary"
                                                        onclick="if(confirm('해당 출품작을 삭제합니다.\n삭제 후 복구는 불가능합니다.\n정말 삭제 하시겠습니까?')){deleteEntry(${entry.entry_no});} else {return false;};">
                                                    삭제
                                                </button>
                                            </div>
                                            <div class="col-md-5" style="text-align: center">
                                                <button class="btn btn-secondary"
                                                        onclick="location.href = '/admin/unknown_entry_list.do?loudsourcing_no=${loudsourcing_no}';">
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
    function deleteEntry(entry_no) {
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
                location.href = '/admin/unknown_entry_list.do?loudsourcing_no=${loudsourcing_no}';
            } else {
                alert("알 수 없는 오류 발생");
                location.href = '/admin/unknown_entry_list.do?loudsourcing_no=${loudsourcing_no}';
            }
        }).fail(function (error) {
            alert(error);
            location.href = '/admin/unknown_entry_list.do?loudsourcing_no=${loudsourcing_no}';
        })
    }
</script>
</body>
</html>

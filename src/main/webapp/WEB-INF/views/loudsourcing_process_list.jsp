<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-07-22
  Time: 오후 7:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>크라우드 관리 - 진행</title>
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
                    <li class="breadcrumb-item" style="color: #baa2fc">크라우드 관리</li>
                    <li class="breadcrumb-item active" aria-current="page">진행</li>
                </ol>
            </nav>

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large"><a style="margin-right: 10px"
                                    href="${pageContext.request.contextPath}/admin/loudsourcing_recruitment.do">
                                모집 </a>진행 <a style="margin-left: 10px"
                                    href="${pageContext.request.contextPath}/admin/loudsourcing_judge.do">
                                심사 </a><a style="margin-left: 10px"
                                    href="${pageContext.request.contextPath}/admin/loudsourcing_end.do">
                                종료 </a><button type="button"
                                               class="btn btn-outline-primary btn-icon-text"
                                               style="float: right; padding-top: 10px; padding-bottom: 10px; margin-bottom: 2px"
                                               onclick="setLoudSourcingToJudge()">
                                <i class="btn-icon-prepend" data-feather="refresh-ccw"></i>
                                심사 수동 업데이트
                            </button><span style="float: right; margin-right: 3px" data-toggle="tooltip" data-placement="top" title="자동으로 심사 업데이트가 되지 않았을 때 클릭하면 됩니다. &#13; 단, 출품작이 하나도 등록되지 않았을 경우 심사로 업데이트 되지 않습니다."><i data-feather="help-circle"></i></span></h6>
                            <div class="table-responsive">
                                <table id="dataTableExample" class="table" style="table-layout: fixed">
                                    <thead>
                                    <tr>
                                        <th width="30px">#</th>
                                        <th width="150px">공모전 이름</th>
                                        <th width="50px">상태</th>
                                        <th width="110px">참여인원/최소 모집인원</th>
                                        <th width="150px">총 기간</th>
                                        <th width="150px">모집 기간</th>
                                        <th width="150px">진행 기간</th>
                                        <th width="80px">탈퇴 인원 출품작</th>
                                        <th width="80px">참여 인원 리스트</th>
                                        <th width="80px">자세히 보기</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="i" begin="1" end="${loudsourcingList.size()}">
                                        <tr>
                                            <td>
                                                ${i}
                                            </td>
                                            <td class="overflow-hidden" style="text-overflow: ellipsis">
                                                ${loudsourcingList[i-1].name}
                                            </td>
                                            <td>
                                                진행 중
                                            </td>
                                            <td>
                                                ${loudsourcingList[i-1].applied_artist_num}/${loudsourcingList[i-1].total_recruitment_number}
                                            </td>
                                            <td>
                                                ${loudsourcingList[i-1].start_date}~${loudsourcingList[i-1].end_date}
                                            </td>
                                            <td>
                                                ${loudsourcingList[i-1].start_date}~${loudsourcingList[i-1].recruitment_end_date}
                                            </td>
                                            <td>
                                                ${loudsourcingList[i-1].process_start_date}~${loudsourcingList[i-1].process_end_date}
                                            </td>
                                            <td>
                                                <button type="button"
                                                        class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                        onclick="location.href='/admin/unknown_entry_list.do?loudsourcing_no=${loudsourcingList[i-1].loudsourcing_no}'">
                                                    <i class="btn-icon-prepend" data-feather="search"></i>
                                                    보기
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button"
                                                        class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                        onclick="location.href='/admin/process_apply_list.do?loudsourcing_no=${loudsourcingList[i-1].loudsourcing_no}'">
                                                    <i class="btn-icon-prepend" data-feather="search"></i>
                                                    보기
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button"
                                                        class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                        onclick="location.href='/admin/loudsourcing_detail.do?loudsourcing_no=${loudsourcingList[i-1].loudsourcing_no}'">
                                                    <i class="btn-icon-prepend" data-feather="search"></i>
                                                    보기
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
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
    function setLoudSourcingToJudge(){
        $.ajax({
            type: 'POST',
            url: '/admin/loudsourcing/set/judge.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (result) {
            if (result === 1) {
                alert("진행 기간이 지난 크라우드를 심사로 업데이트 하였습니다.");
                window.location.reload();
            } else {
                alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                window.location.reload();
            }
        }).fail(function (error) {
            console.log(error);
            window.location.reload();
        })
    }
</script>
</body>
</html>

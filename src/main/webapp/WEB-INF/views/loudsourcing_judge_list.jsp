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
    <title>크라우드 관리 - 심사</title>
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
    <link rel="shortcut icon" href="/assets/images/favicon.png"/>
    <style>
        details {
            margin: 5px 0 10px;
        }

        details > summary {
            background: #fff;
            color: #007bff;
            padding: 10px;
            outline: 0;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.5s;
            text-align: left;
            box-shadow: 1px 1px 2px gray;
        }

        details > summary::-webkit-details-marker {
            background: #444;
            color: #fff;
            background-size: contain;
            transform: rotate3d(0, 0, 1, 90deg);
            transition: transform 0.25s;
        }

        details[open] > summary::-webkit-details-marker {
            transform: rotate3d(0, 0, 1, 180deg);
        }

        details[open] > summary {
            background: #fff;
            box-shadow: 1px 1px 2px gray;
        }

        details[open] > summary ~ * {
            animation: reveal 0.5s;
        }

        .tpt {
            background: #fff;
            color: #000;
            margin: 5px 0 10px;
            padding: 5px 10px;
            line-height: 25px;
            border-radius: 5px;
            box-shadow: 1px 1px 2px gray;
            white-space: pre-wrap;
        }

        @keyframes reveal {
            from {
                opacity: 0;
                transform: translate3d(0, -30px, 0);
            }
            to {
                opacity: 1;
                transform: translate3d(0, 0, 0);
            }
        }
    </style>
</head>
<body>
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
                    <li class="breadcrumb-item active" aria-current="page">심사</li>
                </ol>
            </nav>
            <details>
                <summary>선정 기준 보기</summary>
                <div class="tpt">
                    - 자동 선정 기준 -&#10;
                    1. 총 출품작의 투표 수 상위 약 15%의 출품작을 자동 1차 선정합니다. (투표 수가 0인 출품작은 자동 선정되지 않습니다)&#10;
                    2. 공정성을 위해 선정되지 않은 작품 중, 1차 선정작 리스트에서 가장 적은 투표 수와 같은 투표 수의 작품이 있다면 해당 작품도 2차 선정되어집니다.&#10;
                    3. 2차 선정 이후 종료 전까지 관리자와 광고주가 관리하시면 됩니다.&#10;
                    4. 종료 일자에 자동으로 크라우드는 종료되고 선정 및 탈락된 참여자에겐 자동으로 알림 메세지가 전송됩니다.&#10;
                    5. 심사 및 종료 자동 전환은 각각 0시 5분, 0시 10분에 적용되며 각 페이지에 있는 버튼으로 수동 전환도 가능합니다.&#10;
                    &#10;
                    ## 크라우드 진행 기간은 지났지만 심사로 전환되지 않는 경우&#10;
                    - 제출된 출품작 수가 0개일 때&#10;
                    - 제출된 출품작 모두 투표 수가 0일 때
                </div>
            </details>
            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large"><a style="margin-right: 10px"
                                                                                 href="${pageContext.request.contextPath}/admin/loudsourcing_recruitment.do">
                                모집 </a><a style="margin-right: 10px"
                                          href="${pageContext.request.contextPath}/admin/loudsourcing_process.do">
                                진행 </a>심사 <a style="margin-left: 10px"
                                             href="${pageContext.request.contextPath}/admin/loudsourcing_end.do">
                                종료 </a>
                                <button type="button"
                                        class="btn btn-outline-primary btn-icon-text"
                                        style="float: right; padding-top: 10px; padding-bottom: 10px; margin-bottom: 2px"
                                        onclick="setLoudSourcingToEnd()">
                                    <i class="btn-icon-prepend" data-feather="refresh-ccw"></i>
                                    심사 수동 업데이트
                                </button>
                                <span style="float: right; margin-right: 3px" data-toggle="tooltip" data-placement="top"
                                      title="기간이 지났음에도 자동으로 종료 업데이트가 되지 않았을 때 클릭하시면 됩니다."><i
                                        data-feather="help-circle"></i></span></h6>
                            </h6>
                            <div class="table-responsive">
                                <table id="dataTableExample" class="table" style="table-layout: fixed">
                                    <thead>
                                    <tr>
                                        <th width="30px">#</th>
                                        <th width="100px">공모전 이름</th>
                                        <th width="50px">상태</th>
                                        <th width="80px">자동 선정 인원</th>
                                        <th width="150px">총 기간</th>
                                        <th width="150px">진행 기간</th>
                                        <th width="80px">심사 시작 일자</th>
                                        <th width="80px">선정 인원 리스트</th>
                                        <th width="80px">탈락 인원 리스트</th>
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
                                                심사 중
                                            </td>
                                            <td>
                                                    ${loudsourcingList[i-1].selected_artist_num}명
                                            </td>
                                            <td>
                                                    ${loudsourcingList[i-1].start_date}~${loudsourcingList[i-1].end_date}
                                            </td>
                                            <td>
                                                    ${loudsourcingList[i-1].process_start_date}~${loudsourcingList[i-1].process_end_date}
                                            </td>
                                            <td>
                                                    ${loudsourcingList[i-1].judge_date}
                                            </td>
                                            <td>
                                                <button type="button"
                                                        class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                        onclick="location.href='/admin/selected_entry.do?loudsourcing_no=${loudsourcingList[i-1].loudsourcing_no}'">
                                                    <i class="btn-icon-prepend" data-feather="search"></i>
                                                    보기
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button"
                                                        class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                        onclick="location.href='/admin/unselected_entry.do?loudsourcing_no=${loudsourcingList[i-1].loudsourcing_no}'">
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
        <!-- partial:partials/_footer.jsp -->
        <jsp:include page="partials/_footer.jsp" flush="true"></jsp:include>
        <!-- partial -->
    </div>
</div>

<script>
    function openClosedToc() {
        if (document.getElementById('judge-explain').style.display === 'block') {
            document.getElementById('judge-explain').style.display = 'none';
            document.getElementById('toc-toggle').textContent = '설명 보기';
        } else {
            document.getElementById('judge-explain').style.display = 'block';
            document.getElementById('toc-toggle').textContent = '설명 닫기';
        }
    }

    function setLoudSourcingToEnd() {
        $.ajax({
            type: 'POST',
            url: '/admin/loudsourcing/set/end.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (result) {
            if (result === 1) {
                alert("심사 기간이 지난 크라우드를 종료로 업데이트 하였습니다.");
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

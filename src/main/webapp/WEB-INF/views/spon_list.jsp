<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-07-13
  Time: 오후 1:43
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
    <title>후원 관리</title>
    <!-- core:css -->
    <link rel="stylesheet" href="/assets/vendors/core/core.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <link rel="stylesheet" href="/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="/assets/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
    <link rel="stylesheet" href="/assets/vendors/mdi/css/materialdesignicons.min.css">
    <!-- end plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="/assets/fonts/feather-font/css/iconfont.css">
    <link rel="stylesheet" href="/assets/vendors/flag-icon-css/css/flag-icon.min.css">
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="/assets/css/demo_1/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="/assets/images/favicon.png"/>
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
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
                    <li class="breadcrumb-item" style="color: #baa2fc">후원 관리</li>
                </ol>
            </nav>

            <div class="row">
                <div class="col-md-3 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex justify-content-center">
                                <h6 class="card-title mb-0">${nowMonth} 총 예상 정산금</h6>
                            </div>
                            <div class="row" style="padding-top: 15px">
                                <div class="col-6 col-md-12 col-xl-6" style="border-right: 1px solid #f2f4f9;">
                                    <div class="align-items-baseline">
                                        <span class="d-block" style="font-size: 15px; text-align: center"><i
                                                class="mdi mdi-google-play"></i>Google PlayStore</span>
                                        <span class="mb-2 d-block"
                                              style="font-size: 20px; text-align: center"><fmt:formatNumber
                                                value="${androidIncome}" type="number"/>원</span>
                                    </div>
                                </div>
                                <div class="col-6 col-md-12 col-xl-6">
                                    <div class="align-items-baseline">
                                        <span class="d-block" style="font-size: 15px; text-align: center"><i
                                                class="mdi mdi-apple"></i>Apple Store</span>
                                        <span class="mb-2 d-block"
                                              style="font-size: 20px; text-align: center"><fmt:formatNumber
                                                value="${iosIncome}" type="number"/>원</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex justify-content-center">
                                <h6 class="card-title mb-0">확인 필요한 후원</h6>
                            </div>
                            <div class="row" style="padding-top: 15px">
                                <div class="col-4 col-md-12 col-xl-4" style="border-right: 1px solid #f2f4f9;">
                                    <div class="align-items-baseline">
                                        <span class="d-block" style="font-size: 12px; text-align: center">결제 실패</span>
                                        <span class="mb-2 d-block"
                                              style="font-size: 20px; text-align: center"><fmt:formatNumber
                                                value="${purchase_number}" type="number"/></span>
                                    </div>
                                </div>
                                <div class="col-4 col-md-12 col-xl-4">
                                    <div class="align-items-baseline">
                                        <span class="d-block" style="font-size: 12px; text-align: center">미승인</span>
                                        <span class="mb-2 d-block"
                                              style="font-size: 20px; text-align: center"><fmt:formatNumber
                                                value="${apply_number}" type="number"/></span>
                                    </div>
                                </div>
                                <div class="col-4 col-md-12 col-xl-4" style="border-left: 1px solid #f2f4f9;">
                                    <div class="align-items-baseline">
                                        <span class="d-block" style="font-size: 12px; text-align: center">미정산</span>
                                        <span class="mb-2 d-block"
                                              style="font-size: 20px; text-align: center"><fmt:formatNumber
                                                value="${send_number}" type="number"/></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-block">
                                <div class="spinner-border spinner-border-sm text-primary" role="status" id="Progress_Loading">
                                    <span class="sr-only">Loading...</span>
                                </div>
                                <h6 class="card-title mb-0" style="text-align: center">기간 별 예상 수익금</h6>
                            </div>
                            <div class="row" style="padding-top: 15px">
                                <div class="col-3 col-md-12 col-xl-3" style="border-right: 1px solid #f2f4f9;">
                                    <span class="d-block" style="text-align: center; font-size: 12px">시작 일</span>
                                    <div class="input-group date datepicker dashboard-date mr-2 mb-2 mb-md-0 d-md-none d-xl-flex"
                                         style="width: 100%"
                                         id="spon-send-start-date">
                                        <input type="text" class="form-control" name="input-spon-send-start-date"
                                               id="input-spon-send-start-date" readonly style="text-align: center"><span
                                            class="input-group-addon bg-transparent"><i
                                            data-feather="calendar"></i></span>
                                    </div>
                                </div>
                                <div class="col-3 col-md-12 col-xl-3">
                                    <span class="d-block" style="text-align: center; font-size: 12px">종료 일</span>
                                    <div class="input-group date datepicker dashboard-date mr-2 mb-2 mb-md-0 d-md-none d-xl-flex"
                                         style="width: 100%"
                                         id="spon-send-end-date">
                                        <input type="text" class="form-control" name="input-spon-send-end-date"
                                               id="input-spon-send-end-date" readonly style="text-align: center"><span
                                            class="input-group-addon bg-transparent"><i
                                            data-feather="calendar"></i></span>
                                    </div>
                                </div>
                                <div class="col-3 col-md-12 col-xl-3" style="border-left: 1px solid #f2f4f9;">
                                    <div class="align-items-baseline">
                                        <span class="d-block" style="font-size: 12px; text-align: center">플랫폼</span>
                                        <div class="row">
                                            <div class="col-md-10">
                                                <select class="form-control" id="platform-select"
                                                        style="color: grey; width: 100%"
                                                        onchange="document.getElementById('platform-select').style['color'] = 'black'">
                                                    <option selected disabled style="display: none" id="select-disable">
                                                        선택
                                                    </option>
                                                    <option style="color: black" id="select-all">전체</option>
                                                    <option style="color: black" id="select-google">Google PlayStore
                                                    </option>
                                                    <option style="color: black" id="select-apple">Apple Store</option>
                                                </select>
                                            </div>
                                            <div class="col-md-2">
                                                <button type="button"
                                                        class="btn btn-outline-primary btn-icon-text"
                                                        style="float: right;border-color: transparent; width: 100%; padding-left: 5px; padding-right: 20px; padding-bottom: 10px"
                                                        onclick="getTotalSendPrice()">
                                                    <i class="btn-icon-prepend" data-feather="search"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-3 col-md-12 col-xl-3" style="border-left: 1px solid #f2f4f9;">
                                    <div class="align-items-baseline">
                                        <span class="d-block" style="font-size: 12px; text-align: center">수익금</span>
                                        <span class="mb-2 d-block"
                                              style="font-size: 20px; text-align: center" id="resultPrice">선택 필요</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large"><c:choose><c:when
                                    test="${status == 'purchase'}">결제실패 <a style="margin-left: 10px"
                                                                           href="${pageContext.request.contextPath}/admin/spon/apply.do">미승인 </a><a
                                    style="margin-left: 10px"
                                    href="${pageContext.request.contextPath}/admin/spon/send.do">미정산 </a><a
                                    style="margin-left: 10px"
                                    href="${pageContext.request.contextPath}/admin/spon/complete.do">정산완료 </a><c:if
                                    test="${sponList.size() > 0}">
                                <button type="button"
                                        class="btn btn-outline-primary btn-icon-text"
                                        style="float: right; padding-top: 10px; padding-bottom: 10px; margin-bottom: 2px"
                                        onclick="alert('준비 중 입니다.')">
                                    <i class="btn-icon-prepend" data-feather="credit-card"></i>
                                    전체 결제 검증
                                </button>
                            </c:if>
                            </c:when><c:when test="${status == 'apply'}"><a style="margin-right: 10px"
                                                                            href="${pageContext.request.contextPath}/admin/spon/purchase.do">
                                결제실패 </a>미승인 <a style="margin-left: 10px"
                                                href="${pageContext.request.contextPath}/admin/spon/send.do">
                                미정산 </a><a style="margin-left: 10px"
                                           href="${pageContext.request.contextPath}/admin/spon/complete.do">
                                정산완료 </a></c:when><c:when
                                    test="${status == 'send'}"><a style="margin-right: 10px"
                                                                  href="${pageContext.request.contextPath}/admin/spon/purchase.do">
                                결제실패 </a><a style="margin-right: 10px"
                                            href="${pageContext.request.contextPath}/admin/spon/apply.do">
                                미승인 </a>미정산 <a style="margin-left: 10px"
                                               href="${pageContext.request.contextPath}/admin/spon/complete.do">
                                정산완료 </a></c:when><c:when
                                    test="${status == 'complete'}"><a style="margin-right: 10px"
                                                                      href="${pageContext.request.contextPath}/admin/spon/purchase.do">
                                결제실패 </a><a style="margin-right: 10px"
                                            href="${pageContext.request.contextPath}/admin/spon/apply.do">
                                미승인 </a><a style="margin-right: 10px"
                                           href="${pageContext.request.contextPath}/admin/spon/send.do">
                                미정산 </a>정산완료 </c:when></c:choose></h6>
                            <div class="table-responsive">
                                <table id="dataTableExample" class="table" style="table-layout: fixed">
                                    <thead>
                                    <tr>
                                        <th width="10px">#</th>
                                        <th width="30px">후원한 사용자</th>
                                        <th width="30px">후원받은 사용자</th>
                                        <th width="30px"><c:choose>
                                            <c:when test="${status == 'purchase'}">결제 실패 사유</c:when>
                                            <c:when test="${status != 'purchase'}">결제 여부</c:when>
                                        </c:choose></th>
                                        <th width="30px">승인 여부</th>
                                        <th width="30px">입금 여부</th>
                                        <th width="70px">금액</th>
                                        <th width="60px">후원 일자</th>
                                        <th width="40px">자세히 보기</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="sponList" value="${sponList}"/>
                                    <c:forEach var="i" begin="1" end="${sponList.size()}">
                                    <tr>
                                        <td>${i}</td>
                                        <td class="overflow-hidden"
                                            style="text-overflow: ellipsis">
                                                ${sponList[i-1].user_name}
                                        </td>
                                        <td class="overflow-hidden"
                                            style="text-overflow: ellipsis">
                                                ${sponList[i-1].artist_name}
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${sponList[i-1].verify_status == 0}">
                                                    O
                                                </c:when>
                                                <c:when test="${sponList[i-1].verify_status == 1}">
                                                    취소됨
                                                </c:when>
                                                <c:when test="${sponList[i-1].verify_status == 2}">
                                                    보류 중
                                                </c:when>
                                                <c:when test="${sponList[i-1].verify_status == 3}">
                                                    검증 대기 중
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${sponList[i-1].status == true}">
                                                    O
                                                </c:when>
                                                <c:when test="${sponList[i-1].status == false}">
                                                    X
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${sponList[i-1].purchase_status == true}">
                                                    O
                                                </c:when>
                                                <c:when test="${sponList[i-1].purchase_status == false}">
                                                    X
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                                ${sponList[i-1].price}
                                        </td>
                                        <td class="overflow-hidden"
                                            style="text-overflow: ellipsis">
                                                ${sponList[i-1].spon_date}
                                        </td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="location.href='/admin/spon/detail.do?spon_no=${sponList[i-1].spon_no}&prevStatus=${status}'">
                                                <i class="btn-icon-prepend" data-feather="search"></i>
                                                보기
                                            </button>
                                        </td>
                                    </tr>
                                    </c:forEach>
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
    $(document).ready(function () {
        $('#Progress_Loading').hide(); //첫 시작시 로딩바를 숨겨준다.
    }).ajaxStart(function () {
        $('#Progress_Loading').show(); //ajax실행시 로딩바를 보여준다.
    }).ajaxStop(function () {
        $('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
    });

    function getTotalSendPrice() {
        let selectedPlatform;
        switch ($('#platform-select').val()) {
            case "전체" : {
                selectedPlatform = "all";
            }
                break;
            case "Google PlayStore" : {
                selectedPlatform = "Android";
            }
                break;
            case "Apple Store" : {
                selectedPlatform = "IOS";
            }
                break;
            default :
                alert("플랫폼을 먼저 선택해주세요.");
                return false;
        }
        let start_date = $("input[name=input-spon-send-start-date]").val();
        let end_date = $("input[name=input-spon-send-end-date]").val();

        if (start_date > end_date) {
            alert("날짜를 확인해주세요.");
            return false;
        }

        let formData = {
            "start_date" : start_date,
            "end_date" : end_date,
            "platform" : selectedPlatform
        };
        $.ajax({
            type: 'POST',
            url: '/admin/spon/list/update.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(formData)
        }).done(function (result) {
            if (result === -1) {
                alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                window.location.reload();
            } else {
                let target = document.getElementById("resultPrice");
                target.innerText = result.toLocaleString() + "원"
            }
        }).fail(function (error) {
            console.log(error);
            window.location.href = '/admin/loudsourcing/detail.do?loudsourcing_no=${Loudsourcing.loudsourcing_no}';
        })

    }
</script>
</body>
</html>

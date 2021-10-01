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
    <title>배너광고 관리</title>
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
                    <li class="breadcrumb-item" style="color: #baa2fc">배너광고 관리</li>
                </ol>
            </nav>

            <div class="alert alert-primary" style="white-space: pre-wrap;" role="alert">
                배너 광고는 최대 4개까지 활성화 가능하며 먼저 활성화 한 순서대로 어플리케이션에 광고가 표시됩니다.&#10;
                표의 기본 정렬은 활성화 된 광고 -> 비 활성화된 광고 순서로 표시되며, 활성화 된 광고는 1-2-3-4 순으로 표시됩니다.
            </div>

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large">배너광고 관리
                                <button type="button" style="float: right; padding-top: 10px; padding-bottom: 10px; margin-bottom: 2px"
                                        class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                        onclick="openWindowPopBanner('/admin/banner_make.do', '배너광고 업로드')">
                                    <i class="btn-icon-prepend" data-feather="edit"></i>
                                    추가하기
                                </button>
                            </h6>
                            <div class="table-responsive">
                                <table id="dataTableExample" class="table" style="table-layout: fixed">
                                    <thead>
                                    <tr>
                                        <th width="10px">#</th>
                                        <th width="180px">배너이미지</th>
                                        <th width="70px">등록 일자</th>
                                        <th width="60px">상태 변경 하기</th>
                                        <th width="70px">변동 일자</th>
                                        <th width="50px">이미지 보기</th>
                                        <th width="50px">삭제</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="bannerList" value="${bannerList}"/>
                                    <c:forEach var="i" begin="1" end="${bannerList.size()}">
                                    <tr>
                                        <td>${i}</td>
                                        <td>
                                            <div style="width: 100%; height: 100%; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
                                                 class="d-flex justify-content-center">
                                                <img class="img" src="${bannerList[i-1].img}"
                                                     onerror="this.src='https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/fan_main_img_basic.png'"
                                                     style="width: 480px; height: 240px; border-radius: 0%">
                                            </div>
                                        </td>
                                        <td>
                                                ${bannerList[i-1].reg_date}
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${bannerList[i-1].status == true}">
                                                    <button type="button"
                                                            class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                            onclick="if(confirm('${i}번째 배너를 비활성화 시키겠습니까?')){disableBanner(${bannerList[i-1].banner_ad_no})} else {return false;}">
                                                        <i class="btn-icon-prepend" data-feather="x-square"></i>
                                                        비활성화
                                                    </button>
                                                </c:when>
                                                <c:when test="${bannerList[i-1].status == false}">
                                                    <button type="button"
                                                            class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                            onclick="if(confirm('${i}번째 배너를 활성화 시키겠습니까?')){activeBanner(${bannerList[i-1].banner_ad_no})} else {return false;}">
                                                        <i class="btn-icon-prepend" data-feather="check"></i>
                                                        활성화
                                                    </button>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                                ${bannerList[i-1].revise_date}
                                        </td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="openWindowPopBanner('/admin/banner_detail.do?banner_no=${bannerList[i-1].banner_ad_no}', '배너 광고 상세보기')">
                                                <i class="btn-icon-prepend" data-feather="search"></i>
                                                보기
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="if(confirm('${i}번째 배너를 삭제하시겠습니까?')){deleteBanner(${bannerList[i-1].banner_ad_no});} else {return false;}">
                                                <i class="btn-icon-prepend" data-feather="x-square"></i>
                                                삭제
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
    function openWindowPopBanner(url, name) {
        let options = 'top=10, left=10, width=720, height=820, status=1, scrollbars=1, resizable=1, menubar=0, fullscreen=0, location=0';
        window.open(url, name, options);
    }

    function deleteBanner(banner_no) {
        let data = {"banner_ad_no": banner_no};
        $.ajax({
            type: 'POST',
            url: '/admin/banner_delete.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("배너 삭제 완료");
                window.location.reload();
            } else if (result === 2) {
                alert("최소 하나 이상의 활성화 된 배너가 존재해야 합니다.\n삭제가 불가능합니다.");
            } else {
                alert("알 수 없는 오류 발생. 관리자에게 문의하세요.");
                window.location.reload();
            }
        }).fail(function (error) {
            alert(error);
            window.location.reload();
        })
    }

    function activeBanner(banner_no) {
        let data = {"banner_ad_no": banner_no};
        $.ajax({
            type: 'POST',
            url: '/admin/banner_active.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("배너가 활성화 되었습니다.");
                window.location.reload();
            } else if (result === 2) {
                alert("배너 활성화는 최대 4개까지 가능합니다.");
            } else {
                alert("알 수 없는 오류 발생. 관리자에게 문의하세요.");
                window.location.reload();
            }
        }).fail(function (error) {
            alert(error);
            window.location.reload();
        })
    }

    function disableBanner(banner_no) {
        let data = {"banner_ad_no": banner_no};
        $.ajax({
            type: 'POST',
            url: '/admin/banner_disable.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("배너가 비활성화 되었습니다.");
                window.location.reload();
            } else if (result === 2) {
                alert("최소 하나 이상의 배너가 활성화 되어있어야 합니다.");
            } else {
                alert("알 수 없는 오류 발생. 관리자에게 문의하세요.");
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

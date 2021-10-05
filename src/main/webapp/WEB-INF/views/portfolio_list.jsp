<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-07-21
  Time: 오후 4:46
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
    <title>회원 관리 - 포트폴리오</title>
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
                    <li class="breadcrumb-item" style="color: #baa2fc">회원관리</li>
                    <li class="breadcrumb-item active" aria-current="page">아티스트</li>
                    <li class="breadcrumb-item active" aria-current="page">포트폴리오</li>
                </ol>
            </nav>

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large"><c:choose><c:when
                                    test="${type == 'all'}">포트폴리오 <a style="margin-left: 10px" href="${pageContext.request.contextPath}/admin/portfolio.do?artist_no=${artist_no}&type=vod">VOD </a><a style="margin-left: 10px" href="${pageContext.request.contextPath}/admin/portfolio.do?artist_no=${artist_no}&type=image">이미지 </a><a style="margin-left: 10px" href="${pageContext.request.contextPath}/admin/portfolio.do?artist_no=${artist_no}&type=file">파일 </a>
                            </c:when><c:when test="${type == 'vod'}"><a style="margin-right: 10px"
                                    href="${pageContext.request.contextPath}/admin/portfolio.do?artist_no=${artist_no}&type=all">
                                포트폴리오 </a>VOD <a style="margin-left: 10px"
                                    href="${pageContext.request.contextPath}/admin/portfolio.do?artist_no=${artist_no}&type=image">
                                이미지 </a><a style="margin-left: 10px"
                                    href="${pageContext.request.contextPath}/admin/portfolio.do?artist_no=${artist_no}&type=file">
                                파일 </a></c:when><c:when
                                    test="${type == 'image'}"><a style="margin-right: 10px"
                                    href="${pageContext.request.contextPath}/admin/portfolio.do?artist_no=${artist_no}&type=all">
                                포트폴리오 </a><a style="margin-right: 10px"
                                    href="${pageContext.request.contextPath}/admin/portfolio.do?artist_no=${artist_no}&type=vod">
                                VOD </a>이미지 <a style="margin-left: 10px"
                                    href="${pageContext.request.contextPath}/admin/portfolio.do?artist_no=${artist_no}&type=file">
                                파일 </a></c:when><c:when
                                    test="${type == 'file'}"><a style="margin-right: 10px"
                                    href="${pageContext.request.contextPath}/admin/portfolio.do?artist_no=${artist_no}&type=all">
                                포트폴리오 </a><a style="margin-right: 10px"
                                    href="${pageContext.request.contextPath}/admin/portfolio.do?artist_no=${artist_no}&type=vod">
                                VOD </a><a style="margin-right: 10px"
                                    href="${pageContext.request.contextPath}/admin/portfolio.do?artist_no=${artist_no}&type=image">
                                이미지 </a>파일 </c:when></c:choose></h6>
                            <div class="table-responsive">
                                <table id="dataTableExample" class="table">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>종류</th>
                                        <th>작성자</th>
                                        <th>제목</th>
                                        <th width="360px">썸네일</th>
                                        <th>수정 일자</th>
                                        <th>자세히 보기</th>
                                        <th>삭제</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="i" begin="1" end="${portfolioList.size()}">
                                    <tr>
                                        <td>
                                                ${i}
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${portfolioList[i-1].type == 'vod'}">VOD</c:when>
                                                <c:when test="${portfolioList[i-1].type == 'image'}">이미지</c:when>
                                                <c:when test="${portfolioList[i-1].type == 'text'}">텍스트</c:when>
                                                <c:when test="${portfolioList[i-1].type == 'file'}">파일</c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                                ${portfolioList[i-1].artist_name}
                                        </td>
                                        <td>
                                                ${portfolioList[i-1].title}
                                        </td>
                                        <td>
                                            <div style="width: 360px; height: 100%; overflow: hidden; border: 1px black solid"
                                                 class="d-flex">
                                                <img class="img" src="${portfolioList[i-1].thumbnail}"
                                                     onerror="this.src='https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/fan_main_img_basic.png'"
                                                     style="width: 360px; height: 240px; border-radius: 0%">
                                            </div>
                                        </td>
                                        <td>
                                                ${portfolioList[i-1].revise_date}
                                        </td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="location.href='/admin/portfolio_detail.do?portfolio_no=${portfolioList[i-1].portfolio_no}'">
                                                <i class="btn-icon-prepend" data-feather="search"></i>
                                                보기
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button"
                                                    class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0"
                                                    onclick="if(confirm('${i}번째 포트폴리오를 삭제합니다.\n삭제 후 복구는 불가능합니다.\n정말 삭제 하시겠습니까?')){DeletePortfolio(${portfolioList[i-1].portfolio_no});} else {return false;}">
                                                <i class="btn-icon-prepend" data-feather="trash"></i>
                                                삭제
                                            </button>
                                        </td>
                                        </c:forEach>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="row mt-3 mb-3">
                                <div class="col-md-12">
                                    <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0" style="float: right" onclick="location.href='/admin/artists.do'">
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
    function DeletePortfolio(portfolio_no) {
        const data = {"portfolio_no": portfolio_no};
        $.ajax({
            type: 'POST',
            url: '/admin/portfolio_delete.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("포트폴리오 삭제 완료");
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

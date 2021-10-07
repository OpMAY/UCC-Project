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
    <title>회원 관리 - 포트폴리오 - <c:choose><c:when test="${portfolio.type == 'vod'}">VOD</c:when><c:when
            test="${portfolio.type == 'image'}">이미지</c:when><c:when
            test="${portfolio.type == 'text'}">텍스트</c:when><c:when
            test="${portfolio.type == 'file'}">파일</c:when></c:choose>
    </title>
    <!-- core:css -->
    <link rel="stylesheet" href="/assets/vendors/core/core.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <link rel="stylesheet" href="/assets/vendors/owl.carousel/owl.carousel.min.css">
    <link rel="stylesheet" href="/assets/vendors/owl.carousel/owl.theme.default.min.css">
    <link rel="stylesheet" href="/assets/vendors/animate.css/animate.min.css">
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
</head>
<body onload="setHeightValue()">
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
                <div class="col-md-8 grid-margin stretch-card offset-2">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large">포트폴리오 - <c:choose><c:when
                                    test="${portfolio.type == 'vod'}">VOD</c:when><c:when
                                    test="${portfolio.type == 'image'}">이미지</c:when><c:when
                                    test="${portfolio.type == 'text'}">텍스트</c:when><c:when
                                    test="${portfolio.type == 'file'}">파일</c:when></c:choose></h6>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="row mt-3">
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="type" style="font-size: large">
                                                종류
                                            </label>
                                            <textarea class="form-control" id="type" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled><c:choose><c:when
                                                    test="${portfolio.type == 'vod'}">VOD</c:when><c:when
                                                    test="${portfolio.type == 'image'}">이미지</c:when><c:when
                                                    test="${portfolio.type == 'text'}">텍스트</c:when><c:when
                                                    test="${portfolio.type == 'file'}">파일</c:when></c:choose></textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="artist_name" style="font-size: large">
                                                작성자
                                            </label>
                                            <textarea class="form-control" id="artist_name" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${portfolio.artist_name}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="revise-date" style="font-size: large">
                                                수정 일자
                                            </label>
                                            <textarea class="form-control" id="revise-date" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${portfolio.revise_date}</textarea>
                                        </div>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="like-number" style="font-size: large">
                                                좋아요 수
                                            </label>
                                            <textarea class="form-control" id="like-number" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${portfolio.like_number}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="comment-number"
                                                   style="font-size: large">
                                                댓글 수
                                            </label>
                                            <textarea class="form-control" id="comment-number" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${portfolio.comment_number}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="visit" style="font-size: large">
                                                조회수
                                            </label>
                                            <textarea class="form-control" id="visit" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${portfolio.visit_number}</textarea>
                                        </div>
                                    </div>
                                    <div class="row mt-3">
                                        <c:choose>
                                            <c:when test="${portfolio.type == 'vod'}">
                                                <div class="col-md-6">
                                                    <label class="label" style="font-size: larger">
                                                        동영상
                                                    </label>
                                                    <div style="position: center; border: 1px solid black">
                                                        <video style="width: 100%; height: 520px" width="720"
                                                               id="vod-video" controls>
                                                            <source src="${portfolio.file}" type="video/mp4">
                                                        </video>
                                                    </div>
                                                </div>
                                                <div class="col-md-6" id="title-div">
                                                    <label class="label d-flex" for="title-vod"
                                                           style="font-size: large" id="title-label">
                                                        제목
                                                    </label>
                                                    <textarea class="form-control" id="title-vod" rows="1"
                                                              style="line-height: 150%; font-size: large"
                                                              disabled>${portfolio.title}</textarea>
                                                    <label class="label d-flex mt-3" for="content-vod"
                                                           style="font-size: large">
                                                        내용
                                                    </label>
                                                    <textarea class="form-control" id="content-vod" rows="15"
                                                              style="line-height: 150%; font-size: large"
                                                              disabled>${portfolio.content}</textarea>
                                                </div>
                                            </c:when>
                                            <c:when test="${portfolio.type == 'image'}">
                                                <div class="col-md-6">
                                                    <label class="label" style="font-size: larger">
                                                        이미지
                                                    </label>
                                                    <div class="owl-carousel owl-theme owl-animate-css">
                                                        <c:forEach var="i" begin="1"
                                                                   end="${portfolio.image_list.size()}">
                                                            <div class="item">
                                                                <div style="background-image: url('${portfolio.image_list[i-1]}'); height: 400px; background-size: contain; background-repeat: no-repeat; background-position: center"></div>
                                                                    <%--<img src="${portfolio.image_list[i-1]}"
                                                                         alt="item-image"
                                                                         style="width: 100%; height: auto;">--%>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="label d-flex" for="title-image"
                                                           style="font-size: large">
                                                        제목
                                                    </label>
                                                    <textarea class="form-control" id="title-image" rows="1"
                                                              style="line-height: 150%; font-size: large"
                                                              disabled>${portfolio.title}</textarea>
                                                    <label class="label d-flex mt-3" for="content-image"
                                                           style="font-size: large">
                                                        내용
                                                    </label>
                                                    <textarea class="form-control" id="content-image" rows="12"
                                                              style="line-height: 150%; font-size: large"
                                                              disabled>${portfolio.content}</textarea>
                                                </div>
                                            </c:when>
                                            <c:when test="${portfolio.type == 'text'}">
                                                <div class="col-md-12">
                                                    <label class="label d-flex" for="title-text"
                                                           style="font-size: large">
                                                        제목
                                                    </label>
                                                    <textarea class="form-control" id="title-text" rows="1"
                                                              style="line-height: 150%; font-size: large"
                                                              disabled>${portfolio.title}</textarea>
                                                    <label class="label d-flex mt-3" for="content-text"
                                                           style="font-size: large">
                                                        내용
                                                    </label>
                                                    <textarea class="form-control" id="content-text" rows="8"
                                                              style="line-height: 150%; font-size: large"
                                                              disabled>${portfolio.content}</textarea>
                                                </div>
                                            </c:when>
                                            <c:when test="${portfolio.type == 'file'}">
                                                <div class="col-md-12">
                                                    <label class="label d-flex" for="title-file"
                                                           style="font-size: large">
                                                        제목
                                                    </label>
                                                    <textarea class="form-control" id="title-file" rows="1"
                                                              style="line-height: 150%; font-size: large"
                                                              disabled>${portfolio.title}</textarea>
                                                    <label class="label d-flex mt-3" for="content-file"
                                                           style="font-size: large">
                                                        내용
                                                    </label>
                                                    <textarea class="form-control" id="content-file" rows="8"
                                                              style="line-height: 150%; font-size: large"
                                                              disabled>${portfolio.content}</textarea>
                                                </div>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <c:if test="${portfolio.type == 'file'}">
                                        <div class="row mt-4">
                                            <div class="col-md-12">
                                                <label class="label d-flex" style="font-size: large">
                                                    업로드 된 파일
                                                </label>
                                                <div style="background-color: #d9dadb">
                                                    <c:forEach var="i" begin="1" end="${portfolio.file_list.size()}">
                                                        <div class="mt-1">
                                                            <a style="font-size: 16px; font-weight: bold; color: #6A6A6A; text-decoration: underline"
                                                               class="d-inline"
                                                               onmouseover="this.style.color='#6FAAF2'"
                                                               onmouseout="this.style.color='#6A6A6A'"
                                                               href="${portfolio.file_list[i-1].url}">${portfolio.file_list[i-1].name}</a>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="row mt-4 mb-3 justify-content-around">
                                        <div class="col-md-6 justify-content-center d-flex">
                                            <button class="btn btn-outline-primary" style="width : 50%; height: 150%"
                                                    onclick="if(confirm('포트폴리오를 삭제합니다.\n삭제 후 복구는 불가능합니다\n정말 삭제 하시겠습니까?')){DeletePortfolio(${portfolio.portfolio_no});} else {return false;}">
                                                삭제
                                            </button>
                                        </div>
                                        <div class="col-md-6 justify-content-center d-flex">
                                            <button class="btn btn-secondary" style="width : 50%; height: 150%"
                                                    onclick="window.location = document.referrer;">
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
    <script src="/assets/vendors/owl.carousel/owl.carousel.min.js"></script>
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
    <script src="/assets/js/carousel.js"></script>
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
                    window.location = document.referrer;
                } else {
                    alert("알 수 없는 오류 발생");
                    window.location = document.referrer;
                }
            }).fail(function (error) {
                alert(error);
                window.location = document.referrer;
            })
        }

        function setHeightValue() {
            const title_div_height = $('#title-div').height();
            const title_height = $('#title-label').height();
            const videoElement = $('#vod-video');

            console.log(title_div_height);
            console.log(title_height);
            console.log(title_div_height - title_height);
            videoElement.height(title_div_height - title_height);
            console.log(videoElement.height());
        }

    </script>
</body>
</html>

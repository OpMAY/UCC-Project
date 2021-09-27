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
    <title>회원 관리 - 게시글
    </title>
    <!-- core:css -->
    <link rel="stylesheet" href="../assets/vendors/core/core.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <link rel="stylesheet" href="../assets/vendors/owl.carousel/owl.carousel.min.css">
    <link rel="stylesheet" href="../assets/vendors/owl.carousel/owl.theme.default.min.css">
    <link rel="stylesheet" href="../assets/vendors/animate.css/animate.min.css">
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
                            <h6 class="card-title" style="font-size: x-large">게시글</h6>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="row mt-3">
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="artist_name" style="font-size: large">
                                                작성자
                                            </label>
                                            <textarea class="form-control" id="artist_name" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${board.artist_name}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="revise-date" style="font-size: large">
                                                수정 일자
                                            </label>
                                            <textarea class="form-control" id="revise-date" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${board.revise_date}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="spon-amount" style="font-size: large">
                                                후원 금액
                                            </label>
                                            <textarea class="form-control" id="spon-amount" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${board.spon_amount}원</textarea>
                                        </div>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="like-number" style="font-size: large">
                                                좋아요 수
                                            </label>
                                            <textarea class="form-control" id="like-number" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${board.like_number}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="comment-number"
                                                   style="font-size: large">
                                                댓글 수
                                            </label>
                                            <textarea class="form-control" id="comment-number" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${board.comment_number}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="visit" style="font-size: large">
                                                조회수
                                            </label>
                                            <textarea class="form-control" id="visit" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${board.visit_number}</textarea>
                                        </div>
                                    </div>
                                    <div class="row mt-3">
                                                <div class="col-md-12">
                                                    <label class="label d-flex" for="title-image"
                                                           style="font-size: large">
                                                        제목
                                                    </label>
                                                    <textarea class="form-control" id="title-image" rows="1"
                                                              style="line-height: 150%; font-size: large"
                                                              disabled>${board.title}</textarea>
                                                    <label class="label d-flex mt-3"
                                                           style="font-size: large">
                                                        내용
                                                    </label>
                                                    <div class="col-md-12" style="border: 1px solid; height: 500px; overflow: scroll; padding-top: 10px">
                                                        ${board.content}
                                                    </div>
                                                    <%--<textarea class="form-control" id="content-image" rows="12"
                                                              style="line-height: 150%; font-size: large"
                                                              disabled>${board.content}</textarea>--%>
                                                </div>
                                    </div>
                                    <div class="row mt-4 mb-3 justify-content-around">
                                        <div class="col-md-6 justify-content-center d-flex">
                                            <button class="btn btn-outline-primary" style="width : 50%; height: 150%"
                                                    onclick="if(confirm('정말 삭제 하시겠습니까?')){DeleteBoard(${board.board_no});} else {return false;}">
                                                >
                                                삭제
                                            </button>
                                        </div>
                                        <div class="col-md-6 justify-content-center d-flex">
                                            <button class="btn btn-secondary" style="width : 50%; height: 150%" onclick="window.location = document.referrer;">
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
    <script src="../assets/vendors/owl.carousel/owl.carousel.min.js"></script>
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
    <script src="../assets/js/carousel.js"></script>
    <!-- end custom js for this page -->
    <script>
        function DeleteBoard(board_no) {
            const data = {"board_no": board_no};
            $.ajax({
                type: 'POST',
                url: '/admin/board_delete.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                console.log(result);
                if (result === 0) {
                    alert("게시글 삭제 완료");
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
    </script>
</body>
</html>

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
    <title>배너 광고 제작하기</title>
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
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="main-wrapper">
    <div class="page-wrapper">
        <jsp:include page="partials/_popupnavbar.jsp" flush="true"></jsp:include>
        <div class="page-content">

            <nav class="page-breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item" style="color: #baa2fc">배너 광고 제작하기</li>
                </ol>
            </nav>

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="row justify-content-center">
                        <div class="col-md-12">
                            <div class="card" style="background-color: #FFFFFf; border-radius: 1.5%">
                                <div class="card-body">
                                    <h6 class="card-title" style="font-size: x-large">배너 광고 제작하기
                                    </h6>
                                    <form id="banner-makeForm">
                                        <div class="col-md-12">
                                            <label class="label" style="font-size: larger">
                                                배너 사진 - [사진을 추가 및 변경하려면 사진을 클릭하세요.]
                                            </label>
                                            <input type="file" id="banner-img" name="img" accept="image/*"
                                                   hidden/>
                                            <div style="height: 400px; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
                                                 class="d-flex justify-content-center " id="banner-image-container">
                                                <img class="img-fluid" src=""
                                                     onerror="this.src='https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/fan_main_img_basic.png'"
                                                     onclick="$('#banner-img').click()"
                                                     style="height: 100%; object-fit: contain; cursor: pointer">
                                            </div>
                                            <script>

                                                $("#banner-img").change(function () {
                                                    if (this.files && this.files[0]) {
                                                        const reader = new FileReader;
                                                        reader.onload = function (data) {
                                                            console.log(data);
                                                            $("#banner-image-container img").attr("src", data.target.result);
                                                        }
                                                        reader.readAsDataURL(this.files[0]);
                                                    }
                                                })
                                            </script>
                                        </div>
                                    </form>
                                    <div class="col-md-12 mt-4 mb-3 justify-content-around d-flex">
                                        <button type="button" class="btn btn-outline-primary" style="float: right; width: 25%; height: 150%"
                                                onclick="if(confirm('배너 광고를 제작하시겠습니까?')){makeBanner()}else {return false;}">
                                            제작
                                        </button>
                                        <button type="button" class="btn btn-secondary" style="float: right; width: 25%; height: 150%"
                                                onclick="window.close()">
                                            닫기
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
    function makeBanner() {
        let form = $("#banner-makeForm")[0];
        let formData = new FormData(form);
        if (document.getElementById("banner-img").files.length !== 0) {
            $.ajax({
                type: 'POST',
                enctype: 'multipart/form-data',
                url: '/admin/banner_make_post.do',
                contentType: false,
                processData: false,
                data: formData
            }).done(function (result) {
                console.log(result);
                if (result === 0) {
                    alert("제작이 완료되었습니다.");
                    window.close();
                    refreshParent();
                } else {
                    alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    window.location.reload();
                }
            }).fail(function (error) {
                console.log(error);
                window.location.reload();
            })
        } else {
            alert("사진이 추가되지 않았습니다.");
        }
    }

    function refreshParent() {
        window.opener.location.reload();
    }
</script>
</body>
</html>

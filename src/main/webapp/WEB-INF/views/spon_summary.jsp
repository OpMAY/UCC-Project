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
    <title>아티스트 정산</title>
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
    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://MomentJS.com/downloads/moment.js"></script>
</head>
<body onload="copyDate()">
<div class="main-wrapper">
    <div class="page-wrapper" style="margin-left: 0; width: 100%">
        <jsp:include page="partials/_popupnavbar.jsp" flush="true"></jsp:include>
        <div class="page-content">


            <div class="row">
                <div class="col-md-12 grid-margin">
                    <div class="row justify-content-center">
                        <div class="col-md-12">
                            <div class="card" style="background-color: #FFFFFf; border-radius: 1.5%">
                                <div class="card-body">
                                    <h6 class="card-title" style="font-size: x-large">아티스트 정산
                                    </h6>
                                    <div class="row">
                                        <div class="col-md-12">

                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">

                                        </div>
                                        <div class="col-md-6">

                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="label d-flex" for="artist_bank_info" style="font-size: large">
                                                아티스트 계좌정보
                                            </label>
                                            <textarea class="form-control" id="artist_bank_info" rows="1"
                                                      style="line-height: 150%; font-size: large" placeholder="미승인"
                                                      disabled>${artist.bank_name} ${artist.bank_account} ${artist.bank_owner}</textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label class="label d-flex" for="artist_phone" style="font-size: large">
                                                아티스트 전화번호
                                            </label>
                                            <textarea class="form-control" id="artist_phone" rows="1"
                                                      style="line-height: 150%; font-size: large" placeholder="미승인"
                                                      disabled>${artist.phone}</textarea>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="label d-flex" for="artist_email" style="font-size: large">
                                                아티스트 계좌정보
                                            </label>
                                            <textarea class="form-control" id="artist_email" rows="1"
                                                      style="line-height: 150%; font-size: large" placeholder="미승인"
                                                      disabled>${artist.bank_name} ${artist.bank_account} ${artist.bank_owner}}</textarea>
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
<script src="/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
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
<script src="/assets/js/moment.js"></script>
<!-- end custom js for this page -->
<script>
    function refreshParent() {
        window.opener.location.reload();
    }

    $(document).ready(function () {
        if(${Artist})

        document.getElementById("artist-phone").innerText = phoneFormatter("${Artist.artist_phone}");
    });

    function phoneFormatter(num) {
        let formatNum = '';
        try {
            if (num.length === 11) {
                formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
            } else if (num.length === 8) {
                formatNum = num.replace(/(\d{4})(\d{4})/, '$1-$2');
            } else if (num.length === 10) {
                formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
            } else {
                if (num.indexOf('02') === 0) {
                    formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
                } else {
                    formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
                }
            }
        } catch (e) {
            formatNum = num;
            console.log(e);
        }
        return formatNum;
    }
</script>
</body>
</html>

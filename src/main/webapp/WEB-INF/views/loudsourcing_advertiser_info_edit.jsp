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
    <title>광고 발주자 상세정보</title>
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
</head>
<body>
<div class="main-wrapper">
    <div class="page-wrapper">
        <jsp:include page="partials/_popupnavbar.jsp" flush="true"></jsp:include>
        <div class="page-content">


            <div class="row">
                <div class="col-md-12 grid-margin">
                    <form id="LoudSourcingAdvertiserForm">
                        <div class="row justify-content-center">
                            <div class="col-md-12">
                                <div class="card" style="background-color: #FFFFFf; border-radius: 1.5%">
                                    <div class="card-body">
                                        <h6 class="card-title" style="font-size: x-large">광고 발주자 상세정보</h6>
                                        <div class="col-md-12 mt-3 justify-content-center">
                                            <label class="label" style="font-size: larger" for="advertiser-name">
                                                광고 발주자 명
                                            </label>
                                            <textarea class="form-control" id="advertiser-name" rows="1"
                                                      style="line-height: 150%; font-size: large" placeholder="광고 발주자 명을 입력하세요."
                                            >${LoudSourcing.advertiser_name}</textarea>
                                        </div>
                                        <div class="col-md-12 mt-3 justify-content-center">
                                            <label class="label" style="font-size: larger">
                                                연락처
                                            </label>
                                            <textarea class="form-control" id="advertiser-phone" rows="1"
                                                      style="line-height: 150%; font-size: large" placeholder="광고주 연락처를 입력하세요."
                                            >${LoudSourcing.advertiser_phone}</textarea>
                                        </div>
                                        <div class="col-md-12 mt-3 justify-content-center">
                                            <label class="label" style="font-size: larger" for="advertiser-email">
                                                이메일
                                            </label>
                                            <textarea class="form-control" id="advertiser-email" rows="1"
                                                      style="line-height: 150%; font-size: large" placeholder="광고주 이메일을 입력하세요."
                                            >${LoudSourcing.advertiser_email}</textarea>
                                        </div>
                                        <div class="col-md-12 mt-3 justify-content-around">
                                            <label class="label" style="font-size: larger"
                                                   for="advertiser-bank-name">
                                                은행 명
                                            </label>
                                            <textarea class="form-control" id="advertiser-bank-name" rows="1"
                                                      style="line-height: 150%; font-size: large" placeholder="광고주 은행 명을 입력하세요."
                                            >${LoudSourcing.advertiser_bank_name}</textarea>
                                        </div>
                                        <div class="col-md-12 mt-3 justify-content-center">
                                            <label class="label" style="font-size: larger"
                                                   for="advertiser-bank-owner">
                                                예금주 명
                                            </label>
                                            <textarea class="form-control" id="advertiser-bank-owner" rows="1"
                                                      style="line-height: 150%; font-size: large" placeholder="광고주 은행 예금주 명을 입력하세요."
                                            >${LoudSourcing.advertiser_bank_owner}</textarea>
                                        </div>
                                        <div class="col-md-12 mt-3 justify-content-center">
                                            <label class="label" style="font-size: larger"
                                                   for="advertiser-bank-account">
                                                계좌번호
                                            </label>
                                            <textarea class="form-control" id="advertiser-bank-account" rows="1"
                                                      style="line-height: 150%; font-size: large" placeholder="광고주 은행 계좌번호를 입력하세요."
                                            >${LoudSourcing.advertiser_bank_account}</textarea>
                                        </div>
                                        <div class="col-md-12 mt-3 justify-content-around">
                                            <label class="label" style="font-size: larger" for="advertiser-regdate">
                                                등록 일자
                                            </label>
                                            <textarea class="form-control" id="advertiser-regdate" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${LoudSourcing.reg_date}</textarea>
                                        </div>
                                        <div class="col-md-12 mt-3 justify-content-center">
                                            <label class="label" style="font-size: larger" for="advertiser-reward">
                                                상금
                                            </label>
                                            <textarea class="form-control" id="advertiser-reward" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled><fmt:formatNumber value="${LoudSourcing.reward}"
                                                                                 type="number"/>원</textarea>
                                        </div>
                                        <div class="col-md-12 mt-4 mb-3 justify-content-around d-flex">
                                            <button type="button" class="btn btn-outline-primary" style="float: right; width: 25%; height: 150%"
                                                    onclick="editLoudSourcingAdvertiser()">
                                                수정완료
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
                    </form>

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
    function editLoudSourcingAdvertiser() {
        let loudsourcing_no = ${LoudSourcing.loudsourcing_no};
        let advertiserData = {
            "loudsourcing_no": loudsourcing_no,
            "advertiser_name": $("#advertiser-name").val(),
            "advertiser_phone": $("#advertiser-phone").val(),
            "advertiser_email": $("#advertiser-email").val(),
            "advertiser_bank_name": $("#advertiser-bank-name").val(),
            "advertiser_bank_account": $("#advertiser-bank-account").val(),
            "advertiser_bank_owner": $("#advertiser-bank-owner").val()
        };
        $.ajax({
            type: 'POST',
            url: '/admin/advertiser_edit_post.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(advertiserData)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("수정이 완료되었습니다.");
                window.location.href = '/admin/loudsourcing/advertiser/detail.do?loudsourcing_no=' + loudsourcing_no;
            } else {
                alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                window.reload();
            }
        }).fail(function (error) {
            console.log(error);
            window.reload();
        })
    }
</script>
</body>
</html>

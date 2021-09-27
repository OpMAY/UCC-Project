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
    <title>유저 정지 관리</title>
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
    <script src="https://MomentJS.com/downloads/moment.js"></script>
</head>
<body onload="copyDate()">
<div class="main-wrapper">
    <div class="page-wrapper">
        <jsp:include page="partials/_popupnavbar.jsp" flush="true"></jsp:include>
        <div class="page-content">

            <nav class="page-breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item" style="color: #baa2fc">유저 정지 관리</li>
                </ol>
            </nav>

            <div class="row">
                <div class="col-md-12 grid-margin">
                    <div class="row justify-content-center">
                        <div class="col-md-12">
                            <div class="card" style="background-color: #FFFFFf; border-radius: 1.5%">
                                <div class="card-body">
                                    <h6 class="card-title" style="font-size: x-large">유저 정지 관리
                                    </h6>
                                    <div class="row justify-content-around">
                                        <div class="col-md-10 mt-3 justify-content-around">
                                            <label class="label" style="font-size: larger" for="penalty-user-name">
                                                유저 이름
                                            </label>
                                            <textarea class="form-control" id="penalty-user-name" rows="1"
                                                      style="line-height: 150%; font-size: large" disabled
                                            >${user.name}</textarea>
                                        </div>
                                        <div class="col-md-10 mt-3 justify-content-around">
                                            <label class="label" style="font-size: larger" for="penalty-user-artist">
                                                유저 / 아티스트 여부
                                            </label>
                                            <textarea class="form-control" id="penalty-user-artist" rows="1"
                                                      style="line-height: 150%; font-size: large" disabled
                                            ><c:choose><c:when test="${user._artist == false}">유저</c:when><c:when
                                                    test="${user._artist == true}">아티스트</c:when></c:choose></textarea>
                                        </div>
                                        <c:if test="${user._artist == true}">
                                            <div class="col-md-10 mt-3 justify-content-around">
                                                <label class="label" style="font-size: larger"
                                                       for="penalty-artist-name">
                                                    아티스트 이름
                                                </label>
                                                <textarea class="form-control" id="penalty-artist-name" rows="1"
                                                          style="line-height: 150%; font-size: large" disabled
                                                >${artist_name}</textarea>
                                            </div>
                                        </c:if>
                                        <div class="col-md-10 mt-3">
                                            <label class="label" style="font-size: larger">
                                                시작 날짜
                                            </label>
                                            <div class="input-group date datepicker" id="ban-start-date">
                                                <input type="text" class="form-control"
                                                       name="penalty-start-date"
                                                       onchange="copyDate()"><span
                                                    class="input-group-addon"><i
                                                    data-feather="calendar"></i></span>
                                            </div>
                                        </div>
                                        <div class="col-md-10 mt-3">
                                            <label class="label" style="font-size: larger" for="penalty-end-date">
                                                해제 일
                                            </label>
                                            <textarea class="form-control" id="penalty-end-date" rows="1"
                                                      style="line-height: 150%; font-size: large" disabled
                                            ></textarea>
                                        </div>
                                        <div class="col-md-10 mt-3">
                                            <label class="label" style="font-size: larger">
                                                기간
                                            </label>
                                            <div class="form-group">
                                                <div class="form-check">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" name="ban-days"
                                                               id="3-days" value="3" onchange="copyDate()">
                                                        3일
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" name="ban-days"
                                                               id="7-days" value="7" onchange="copyDate()">
                                                        7일
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" name="ban-days"
                                                               id="15-days" value="15" onchange="copyDate()">
                                                        15일
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" name="ban-days"
                                                               id="30-days" value="30" onchange="copyDate()">
                                                        30일
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" name="ban-days"
                                                               id="90-days" value="90" onchange="copyDate()">
                                                        90일
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" name="ban-days"
                                                               id="forever" value="forever" onchange="copyDate()">
                                                        영구 정지
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-10 mt-3 justify-content-center">
                                            <label class="label" style="font-size: larger"
                                                   for="penalty-reason">
                                                정지 사유
                                            </label>
                                            <textarea class="form-control" id="penalty-reason" rows="5"
                                                      style="line-height: 150%; font-size: large"
                                            ></textarea>
                                        </div>
                                        <div class="col-md-10 mt-4 mb-3 justify-content-around d-flex">
                                            <button type="button" class="btn btn-outline-primary" style="float: right; width: 25%; height: 150%"
                                                    onclick="if(confirm('이 유저를 정지하시겠습니까?')){banUser(${user.user_no})} else {return false;}">
                                                정지
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
<script src="../assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
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
<script src="../assets/js/moment.js"></script>
<!-- end custom js for this page -->
<script>
    function copyDate() {
        let dateString = $('input[name="penalty-start-date"]').val();
        let date = new Date(dateString);
        let checked_value = $('input[name="ban-days"]:checked').val();
        if (checked_value === 'forever') {
            document.getElementById("penalty-end-date").value = '영구 정지';
        } else if (checked_value === undefined) {
            document.getElementById("penalty-end-date").value = '정지 일수를 선택해주세요.';
        } else {
            let dayChanged = date.getDate() + parseInt(checked_value) + 1;
            let endDate = new Date(date.getFullYear(), date.getMonth(), dayChanged);
            let endDateString = dateFormat(endDate);
            document.getElementById("penalty-end-date").value = endDateString;
        }
    }

    function dateFormat(date) {
        let month = date.getMonth() + 1;
        let day = date.getDate();

        month = month >= 10 ? month : '0' + month;
        day = day >= 10 ? day : '0' + day;

        return date.getFullYear() + '-' + month + '-' + day;
    }

    function banUser(user_no) {
        if (!inspection("penalty-reason", "penalty_reason")) {
            return false;
        } else if ($('input[name="ban-days"]:checked').val() == undefined){
            alert("정지 일수를 선택해주세요.");
            return false;
        }
        let days = $('input[name="ban-days"]:checked').val();
        let penalty_days;
        if (days === 'forever') {
            penalty_days = 0;
        } else if (days === null) {
            alert("정지 일수를 선택해주세요.");
        } else {
            penalty_days = days;
        }
        let penaltyData = {
            "user_no": user_no,
            "penalty_start_date": $('input[name="penalty-start-date"]').val(),
            "penalty_end_date": $("#penalty-end-date").val(),
            "penalty_days": penalty_days,
            "penalty_reason": $("#penalty-reason").val()
        };
        $.ajax({
            type: 'POST',
            url: '/admin/penalty_user.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(penaltyData)
        }).done(function (result) {
            if (result === 0) {
                alert("해당 유저를 정지했습니다.");
                window.close();
                refreshParent();
            } else {
                alert("알 수 없는 오류 발생");
                window.location.reload();
            }
        }).fail(function (error) {
            alert(error);
            window.location.reload();
        })
    }

    function refreshParent() {
        window.opener.location.reload();
    }
</script>
</body>
</html>

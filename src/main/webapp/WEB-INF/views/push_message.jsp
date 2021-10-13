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
    <title>커스텀 메세지</title>
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
</head>
<body>
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
                                    <h6 class="card-title" style="font-size: x-large">Push 알림 전송
                                    </h6>
                                    <div class="form-group">
                                        <div class="form-check">
                                            <label class="form-check-label">
                                                <input type="radio" class="form-check-input" name="send-to"
                                                       id="all-user" value="user">
                                                전체
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <label class="form-check-label">
                                                <input type="radio" class="form-check-input" name="send-to"
                                                       id="all-artist" value="artist">
                                                아티스트
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-12 mt-3 justify-content-center">
                                        <label class="label" style="font-size: larger"
                                               for="push-make-title">
                                            Push 알림 제목
                                        </label>
                                        <textarea class="form-control" id="push-make-title" rows="1"
                                                  style="line-height: 150%; font-size: large" placeholder="알림 제목을 입력하세요."
                                        ></textarea>
                                    </div>
                                    <div class="col-md-12 mt-3 justify-content-around">
                                        <label class="label" style="font-size: larger" for="push-make-content">
                                            Push 알림 내용
                                        </label>
                                        <textarea class="form-control" id="push-make-content" rows="5"
                                                  style="line-height: 150%; font-size: large" placeholder="알림 내용을 입력하세요."
                                        ></textarea>
                                    </div>
                                    <div class="col-md-12 mt-4 mb-3 justify-content-around d-flex">
                                        <button type="button" class="btn btn-outline-primary"
                                                style="float: right; width: 25%; height: 150%"
                                                onclick="sendPush()">
                                            전송
                                        </button>
                                        <button type="button" class="btn btn-secondary"
                                                style="float: right; width: 25%; height: 150%"
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
    function sendPush() {
        if (!inspection("push-make-title", "push_title")) {
            return false;
        } else if (!inspection("push-make-content", "push_content")) {
            return false;
        } else if ($('input[name="send-to"]:checked').val() == undefined) {
            alert("전송할 대상을 선택해주세요.");
            return false;
        }
        let title = $("#push-make-title").val();
        let content = $("#push-make-content").val();
        let sendTo = $('input[name="send-to"]:checked').val();
        let message = 'Push 알림을 전송합니다.\n\n알림 제목 : ' + title + '\n알림 내용 : ' + content + '\n전송 대상 : ';
        if (sendTo === 'user') {
            message = message + '유저';
        } else if (sendTo === 'artist') {
            message = message + '아티스트';
        }
        if (confirm(message)) {
            let pushData = {
                "title": title,
                "content": content,
                "send_to": sendTo
            };
            $.ajax({
                type: 'POST',
                url: '/admin/push_send_post.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(pushData)
            }).done(function (result) {
                console.log(result);
                if (result === 0) {
                    alert("Push 알림이 전송되었습니다.");
                    window.location.reload();
                } else {
                    alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    window.location.reload();
                }
            }).fail(function (error) {
                console.log(error);
                window.location.reload();
            })
        } else {
            return false;
        }
    }

    function refreshParent() {
        window.opener.location.reload();
    }
</script>
</body>
</html>

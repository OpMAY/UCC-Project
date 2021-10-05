<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-07-20
  Time: 오후 2:47
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
            <c:set var="spon" value="${spon}"/>
            <div class="row">
                <div class="col-md-12 grid-margin">
                    <div class="row justify-content-center">
                        <div class="col-md-10">
                            <div class="card mb-3" style="background-color: #FFFFFf; border-radius: 1.5%">
                                <h6 class="card-title mt-3 ml-3" style="font-size: xxx-large; text-align: center; color: midnightblue">후원 관리</h6>
                                <hr>
                                <h6 class="card-title mt-3 ml-3" style="font-size: x-large">후원 내역</h6>
                                <div class="row mt-3 justify-content-center">
                                    <div class="col-md-10">
                                        <label class="label d-flex" for="receipt-id" style="font-size: large">
                                            후원 번호
                                        </label>
                                        <textarea class="form-control" id="receipt-id" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${spon.receipt_id}</textarea>
                                    </div>
                                </div>
                                <div class="row mt-3 justify-content-around">
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="spon-user" style="font-size: large">
                                            후원자 명
                                        </label>
                                        <textarea class="form-control" id="spon-user" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${spon.user_name}</textarea>
                                    </div>
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="spon-method" style="font-size: large">
                                            결제 방법
                                        </label>
                                        <textarea class="form-control" id="spon-method" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>카드 결제</textarea>
                                    </div>
                                </div>
                                <div class="row mt-3 justify-content-around">
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="spon-date" style="font-size: large">
                                            후원 일자
                                        </label>
                                        <textarea class="form-control" id="spon-date" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${spon.spon_date}</textarea>
                                    </div>
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="spon-price" style="font-size: large">
                                            결제 금액
                                        </label>
                                        <textarea class="form-control" id="spon-price" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled><fmt:formatNumber value="${spon.price}"
                                                                             type="number"/>원</textarea>
                                    </div>
                                </div>
                                <div class="row mt-3 justify-content-around">
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="spon-status" style="font-size: large">
                                            후원 상태
                                        </label>
                                        <textarea class="form-control" id="spon-status" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled><c:choose><c:when test="${spon.status == false}">미승인</c:when><c:when
                                                test="${spon.status == true}">승인</c:when></c:choose></textarea>
                                    </div>
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="send-price" style="font-size: large">
                                            입금할 금액
                                        </label>
                                        <textarea class="form-control" id="send-price" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled><fmt:formatNumber value="${spon.price_send}"
                                                                             type="number"/>원</textarea>
                                    </div>
                                </div>
                                <div class="row mt-3 mb-3 justify-content-around">
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="spon-type" style="font-size: large">
                                            후원 종류
                                        </label>
                                        <textarea class="form-control" id="spon-type" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled><c:choose><c:when
                                                test="${spon.type == 'Board Spon'}">게시글 후원</c:when><c:when
                                                test="${spon.type == 'Artist Spon'}">아티스트 후원</c:when></c:choose></textarea>
                                    </div>
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="spon-rate" style="font-size: large">
                                            부과세
                                        </label>
                                        <textarea class="form-control" id="spon-rate" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>10%</textarea>
                                    </div>
                                </div>
                                <c:if test="${spon.status == true}">
                                    <div class="row mt-3 mb-3 justify-content-around">
                                        <div class="col-md-5">
                                            <label class="label d-flex" for="apply-date" style="font-size: large">
                                                승인 일자
                                            </label>
                                            <textarea class="form-control" id="apply-date" rows="1"
                                                      style="line-height: 150%; font-size: large;"
                                                      disabled>${spon.apply_date}</textarea>
                                        </div>
                                        <div class="col-md-5">
                                            <label class="label d-flex" for="send-date" style="font-size: large">
                                                입금 일자
                                            </label>
                                            <textarea class="form-control" id="send-date" rows="1"
                                                      style="line-height: 150%; font-size: large;"
                                                      disabled><c:choose><c:when
                                                    test="${spon.send_date == null}">-</c:when><c:when
                                                    test="${spon.send_date != null}">${spon.send_date}</c:when></c:choose></textarea>
                                        </div>
                                    </div>
                                </c:if>
                                <h6 class="card-title ml-3 mt-3" style="font-size: x-large">구매자 정보</h6>
                                <div class="row mt-3 mb-3 justify-content-around">
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="spon-user-name" style="font-size: large">
                                            구매자 명
                                        </label>
                                        <textarea class="form-control" id="spon-user-name" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${spon.user_name}</textarea>
                                    </div>
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="spon-user-email" style="font-size: large">
                                            이메일
                                        </label>
                                        <textarea class="form-control" id="spon-user-email" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${user.email}</textarea>
                                    </div>
                                </div>
                                <h6 class="card-title ml-3 mt-3" style="font-size: x-large">아티스트 정보</h6>
                                <div class="row mt-3 justify-content-around">
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="artist-name" style="font-size: large">
                                            아티스트 명
                                        </label>
                                        <textarea class="form-control" id="artist-name" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${spon.artist_name}</textarea>
                                    </div>
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="artist-bank-name" style="font-size: large">
                                            은행명
                                        </label>
                                        <textarea class="form-control" id="artist-bank-name" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${artist.bank_name}</textarea>
                                    </div>
                                </div>
                                <div class="row mt-3 justify-content-around">
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="artist-phone" style="font-size: large">
                                            연락처
                                        </label>
                                        <textarea class="form-control" id="artist-phone" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${artist.artist_phone}</textarea>
                                    </div>
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="artist-bank-owner" style="font-size: large">
                                            예금주 명
                                        </label>
                                        <textarea class="form-control" id="artist-bank-owner" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${artist.bank_owner}</textarea>
                                    </div>
                                </div>
                                <div class="row mt-3 mb-3 justify-content-around">
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="artist-email" style="font-size: large">
                                            이메일
                                        </label>
                                        <textarea class="form-control" id="artist-email" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${artist.artist_phone}</textarea>
                                    </div>
                                    <div class="col-md-5">
                                        <label class="label d-flex" for="artist-bank-account" style="font-size: large">
                                            계좌 번호
                                        </label>
                                        <textarea class="form-control" id="artist-bank-account" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${artist.bank_account}</textarea>
                                    </div>
                                </div>
                                <div class="row mt-3 mb-3 justify-content-around">
                                    <div class="col-md-3 justify-content-center d-flex">
                                        <c:choose>
                                            <c:when test="${spon.status == true}">
                                                <button class="btn btn-outline-primary" disabled style="width : 50%; height: 150%"
                                                        onclick="if(confirm('이 후원 내역의 결제정보를 승인하시겠습니까?')){applySpon(${spon.spon_no});} else {return false;}">
                                                    승인하기
                                                </button>
                                            </c:when>
                                            <c:when test="${spon.status == false}">
                                                <button class="btn btn-outline-primary" style="width : 50%; height: 150%"
                                                        onclick="if(confirm('이 후원 내역의 결제정보를 승인하시겠습니까?')){applySpon(${spon.spon_no});} else {return false;}">
                                                    승인하기
                                                </button>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div class="col-md-3 justify-content-center d-flex">
                                        <button class="btn btn-outline-primary" style="width : 50%; height: 150%"
                                                onclick="if(confirm('이 후원 내역을 삭제하시겠습니까?')){deleteSpon(${spon.spon_no});} else {return false;}">
                                            삭제
                                        </button>
                                    </div>
                                    <div class="col-md-3 justify-content-center d-flex">
                                        <button class="btn btn-secondary" style="width : 50%; height: 150%"
                                                onclick="location.href = '/admin/spon.do';">
                                            돌아가기
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
        function applySpon(spon_no) {
            let data = {
                "spon_no": spon_no
            };
            $.ajax({
                type: 'POST',
                url: '/admin/spon_apply.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                console.log(result);
                if (result === 0) {
                    alert("후원 결제 정보 승인을 완료했습니다.");
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

        function deleteSpon(spon_no) {
            let data = {
                "spon_no": spon_no
            };
            $.ajax({
                type: 'POST',
                url: '/admin/spon_send.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                console.log(result);
                if (result === 0) {
                    alert("후원 내역을 삭제했습니다.");
                    location.href = '/admin/spon.do'
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

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
    <link rel="shortcut icon" href="/assets/images/ucc/ucc_favicon.png"/>
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
                                <h6 class="card-title mt-3 ml-3" style="font-size: x-large">후원 내역</h6>
                                <c:if test="${spon.platform == 'Android'}">
                                    <div class="row mt-3 justify-content-around">
                                        <div class="col-md-12" style="padding: 0 30px 0 30px">
                                            <label class="label d-flex" for="google-orderId" style="font-size: large">
                                                구글 검증 코드
                                            </label>
                                            <textarea class="form-control" id="google-orderId" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${spon.order_id}</textarea>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="row mt-3 justify-content-around">
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="spon-user" style="font-size: large">
                                            후원자 명
                                        </label>
                                        <textarea class="form-control" id="spon-user" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${spon.user_name}</textarea>
                                    </div>
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="spon-platform" style="font-size: large">
                                            결제 플랫폼
                                        </label>
                                        <textarea class="form-control" id="spon-platform" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled><c:choose><c:when
                                                test="${spon.platform == 'Android'}">구글 플레이</c:when><c:when
                                                test="${spon.platform == 'IOS'}">애플 앱스토어</c:when></c:choose></textarea>
                                    </div>
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="spon-type" style="font-size: large">
                                            후원 종류
                                        </label>
                                        <textarea class="form-control" id="spon-type" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled><c:choose><c:when
                                                test="${spon.type == 'Board Spon'}">게시글 후원</c:when><c:when
                                                test="${spon.type == 'Artist Spon'}">아티스트 후원</c:when></c:choose></textarea>
                                    </div>
                                </div>
                                <div class="row mt-3 justify-content-around">
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="spon-price" style="font-size: large">
                                            후원 금액
                                        </label>
                                        <textarea class="form-control" id="spon-price" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${spon.price}</textarea>
                                    </div>
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <c:choose>
                                            <c:when test="${spon.platform == 'Android'}">
                                                <label class="label d-flex" for="spon-app-vat" style="font-size: large">
                                                    부가가치세 계산 금액 <span style="float: right; margin-right: 3px"
                                                                      data-toggle="tooltip" data-placement="top"
                                                                      title="구글 플레이 스토어의 상품 가격은 부가가치세 10% 포함 가격입니다."><i
                                                        data-feather="help-circle"></i></span>
                                                </label>
                                                <textarea class="form-control" id="spon-app-vat" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${googlePrice}</textarea>
                                            </c:when>
                                            <c:when test="${spon.platform == 'IOS'}">
                                                <label class="label d-flex" for="spon-app-vat" style="font-size: large">
                                                    인앱 수수료 적용 금액 <span style="float: right; margin-right: 3px"
                                                                       data-toggle="tooltip" data-placement="top"
                                                                       title="애플 앱스토어의 경우 15%의 수수료가 부과됩니다."><i
                                                        data-feather="help-circle"></i></span>
                                                </label>
                                                <textarea class="form-control" id="spon-app-vat" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled>${applePrice}</textarea>
                                            </c:when>
                                        </c:choose>

                                    </div>
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="spon-exchange-rate" style="font-size: large">
                                            결제 당시 환율 <span style="float: right; margin-right: 3px" data-toggle="tooltip"
                                                           data-placement="top" data-html="true"
                                                           title="단위 값에 따른 환율입니다.<br>ex) 1달러 = 1,200원"><i
                                                data-feather="help-circle"></i></span>
                                        </label>
                                        <textarea class="form-control" id="spon-exchange-rate" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${currencyRate}원</textarea>
                                    </div>
                                </div>
                                <div class="row mt-3 justify-content-around">
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="spon-status" style="font-size: large">
                                            정산 금액
                                        </label>
                                        <textarea class="form-control" id="spon-status" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled><fmt:formatNumber value="${resultPrice}"
                                                                             type="number"/>원</textarea>
                                    </div>
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <c:choose>
                                            <c:when test="${spon.platform == 'Android'}">
                                                <label class="label d-flex" for="send-vat" style="font-size: large">
                                                    인앱 결제 수수료 <span style="float: right; margin-right: 3px"
                                                                    data-toggle="tooltip" data-placement="top"
                                                                    data-html="true"
                                                                    title="구글 플레이 수수료 15%<br>= 부가가치세 10%<br>+ 인앱 수수료 5%<br>위 값 중 인앱 수수료에<br>해당하는 값입니다."><i
                                                        data-feather="help-circle"></i></span>
                                                </label>
                                                <textarea class="form-control" id="send-vat" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled><fmt:formatNumber value="${tax}"
                                                                                     type="number"/>원</textarea>
                                            </c:when>
                                            <c:when test="${spon.platform == 'IOS'}">
                                                <label class="label d-flex" for="send-vat" style="font-size: large">
                                                    부가가치세 <span style="float: right; margin-right: 3px"
                                                                data-toggle="tooltip" data-placement="top"
                                                                title="부가가치세 10%"><i
                                                        data-feather="help-circle"></i></span>
                                                </label>
                                                <textarea class="form-control" id="send-vat" rows="1"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled><fmt:formatNumber value="${tax}"
                                                                                     type="number"/>원</textarea>
                                            </c:when>
                                        </c:choose>

                                    </div>
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="send-price" style="font-size: large">
                                            입금할 금액
                                        </label>
                                        <textarea class="form-control" id="send-price" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled><fmt:formatNumber value="${sendPrice}"
                                                                             type="number"/>원</textarea>
                                    </div>
                                </div>
                                <div class="row mt-3 mb-3 justify-content-around">
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label class="label d-flex" for="spon-type1" style="font-size: large">
                                                    결제 유효 여부
                                                </label>
                                            </div>
                                            <div class="col-md-6">
                                                <button type="button" class="btn btn-outline-primary"
                                                        style="float:right"
                                                        onclick="if(confirm('이 결제에 대한 결제 검증 요청을 하시겠습니까?')){updateSpon(${spon.spon_no})} else{return false;}"
                                                >
                                                    결제 정보 업데이트
                                                </button>
                                            </div>
                                        </div>
                                        <textarea class="form-control" id="spon-type1" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled><c:choose><c:when
                                                test="${spon.verify_status == 0}">O</c:when><c:when
                                                test="${spon.verify_status == 1}">취소</c:when><c:when
                                                test="${spon.verify_status == 2}">보류 중</c:when><c:when
                                                test="${spon.verify_status == 3}">검증 대기 중</c:when></c:choose></textarea>
                                    </div>
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="spon-rate" style="font-size: large">
                                            관리자 승인 날짜
                                        </label>
                                        <textarea class="form-control" id="spon-rate" rows="1"
                                                  style="line-height: 150%; font-size: large" placeholder="미승인"
                                                  disabled>${spon.apply_date}</textarea>
                                    </div>
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="spon-date" style="font-size: large">
                                            후원 일자
                                        </label>
                                        <textarea class="form-control" id="spon-date" rows="1"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${spon.spon_date}</textarea>
                                    </div>
                                </div>
                                <div class="row mt-3 mb-3 justify-content-around">
                                    <div class="col-md-8 mb-3 d-flex" style="padding: 0 30px 0 30px">
                                        <c:choose>
                                            <c:when test="${spon.status == true}">
                                                <button class="btn btn-outline-primary" disabled
                                                        style="width : 50%; height: 150%"
                                                        onclick="if(confirm('이 후원 내역의 결제정보를 승인하시겠습니까?')){applySpon(${spon.spon_no});} else {return false;}">
                                                    승인완료
                                                </button>
                                            </c:when>
                                            <c:when test="${spon.status == false}">
                                                <button class="btn btn-outline-primary"
                                                        style="width : 50%; height: 150%"
                                                        onclick="if(confirm('이 후원 내역의 결제정보를 승인하시겠습니까?')){applySpon(${spon.spon_no});} else {return false;}">
                                                    승인하기
                                                </button>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div class="col-md-2 mb-3 justify-content-center d-flex">
                                        <button class="btn btn-outline-primary" style="width : 80%; height: 150%"
                                                onclick="if(confirm('이 후원 내역을 삭제하시겠습니까?')){deleteSpon(${spon.spon_no});} else {return false;}">
                                            삭제
                                        </button>
                                    </div>
                                    <div class="col-md-2 mb-3 justify-content-center d-flex">
                                        <button class="btn btn-secondary" style="width : 80%; height: 150%"
                                                onclick="location.href = '/admin/spon/${prevStatus}.do';">
                                            돌아가기
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card mb-3" style="background-color: #FFFFFf; border-radius: 1.5%">
                                <h6 class="card-title ml-3 mt-3" style="font-size: x-large">후원받는 아티스트 정보</h6>
                                <div class="row mt-3 justify-content-around">
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="artist-name" style="font-size: large">
                                            아티스트 명
                                        </label>
                                        <textarea class="form-control" id="artist-name" rows="2"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${spon.artist_name}</textarea>
                                    </div>
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="artist-phone" style="font-size: large">
                                            연락처
                                        </label>
                                        <textarea class="form-control" id="artist-phone" rows="2"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${artist.artist_phone}</textarea>
                                    </div>
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="artist-send-date" style="font-size: large">
                                            입금 확인 일자
                                        </label>
                                        <textarea class="form-control" id="artist-send-date" rows="2"
                                                  style="line-height: 150%; font-size: large" placeholder="미입금"
                                                  disabled>${spon.send_date}</textarea>
                                    </div>
                                </div>
                                <div class="row mt-3 mb-3 justify-content-around">
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="artist-bank-name" style="font-size: large">
                                            은행명
                                        </label>
                                        <textarea class="form-control" id="artist-bank-name" rows="2"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${artist.bank_name}</textarea>
                                    </div>
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="artist-bank-owner" style="font-size: large">
                                            예금주 명
                                        </label>
                                        <textarea class="form-control" id="artist-bank-owner" rows="2"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${artist.bank_owner}</textarea>
                                    </div>
                                    <div class="col-md-4" style="padding: 0 30px 0 30px">
                                        <label class="label d-flex" for="artist-bank-account" style="font-size: large">
                                            계좌 번호
                                        </label>
                                        <textarea class="form-control" id="artist-bank-account" rows="2"
                                                  style="line-height: 150%; font-size: large"
                                                  disabled>${artist.bank_account}</textarea>
                                    </div>
                                </div>
                                <div class="row mt-3 mb-3 justify-content-around">
                                    <div class="col-md-12 mb-3" style="padding-right: 70px">
                                        <c:choose>
                                            <c:when test="${spon.send_date != null}">
                                                <button class="btn btn-outline-primary" disabled
                                                        style="width : 25%; height: 150%; float: right">
                                                    입금 완료
                                                </button>
                                            </c:when>
                                            <c:when test="${spon.send_date == null}">
                                                <button class="btn btn-outline-primary"
                                                        style="width : 25%; height: 150%; float: right"
                                                        onclick="if(confirm('이 후원 내역을 입금 확인 처리 하시겠습니까?\n처리 후 변경은 불가능합니다.')){sendSpon(${spon.spon_no});} else {return false;}">
                                                    입금 확인
                                                </button>
                                            </c:when>
                                        </c:choose>
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
                } else if (result === 2) {
                    alert("결제 유효성을 먼저 업데이트 해주세요.\n유효한 결제만 승인 가능합니다.");
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
                url: '/admin/spon_delete.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                console.log(result);
                if (result === 0) {
                    alert("후원 내역을 삭제했습니다.");
                    location.href = '/admin/spon/${prevStatus}.do'
                } else {
                    alert("알 수 없는 오류 발생");
                    window.location.reload();
                }
            }).fail(function (error) {
                alert(error);
                window.location.reload();
            })
        }

        function sendSpon(spon_no) {
            let data = {"spon_no": spon_no};
            $.ajax({
                type: 'POST',
                url: '/admin/spon_send.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                console.log(result);
                if (result === 0) {
                    alert("후원 금액 전송 처리하였습니다.");
                    window.location.reload();
                } else if (result === 2) {
                    alert("승인된 후원 결제 내역만 송금처리 할 수 있습니다.");
                } else {
                    alert("알 수 없는 오류 발생. 관리자에게 문의하세요.");
                    window.location.reload();
                }
            }).fail(function (error) {
                alert(error);
                window.location.reload();
            })
        }

        function updateSpon(spon_no) {
            let data = {"spon_no": spon_no};
            $.ajax({
                type: 'POST',
                url: '/admin/spon/update.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                console.log(result);
                if (result.status === 0) {
                    alert("업데이트 되었습니다.");
                    window.location.reload();
                } else {
                    alert('결제 검증 오류 발생\n원인 : ' + result.statusMessage);
                    window.location.reload();
                }
            }).fail(function(xhr, textStatus, errorThrown) {
                alert('결제 검증 오류 발생\n원인 : ' + xhr.responseText);
                console.log(xhr.responseText);
                console.log(textStatus);
                console.log(errorThrown);
            })
        }
    </script>
</body>
</html>

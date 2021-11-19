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
                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="artist-select">아티스트 명</label>
                                            <select class="form-control" id="artist-select" style="color: #0e1014">
                                                <option selected disabled hidden id="default">아티스트를 선택하세요.</option>
                                                <c:forEach var="i" begin="1" end="${artistList.size()}">
                                                    <option>${artistList[i-1].artist_name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-6 col-md-6">
                                            <label class="label d-flex" for="price-to-send">
                                                정산 금액
                                            </label>
                                            <textarea class="form-control" id="price-to-send" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled></textarea>
                                        </div>
                                        <div class="col-6 col-md-6">
                                            <label class="label d-flex" for="number-to-send">
                                                후원 정산 건수
                                            </label>
                                            <textarea class="form-control" id="number-to-send" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled></textarea>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label class="label d-flex" for="artist_bank_info">
                                                아티스트 계좌정보
                                            </label>
                                            <textarea class="form-control" id="artist_bank_info" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled></textarea>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-6 col-md-6">
                                            <label class="label d-flex" for="artist_phone">
                                                아티스트 전화번호
                                            </label>
                                            <textarea class="form-control" id="artist_phone" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled></textarea>
                                        </div>
                                        <div class="col-6 col-md-6">
                                            <label class="label d-flex" for="artist_email">
                                                아티스트 이메일
                                            </label>
                                            <textarea class="form-control" id="artist_email" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled></textarea>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-12 justify-content-around d-flex">
                                            <button type="button" class="btn btn-outline-primary" style="float: right; width: 25%; height: 150%"
                                                    onclick="if(confirm('입금처리 하시겠습니까?')){setPurchaseStatusTrue()} else {return false;}">
                                                입금 처리
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

    function checkArtistListSize(){
        let size = ${artistList.size()};
        return size !== 0;
    }

    function setPurchaseStatusTrue(){
        let artist_name = $('select[id=artist-select] option:selected').val();
        if(artist_name === '아티스트를 선택하세요.'){
            alert("입금 처리할 아티스트를 먼저 선택해주세요.");
            return false;
        }
        console.log(artist_name);
        let data = {
            "artist_name" : artist_name
        };
        $.ajax({
            type: 'POST',
            url: '/admin/spon/summary/send/to_artist.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            if (result === 0) {
                alert("입금 처리 완료했습니다.");
                window.location.reload();
            } else {
                alert("알 수 없는 오류 발생");
                window.location.reload();
            }
        }).fail(function(xhr, textStatus, errorThrown) {
            alert(xhr.responseText);
            console.log(xhr.responseText);
            console.log(textStatus);
            console.log(errorThrown);
        })
    }

    $(document).ready(function () {
        if(!checkArtistListSize()){
            alert("정산할 아티스트가 존재하지 않습니다.");
            window.close();
        }
    });

    $('select[id=artist-select]').on('change', function(){
        let artist_name = $('select[id=artist-select] option:selected').val();
        console.log(artist_name);
        let data = {
            "artist_name" : artist_name
        };
        $.ajax({
            type: 'POST',
            url: '/admin/spon/summary/information/artist.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            if (result !== null) {
                console.log(result);
                document.getElementById("price-to-send").innerText = result.spon_amount.toLocaleString() + '원';
                document.getElementById("number-to-send").innerText = result.spon_count.toLocaleString() + '회';
                document.getElementById("artist_bank_info").innerText = result.artist.bank_name + ' ' + result.artist.bank_account + ' ' + result.artist.bank_owner;
                document.getElementById("artist_phone").innerText = phoneFormatter(result.artist.artist_phone);
                document.getElementById("artist_email").innerText = result.artist.email;
            } else {
                alert("알 수 없는 오류 발생");
                window.location.reload();
            }
        }).fail(function(xhr, textStatus, errorThrown) {
            alert(xhr.responseText);
            console.log(xhr.responseText);
            console.log(textStatus);
            console.log(errorThrown);
        })
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

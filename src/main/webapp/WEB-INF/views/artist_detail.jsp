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
    <title>회원 관리 - 아티스트 상세</title>
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
    <style>
        * {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        ul {
            padding: 16px 0;
        }

        ul li {
            display: inline-block;
            margin: 0 5px;
            font-size: 20px;
            letter-spacing: -.5px;
        }

        form {
            padding-top: 16px;
        }

        ul li.tag-item {
            padding: 4px 8px;
            background-color: #ffffff;
            color: #727cf5;
            border-radius: 20px;
            border: 1px solid #727cf5;
        }
    </style>
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

            <div class="row justify-content-center">
                <div class="col-md-10 grid-margin">
                    <c:set var="User" value="${User}"/>
                    <c:set var="Artist" value="${Artist}"/>
                    <div class="row justify-content-center">
                        <div class="col-md-12">
                            <div class="card" style="background-color: #FFFFFf; border-radius: 1.5%">
                                <div class="card-body">
                                    <h6 class="card-title" style="font-size: x-large">아티스트 상세</h6>
                                    <div class="row mt-3 justify-content-around">
                                        <div class="col-md-6">
                                            <label class="label" style="font-size: larger">
                                                프로필 이미지
                                            </label>
                                            <div style="width: 100%; height: 320px; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
                                                 class="d-flex justify-content-center">
                                                <img class="img-fluid" src="${Artist.artist_profile_img}"
                                                     style="height: 100%">
                                            </div>
                                            <div class="row mt-3 mb-3">
                                                <div class="col-md-12 justify-content-center d-flex">
                                                    <button type="button" class="btn btn-outline-primary"
                                                            style="width: 50%;height: 120%"
                                                            onclick="if(confirm('이 아티스트의 프로필 이미지를 기본 이미지로 변경하시겠습니까?\n변경 여부는 해당 아티스트에게 메세지로 전송됩니다.')){setProfileImageBasic(${Artist.artist_no})} else {return false;}">
                                                        기본 이미지로 변경
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="label" style="font-size: larger">
                                                배경 이미지
                                            </label>
                                            <div style="width: 100%; height: 320px; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
                                                 class="d-flex justify-content-center">
                                                <img class="img-fluid" src="${Artist.main_img}"
                                                     style="height: 100%">
                                            </div>
                                            <div class="row mt-3 mb-3">
                                                <div class="col-md-12 justify-content-center d-flex">
                                                    <button type="button" class="btn btn-outline-primary"
                                                            style="width: 50%;height: 120%"
                                                            onclick="if(confirm('이 아티스트의 배경 이미지를 기본 이미지로 변경하시겠습니까?\n변경 여부는 해당 아티스트에게 메세지로 전송됩니다.')){setMainImageBasic(${Artist.artist_no})} else {return false;}">
                                                        기본 이미지로 변경
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row justify-content-center mt-3">
                                        <div class="col-md-12">
                                            <label class="label d-flex" for="artist-explain"
                                                   style="font-size: larger">
                                                아티스트 소개
                                            </label>
                                            <textarea class="form-control" id="artist-explain" rows="10"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${Artist.explain}</textarea>
                                            <div class="row justify-content-center mt-3 mb-3">
                                                <div class="col-md-4 d-block">
                                                    <button type="button" class="btn btn-outline-primary"
                                                            style="width : 100%; height: 120%"
                                                            onclick="if(confirm('이 아티스트의 설명을 삭제하시겠습니까?\n변경 여부는 해당 아티스트에게 메세지로 전송됩니다.')){resetArtistExplain(${Artist.artist_no})} else {return false;}">
                                                        아티스트 소개 내용 삭제
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mt-3 justify-content-around">
                                        <div class="col-md-4">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label class="label d-flex" for="artist-name"
                                                           style="font-size: large">
                                                        아티스트 명
                                                    </label>
                                                </div>
                                                <div class="col-md-6">
                                                    <button type="button" class="btn btn-outline-primary"
                                                            style="float:right"
                                                            onclick="if(confirm('이 아티스트의 이름을 기본 이름으로 변경하시겠습니까?\n변경 여부는 해당 아티스트에게 메세지로 전송됩니다.')){setArtistNameBasic(${Artist.artist_no})} else{return false;}">
                                                        기본 이름으로 변경
                                                    </button>
                                                </div>
                                            </div>
                                            <textarea class="form-control" id="artist-name" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${Artist.artist_name}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="user-sns" style="font-size: large">
                                                소셜 로그인
                                            </label>
                                            <textarea class="form-control" id="user-sns" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${User.sns}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="reg-date" style="font-size: large">
                                                전환 일자
                                            </label>
                                            <textarea class="form-control" id="reg-date" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${Artist.reg_date}</textarea>
                                        </div>
                                    </div>
                                    <div class="row mt-3 justify-content-around">
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="bank-name" style="font-size: large">
                                                은행 명
                                            </label>
                                            <textarea class="form-control" id="bank-name" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${Artist.bank_name}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="bank-owner" style="font-size: large">
                                                예금주 명
                                            </label>
                                            <textarea class="form-control" id="bank-owner" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${Artist.bank_owner}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="bank-account" style="font-size: large">
                                                계좌 번호
                                            </label>
                                            <textarea class="form-control" id="bank-account" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${Artist.bank_account}</textarea>
                                        </div>
                                    </div>
                                    <div class="row mt-3 justify-content-around">
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="email" style="font-size: large">
                                                이메일
                                            </label>
                                            <textarea class="form-control" id="email" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${Artist.email}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="artist-phone" style="font-size: large">
                                                연락처
                                            </label>
                                            <textarea class="form-control" id="artist-phone" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${Artist.artist_phone}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="fankok-number"
                                                   style="font-size: large">
                                                팬콕 수
                                            </label>
                                            <textarea class="form-control" id="fankok-number" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${Artist.fan_number}</textarea>
                                        </div>
                                    </div>
                                    <div class="row mt-3 justify-content-around">
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="spon-number" style="font-size: large">
                                                총 후원 정산 받은 횟수
                                            </label>
                                            <textarea class="form-control" id="spon-number" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>0회</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="last-spon-date" style="font-size: large">
                                                최근 후원금 정산 일
                                            </label>
                                            <textarea class="form-control" id="last-spon-date" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>-</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="total-spon-amount"
                                                   style="font-size: large">
                                                총 후원 정산 금액
                                            </label>
                                            <textarea class="form-control" id="total-spon-amount" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>0원</textarea>
                                        </div>
                                    </div>
                                    <div class="row mt-3 justify-content-center">
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label class="label d-flex" for="tag-list" style="font-size: large">
                                                        해시태그
                                                    </label>
                                                </div>
                                            </div>
                                            <ul id="tag-list"></ul>
                                            <hr>
                                            <div class="row justify-content-center mb-3">
                                                <div class="col-md-4">
                                                    <button type="button" class="btn btn-outline-primary"
                                                            style="width : 100%; height: 120%"
                                                            onclick="if(confirm('이 아티스트의 해시태그를 초기화하시겠습니까?\n변경 여부는 해당 아티스트에게 메세지로 전송됩니다.')){resetHashtags(${Artist.artist_no})} else {return false;}">
                                                        해시태그 초기화
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row mt-3 justify-content-around">
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="visit-today" style="font-size: large">
                                                금일 방문수
                                            </label>
                                            <textarea class="form-control" id="visit-today" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${Artist.visit_today}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="ban-number" style="font-size: large">
                                                누적 정지 횟수
                                            </label>
                                            <textarea class="form-control" id="ban-number" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${penalty_num}</textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="label d-flex" for="ban-date"
                                                   style="font-size: large">
                                                사용 정지 기간
                                            </label>
                                            <textarea class="form-control" id="ban-date" rows="1"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled><c:choose><c:when
                                                    test="${penalty == null}">-</c:when><c:when
                                                    test="${penalty != null}">${penalty.penalty_start_date}~${penalty.penalty_end_date}</c:when></c:choose></textarea>
                                        </div>
                                    </div>
                                    <c:if test="${penalty != null}">
                                        <div class="row mt-3">
                                            <div class="col-md-12">
                                                <label class="label d-flex" for="ban-reason"
                                                       style="font-size: large">
                                                    정지사유
                                                </label>
                                                <textarea class="form-control" id="ban-reason" rows="10"
                                                          style="line-height: 150%; font-size: large"
                                                          disabled><c:choose><c:when
                                                        test="${penalty == null}">-</c:when><c:when
                                                        test="${penalty != null}">${penalty.penalty_reason}</c:when></c:choose></textarea>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="row mt-4 mb-3 justify-content-around">
                                        <div class="col-md-6 col-sm-6 col-6 justify-content-center d-flex">
                                            <c:choose>
                                                <c:when test="${penalty != null}">
                                                    <button type="button" class="btn btn-outline-primary"
                                                            style="width : 50%; height: 150%"
                                                            onclick="if(confirm('이 아티스트의 정지를 해제하시겠습니까?')){resetArtistPenalty(${Artist.user_no});} else {return false;}">
                                                        정지 해제
                                                    </button>
                                                </c:when>
                                                <c:when test="${penalty == null}">
                                                    <button type="button" class="btn btn-outline-primary" style="width : 50%; height: 150%"
                                                            onclick="if(confirm('이 아티스트를 정지하겠습니까?')){openWindowPopBan('/admin/penalty.do?user_no=${Artist.user_no}','회원 정지', ${penalty.penalty_no})} else {return false;}">
                                                        사용 정지
                                                    </button>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                        <div class="col-md-6 col-sm-6 col-6 justify-content-center d-flex">
                                            <button class="btn btn-secondary" style="width : 50%; height: 150%" onclick="location.href='/admin/artists.do'">
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
        function openWindowPopBan(url, name, penalty) {
            if(penalty == null){
                let options = 'top=10, left=10, width=720, height=1040, status=1, scrollbars=1, resizable=1, menubar=0, fullscreen=0, location=0';
                window.open(url, name, options);
            } else {
                alert("이미 정지된 유저입니다.");
                return false;
            }
        }

        function replaceAll(str, searchStr, replaceStr) {
            return str.split(searchStr).join(replaceStr);
        }


        function resetArtistPenalty(user_no) {
            let data = {"user_no": user_no};
            $.ajax({
                type: 'POST',
                url: '/admin/user_reset_penalty.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                if (result === 0) {
                    alert("해당 아티스트의 정지를 해제하였습니다.");
                    window.location.reload();
                } else {
                    alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    window.location.reload();
                }
            }).fail(function (error) {
                console.log(error);
                window.location.reload();
            })
        }
        function resetHashtags(artist_no) {
            let data = {"artist_no": artist_no};
            $.ajax({
                type: 'POST',
                url: '/admin/artist_reset_hashtag.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                if (result === 0) {
                    alert("해당 아티스트의 해시태그가 초기화 되었습니다.");
                    window.location.reload();
                } else {
                    alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    window.location.reload();
                }
            }).fail(function (error) {
                console.log(error);
                window.location.reload();
            })
        }

        function resetArtistExplain(artist_no){
            let data = {"artist_no": artist_no};
            $.ajax({
                type: 'POST',
                url: '/admin/artist_reset_explain.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                if (result === 0) {
                    alert("아티스트 설명이 삭제되었습니다.");
                    window.location.reload();
                } else {
                    alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    window.location.reload();
                }
            }).fail(function (error) {
                console.log(error);
                window.location.reload();
            })
        }

        function setProfileImageBasic(artist_no) {
            let data = {"artist_no": artist_no};
            $.ajax({
                type: 'POST',
                url: '/admin/artist_change_profile.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                if (result === 0) {
                    alert("프로필 이미지가 기본 이미지로 변경되었습니다.");
                    window.location.reload();
                } else {
                    alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    window.location.reload();
                }
            }).fail(function (error) {
                console.log(error);
                window.location.reload();
            })
        }

        function setMainImageBasic(artist_no) {
            let data = {"artist_no": artist_no};
            $.ajax({
                type: 'POST',
                url: '/admin/artist_change_main.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                if (result === 0) {
                    alert("메인 이미지가 기본 이미지로 변경되었습니다.");
                    window.location.reload();
                } else {
                    alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    window.location.reload();
                }
            }).fail(function (error) {
                console.log(error);
                window.location.reload();
            })
        }

        function setArtistNameBasic(artist_no) {
            let data = {"artist_no": artist_no};
            $.ajax({
                type: 'POST',
                url: '/admin/artist_change_name.do',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function (result) {
                if (result === 0) {
                    alert("해당 아티스트 이름을 기본 이름으로 변경하였습니다.");
                    window.location.reload();
                } else {
                    alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    window.location.reload();
                }
            }).fail(function (error) {
                console.log(error);
                window.location.reload();
            })
        }

        $(document).ready(function () {
            let tag = {};
            let counter = 0;

            // 서버 기본 해시태그 리스트를 가져온다.
            let hashtagList = '<c:out value="${Artist.hashtag}"/>';
            let edit = replaceAll(hashtagList, '&#034;', '"');
            if(edit != "") {
                let obj = JSON.parse(edit);
                for (let i = 0; i < obj.length; i++) {
                    tag[counter] = obj[i];
                    $("#tag-list").append("<li class='tag-item'>#" + obj[i] + "</li>");
                    counter++;
                }
            }
        });
    </script>
</body>
</html>

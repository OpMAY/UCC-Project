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
    <title>검색 추천 해시태그 관리</title>
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
            padding: 2px 6px 1px 11px;
            color: #000000;
            opacity: 72%;
            border-radius: 15px;
            cursor: pointer;
        }

        .tag-item:hover {
            background-color: #031a61;
            color: #fff;
            opacity: 80%;
        }

        .del-btn {
            font-size: 20px;
            font-weight: bold;
            cursor: pointer;
            margin-left: 8px;
        }

        .tr_hashTag_area {
            position: relative;
        }

        .tr_hashTag_area label {
            position: absolute;
            top: 1px; /* input 요소의 border-top 설정값 만큼 */
            left: 1px; /* input 요소의 border-left 설정값 만큼 */
            padding: .8em .5em; /* input 요소의 padding 값 만큼 */
            color: #999;
            cursor: text;
        }

        .tr_hashTag_area input[type="text"] {
            width: 100%; /* 원하는 너비 설정 */
            height: auto; /* 높이값 초기화 */
            line-height: normal; /* line-height 초기화 */
            padding: .8em .5em; /* 원하는 여백 설정, 상하단 여백으로 높이를 조절 */
            font-family: inherit; /* 폰트 상속 */
            border: 1px solid #999;
            border-radius: 0; /* iSO 둥근모서리 제거 */
            outline-style: none; /* 포커스시 발생하는 효과 제거를 원한다면 */
            -webkit-appearance: none; /* 브라우저별 기본 스타일링 제거 */
            -moz-appearance: none;
            appearance: none;
        }

    </style>
</head>
<body>
<c:set var="hashtagList" value="${hashtagList}"/>
<div class="main-wrapper">
    <!-- partial:partials/_sidebar.jsp -->
    <jsp:include page="partials/_sidebar.jsp" flush="true"></jsp:include>
    <!-- partial -->

    <div class="page-wrapper">
        <!-- partial:partials/_navbar.jsp -->
        <jsp:include page="partials/_navbar.jsp" flush="true"></jsp:include>
        <!-- partial -->
        <div class="page-content">

            <nav class="page-breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item" style="color: #baa2fc">검색 해시태그 관리</li>
                </ol>
            </nav>

            <div class="row">
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title" style="font-size: x-large">검색 해시태그 관리</h6>
                            <div class="col-md-12">
                                <div class="row mt-3">
                                    <div class="col-md-9">
                                        <label class="label d-flex" for="search-example"
                                               style="font-size: larger">
                                            검색창 예시
                                        </label>
                                        <textarea class="form-control" id="search-example" rows="1"
                                                  style="line-height: 150%; font-size: large" placeholder="검색창 입니다."
                                                  disabled></textarea>
                                    </div>
                                </div>
                                <div class="tr_hashTag_area">
                                    <div class="form-group">
                                        <input type="hidden" value="" name="tag" id="rdTag"/>
                                    </div>

                                    <ul id="tag-list"></ul>

                                    <div class="form-group mt-5">
                                        <input type="text" id="tag" size="7" placeholder="엔터로 해시태그를 등록해주세요. 최대 5자까지 가능하며 해시태그는 최대 10개 등록 가능합니다."
                                               style="width: 50%;"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row mt-4 mb-3 justify-content-around">
                                <div class="col-md-6 justify-content-center d-flex">
                                    <button type="button" id="save-button" class="btn btn-outline-primary" style="width : 50%; height: 150%">
                                        저장
                                    </button>
                                </div>
                                <div class="col-md-6 justify-content-center d-flex">
                                    <button class="btn btn-secondary" style="width : 50%; height: 150%"
                                            onclick="location.reload()">
                                        원래대로
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
        function marginTag() {
            return Object.values(tag).filter(function (word) {
                return word !== "";
            });
        }

        function replaceAll(str, searchStr, replaceStr) {
            return str.split(searchStr).join(replaceStr);
        }

        $(document).ready(function () {
            let tag = {};
            let counter = 0;

            // 서버 기본 해시태그 리스트를 가져온다.
            let hashtagList = '<c:out value="${hashtagList}"/>';
            let edit = replaceAll(hashtagList, '&#034;', '"');
            console.log(edit);
            let obj = JSON.parse(edit);
            for (let i = 0; i < obj.length; i++) {
                console.log(obj[i]);
                tag[counter] = obj[i];
                console.log(counter);
                $("#tag-list").append("<li class='tag-item' tagId='" + counter + "' style='text-decoration: underline'>#" + obj[i] + "<span class='del-btn' idx='" + counter + "'><svg style=\"width:16px;height:16px\" viewBox=\"0 0 24 24\">\n" +
                    "    <path fill=\"currentColor\" d=\"M19,6.41L17.59,5L12,10.59L6.41,5L5,6.41L10.59,12L5,17.59L6.41,19L12,13.41L17.59,19L19,17.59L13.41,12L19,6.41Z\" />\n" +
                    "</svg></span></li>");
                counter++;
            }

            // 입력한 값을 태그로 생성한다.
            function addTag(value) {
                tag[counter] = value;
                counter++; // del-btn 의 고유 id 가 된다.
            }

            // tag 안에 있는 값을 array type 으로 만들어서 넘긴다.
            function marginTag() {
                return Object.values(tag).filter(function (word) {
                    return word !== "";
                });
            }

            $("#save-button").on("click", function (e) {
                let data = marginTag();
                $.ajax({
                    type: 'POST',
                    url: '/admin/hashtag_edit.do',
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function (result) {
                    console.log(result);
                    if (result === 0) {
                        alert("해시태그 수정 완료");
                        window.location.reload();
                    } else {
                        alert("알 수 없는 오류 발생");
                        window.location.reload();
                    }
                }).fail(function (error) {
                    alert(error);
                    window.location.reload();
                });
            });

            $("#tag-list li").click(function (e) {
                let idx = $(this).attr("tagId");
                let hashtagData = tag[idx];
                $("#search-example").val(hashtagData);
            });

            // 서버에 제공
            $("#tag-form").on("submit", function (e) {
                let value = marginTag(); // return array
                $("#rdTag").val(value);

                $(this).submit();
            });


            $("#tag").on("keypress", function (e) {
                let self = $(this);

                if(e.keyCode === 32){
                    e.preventDefault();
                }

                //엔터나 스페이스바 눌렀을때 실행
                if (e.key === "Enter") {

                    let tagValue = self.val(); // 값 가져오기

                    // 해시태그 값 없으면 실행X
                    if (tagValue !== "") {
                        console.log("length : " + tagValue.length);
                        if (tagValue.length <= 5) {

                            // 같은 태그가 있는지 검사한다. 있다면 해당값이 array 로 return 된다.
                            let result = Object.values(tag).filter(function (word) {
                                return word === tagValue;
                            });

                            // 해시태그가 중복되었는지 확인
                            if (result.length === 0) {
                                if (counter < 10) {
                                    $("#tag-list").append("<li class='tag-item' tagId='" + counter + "' style='text-decoration: underline'>#" + tagValue + "<span class='del-btn' idx='" + counter + "'><svg style=\"width:16px;height:16px\" viewBox=\"0 0 24 24\">\n" +
                                        "    <path fill=\"currentColor\" d=\"M19,6.41L17.59,5L12,10.59L6.41,5L5,6.41L10.59,12L5,17.59L6.41,19L12,13.41L17.59,19L19,17.59L13.41,12L19,6.41Z\" />\n" +
                                        "</svg></span></li>");
                                    addTag(tagValue);
                                    self.val("");
                                } else {
                                    alert("검색 해시태그는 최대 10개까지 등록 가능합니다.");
                                }

                            } else {
                                alert("태그값이 중복됩니다.");
                            }
                        } else {
                            alert("해시태그는 최대 5자까지 가능합니다.");
                        }
                    }
                    e.preventDefault(); // SpaceBar 시 빈공간이 생기지 않도록 방지
                }
            });

            // 삭제 버튼
            // 인덱스 검사 후 삭제
            $(document).on("click", ".del-btn", function (e) {
                let index = $(this).attr("idx");
                tag[index] = "";
                $(this).parent().remove();
                counter--;
            });

            let placeholderTarget = $('.tr_hashTag_area input[type="text"]');
            //포커스시
            placeholderTarget.on('focus', function () {
                $(this).siblings('label').fadeOut('fast');
            });
            //포커스아웃시
            placeholderTarget.on('focusout', function () {
                if ($(this).val() === '') {
                    $(this).siblings('label').fadeIn('fast');
                }
            });

        })
    </script>
</body>
</html>

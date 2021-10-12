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
    <title>크라우드 수정</title>
    <!-- core:css -->
    <link rel="stylesheet" href="/assets/vendors/core/core.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <link rel="stylesheet" href="/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="/assets/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
    <link rel="stylesheet" href="/assets/vendors/jquery-tags-input/jquery.tagsinput.min.css">
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

        .tag-item:hover {
            background-color: #6B5D7E;
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
<script>
    let input_index = 1;
    console.log(input_index);
    let file_number = 0;
</script>
<c:set var="Loudsourcing" value="${Loudsourcing}"/>
<c:set var="files" value="${files}"/>
<div class="main-wrapper">
    <!-- partial:partials/_sidebar.jsp -->
    <jsp:include page="partials/_sidebar.jsp" flush="true"></jsp:include>
    <!-- partial -->

    <div class="page-wrapper">
        <!-- partial:partials/_navbar.jsp -->
        <jsp:include page="partials/_navbar.jsp" flush="true"></jsp:include>
        <!-- partial -->
        <div class="page-content">

            <div class="row">
                <div class="col-md-12 grid-margin justify-content-center">


                    <div class="row justify-content-center">
                        <div class="col-md-12">
                            <div class="card" style="background-color: #FFFFFf; border-radius: 1.5%">
                                <div class="card-body">
                                    <h6 class="card-title" style="font-size: x-large">크라우드 상세</h6>
                                    <form id="loudsourcingEditForm">
                                        <div class="row justify-content-around mt-3">
                                            <div class="col-md-6">
                                                <label class="label d-flex" for="loudsourcing-name"
                                                       style="font-size: larger">
                                                    공모전 이름
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-name" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          onkeypress="preventEnterEdit()"
                                                >${Loudsourcing.name}</textarea>
                                            </div>
                                            <div class="col-md-6" style="vertical-align: middle">
                                                <button type="button" class="btn btn-outline-primary"
                                                        style="float:right; font-size: large;position: relative; top: 43px;"
                                                        onclick="openWindowPopEdit('/admin/advertiser_edit.do?loudsourcing_no=${Loudsourcing.loudsourcing_no}', '광고 발주자 상세정보 수정')">
                                                    광고 발주자 상세정보 수정
                                                </button>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-6">
                                                <label class="label" style="font-size: larger">
                                                    공모전 사진 - [사진을 변경하려면 사진을 클릭하세요.]
                                                </label>
                                                <input type="file" id="loudsourcing-img" name="img" accept="image/*"
                                                       hidden/>
                                                <div style="height: 800px; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
                                                     class="d-flex justify-content-center " id="image-container">
                                                    <img class="img-fluid" src="${Loudsourcing.img}"
                                                         onerror="this.src='https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/fan_main_img_basic.png'"
                                                         onclick="$('#loudsourcing-img').click()"
                                                         style="height: 100%; object-fit: contain; cursor: pointer">
                                                </div>
                                                <script>

                                                    $("#loudsourcing-img").change(function () {
                                                        if (this.files && this.files[0]) {
                                                            const reader = new FileReader;
                                                            reader.onload = function (data) {
                                                                console.log(data);
                                                                $("#image-container img").attr("src", data.target.result);
                                                            }
                                                            reader.readAsDataURL(this.files[0]);
                                                        }
                                                    })
                                                </script>
                                            </div>
                                            <div class="col-md-6">
                                                <label class="label d-flex" for="loudsourcing-status"
                                                       style="font-size: larger">
                                                    상태
                                                </label>
                                                <c:choose>
                                                    <c:when test="${Loudsourcing.status == 'recruitment'}">
                                                        <select class="form-control" id="loudsourcing-status"
                                                                style="font-size: large;text-align: center; text-align-last: center">
                                                            <option selected>모집</option>
                                                            <option>진행</option>
                                                        </select>
                                                    </c:when>
                                                    <c:when test="${Loudsourcing.status == 'process'}">
                                                        <select class="form-control" id="loudsourcing-status"
                                                                style="font-size: large;text-align: center; text-align-last: center">
                                                            <option>모집</option>
                                                            <option selected>진행</option>
                                                        </select>
                                                    </c:when>
                                                </c:choose>

                                                <label class="label d-flex" for="loudsourcing-host"
                                                       style="font-size: larger">
                                                    주최
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-host" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          onkeypress="preventEnterEdit()"
                                                >${Loudsourcing.host}</textarea>
                                                <label class="label d-flex" for="loudsourcing-reward"
                                                       style="font-size: larger">
                                                    상금
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-reward" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                >${Loudsourcing.reward}</textarea>
                                                <label class="label d-flex"
                                                       style="font-size: larger">
                                                    해시태그 - (대괄호([]), 쌍따옴표(") 등의 특수문자는 불가능합니다.)
                                                </label>
                                                <div class="tr_hashTag_area">
                                                    <div class="form-group">
                                                        <input type="hidden" value="" name="tag" id="rdTag"/>
                                                    </div>

                                                    <ul id="tag-list"></ul>

                                                    <div class="form-group">
                                                        <input type="text" id="tag" size="7"
                                                               placeholder="엔터로 해시태그를 등록해주세요. 최대 5자까지 가능하며 해시태그는 최대 10개 등록 가능합니다."
                                                               style="width: 100%;"/>
                                                    </div>
                                                </div>
                                                <label class="label d-flex" for="total-recruit-number"
                                                       style="font-size: larger">
                                                    총 모집 인원
                                                </label>
                                                <textarea class="form-control" id="total-recruit-number" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                >${Loudsourcing.total_recruitment_number}</textarea>
                                                <label class="label d-flex" for="total-date"
                                                       style="font-size: larger">
                                                    총 기간
                                                </label>
                                                <c:choose>
                                                    <c:when test="${Loudsourcing.status == 'recruitment'}">
                                                        <div class="row justify-content-around" id="total-date">
                                                            <div class="col-md-6">
                                                                <div class="input-group date datepicker"
                                                                     id="dp-edit-loudsourcing-start-date">
                                                                    <input type="text" class="form-control"
                                                                           name="loudsourcing-start-date"
                                                                           id="loudsourcing-start-date"
                                                                           onchange="copyStartDate()"
                                                                           style="font-size: large;line-height: normal;text-align: center" disabled
                                                                           value="${Loudsourcing.start_date}"><span
                                                                        class="input-group-addon"><i
                                                                        data-feather="calendar"></i></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="input-group date datepicker"
                                                                     id="dp-edit-loudsourcing-end-date">
                                                                    <input type="text" class="form-control"
                                                                           name="loudsourcing-end-date"
                                                                           id="loudsourcing-end-date"
                                                                           style="font-size: large;line-height: normal;text-align: center"
                                                                           value="${Loudsourcing.end_date}"><span
                                                                        class="input-group-addon" id="click-test"><i
                                                                        data-feather="calendar"></i></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${Loudsourcing.status == 'process'}">
                                                        <div class="row justify-content-around" id="total-date">
                                                            <div class="col-md-6">
                                                                <div class="input-group date datepicker"
                                                                     id="dp-edit-loudsourcing-start-date">
                                                                    <input type="text" class="form-control"
                                                                           name="loudsourcing-start-date"
                                                                           id="loudsourcing-start-date"
                                                                           onchange="copyStartDate()"
                                                                           style="font-size: large;line-height: normal;text-align: center"
                                                                           disabled
                                                                           value="${Loudsourcing.start_date}"><span
                                                                        class="input-group-addon" hidden><i
                                                                        data-feather="calendar"></i></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="input-group date datepicker"
                                                                     id="dp-edit-loudsourcing-end-date">
                                                                    <input type="text" class="form-control"
                                                                           name="loudsourcing-end-date"
                                                                           id="loudsourcing-end-date"
                                                                           style="font-size: large;line-height: normal;text-align: center"
                                                                           value="${Loudsourcing.end_date}"><span
                                                                        class="input-group-addon" id="click-test"><i
                                                                        data-feather="calendar"></i></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                </c:choose>

                                                <label class="label d-flex" for="recruitment-date"
                                                       style="font-size: larger">
                                                    모집 기간
                                                </label>
                                                <div class="row justify-content-around" id="recruitment-date">
                                                    <div class="col-md-6">
                                                <textarea class="form-control" id="recruitment-start-date" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          disabled>${Loudsourcing.start_date}</textarea>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="input-group date datepicker"
                                                             id="dp-edit-loudsourcing-recruitment-end-date">
                                                            <input type="text" class="form-control"
                                                                   name="loudsourcing-recruitment-end-date"
                                                                   id="loudsourcing-recruitment-end-date"
                                                                   style="font-size: large;line-height: normal;text-align: center"
                                                                   onchange="copyProcessStartDate()"
                                                                   value="${Loudsourcing.recruitment_end_date}"
                                                            ><span
                                                                class="input-group-addon"><i
                                                                data-feather="calendar"></i></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <label class="label d-flex" for="process-date"
                                                       style="font-size: larger">
                                                    진행 기간
                                                </label>
                                                <div class="row justify-content-around" id="process-date">
                                                    <div class="col-md-6">
                                                        <div class="input-group date datepicker"
                                                             id="dp-edit-loudsourcing-process-start-date">
                                                            <input type="text" class="form-control"
                                                                   name="loudsourcing-process-start-date"
                                                                   id="loudsourcing-process-start-date"
                                                                   style="font-size: large;line-height: normal;text-align: center"
                                                                   disabled
                                                                   value="${Loudsourcing.process_start_date}"
                                                            ><span
                                                                class="input-group-addon" hidden><i
                                                                data-feather="calendar"></i></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="input-group date datepicker"
                                                             id="dp-edit-loudsourcing-process-end-date">
                                                            <input type="text" class="form-control"
                                                                   name="loudsourcing-process-end-date"
                                                                   id="loudsourcing-process-end-date"
                                                                   style="font-size: large;line-height: normal;text-align: center"
                                                                   onchange="copyJudgeDate()"
                                                                   value="${Loudsourcing.process_end_date}"
                                                            ><span
                                                                class="input-group-addon"><i
                                                                data-feather="calendar"></i></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <label class="label d-flex"
                                                       style="font-size: larger">
                                                    심사 시작 일자
                                                </label>
                                                <div class="input-group date datepicker"
                                                     id="dp-edit-loudsourcing-judge-start-date">
                                                    <input type="text" class="form-control"
                                                           name="loudsourcing-judge-start-date"
                                                           id="loudsourcing-judge-start-date"
                                                           style="font-size: large;text-align: center" disabled
                                                           value="${Loudsourcing.judge_date}"
                                                    ><span
                                                        class="input-group-addon" hidden><i
                                                        data-feather="calendar"></i></span>
                                                </div>
                                                <label class="label d-flex"
                                                       style="font-size: larger">
                                                    파일 - [파일은 최대 3개까지 업로드 가능하며 파일 용량은 하나당 10MB를 넘을 수 없습니다.]
                                                </label>
                                                <div style="background-color: #ffffff; border: 1px solid black; line-height: 150%"
                                                     id="file-list">
                                                    <c:forEach var="i" begin="1" end="${files.size()}">
                                                        <div class="d-flex align-items-center justify-content-between">
                                                            <a style="font-size: 15px; font-weight: bold; color: #6A6A6A; text-decoration: underline"
                                                               class="d-inline"
                                                               id="existed_file${i}"
                                                               onmouseover="this.style.color='#6FAAF2'"
                                                               onmouseout="this.style.color='#6A6A6A'"
                                                               href="${files[i-1].url}"><i
                                                                    data-feather="file"></i>${files[i-1].name}</a>
                                                            <button class="btn btn-outline-primary" type="button"
                                                                    onclick="DeleteElement(this, null)"
                                                                    style="float: right; height: 35px">X
                                                            </button>
                                                        </div>
                                                        <script>
                                                            file_number++;
                                                        </script>
                                                    </c:forEach>
                                                    <input type="file" id="file-add-input-1" name="files"
                                                           hidden/>
                                                </div>
                                                <script>
                                                    function DeleteElement(elem, id) {
                                                        elem.parentElement.remove();
                                                        console.log(id);
                                                        let agent = navigator.userAgent.toLowerCase();
                                                        if (id != null) {
                                                            if ((navigator.appName === 'Netscape' && navigator.userAgent.search('Trident') !== -1) || (agent.indexOf("msie") !== -1)) {
                                                                $(id).replaceWith($(id).clone(true));
                                                            } else {
                                                                $(id).val('');
                                                            }
                                                        }
                                                        file_number--;
                                                    }
                                                </script>
                                                <div class="col-md-4 mt-3"
                                                     style="left: 50%; transform: translateX(-50%)">
                                                    <button class="btn btn-block btn-outline-primary"
                                                            id="file-add-btn"
                                                            type="button"
                                                            style="align-content: center">파일 추가
                                                    </button>
                                                    <script>
                                                        $('#file-add-btn').click(function () {
                                                            if (file_number >= 3) {
                                                                alert("파일은 최대 3개까지 업로드 가능합니다.");
                                                            } else {
                                                                $('#file-add-input-' + input_index).click();
                                                                console.log(input_index);
                                                                $("#file-add-input-" + input_index).change(function () {
                                                                    console.log("Before" + input_index);
                                                                    if (this.files && this.files[0]) {
                                                                        const reader = new FileReader;
                                                                        reader.onload = function (data) {
                                                                            if (data.total > 10 * 1024 * 1024) {
                                                                                alert("파일용량은 10MB를 넘길 수 없습니다.");
                                                                                let agent = navigator.userAgent.toLowerCase();
                                                                                if ((navigator.appName === 'Netscape' && navigator.userAgent.search('Trident') !== -1) || (agent.indexOf("msie") !== -1)) {
                                                                                    $("#file-add-input-" + input_index).replaceWith($("#file-add-input-" + input_index).clone(true));
                                                                                } else {
                                                                                    $("#file-add-input-" + input_index).val('');
                                                                                }
                                                                                return false;
                                                                            } else {
                                                                                console.log("In " + input_index);
                                                                                console.log($('#file-add-input-' + input_index).val());
                                                                                const fileValue = $('#file-add-input-' + input_index).val().split("\\");
                                                                                const fileName = fileValue[fileValue.length - 1];
                                                                                if (fileName !== "") {
                                                                                    input_index++;
                                                                                    file_number++;
                                                                                    console.log("After" + input_index);
                                                                                    const now_index = input_index - 1;
                                                                                    $("#file-list").append("<div class=\"d-flex align-items-center justify-content-between\"><a style=\"font-size: 15px; font-weight: bold; color: #6A6A6A; text-decoration: underline\" class=\"d-inline\" onmouseover=\"this.style.color='#6FAAF2'\" onmouseout=\"this.style.color='#6A6A6A'\" href=\"javascript: void(0)\"><i data-feather=\"file\"></i>" + fileName + "</a><button class=\"btn btn-outline-primary\" type='button' style=\"float: right;height: 35px\" onclick=\"DeleteElement(this, '#file-add-input-" + now_index + "')\">X</button></div><input type=\"file\" id=\"file-add-input-" + input_index + "\" name=\"files\" hidden/>");
                                                                                }
                                                                            }
                                                                        };
                                                                        reader.readAsDataURL(this.files[0]);
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    </script>
                                                </div>
                                                <script>

                                                </script>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-12">
                                                <label class="label d-flex" for="loudsourcing-content"
                                                       style="font-size: large">
                                                    출품 안내
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-content" rows="10"
                                                          style="line-height: 150%; font-size: large"
                                                >${Loudsourcing.content}</textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-12">
                                                <label class="label d-flex" for="loudsourcing-warning"
                                                       style="font-size: large">
                                                    주의 사항
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-warning" rows="10"
                                                          style="line-height: 150%; font-size: large"
                                                >${Loudsourcing.warning}</textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-4 mb-3 justify-content-around">
                                            <div class="col-md-6 justify-content-center d-flex">
                                                <button type="button" class="btn btn-outline-primary"
                                                        style="width : 50%; height: 150%"
                                                        id="editButton">
                                                    수정
                                                </button>
                                            </div>
                                            <div class="col-md-6 justify-content-center d-flex">
                                                <button type="button" class="btn btn-secondary"
                                                        style="width : 50%; height: 150%"
                                                        onclick="location.href='/admin/loudsourcing_detail.do?loudsourcing_no=${Loudsourcing.loudsourcing_no}'">
                                                    취소
                                                </button>
                                            </div>
                                        </div>
                                    </form>
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
<script src="/assets/vendors/jquery-tags-input/jquery.tagsinput.min.js"></script>
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
<script src="/assets/js/tags-input.js"></script>
<!-- endinject -->
<!-- custom js for this page -->
<script src="/assets/js/dashboard.js"></script>
<script src="/assets/js/datepicker.js"></script>
<script src="/assets/js/data-table.js"></script>
<!-- end custom js for this page -->

</body>
</html>
<script>
    function openWindowPopEdit(url, name) {
        let options = 'top=10, left=10, width=720, height=1040, status=1, scrollbars=1, resizable=1, menubar=0, fullscreen=0, location=0';
        window.open(url, name, options);
    }

    function checkDateValid(){
        let start_date = new Date($('input[name=loudsourcing-start-date]').val());
        let end_date = new Date($('input[name=loudsourcing-end-date]').val());
        let recruitment_end_date = new Date($('input[name=loudsourcing-recruitment-end-date]').val());
        let process_start_date = new Date($('input[name=loudsourcing-process-start-date]').val());
        let process_end_date = new Date($('input[name=loudsourcing-process-end-date]').val());
        let judge_start_date = new Date($('input[name=loudsourcing-judge-start-date]').val());
        if(start_date > end_date){
            alert("날짜를 확인해주세요.");
            return false;
        } else if (process_start_date > process_end_date){
            alert("날짜를 확인해주세요.");
            return false;
        } else if (judge_start_date > end_date){
            alert("날짜를 확인해주세요.");
            return false;
        } else if (recruitment_end_date > end_date){
            alert("날짜를 확인해주세요.");
            return false;
        }
        return true;
    }

    $('#dp-edit-loudsourcing-end-date').on("click", function () {
        checkDate('endDate');
    });

    $('#dp-edit-loudsourcing-recruitment-end-date').on("click", function () {
        checkDate('recruitmentEndDate');
    });

    $('#dp-edit-loudsourcing-process-end-date').on("click", function () {
        checkDate('processEndDate');
    });

    function checkDate(input) {
        switch (input) {
            case 'endDate':
                if ($('input[name=loudsourcing-start-date]') != undefined) {
                    if ($('input[name=loudsourcing-start-date]').val() === "") {
                        alert("시작 일자를 먼저 설정해주세요.");
                        $('#dp-edit-loudsourcing-end-date').datepicker("hide");
                    }
                }
                break;
            case 'recruitmentEndDate':
                if ($('input[name=loudsourcing-start-date]') != undefined) {
                    if ($('input[name=loudsourcing-start-date]').val() === "") {
                        console.log($('#dp-loudsourcing-start-date').val());
                        alert("시작 일자를 먼저 설정해주세요.");
                        $('#dp-edit-loudsourcing-recruitment-end-date').datepicker("hide");
                    } else if ($('input[name=loudsourcing-end-date]').val() === "") {
                        alert("종료 일자를 먼저 설정해주세요.");
                        $('#dp-edit-loudsourcing-recruitment-end-date').datepicker("hide");
                    }
                } else {
                    if ($('input[name=loudsourcing-end-date]').val() === "") {
                        alert("종료 일자를 먼저 설정해주세요.");
                        $('#dp-edit-loudsourcing-recruitment-end-date').datepicker("hide");
                    }
                }
                break;
            case 'processEndDate':
                if ($('input[name=loudsourcing-start-date]') != undefined) {
                    if ($('input[name=loudsourcing-start-date]').val() === "") {
                        alert("시작 일자를 먼저 설정해주세요.");
                        $('#dp-edit-loudsourcing-process-end-date').datepicker("hide");
                    } else if ($('input[name=loudsourcing-end-date]').val() === "") {
                        alert("종료 일자를 먼저 설정해주세요.");
                        $('#dp-edit-loudsourcing-process-end-date').datepicker("hide");
                    } else if ($('input[name=loudsourcing-recruitment-end-date]').val() === "") {
                        alert("모집 종료 일자를 먼저 설정해주세요.");
                        $('#dp-edit-loudsourcing-process-end-date').datepicker("hide");
                    }
                } else {
                    if ($('input[name=loudsourcing-end-date]').val() === "") {
                        alert("종료 일자를 먼저 설정해주세요.");
                        $('#dp-edit-loudsourcing-process-end-date').datepicker("hide");
                    } else if ($('input[name=loudsourcing-recruitment-end-date]').val() === "") {
                        alert("모집 종료 일자를 먼저 설정해주세요.");
                        $('#dp-edit-loudsourcing-process-end-date').datepicker("hide");
                    }
                }
                break;
        }
    }

    function to_date2(date_str) {
        let yyyyMMdd = String(date_str);
        let sYear = yyyyMMdd.substring(0, 4);
        let sMonth = yyyyMMdd.substring(5, 7);
        let sDate = yyyyMMdd.substring(8, 10);

        //alert("sYear :"+sYear +"   sMonth :"+sMonth + "   sDate :"+sDate);
        return new Date(Number(sYear), Number(sMonth) - 1, Number(sDate));
    }

    function formatDate(date) {
        let d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;

        return [year, month, day].join('-');
    }

    function nextDay(date) {
        let dDate = to_date2(date);
        let number = dDate.getDate() + 1;
        let sDate = new Date(dDate.getFullYear(), dDate.getMonth(), number);
        return formatDate(sDate);
    }

    function copyStartDate() {
        document.getElementById("recruitment-start-date").value = $('input[name="loudsourcing-start-date"]').val();
    }

    function copyJudgeDate() {
        $('input[name="loudsourcing-judge-start-date"]').val(nextDay($('input[name="loudsourcing-process-end-date"]').val()));
    }

    function copyProcessStartDate() {
        $('input[name="loudsourcing-process-start-date"]').val(nextDay($('input[name="loudsourcing-recruitment-end-date"]').val()));
    }

    function preventEnterEdit() {
        if (event.keyCode === 13)
            event.returnValue = false;
    }

    $(document).ready(function () {
        let tag = {};
        let counter = 0;

        // 서버 기본 해시태그 리스트를 가져온다.
        let hashtagList = '<c:out value="${Loudsourcing.hashtag}"/>';
        let edit = replaceAll(hashtagList, '&#034;', '"');
        console.log(edit);
        if(edit !== "") {
            let obj = JSON.parse(edit);
            for (let i = 0; i < obj.length; i++) {
                console.log(obj[i]);
                tag[counter] = obj[i];
                console.log(counter);
                $("#tag-list").append("<li class='tag-item' style='margin-bottom: 3px'>" + obj[i] + "<span class='del-btn' idx='" + counter + "'>x</span></li>");
                counter++;
            }
        }

        function replaceAll(str, searchStr, replaceStr) {
            return str.split(searchStr).join(replaceStr);
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

        // 서버에 제공
        $("#tag-form").on("submit", function (e) {
            let value = marginTag(); // return array
            $("#rdTag").val(value);

            $(this).submit();
        });

        $('#tag').on("paste", function () {
            return false;
        });

        $('#tag').on("copy", function () {
            return false;
        })


        $("#tag").on("keypress", function (e) {
            let self = $(this);

            if (e.keyCode === 32 || e.keyCode === 34 || e.keyCode === 91 || e.keyCode === 93) {
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
                                $("#tag-list").append("<li class='tag-item' style='margin-bottom: 3px'>" + tagValue + "<span class='del-btn' idx='" + counter + "'>x</span></li>");
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

        $('input[name=loudsourcing-start-date]').attr("readonly", true);
        $('input[name=loudsourcing-end-date]').attr("readonly", true);
        $('input[name=loudsourcing-recruitment-end-date]').attr("readonly", true);
        $('input[name=loudsourcing-process-start-date]').attr("readonly", true);
        $('input[name=loudsourcing-process-end-date]').attr("readonly", true);
        $('input[name=loudsourcing-judge-start-date]').attr("readonly", true);

        $('#editButton').on("click", function () {
            console.log("clicked");
            editLoudSourcing();
        });

        $('textarea[id="loudsourcing-name"]').on('paste', function (e) {
            let data;
            console.log("name");
            if (window.clipboardData) {
                data = window.clipboardData.getData("Text");
            } else {
                data = e.originalEvent.clipboardData.getData("Text");
            }
            let match = /r|\n/.exec(data);
            if (match) {
                data = data.replace(/(\r\n|\n|\r)/gm, "");
                $(this).val($(this).val() + data);
                e.originalEvent.preventDefault();
            }
        });

        $('textarea[id="loudsourcing-host"]').on('paste', function (e) {
            let data;
            console.log("host")
            if (window.clipboardData) {
                data = window.clipboardData.getData("Text");
            } else {
                data = e.originalEvent.clipboardData.getData("Text");
            }
            let match = /r|\n/.exec(data);
            if (match) {
                data = data.replace(/(\r\n|\n|\r)/gm, "");
                $(this).val($(this).val() + data);
                e.originalEvent.preventDefault();
            }
        });



        function editLoudSourcing() {
            if (!inspection("loudsourcing-name", "loudsourcing_name")) {
                return false;
            } else if (!inspection("loudsourcing-host", "host_name")) {
                return false;
            } else if (!inspection("loudsourcing-reward", "reward")) {
                return false;
            } else if (!inspection("total-recruit-number", "total_recruit_number")) {
                return false;
            } else if (!inspection("loudsourcing-content", "loudsourcing_content")) {
                return false;
            } else if (!inspection("loudsourcing-warning", "warning")) {
                return false;
            } else if (!inspection("loudsourcing-start-date", "date")) {
                return false;
            } else if (!inspection("loudsourcing-end-date", "date")) {
                return false;
            } else if (!inspection("loudsourcing-recruitment-end-date", "date")) {
                return false;
            } else if (!inspection("loudsourcing-process-start-date", "date")) {
                return false;
            } else if (!inspection("loudsourcing-process-end-date", "date")) {
                return false;
            } else if (!inspection("loudsourcing-judge-start-date", "date")) {
                return false;
            } else if (!checkDateValid()){
                return false;
            }

            let start_date = $('input[name=loudsourcing-start-date]').val();
            let end_date = $('input[name=loudsourcing-end-date]').val();
            let recruitment_end_date = $('input[name=loudsourcing-recruitment-end-date]').val();
            let process_start_date = $('input[name=loudsourcing-process-start-date]').val();
            let process_end_date = $('input[name=loudsourcing-process-end-date]').val();
            let judge_start_date = $('input[name=loudsourcing-judge-start-date]').val();

            let form = $("#loudsourcingEditForm")[0];
            let formData = new FormData(form);
            console.log("existed_file url : " + $("#existed_file1").attr("href"));
            console.log("existed_file fileName : " + $("#existed_file1").text());
            let OriginalFileObject = [];
            for (let i = 1; i <= ${files.size()}; i++) {
                let OriginalFileData = {
                    name: $("#existed_file" + i).text(),
                    url: $("#existed_file" + i).attr("href")
                };
                OriginalFileObject.push(OriginalFileData);
            }
            formData.append("original_files", JSON.stringify(OriginalFileObject));
            let status;
            switch ($("#loudsourcing-status").val()) {
                case "모집" : {
                    status = "recruitment";
                }
                    break;
                case "진행" : {
                    status = "process";
                }
                    break;
                case "심사" : {
                    status = "judge";
                }
                    break;
                case "종료" : {
                    status = "end";
                }
                    break;
            }

            console.log("status : " + status);
            console.log("start_date : " + start_date);
            let loudsourcingData = {
                "loudsourcing_no": ${Loudsourcing.loudsourcing_no},
                "name": $("#loudsourcing-name").val(),
                "status": status,
                "host": $("#loudsourcing-host").val(),
                "reward": $("#loudsourcing-reward").val(),
                "warning": $("#loudsourcing-warning").val(),
                "hashtag": marginTag().toString(),
                "content": $("#loudsourcing-content").val(),
                "start_date": start_date,
                "recruitment_end_date": recruitment_end_date,
                "process_start_date": process_start_date,
                "process_end_date": process_end_date,
                "judge_date": judge_start_date,
                "end_date": end_date,
                "total_recruitment_number": $("#total-recruit-number").val()
            };
            formData.append("loudsourcing", JSON.stringify(loudsourcingData));
            $.ajax({
                type: 'POST',
                enctype: 'multipart/form-data',
                url: '/admin/loudsourcing_edit_post.do',
                contentType: false,
                processData: false,
                data: formData
            }).done(function (result) {
                console.log(result);
                if (result === 0) {
                    alert("수정이 완료되었습니다.");
                    window.location.href = '/admin/loudsourcing_detail.do?loudsourcing_no=${Loudsourcing.loudsourcing_no}';
                } else {
                    alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    window.location.href = '/admin/loudsourcing_detail.do?loudsourcing_no=${Loudsourcing.loudsourcing_no}';
                }
            }).fail(function (error) {
                console.log(error);
                window.location.href = '/admin/loudsourcing_detail.do?loudsourcing_no=${Loudsourcing.loudsourcing_no}';
            })
        }
    });
</script>

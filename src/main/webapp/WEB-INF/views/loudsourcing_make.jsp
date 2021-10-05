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
    <title>새 크라우드 만들기</title>
    <!-- core:css -->
    <link rel="stylesheet" href="../assets/vendors/core/core.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <link rel="stylesheet" href="../assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="../assets/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
    <link rel="stylesheet" href="../assets/vendors/jquery-tags-input/jquery.tagsinput.min.css">
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
            margin-bottom: 10px;
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
    let file_number = 0;
</script>
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
                                    <h6 class="card-title" style="font-size: x-large">크라우드 제작</h6>
                                    <form id="loudsourcingEditForm">
                                        <div class="row justify-content-around mt-3">
                                            <div class="col-md-6">
                                                <label class="label d-flex" for="loudsourcing-make-name"
                                                       style="font-size: larger">
                                                    공모전 이름
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-make-name" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center; white-space: nowrap"
                                                          onkeypress="preventEnter()" placeholder="공모전 이름을 입력하세요."
                                                ></textarea>
                                            </div>
                                            <div class="col-md-6">

                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-6">
                                                <label class="label" style="font-size: larger">
                                                    공모전 사진 - [사진을 변경하려면 사진을 클릭하세요.]
                                                </label>
                                                <input type="file" id="loudsourcing-make-img" name="img"
                                                       accept="image/*"
                                                       hidden/>
                                                <div style="height: 800px; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
                                                     class="d-flex justify-content-center " id="image-container">
                                                    <img class="img-fluid" src=""
                                                         onerror="this.src='https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/fan_main_img_basic.png'"
                                                         onclick="$('#loudsourcing-make-img').click()"
                                                         style="height: 100%; object-fit: contain; cursor: pointer">
                                                </div>
                                                <script>

                                                    $("#loudsourcing-make-img").change(function () {
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
                                                <label class="label d-flex" for="loudsourcing-make-status"
                                                       style="font-size: larger">
                                                    상태
                                                </label>
                                                <select class="form-control" id="loudsourcing-make-status"
                                                        style="font-size: large;text-align: center; text-align-last: center">
                                                    <option selected>모집</option>
                                                </select>
                                                <label class="label d-flex" for="loudsourcing-make-host"
                                                       style="font-size: larger">
                                                    주최
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-make-host" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center; white-space: nowrap"
                                                          onkeypress="preventEnter()" placeholder="주최 광고주를 입력해주세요."
                                                ></textarea>
                                                <label class="label d-flex" for="loudsourcing-make-reward"
                                                       style="font-size: larger">
                                                    상금
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-make-reward"
                                                          rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          onkeypress="onlyNumber()"
                                                          oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                                                          placeholder="상금을 입력해주세요."
                                                ></textarea>
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
                                                <label class="label d-flex" for="make-total-recruit-number"
                                                       style="font-size: larger">
                                                    최소 모집 인원
                                                </label>
                                                <textarea class="form-control" id="make-total-recruit-number"
                                                          rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          onkeypress="onlyNumber()"
                                                          oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                                                          placeholder="최소 모집 인원을 입력해주세요."
                                                ></textarea>
                                                <label class="label d-flex" for="total-date"
                                                       style="font-size: larger">
                                                    총 기간
                                                </label>
                                                <div class="row justify-content-around" id="total-date">
                                                    <div class="col-md-6">
                                                        <div class="input-group date datepicker"
                                                             id="dp-loudsourcing-start-date">
                                                            <input type="text" class="form-control"
                                                                   name="loudsourcing-start-date"
                                                                   id="loudsourcing-start-date"
                                                                   onchange="copyStartDate()"
                                                                   style="font-size: large;line-height: normal;text-align: center"><span
                                                                class="input-group-addon"><i
                                                                data-feather="calendar"></i></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="input-group date datepicker"
                                                             id="dp-loudsourcing-end-date">
                                                            <input type="text" class="form-control"
                                                                   name="loudsourcing-end-date"
                                                                   id="loudsourcing-end-date"
                                                                   style="font-size: large;line-height: normal;text-align: center"><span
                                                                class="input-group-addon" id="click-test"><i
                                                                data-feather="calendar"></i></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <label class="label d-flex" for="recruitment-date"
                                                       style="font-size: larger">
                                                    모집 기간
                                                </label>
                                                <div class="row justify-content-around" id="recruitment-date">
                                                    <div class="col-md-6">
                                                <textarea class="form-control" id="recruitment-start-date" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          disabled></textarea>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="input-group date datepicker"
                                                             id="dp-loudsourcing-recruitment-end-date">
                                                            <input type="text" class="form-control"
                                                                   name="loudsourcing-recruitment-end-date"
                                                                   id="loudsourcing-recruitment-end-date"
                                                                   style="font-size: large;line-height: normal;text-align: center"
                                                                   onchange="copyProcessStartDate()"
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
                                                             id="dp-loudsourcing-process-start-date">
                                                            <input type="text" class="form-control"
                                                                   name="loudsourcing-process-start-date"
                                                                   id="loudsourcing-process-start-date"
                                                                   style="font-size: large;line-height: normal;text-align: center"
                                                                   disabled
                                                            ><span class="input-group-addon" hidden></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="input-group date datepicker"
                                                             id="dp-loudsourcing-process-end-date">
                                                            <input type="text" class="form-control"
                                                                   name="loudsourcing-process-end-date"
                                                                   id="loudsourcing-process-end-date"
                                                                   style="font-size: large;line-height: normal;text-align: center"
                                                                   onchange="copyJudgeDate()"
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
                                                     id="dp-loudsourcing-judge-start-date">
                                                    <input type="text" class="form-control"
                                                           name="loudsourcing-judge-start-date"
                                                           id="loudsourcing-judge-start-date"
                                                           style="font-size: large;text-align: center" disabled
                                                    ><span class="input-group-addon" hidden></span>
                                                </div>
                                                <label class="label d-flex" for="file-list"
                                                       style="font-size: larger">
                                                    파일 - [파일은 최대 3개까지 업로드 가능하며 파일 용량은 하나당 10MB를 넘을 수 없습니다.]
                                                </label>
                                                <div style="background-color: #ffffff; border: 1px solid black; line-height: 150%"
                                                     id="file-list">
                                                    <input type="file" id="file-add-input-1" name="files"
                                                           hidden/>
                                                </div>
                                                <script>
                                                    function DeleteElement(elem, id) {
                                                        elem.parentElement.remove();
                                                        console.log(id);
                                                        if (id != null) {
                                                            $(id).val('');
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
                                                                            console.log(data);
                                                                            if (data.total > 10 * 1024 * 1024) {
                                                                                alert("파일용량은 10MB를 넘길 수 없습니다.");
                                                                                $("#file-add-input-" + input_index).val('');
                                                                                return false;
                                                                            } else {
                                                                                console.log("In " + input_index);
                                                                                console.log($('#file-add-input-' + input_index).val());
                                                                                const fileValue = $('#file-add-input-' + input_index).val().split("\\");
                                                                                const fileName = fileValue[fileValue.length - 1];
                                                                                input_index++;
                                                                                file_number++;
                                                                                console.log("After" + input_index);
                                                                                const now_index = input_index - 1;
                                                                                $("#file-list").append("<div class=\"d-flex align-items-center justify-content-between\"><a style=\"font-size: 15px; font-weight: bold; color: #6A6A6A; text-decoration: underline\" class=\"d-inline\" onmouseover=\"this.style.color='#6FAAF2'\" onmouseout=\"this.style.color='#6A6A6A'\" href=\"javascript: void(0)\"><i data-feather=\"file\"></i>" + fileName + "</a><button class=\"btn btn-outline-primary\" type='button' style=\"float: right;height: 35px\" onclick=\"DeleteElement(this, '#file-add-input-" + now_index + "')\">X</button></div><input type=\"file\" id=\"file-add-input-" + input_index + "\" name=\"files\" hidden/>");
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
                                                <label class="label d-flex" for="loudsourcing-make-content"
                                                       style="font-size: large">
                                                    출품 안내
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-make-content"
                                                          rows="10"
                                                          style="line-height: 150%; font-size: large"
                                                          placeholder="출품 안내를 입력해주세요."
                                                ></textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-3 justify-content-around">
                                            <div class="col-md-12">
                                                <label class="label d-flex" for="loudsourcing-make-warning"
                                                       style="font-size: large">
                                                    주의 사항
                                                </label>
                                                <textarea class="form-control" id="loudsourcing-make-warning"
                                                          rows="10"
                                                          style="line-height: 150%; font-size: large"
                                                          placeholder="주의사항을 입력해주세요."
                                                ></textarea>
                                            </div>
                                        </div>
                                        <div class="row mt-4 mb-3 justify-content-around">
                                            <div class="col-md-6 justify-content-center d-flex">
                                                <button type="button" id="makeButton"
                                                        class="btn btn-outline-primary"
                                                        style="width : 50%; height: 150%"
                                                >
                                                    확인
                                                </button>
                                            </div>
                                            <div class="col-md-6 justify-content-center d-flex">
                                                <button type="button" class="btn btn-secondary"
                                                        style="width : 50%; height: 150%"
                                                        onclick="location.href = '/admin/loudsourcing_recruitment.do';">
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
<script src="../assets/vendors/core/core.js"></script>
<!-- endinject -->
<!-- plugin js for this page -->
<script src="../assets/vendors/chartjs/Chart.min.js"></script>
<script src="../assets/vendors/jquery.flot/jquery.flot.js"></script>
<script src="../assets/vendors/jquery.flot/jquery.flot.resize.js"></script>
<script src="../assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
<script src="../assets/vendors/jquery-tags-input/jquery.tagsinput.min.js"></script>
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
<script src="../assets/js/tags-input.js"></script>
<script src="../assets/js/datepicker.js"></script>
<script src="../assets/js/data-table.js"></script>
<!-- end custom js for this page -->
</body>
</html>
<script>
    $('#dp-loudsourcing-end-date').on("click", function () {
        checkDate('endDate');
    });

    $('#dp-loudsourcing-recruitment-end-date').on("click", function () {
        checkDate('recruitmentEndDate');
    });

    $('#dp-loudsourcing-process-end-date').on("click", function () {
        checkDate('processEndDate');
    });

    $('textarea[id="loudsourcing-make-name"]').on('paste', function (e) {
        let data;
        if (window.clipboardData) {
            data = window.clipboardData.getData("Text");
        } else {
            data = e.originalEvent.clipboardData.getData("Text");
        }
        let match = /r|\n/.exec(data);
        if(match){
            data = data.replace(/(\r\n|\n|\r)/gm,"");
            $(this).val($(this).val() + data);
            e.originalEvent.preventDefault();
        }
    });

    $('textarea[id="loudsourcing-make-host"]').on('paste', function (e) {
        let data;
        if (window.clipboardData) {
            data = window.clipboardData.getData("Text");
        } else {
            data = e.originalEvent.clipboardData.getData("Text");
        }
        let match = /r|\n/.exec(data);
        if(match){
            data = data.replace(/(\r\n|\n|\r)/gm,"");
            $(this).val($(this).val() + data);
            e.originalEvent.preventDefault();
        }
    });

    function checkDate(input) {
        switch (input) {
            case 'endDate':
                if ($('input[name=loudsourcing-start-date]').val() === "") {
                    alert("시작 일자를 먼저 설정해주세요.");
                    $('#dp-loudsourcing-end-date').datepicker("hide");
                }
                break;
            case 'recruitmentEndDate':
                if ($('input[name=loudsourcing-start-date]').val() === "") {
                    console.log($('#dp-loudsourcing-start-date').val());
                    alert("시작 일자를 먼저 설정해주세요.");
                    $('#dp-loudsourcing-recruitment-end-date').datepicker("hide");
                } else if ($('input[name=loudsourcing-end-date]').val() === "") {
                    alert("종료 일자를 먼저 설정해주세요.");
                    $('#dp-loudsourcing-recruitment-end-date').datepicker("hide");
                }
                break;
            case 'processEndDate':
                if ($('input[name=loudsourcing-start-date]').val() === "") {
                    alert("시작 일자를 먼저 설정해주세요.");
                    $('#dp-loudsourcing-process-end-date').datepicker("hide");
                } else if ($('input[name=loudsourcing-end-date]').val() === "") {
                    alert("종료 일자를 먼저 설정해주세요.");
                    $('#dp-loudsourcing-process-end-date').datepicker("hide");
                } else if ($('input[name=loudsourcing-recruitment-end-date]').val() === "") {
                    alert("모집 종료 일자를 먼저 설정해주세요.");
                    $('#dp-loudsourcing-process-end-date').datepicker("hide");
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

    function preventEnter() {
        if (event.keyCode === 13)
            event.returnValue = false;
    }

    function onlyNumber() {
        if (event.keyCode < 48 || event.keyCode > 57 || event.keyCode === 13)
            event.returnValue = false;
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

    $(document).ready(function () {
        let tag = {};
        let counter = 0;

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
                    if (tagValue.length <= 5) {

                        // 같은 태그가 있는지 검사한다. 있다면 해당값이 array 로 return 된다.
                        let result = Object.values(tag).filter(function (word) {
                            return word === tagValue;
                        });

                        // 해시태그가 중복되었는지 확인
                        if (result.length === 0) {
                            if (counter < 10) {
                                $("#tag-list").append("<li class='tag-item'>" + tagValue + "<span class='del-btn' idx='" + counter + "'>x</span></li>");
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

        $("#makeButton").on("click", function () {
            uploadLoudSourcing();
        });

        $('input[name=loudsourcing-start-date]').attr("readonly", true);
        $('input[name=loudsourcing-end-date]').attr("readonly", true);
        $('input[name=loudsourcing-recruitment-end-date]').attr("readonly", true);
        $('input[name=loudsourcing-process-start-date]').attr("readonly", true);
        $('input[name=loudsourcing-process-end-date]').attr("readonly", true);
        $('input[name=loudsourcing-judge-start-date]').attr("readonly", true);

        function uploadLoudSourcing() {
            if (!inspection("loudsourcing-make-name", "loudsourcing_name")) {
                return false;
            } else if (!inspection("loudsourcing-make-host", "host_name")) {
                return false;
            } else if (!inspection("loudsourcing-make-reward", "reward")) {
                return false;
            } else if (!inspection("make-total-recruit-number", "total_recruit_number")) {
                return false;
            } else if (!inspection("loudsourcing-make-content", "loudsourcing_content")) {
                return false;
            } else if (!inspection("loudsourcing-make-warning", "warning")) {
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
            }

            let start_date = $('input[name=loudsourcing-start-date]').val();
            let end_date = $('input[name=loudsourcing-end-date]').val();
            let recruitment_end_date = $('input[name=loudsourcing-recruitment-end-date]').val();
            let process_start_date = $('input[name=loudsourcing-process-start-date]').val();
            let process_end_date = $('input[name=loudsourcing-process-end-date]').val();
            let judge_start_date = $('input[name=loudsourcing-judge-start-date]').val();
            console.log("start_date : " + start_date);
            console.log("end_date : " + end_date);
            console.log("recruitment_end_date : " + recruitment_end_date);
            console.log("process_start_date : " + process_start_date);
            console.log("process_end_date : " + process_end_date);
            console.log("judge_start_date : " + judge_start_date);

            let form = $("#loudsourcingEditForm")[0];
            let formData = new FormData(form);
            let status;
            switch ($("#loudsourcing-make-status").val()) {
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
            let loudsourcingData = {
                "name": $("#loudsourcing-make-name").val(),
                "status": status,
                "host": $("#loudsourcing-make-host").val(),
                "reward": $("#loudsourcing-make-reward").val(),
                "warning": $("#loudsourcing-make-warning").val(),
                "hashtag": marginTag().toString(),
                "content": $("#loudsourcing-make-content").val(),
                "start_date": start_date,
                "recruitment_end_date": recruitment_end_date,
                "process_start_date": process_start_date,
                "process_end_date": process_end_date,
                "judge_date": judge_start_date,
                "end_date": end_date,
                "total_recruitment_number": $("#make-total-recruit-number").val()
            };
            formData.append("loudsourcing", JSON.stringify(loudsourcingData));
            $.ajax({
                type: 'POST',
                enctype: 'multipart/form-data',
                url: '/admin/loudsourcing_make_post.do',
                contentType: false,
                processData: false,
                data: formData
            }).done(function (result) {
                console.log(result);
                if (result === 0) {
                    alert("새 크라우드를 생성하였습니다.");
                    window.location.href = '/admin/loudsourcing_recruitment.do';
                } else {
                    alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                    window.location.href = '/admin/loudsourcing_recruitment.do';
                }
            }).fail(function (error) {
                console.log(error);
                window.location.href = '/admin/loudsourcing_recruitment.do';
            })
        }
    })
</script>


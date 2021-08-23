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
            background-color: #98B6FF;
            color: #000;
            opacity: 80%;
            border-radius: 20px;
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
                <div class="col-md-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body" id="main-card">
                            <h6 class="card-title" style="font-size: x-large">크라우드 상세</h6>
                            <form id="loudsourcingEditForm">
                                <div class="row justify-content-center">
                                    <div class="col-md-10">
                                        <div class="card" style="background-color: #FFFFFf; border-radius: 1.5%">
                                            <div class="row justify-content-around mt-3">
                                                <div class="col-md-5">
                                                    <label class="label d-flex" for="loudsourcing-make-name"
                                                           style="font-size: larger">
                                                        공모전 이름
                                                    </label>
                                                    <textarea class="form-control" id="loudsourcing-make-name" rows="1"
                                                              style="line-height: 150%; font-size: large; text-align: center"
                                                    ></textarea>
                                                </div>
                                                <div class="col-md-5">

                                                </div>
                                            </div>
                                            <div class="row mt-3 justify-content-around">
                                                <div class="col-md-5">
                                                    <label class="label" style="font-size: larger">
                                                        공모전 사진 - [사진을 변경하려면 사진을 클릭하세요.]
                                                    </label>
                                                    <input type="file" id="loudsourcing-make-img" name="img" accept="image/*"
                                                           hidden/>
                                                    <div style="width: 29vw; height: 800px; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
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
                                                <div class="col-md-5">
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
                                                              style="line-height: 150%; font-size: large; text-align: center"
                                                    ></textarea>
                                                    <label class="label d-flex" for="loudsourcing-make-reward"
                                                           style="font-size: larger">
                                                        상금
                                                    </label>
                                                    <textarea class="form-control" id="loudsourcing-make-reward" rows="1"
                                                              style="line-height: 150%; font-size: large; text-align: center"
                                                    ></textarea>
                                                    <label class="label d-flex"
                                                           style="font-size: larger">
                                                        해시태그
                                                    </label>
                                                    <div class="tr_hashTag_area">
                                                        <div class="form-group">
                                                            <input type="hidden" value="" name="tag" id="rdTag"/>
                                                        </div>

                                                        <ul id="tag-list"></ul>

                                                        <div class="form-group">
                                                            <input type="text" id="tag" size="7" placeholder="엔터로 해시태그를 등록해주세요. 최대 5자까지 가능하며 해시태그는 최대 10개 등록 가능합니다."
                                                                   style="width: 100%;"/>
                                                        </div>
                                                    </div>
                                                    <label class="label d-flex" for="make-total-recruit-number"
                                                           style="font-size: larger">
                                                        총 모집 인원
                                                    </label>
                                                    <textarea class="form-control" id="make-total-recruit-number" rows="1"
                                                              style="line-height: 150%; font-size: large; text-align: center"
                                                    ></textarea>
                                                    <label class="label d-flex" for="total-date"
                                                           style="font-size: larger">
                                                        총 기간
                                                    </label>
                                                    <div class="row justify-content-around" id="total-date">
                                                        <div class="col-md-5">
                                                        <textarea class="form-control" id="start-date" rows="1"
                                                                  style="line-height: 150%; font-size: large; text-align: center"
                                                        ></textarea>
                                                        </div>
                                                        <div class="col-md-5">
                                                        <textarea class="form-control" id="end-date" rows="1"
                                                                  style="line-height: 150%; font-size: large; text-align: center"
                                                        ></textarea>
                                                        </div>
                                                    </div>
                                                    <label class="label d-flex" for="recruitment-date"
                                                           style="font-size: larger">
                                                        모집 기간
                                                    </label>
                                                    <div class="row justify-content-around" id="recruitment-date">
                                                        <div class="col-md-5">
                                                <textarea class="form-control" id="recruitment-start-date" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                          disabled></textarea>
                                                        </div>
                                                        <div class="col-md-5">
                                                <textarea class="form-control" id="recruitment-end-date" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                ></textarea>
                                                        </div>
                                                    </div>
                                                    <label class="label d-flex" for="process-date"
                                                           style="font-size: larger">
                                                        진행 기간
                                                    </label>
                                                    <div class="row justify-content-around" id="process-date">
                                                        <div class="col-md-5">
                                                <textarea class="form-control" id="process-start-date" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                ></textarea>
                                                        </div>
                                                        <div class="col-md-5">
                                                <textarea class="form-control" id="process-end-date" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                ></textarea>
                                                        </div>
                                                    </div>
                                                    <label class="label d-flex" for="judge-date"
                                                           style="font-size: larger">
                                                        심사 시작 일자
                                                    </label>
                                                    <textarea class="form-control" id="judge-date" rows="1"
                                                              style="line-height: 150%; font-size: large; text-align: center"
                                                    ></textarea>
                                                    <label class="label d-flex" for="judge-date"
                                                           style="font-size: larger">
                                                        파일
                                                    </label>
                                                    <div style="background-color: #d9dadb" id="file-list">
                                                        <input type="file" id="file-add-input-1" name="files"
                                                               hidden/>
                                                    </div>
                                                    <script>
                                                        function DeleteElement(elem) {
                                                            elem.parentElement.remove();
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
                                                                console.log(input_index);
                                                                $("#file-add-input-" + input_index).change(function () {
                                                                    console.log("Before" + input_index);
                                                                    if (this.files && this.files[0]) {
                                                                        const reader = new FileReader;
                                                                        reader.onload = function (data) {
                                                                            console.log("In " + input_index)
                                                                            console.log($('#file-add-input-' + input_index).val());
                                                                            const fileValue = $('#file-add-input-' + input_index).val().split("\\");
                                                                            const fileName = fileValue[fileValue.length - 1];
                                                                            input_index++;
                                                                            console.log("After" + input_index);
                                                                            $("#file-list").append("<div class=\"mt-1 d-flex align-items-center justify-content-between\"><a style=\"font-size: 15px; font-weight: bold; color: #6A6A6A; text-decoration: underline\" class=\"d-inline\" onmouseover=\"this.style.color='#6FAAF2'\" onmouseout=\"this.style.color='#6A6A6A'\" href=\"#\"><i data-feather=\"file\"></i>" + fileName + "</a><button class=\"btn btn-outline-primary\" style=\"float: right\" onclick=\"DeleteElement(this)\">X</button></div><input type=\"file\" id=\"file-add-input-" + input_index + "\" name=\"files\" hidden/>");
                                                                        };
                                                                        reader.readAsDataURL(this.files[0]);
                                                                    }
                                                                });
                                                                $('#file-add-input-' + input_index).click()
                                                            })
                                                        </script>
                                                    </div>
                                                    <script>

                                                    </script>
                                                </div>
                                            </div>
                                            <div class="row mt-3 justify-content-around">
                                                <div class="col-md-11">
                                                    <label class="label d-flex" for="loudsourcing-make-content"
                                                           style="font-size: large">
                                                        출품 안내
                                                    </label>
                                                    <textarea class="form-control" id="loudsourcing-make-content" rows="10"
                                                              style="line-height: 150%; font-size: large"
                                                    ></textarea>
                                                </div>
                                            </div>
                                            <div class="row mt-3 justify-content-around">
                                                <div class="col-md-11">
                                                    <label class="label d-flex" for="loudsourcing-make-warning"
                                                           style="font-size: large">
                                                        주의 사항
                                                    </label>
                                                    <textarea class="form-control" id="loudsourcing-make-warning" rows="10"
                                                              style="line-height: 150%; font-size: large"
                                                    ></textarea>
                                                </div>
                                            </div>
                                            <div class="row mt-4 mb-3 justify-content-around">
                                                <div class="col-md-6 justify-content-center d-flex">
                                                    <button type="button" id="makeButton" class="btn btn-outline-primary"
                                                            >
                                                        확인
                                                    </button>
                                                </div>
                                                <div class="col-md-6 justify-content-center d-flex">
                                                    <button type="button" class="btn btn-secondary"
                                                            onclick="location.href = '/admin/loudsourcing_recruitment.do';">
                                                        취소
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
    <script>
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


            $("#tag").on("keypress", function (e) {
                let self = $(this);

                //엔터나 스페이스바 눌렀을때 실행
                if (e.key === "Enter" || e.keyCode === 32) {

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

            $("#makeButton").on("click", function(){
               uploadLoudSourcing();
            });

            function uploadLoudSourcing() {
                if (!inspection("loudsourcing-make-name", "loudsourcing_name")) {
                    return false;
                } else if (!inspection("loudsourcing-make-host", "host_name")){
                    return false;
                } else if (!inspection("loudsourcing-make-reward", "reward")){
                    return false;
                } else if(!inspection("make-total-recruit-number", "total_recruit_number")){
                    return false;
                } else if (!inspection("loudsourcing-make-content", "loudsourcing_content")){
                    return false;
                } else if (!inspection("loudsourcing-make-warning", "warning")){
                    return false;
                }

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
                    "hashtag": marginTag(),
                    "content": $("#loudsourcing-make-content").val(),
                    "start_date": $("#start-date").val(),
                    "recruitment_end_date": $("#recruitment-end-date").val(),
                    "process_start_date": $("#process-start-date").val(),
                    "process_end_date": $("#process-end-date").val(),
                    "judge_date": $("#judge-date").val(),
                    "end_date": $("#end-date").val(),
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
                        window.location.href = 'http://localhost:8080/admin/loudsourcing_recruitment.do';
                    } else {
                        alert("알 수 없는 오류가 발생하였습니다. 관리자에게 문의해주세요.");
                        window.location.href = 'http://localhost:8080/admin/loudsourcing_recruitment.do';
                    }
                }).fail(function (error) {
                    console.log(error);
                    window.location.href = 'http://localhost:8080/admin/loudsourcing_recruitment.do';
                })
            }
        })
    </script>
</body>
</html>

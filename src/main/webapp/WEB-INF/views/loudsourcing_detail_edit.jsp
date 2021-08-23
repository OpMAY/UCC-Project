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
                                <c:set var="Loudsourcing" value="${Loudsourcing}"/>
                                <c:set var="files" value="${files}"/>
                                <div class="row justify-content-center">
                                    <div class="col-md-10">
                                        <div class="card" style="background-color: #FFFFFf; border-radius: 1.5%">
                                            <div class="row justify-content-around mt-3">
                                                <div class="col-md-5">
                                                    <label class="label d-flex" for="loudsourcing-name"
                                                           style="font-size: larger">
                                                        공모전 이름
                                                    </label>
                                                    <textarea class="form-control" id="loudsourcing-name" rows="1"
                                                              style="line-height: 150%; font-size: large; text-align: center"
                                                    >${Loudsourcing.name}</textarea>
                                                </div>
                                                <div class="col-md-5">
                                                    <button type="button" class="btn btn-outline-primary mt-4"
                                                            style="float:right; font-size: large" onclick="openWindowPopEdit('/admin/advertiser_edit.do?loudsourcing_no=${Loudsourcing.loudsourcing_no}', '광고 발주자 상세정보 수정')">
                                                        광고 발주자 상세정보 수정
                                                    </button>
                                                </div>
                                            </div>
                                            <div class="row mt-3 justify-content-around">
                                                <div class="col-md-5">
                                                    <label class="label" style="font-size: larger">
                                                        공모전 사진 - [사진을 변경하려면 사진을 클릭하세요.]
                                                    </label>
                                                    <input type="file" id="loudsourcing-img" name="img" accept="image/*"
                                                           hidden/>
                                                    <div style="width: 29vw; height: 800px; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
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
                                                <div class="col-md-5">
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
                                                    >${Loudsourcing.host}</textarea>
                                                    <label class="label d-flex" for="loudsourcing-reward"
                                                           style="font-size: larger">
                                                        상금
                                                    </label>
                                                    <textarea class="form-control" id="loudsourcing-reward" rows="1"
                                                              style="line-height: 150%; font-size: large; text-align: center"
                                                    >${Loudsourcing.reward}</textarea>
                                                    <label class="label d-flex" for="loudsourcing-hashtag"
                                                           style="font-size: larger">
                                                        해시태그 - [추가하려면 입력하고 엔터를 누르세요.]
                                                    </label>
                                                    <div>
                                                        <input hidden="hidden"/>
                                                        <input name="tags" id="loudsourcing-hashtag"
                                                               value="${Loudsourcing.hashtag}"/>
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
                                                    <div class="row justify-content-around" id="total-date">
                                                        <div class="col-md-5">
                                                        <textarea class="form-control" id="start-date" rows="1"
                                                                  style="line-height: 150%; font-size: large; text-align: center"
                                                        >${Loudsourcing.start_date}</textarea>
                                                        </div>
                                                        <div class="col-md-5">
                                                        <textarea class="form-control" id="end-date" rows="1"
                                                                  style="line-height: 150%; font-size: large; text-align: center"
                                                        >${Loudsourcing.end_date}</textarea>
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
                                                          disabled>${Loudsourcing.start_date}</textarea>
                                                        </div>
                                                        <div class="col-md-5">
                                                <textarea class="form-control" id="recruitment-end-date" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                >${Loudsourcing.recruitment_end_date}</textarea>
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
                                                >${Loudsourcing.process_start_date}</textarea>
                                                        </div>
                                                        <div class="col-md-5">
                                                <textarea class="form-control" id="process-end-date" rows="1"
                                                          style="line-height: 150%; font-size: large; text-align: center"
                                                >${Loudsourcing.process_end_date}</textarea>
                                                        </div>
                                                    </div>
                                                    <label class="label d-flex" for="judge-date"
                                                           style="font-size: larger">
                                                        심사 시작 일자
                                                    </label>
                                                    <textarea class="form-control" id="judge-date" rows="1"
                                                              style="line-height: 150%; font-size: large; text-align: center"
                                                    >${Loudsourcing.judge_date}</textarea>
                                                    <label class="label d-flex" for="judge-date"
                                                           style="font-size: larger">
                                                        파일
                                                    </label>
                                                    <div style="background-color: #d9dadb" id="file-list">
                                                        <c:forEach var="i" begin="1" end="${files.size()}">
                                                            <div class="mt-1 d-flex align-items-center justify-content-between">
                                                                <a style="font-size: 15px; font-weight: bold; color: #6A6A6A; text-decoration: underline"
                                                                   class="d-inline"
                                                                   id="existed_file${i}"
                                                                   onmouseover="this.style.color='#6FAAF2'"
                                                                   onmouseout="this.style.color='#6A6A6A'"
                                                                   href="${files[i-1].url}"><i
                                                                        data-feather="file"></i>${files[i-1].name}</a>
                                                                <button class="btn btn-outline-primary"
                                                                        onclick="DeleteElement(this)"
                                                                        style="float: right">X
                                                                </button>
                                                            </div>
                                                        </c:forEach>
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
                                                <div class="col-md-11">
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
                                                            onclick="editLoudSourcing()">
                                                        수정
                                                    </button>
                                                </div>
                                                <div class="col-md-6 justify-content-center d-flex">
                                                    <button type="button" class="btn btn-secondary"
                                                            onclick="location.href='/admin/loudsourcing_detail.do?loudsourcing_no=${Loudsourcing.loudsourcing_no}'">
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
    <script src="../assets/js/tags-input.js"></script>
    <!-- endinject -->
    <!-- custom js for this page -->
    <script src="../assets/js/dashboard.js"></script>
    <script src="../assets/js/datepicker.js"></script>
    <script src="../assets/js/data-table.js"></script>
    <!-- end custom js for this page -->
    <script>
        function openWindowPopEdit(url, name){
            let options = 'top=10, left=10, width=720, height=1040, status=1, scrollbars=1, resizable=1, menubar=0, fullscreen=0, location=0';
            window.open(url, name, options);
        }
    </script>
    <script>
        function editLoudSourcing() {
            if (!inspection("loudsourcing-name", "loudsourcing_name")) {
                return false;
            } else if (!inspection("loudsourcing-host", "host_name")){
                return false;
            } else if (!inspection("loudsourcing-reward", "reward")){
                return false;
            } else if(!inspection("total-recruit-number", "total_recruit_number")){
                return false;
            } else if (!inspection("loudsourcing-content", "loudsourcing_content")){
                return false;
            } else if (!inspection("loudsourcing-warning", "warning")){
                return false;
            }
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
            console.log("Object : " + OriginalFileObject);
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
            let loudsourcingData = {
                "loudsourcing_no": ${Loudsourcing.loudsourcing_no},
                "name": $("#loudsourcing-name").val(),
                "status": status,
                "host": $("#loudsourcing-host").val(),
                "reward": $("#loudsourcing-reward").val(),
                "warning": $("#loudsourcing-warning").val(),
                "hashtag": $("#loudsourcing-hashtag").val(),
                "content": $("#loudsourcing-content").val(),
                "start_date": $("#start-date").val(),
                "recruitment_end_date": $("#recruitment-end-date").val(),
                "process_start_date": $("#process-start-date").val(),
                "process_end_date": $("#process-end-date").val(),
                "judge_date": $("#judge-date").val(),
                "end_date": $("#end-date").val(),
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
    </script>
</body>
</html>

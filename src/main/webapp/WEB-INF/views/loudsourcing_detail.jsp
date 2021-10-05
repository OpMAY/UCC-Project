<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-07-20
  Time: 오후 9:06
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
    <title>크라우드 상세</title>
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

            <div class="row">
                <div class="col-md-12 grid-margin">
                    <%--                    <div class="card">--%>
                    <%--                        <div class="card-body">--%>
                    <c:set var="Loudsourcing" value="${Loudsourcing}"/>
                    <c:set var="files" value="${files}"/>
                    <div class="row justify-content-center">
                        <div class="col-md-12">
                            <div class="card" style="background-color: #FFFFFf; border-radius: 1.5%">
                                <div class="card-body">
                                    <h6 class="card-title" style="font-size: x-large">크라우드 상세</h6>
                                    <div class="row justify-content-around mt-3">
                                        <div class="col-md-6">
                                            <label class="label d-flex" for="loudsourcing-name"
                                                   style="font-size: larger">
                                                공모전 이름
                                            </label>
                                            <textarea class="form-control" id="loudsourcing-name" rows="1"
                                                      style="line-height: 150%; font-size: large; text-align: center"
                                                      disabled>${Loudsourcing.name}</textarea>
                                        </div>
                                        <div class="col-md-6" style="vertical-align: middle">
                                            <button class="btn btn-outline-primary"
                                                    style="float:right; font-size: large;position: relative; top: 43px;"
                                                    onclick="openWindowPop('/admin/loudsourcing_advertiser.do?loudsourcing_no=${Loudsourcing.loudsourcing_no}', '광고 발주자 상세정보')">
                                                광고 발주자 상세정보 보기
                                            </button>
                                        </div>
                                    </div>
                                    <div class="row mt-3 justify-content-around">
                                        <div class="col-md-6">
                                            <label class="label" style="font-size: larger">
                                                공모전 사진
                                            </label>
                                            <div style="height: 800px; overflow: hidden; background-color: #d1d1d1; border: 1px solid black"
                                                 class="d-flex justify-content-center">
                                                <img class="img-fluid" src="${Loudsourcing.img}"
                                                     onerror="this.src='https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/fan_main_img_basic.png'"
                                                     style="height: 100%; object-fit: contain;">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <label class="label d-flex" for="loudsourcing-status"
                                                   style="font-size: larger">
                                                상태
                                            </label>
                                            <textarea class="form-control" id="loudsourcing-status" rows="1"
                                                      style="line-height: 150%; font-size: large; text-align: center"
                                                      disabled><c:choose><c:when
                                                    test="${Loudsourcing.status == 'recruitment'}">모집</c:when><c:when
                                                    test="${Loudsourcing.status == 'process'}">진행</c:when><c:when
                                                    test="${Loudsourcing.status == 'judge'}">심사</c:when><c:when
                                                    test="${Loudsourcing.status == 'end'}">종료</c:when></c:choose></textarea>
                                            <label class="label d-flex" for="loudsourcing-host"
                                                   style="font-size: larger">
                                                주최
                                            </label>
                                            <textarea class="form-control" id="loudsourcing-host" rows="1"
                                                      style="line-height: 150%; font-size: large; text-align: center"
                                                      disabled>${Loudsourcing.host}</textarea>
                                            <label class="label d-flex" for="loudsourcing-reward"
                                                   style="font-size: larger">
                                                상금
                                            </label>
                                            <textarea class="form-control" id="loudsourcing-reward" rows="1"
                                                      style="line-height: 150%; font-size: large; text-align: center"
                                                      disabled><fmt:formatNumber value="${Loudsourcing.reward}"
                                                                                 type="number"/>원</textarea>
                                            <label class="label d-flex"
                                                   style="font-size: larger">
                                                해시태그
                                            </label>
                                            <div>
                                                <ul id="tag-list"></ul>
                                            </div>
                                            <c:choose>
                                                <c:when test="${Loudsourcing.status == 'recruitment'}">
                                                    <label class="label d-flex" for="total-recruit-number"
                                                           style="font-size: larger">
                                                        참여인원 / 모집인원 (명)
                                                    </label>
                                                    <textarea class="form-control" id="total-recruit-number" rows="1"
                                                              style="line-height: 150%; font-size: large; text-align: center; overflow: hidden; resize: none"
                                                              disabled>${Loudsourcing.applied_artist_num} / ${Loudsourcing.total_recruitment_number}</textarea>
                                                </c:when>
                                                <c:when test="${Loudsourcing.status == 'process'}">
                                                    <label class="label d-flex" for="total-recruit-number"
                                                           style="font-size: larger">
                                                        참여인원 / 모집인원 (명)
                                                    </label>
                                                    <textarea class="form-control" id="total-recruit-number" rows="1"
                                                              style="line-height: 150%; font-size: large; text-align: center; overflow: hidden; resize: none"
                                                              disabled>${Loudsourcing.applied_artist_num} / ${Loudsourcing.total_recruitment_number}</textarea>
                                                </c:when>
                                                <c:when test="${Loudsourcing.status == 'judge'}">
                                                    <label class="label d-flex" for="total-recruit-number"
                                                           style="font-size: larger">
                                                        선정인원 / 탈락인원 (명)
                                                    </label>
                                                    <textarea class="form-control" id="total-recruit-number" rows="1"
                                                              style="line-height: 150%; font-size: large; text-align: center; overflow: hidden; resize: none"
                                                              disabled>${preSelectedNum} / ${unPreSelectedNum}</textarea>
                                                </c:when>
                                                <c:when test="${Loudsourcing.status == 'end'}">
                                                    <label class="label d-flex" for="total-recruit-number"
                                                           style="font-size: larger">
                                                        최종 선정 인원 (명)
                                                    </label>
                                                    <textarea class="form-control" id="total-recruit-number" rows="1"
                                                              style="line-height: 150%; font-size: large; text-align: center; overflow: hidden; resize: none"
                                                              disabled>${final_selected_num}</textarea>
                                                </c:when>
                                            </c:choose>
                                            <label class="label d-flex" for="total-date"
                                                   style="font-size: larger">
                                                총 기간
                                            </label>
                                            <textarea class="form-control" id="total-date" rows="1"
                                                      style="line-height: 150%; font-size: large; text-align: center"
                                                      disabled>${Loudsourcing.start_date}~${Loudsourcing.end_date}</textarea>
                                            <label class="label d-flex" for="recruitment-date"
                                                   style="font-size: larger">
                                                모집 기간
                                            </label>
                                            <textarea class="form-control" id="recruitment-date" rows="1"
                                                      style="line-height: 150%; font-size: large; text-align: center"
                                                      disabled>${Loudsourcing.start_date}~${Loudsourcing.recruitment_end_date}</textarea>
                                            <label class="label d-flex" for="process-date"
                                                   style="font-size: larger">
                                                진행 기간
                                            </label>
                                            <textarea class="form-control" id="process-date" rows="1"
                                                      style="line-height: 150%; font-size: large; text-align: center"
                                                      disabled>${Loudsourcing.process_start_date}~${Loudsourcing.process_end_date}</textarea>
                                            <label class="label d-flex" for="judge-date"
                                                   style="font-size: larger">
                                                심사 시작 일자
                                            </label>
                                            <textarea class="form-control" id="judge-date" rows="1"
                                                      style="line-height: 150%; font-size: large; text-align: center"
                                                      disabled>${Loudsourcing.judge_date}</textarea>
                                            <label class="label d-flex" for="judge-date"
                                                   style="font-size: larger">
                                                파일
                                            </label>
                                            <div style="background-color: #ffffff; border: 1px solid black; line-height: 150%">
                                                <c:forEach var="i" begin="1" end="${files.size()}">
                                                    <div class="mt-1">
                                                        <a style="font-size: 16px; font-weight: bold; color: #6A6A6A; text-decoration: underline"
                                                           class="d-inline"
                                                           onmouseover="this.style.color='#6FAAF2'"
                                                           onmouseout="this.style.color='#6A6A6A'"
                                                           href="${files[i-1].url}"><i
                                                                data-feather="file"></i>${files[i-1].name}</a>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mt-3 justify-content-around">
                                        <div class="col-md-12">
                                            <label class="label d-flex" for="visit-today" style="font-size: large">
                                                출품 안내
                                            </label>
                                            <textarea class="form-control" id="visit-today" rows="10"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${Loudsourcing.content}</textarea>
                                        </div>
                                    </div>
                                    <div class="row mt-3 justify-content-around">
                                        <div class="col-md-12">
                                            <label class="label d-flex" for="ban-number" style="font-size: large">
                                                주의 사항
                                            </label>
                                            <textarea class="form-control" id="ban-number" rows="10"
                                                      style="line-height: 150%; font-size: large"
                                                      disabled>${Loudsourcing.warning}</textarea>
                                        </div>
                                    </div>
                                    <c:choose>
                                        <c:when test="${Loudsourcing.status == 'recruitment'}">
                                            <div class="row mt-4 mb-3 justify-content-around">
                                                <div class="col-md-6 justify-content-center d-flex">
                                                    <button class="btn btn-outline-primary" style="width : 50%; height: 150%"
                                                            onclick="location.href='/admin/loudsourcing_edit.do?loudsourcing_no=${Loudsourcing.loudsourcing_no}'">
                                                        수정
                                                    </button>
                                                </div>
                                                <div class="col-md-6 justify-content-center d-flex">
                                                    <button class="btn btn-secondary" style="width : 50%; height: 150%"
                                                            onclick="location.href = '/admin/loudsourcing_recruitment.do';">
                                                        돌아가기
                                                    </button>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:when test="${Loudsourcing.status == 'process'}">
                                            <div class="row mt-4 mb-3 justify-content-around">
                                                <div class="col-md-3" style="text-align: center">
                                                    <button class="btn btn-outline-primary" style="width : 50%; height: 150%"
                                                            onclick="if(confirm('진행 중인 크라우드를 수정합니다.\n\n진행 중인 크라우드를 수정하기 전 해당 크라우드에 참여한\n모든 아티스트에게 변경 여부를 꼭 공지하시길 바랍니다.\n정말 수정하시겠습니까?')){location.href='/admin/loudsourcing_edit.do?loudsourcing_no=${Loudsourcing.loudsourcing_no}'} else {return false}">
                                                        수정
                                                    </button>
                                                </div>
                                                <div class="col-md-3" style="text-align: center">
                                                    <button class="btn btn-outline-primary" style="width : 50%; height: 150%"
                                                            onclick="if(confirm('진행 중인 크라우드를 삭제합니다.\n\n진행 중인 크라우드를 삭제하면 현재 등록된 출품작도 함께 삭제됩니다.\n정말 삭제하시겠습니까?')){deleteLoudSourcing(${Loudsourcing.loudsourcing_no});} else {return false;}">
                                                        삭제
                                                    </button>
                                                </div>
                                                <div class="col-md-3" style="text-align: center">
                                                    <button class="btn btn-secondary" style="width : 50%; height: 150%"
                                                            onclick="location.href = '/admin/loudsourcing_process.do';">
                                                        돌아가기
                                                    </button>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:when test="${Loudsourcing.status == 'judge'}">
                                            <div class="row mt-4 mb-3 justify-content-center">
                                                <div class="col-md-12" style="text-align: center">
                                                    <button class="btn btn-secondary" style="width : 50%; height: 150%"
                                                            onclick="location.href = '/admin/loudsourcing_judge.do';">
                                                        돌아가기
                                                    </button>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:when test="${Loudsourcing.status == 'end'}">
                                            <div class="row mt-4 mb-3 justify-content-center">
                                                <div class="col-md-12" style="text-align: center">
                                                    <button class="btn btn-secondary" style="width : 50%; height: 150%"
                                                            onclick="location.href = '/admin/loudsourcing_end.do';">
                                                        돌아가기
                                                    </button>
                                                </div>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
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
    <!-- end plugin js for this page -->
    <!-- inject:js -->
    <script src="../assets/vendors/feather-icons/feather.min.js"></script>
    <script src="../assets/js/template.js"></script>
    <script src="../assets/js/tags-input.js"></script>
    <script src="../assets/js/inspect.js"></script>
    <!-- endinject -->
    <!-- custom js for this page -->
    <script src="../assets/js/dashboard.js"></script>
    <script src="../assets/js/datepicker.js"></script>
    <script src="../assets/js/data-table.js"></script>
    <!-- end custom js for this page -->
</body>
<script>
    function openWindowPop(url, name) {
        let options = 'top=10, left=10, width=720, height=1040, status=1, scrollbars=1, resizable=1, menubar=0, fullscreen=0, location=0';
        window.open(url, name, options);
    }

    function deleteLoudSourcing(loudsourcing_no) {
        let data = {"loudsourcing_no": loudsourcing_no};
        $.ajax({
            type: 'POST',
            url: '/admin/delete_loudsourcing.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 0) {
                alert("해당 크라우드를 삭제했습니다.");
                window.location.reload();
            } else {
                alert("알 수 없는 오류가 발생했습니다. 관리자에게 문의해주세요.");
                window.location.reload();
            }
        }).fail(function (error) {
            console.log(error);
            alert(error);
            window.location.reload();
        })
    }

    function replaceAll(str, searchStr, replaceStr) {
        return str.split(searchStr).join(replaceStr);
    }

    $(document).ready(function () {
        let tag = {};
        let counter = 0;

        // 서버 기본 해시태그 리스트를 가져온다.
        let hashtagList = '<c:out value="${Loudsourcing.hashtag}"/>';
        console.log(hashtagList);
        let edit = replaceAll(hashtagList, '&#034;', '"');
        let obj = JSON.parse(edit);
        console.log(obj);
        console.log("length : " + obj.length);
        for (let i = 0; i < obj.length; i++) {
            console.log(obj[i]);
            tag[counter] = obj[i];
            console.log(counter);
            $("#tag-list").append("<li class='tag-item'>#" + obj[i] + "</li>");
            counter++;
        }
    });
</script>
</html>

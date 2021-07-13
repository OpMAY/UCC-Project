<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>관리자 HOME</title>
    <!-- core:css -->
    <link rel="stylesheet" href="../assets/vendors/core/core.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <link rel="stylesheet" href="../assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
    <!-- end plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="../assets/fonts/feather-font/css/iconfont.css">
    <link rel="stylesheet" href="../assets/vendors/flag-icon-css/css/flag-icon.min.css">
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="../assets/css/demo_1/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="../assets/images/favicon.png"/>
    <script>

    </script>
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

            <div class="d-flex justify-content-between align-items-center flex-wrap grid-margin">
                <div>
                    <h4 class="mb-3 mb-md-0">관리자 페이지에 오신 것을 환영합니다.</h4>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-7 col-xl-8 stretch-card">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-baseline mb-2">
                            <h6 class="card-title mb-3">미답변 문의</h6>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-hover mb-0">
                                <thead>
                                <tr>
                                    <th class="pt-0">#</th>
                                    <th class="pt-0">작성자 명</th>
                                    <th class="pt-0">제목</th>
                                    <th class="pt-0">답변 상태</th>
                                    <th class="pt-0">생성일자</th>
                                    <th class="pt-0">자세히 보기</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>한지우</td>
                                    <td>문의 제목입니다.</td>
                                    <td>미답변</td>
                                    <td>2020/04/26</td>
                                    <td>
                                        <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0">
                                            <i class="btn-icon-prepend" data-feather="search"></i>
                                        보기
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>조한석</td>
                                    <td>두번째 문의</td>
                                    <td>미답변</td>
                                    <td>01/05/2020</td>
                                    <td>
                                        <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0">
                                            <i class="btn-icon-prepend" data-feather="search"></i>
                                        보기
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>김우식</td>
                                    <td>세번째 문의입니다.</td>
                                    <td>미답변</td>
                                    <td>01/05/2020</td>
                                    <td>
                                        <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0">
                                            <i class="btn-icon-prepend" data-feather="search"></i>
                                            보기
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>유병준</td>
                                    <td>네번째 문의입니다.</td>
                                    <td>미답변</td>
                                    <td>01/01/2020
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0">
                                            <i class="btn-icon-prepend" data-feather="search"></i>
                                            보기
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>정지원</td>
                                    <td>다섯번째 문의입니다.</td>
                                    <td>미답변</td>
                                    <td>01/01/2020</td>
                                    <td>
                                        <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0">
                                            <i class="btn-icon-prepend" data-feather="search"></i>
                                            보기
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>6</td>
                                    <td>박상우</td>
                                    <td>여섯번째 문의입니다.</td>
                                    <td>미답변</td>
                                    <td>01/01/2020</td>
                                    <td>
                                        <button type="button" class="btn btn-outline-primary btn-icon-text mr-2 mb-2 mb-md-0">
                                            <i class="btn-icon-prepend" data-feather="search"></i>
                                            보기
                                        </button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
                <div class="col-lg-5 col-xl-4 stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-baseline mb-2">
                                <h6 class="card-title mb-auto">가입 SNS</h6>
                                <div class="input-group date datepicker dashboard-date mr-2 mb-4 mb-md-0 d-md-none d-xl-flex" id="dashboardDate">
                                    <span class="input-group-addon bg-transparent"><i data-feather="calendar" class="text-primary"></i></span>
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-hover mb-0">
                                    <thead>
                                    <tr>
                                        <th class="pt-0">가입 방식</th>
                                        <th class="pt-0">가입 횟수</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>NAVER</td>
                                        <td>100 회</td>
                                    </tr>
                                    <tr>
                                        <td>KAKAO</td>
                                        <td>20 회</td>
                                    </tr>
                                    <tr>
                                        <td>GOOGLE</td>
                                        <td>40 회</td>
                                    </tr>
                                    <tr>
                                        <td>APPLE</td>
                                        <td>15 회</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- row -->

            <div class="row">
                <div class="col-lg-12 col-xl-12 stretch-card" style="padding-top: 20px">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-baseline mb-2">
                                <h6 class="card-title mb-4">최신 크라우드 현황</h6>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-hover mb-0">
                                    <thead>
                                    <tr>
                                        <th class="pt-0">#</th>
                                        <th class="pt-0">공모전 이름</th>
                                        <th class="pt-0">상태</th>
                                        <th class="pt-0">참여인원 / 총 참여인원</th>
                                        <th class="pt-0">총 기간</th>
                                        <th class="pt-0">모집 기간</th>
                                        <th class="pt-0">진행 기간</th>
                                        <th class="pt-0">참여 인원 리스트</th>
                                        <th class="pt-0">자세히 보기</th>
                                        <th class="pt-0">삭제</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>NobleUI jQuery</td>
                                        <td>01/01/2020</td>
                                        <td>26/04/2020</td>
                                        <td><span class="badge badge-danger">Released</span></td>
                                        <td>Leonardo Payne</td>
                                        <td>01/01/2020</td>
                                        <td>26/04/2020</td>
                                        <td><span class="badge badge-danger">Released</span></td>
                                        <td>Leonardo Payne</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>NobleUI Angular</td>
                                        <td>01/01/2020</td>
                                        <td>26/04/2020</td>
                                        <td><span class="badge badge-success">Review</span></td>
                                        <td>Carl Henson</td>
                                        <td>01/01/2020</td>
                                        <td>26/04/2020</td>
                                        <td><span class="badge badge-danger">Released</span></td>
                                        <td>Leonardo Payne</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>NobleUI ReactJs</td>
                                        <td>01/05/2020</td>
                                        <td>10/09/2020</td>
                                        <td><span class="badge badge-info-muted">Pending</span></td>
                                        <td>Jensen Combs</td>
                                        <td>01/01/2020</td>
                                        <td>26/04/2020</td>
                                        <td><span class="badge badge-danger">Released</span></td>
                                        <td>Leonardo Payne</td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>NobleUI VueJs</td>
                                        <td>01/01/2020</td>
                                        <td>31/11/2020</td>
                                        <td><span class="badge badge-warning">Work in Progress</span>
                                        </td>
                                        <td>Amiah Burton</td>
                                        <td>01/01/2020</td>
                                        <td>26/04/2020</td>
                                        <td><span class="badge badge-danger">Released</span></td>
                                        <td>Leonardo Payne</td>
                                    </tr>
                                    <tr>
                                        <td>5</td>
                                        <td>NobleUI Laravel</td>
                                        <td>01/01/2020</td>
                                        <td>31/12/2020</td>
                                        <td><span class="badge badge-danger-muted text-white">Coming soon</span></td>
                                        <td>Yaretzi Mayo</td>
                                        <td>01/01/2020</td>
                                        <td>26/04/2020</td>
                                        <td><span class="badge badge-danger">Released</span></td>
                                        <td>Leonardo Payne</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- row -->

        </div>

        <!-- partial:partials/_footer.html -->
        <footer class="footer d-flex flex-column flex-md-row align-items-center justify-content-between">
            <p class="text-muted text-center text-md-left">Copyright © 2020 <a href="https://www.nobleui.com"
                                                                               target="_blank">NobleUI</a>. All rights
                reserved</p>
            <p class="text-muted text-center text-md-left mb-0 d-none d-md-block">Handcrafted With <i
                    class="mb-1 text-primary ml-1 icon-small" data-feather="heart"></i></p>
        </footer>
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
<!-- end plugin js for this page -->
<!-- inject:js -->
<script src="../assets/vendors/feather-icons/feather.min.js"></script>
<script src="../assets/js/template.js"></script>
<!-- endinject -->
<!-- custom js for this page -->
<script src="../assets/js/dashboard.js"></script>
<script src="../assets/js/datepicker.js"></script>
<!-- end custom js for this page -->
</body>
</html>    
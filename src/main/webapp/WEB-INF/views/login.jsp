<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>UCC 관리자 페이지</title>
    <!-- core:css -->
    <link rel="stylesheet" href="/assets/vendors/core/core.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <!-- end plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="/assets/fonts/feather-font/css/iconfont.css">
    <link rel="stylesheet" href="/assets/vendors/flag-icon-css/css/flag-icon.min.css">
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="/assets/css/demo_1/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="/assets/images/favicon.png"/>

</head>
<body class="sidebar-dark">
<div class="main-wrapper">
    <div class="page-wrapper full-page">
        <div class="page-content d-flex align-items-center justify-content-center">

            <div class="row w-100 mx-0 auth-page">
                <div class="col-md-8 col-xl-6 mx-auto">
                    <div class="card">
                        <div class="row">
                            <div class="col-md-8 pl-md-0">
                                <div class="auth-form-wrapper px-4 py-5">
                                    <a href="#" class="noble-ui-logo d-block mb-2"><span>UCC 앱</span></a>
                                    <h5 class="text-muted font-weight-normal mb-4">관리자 페이지</h5>
                                    <form class="forms-sample" name="login-form">
                                        <div class="form-group">
                                            <label for="id">ID</label>
                                            <input type="text" class="form-control" id="id" name="id" placeholder="id"
                                                   autofocus>
                                        </div>
                                        <div class="form-group">
                                            <label for="password">Password</label>
                                            <input type="password" class="form-control" id="password"
                                                   autocomplete="current-password" name="password"
                                                   placeholder="password">
                                        </div>
                                        <div class="mt-3">
                                            <button type="button" id="login_btn"
                                                    class="btn btn-primary mr-2 mb-2 mb-md-0 text-white">Login
                                            </button>
                                        </div>
                                    </form>
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
<!-- end plugin js for this page -->
<!-- inject:js -->
<script src="/assets/vendors/feather-icons/feather.min.js"></script>
<script src="/assets/js/template.js"></script>
<script>
    function login() {
        const id = $('#id').val();
        const pw = $('#password').val();
        const data = {"id": id, "password": pw}
        $.ajax({
            type: 'POST',
            url: '/admin/login_post.do',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            console.log(result);
            if (result === 1) {
                alert("로그인 성공");
                window.location.href = '/admin/main.do';
            } else {
                alert("아이디 또는 비밀번호를 확인하세요.");
            }
        }).fail(function (error) {
            console.log(error);
        })
    }
    $('#id').keypress(function(e){
    	if(e.which === 13){
    		login();
		}
	});
    $('#password').keypress(function (e) {
    	if(e.which === 13){
    		login();
		}
	})
    $('#login_btn').click(function () {
			login();
        }
    )
</script>
<!-- endinject -->
<!-- custom js for this page -->
<!-- end custom js for this page -->
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<nav class="navbar">
	<a href="#" class="sidebar-toggler">
		<i data-feather="menu"></i>
	</a>
	<div class="navbar-content">
		<div class="word" style="text-align: center; margin-top: auto; margin-bottom: auto; font-size: large">
			<script>
				document.write(document.title)
			</script>
		</div>
		<ul class="navbar-nav"><%----%>
			<li class="nav-item dropdown nav-profile">
				<a class="nav-link dropdown-toggle" href="#" id="profileDropdown" role="button"
				   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<img src="https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/profile_img_basic.png" style="width: 30px; height: 30px; border: 1px black solid" alt="profile">
				</a>
				<div class="dropdown-menu" aria-labelledby="profileDropdown">
					<div class="dropdown-body">
						<ul class="profile-nav p-0 pt-3">
							<li class="nav-item">
								<img src="https://vodappserver.s3.ap-northeast-2.amazonaws.com/api/images/default/profile_img_basic.png" style="width: 100%">
							</li>
							<li class="nav-item">
								<a href="${pageContext.request.contextPath}/admin/logout.do" class="nav-link">
									<i data-feather="user"></i>
									<span>로그아웃</span>
								</a>
							</li>
						</ul>
					</div>
				</div>
			</li>
		</ul>
	</div>
</nav>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<nav class="navbar">
	<a href="#" class="sidebar-toggler">
		<i data-feather="menu"></i>
	</a>
	<div class="navbar-content">
		<div class="word" style="text-align: center; margin-top: auto; margin-bottom: auto">
			<script>
				document.write(document.title)
			</script>
		</div>
		<ul class="navbar-nav"><%----%>
			<li class="nav-item dropdown nav-profile">
				<a class="nav-link dropdown-toggle" href="#" id="profileDropdown" role="button"
				   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<img src="https://via.placeholder.com/30x30" alt="profile">
				</a>
				<div class="dropdown-menu" aria-labelledby="profileDropdown">
					<div class="dropdown-body">
						<ul class="profile-nav p-0 pt-3">
							<li class="nav-item">
								<a href="/admin/logout" class="nav-link">
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
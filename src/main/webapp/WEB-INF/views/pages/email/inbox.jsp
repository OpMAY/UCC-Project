<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>NobleUI Responsive Bootstrap 4 Dashboard Template</title>
	<!-- core:css -->
	<link rel="stylesheet" href="../../../assets/vendors/core/core.css">
	<!-- endinject -->
	<!-- plugin css for this page -->
	<!-- end plugin css for this page -->
	<!-- inject:css -->
	<link rel="stylesheet" href="../../../assets/fonts/feather-font/css/iconfont.css">
	<link rel="stylesheet" href="../../../assets/vendors/flag-icon-css/css/flag-icon.min.css">
	<!-- endinject -->
  <!-- Layout styles -->  
	<link rel="stylesheet" href="../../../assets/css/demo_1/style.css">
  <!-- End layout styles -->
  <link rel="shortcut icon" href="../../../assets/images/favicon.png" />
</head>
<body>
	<div class="main-wrapper">

		<!-- partial:../../partials/_sidebar.jsp -->
		<nav class="sidebar">
      <div class="sidebar-header">
        <a href="#" class="sidebar-brand">
          Noble<span>UI</span>
        </a>
        <div class="sidebar-toggler not-active">
          <span></span>
          <span></span>
          <span></span>
        </div>
      </div>
      <div class="sidebar-body">
        <ul class="nav">
          <li class="nav-item nav-category">Main</li>
          <li class="nav-item">
            <a href="../../dashboard-one.html" class="nav-link">
              <i class="link-icon" data-feather="box"></i>
              <span class="link-title">Dashboard</span>
            </a>
          </li>
          <li class="nav-item nav-category">web apps</li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#emails" role="button" aria-expanded="false" aria-controls="emails">
              <i class="link-icon" data-feather="mail"></i>
              <span class="link-title">Email</span>
              <i class="link-arrow" data-feather="chevron-down"></i>
            </a>
            <div class="collapse" id="emails">
              <ul class="nav sub-menu">
                <li class="nav-item">
                  <a href="../../pages/email/inbox.jsp" class="nav-link">Inbox</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/email/read.jsp" class="nav-link">Read</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/email/compose.jsp" class="nav-link">Compose</a>
                </li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a href="../../pages/apps/chat.jsp" class="nav-link">
              <i class="link-icon" data-feather="message-square"></i>
              <span class="link-title">Chat</span>
            </a>
          </li>
          <li class="nav-item">
            <a href="../../pages/apps/calendar.jsp" class="nav-link">
              <i class="link-icon" data-feather="calendar"></i>
              <span class="link-title">Calendar</span>
            </a>
          </li>
          <li class="nav-item nav-category">Components</li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#uiComponents" role="button" aria-expanded="false" aria-controls="uiComponents">
              <i class="link-icon" data-feather="feather"></i>
              <span class="link-title">UI Kit</span>
              <i class="link-arrow" data-feather="chevron-down"></i>
            </a>
            <div class="collapse" id="uiComponents">
              <ul class="nav sub-menu">
                <li class="nav-item">
                  <a href="../../pages/ui-components/alerts.jsp" class="nav-link">Alerts</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/badges.jsp" class="nav-link">Badges</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/breadcrumbs.jsp" class="nav-link">Breadcrumbs</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/buttons.jsp" class="nav-link">Buttons</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/button-group.jsp" class="nav-link">Button group</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/cards.jsp" class="nav-link">Cards</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/carousel.jsp" class="nav-link">Carousel</a>
                </li>
                <li class="nav-item">
                    <a href="../../pages/ui-components/collapse.jsp" class="nav-link">Collapse</a>
                  </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/dropdowns.jsp" class="nav-link">Dropdowns</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/list-group.jsp" class="nav-link">List group</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/media-object.jsp" class="nav-link">Media object</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/modal.jsp" class="nav-link">Modal</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/navs.jsp" class="nav-link">Navs</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/navbar.jsp" class="nav-link">Navbar</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/pagination.jsp" class="nav-link">Pagination</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/popover.jsp" class="nav-link">Popovers</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/progress.jsp" class="nav-link">Progress</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/scrollbar.jsp" class="nav-link">Scrollbar</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/scrollspy.jsp" class="nav-link">Scrollspy</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/spinners.jsp" class="nav-link">Spinners</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/ui-components/tooltips.jsp" class="nav-link">Tooltips</a>
                </li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#advancedUI" role="button" aria-expanded="false" aria-controls="advancedUI">
              <i class="link-icon" data-feather="anchor"></i>
              <span class="link-title">Advanced UI</span>
              <i class="link-arrow" data-feather="chevron-down"></i>
            </a>
            <div class="collapse" id="advancedUI">
              <ul class="nav sub-menu">
                <li class="nav-item">
                  <a href="../../pages/advanced-ui/cropper.jsp" class="nav-link">Cropper</a>
                </li>
                <li class="nav-item">
                    <a href="../../pages/advanced-ui/owl-carousel.jsp" class="nav-link">Owl carousel</a>
                  </li>
                <li class="nav-item">
                  <a href="../../pages/advanced-ui/sweet-alert.jsp" class="nav-link">Sweet Alert</a>
                </li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#forms" role="button" aria-expanded="false" aria-controls="forms">
              <i class="link-icon" data-feather="inbox"></i>
              <span class="link-title">Forms</span>
              <i class="link-arrow" data-feather="chevron-down"></i>
            </a>
            <div class="collapse" id="forms">
              <ul class="nav sub-menu">
                <li class="nav-item">
                  <a href="../../pages/forms/basic-elements.jsp" class="nav-link">Basic Elements</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/forms/advanced-elements.jsp" class="nav-link">Advanced Elements</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/forms/editors.jsp" class="nav-link">Editors</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/forms/wizard.jsp" class="nav-link">Wizard</a>
                </li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link"  data-toggle="collapse" href="#charts" role="button" aria-expanded="false" aria-controls="charts">
              <i class="link-icon" data-feather="pie-chart"></i>
              <span class="link-title">Charts</span>
              <i class="link-arrow" data-feather="chevron-down"></i>
            </a>
            <div class="collapse" id="charts">
              <ul class="nav sub-menu">
                <li class="nav-item">
                  <a href="../../pages/charts/apex.jsp" class="nav-link">Apex</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/charts/chartjs.jsp" class="nav-link">ChartJs</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/charts/flot.jsp" class="nav-link">Flot</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/charts/morrisjs.jsp" class="nav-link">Morris</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/charts/peity.jsp" class="nav-link">Peity</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/charts/sparkline.jsp" class="nav-link">Sparkline</a>
                </li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#tables" role="button" aria-expanded="false" aria-controls="tables">
              <i class="link-icon" data-feather="layout"></i>
              <span class="link-title">Table</span>
              <i class="link-arrow" data-feather="chevron-down"></i>
            </a>
            <div class="collapse" id="tables">
              <ul class="nav sub-menu">
                <li class="nav-item">
                  <a href="../../pages/tables/basic-table.jsp" class="nav-link">Basic Tables</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/tables/data-table.jsp" class="nav-link">Data Table</a>
                </li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#icons" role="button" aria-expanded="false" aria-controls="icons">
              <i class="link-icon" data-feather="smile"></i>
              <span class="link-title">Icons</span>
              <i class="link-arrow" data-feather="chevron-down"></i>
            </a>
            <div class="collapse" id="icons">
              <ul class="nav sub-menu">
                <li class="nav-item">
                  <a href="../../pages/icons/feather-icons.jsp" class="nav-link">Feather Icons</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/icons/flag-icons.jsp" class="nav-link">Flag Icons</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/icons/mdi-icons.jsp" class="nav-link">Mdi Icons</a>
                </li>
              </ul>
            </div>
          </li>
          <li class="nav-item nav-category">Pages</li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#general-pages" role="button" aria-expanded="false" aria-controls="general-pages">
              <i class="link-icon" data-feather="book"></i>
              <span class="link-title">Special pages</span>
              <i class="link-arrow" data-feather="chevron-down"></i>
            </a>
            <div class="collapse" id="general-pages">
              <ul class="nav sub-menu">
                <li class="nav-item">
                  <a href="../../pages/general/blank-page.jsp" class="nav-link">Blank page</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/general/faq.jsp" class="nav-link">Faq</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/general/invoice.jsp" class="nav-link">Invoice</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/general/profile.jsp" class="nav-link">Profile</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/general/pricing.jsp" class="nav-link">Pricing</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/general/timeline.jsp" class="nav-link">Timeline</a>
                </li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#authPages" role="button" aria-expanded="false" aria-controls="authPages">
              <i class="link-icon" data-feather="unlock"></i>
              <span class="link-title">Authentication</span>
              <i class="link-arrow" data-feather="chevron-down"></i>
            </a>
            <div class="collapse" id="authPages">
              <ul class="nav sub-menu">
                <li class="nav-item">
                  <a href="../../pages/auth/login.jsp" class="nav-link">Login</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/auth/register.jsp" class="nav-link">Register</a>
                </li>
              </ul>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#errorPages" role="button" aria-expanded="false" aria-controls="errorPages">
              <i class="link-icon" data-feather="cloud-off"></i>
              <span class="link-title">Error</span>
              <i class="link-arrow" data-feather="chevron-down"></i>
            </a>
            <div class="collapse" id="errorPages">
              <ul class="nav sub-menu">
                <li class="nav-item">
                  <a href="../../pages/error/404.html" class="nav-link">404</a>
                </li>
                <li class="nav-item">
                  <a href="../../pages/error/500.html" class="nav-link">500</a>
                </li>
              </ul>
            </div>
          </li>
          <li class="nav-item nav-category">Docs</li>
          <li class="nav-item">
            <a href="https://www.nobleui.com/html/documentation/docs.html" target="_blank" class="nav-link">
              <i class="link-icon" data-feather="hash"></i>
              <span class="link-title">Documentation</span>
            </a>
          </li>
        </ul>
      </div>
    </nav>
    <nav class="settings-sidebar">
      <div class="sidebar-body">
        <a href="#" class="settings-sidebar-toggler">
          <i data-feather="settings"></i>
        </a>
        <h6 class="text-muted">Sidebar:</h6>
        <div class="form-group border-bottom">
          <div class="form-check form-check-inline">
            <label class="form-check-label">
              <input type="radio" class="form-check-input" name="sidebarThemeSettings" id="sidebarLight" value="sidebar-light" checked>
              Light
            </label>
          </div>
          <div class="form-check form-check-inline">
            <label class="form-check-label">
              <input type="radio" class="form-check-input" name="sidebarThemeSettings" id="sidebarDark" value="sidebar-dark">
              Dark
            </label>
          </div>
        </div>
        <div class="theme-wrapper">
          <h6 class="text-muted mb-2">Light Theme:</h6>
          <a class="theme-item active" href="../../../demo_1/dashboard-one.html">
            <img src="../../../assets/images/screenshots/light.jpg" alt="light theme">
          </a>
          <h6 class="text-muted mb-2">Dark Theme:</h6>
          <a class="theme-item" href="../../../demo_2/dashboard-one.html">
            <img src="../../../assets/images/screenshots/dark.jpg" alt="light theme">
          </a>
        </div>
      </div>
    </nav>
		<!-- partial -->
	
		<div class="page-wrapper">
				
			<!-- partial:../../partials/_navbar.jsp -->
			<nav class="navbar">
				<a href="#" class="sidebar-toggler">
					<i data-feather="menu"></i>
				</a>
				<div class="navbar-content">
					<form class="search-form">
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<i data-feather="search"></i>
								</div>
							</div>
							<input type="text" class="form-control" id="navbarForm" placeholder="Search here...">
						</div>
					</form>
					<ul class="navbar-nav">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="languageDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i class="flag-icon flag-icon-us mt-1" title="us"></i> <span class="font-weight-medium ml-1 mr-1">English</span>
							</a>
							<div class="dropdown-menu" aria-labelledby="languageDropdown">
                <a href="javascript:;" class="dropdown-item py-2"><i class="flag-icon flag-icon-us" title="us" id="us"></i> <span class="ml-1"> English </span></a>
                <a href="javascript:;" class="dropdown-item py-2"><i class="flag-icon flag-icon-fr" title="fr" id="fr"></i> <span class="ml-1"> French </span></a>
                <a href="javascript:;" class="dropdown-item py-2"><i class="flag-icon flag-icon-de" title="de" id="de"></i> <span class="ml-1"> German </span></a>
                <a href="javascript:;" class="dropdown-item py-2"><i class="flag-icon flag-icon-pt" title="pt" id="pt"></i> <span class="ml-1"> Portuguese </span></a>
                <a href="javascript:;" class="dropdown-item py-2"><i class="flag-icon flag-icon-es" title="es" id="es"></i> <span class="ml-1"> Spanish </span></a>
							</div>
            </li>
						<li class="nav-item dropdown nav-apps">
							<a class="nav-link dropdown-toggle" href="#" id="appsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i data-feather="grid"></i>
							</a>
							<div class="dropdown-menu" aria-labelledby="appsDropdown">
								<div class="dropdown-header d-flex align-items-center justify-content-between">
									<p class="mb-0 font-weight-medium">Web Apps</p>
									<a href="javascript:;" class="text-muted">Edit</a>
								</div>
								<div class="dropdown-body">
									<div class="d-flex align-items-center apps">
										<a href="../../pages/apps/chat.jsp"><i data-feather="message-square" class="icon-lg"></i><p>Chat</p></a>
										<a href="../../pages/apps/calendar.jsp"><i data-feather="calendar" class="icon-lg"></i><p>Calendar</p></a>
										<a href="../../pages/email/inbox.jsp"><i data-feather="mail" class="icon-lg"></i><p>Email</p></a>
										<a href="../../pages/general/profile.jsp"><i data-feather="instagram" class="icon-lg"></i><p>Profile</p></a>
									</div>
								</div>
								<div class="dropdown-footer d-flex align-items-center justify-content-center">
									<a href="javascript:;">View all</a>
								</div>
							</div>
						</li>
						<li class="nav-item dropdown nav-messages">
							<a class="nav-link dropdown-toggle" href="#" id="messageDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i data-feather="mail"></i>
							</a>
							<div class="dropdown-menu" aria-labelledby="messageDropdown">
								<div class="dropdown-header d-flex align-items-center justify-content-between">
									<p class="mb-0 font-weight-medium">9 New Messages</p>
									<a href="javascript:;" class="text-muted">Clear all</a>
								</div>
								<div class="dropdown-body">
									<a href="javascript:;" class="dropdown-item">
										<div class="figure">
											<img src="https://via.placeholder.com/30x30" alt="userr">
										</div>
										<div class="content">
											<div class="d-flex justify-content-between align-items-center">
												<p>Leonardo Payne</p>
												<p class="sub-text text-muted">2 min ago</p>
											</div>	
											<p class="sub-text text-muted">Project status</p>
										</div>
									</a>
									<a href="javascript:;" class="dropdown-item">
										<div class="figure">
											<img src="https://via.placeholder.com/30x30" alt="userr">
										</div>
										<div class="content">
											<div class="d-flex justify-content-between align-items-center">
												<p>Carl Henson</p>
												<p class="sub-text text-muted">30 min ago</p>
											</div>	
											<p class="sub-text text-muted">Client meeting</p>
										</div>
									</a>
									<a href="javascript:;" class="dropdown-item">
										<div class="figure">
											<img src="https://via.placeholder.com/30x30" alt="userr">
										</div>
										<div class="content">
											<div class="d-flex justify-content-between align-items-center">
												<p>Jensen Combs</p>												
												<p class="sub-text text-muted">1 hrs ago</p>
											</div>	
											<p class="sub-text text-muted">Project updates</p>
										</div>
									</a>
									<a href="javascript:;" class="dropdown-item">
										<div class="figure">
											<img src="https://via.placeholder.com/30x30" alt="userr">
										</div>
										<div class="content">
											<div class="d-flex justify-content-between align-items-center">
												<p>Amiah Burton</p>
												<p class="sub-text text-muted">2 hrs ago</p>
											</div>
											<p class="sub-text text-muted">Project deadline</p>
										</div>
									</a>
									<a href="javascript:;" class="dropdown-item">
										<div class="figure">
											<img src="https://via.placeholder.com/30x30" alt="userr">
										</div>
										<div class="content">
											<div class="d-flex justify-content-between align-items-center">
												<p>Yaretzi Mayo</p>
												<p class="sub-text text-muted">5 hr ago</p>
											</div>
											<p class="sub-text text-muted">New record</p>
										</div>
									</a>
								</div>
								<div class="dropdown-footer d-flex align-items-center justify-content-center">
									<a href="javascript:;">View all</a>
								</div>
							</div>
						</li>
						<li class="nav-item dropdown nav-notifications">
							<a class="nav-link dropdown-toggle" href="#" id="notificationDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i data-feather="bell"></i>
								<div class="indicator">
									<div class="circle"></div>
								</div>
							</a>
							<div class="dropdown-menu" aria-labelledby="notificationDropdown">
								<div class="dropdown-header d-flex align-items-center justify-content-between">
									<p class="mb-0 font-weight-medium">6 New Notifications</p>
									<a href="javascript:;" class="text-muted">Clear all</a>
								</div>
								<div class="dropdown-body">
									<a href="javascript:;" class="dropdown-item">
										<div class="icon">
											<i data-feather="user-plus"></i>
										</div>
										<div class="content">
											<p>New customer registered</p>
											<p class="sub-text text-muted">2 sec ago</p>
										</div>
									</a>
									<a href="javascript:;" class="dropdown-item">
										<div class="icon">
											<i data-feather="gift"></i>
										</div>
										<div class="content">
											<p>New Order Recieved</p>
											<p class="sub-text text-muted">30 min ago</p>
										</div>
									</a>
									<a href="javascript:;" class="dropdown-item">
										<div class="icon">
											<i data-feather="alert-circle"></i>
										</div>
										<div class="content">
											<p>Server Limit Reached!</p>
											<p class="sub-text text-muted">1 hrs ago</p>
										</div>
									</a>
									<a href="javascript:;" class="dropdown-item">
										<div class="icon">
											<i data-feather="layers"></i>
										</div>
										<div class="content">
											<p>Apps are ready for update</p>
											<p class="sub-text text-muted">5 hrs ago</p>
										</div>
									</a>
									<a href="javascript:;" class="dropdown-item">
										<div class="icon">
											<i data-feather="download"></i>
										</div>
										<div class="content">
											<p>Download completed</p>
											<p class="sub-text text-muted">6 hrs ago</p>
										</div>
									</a>
								</div>
								<div class="dropdown-footer d-flex align-items-center justify-content-center">
									<a href="javascript:;">View all</a>
								</div>
							</div>
						</li>
						<li class="nav-item dropdown nav-profile">
							<a class="nav-link dropdown-toggle" href="#" id="profileDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<img src="https://via.placeholder.com/30x30" alt="profile">
							</a>
							<div class="dropdown-menu" aria-labelledby="profileDropdown">
								<div class="dropdown-header d-flex flex-column align-items-center">
									<div class="figure mb-3">
										<img src="https://via.placeholder.com/80x80" alt="">
									</div>
									<div class="info text-center">
										<p class="name font-weight-bold mb-0">Amiah Burton</p>
										<p class="email text-muted mb-3">amiahburton@gmail.com</p>
									</div>
								</div>
								<div class="dropdown-body">
									<ul class="profile-nav p-0 pt-3">
										<li class="nav-item">
											<a href="../../pages/general/profile.jsp" class="nav-link">
												<i data-feather="user"></i>
												<span>Profile</span>
											</a>
										</li>
										<li class="nav-item">
											<a href="javascript:;" class="nav-link">
												<i data-feather="edit"></i>
												<span>Edit Profile</span>
											</a>
										</li>
										<li class="nav-item">
											<a href="javascript:;" class="nav-link">
												<i data-feather="repeat"></i>
												<span>Switch User</span>
											</a>
										</li>
										<li class="nav-item">
											<a href="javascript:;" class="nav-link">
												<i data-feather="log-out"></i>
												<span>Log Out</span>
											</a>
										</li>
									</ul>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</nav>
			<!-- partial -->

			<div class="page-content">
        
        <div class="row inbox-wrapper">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-body">
                <div class="row">
                  <div class="col-lg-3 email-aside border-lg-right">
                    <div class="aside-content">
                      <div class="aside-header">
                        <button class="navbar-toggle" data-target=".aside-nav" data-toggle="collapse" type="button"><span class="icon"><i data-feather="chevron-down"></i></span></button><span class="title">Mail Service</span>
                        <p class="description">amiahburton@gmail.com</p>
                      </div>
                      <div class="aside-compose"><a class="btn btn-primary btn-block" href="../../../demo_1/pages/email/compose.html">Compose Email</a></div>
                      <div class="aside-nav collapse">
                        <ul class="nav">
                          <li class="active"><a href="#"><span class="icon"><i data-feather="inbox"></i></span>Inbox<span class="badge badge-danger-muted text-white font-weight-bold float-right">2</span></a></li>
                          <li><a href="#"><span class="icon"><i data-feather="mail"></i></span>Sent Mail</a></li>
                          <li><a href="#"><span class="icon"><i data-feather="briefcase"></i></span>Important<span class="badge badge-info-muted text-white font-weight-bold float-right">4</span></a></li>
                          <li><a href="#"><span class="icon"><i data-feather="file"></i></span>Drafts</a></li>
                          <li><a href="#"><span class="icon"><i data-feather="star"></i></span>Tags</a></li>
                          <li><a href="#"><span class="icon"><i data-feather="trash"></i></span>Trash</a></li>
                        </ul>
                        <span class="title">Labels</span>
                        <ul class="nav nav-pills nav-stacked">
                          <li>
                            <a href="#"><i data-feather="tag" class="text-warning"></i> Important </a>
                          </li>
                          <li><a href="#">
                            <i data-feather="tag" class="text-primary"></i> Business </a>
                          </li>
                          <li>
                            <a href="#"> <i data-feather="tag" class="text-info"></i> Inspiration </a>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-9 email-content">
                    <div class="email-inbox-header">
                      <div class="row align-items-center">
                        <div class="col-lg-6">
                          <div class="email-title mb-2 mb-md-0"><span class="icon"><i data-feather="inbox"></i></span> Inbox <span class="new-messages">(2 new messages)</span> </div>
                        </div>
                        <div class="col-lg-6">
                          <div class="email-search">
                            <div class="input-group input-search">
                              <input class="form-control" type="text" placeholder="Search mail..."><span class="input-group-btn">
                              <button class="btn btn-outline-secondary" type="button"><i data-feather="search"></i></button></span>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="email-filters d-flex align-items-center justify-content-between flex-wrap">
                      <div class="email-filters-left flex-wrap d-none d-md-flex">
                        <div class="form-check form-check-flat form-check-primary">
                          <label class="form-check-label">
                            <input type="checkbox" class="form-check-input">
                          </label>
                        </div>
                        <div class="btn-group ml-3">
                          <button class="btn btn-outline-primary dropdown-toggle" data-toggle="dropdown" type="button"> With selected <span class="caret"></span></button>
                          <div class="dropdown-menu" role="menu">
                            <a class="dropdown-item" href="#">Mark as read</a>
                            <a class="dropdown-item" href="#">Mark as unread</a><a class="dropdown-item" href="#">Spam</a>
                            <div class="dropdown-divider"></div><a class="dropdown-item text-danger" href="#">Delete</a>
                          </div>
                        </div>
                        <div class="btn-group mb-1 mb-md-0">
                          <button class="btn btn-outline-primary" type="button">Archive</button>
                          <button class="btn btn-outline-primary" type="button">Span</button>
                          <button class="btn btn-outline-primary" type="button">Delete</button>
                        </div>
                        <div class="btn-group mb-1 mb-md-0 d-none d-xl-block">
                          <button class="btn btn-outline-primary dropdown-toggle" data-toggle="dropdown" type="button">Order by <span class="caret"></span></button>
                          <div class="dropdown-menu dropdown-menu-right" role="menu">
                            <a class="dropdown-item" href="#">Date</a>
                            <a class="dropdown-item" href="#">From</a>
                            <a class="dropdown-item" href="#">Subject</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Size</a>
                          </div>
                        </div>
                      </div>
                      <div class="email-filters-right"><span class="email-pagination-indicator">1-50 of 253</span>
                        <div class="btn-group email-pagination-nav">
                          <button class="btn btn-outline-secondary btn-icon" type="button"><i data-feather="chevron-left"></i></button>
                          <button class="btn btn-outline-secondary btn-icon" type="button"><i data-feather="chevron-right"></i></button>
                        </div>
                      </div>
                    </div>
                    <div class="email-list">
                      <div class="email-list-item email-list-item--unread">
                        <div class="email-list-actions">
                          <div class="form-check form-check-flat form-check-primary">
                            <label class="form-check-label">
                              <input type="checkbox" class="form-check-input">
                            </label>
                          </div>
                          <a class="favorite" href="#"><span><i data-feather="star"></i></span></a>
                        </div>
                        <a href="./read.jsp" class="email-list-detail">
                          <div>
                            <span class="from">Penelope Thornton</span>
                            <p class="msg">Urgent - You forgot your keys in the class room, please come imediatly!</p>
                          </div>
                          <span class="date">
                            <span class="icon"><i data-feather="paperclip"></i> </span>
                            28 Jul
                          </span>
                        </a>
                      </div>
                      <div class="email-list-item email-list-item--unread">
                        <div class="email-list-actions">
                          <div class="form-check form-check-flat form-check-primary">
                            <label class="form-check-label">
                              <input type="checkbox" class="form-check-input">
                            </label>
                          </div>
                          <a class="favorite" href="#"><span><i data-feather="star"></i></span></a>
                        </div>
                        <a href="./read.jsp" class="email-list-detail">
                          <div>
                            <span class="from">Penelope Thornton</span>
                            <p class="msg">Urgent - You forgot your keys in the class room, please come imediatly!</p>
                          </div>
                          <span class="date">
                            <span class="icon"><i data-feather="paperclip"></i> </span>
                            28 Jul
                          </span>
                        </a>
                      </div>
                      <div class="email-list-item">
                        <div class="email-list-actions">
                          <div class="form-check form-check-flat form-check-primary">
                            <label class="form-check-label">
                              <input type="checkbox" class="form-check-input">
                            </label>
                          </div>
                          <a class="favorite active" href="#"><span><i data-feather="star"></i></span></a>
                        </div>
                        <a href="./read.jsp" class="email-list-detail">
                          <div>
                            <span class="from">Penelope Thornton</span>
                            <p class="msg">Urgent - You forgot your keys in the class room, please come imediatly!</p>
                          </div>
                          <span class="date">
                            28 Jul
                          </span>
                        </a>
                      </div>
                      <div class="email-list-item">
                        <div class="email-list-actions">
                          <div class="form-check form-check-flat form-check-primary">
                            <label class="form-check-label">
                              <input type="checkbox" class="form-check-input">
                            </label>
                          </div>
                          <a class="favorite active" href="#"><span><i data-feather="star"></i></span></a>
                        </div>
                        <a href="./read.jsp" class="email-list-detail">
                          <div>
                            <span class="from">Penelope Thornton</span>
                            <p class="msg">Urgent - You forgot your keys in the class room, please come imediatly!</p>
                          </div>
                          <span class="date">
                            <span class="icon"><i data-feather="paperclip"></i> </span>
                            28 Jul
                          </span>
                        </a>
                      </div>
                      <div class="email-list-item">
                        <div class="email-list-actions">
                          <div class="form-check form-check-flat form-check-primary">
                            <label class="form-check-label">
                              <input type="checkbox" class="form-check-input">
                            </label>
                          </div>
                          <a class="favorite" href="#"><span><i data-feather="star"></i></span></a>
                        </div>
                        <a href="./read.jsp" class="email-list-detail">
                          <div>
                            <span class="from">Penelope Thornton</span>
                            <p class="msg">Urgent - You forgot your keys in the class room, please come imediatly!</p>
                          </div>
                          <span class="date">
                            28 Jul
                          </span>
                        </a>
                      </div>
                      <div class="email-list-item">
                        <div class="email-list-actions">
                          <div class="form-check form-check-flat form-check-primary">
                            <label class="form-check-label">
                              <input type="checkbox" class="form-check-input">
                            </label>
                          </div>
                          <a class="favorite" href="#"><span><i data-feather="star"></i></span></a>
                        </div>
                        <a href="./read.jsp" class="email-list-detail">
                          <div>
                            <span class="from">Penelope Thornton</span>
                            <p class="msg">Urgent - You forgot your keys in the class room, please come imediatly!</p>
                          </div>
                          <span class="date">
                            28 Jul
                          </span>
                        </a>
                      </div>
                      <div class="email-list-item">
                        <div class="email-list-actions">
                          <div class="form-check form-check-flat form-check-primary">
                            <label class="form-check-label">
                              <input type="checkbox" class="form-check-input">
                            </label>
                          </div>
                          <a class="favorite" href="#"><span><i data-feather="star"></i></span></a>
                        </div>
                        <a href="./read.jsp" class="email-list-detail">
                          <div>
                            <span class="from">Penelope Thornton</span>
                            <p class="msg">Urgent - You forgot your keys in the class room, please come imediatly!</p>
                          </div>
                          <span class="date">
                            <span class="icon"><i data-feather="paperclip"></i> </span>
                            28 Jul
                          </span>
                        </a>
                      </div>
                      <div class="email-list-item">
                        <div class="email-list-actions">
                          <div class="form-check form-check-flat form-check-primary">
                            <label class="form-check-label">
                              <input type="checkbox" class="form-check-input">
                            </label>
                          </div>
                          <a class="favorite active" href="#"><span><i data-feather="star"></i></span></a>
                        </div>
                        <a href="./read.jsp" class="email-list-detail">
                          <div>
                            <span class="from">Penelope Thornton</span>
                            <p class="msg">Urgent - You forgot your keys in the class room, please come imediatly!</p>
                          </div>
                          <span class="date">
                            <span class="icon"><i data-feather="paperclip"></i> </span>
                            28 Jul
                          </span>
                        </a>
                      </div>
                      <div class="email-list-item">
                        <div class="email-list-actions">
                          <div class="form-check form-check-flat form-check-primary">
                            <label class="form-check-label">
                              <input type="checkbox" class="form-check-input">
                            </label>
                          </div>
                          <a class="favorite" href="#"><span><i data-feather="star"></i></span></a>
                        </div>
                        <a href="./read.jsp" class="email-list-detail">
                          <div>
                            <span class="from">Penelope Thornton</span>
                            <p class="msg">Urgent - You forgot your keys in the class room, please come imediatly!</p>
                          </div>
                          <span class="date">
                            28 Jul
                          </span>
                        </a>
                      </div>
                      <div class="email-list-item">
                        <div class="email-list-actions">
                          <div class="form-check form-check-flat form-check-primary">
                            <label class="form-check-label">
                              <input type="checkbox" class="form-check-input">
                            </label>
                          </div>
                          <a class="favorite" href="#"><span><i data-feather="star"></i></span></a>
                        </div>
                        <a href="./read.jsp" class="email-list-detail">
                          <div>
                            <span class="from">Penelope Thornton</span>
                            <p class="msg">Urgent - You forgot your keys in the class room, please come imediatly!</p>
                          </div>
                          <span class="date">
                            28 Jul
                          </span>
                        </a>
                      </div>
                      <div class="email-list-item">
                        <div class="email-list-actions">
                          <div class="form-check form-check-flat form-check-primary">
                            <label class="form-check-label">
                              <input type="checkbox" class="form-check-input">
                            </label>
                          </div>
                          <a class="favorite" href="#"><span><i data-feather="star"></i></span></a>
                        </div>
                        <a href="./read.jsp" class="email-list-detail">
                          <div>
                            <span class="from">Penelope Thornton</span>
                            <p class="msg">Urgent - You forgot your keys in the class room, please come imediatly!</p>
                          </div>
                          <span class="date">
                            <span class="icon"><i data-feather="paperclip"></i> </span>
                            28 Jul
                          </span>
                        </a>
                      </div>
                      <div class="email-list-item">
                        <div class="email-list-actions">
                          <div class="form-check form-check-flat form-check-primary">
                            <label class="form-check-label">
                              <input type="checkbox" class="form-check-input">
                            </label>
                          </div>
                          <a class="favorite" href="#"><span><i data-feather="star"></i></span></a>
                        </div>
                        <a href="./read.jsp" class="email-list-detail">
                          <div>
                            <span class="from">Penelope Thornton</span>
                            <p class="msg">Urgent - You forgot your keys in the class room, please come imediatly!</p>
                          </div>
                          <span class="date">
                            <span class="icon"><i data-feather="paperclip"></i> </span>
                            28 Jul
                          </span>
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
                
              </div>
            </div>
          </div>
        </div>

			</div>

			<!-- partial:../../partials/_footer.html -->
			<footer class="footer d-flex flex-column flex-md-row align-items-center justify-content-between">
				<p class="text-muted text-center text-md-left">Copyright © 2020 <a href="https://www.nobleui.com" target="_blank">NobleUI</a>. All rights reserved</p>
				<p class="text-muted text-center text-md-left mb-0 d-none d-md-block">Handcrafted With <i class="mb-1 text-primary ml-1 icon-small" data-feather="heart"></i></p>
			</footer>
			<!-- partial -->
	
		</div>
	</div>

	<!-- core:js -->
	<script src="../../../assets/vendors/core/core.js"></script>
	<!-- endinject -->
	<!-- plugin js for this page -->
	<!-- end plugin js for this page -->
	<!-- inject:js -->
	<script src="../../../assets/vendors/feather-icons/feather.min.js"></script>
	<script src="../../../assets/js/template.js"></script>
	<!-- endinject -->
	<!-- custom js for this page -->
	<!-- end custom js for this page -->
</body>
</html>
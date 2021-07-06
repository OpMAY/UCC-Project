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

        <div class="profile-page tx-13">
          <div class="row">
            <div class="col-12 grid-margin">
							<div class="profile-header">
								<div class="cover">
									<div class="gray-shade"></div>
									<figure>
										<img src="https://via.placeholder.com/1148x272" class="img-fluid" alt="profile cover">
									</figure>
									<div class="cover-body d-flex justify-content-between align-items-center">
										<div>
											<img class="profile-pic" src="https://via.placeholder.com/100x100" alt="profile">
											<span class="profile-name">Amiah Burton</span>
										</div>
										<div class="d-none d-md-block">
											<button class="btn btn-primary btn-icon-text btn-edit-profile">
												<i data-feather="edit" class="btn-icon-prepend"></i> Edit profile
											</button>
										</div>
									</div>
								</div>
								<div class="header-links">
									<ul class="links d-flex align-items-center mt-3 mt-md-0">
										<li class="header-link-item d-flex align-items-center active">
											<i class="mr-1 icon-md" data-feather="columns"></i>
											<a class="pt-1px d-none d-md-block" href="#">Timeline</a>
										</li>
										<li class="header-link-item ml-3 pl-3 border-left d-flex align-items-center">
											<i class="mr-1 icon-md" data-feather="user"></i>
											<a class="pt-1px d-none d-md-block" href="#">About</a>
										</li>
										<li class="header-link-item ml-3 pl-3 border-left d-flex align-items-center">
											<i class="mr-1 icon-md" data-feather="users"></i>
											<a class="pt-1px d-none d-md-block" href="#">Friends <span class="text-muted tx-12">3,765</span></a>
										</li>
										<li class="header-link-item ml-3 pl-3 border-left d-flex align-items-center">
											<i class="mr-1 icon-md" data-feather="image"></i>
											<a class="pt-1px d-none d-md-block" href="#">Photos</a>
										</li>
										<li class="header-link-item ml-3 pl-3 border-left d-flex align-items-center">
											<i class="mr-1 icon-md" data-feather="video"></i>
											<a class="pt-1px d-none d-md-block" href="#">Videos</a>
										</li>
									</ul>
								</div>
            	</div>
            </div>
					</div>
					<div class="row profile-body">
						<!-- left wrapper start -->
						<div class="d-none d-md-block col-md-4 col-xl-3 left-wrapper">
							<div class="card rounded">
								<div class="card-body">
									<div class="d-flex align-items-center justify-content-between mb-2">
										<h6 class="card-title mb-0">About</h6>
										<div class="dropdown">
											<button class="btn p-0" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
												<i class="icon-lg text-muted pb-3px" data-feather="more-horizontal"></i>
											</button>
											<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
												<a class="dropdown-item d-flex align-items-center" href="#"><i data-feather="edit-2" class="icon-sm mr-2"></i> <span class="">Edit</span></a>
												<a class="dropdown-item d-flex align-items-center" href="#"><i data-feather="git-branch" class="icon-sm mr-2"></i> <span class="">Update</span></a>
												<a class="dropdown-item d-flex align-items-center" href="#"><i data-feather="eye" class="icon-sm mr-2"></i> <span class="">View all</span></a>
											</div>
										</div>
									</div>
									<p>Hi! I'm Amiah the Senior UI Designer at NobleUI. We hope you enjoy the design and quality of Social.</p>
									<div class="mt-3">
										<label class="tx-11 font-weight-bold mb-0 text-uppercase">Joined:</label>
										<p class="text-muted">November 15, 2015</p>
									</div>
									<div class="mt-3">
										<label class="tx-11 font-weight-bold mb-0 text-uppercase">Lives:</label>
										<p class="text-muted">New York, USA</p>
									</div>
									<div class="mt-3">
										<label class="tx-11 font-weight-bold mb-0 text-uppercase">Email:</label>
										<p class="text-muted">me@nobleui.com</p>
									</div>
									<div class="mt-3">
										<label class="tx-11 font-weight-bold mb-0 text-uppercase">Website:</label>
										<p class="text-muted">www.nobleui.com</p>
									</div>
									<div class="mt-3 d-flex social-links">
										<a href="javascript:;" class="btn d-flex align-items-center justify-content-center border mr-2 btn-icon github">
											<i data-feather="github" data-toggle="tooltip" title="github.com/nobleui"></i>
										</a>
										<a href="javascript:;" class="btn d-flex align-items-center justify-content-center border mr-2 btn-icon twitter">
											<i data-feather="twitter" data-toggle="tooltip" title="twitter.com/nobleui"></i>
										</a>
										<a href="javascript:;" class="btn d-flex align-items-center justify-content-center border mr-2 btn-icon instagram">
											<i data-feather="instagram" data-toggle="tooltip" title="instagram.com/nobleui"></i>
										</a>
									</div>
								</div>
							</div>
						</div>
						<!-- left wrapper end -->
						<!-- middle wrapper start -->
						<div class="col-md-8 col-xl-6 middle-wrapper">
							<div class="row">
								<div class="col-md-12 grid-margin">
									<div class="card rounded">
										<div class="card-header">
											<div class="d-flex align-items-center justify-content-between">
												<div class="d-flex align-items-center">
													<img class="img-xs rounded-circle" src="https://via.placeholder.com/37x37" alt="">													
													<div class="ml-2">
														<p>Mike Popescu</p>
														<p class="tx-11 text-muted">1 min ago</p>
													</div>
												</div>
												<div class="dropdown">
													<button class="btn p-0" type="button" id="dropdownMenuButton2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
														<i class="icon-lg pb-3px" data-feather="more-horizontal"></i>
													</button>
													<div class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
														<a class="dropdown-item d-flex align-items-center" href="#"><i data-feather="meh" class="icon-sm mr-2"></i> <span class="">Unfollow</span></a>
														<a class="dropdown-item d-flex align-items-center" href="#"><i data-feather="corner-right-up" class="icon-sm mr-2"></i> <span class="">Go to post</span></a>
														<a class="dropdown-item d-flex align-items-center" href="#"><i data-feather="share-2" class="icon-sm mr-2"></i> <span class="">Share</span></a>
														<a class="dropdown-item d-flex align-items-center" href="#"><i data-feather="copy" class="icon-sm mr-2"></i> <span class="">Copy link</span></a>
													</div>
												</div>
											</div>
										</div>
										<div class="card-body">
											<p class="mb-3 tx-14">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus minima delectus nemo unde quae recusandae assumenda.</p>
											<img class="img-fluid" src="https://via.placeholder.com/513x342" alt="">
										</div>
										<div class="card-footer">
											<div class="d-flex post-actions">
												<a href="javascript:;" class="d-flex align-items-center text-muted mr-4">
													<i class="icon-md" data-feather="heart"></i>
													<p class="d-none d-md-block ml-2">Like</p>
												</a>
												<a href="javascript:;" class="d-flex align-items-center text-muted mr-4">
													<i class="icon-md" data-feather="message-square"></i>
													<p class="d-none d-md-block ml-2">Comment</p>
												</a>
												<a href="javascript:;" class="d-flex align-items-center text-muted">
													<i class="icon-md" data-feather="share"></i>
													<p class="d-none d-md-block ml-2">Share</p>
												</a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="card rounded">
										<div class="card-header">
											<div class="d-flex align-items-center justify-content-between">
												<div class="d-flex align-items-center">
													<img class="img-xs rounded-circle" src="https://via.placeholder.com/37x37" alt="">													
													<div class="ml-2">
														<p>Mike Popescu</p>
														<p class="tx-11 text-muted">5 min ago</p>
													</div>
												</div>
												<div class="dropdown">
													<button class="btn p-0" type="button" id="dropdownMenuButton3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
														<i class="icon-lg pb-3px" data-feather="more-horizontal"></i>
													</button>
													<div class="dropdown-menu" aria-labelledby="dropdownMenuButton3">
														<a class="dropdown-item d-flex align-items-center" href="#"><i data-feather="meh" class="icon-sm mr-2"></i> <span class="">Unfollow</span></a>
														<a class="dropdown-item d-flex align-items-center" href="#"><i data-feather="corner-right-up" class="icon-sm mr-2"></i> <span class="">Go to post</span></a>
														<a class="dropdown-item d-flex align-items-center" href="#"><i data-feather="share-2" class="icon-sm mr-2"></i> <span class="">Share</span></a>
														<a class="dropdown-item d-flex align-items-center" href="#"><i data-feather="copy" class="icon-sm mr-2"></i> <span class="">Copy link</span></a>
													</div>
												</div>
											</div>
										</div>
										<div class="card-body">
											<p class="mb-3 tx-14">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
											<img class="img-fluid" src="https://via.placeholder.com/513x342" alt="">
										</div>
										<div class="card-footer">
											<div class="d-flex post-actions">
												<a href="javascript:;" class="d-flex align-items-center text-muted mr-4">
													<i class="icon-md" data-feather="heart"></i>
													<p class="d-none d-md-block ml-2">Like</p>
												</a>
												<a href="javascript:;" class="d-flex align-items-center text-muted mr-4">
													<i class="icon-md" data-feather="message-square"></i>
													<p class="d-none d-md-block ml-2">Comment</p>
												</a>
												<a href="javascript:;" class="d-flex align-items-center text-muted">
													<i class="icon-md" data-feather="share"></i>
													<p class="d-none d-md-block ml-2">Share</p>
												</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- middle wrapper end -->
						<!-- right wrapper start -->
						<div class="d-none d-xl-block col-xl-3 right-wrapper">
							<div class="row">
								<div class="col-md-12 grid-margin">
									<div class="card rounded">
										<div class="card-body">
											<h6 class="card-title">latest photos</h6>
											<div class="latest-photos">
												<div class="row">
													<div class="col-md-4">
														<figure>
															<img class="img-fluid" src="https://via.placeholder.com/67x67" alt="">
														</figure>
													</div>
													<div class="col-md-4">
														<figure>
															<img class="img-fluid" src="https://via.placeholder.com/67x67" alt="">
														</figure>
													</div>
													<div class="col-md-4">
														<figure>
															<img class="img-fluid" src="https://via.placeholder.com/67x67" alt="">
														</figure>
													</div>
													<div class="col-md-4">
														<figure>
															<img class="img-fluid" src="https://via.placeholder.com/67x67" alt="">
														</figure>
													</div>
													<div class="col-md-4">
														<figure>
															<img class="img-fluid" src="https://via.placeholder.com/67x67" alt="">
														</figure>
													</div>
													<div class="col-md-4">
														<figure>
															<img class="img-fluid" src="https://via.placeholder.com/67x67" alt="">
														</figure>
													</div>
													<div class="col-md-4">
														<figure class="mb-0">
															<img class="img-fluid" src="https://via.placeholder.com/67x67" alt="">
														</figure>
													</div>
													<div class="col-md-4">
														<figure class="mb-0">
															<img class="img-fluid" src="https://via.placeholder.com/67x67" alt="">
														</figure>
													</div>
													<div class="col-md-4">
														<figure class="mb-0">
															<img class="img-fluid" src="https://via.placeholder.com/67x67" alt="">
														</figure>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12 grid-margin">
									<div class="card rounded">
										<div class="card-body">
											<h6 class="card-title">suggestions for you</h6>
											<div class="d-flex justify-content-between mb-2 pb-2 border-bottom">
												<div class="d-flex align-items-center hover-pointer">
													<img class="img-xs rounded-circle" src="https://via.placeholder.com/37x37" alt="">													
													<div class="ml-2">
														<p>Mike Popescu</p>
														<p class="tx-11 text-muted">12 Mutual Friends</p>
													</div>
												</div>
												<button class="btn btn-icon"><i data-feather="user-plus" data-toggle="tooltip" title="Connect"></i></button>
											</div>
											<div class="d-flex justify-content-between mb-2 pb-2 border-bottom">
												<div class="d-flex align-items-center hover-pointer">
													<img class="img-xs rounded-circle" src="https://via.placeholder.com/37x37" alt="">													
													<div class="ml-2">
														<p>Mike Popescu</p>
														<p class="tx-11 text-muted">12 Mutual Friends</p>
													</div>
												</div>
												<button class="btn btn-icon"><i data-feather="user-plus" data-toggle="tooltip" title="Connect"></i></button>
											</div>
											<div class="d-flex justify-content-between mb-2 pb-2 border-bottom">
												<div class="d-flex align-items-center hover-pointer">
													<img class="img-xs rounded-circle" src="https://via.placeholder.com/37x37" alt="">													
													<div class="ml-2">
														<p>Mike Popescu</p>
														<p class="tx-11 text-muted">12 Mutual Friends</p>
													</div>
												</div>
												<button class="btn btn-icon"><i data-feather="user-plus" data-toggle="tooltip" title="Connect"></i></button>
											</div>
											<div class="d-flex justify-content-between mb-2 pb-2 border-bottom">
												<div class="d-flex align-items-center hover-pointer">
													<img class="img-xs rounded-circle" src="https://via.placeholder.com/37x37" alt="">													
													<div class="ml-2">
														<p>Mike Popescu</p>
														<p class="tx-11 text-muted">12 Mutual Friends</p>
													</div>
												</div>
												<button class="btn btn-icon"><i data-feather="user-plus" data-toggle="tooltip" title="Connect"></i></button>
											</div>
											<div class="d-flex justify-content-between mb-2 pb-2 border-bottom">
												<div class="d-flex align-items-center hover-pointer">
													<img class="img-xs rounded-circle" src="https://via.placeholder.com/37x37" alt="">													
													<div class="ml-2">
														<p>Mike Popescu</p>
														<p class="tx-11 text-muted">12 Mutual Friends</p>
													</div>
												</div>
												<button class="btn btn-icon"><i data-feather="user-plus" data-toggle="tooltip" title="Connect"></i></button>
											</div>
											<div class="d-flex justify-content-between">
												<div class="d-flex align-items-center hover-pointer">
													<img class="img-xs rounded-circle" src="https://via.placeholder.com/37x37" alt="">													
													<div class="ml-2">
														<p>Mike Popescu</p>
														<p class="tx-11 text-muted">12 Mutual Friends</p>
													</div>
												</div>
												<button class="btn btn-icon"><i data-feather="user-plus" data-toggle="tooltip" title="Connect"></i></button>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- right wrapper end -->
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
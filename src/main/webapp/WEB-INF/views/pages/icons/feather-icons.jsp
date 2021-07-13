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

				<nav class="page-breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Icons</a></li>
						<li class="breadcrumb-item active" aria-current="page">Feather Icons</li>
					</ol>
				</nav>

				<div class="row">
					<div class="col-md-12 stretch-card">
            <div class="card">
              <div class="card-body">
								<h6 class="card-title">Feather Icons</h6>
                <p class="card-description">Visit the <a href="https://feathericons.com/" target="_blank"> Official Feather Icons Documentation </a>.</p>
                <p class="card-description">Usage example : &lt;i data-feather="star"&gt;&lt;/i&gt;</p>                           
                <div class="container">
                  <div class="icons-list row">
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="activity"></i> activity </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="airplay"></i> airplay </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="alert-circle"></i> alert-circle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="alert-octagon"></i> alert-octagon </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="alert-triangle"></i> alert-triangle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="align-center"></i> align-center </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="align-justify"></i> align-justify </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="align-left"></i> align-left </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="align-right"></i> align-right </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="anchor"></i> anchor </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="aperture"></i> aperture </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="archive"></i> archive </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="arrow-down-circle"></i> arrow-down-circle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="arrow-down-left"></i> arrow-down-left </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="arrow-down-right"></i> arrow-down-right </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="arrow-down"></i> arrow-down </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="arrow-left-circle"></i> arrow-left-circle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="arrow-left"></i> arrow-left </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="arrow-right-circle"></i> arrow-right-circle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="arrow-right"></i> arrow-right </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="arrow-up-circle"></i> arrow-up-circle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="arrow-up-left"></i> arrow-up-left </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="arrow-up-right"></i> arrow-up-right </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="arrow-up"></i> arrow-up </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="at-sign"></i> at-sign </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="award"></i> award </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="bar-chart-2"></i> bar-chart-2 </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="bar-chart"></i> bar-chart </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="battery-charging"></i> battery-charging </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="battery"></i> battery </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="bell-off"></i> bell-off </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="bell"></i> bell </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="bluetooth"></i> bluetooth </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="bold"></i> bold </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="book-open"></i> book-open </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="book"></i> book </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="bookmark"></i> bookmark </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="box"></i> box </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="briefcase"></i> briefcase </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="calendar"></i> calendar </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="camera-off"></i> camera-off </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="camera"></i> camera </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="cast"></i> cast </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="check-circle"></i> check-circle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="check-square"></i> check-square </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="check"></i> check </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="chevron-down"></i> chevron-down </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="chevron-left"></i> chevron-left </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="chevron-right"></i> chevron-right </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="chevron-up"></i> chevron-up </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="chevrons-down"></i> chevrons-down </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="chevrons-left"></i> chevrons-left </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="chevrons-right"></i> chevrons-right </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="chevrons-up"></i> chevrons-up </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="chrome"></i> chrome </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="clipboard"></i> clipboard </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="clock"></i> clock </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="cloud-drizzle"></i> cloud-drizzle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="cloud-lightning"></i> cloud-lightning </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="cloud-off"></i> cloud-off </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="cloud-rain"></i> cloud-rain </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="cloud-snow"></i> cloud-snow </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="cloud"></i> cloud </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="code"></i> code </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="codepen"></i> codepen </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="codesandbox"></i> codesandbox </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="coffee"></i> coffee </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="columns"></i> columns </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="command"></i> command </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="compass"></i> compass </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="copy"></i> copy </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="corner-down-left"></i> corner-down-left </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="corner-down-right"></i> corner-down-right </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="corner-left-down"></i> corner-left-down </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="corner-left-up"></i> corner-left-up </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="corner-right-down"></i> corner-right-down </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="corner-right-up"></i> corner-right-up </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="corner-up-left"></i> corner-up-left </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="corner-up-right"></i> corner-up-right </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="cpu"></i> cpu </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="credit-card"></i> credit-card </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="crop"></i> crop </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="crosshair"></i> crosshair </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="database"></i> database </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="delete"></i> delete </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="disc"></i> disc </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="dollar-sign"></i> dollar-sign </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="download-cloud"></i> download-cloud </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="download"></i> download </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="droplet"></i> droplet </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="edit-2"></i> edit-2 </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="edit-3"></i> edit-3 </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="edit"></i> edit </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="external-link"></i> external-link </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="eye-off"></i> eye-off </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="eye"></i> eye </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="facebook"></i> facebook </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="fast-forward"></i> fast-forward </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="feather"></i> feather </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="figma"></i> figma </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="file-minus"></i> file-minus </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="file-plus"></i> file-plus </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="file-text"></i> file-text </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="file"></i> file </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="film"></i> film </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="filter"></i> filter </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="flag"></i> flag </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="folder-minus"></i> folder-minus </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="folder-plus"></i> folder-plus </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="folder"></i> folder </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="frown"></i> frown </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="gift"></i> gift </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="git-branch"></i> git-branch </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="git-commit"></i> git-commit </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="git-merge"></i> git-merge </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="git-pull-request"></i> git-pull-request </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="github"></i> github </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="gitlab"></i> gitlab </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="globe"></i> globe </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="grid"></i> grid </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="hard-drive"></i> hard-drive </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="hash"></i> hash </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="headphones"></i> headphones </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="airplay"></i> airplay </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="heart"></i> heart </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="help-circle"></i> help-circle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="hexagon"></i> hexagon </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="home"></i> home </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="image"></i> image </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="inbox"></i> inbox </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="info"></i> info </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="instagram"></i> instagram </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="italic"></i> italic </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="key"></i> key </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="layers"></i> layers </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="layout"></i> layout </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="life-buoy"></i> life-buoy </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="link-2"></i> link-2 </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="link"></i> link </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="linkedin"></i> linkedin </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="list"></i> list </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="loader"></i> loader </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="lock"></i> lock </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="log-in"></i> log-in </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="log-out"></i> log-out </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="mail"></i> mail </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="map-pin"></i> map-pin </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="map"></i> map </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="maximize-2"></i> maximize-2 </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="maximize"></i> maximize </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="minus-circle"></i> minus-circle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="minus-square"></i> minus-square </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="minus"></i> minus </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="monitor"></i> monitor </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="moon"></i> moon </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="more-horizontal"></i> more-horizontal </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="more-vertical"></i> more-vertical </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="mouse-pointer"></i> mouse-pointer </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="move"></i> move </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="music"></i> music </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="navigation-2"></i> navigation-2 </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="navigation"></i> navigation </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="octagon"></i> octagon </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="package"></i> package </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="paperclip"></i> paperclip </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="pause-circle"></i> pause-circle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="pause"></i> pause </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="pen-tool"></i> pen-tool </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="percent"></i> percent </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="phone-call"></i> phone-call </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="phone-forwarded"></i> phone-forwarded </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="phone-incoming"></i> phone-incoming </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="phone-missed"></i> phone-missed </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="phone-off"></i> phone-off </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="phone-outgoing"></i> phone-outgoing </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="phone"></i> phone </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="pie-chart"></i> pie-chart </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="play-circle"></i> play-circle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="play"></i> play </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="plus-circle"></i> plus-circle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="plus-square"></i> plus-square </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="plus"></i> plus </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="pocket"></i> pocket </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="power"></i> power </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="printer"></i> printer </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="radio"></i> radio </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="refresh-ccw"></i> refresh-ccw </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="rotate-cw"></i> rotate-cw </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="rss"></i> rss </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="save"></i> save </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="scissors"></i> scissors </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="search"></i> search </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="send"></i> send </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="server"></i> server </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="settings"></i> settings </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="share-2"></i> share-2 </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="share"></i> share </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="shield-off"></i> shield-off </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="shield"></i> shield </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="shopping-bag"></i> shopping-bag </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="shopping-cart"></i> shopping-cart </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="shuffle"></i> shuffle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="sidebar"></i> sidebar </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="skip-back"></i> skip-back </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="skip-forward"></i> skip-forward </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="slack"></i> slack </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="slash"></i> slash </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="sliders"></i> sliders </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="smartphone"></i> smartphone </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="smile"></i> smile </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="speaker"></i> speaker </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="square"></i> square </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="star"></i> star </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="stop-circle"></i> stop-circle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="sun"></i> sun </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="sunrise"></i> sunrise </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="sunset"></i> sunset </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="tablet"></i> tablet </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="tag"></i> tag </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="target"></i> target </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="terminal"></i> terminal </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="thermometer"></i> airplay </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="thumbs-down"></i> thumbs-down </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="thumbs-up"></i> thumbs-up </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="toggle-left"></i> toggle-left </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="toggle-right"></i> toggle-right </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="trash-2"></i> airplay </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="trash"></i> trash </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="trello"></i> trello </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="trending-down"></i> trending-down </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="trending-up"></i> trending-up </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="triangle"></i> triangle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="truck"></i> truck </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="tv"></i> tv </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="twitter"></i> twitter </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="type"></i> type </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="umbrella"></i> umbrella </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="underline"></i> underline </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="unlock"></i> unlock </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="upload-cloud"></i> upload-cloud </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="upload"></i> upload </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="user-check"></i> user-check </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="user-plus"></i> user-plus </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="user-minus"></i> user-minus </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="user-x"></i> user-x </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="user"></i> user </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="users"></i> users </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="video-off"></i> video-off </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="video"></i> video </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="voicemail"></i> voicemail </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="volume-1"></i> volume-1 </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="volume-2"></i> volume-2 </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="volume-x"></i> volume-x </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="volume"></i> volume </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="watch"></i> watch </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="wifi-off"></i> wifi-off </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="wifi"></i> wifi </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="wind"></i> wind </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="x-circle"></i> x-circle </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="x-octagon"></i> x-octagon </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="x-square"></i> x-square </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="x"></i> x </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="youtube"></i> youtube </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="zap-off"></i> zap-off </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="zap"></i> zap </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="zoom-in"></i> zoom-in </div>
                    <div class="col-sm-6 col-md-4 col-lg-3"> <i data-feather="zoom-out"></i> zoom-out </div>
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
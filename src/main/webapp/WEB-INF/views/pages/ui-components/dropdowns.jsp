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
	<link rel="stylesheet" href="../../../assets/vendors/prismjs/themes/prism.css">
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

		<!-- partial:../../partials/_sidebar.html -->
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
				
			<!-- partial:../../partials/_navbar.html -->
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
				<div class="row">
					<div class="col-xl-10 main-content pl-xl-4 pr-xl-5">
						<h1 class="page-title">Dropdowns</h1>
						<p class="lead">Dropdowns are toggleable, contextual overlays for displaying lists of links and more. They’re made interactive with the included Bootstrap dropdown javascript plugin. Read the <a href="https://getbootstrap.com/docs/4.3/components/dropdowns/dropdowns.jsp" target="_blank">Official Bootstrap Documentation</a> for a full list of instructions and other options.</p>
						
						<hr>
						
						<h4 id="default">Basic Example</h4>
						<p class="mb-3">Wrap the dropdown’s toggle (your button or link) and the dropdown menu within <code>.dropdown</code>, or another element that declares position relative. Dropdowns can be triggered from link or button elements to better fit your potential needs.</p>
						<div class="example">
							<div class="dropdown">
								<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Dropdown button
								</button>
								<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
						</div>
						<figure class="highlight" id="defaultDropdown">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="dropdown">
	<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Dropdown button
	</button>
	<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		<a class="dropdown-item" href="#">Action</a>
		<a class="dropdown-item" href="#">Another action</a>
		<a class="dropdown-item" href="#">Something else here</a>
	</div>
</div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#defaultDropdown"><i data-feather="copy"></i></button>
            </figure>
            
						<hr>
						
						<h4 id="variations">Dropdown variations</h4>
						<p class="mb-3">Add class <code>.btn-*</code> for solid colored buttons.</p>
						<div class="example">
							<div class="btn-group">
								<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Primary</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Secondary</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Success</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Info</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Warning</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Danger</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
						</div>
						<figure class="highlight" id="dropdownVariations">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="btn-group">
	<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Primary</button>
	<div class="dropdown-menu">
		<a class="dropdown-item" href="#">Action</a>
		<a class="dropdown-item" href="#">Another action</a>
		<a class="dropdown-item" href="#">Something else here</a>
	</div>
</div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#dropdownVariations"><i data-feather="copy"></i></button>
            </figure>
            
						<hr>
						
						<h4 id="split">Split button</h4>
						<p class="mb-3">Similarly, create split button dropdowns with virtually the same markup as single button dropdowns, but with the addition of <code>.dropdown-toggle-split</code> for proper spacing around the dropdown caret.</p>
						<div class="example">
							<div class="btn-group">
								<button type="button" class="btn btn-primary">Primary</button>
								<button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									<span class="sr-only">Toggle Dropdown</span>
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-secondary">Secondary</button>
								<button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									<span class="sr-only">Toggle Dropdown</span>
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-success">Success</button>
								<button type="button" class="btn btn-success dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									<span class="sr-only">Toggle Dropdown</span>
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-info">Info</button>
								<button type="button" class="btn btn-info dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									<span class="sr-only">Toggle Dropdown</span>
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-warning">Warning</button>
								<button type="button" class="btn btn-warning dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									<span class="sr-only">Toggle Dropdown</span>
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-danger">Danger</button>
								<button type="button" class="btn btn-danger dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									<span class="sr-only">Toggle Dropdown</span>
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
						</div>
						<figure class="highlight" id="splitButton">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="btn-group">
	<button type="button" class="btn btn-primary">Primary</button>
	<button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		<span class="sr-only">Toggle Dropdown</span>
	</button>
	<div class="dropdown-menu">
		<a class="dropdown-item" href="#">Action</a>
		<a class="dropdown-item" href="#">Another action</a>
		<a class="dropdown-item" href="#">Something else here</a>
	</div>
</div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#splitButton"><i data-feather="copy"></i></button>
            </figure>
            
						<hr>
						
						<h4 id="sizing">Sizing</h4>
						<p class="mb-3">Button dropdowns work with buttons of all sizes, including default and split dropdown buttons.</p>
						<div class="example">
								<div class="btn-toolbar" role="toolbar">
									<div class="btn-group">
										<button class="btn btn-secondary btn-lg dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
											Large button
										</button>
										<div class="dropdown-menu">
											<a class="dropdown-item" href="#">Action</a>
											<a class="dropdown-item" href="#">Another action</a>
											<a class="dropdown-item" href="#">Something else here</a>
										</div>
									</div><!-- /btn-group -->
									<div class="btn-group ml-2">
										<button type="button" class="btn btn-lg btn-secondary">Large split button</button>
										<button type="button" class="btn btn-lg btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
											<span class="sr-only">Toggle Dropdown</span>
										</button>
										<div class="dropdown-menu">
											<a class="dropdown-item" href="#">Action</a>
											<a class="dropdown-item" href="#">Another action</a>
											<a class="dropdown-item" href="#">Something else here</a>
										</div>
									</div><!-- /btn-group -->
								</div>
								<div class="btn-toolbar" role="toolbar">
									<div class="btn-group">
										<button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
											Small button
										</button>
										<div class="dropdown-menu">
											<a class="dropdown-item" href="#">Action</a>
											<a class="dropdown-item" href="#">Another action</a>
											<a class="dropdown-item" href="#">Something else here</a>
										</div>
									</div><!-- /btn-group -->
									<div class="btn-group ml-2">
										<button type="button" class="btn btn-sm btn-secondary">Small split button</button>
										<button type="button" class="btn btn-sm btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
											<span class="sr-only">Toggle Dropdown</span>
										</button>
										<div class="dropdown-menu">
											<a class="dropdown-item" href="#">Action</a>
											<a class="dropdown-item" href="#">Another action</a>
											<a class="dropdown-item" href="#">Something else here</a>
										</div>
									</div><!-- /btn-group -->
								</div>
						</div>
						<figure class="highlight" id="buttonSizing">
<pre><code class="language-markup"><script type="script/prism-html-markup"><!-- Large button groups (default and split) -->
<div class="btn-group">
	<button class="btn btn-secondary btn-lg dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Large button
	</button>
	<div class="dropdown-menu">
		...
	</div>
</div>
<div class="btn-group">
	<button class="btn btn-secondary btn-lg" type="button">
		Large split button
	</button>
	<button type="button" class="btn btn-lg btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		<span class="sr-only">Toggle Dropdown</span>
	</button>
	<div class="dropdown-menu">
		...
	</div>
</div>

<!-- Small button groups (default and split) -->
<div class="btn-group">
	<button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Small button
	</button>
	<div class="dropdown-menu">
		...
	</div>
</div>
<div class="btn-group">
	<button class="btn btn-secondary btn-sm" type="button">
		Small split button
	</button>
	<button type="button" class="btn btn-sm btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		<span class="sr-only">Toggle Dropdown</span>
	</button>
	<div class="dropdown-menu">
		...
	</div>
</div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#buttonSizing"><i data-feather="copy"></i></button>
            </figure>
            
						<hr>
						
						<h4 id="dropup">Dropup</h4>
						<p class="mb-3">Trigger dropdown menus above elements by adding <code>.dropup</code> to the parent element.</p>
						<div class="example">
							<div class="btn-group dropup">
								<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Dropup
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
							<div class="btn-group dropup">
								<button type="button" class="btn btn-secondary">
									Split dropup
								</button>
								<button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									<span class="sr-only">Toggle Dropdown</span>
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
						</div>
						<figure class="highlight" id="dropUP">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="btn-group dropup">
	<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Dropup
	</button>
	<div class="dropdown-menu">
		<a class="dropdown-item" href="#">Action</a>
		<a class="dropdown-item" href="#">Another action</a>
		<a class="dropdown-item" href="#">Something else here</a>
	</div>
</div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#dropUP"><i data-feather="copy"></i></button>
            </figure>
            
						<hr>
						
						<h4 id="dropright">Dropright</h4>
						<p class="mb-3">Trigger dropdown menus at the right of the elements by adding <code>.dropright</code> to the parent element.</p>
						<div class="example">
							<div class="btn-group dropright">
								<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Dropright
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#">Separated link</a>
								</div>
							</div>
							<div class="btn-group dropright">
								<button type="button" class="btn btn-secondary">
									Split dropright
								</button>
								<button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									<span class="sr-only">Toggle Dropright</span>
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#">Separated link</a>
								</div>
							</div>
						</div>
						<figure class="highlight" id="dropRight">
<pre><code class="language-markup"><script type="script/prism-html-markup"><!-- Default dropright button -->
<div class="btn-group dropright">
	<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Dropright
	</button>
	<div class="dropdown-menu">
		<!-- Dropdown menu links -->
	</div>
</div>

<!-- Split dropright button -->
<div class="btn-group dropright">
	<button type="button" class="btn btn-secondary">
		Split dropright
	</button>
	<button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		<span class="sr-only">Toggle Dropright</span>
	</button>
	<div class="dropdown-menu">
		<!-- Dropdown menu links -->
	</div>
</div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#dropRight"><i data-feather="copy"></i></button>
            </figure>
            
						<hr>
						
						<h4 id="dropleft">Dropleft</h4>
						<p class="mb-3">Trigger dropdown menus at the right of the elements by adding <code>.dropleft</code> to the parent element.</p>
						<div class="example">
							<div class="btn-group dropleft">
								<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Dropleft
								</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#">Separated link</a>
								</div>
							</div>
							<div class="btn-group">
								<div class="btn-group dropleft" role="group">
									<button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										<span class="sr-only">Toggle Dropleft</span>
									</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="#">Action</a>
										<a class="dropdown-item" href="#">Another action</a>
										<a class="dropdown-item" href="#">Something else here</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#">Separated link</a>
									</div>
								</div>
								<button type="button" class="btn btn-secondary">
									Split dropleft
								</button>
							</div>
						</div>
						<figure class="highlight" id="dropLeft">
<pre><code class="language-markup"><script type="script/prism-html-markup"><!-- Default dropleft button -->
<div class="btn-group dropleft">
	<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Dropleft
	</button>
	<div class="dropdown-menu">
		<!-- Dropdown menu links -->
	</div>
</div>

<!-- Split dropleft button -->
<div class="btn-group">
	<div class="btn-group dropleft" role="group">
		<button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			<span class="sr-only">Toggle Dropleft</span>
		</button>
		<div class="dropdown-menu">
			<!-- Dropdown menu links -->
		</div>
	</div>
	<button type="button" class="btn btn-secondary">
		Split dropleft
	</button>
</div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#dropLeft"><i data-feather="copy"></i></button>
            </figure>
            
						<hr>
						
						<h4 id="active-item">Active menu item</h4>
						<p class="mb-3">Add <code>.active</code> to items in the dropdown to <strong>style them as active</strong>.</p>
						<div class="example">
							<div class="dropdown">
								<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Dropdown button
								</button>
								<div class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item active" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
						</div>
						<figure class="highlight" id="activeItem">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="dropdown">
	<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Dropdown button
	</button>
	<div class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
		<a class="dropdown-item" href="#">Action</a>
		<a class="dropdown-item active" href="#">Another action</a>
		<a class="dropdown-item" href="#">Something else here</a>
	</div>
</div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#activeItem"><i data-feather="copy"></i></button>
            </figure>
            
						<hr>
						
						<h4 id="disabled-item">disabled menu item</h4>
						<p class="mb-3">Add <code>.disabled</code> to items in the dropdown to <strong>style them as active</strong>.</p>
						<div class="example">
							<div class="dropdown">
								<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Dropdown button
								</button>
								<div class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item disabled" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
						</div>
						<figure class="highlight" id="disabledItem">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="dropdown">
	<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Dropdown button
	</button>
	<div class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
		<a class="dropdown-item" href="#">Action</a>
		<a class="dropdown-item disabled" href="#">Another action</a>
		<a class="dropdown-item" href="#">Something else here</a>
	</div>
</div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#disabledItem"><i data-feather="copy"></i></button>
            </figure>
            
						<hr>
						
						<h4 id="dropdown-header">Dropdown header</h4>
						<p class="mb-3">Add a header to label sections of actions in any dropdown menu.</p>
						<div class="example">
							<div class="dropdown">
								<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Dropdown button
								</button>
								<div class="dropdown-menu" aria-labelledby="dropdownMenuButton3">
									<h6 class="dropdown-header">Dropdown header</h6>
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
						</div>
						<figure class="highlight" id="dropdownHeader">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="dropdown">
	<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Dropdown button
	</button>
	<div class="dropdown-menu" aria-labelledby="dropdownMenuButton3">
		<h6 class="dropdown-header">Dropdown header</h6>
		<a class="dropdown-item" href="#">Action</a>
		<a class="dropdown-item" href="#">Another action</a>
		<a class="dropdown-item" href="#">Something else here</a>
	</div>
</div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#dropdownHeader"><i data-feather="copy"></i></button>
            </figure>
            
						<hr>
						
						<h4 id="dropdown-dividers">Dropdown dividers</h4>
						<p class="mb-3">Add a header to label sections of actions in any dropdown menu.</p>
						<div class="example">
							<div class="dropdown">
								<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Dropdown button
								</button>
								<div class="dropdown-menu" aria-labelledby="dropdownMenuButton4">
									<a class="dropdown-item" href="#">Action</a>
									<a class="dropdown-item" href="#">Another action</a>
									<a class="dropdown-item" href="#">Something else here</a>
									<div class="dropdown-divider"></div>
  								<a class="dropdown-item" href="#">Separated link</a>
								</div>
							</div>
						</div>
						<figure class="highlight" id="dropdownDividers">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="dropdown">
	<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Dropdown button
	</button>
	<div class="dropdown-menu" aria-labelledby="dropdownMenuButton4">
		<a class="dropdown-item" href="#">Action</a>
		<a class="dropdown-item" href="#">Another action</a>
		<a class="dropdown-item" href="#">Something else here</a>
		<div class="dropdown-divider"></div>
		<a class="dropdown-item" href="#">Separated link</a>
	</div>
</div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#dropdownDividers"><i data-feather="copy"></i></button>
            </figure>
            
						<hr>
						
						<h4 id="dropdown-text">Text in dropdown</h4>
						<p class="mb-3">Place any freeform text within a dropdown menu with text and use spacing utilities. Note that you’ll likely need additional sizing styles to constrain the menu width.</p>
						<div class="example">
							<div class="dropdown">
								<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton5" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Dropdown button
								</button>
								<div class="dropdown-menu p-4 text-muted" aria-labelledby="dropdownMenuButton5">
									<p>Some example text that's free-flowing within the dropdown menu.</p>
									<p class="mb-0">And this is more example text.</p>
								</div>
							</div>
						</div>
						<figure class="highlight" id="dropdownText">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="dropdown">
	<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton5" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Dropdown button
	</button>
	<div class="dropdown-menu p-4 text-muted"  aria-labelledby="dropdownMenuButton5">
		<p>Some example text that's free-flowing within the dropdown menu.</p>
		<p class="mb-0">And this is more example text.</p>
	</div>
</div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#dropdownText"><i data-feather="copy"></i></button>
            </figure>
            
						<hr>
						
						<h4 id="dropdown-form">Forms in dropdown</h4>
						<p>Put a form within a dropdown menu, or make it into a dropdown menu, and use margin or padding utilities to give it the negative space you require.</p>
						<div class="example">
							<div class="dropdown">
								<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton6" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									Dropdown button
								</button>
								<form class="dropdown-menu p-4"  aria-labelledby="dropdownMenuButton6">
									<div class="form-group">
										<label for="exampleDropdownFormEmail2">Email address</label>
										<input type="email" class="form-control" id="exampleDropdownFormEmail2" placeholder="email@example.com">
									</div>
									<div class="form-group">
										<label for="exampleDropdownFormPassword2">Password</label>
										<input type="password" class="form-control" id="exampleDropdownFormPassword2" placeholder="Password">
									</div>
									<button type="submit" class="btn btn-primary">Sign in</button>
								</form>
							</div>
						</div>
						<figure class="highlight" id="dropdownForm">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="dropdown">
	<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton6" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Dropdown button
	</button>
	<form class="dropdown-menu p-4"  aria-labelledby="dropdownMenuButton6">
		<div class="form-group">
			<label for="exampleDropdownFormEmail2">Email address</label>
			<input type="email" class="form-control" id="exampleDropdownFormEmail2" placeholder="email@example.com">
		</div>
		<div class="form-group">
			<label for="exampleDropdownFormPassword2">Password</label>
			<input type="password" class="form-control" id="exampleDropdownFormPassword2" placeholder="Password">
		</div>
		<div class="form-group">
			<div class="form-check">
				<input type="checkbox" class="form-check-input" id="dropdownCheck2">
				<label class="form-check-label" for="dropdownCheck2">
					Remember me
				</label>
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Sign in</button>
	</form>
</div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#dropdownForm"><i data-feather="copy"></i></button>
						</figure>
						            
					</div>
					<div class="col-xl-2 content-nav-wrapper">
						<ul class="nav content-nav d-flex flex-column">
							<li class="nav-item">
								<a href="#default" class="nav-link">Basic example</a>
							</li>
							<li class="nav-item">
								<a href="#variations" class="nav-link">Dropdown variations</a>
							</li>
							<li class="nav-item">
								<a href="#split" class="nav-link">Split button</a>
							</li>
							<li class="nav-item">
								<a href="#sizing" class="nav-link">Sizing</a>
							</li>
							<li class="nav-item">
								<a href="#dropup" class="nav-link">Dropup</a>
							</li>
							<li class="nav-item">
								<a href="#dropright" class="nav-link">Dropright</a>
							</li>
							<li class="nav-item">
								<a href="#dropleft" class="nav-link">Dropleft</a>
							</li>
							<li class="nav-item">
								<a href="#active-item" class="nav-link">Active menu item</a>
							</li>
							<li class="nav-item">
								<a href="#disabled-item" class="nav-link">Disabled menu item</a>
							</li>
							<li class="nav-item">
								<a href="#dropdown-header" class="nav-link">Dropdown header</a>
							</li>
							<li class="nav-item">
								<a href="#dropdown-dividers" class="nav-link">Dropdown dividers</a>
							</li>
							<li class="nav-item">
								<a href="#dropdown-text" class="nav-link">Text in dropdown</a>
							</li>
							<li class="nav-item">
								<a href="#dropdown-form" class="nav-link">Form in dropdown</a>
							</li>
						</ul>
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
	<script src="../../../assets/vendors/prismjs/prism.js"></script>
	<script src="../../../assets/vendors/clipboard/clipboard.min.js"></script>
	<!-- end plugin js for this page -->
	<!-- inject:js -->
	<script src="../../../assets/vendors/feather-icons/feather.min.js"></script>
	<script src="../../../assets/js/template.js"></script>
	<!-- endinject -->
	<!-- custom js for this page -->
	<!-- end custom js for this page -->
</body>
</html>
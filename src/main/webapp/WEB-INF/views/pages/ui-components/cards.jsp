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
				<div class="row">
					<div class="col-xl-10 main-content pl-xl-4 pr-xl-5">
						<h1 class="page-title">Cards</h1>
						<p class="lead">A flexible and extensible content container with multiple variants and options. Read the <a href="https://getbootstrap.com/docs/4.3/components/card/" target="_blank">Official Bootstrap Documentation</a> for a full list of instructions and other options.</p>
						<hr>
						<h4 id="default">Basic Example</h4>
						<p class="mb-3">Below is an example of a basic card with mixed content and a fixed width. Cards have no fixed width to start, so they’ll naturally fill the full width of its parent element. This is easily customized with our various <a href="#sizing">sizing options</a>.</p>
						<div class="example">
              <div class="row">
                <div class="col-12 col-md-6 col-xl-4">
                  <div class="card">
                      <img src="../../../assets/images/placeholder.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                      <h5 class="card-title">Card title</h5>
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                      <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                  </div>
                </div>
              </div>
						</div>
						<figure class="highlight" id="defaultCard">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="card">
    <img src="..." class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title">Card title</h5>
      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
      <a href="#" class="btn btn-primary">Go somewhere</a>
    </div>
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#defaultCard"><i data-feather="copy"></i></button>
            </figure>
            
            <hr>

            <h4 id="contents">Content types</h4>
						<p class="mb-3">Cards support a wide variety of content, including images, text, list groups, links, and more. Below are examples of what’s supported.</p>
            <h4 id="body">Body</h4>
            <p class="mb-3">The building block of a card is the <code>.card-body</code>. Use it whenever you need a padded section within a card.</p>
            <div class="example">
              <div class="row">
                <div class="col-12 col-md-6 col-xl-4">
                  <div class="card">
                    <div class="card-body">
                      This is some text within a card body.
                    </div>
                  </div>
                </div>
              </div>
						</div>
						<figure class="highlight" id="contentBody">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="card">
    <div class="card-body">
      This is some text within a card body.
    </div>
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#contentBody"><i data-feather="copy"></i></button>
            </figure>
            <h4 id="title-text-link">Titles, text, and links</h4>
            <p class="mb-3">Subtitles are used by adding a <code>.card-subtitle</code> to a <code>&lt;h*&gt;</code> tag. If the <code>.card-title</code> and the <code>.card-subtitle</code> items are placed in a <code>.card-body</code> item, the card title and subtitle are aligned nicely.</p>
            <div class="example">
              <div class="row">
                <div class="col-12 col-md-6 col-xl-4">
                  <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">Card title</h5>
                      <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                      <a href="#" class="card-link">Card link</a>
                      <a href="#" class="card-link">Another link</a>
                    </div>
                  </div>
                </div>
              </div>
						</div>
						<figure class="highlight" id="titleText">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="card">
    <div class="card-body">
      <h5 class="card-title">Card title</h5>
      <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
      <a href="#" class="card-link">Card link</a>
      <a href="#" class="card-link">Another link</a>
    </div>
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#titleText"><i data-feather="copy"></i></button>
            </figure>
            <h4 id="images">Images</h4>
            <p class="mb-3"><code>.card-img-top</code> places an image to the top of the card. With <code>.card-text</code>, text can be added to the card. Text within <code>.card-text</code> can also be styled with the standard HTML tags.</p>
            <div class="example">
              <div class="row">
                <div class="col-12 col-md-6 col-xl-4 grid-margin grid-margin-md-0">
                  <div class="card">
                    <img src="../../../assets/images/placeholder.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                  </div>
                </div>
                <div class="col-12 col-md-6 col-xl-4">
                  <div class="card">
                    <div class="card-body">
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                    <img src="../../../assets/images/placeholder.jpg" class="card-img-top" alt="...">
                  </div>
                </div>
              </div>
						</div>
						<figure class="highlight" id="imageCard">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="card">
    <img src="..." class="card-img-top" alt="...">
    <div class="card-body">
      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
    </div>
  </div>
  <div class="card">
    <div class="card-body">
      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
    </div>
    <img src="..." class="card-img-top" alt="...">
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#imageCard"><i data-feather="copy"></i></button>
            </figure>
            <h4 id="list-groups">List groups</h4>
            <p class="mb-3">Create lists of content in a card with a flush list group.</p>
            <div class="example">
              <div class="row">
                <div class="col-12 col-md-6 col-xl-4">
                  <div class="card">
                    <ul class="list-group list-group-flush">
                      <li class="list-group-item">Cras justo odio</li>
                      <li class="list-group-item">Dapibus ac facilisis in</li>
                      <li class="list-group-item">Vestibulum at eros</li>
                    </ul>
                  </div>
                </div>
              </div>
						</div>
						<figure class="highlight" id="listGropus">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="card">
    <ul class="list-group list-group-flush">
      <li class="list-group-item">Cras justo odio</li>
      <li class="list-group-item">Dapibus ac facilisis in</li>
      <li class="list-group-item">Vestibulum at eros</li>
    </ul>
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#listGropus"><i data-feather="copy"></i></button>
            </figure>
            <div class="example">
              <div class="row">
                <div class="col-12 col-md-6 col-xl-4">
                  <div class="card">
                    <div class="card-header">
                      Featured
                    </div>
                    <ul class="list-group list-group-flush">
                      <li class="list-group-item">Cras justo odio</li>
                      <li class="list-group-item">Dapibus ac facilisis in</li>
                      <li class="list-group-item">Vestibulum at eros</li>
                    </ul>
                  </div>
                </div>
              </div>
						</div>
						<figure class="highlight" id="listGropus2">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="card">
    <div class="card-header">
      Featured
    </div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">Cras justo odio</li>
      <li class="list-group-item">Dapibus ac facilisis in</li>
      <li class="list-group-item">Vestibulum at eros</li>
    </ul>
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#listGropus2"><i data-feather="copy"></i></button>
            </figure>
            
            <hr>

            <h4 id="header-footer">Header & Footer</h4>
						<p class="mb-3">Add an optional header and/or footer within a card.</p>
						<div class="example">
              <div class="row">
                <div class="col-12 col-md-6 col-xl-4">
                  <div class="card">
                    <div class="card-header">
                      Card header
                    </div>
                    <div class="card-body">
                      <h5 class="card-title">Special title treatment</h5>
                      <p class="card-text mb-1">With supporting text below as a natural lead-in to additional content.</p>
                      <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                  </div>
                </div>
                <div class="col-12 col-md-6 col-xl-4">
                  <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">Special title treatment</h5>
                      <p class="card-text mb-1">With supporting text below as a natural lead-in to additional content.</p>
                      <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                    <div class="card-footer">
                      Card footer
                    </div>
                  </div>
                </div>
              </div>
						</div>
						<figure class="highlight" id="headerFooter">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="card">
    <div class="card-header">
      Card header
    </div>
    <div class="card-body">
      ...
    </div>
  </div>

  <div class="card">
    <div class="card-body">
      ...
    </div>
    <div class="card-footer">
      Card footer
    </div>
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#headerFooter"><i data-feather="copy"></i></button>
            </figure>
            
            <hr>

            <h4 id="sizing">Sizing</h4>
						<p class="mb-3">Cards assume no specific <code>width</code> to start, so they’ll be 100% wide unless otherwise stated. You can change this as needed with custom CSS, grid classes, grid Sass mixins, or utilities.</p>
						<div class="example">
              <div class="row">
                <div class="col-12 col-md-4 col-xl-6">
                  <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">Special title treatment</h5>
                      <p class="card-text mb-1">With supporting text below as a natural lead-in to additional content.</p>
                      <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                  </div>
                </div>
                <div class="col-12 col-md-6 col-xl-4">
                  <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">Special title treatment</h5>
                      <p class="card-text mb-1">With supporting text below as a natural lead-in to content.</p>
                      <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                  </div>
                </div>
              </div>
						</div>
						<figure class="highlight" id="cardSizing">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="row">
    <div class="col-12 col-md-4 col-xl-6">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Special title treatment</h5>
          <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
          <a href="#" class="btn btn-primary">Go somewhere</a>
        </div>
      </div>
    </div>
    <div class="col-12 col-md-6 col-xl-4">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Special title treatment</h5>
          <p class="card-text">With supporting text below as a natural lead-in to content.</p>
          <a href="#" class="btn btn-primary">Go somewhere</a>
        </div>
      </div>
    </div>
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#cardSizing"><i data-feather="copy"></i></button>
            </figure>
            
            <hr>

            <h4 id="card-navigation">Card navigation</h4>
						<p class="mb-3">Add some navigation to a card’s header (or block) with Bootstrap’s nav components.</p>
						<div class="example">
              <div class="row">
                <div class="col-12">
                  <div class="card text-center">
                    <div class="card-header">
                      <ul class="nav nav-tabs card-header-tabs">
                        <li class="nav-item"><a class="nav-link active" href="javascript:;">Active</a></li>
                        <li class="nav-item"><a class="nav-link" href="javascript:;">Link</a></li>
                        <li class="nav-item"><a class="nav-link disabled" href="javascript:;" tabindex="-1" aria-disabled="true">Disabled</a></li>
                      </ul>
                    </div>
                    <div class="card-body">
                      <h5 class="card-title">Special title treatment</h5>
                      <p class="card-text mb-1">With supporting text below as a natural lead-in to additional content.</p>
                      <a href="javascript:;" class="btn btn-primary">Go somewhere</a>
                    </div>
                  </div>
                </div>  
              </div>
						</div>
						<figure class="highlight" id="cardNavigation">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="card text-center">
    <div class="card-header">
      <ul class="nav nav-tabs card-header-tabs">
        <li class="nav-item"><a class="nav-link active" href="#">Active</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Link</a></li>
        <li class="nav-item"><a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a></li>
      </ul>
    </div>
    <div class="card-body">
      <h5 class="card-title">Special title treatment</h5>
      <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
      <a href="#" class="btn btn-primary">Go somewhere</a>
    </div>
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#cardNavigation"><i data-feather="copy"></i></button>
            </figure>
            
            <hr>

            <h4 id="image-overlay">Image overlay</h4>
						<p class="mb-3">Turn an image into a card background and overlay your card’s text. Depending on the image, you may or may not need additional styles or utilities.</p>
						<div class="example">
              <div class="row">
                <div class="col-12 col-md-6">
                  <div class="card text-white">
                    <img src="https://via.placeholder.com/410x251" class="card-img" alt="...">
                    <div class="card-img-overlay">
                      <h5 class="card-title text-white font-weight-bold">Card title</h5>
                      <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                      <p class="card-text">Last updated 3 mins ago</p>
                    </div>
                  </div>
                </div>  
              </div>
						</div>
						<figure class="highlight" id="imgOverlay">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="card text-white">
    <img src="..." class="card-img" alt="...">
    <div class="card-img-overlay">
      <h5 class="card-title">Card title</h5>
      <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
      <p class="card-text">Last updated 3 mins ago</p>
    </div>
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#imgOverlay"><i data-feather="copy"></i></button>
            </figure>
            
            <hr>

            <h4 id="bg-color">Background Color</h4>
						<p class="mb-3">Use text and background utilities to change the appearance of a card.</p>
						<div class="example">
              <div class="row">
                <div class="col-12 col-md-6 grid-margin">
                  <div class="card text-white bg-primary">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                      <h5 class="card-title text-white">Primary card title</h5>
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                  </div>
                </div>
                <div class="col-12 col-md-6 grid-margin">
                  <div class="card text-white bg-secondary">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                      <h5 class="card-title text-white">Secondary card title</h5>
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                  </div>
                </div>
                <div class="col-12 col-md-6 grid-margin">
                  <div class="card text-white bg-success">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                      <h5 class="card-title text-white">Success card title</h5>
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                  </div>
                </div>
                <div class="col-12 col-md-6 grid-margin">
                  <div class="card text-white bg-danger">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                      <h5 class="card-title text-white">Danger card title</h5>
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                  </div>
                </div>
                <div class="col-12 col-md-6 grid-margin">
                  <div class="card text-white bg-warning">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                      <h5 class="card-title text-white">Warning card title</h5>
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                  </div>
                </div>
                <div class="col-12 col-md-6 grid-margin">
                  <div class="card text-white bg-info">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                      <h5 class="card-title text-white">Info card title</h5>
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                  </div>
                </div>
                <div class="col-12 col-md-6 grid-margin">
                  <div class="card bg-light">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                      <h5 class="card-title text-body">Light card title</h5>
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                  </div>
                </div>
                <div class="col-12 col-md-6 grid-margin">
                  <div class="card text-white bg-dark">
                    <div class="card-header">Header</div>
                    <div class="card-body">
                      <h5 class="card-title text-white">Dark card title</h5>
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                  </div>
                </div>
              </div>
						</div>
						<figure class="highlight" id="bgColor">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="card text-white bg-primary">
    <div class="card-header">Header</div>
    <div class="card-body">
      <h5 class="card-title">Primary card title</h5>
      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
    </div>
  </div>

  <div class="card text-white bg-secondary">
    ...
  </div>
  <div class="card text-white bg-success">
    ...
  </div>
  <div class="card text-white bg-danger">
    ...
  </div>
  <div class="card text-white bg-warning">
    ...
  </div>
  <div class="card text-white bg-info">
    ...
  </div>
  <div class="card text-white bg-light">
    ...
  </div>
  <div class="card text-white bg-dark">
    ...
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#bgColor"><i data-feather="copy"></i></button>
            </figure>
            
            <hr>

            <h4 id="card-group">Card groups</h4>
						<p class="mb-3">Use card groups to render cards as a single, attached element with equal width and height columns. Card groups use <code>display: flex;</code> to achieve their uniform sizing.</p>
						<div class="example">
              <div class="card-group">
                <div class="card">
                  <img src="../../../assets/images/placeholder.jpg" class="card-img-top" alt="...">
                  <div class="card-body">
                    <h5 class="card-title">Card title</h5>
                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                  </div>
                </div>
                <div class="card">
                  <img src="../../../assets/images/placeholder.jpg" class="card-img-top" alt="...">
                  <div class="card-body">
                    <h5 class="card-title">Card title</h5>
                    <p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                  </div>
                </div>
                <div class="card">
                  <img src="../../../assets/images/placeholder.jpg" class="card-img-top" alt="...">
                  <div class="card-body">
                    <h5 class="card-title">Card title</h5>
                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This card has even longer content than the first to show that equal height action.</p>
                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                  </div>
                </div>
              </div>
            </div>
						<figure class="highlight" id="cardGroup">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="card-group">
    <div class="card"> ... </div>
    <div class="card"> ... </div>
    <div class="card"> ... </div>
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#cardGroup"><i data-feather="copy"></i></button>
            </figure>
            
            <hr>

            <h4 id="decks">Card decks</h4>
						<p class="mb-3">Need a set of equal width and height cards that aren’t attached to one another? Use card decks.</p>
						<div class="example">
              <div class="card-deck">
                <div class="card">
                  <img src="../../../assets/images/placeholder.jpg" class="card-img-top" alt="...">
                  <div class="card-body">
                    <h5 class="card-title">Card title</h5>
                    <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                  </div>
                </div>
                <div class="card">
                  <img src="../../../assets/images/placeholder.jpg" class="card-img-top" alt="...">
                  <div class="card-body">
                    <h5 class="card-title">Card title</h5>
                    <p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                  </div>
                </div>
                <div class="card">
                  <img src="../../../assets/images/placeholder.jpg" class="card-img-top" alt="...">
                  <div class="card-body">
                    <h5 class="card-title">Card title</h5>
                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This card has even longer content than the first to show that equal height action.</p>
                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                  </div>
                </div>
              </div>
            </div>
						<figure class="highlight" id="cardDecks">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="card-deck">
    <div class="card"> ... </div>
    <div class="card"> ... </div>
    <div class="card"> ... </div>
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#cardDecks"><i data-feather="copy"></i></button>
            </figure>
            
            <hr>

            <h4 id="columns">Card columns</h4>
						<p>Cards can be organized into <a href="https://masonry.desandro.com/" target="_blank">Masonry</a>-like columns with just CSS by wrapping them in <code>.card-columns</code>. Cards are built with CSS <code>column</code> properties instead of flexbox for easier alignment. Cards are ordered from top to bottom and left to right.</p>
						<div class="example">
              <div class="card-columns">
                <div class="card">
                  <img src="../../../assets/images/placeholder.jpg" class="card-img-top" alt="...">
                  <div class="card-body">
                    <h5 class="card-title">Card title that wraps to a new line</h5>
                    <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                  </div>
                </div>
                <div class="card p-3">
                  <blockquote class="blockquote mb-0 card-body">
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
                    <footer class="blockquote-footer">
                      <small class="text-muted">
                        Someone famous in <cite title="Source Title">Source Title</cite>
                      </small>
                    </footer>
                  </blockquote>
                </div>
                <div class="card">
                  <img src="../../../assets/images/placeholder.jpg" class="card-img-top" alt="...">
                  <div class="card-body">
                    <h5 class="card-title">Card title</h5>
                    <p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                  </div>
                </div>
                <div class="card bg-primary text-white text-center p-3">
                  <blockquote class="blockquote mb-0">
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat.</p>
                    <footer class="blockquote-footer text-white">
                      <small>
                        Someone famous in <cite title="Source Title">Source Title</cite>
                      </small>
                    </footer>
                  </blockquote>
                </div>
                <div class="card text-center">
                  <div class="card-body">
                    <h5 class="card-title">Card title</h5>
                    <p class="card-text">This card has a regular title and short paragraphy of text below it.</p>
                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                  </div>
                </div>
                <div class="card">
                  <img src="../../../assets/images/placeholder.jpg" class="card-img-top" alt="...">
                </div>
                <div class="card p-3 text-right">
                  <blockquote class="blockquote mb-0">
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
                    <footer class="blockquote-footer">
                      <small class="text-muted">
                        Someone famous in <cite title="Source Title">Source Title</cite>
                      </small>
                    </footer>
                  </blockquote>
                </div>
                <div class="card">
                  <div class="card-body">
                    <h5 class="card-title">Card title</h5>
                    <p class="card-text">This is another card with title and supporting text below. This card has some additional content to make it slightly taller overall.</p>
                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                  </div>
                </div>
              </div>
            </div>
						<figure class="highlight" id="cardColumns">
<pre><code class="language-markup"><script type="script/prism-html-markup"><div class="card-columns">
    <div class="card">
      <img src="..." class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">Card title that wraps to a new line</h5>
        <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
      </div>
    </div>
    <div class="card p-3">
      <blockquote class="blockquote mb-0 card-body">
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
        <footer class="blockquote-footer">
          <small class="text-muted">
            Someone famous in <cite title="Source Title">Source Title</cite>
          </small>
        </footer>
      </blockquote>
    </div>
    <div class="card">
      <img src="..." class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
      </div>
    </div>
    <div class="card bg-primary text-white text-center p-3">
      <blockquote class="blockquote mb-0">
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat.</p>
        <footer class="blockquote-footer text-white">
          <small>
            Someone famous in <cite title="Source Title">Source Title</cite>
          </small>
        </footer>
      </blockquote>
    </div>
    <div class="card text-center">
      <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <p class="card-text">This card has a regular title and short paragraphy of text below it.</p>
        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
      </div>
    </div>
    <div class="card">
      <img src="..." class="card-img-top" alt="...">
    </div>
    <div class="card p-3 text-right">
      <blockquote class="blockquote mb-0">
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
        <footer class="blockquote-footer">
          <small class="text-muted">
            Someone famous in <cite title="Source Title">Source Title</cite>
          </small>
        </footer>
      </blockquote>
    </div>
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <p class="card-text">This is another card with title and supporting text below. This card has some additional content to make it slightly taller overall.</p>
        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
      </div>
    </div>
  </div></script></code></pre>
							<button type="button" class="btn btn-clipboard" data-clipboard-target="#cardColumns"><i data-feather="copy"></i></button>
            </figure>
            
					</div>
					<div class="col-xl-2 content-nav-wrapper">
						<ul class="nav content-nav d-flex flex-column">
							<li class="nav-item">
								<a href="#default" class="nav-link">Basic Example</a>
              </li>
              <li class="nav-item">
								<a href="#contents" class="nav-link">Content types</a>
              </li>
              <li class="nav-item">
								<a href="#header-footer" class="nav-link">Header and footer</a>
              </li>
              <li class="nav-item">
								<a href="#sizing" class="nav-link">Sizing</a>
              </li>
              <li class="nav-item">
								<a href="#card-navigation" class="nav-link">Card navigation</a>
              </li>
              <li class="nav-item">
								<a href="#image-overlay" class="nav-link">Image overlay</a>
              </li>
              <li class="nav-item">
								<a href="#bg-color" class="nav-link">Background color</a>
              </li>
              <li class="nav-item">
								<a href="#card-group" class="nav-link">Card groups</a>
              </li>
              <li class="nav-item">
								<a href="#decks" class="nav-link">Card decks</a>
              </li>
              <li class="nav-item">
								<a href="#columns" class="nav-link">Card columns</a>
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
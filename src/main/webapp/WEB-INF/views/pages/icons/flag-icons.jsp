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
  <link rel="stylesheet" href="../../../assets/vendors/flag-icon-css/css/flag-icon.min.css">
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
						<li class="breadcrumb-item active" aria-current="page">Flag Icons</li>
					</ol>
				</nav>

				<div class="row">
					<div class="col-md-12 stretch-card">
            <div class="card">
              <div class="card-body">
                <h6 class="card-title">Flag Icons</h6>
                <p class="card-description">Visit the <a href="http://flag-icon-css.lip.is/" target="_blank"> Official Flag Icons Documentation </a>.</p>                
                <p class="card-description">Usage example : &lt;i class="flag-icon flag-icon-aw"&gt;&lt;/i&gt;</p>
                <div class="container">
                  <div class="icons-list row">
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ad" title="ad" id="ad"></i> AD</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ae" title="ae" id="ae"></i> AE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-af" title="af" id="af"></i> AF</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ag" title="ag" id="ag"></i> AG</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ai" title="ai" id="ai"></i> AU</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-al" title="al" id="al"></i> AL</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-am" title="am" id="am"></i> AM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ao" title="ao" id="ao"></i> AO</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-aq" title="aq" id="aq"></i> AQ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ar" title="ar" id="ar"></i> AR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-as" title="as" id="as"></i> AS</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-at" title="at" id="at"></i> AT</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-au" title="au" id="au"></i> AU</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-aw" title="aw" id="aw"></i> AW</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ax" title="ax" id="ax"></i> AX</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-az" title="az" id="az"></i> AZ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ba" title="ba" id="ba"></i> BA</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bb" title="bb" id="bb"></i> BB</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bd" title="bd" id="bd"></i> BD</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-be" title="be" id="be"></i> BE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bf" title="bf" id="bf"></i> BF</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bg" title="bg" id="bg"></i> BG</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bh" title="bh" id="bh"></i> BH</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bi" title="bi" id="bi"></i> BI</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bj" title="bj" id="bj"></i> BJ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bl" title="bl" id="bl"></i> BL</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bm" title="bm" id="bm"></i> BM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bn" title="bn" id="bn"></i> BN</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bo" title="bo" id="bo"></i> BO</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bq" title="bq" id="bq"></i> BQ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-br" title="br" id="br"></i> BR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bs" title="bs" id="bs"></i> BS</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bt" title="bt" id="bt"></i> BT</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bv" title="bv" id="bv"></i> BV</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bw" title="bw" id="bw"></i> BW</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-by" title="by" id="by"></i> BY</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-bz" title="bz" id="bz"></i> BZ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ca" title="ca" id="ca"></i> CA</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cc" title="cc" id="cc"></i> CC</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cd" title="cd" id="cd"></i> CD</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cf" title="cf" id="cf"></i> CF</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cg" title="cg" id="cg"></i> CG</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ch" title="ch" id="ch"></i> CH</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ci" title="ci" id="ci"></i> CI</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ck" title="ck" id="ck"></i> CK</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cl" title="cl" id="cl"></i> CL</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cm" title="cm" id="cm"></i> CM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cn" title="cn" id="cn"></i> CN</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-co" title="co" id="co"></i> CO</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cr" title="cr" id="cr"></i> CR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cu" title="cu" id="cu"></i> CU</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cv" title="cv" id="cv"></i> CV</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cw" title="cw" id="cw"></i> CW</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cx" title="cx" id="cx"></i> CX</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cy" title="cy" id="cy"></i> CY</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-cz" title="cz" id="cz"></i> CZ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-de" title="de" id="de"></i> DE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-dj" title="dj" id="dj"></i> DJ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-dk" title="dk" id="dk"></i> DK</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-dm" title="dm" id="dm"></i> DM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-do" title="do" id="do"></i> DO</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-dz" title="dz" id="dz"></i> DZ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ec" title="ec" id="ec"></i> EC</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ee" title="ee" id="ee"></i> EE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-eg" title="eg" id="eg"></i> EG</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-eh" title="eh" id="eh"></i> EH</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-er" title="er" id="er"></i> ER</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-es" title="es" id="es"></i> ES</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-et" title="et" id="et"></i> ET</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-fi" title="fi" id="fi"></i> FI</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-fj" title="fj" id="fj"></i> FJ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-fk" title="fk" id="fk"></i> FK</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-fm" title="fm" id="fm"></i> FM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-fo" title="fo" id="fo"></i> FO</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-fr" title="fr" id="fr"></i> FR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ga" title="ga" id="ga"></i> GA</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gb" title="gb" id="gb"></i> GB</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gd" title="gd" id="gd"></i> GD</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ge" title="ge" id="ge"></i> GE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gf" title="gf" id="gf"></i> GF</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gg" title="gg" id="gg"></i> GG</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gh" title="gh" id="gh"></i> GH</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gi" title="gi" id="gi"></i> GI</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gl" title="gl" id="gl"></i> GL</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gm" title="gm" id="gm"></i> GM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gn" title="gn" id="gn"></i> GN</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gp" title="gp" id="gp"></i> GP</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gq" title="gq" id="gq"></i> GQ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gr" title="gr" id="gr"></i> GR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gs" title="gs" id="gs"></i> GS</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gt" title="gt" id="gt"></i> GT</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gu" title="gu" id="gu"></i> GU</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gw" title="gw" id="gw"></i> GW</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-gy" title="gy" id="gy"></i> GY</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-hk" title="hk" id="hk"></i> HK</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-hm" title="hm" id="hm"></i> HM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-hn" title="hn" id="hn"></i> HN</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-hr" title="hr" id="hr"></i> HR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ht" title="ht" id="ht"></i> HT</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-hu" title="hu" id="hu"></i> HU</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-id" title="id" id="id"></i> ID</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ie" title="ie" id="ie"></i> IE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-il" title="il" id="il"></i> IL</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-im" title="im" id="im"></i> IM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-in" title="in" id="in"></i> IN</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-io" title="io" id="io"></i> IO</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-iq" title="iq" id="iq"></i> IQ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ir" title="ir" id="ir"></i> IR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-is" title="is" id="is"></i> IS</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-it" title="it" id="it"></i> IT</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-je" title="je" id="je"></i> JE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-jm" title="jm" id="jm"></i> JM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-jo" title="jo" id="jo"></i> JO</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-jp" title="jp" id="jp"></i> JP</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ke" title="ke" id="ke"></i> KE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-kg" title="kg" id="kg"></i> KG</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-kh" title="kh" id="kh"></i> KH</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ki" title="ki" id="ki"></i> KI</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-km" title="km" id="km"></i> KM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-kn" title="kn" id="kn"></i> KN</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-kp" title="kp" id="kp"></i> KP</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-kr" title="kr" id="kr"></i> KR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-kw" title="kw" id="kw"></i> KW</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ky" title="ky" id="ky"></i> KY</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-kz" title="kz" id="kz"></i> KZ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-la" title="la" id="la"></i> LA</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-lb" title="lb" id="lb"></i> LB</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-lc" title="lc" id="lc"></i> LC</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-li" title="li" id="li"></i> LI</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-lk" title="lk" id="lk"></i> LK</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-lr" title="lr" id="lr"></i> LR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ls" title="ls" id="ls"></i> LS</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-lt" title="lt" id="lt"></i> LT</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-lu" title="lu" id="lu"></i> LU</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-lv" title="lv" id="lv"></i> LV</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ly" title="ly" id="ly"></i> LY</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ma" title="ma" id="ma"></i> MA</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mc" title="mc" id="mc"></i> MC</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-md" title="md" id="md"></i> MD</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-me" title="me" id="me"></i> ME</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mf" title="mf" id="mf"></i> MF</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mg" title="mg" id="mg"></i> MG</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mh" title="mh" id="mh"></i> MH</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mk" title="mk" id="mk"></i> MK</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ml" title="ml" id="ml"></i> ML</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mm" title="mm" id="mm"></i> MM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mn" title="mn" id="mn"></i> MN</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mo" title="mo" id="mo"></i> MO</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mp" title="mp" id="mp"></i> MP</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mq" title="mq" id="mq"></i> MQ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mr" title="mr" id="mr"></i> MR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ms" title="ms" id="ms"></i> MS</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mt" title="mt" id="mt"></i> MT</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mu" title="mu" id="mu"></i> MU</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mv" title="mv" id="mv"></i> MV</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mw" title="mw" id="mw"></i> MW</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mx" title="mx" id="mx"></i> MX</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-my" title="my" id="my"></i> MY</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-mz" title="mz" id="mz"></i> MZ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-na" title="na" id="na"></i> NA</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-nc" title="nc" id="nc"></i> NC</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ne" title="ne" id="ne"></i> NE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-nf" title="nf" id="nf"></i> NF</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ng" title="ng" id="ng"></i> NG</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ni" title="ni" id="ni"></i> NI</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-nl" title="nl" id="nl"></i> NL</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-no" title="no" id="no"></i> NO</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-np" title="np" id="np"></i> NP</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-nr" title="nr" id="nr"></i> NR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-nu" title="nu" id="nu"></i> NU</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-nz" title="nz" id="nz"></i> NZ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-om" title="om" id="om"></i> OM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-pa" title="pa" id="pa"></i> PA</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-pe" title="pe" id="pe"></i> PE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-pf" title="pf" id="pf"></i> PF</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-pg" title="pg" id="pg"></i> PG</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ph" title="ph" id="ph"></i> PH</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-pk" title="pk" id="pk"></i> PK</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-pl" title="pl" id="pl"></i> PL</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-pm" title="pm" id="pm"></i> PM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-pn" title="pn" id="pn"></i> PN</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-pr" title="pr" id="pr"></i> PR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ps" title="ps" id="ps"></i> PS</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-pt" title="pt" id="pt"></i> PT</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-pw" title="pw" id="pw"></i> PW</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-py" title="py" id="py"></i> PY</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-qa" title="qa" id="qa"></i> QA</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-re" title="re" id="re"></i> RE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ro" title="ro" id="ro"></i> RO</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-rs" title="rs" id="rs"></i> RS</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ru" title="ru" id="ru"></i> RU</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-rw" title="rw" id="rw"></i> RW</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sa" title="sa" id="sa"></i> SA</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sb" title="sb" id="sb"></i> SB</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sc" title="sc" id="sc"></i> SC</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sd" title="sd" id="sd"></i> SD</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-se" title="se" id="se"></i> SE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sg" title="sg" id="sg"></i> SG</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sh" title="sh" id="sh"></i> SH</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-si" title="si" id="si"></i> SI</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sj" title="sj" id="sj"></i> SJ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sk" title="sk" id="sk"></i> SK</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sl" title="sl" id="sl"></i> SL</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sm" title="sm" id="sm"></i> SM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sn" title="sn" id="sn"></i> SN</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-so" title="so" id="so"></i> SO</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sr" title="sr" id="sr"></i> SR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ss" title="ss" id="ss"></i> SS</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-st" title="st" id="st"></i> ST</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sv" title="sv" id="sv"></i> SV</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sx" title="sx" id="sx"></i> SX</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sy" title="sy" id="sy"></i> SY</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-sz" title="sz" id="sz"></i> SZ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-tc" title="tc" id="tc"></i> TC</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-td" title="td" id="td"></i> TD</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-tf" title="tf" id="tf"></i> TF</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-tg" title="tg" id="tg"></i> TG</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-th" title="th" id="th"></i> TH</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-tj" title="tj" id="tj"></i> TJ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-tk" title="tk" id="tk"></i> TK</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-tl" title="tl" id="tl"></i> TL</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-tm" title="tm" id="tm"></i> TM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-tn" title="tn" id="tn"></i> TN</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-to" title="to" id="to"></i> TO</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-tr" title="tr" id="tr"></i> TR</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-tt" title="tt" id="tt"></i> TT</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-tv" title="tv" id="tv"></i> TV</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-tw" title="tw" id="tw"></i> TW</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-tz" title="tz" id="tz"></i> TZ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ua" title="ua" id="ua"></i> UA</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ug" title="ug" id="ug"></i> UG</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-um" title="um" id="um"></i> UM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-us" title="us" id="us"></i> US</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-uy" title="uy" id="uy"></i> UY</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-uz" title="uz" id="uz"></i> UZ</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-va" title="va" id="va"></i> VA</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-vc" title="vc" id="vc"></i> VC</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ve" title="ve" id="ve"></i> VE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-vg" title="vg" id="vg"></i> VG</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-vi" title="vi" id="vi"></i> VI</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-vn" title="vn" id="vn"></i> VN</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-vu" title="vu" id="vu"></i> VU</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-wf" title="wf" id="wf"></i> WF</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ws" title="ws" id="ws"></i> WS</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-ye" title="ye" id="ye"></i> YE</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-yt" title="yt" id="yt"></i> YT</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-za" title="za" id="za"></i> ZA</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-zm" title="zm" id="zm"></i> ZM</div>
                    <div class="col-6 col-md-3"><i class="flag-icon flag-icon-zw" title="zw" id="zw"></i> ZW</div>
                  </div>
                </div>
              </div>
            </div>
					</div>
				</div>

			</div>

			<!-- partial:../../partials/_footer.jsp -->
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<nav class="sidebar">
  <div class="sidebar-header">
    <a href="#" class="sidebar-brand">
      <span>UCC</span> 관리자
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
        <a href="/admin/main.do" class="nav-link">
          <i class="link-icon" data-feather="box"></i>
          <span class="link-title">HOME</span>
        </a>
      </li>
      <li class="nav-item nav-category">회원 관리</li>
      <li class="nav-item">
        <a href="/admin/users.do" class="nav-link">
          <i class="link-icon" data-feather="user"></i>
          <span class="link-title">사용자</span>
        </a>
      </li>
      <li class="nav-item">
        <a href="/admin/artists.do" class="nav-link">
          <i class="link-icon" data-feather="user-check"></i>
          <span class="link-title">아티스트</span>
        </a>
      </li>
      <li class="nav-item nav-category">크라우드 시스템</li>
      <li class="nav-item">
        <a class="nav-link" data-toggle="collapse" href="#uiComponents" role="button" aria-expanded="false"
           aria-controls="uiComponents">
          <i class="link-icon" data-feather="feather"></i>
          <span class="link-title">크라우드</span>
          <i class="link-arrow" data-feather="chevron-down"></i>
        </a>
        <div class="collapse" id="uiComponents">
          <ul class="nav sub-menu">
            <li class="nav-item">
              <a href="/admin/loudsourcing_recruitment.do" class="nav-link">모집</a>
            </li>
            <li class="nav-item">
              <a href="/admin/loudsourcing_process.do" class="nav-link">진행</a>
            </li>
            <li class="nav-item">
              <a href="/admin/loudsourcing_judge.do" class="nav-link">심사</a>
            </li>
            <li class="nav-item">
              <a href="/admin/loudsourcing_end.do" class="nav-link">종료</a>
            </li>
          </ul>
        </div>
      </li>
      <li class="nav-item nav-category">고객 센터</li>
      <li class="nav-item">
        <a class="nav-link" data-toggle="collapse" href="#general-pages" role="button" aria-expanded="false"
           aria-controls="general-pages">
          <i class="link-icon" data-feather="help-circle"></i>
          <span class="link-title">FAQ</span>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" data-toggle="collapse" href="#authPages" role="button" aria-expanded="false"
           aria-controls="authPages">
          <i class="link-icon" data-feather="bell"></i>
          <span class="link-title">공지 사항</span>
        </a>
      </li>
      <li class="nav-item nav-category">문의</li>
      <li class="nav-item">
        <a href="https://www.nobleui.com/html/documentation/docs.html" target="_blank" class="nav-link">
          <i class="link-icon" data-feather="cloud"></i>
          <span class="link-title">크라우드 문의</span>
        </a>
      </li>
      <li class="nav-item">
        <a href="https://www.nobleui.com/html/documentation/docs.html" target="_blank" class="nav-link">
          <i class="link-icon" data-feather="flag"></i>
          <span class="link-title">신고 문의</span>
        </a>
      </li>
      <li class="nav-item">
        <a href="https://www.nobleui.com/html/documentation/docs.html" target="_blank" class="nav-link">
          <i class="link-icon" data-feather="file"></i>
          <span class="link-title">기타 문의</span>
        </a>
      </li>
      <li class="nav-item nav-category">검색어</li>
      <li class="nav-item">
        <a href="https://www.nobleui.com/html/documentation/docs.html" target="_blank" class="nav-link">
          <i class="link-icon" data-feather="hash"></i>
          <span class="link-title">검색 해시태그</span>
        </a>
      </li>
      <li class="nav-item nav-category">후원</li>
      <li class="nav-item">
        <a class="nav-link" data-toggle="collapse" href="#spontypes" role="button" aria-expanded="false"
           aria-controls="spontypes">
          <i class="link-icon" data-feather="dollar-sign"></i>
          <span class="link-title">후원 관리</span>
          <i class="link-arrow" data-feather="chevron-down"></i>
        </a>
        <div class="collapse" id="spontypes">
          <ul class="nav sub-menu">
            <li class="nav-item">
              <a href="pages/ui-components/alerts.html" class="nav-link">아티스트 후원</a>
            </li>
            <li class="nav-item">
              <a href="pages/ui-components/badges.html" class="nav-link">게시글 후원</a>
            </li>
          </ul>
        </div>
      </li>
      <li class="nav-item nav-category">배너 광고</li>
      <li class="nav-item">
        <a href="https://www.nobleui.com/html/documentation/docs.html" target="_blank" class="nav-link">
          <i class="link-icon" data-feather="film"></i>
          <span class="link-title">배너 관리</span>
        </a>
      </li>
    </ul>
  </div>
</nav>
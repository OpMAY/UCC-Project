<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<nav class="sidebar">
  <div class="sidebar-header">
    <a href="/admin/main.do" class="sidebar-brand">
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
              <a href="/admin/loudsourcing/recruitment.do" class="nav-link">모집</a>
            </li>
            <li class="nav-item">
              <a href="/admin/loudsourcing/process.do" class="nav-link">진행</a>
            </li>
            <li class="nav-item">
              <a href="/admin/loudsourcing/judge.do" class="nav-link">심사</a>
            </li>
            <li class="nav-item">
              <a href="/admin/loudsourcing/end.do" class="nav-link">종료</a>
            </li>
          </ul>
        </div>
      </li>
      <li class="nav-item nav-category">고객 센터</li>
      <li class="nav-item">
        <a class="nav-link" href="/admin/notices.do">
          <i class="link-icon" data-feather="help-circle"></i>
          <span class="link-title">공지 사항</span>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/admin/faqs.do">
          <i class="link-icon" data-feather="bell"></i>
          <span class="link-title">FAQ</span>
        </a>
      </li>
      <li class="nav-item nav-category">문의</li>
      <li class="nav-item">
        <a href="/admin/inquiry/loudsourcing.do" class="nav-link">
          <i class="link-icon" data-feather="cloud"></i>
          <span class="link-title">크라우드 문의</span>
        </a>
      </li>
      <li class="nav-item">
        <a href="/admin/inquiry/report.do" class="nav-link">
          <i class="link-icon" data-feather="flag"></i>
          <span class="link-title">신고 문의</span>
        </a>
      </li>
      <li class="nav-item">
        <a href="/admin/inquiry/normal.do" class="nav-link">
          <i class="link-icon" data-feather="file"></i>
          <span class="link-title">일반 문의</span>
        </a>
      </li>
      <li class="nav-item nav-category">검색어</li>
      <li class="nav-item">
        <a href="/admin/hashtag.do" class="nav-link">
          <i class="link-icon" data-feather="hash"></i>
          <span class="link-title">검색 해시태그</span>
        </a>
      </li>
      <li class="nav-item nav-category">후원</li>
      <li class="nav-item">
        <a class="nav-link" data-toggle="collapse" href="#sponComponents" role="button" aria-expanded="false"
           aria-controls="uiComponents">
          <i class="link-icon" data-feather="dollar-sign"></i>
          <span class="link-title">후원 관리</span>
          <i class="link-arrow" data-feather="chevron-down"></i>
        </a>
        <div class="collapse" id="sponComponents">
          <ul class="nav sub-menu">
            <li class="nav-item">
              <a href="/admin/spon/purchase.do" class="nav-link">결제 실패 내역</a>
            </li>
            <li class="nav-item">
              <a href="/admin/spon/apply.do" class="nav-link">미승인 내역</a>
            </li>
            <li class="nav-item">
              <a href="/admin/spon/send.do" class="nav-link">미정산 내역</a>
            </li>
            <li class="nav-item">
              <a href="/admin/spon/complete.do" class="nav-link">정산 완료 내역</a>
            </li>
          </ul>
        </div>
      </li>
      <li class="nav-item nav-category">배너 광고</li>
      <li class="nav-item">
        <a href="/admin/banners.do" class="nav-link">
          <i class="link-icon" data-feather="film"></i>
          <span class="link-title">배너 관리</span>
        </a>
      </li>
      <li class="nav-item nav-category">커스텀 알림</li>
      <li class="nav-item">
        <a style="cursor: pointer" onclick="openWindowPopPush('/admin/messages.do', 'Push 알림 전송하기')" class="nav-link">
          <i class="link-icon" data-feather="edit"></i>
          <span class="link-title">PUSH 알림 전송</span>
        </a>
      </li>
      <script>
        function openWindowPopPush(url, name){
          let options = 'top=10, left=10, width=720, height=680, status=1, scrollbars=1, resizable=1, menubar=0, fullscreen=0, location=0';
          window.open(url, name, options);
        }
      </script>
    </ul>
  </div>
</nav>
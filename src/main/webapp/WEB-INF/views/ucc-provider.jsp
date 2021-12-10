<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!Doctype html>
<html lang="en">
<head>

    <script src="/js/amplitude_setting.js"></script>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="/assets/css/ucc-provider.css">
    <title>장르 불문 아티스트를 위한 놀이터 UCC</title>
    <link rel="icon" href="/assets/images/ucc/ucc_favicon.png">
    <link rel="apple-touch-icon" href="/assets/images/ucc/ucc_favicon.png"/>
    <link rel="stylesheet" href="/assets/css/owl.carousel.css">
</head>
<body>
<header style="height: 66px; position:fixed; top:0; width: 100%; z-index: 100; overflow-x: hidden;">
    <div class="d-flex justify-content-between h-100 align-items-center" style="padding: 0 1.875rem;">
        <img src="/assets/images/ucc/top-left-logo-white.png" class="logo-fixed" height="50%"
             style="transform: translate(0, 5%)"/>
        <div class="h-100 d-flex align-items-center">
            <button id="header-start" class="fixed-ctr-button" value="header-start">
                <span class="font-family-aggro-b font-size-14 font-size-sm-8">UCC 시작하기</span>
            </button>
        </div>
    </div>
</header>
<main>
    <section id="section1" style="position: relative; width: 100%;"
             class="d-flex justify-content-center align-items-end pb-150-50">
        <video playsinline loop muted autoplay preload="auto"
               style="position: absolute; width: 100%; top:0; left:0; object-fit: cover; z-index: -1;">
            <source src="/assets/video/ucc_user_main_video_small.m4v" type="video/mp4">
            Sorry, your browser doesn't support embedded videos.
        </video>
        <div class="d-flex justify-content-center text-center">
            <%--            <span class="font-white font-family-aggro-b font-size-56 font-size-sm-24">아티스트가 수익 걱정 없이<br>활동할 수 있는 무대 <img src="/assets/images/ucc/ucc-main-logo-linear.svg" class="section1-logo"></span>--%>
            <span id="artist-funnel1" class="font-white font-family-aggro-b font-size-56 font-size-sm-24">아티스트가 수익 걱정 없이<br>활동할 수 있는 무대 <span
                    class="font-family-aggro-b"
                    style="background: linear-gradient(to bottom, #91f4f2 20%, #719EFC 55%, #8e54e6 80%); -webkit-background-clip: text; -webkit-text-fill-color: transparent;">UCC</span></span>
        </div>
        <a href="#section2" style="position: absolute; bottom: 0; left: 50%;" class="mb-23-10">
            <img src="/assets/images/ucc/icon_arrow_bottom.svg" style="transform: translate(-50%, 0)">
        </a>
    </section>

    <section id="section2"
             class="d-flex flex-column justify-content-center align-items-center text-center pt-150-50 pb-200-100"
             style="background: url('/assets/images/ucc/section2-bg.png'); background-size: cover;">
        <div>
            <span id="artist-funnel2" class="font-size-48 font-size-sm-24 font-family-aggro-b font-white">장르 제한 NO!<br>‘누구에게나’ 열려있는 기회!</span>
        </div>
        <div class="mb-44-27 line-height-1_5-1">
            <span class="font-size-24 font-size-sm-12 font-grey">뮤지션부터 벌룬 아트 디자이너까지<br>UCC 무대는 누구에게나 수익 창출 기회가 열려 있습니다.</span>
        </div>
        <button id="header-active" class="ctr-button" value="header-active">
            <span class="font-family-aggro-b font-size-16 font-size-sm-8">UCC에서 활동하기</span>
        </button>
        <div class="mt-10-27 d-flex align-items-end justify-content-center img-container"
             style="transform: translate(4%, 0);">
            <img id="artist-funnel3" class="img1" src="/assets/images/ucc/section2-img1.png"
                 style="position: relative; z-index: 1;">
            <img class="img2" src="/assets/images/ucc/section2-img2.png" style="position: relative; z-index: 3;">
            <img class="img3" src="/assets/images/ucc/section2-img3.png" style="position: relative; z-index: 1;">
        </div>
    </section>

    <section id="section3" class="d-flex flex-column" style="position:relative; overflow: hidden;">
        <div style="z-index: 2;">
            <span id="artist-funnel4"
                  class="font-family-aggro-b font-size-64 font-size-md-48 font-size-sm-20 font-white"><span
                    class="font-family-aggro-b"
                    style="background: linear-gradient(to bottom, #836EFF, rgba(129,107,255,0.49)); -webkit-background-clip: text; -webkit-text-fill-color: transparent;">장르 불문</span> 아티스트를 위한<br>놀이터 UCC</span>
        </div>
        <div class="line-height-1_5-1 mb-30-20" style="z-index: 2;">
            <span class="font-grey font-size-24 font-size-sm-12">아티스트와 세상을 연결하고 자유로운 방식의 소통을 통해<br>창작 활동을 즐기는 놀이터 같은 공간을 마련했습니다.</span>
        </div>
        <div>
            <button id="medium-start" class="ctr-button" value="medium-start">
                <span class="font-family-aggro-b font-size-16 font-size-sm-8">UCC 시작하기</span>
            </button>
        </div>
        <img src="/assets/images/ucc/section3-screen1.png" class="img1 d-none d-md-inline-block" alt=""
             style="position: absolute; right: 11%; bottom: 6%; width: 39%; z-index: 0;">
        <img src="/assets/images/ucc/section3-screen2.png" class="img2 d-none d-md-inline-block" alt=""
             style="position: absolute; right: -25%; bottom: -8%; width: 43%;">
        <img src="/assets/images/ucc/section3-screen3.png" class="img3 d-none d-md-inline-block" alt=""
             style="position: absolute; right: -17%; bottom: 65%; width: 30%;">

        <img src="/assets/images/ucc/section3-screen1-sm.png" class="img4 d-md-none d-inline-block" alt=""
             style="position: absolute; right: 20%; bottom: 6%; width: 73%">
        <img src="/assets/images/ucc/section3-screen2-sm.png" class="img5 d-md-none d-inline-block" alt=""
             style="position: absolute; right: -28%; bottom: 2%; width: 65%;">
        <img src="/assets/images/ucc/section3-screen3-sm.png" class="img6 d-md-none d-inline-block" alt=""
             style="position: absolute; right: -37%; bottom: 32%; width: 80%;">
    </section>

    <section id="section4" class="bg-0d py-150-50" style="overflow:hidden;">
        <!-- web -->
        <div class="d-flex flex-column text-center">
            <div class="font-size-40 font-size-sm-20 font-family-aggro-b font-white">
                <span id="artist-funnel5">나의 예술을 표현하는 데<br>제한이 없어요!</span></div>
            <div class="font-size-24 font-size-sm-12 font-grey line-height-1_5-1"><span>비디오는 물론, 이미지, 에디트 등<br>다양한 방법으로 창작 활동이 가능합니다.</span>
            </div>
            <div class="row mx-0 d-none d-md-flex" style="margin-top: 100px;">
                <div class="col-6 d-flex flex-column justify-content-center align-items-end cont1">
                    <div class="fade-in-img img1"><img src="/assets/images/ucc/provider-scroll-slide-1.png" alt="">
                    </div>
                    <div class="fade-in-img img3"><img src="/assets/images/ucc/provider-scroll-slide-3.png" alt="">
                    </div>
                </div>
                <div class="col-6 d-flex justify-content-start align-items-center cont2">
                    <div id="artist-funnel6" class="fade-in-img img2"><img
                            src="/assets/images/ucc/provider-scroll-slide-2.png" alt="">
                    </div>
                </div>
            </div>
            <style>
                .owl-dot {
                    background: white !important;
                    width: 10px;
                    height: 10px;
                    border-radius: 50%;
                    margin: 6.25% 5px 0;
                }

                .owl-dot.active {
                    background: #816BFF !important;
                }

                .owl-carousel .item {
                    position: relative;
                    width: 40%;
                    margin: auto;
                }

                .owl-stage-outer {
                    overflow: unset !important;
                }
            </style>
            <div class="d-md-none d-block" style="margin-top: 10%;">
                <div class="owl-carousel owl-theme">
                    <div class="item img1">
                        <img src="/assets/images/ucc/provider-scroll-slide-1.png" alt="" style="display: block">
                    </div>
                    <div class="item img2">
                        <img src="/assets/images/ucc/provider-scroll-slide-2.png" alt="" style="display: block">
                    </div>
                    <div class="item img3">
                        <img src="/assets/images/ucc/provider-scroll-slide-3.png" alt="" style="display: block">
                    </div>
                </div>
            </div>
        </div>
        <!-- mobile -->
    </section>

    <section id="section5" class="d-flex flex-column align-items-center text-center py-150-50"
             style="overflow-x: hidden;">
        <div>
            <span id="artist-funnel7" class="font-size-40 font-size-sm-20 font-family-aggro-b">이미 다양한 아티스트가<br>자유롭게 활동하고 있습니다 </span>
        </div>
        <div class="line-height-1_5-1">
            <span class="font-size-24 font-size-sm-12 font-grey">UCC 아티스트들은 팬들과 진심으로 소통하고<br>다양한 방법으로 수익을 창출하고 있습니다.</span>
        </div>
        <div class="fade-in-img horizontal-loop mb-100-50 mt-70-35">
        </div>
        <button id="bottom-active" class="ctr-button" value="bottom-active">
            <span id="artist-funnel8" class="font-family-aggro-b font-size-16 font-size-sm-8">UCC에서 활동하기</span>
        </button>
    </section>

    <%-- section6 --%>
    <div id="wrapper" class="wrapper" style="position: relative;">
        <div style="position: sticky; top: 0; width: 100%; height: 100vh;">
            <div style="position: relative; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.85);">
                <video playsinline loop muted autoplay preload="auto"
                       style="position: absolute; width: 100%; height: 100%; top:0; left:0; object-fit: cover; z-index: -1;">
                    <source src="/assets/video/diamonds2.mp4" type="video/mp4">
                    Sorry, your browser doesn't support embedded videos.
                </video>
                <div style="position: relative; height: 100%;">
                    <img id="artist-funnel9" class="img-fluid img-1 d-none d-md-inline-block"
                         src="/assets/images/ucc/section6.png" alt=""
                         style="height: 100vh;">
                    <img class="img-fluid img-1 d-md-none d-inline-block" src="/assets/images/ucc/section6-sm.png"
                         alt="" style="width: 100%; height: 100%; align-self: center;">
                    <img id="artist-funnel10" class="img-fluid img-2 d-none d-md-inline-block"
                         src="/assets/images/ucc/section7.png" alt=""
                         style="height: 100vh;">
                    <img class="img-fluid img-2 d-md-none d-inline-block" src="/assets/images/ucc/section7-sm.png"
                         alt="" style="width: 100%; height: 100%; align-self: center;">
                </div>
            </div>
        </div>
        <div id="overlay" style="height: 2000px; width: 100%;"></div>
    </div>

    <section id="section8"
             class="d-flex flex-column flex-md-row justify-content-between align-items-center pt-150-50 pb-150-30 px-225-70">
        <div id="artist-funnel11" class="d-flex flex-column text-center text-md-left mb-0-30" style="flex:4;">
            <div class="mb-30-20">
                <span class="font-family-aggro-b font-size-40 font-size-sm-20">UCC와 팬들이<br>당신의 창작 활동을 기다립니다</span>
            </div>
            <div>
                <button id="bottom-start" class="ctr-button" value="bottom-start">
                    <span class="font-family-aggro-b font-size-16 font-size-sm-8">UCC 시작하기</span>
                </button>
            </div>
        </div>

        <div class="img-container" style="flex: 6;">
            <img src="/assets/images/ucc/section8-img.png" alt="" width="100%">
        </div>
    </section>
</main>

<jsp:include page="partials/ucc-footer.jsp"/>

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>
<script src="/assets/js/owl.carousel.js"></script>
<script src="/assets/js/mousewheel.js"></script>


<script>

    /*전체 페이지 뷰*/
    amplitude.getInstance().logEvent('artist-page-views');

    /*유입 채널*/
    var url = document.referrer;
    var ac_name;
    var event = "acquisition-artist-from-channel";
    var eventProperties;

    if (url.indexOf("naver") > 0) {
        ac_name = "naver";
    } else if (url.indexOf("daum") > 0) {
        ac_name = "daum";
    } else if (url.indexOf("google") > 0) {
        ac_name = "google";
    } else if (url.indexOf("tistory") > 0) {
        ac_name = "tistory";
    } else if (url.indexOf("bing") > 0) {
        ac_name = "bing";
    } else if (url.indexOf("yahoo") > 0) {
        ac_name = "yahoo";
    } else if (url.indexOf("facebook") > 0) {
        ac_name = "facebook";
    } else if (url.indexOf("insta") > 0) {
        ac_name = "instagram";
    } else if (url.indexOf("nate") > 0) {
        ac_name = "nate";
    } else if (url.indexOf("youtube") > 0) {
        ac_name = "youtube";
    } else if (url.indexOf("KAKAO") > 0) {
        ac_name = "kakao";
    } else if (url.indexOf("tiktok") > 0) {
        ac_name = "tiktok";
    } else {
        if (url == "") {
            ac_name = "empty";
        } else {
            ac_name = url.substr(0, 40);
        }
    }
    eventProperties = {
        "channel": ac_name
    };

    amplitude.getInstance().logEvent(event, eventProperties);


    /*버튼 이벤트*/
    function run_ucc_btn_start(type) {
        var startBtnType = {
            "type": type
        };
        amplitude.getInstance().logEvent('btn-artist-ucc-start', startBtnType);
    }

    function run_ucc_btn_device(DeviceType) {
        var startDeviceType = {
            "DeviceType": DeviceType
        };
        amplitude.getInstance().logEvent('btn-artist-ucc-device', startDeviceType);
    }

    function run_ucc_btn_scroll() {
        amplitude.getInstance().logEvent('btn-artist-ucc-scroll');
    }

    /*유입 시간*/
    var now = new Date();
    var time = now.getHours();
    var ac_time;

    if (time == 24) {
        ac_time = 0 + " ~ " + 1;
    } else {
        ac_time = time + " ~ " + (time + 1);
    }

    var time_event = "acquisition-user-time";
    var time_eventProperties = {
        "time": ac_time
    };

    amplitude.getInstance().logEvent(time_event, time_eventProperties);

    /*퍼널*/
    var funnel_count = 11;
    var funnel_checked = new Array(funnel_count).fill(false);
    var lastScrollTop = 0;

    var getElementHeight = function (funnel_name) {
        var funnel_element = document.getElementById(funnel_name);
        var funnel_clientRect = funnel_element.getBoundingClientRect();
        var funnel_value = funnel_clientRect.top;
        return funnel_value;
    };

    /*페이지 열리자 마자 퍼널 형성*/
    window.onload = function () {
        amplitude.getInstance().logEvent('fn-artist-page-scroll-0', time_eventProperties);
    };

    document.addEventListener('scroll', function () {
        var st = window.pageYOffset || document.documentElement.scrollTop;
        if (st > lastScrollTop) {
            if (getElementHeight('artist-funnel1') < 0 && funnel_checked[0] == false) {
                funnel_checked[0] = true;
                amplitude.getInstance().logEvent('fn-artist-page-scroll-1', time_eventProperties);
            } else if (getElementHeight('artist-funnel2') < 0 && funnel_checked[1] == false) {
                funnel_checked[1] = true;
                amplitude.getInstance().logEvent('fn-artist-page-scroll-2', time_eventProperties);
            } else if (getElementHeight('artist-funnel3') < 0 && funnel_checked[2] == false) {
                funnel_checked[2] = true;
                amplitude.getInstance().logEvent('fn-artist-page-scroll-3', time_eventProperties);
            } else if (getElementHeight('artist-funnel4') < 0 && funnel_checked[3] == false) {
                funnel_checked[3] = true;
                amplitude.getInstance().logEvent('fn-artist-page-scroll-4', time_eventProperties);
            } else if (getElementHeight('artist-funnel5') < 0 && funnel_checked[4] == false) {
                funnel_checked[4] = true;
                amplitude.getInstance().logEvent('fn-artist-page-scroll-5', time_eventProperties);
            } else if (getElementHeight('artist-funnel6') < 0 && funnel_checked[5] == false) {
                funnel_checked[5] = true;
                amplitude.getInstance().logEvent('fn-artist-page-scroll-6', time_eventProperties);
            } else if (getElementHeight('artist-funnel7') < 0 && funnel_checked[6] == false) {
                funnel_checked[6] = true;
                amplitude.getInstance().logEvent('fn-artist-page-scroll-7', time_eventProperties);
            } else if (getElementHeight('artist-funnel8') < 0 && funnel_checked[7] == false) {
                funnel_checked[7] = true;
                amplitude.getInstance().logEvent('fn-artist-page-scroll-8', time_eventProperties);
            } else if (getElementHeight('artist-funnel9') < 0 && funnel_checked[8] == false) {
                funnel_checked[8] = true;
                amplitude.getInstance().logEvent('fn-artist-page-scroll-9', time_eventProperties);
            } else if (getElementHeight('artist-funnel10') < 0 && funnel_checked[9] == false) {
                funnel_checked[9] = true;
                amplitude.getInstance().logEvent('fn-artist-page-scroll-10', time_eventProperties);
            } else if (getElementHeight('artist-funnel11') < 0 && funnel_checked[10] == false) {
                funnel_checked[10] = true;
                amplitude.getInstance().logEvent('fn-artist-page-scroll-11', time_eventProperties);
            }

        } else {
            /*업 스크롤 부분*/
        }
        lastScrollTop = st <= 0 ? 0 : st;
    }, false);

</script>


<script>
    function isWebEnv() {
        var userAgent = navigator.userAgent || navigator.vendor || window.opera;
        if (/Mobile|Phone|Pad/i.test(userAgent)) {
            return false;
        }
        return true;
    }

    function setScreenSize() { // 모바일 상하단 메뉴바 크기 고려하여 100vh로 조정
        let vh = window.innerHeight * 0.01;
        console.log(window.innerHeight * 0.01);
        document.documentElement.style.setProperty('--vh', `\${vh}px`);
    }

    setScreenSize();
    if (isWebEnv()) { // 웹환경에서만 (모바일 X) 리사이즈시 section1 100vh 유지
        window.addEventListener('resize', function (event) {
            setScreenSize();
        });
    }


    $('.owl-carousel').owlCarousel({
        loop: false,
        // margin: 300,
        nav: false,
        // autoplay: true,
        autoplay: false,
        dots: true,
        // autoplayTimeout: 2000,
        // autoplayHoverPause: true,
        responsive: {
            0: {
                items: 1
            }
        },
    });


    /* image slide start */
    const scrollElements = document.querySelectorAll(".slide-img");
    const elementInView = (el, dividend = 1) => {
        const elementTop = el.getBoundingClientRect().top;

        return (
            elementTop <=
            (window.innerHeight || document.documentElement.clientHeight) / dividend
        );
    };
    const elementOutofView = (el) => {
        const elementTop = el.getBoundingClientRect().top;

        return (
            elementTop > (window.innerHeight || document.documentElement.clientHeight)
        );
    };
    const displayScrollElement = (element) => {
        element.classList.add("scrolled");
    };

    const hideScrollElement = (element) => {
        element.classList.remove("scrolled");
    };
    const handleScrollAnimation = () => {
        scrollElements.forEach((el) => {
            if (elementInView(el, 1.25)) {
                displayScrollElement(el);
            } else if (elementOutofView(el)) {
                hideScrollElement(el)
            }
        })
    };
    window.addEventListener("scroll", () => {
        handleScrollAnimation();
        handleScrollAnimation2();
        handleScrollSection2();
        handleScrollSection3();
    });
    /* image slide end */

    /* image fade in start */
    const scrollElements2 = document.querySelectorAll(".fade-in-img");
    const displayScrollElement2 = (element) => {
        element.classList.add("scrolled2");
        element.style.opacity = 1;
    };

    const hideScrollElement2 = (element) => {
        element.classList.remove("scrolled2");
        element.style.opacity = 0;
    };

    let flag = false;
    const handleScrollAnimation2 = () => {
        scrollElements2.forEach((el) => {
            if (elementInView(el, 1.25)) {
                if (el.classList.contains('horizontal-loop')) {
                    if (flag) return;
                    flag = true;

                    displayScrollElement2(el);
                    if (el.classList.contains("scrolled2")) {
                        setTimeout(() => {
                            el.classList.remove("scrolled2");
                        }, 2000);
                    }
                } else {
                    displayScrollElement2(el);
                }
            } else if (elementOutofView(el)) {
                hideScrollElement2(el)
                if (el.classList.contains('horizontal-loop')) flag = false;
            }
        })
    };
    /* image fade in end */

    /* image slide up start */
    const section2_img1 = document.querySelector("#section2 .img1");
    const section2_img2 = document.querySelector("#section2 .img3");
    const section2_img3 = document.querySelector("#section2 .img2");
    const displayScrollSection2 = (element, className) => {
        element.classList.add(className);
    };

    const hideScrollSection2 = (element, className) => {
        element.classList.remove(className);
    };

    const handleScrollSection2 = () => {
        displayAndHide(section2_img1, 'slide-up');
        displayAndHide(section2_img2, 'slide-up2');
        displayAndHide(section2_img3, 'slide-down');
    };

    const displayAndHide = (el, className) => {
        if (elementInView(el, 1.25)) {
            displayScrollSection2(el, className);
        } else if (elementOutofView(el)) {
            hideScrollSection2(el, className);
        }
    };
    /* image fade in end */

    /* section3 screen slide in Start */
    const section3_img1 = document.querySelector("#section3 .img1");
    const section3_img2 = document.querySelector("#section3 .img2");
    const section3_img3 = document.querySelector("#section3 .img3");
    const section3_img4 = document.querySelector("#section3 .img4");
    const section3_img5 = document.querySelector("#section3 .img5");
    const section3_img6 = document.querySelector("#section3 .img6");
    const handleScrollSection3 = () => {
        displayAndHide(section3_img1, 'screen-slide1');
        displayAndHide(section3_img2, 'screen-slide2');
        displayAndHide(section3_img3, 'screen-slide3');
        displayAndHide(section3_img4, 'screen-slide1-sm');
        displayAndHide(section3_img5, 'screen-slide2-sm');
        displayAndHide(section3_img6, 'screen-slide3-sm');
    };
    /* section3 screen slide in Start */

    /* click and move */
    $('a[href="#section2"]').click((e) => {
        run_ucc_btn_scroll();
        e.preventDefault();
        let dest = $('#section2').offset().top;

        $('html, body').animate({
            scrollTop: dest
        }, 700, 'swing');
    });

    /* 다운로드버튼 for iPhone, Android */
    function getMobileOperatingSystem() {
        var userAgent = navigator.userAgent || navigator.vendor || window.opera;
        // Windows Phone must come first because its UA also contains "Android"
        if (/windows phone/i.test(userAgent)) {
            return "Windows Phone";
        }
        if (/android/i.test(userAgent)) {
            return "Android";
        }
        // iOS detection from: http://stackoverflow.com/a/9039885/177710
        if (/iPad|iPhone|iPod|Mac/.test(userAgent) && !window.MSStream) {
            return "iOS";
        }
        return "unknown";
    }

    switch (getMobileOperatingSystem()) {
        case 'iOS':
            // 애플스토어 다운로드
            console.log('iOS');
            $('#header-start').click(() => {
                run_ucc_btn_device('ios-download-clicked');
                run_ucc_btn_start($('#header-start').val());
                window.location.href = 'https://apps.apple.com/kr/app/ucc/id1580818238';
            });
            $('#medium-start').click(() => {
                run_ucc_btn_device('ios-download-clicked');
                run_ucc_btn_start($('#medium-start').val());
                window.location.href = 'https://apps.apple.com/kr/app/ucc/id1580818238';
            });
            $('#bottom-start').click(() => {
                run_ucc_btn_device('ios-download-clicked');
                run_ucc_btn_start($('#bottom-start').val());
                window.location.href = 'https://apps.apple.com/kr/app/ucc/id1580818238';
            });

            $('#header-active').click(() => {
                run_ucc_btn_device('ios-download-clicked');
                run_ucc_btn_start($('#header-active').val());
                window.location.href = 'https://apps.apple.com/kr/app/ucc/id1580818238';
            });

            $('#bottom-active').click(() => {
                run_ucc_btn_device('ios-download-clicked');
                run_ucc_btn_start($('#bottom-active').val());
                window.location.href = 'https://apps.apple.com/kr/app/ucc/id1580818238';
            });

            break;
        case 'Android':
        case 'Windows Phone':
        default:
            // 안드로이드 다운로드
            console.log('android');

            $('#header-start').click(() => {
                run_ucc_btn_device('google-download-clicked');
                run_ucc_btn_start($('#header-start').val());
                console.log($('#header-start').val());
                window.location.href = 'https://play.google.com/store/apps/details?id=com.weart.ucc';
            });

            $('#medium-start').click(() => {
                run_ucc_btn_device('google-download-clicked');
                run_ucc_btn_start($('#medium-start').val());
                window.location.href = 'https://play.google.com/store/apps/details?id=com.weart.ucc';

            });
            $('#bottom-start').click(() => {
                run_ucc_btn_device('google-download-clicked');
                run_ucc_btn_start($('#bottom-start').val());
                window.location.href = 'https://play.google.com/store/apps/details?id=com.weart.ucc';

            });

            $('#header-active').click(() => {
                run_ucc_btn_device('google-download-clicked');
                run_ucc_btn_start($('#header-active').val());
                window.location.href = 'https://play.google.com/store/apps/details?id=com.weart.ucc';

            });

            $('#bottom-active').click(() => {
                run_ucc_btn_device('google-download-clicked');
                run_ucc_btn_start($('#bottom-active').val());
                window.location.href = 'https://play.google.com/store/apps/details?id=com.weart.ucc';
            });
            break;
    }

    /* section 5, 8에서는 로고 검은색으로 변경 */
    $(window).scroll(function () {
        let height = $(document).scrollTop() + 20 + $('.logo-fixed').height();
        let section_ypos = [];
        let sections = $('section');
        sections.map((idx, section) => {
            section_ypos.push(section.offsetTop);
        });
        if (
            (section_ypos[4] < height && height < section_ypos[5]) ||
            (section_ypos[7] < height)
        ) {
            $('.logo-fixed').addClass('dark-logo');
        } else {
            $('.logo-fixed').removeClass('dark-logo');
        }
    });

    /* wrapper */
    $(function () {
        var offsetTop = $('#wrapper').offset().top;
        $(window).scroll(function () {
            var window = $(this).scrollTop();
            if (window > offsetTop + $('#wrapper').height() / 3) {
                $('#wrapper .img-1').removeClass('active');
                $('#wrapper .img-2').addClass('active');
            } else {
                $('#wrapper .img-1').addClass('active');
                $('#wrapper .img-2').removeClass('active');
            }
        })
    });
</script>
</body>
</html>
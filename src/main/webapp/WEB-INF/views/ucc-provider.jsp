<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html lang="en">
<head>
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
    <style>
        main span, main div.span, section span, section div.span {
            font-family: aggro-m !important;
        }
        .font-7f, div.font-7f span {
            color: #7f7f7f;
        }
    </style>
</head>
<body style="min-width: 1024px; position: relative;">
<header style="background: #0E0E10; height: 4.2rem; position:fixed; top:0; width: 100%; z-index: 100;">
    <div class="d-flex justify-content-between h-100 align-items-center" style="padding: 0 1.875rem;">
        <img src="/assets/images/ucc/top-left-logo-white.png" height="50%" style="transform: translate(0, 5%)"/>
        <div class="h-100 d-flex align-items-center" style="margin-top: 20px">
            <div class="h-100">
                <a href="https://play.google.com/store/apps/details?id=com.weart.ucc"><img src="/assets/images/ucc/google-play-button.png" height="70%"/></a>
            </div>
            <div class="h-100">
                <a href="https://apps.apple.com/kr/app/ucc/id1580818238"><img src="/assets/images/ucc/apple-store-button.png" height="70%"/></a>
            </div>
        </div>
    </div>
</header>
<main style="height: calc(100vh - 4.2rem); margin-top: 4.2rem;" class="d-flex justify-content-center align-items-end w-100">
    <video playsinline muted loop autoplay style="width: 100%; height: 100vh; object-fit: cover; position: absolute; z-index: -1;">
        <source src="/assets/video/ucc_user_main_video_small.m4v" type="video/mp4">
        Sorry, your browser doesn't support embedded videos.
    </video>
    <div class="font-white font-family-aggro-b font-size-lg-56 font-size-48 d-flex flex-column align-items-center text-center">
        <span>
            아티스트가 수익 걱정 없이<br>활동할 수 있는 무대 <img src="/assets/images/ucc/ucc-main-logo-linear.svg"/>
        </span>
        <div>
            <a href="#section1"><img src="/assets/images/ucc/icon_arrow_bottom.svg"></a>
        </div>
    </div>
</main>
<section id="section1" style="overflow-x: hidden; padding-top: 5rem;">
    <div class="custom-container position-relative">
        <div class="d-flex flex-column h-100 content">
            <div class="d-flex font-family-aggro-b font-size-40 font-size-lg-48">
                <span>장르 제한 NO!<br>열정과 재능 “ONLY”</span>
            </div>
            <div class="d-flex font-family-aggro-m font-size-16 font-size-lg-24 font-7f py-2">
                <span>뮤지션부터 벌룬 아트 디자이너까지 UCC에는<br>
                다양한 분야의 열정을 지닌 아티스트가 활동 중입니다.</span>
            </div>
            <div style="margin-top: 6rem;" class="d-flex">
                <a href="#section6" style="text-decoration: none; z-index: 10;">
                    <div style="background: #816bff; border-radius: 5px; padding: 20px 32px;">
                        <span style="color: white;" class="font-family-aggro-b">UCC에서 활동하기</span>
                    </div>
                </a>
            </div>
        </div>
        <div class="image-box">
            <img id="screen1" src="/assets/images/ucc/mobile-screene-tilt.png">
            <img id="artist-circle1" src="/assets/images/ucc/provider-artist-circle-x1-1.png">
            <img id="artist-circle2" src="/assets/images/ucc/provider-artist-circle-x1-2.png">
            <img id="artist-circle3" src="/assets/images/ucc/provider-artist-circle-x1-3.png">
            <img id="artist-circle4" src="/assets/images/ucc/provider-artist-circle-x1-4.png">
            <img id="artist-circle5" src="/assets/images/ucc/provider-artist-circle-x1-5.png">
            <img id="artist-circle6" src="/assets/images/ucc/provider-artist-circle-x1-6.png">
            <img id="artist-circle7" src="/assets/images/ucc/provider-artist-circle-x1-7.png">
        </div>
    </div>
</section>
<section id="section2" style="background: black; padding-top: 6rem; padding-bottom: 5rem; overflow: hidden">
    <div class="d-flex flex-column align-items-center">
        <div class="text-center">
            <span class="font-size-lg-40 font-size-32 font-family-aggro-b font-white">아티스트의 넘치는 열정,<br>그러나 부족했던 창작 활동의 무대</span>
        </div>
        <div class="text-center" style="padding-top: 1rem; padding-bottom: 3rem;">
            <span class="font-size-lg-24 font-size-16 font-7f">좋아서 시작한 예술 창작 활동이지만<br>현실의 벽은 높기만 합니다. </span>
        </div>
        <div class="squares">
            <img class="py-4" src="/assets/images/ucc/artist-pain-1.png">
            <img class="py-4" src="/assets/images/ucc/artist-pain-2.png">
            <img class="py-4" src="/assets/images/ucc/artist-pain-3.png">
        </div>
    </div>
</section>
<section id="section3" style="padding-top: 5rem; padding-bottom: 5rem;">
    <div class="d-flex flex-column align-items-center">
        <div class="text-center">
            <span class="font-size-lg-40 font-size-32 font-family-aggro-b">다양한 수익 창출 기회가<br>열려있는 UCC 무대</span>
        </div>
        <div class="text-center" style="padding-top: 1rem; padding-bottom: 3rem;">
            <span class="font-size-lg-24 font-size-16 font-7f">예술 창작 활동을 응원하는 팬들의 후원,<br>가치를 볼 줄 아는 브랜드들과 협업을 확인해 보세요.</span>
        </div>
        <div class="d-flex screens-4">
            <div class="d-flex flex-column align-items-center text-center">
                <span class="font-size-32 font-size-lg-32">아티스트 후원</span>
                <span class="font-size-16 font-7f" style="padding-top: 1rem; padding-bottom: 3rem;">아티스트를 응원하는 팬들의 후원으로 <br>작품 활동을 이어나가 보세요! </span>
                <img class="slide-img" src="/assets/images/ucc/ucc-provider-mobile-screen-sample-1.png" style="width: 349px; object-fit: contain; opacity: 0; margin-right: 45px;">
            </div>
            <div class="d-flex justify-content-center align-items-start">
                <img class="slide-img" src="/assets/images/ucc/ucc-provider-mobile-screen-sample-2.png" style="width: 349px; object-fit: contain; opacity: 0; margin-left: 45px;">
            </div>
        </div>
        <div class="d-flex screens-4">
            <div class="d-flex justify-content-center align-items-start">
                <img class="slide-img" src="/assets/images/ucc/ucc-provider-mobile-screen-sample-3.png" style="width: 349px; object-fit: contain; opacity: 0; margin-right: 45px;">
            </div>
            <div class="d-flex flex-column align-items-center text-center">
                <span class="font-size-32 font-size-lg-32">콘테스트/협찬&광고</span>
                <span class="font-size-16 font-7f" style="padding-top: 1rem; padding-bottom: 3rem;">다양한 브랜드와 작품 협업을 통해<br>수익을 창출해 보세요!</span>
                <img class="slide-img" src="/assets/images/ucc/ucc-provider-mobile-screen-sample-4.png" style="width: 349px; object-fit: contain; opacity: 0; margin-left: 45px;">
            </div>
        </div>
        <div style="padding-bottom: 3rem; padding-top: 4rem;">
            <a href="#section6" style="text-decoration: none;">
                <div style="background: #816bff; border-radius: 5px; padding: 20px 32px;">
                    <span style="color: white;" class="font-family-aggro-b">UCC에서 활동하기</span>
                </div>
            </a>
        </div>
    </div>
</section>
<section id="section4" style="background: black; padding-bottom: 6rem; padding-top: 6rem; overflow: hidden">
    <div class="d-flex flex-column align-items-center text-center">
        <span class="font-white font-size-lg-40 font-size-32 font-family-aggro-b">나의 예술을 표현하는 데<br>제한이 없어요!</span>
        <span class="font-size-lg-24 font-size-16 font-7f" style="padding-top: 1rem; padding-bottom: 2rem;">비디오는 물론, 이미지, 에디트 등<br>다양한 방법으로 작품 활동이 가능합니다.</span>
        <div class="slide-scroll-lg" style="margin-top: 4rem; margin-bottom: 3rem;">
            <div class="d-flex flex-column" style="margin-right: 39px;">
                <div class="scroll-slide-1 fade-out-img" style="margin-bottom: 50px; opacity: 0;"><img src="/assets/images/ucc/provider-scroll-slide-1.png"></div>
                <div class="scroll-slide-3 fade-out-img" style="margin-top: 50px; opacity: 0;"><img src="/assets/images/ucc/provider-scroll-slide-3.png"></div>
            </div>
            <div class="d-flex align-items-center" style="margin-left: 39px;">
                <div class="scroll-slide-2 fade-out-img" style="opacity: 0;"><img src="/assets/images/ucc/provider-scroll-slide-2.png"></div>
            </div>
        </div>
        <div class="slide-scroll">
            <div class="scroll-slide-1"><img src="/assets/images/ucc/provider-scroll-slide-1.png"></div>
            <div class="scroll-slide-2"><img src="/assets/images/ucc/provider-scroll-slide-2.png"></div>
            <div class="scroll-slide-3"><img src="/assets/images/ucc/provider-scroll-slide-3.png"></div>
        </div>
    </div>
</section>
<section id="section5" style="overflow: hidden">
    <div class="d-flex flex-column text-center align-items-center">
        <span class="font-size-lg-40 font-size-32 font-family-aggro-b">창작 활동을 사랑한다면<br>더 이상 망설이지 마세요!</span>
        <span class="font-size-lg-24 font-size-16 font-7f" style="padding: 1rem 0;">UCC는 예술을 사랑하는 사람들이 모여 소통하고<br>공감하며 더 나은 예술 문화를 만들어 나갑니다.</span>
        <div class="squares" style="margin-top: 2rem;">
            <img src="/assets/images/ucc/provider-content-square-1.png" style="padding: 2.8rem 0;">
            <img src="/assets/images/ucc/provider-content-square-2.png" style="padding: 2.8rem 0;">
            <img src="/assets/images/ucc/provider-content-square-3.png" style="padding: 2.8rem 0;">
        </div>
    </div>
</section>
<section id="section6" style="background: black; padding-top: 6rem; overflow: hidden;">
    <div class="d-flex flex-column align-items-center text-center">
        <span class="font-white font-family-aggro-b font-size-lg-40 font-size-32">UCC와 예술을 사랑하는 사람들이<br>당신의 창작 활동을 기다립니다</span>
        <div style="margin-top: 3rem; margin-bottom: 6rem;">
            <a href="https://play.google.com/store/apps/details?id=com.weart.ucc" style="margin: 0 10px; display: inline-block; width: 231px;"><img src="/assets/images/ucc/google-play-button.png" width="100%"/></a>
            <a href="https://apps.apple.com/kr/app/ucc/id1580818238" style="margin: 0 10px; display: inline-block; width: 231px;"><img src="/assets/images/ucc/apple-store-button.png" width="100%"/></a>
        </div>
        <div class="d-flex artists">
            <div class="owl-carousel">
                <div class="item font-grey position-relative"><img src="/assets/images/ucc/artist-1.png">
                    <div class="custom-overlay font-white d-none">
                        <span class="font-size-24 font-size-sm-12 mb-1 mb-lg-1 font-family-aggro-b">박성헌</span>
                        <span class="font-size-20 font-size-sm-12 mb-3 mg-lg-2">연극배우</span>
                        <span class="font-size-16 font-size-sm-10">불타는 열정을 가지고 연기하는 배우가 되겠습니다.</span>
                    </div>
                </div>
                <div class="item font-grey position-relative"><img src="/assets/images/ucc/artist-2.png">
                    <div class="custom-overlay font-white d-none">
                        <span class="font-size-24 font-size-sm-12 mb-1 mb-lg-1 font-family-aggro-b">김태윤</span>
                        <span class="font-size-20 font-size-sm-12 mb-3 mg-lg-2">벌룬 아트 디자이너</span>
                        <span class="font-size-16 font-size-sm-10">풍선으로 한계가 없다는 것을 보여드리겠습니다.</span>
                    </div>
                </div>
                <div class="item font-grey position-relative"><img src="/assets/images/ucc/artist-3.png">
                    <div class="custom-overlay font-white d-none">
                        <span class="font-size-24 font-size-sm-12 mb-1 mb-lg-1 font-family-aggro-b">윤진원</span>
                        <span class="font-size-20 font-size-sm-12 mb-3 mg-lg-2">모델</span>
                        <span class="font-size-16 font-size-sm-10">브랜드 'COKIE'의 최욱일 디자이너와 콜라보레이션한 모델 윤진원입니다.</span>
                    </div>
                </div>
                <div class="item font-grey position-relative"><img src="/assets/images/ucc/artist-4.png">
                    <div class="custom-overlay font-white d-none">
                        <span class="font-size-24 font-size-sm-12 mb-1 mb-lg-1 font-family-aggro-b">김요한</span>
                        <span class="font-size-20 font-size-sm-12 mb-3 mg-lg-2">가수</span>
                        <span class="font-size-16 font-size-sm-10">잔잔한 청년 버스커 김요한입니다.</span>
                    </div>
                </div>
                <div class="item font-grey position-relative"><img src="/assets/images/ucc/artist-5.png">
                    <div class="custom-overlay font-white d-none">
                        <span class="font-size-24 font-size-sm-12 mb-1 mb-lg-1 font-family-aggro-b">신아람</span>
                        <span class="font-size-20 font-size-sm-12 mb-3 mg-lg-2">재즈 피아니스트</span>
                        <span class="font-size-16 font-size-sm-10">저를 솔직하게 예술에 담아내는 그 날까지 연주하겠습니다.</span>
                    </div>
                </div>
                <div class="item font-grey position-relative"><img src="/assets/images/ucc/artist-6.png">
                    <div class="custom-overlay font-white d-none">
                        <span class="font-size-24 font-size-sm-12 mb-1 mb-lg-1 font-family-aggro-b">길기현</span>
                        <span class="font-size-20 font-size-sm-12 mb-3 mg-lg-2">웨이뮤직 대표</span>
                        <span class="font-size-16 font-size-sm-10">웨이뮤직 및 비르투오소 소속 독일 유학파 작곡가, 피아니스트 길기현입니다.</span>
                    </div>
                </div>
                <div class="item font-grey position-relative"><img src="/assets/images/ucc/artist-7.png">
                    <div class="custom-overlay font-white d-none">
                        <span class="font-size-24 font-size-sm-12 mb-1 mb-lg-1 font-family-aggro-b">영락</span>
                        <span class="font-size-20 font-size-sm-12 mb-3 mg-lg-2">가수</span>
                        <span class="font-size-16 font-size-sm-10">영원히 즐겁게 노래하는 가수가 되겠습니다.</span>
                    </div>
                </div>
                <div class="item font-grey position-relative"><img src="/assets/images/ucc/artist-8.png">
                    <div class="custom-overlay font-white d-none">
                        <span class="font-size-24 font-size-sm-12 mb-1 mb-lg-1 font-family-aggro-b">육민재</span>
                        <span class="font-size-20 font-size-sm-12 mb-3 mg-lg-2">프로듀서</span>
                        <span class="font-size-16 font-size-sm-10">위로와 공감을 주는 프로듀서가 되겠습니다.</span>
                    </div>
                </div>
                <div class="item font-grey position-relative"><img src="/assets/images/ucc/artist-9.png">
                    <div class="custom-overlay font-white d-none">
                        <span class="font-size-24 font-size-sm-12 mb-1 mb-lg-1 font-family-aggro-b">윤정인</span>
                        <span class="font-size-20 font-size-sm-12 mb-3 mg-lg-2">배우</span>
                        <span class="font-size-16 font-size-sm-10">배우 지망생 윤정인입니다.</span>
                    </div>
                </div>
                <div class="item font-grey position-relative"><img src="/assets/images/ucc/artist-10.png">
                    <div class="custom-overlay font-white d-none">
                        <span class="font-size-24 font-size-sm-12 mb-1 mb-lg-1 font-family-aggro-b">피난</span>
                        <span class="font-size-20 font-size-sm-12 mb-3 mg-lg-2">밴드</span>
                        <span class="font-size-16 font-size-sm-10">불안한 청춘을 노래하는 밴드 피난입니다.</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="partials/ucc-footer.jsp"/>

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>
<script src="/assets/js/owl.carousel.js"></script>


<script>
    $('.owl-carousel').owlCarousel({
        loop: true,
        margin: 30,
        nav: false,
        // autoplay: true,
        autoplay: false,
        dots: false,
        autoplayTimeout: 2000,
        autoplayHoverPause: true,
        responsive:{
            0:{
                items:4
            },
            1030:{
                items:7
            }
        }
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
    });
    /* image slide end */

    /* image fade in start */
    const scrollElements2 = document.querySelectorAll(".fade-out-img");
    const elementInView2 = (el, dividend = 1) => {
        const elementTop = el.getBoundingClientRect().top;
        return (
            elementTop <=
            (window.innerHeight || document.documentElement.clientHeight) / dividend
        );
    };
    const elementOutofView2 = (el) => {
        const elementTop = el.getBoundingClientRect().top;

        return (
            elementTop > (window.innerHeight || document.documentElement.clientHeight)
        );
    };
    const displayScrollElement2 = (element) => {
        element.classList.add("scrolled2");
        element.style.opacity = 1;
    };

    const hideScrollElement2 = (element) => {
        element.classList.remove("scrolled2");
        element.style.opacity = 0;
    };
    const handleScrollAnimation2 = () => {
        scrollElements2.forEach((el) => {
            if (elementInView2(el, 1.25)) {
                displayScrollElement2(el);
            } else if (elementOutofView2(el)) {
                hideScrollElement2(el)
            }
        })
    };
    /* image fade in end */

    /* click and move */
    $('a[href="#section1"]').click((e) => {
        console.log("trigg");
        e.preventDefault();
        let dest = $('#section1').offset().top;

        $('html, body').animate({
            scrollTop: dest
        }, 700, 'swing');
    });
    $('a[href="#section6"]').click((e) => {
        console.log("trigg");
        e.preventDefault();
        let dest = $('#section6').offset().top;

        $('html, body').animate({
            scrollTop: dest
        }, 700, 'swing');
    });
</script>
</body>
</html>
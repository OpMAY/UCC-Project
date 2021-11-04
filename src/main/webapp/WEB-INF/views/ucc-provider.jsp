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
    <link rel="stylesheet" href="/assets/css/ucc-user.css">
    <link rel="stylesheet" href="/assets/css/ucc-provider.css">
    <title>[UCC] temp title</title>
    <!--    <link rel="icon" href="../../../assets/images/ucc/favicon.ico">-->
    <!--    <link rel="apple-touch-icon" href="../../../assets/images/ucc/favicon.ico"/>-->
    <link rel="stylesheet" href="/assets/css/owl.carousel.css">
    <style>
        main span, main div.span, section span, section div.span {
            font-family: aggro-m !important;
        }
    </style>
</head>
<body style="background: black;">
    <div style="background: url('/assets/images/ucc/ucc-provider-background-svg1.png'); background-repeat: repeat-y; width: 100%; background-size: contain; max-width: 2560px; margin: auto;">
    <main class="position-relative">
        <img id="background-top" src="/assets/images/ucc/ucc-provider-background-top.png" style="position: absolute; top: 0; width: 100%; z-index: 0;">
        <div class="container">
            <div class="row py-4">
                <div class="col-12 d-flex justify-content-between">
                    <img class="logo-gradient" src="/assets/images/ucc/ucc-provider-logo.png">
                    <div>
                        <img class="logo-fixed2" data-toggle="modal" data-target="#shareModal" src="/assets/images/ucc/icon_share.svg" width="25px" style="cursor: pointer;"><!-- onclick modal open -->
                        <!-- 모달 -->
                        <div class="modal fade" id="shareModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content" style="border-radius: 0;">
                                    <div class="row text-center flex-column align-items-center position-relative p-3">
                                        <button type="button" class="close position-absolute" data-dismiss="modal" aria-label="Close" style="right: 40px; top: 20px;">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                        <div class="row font-size-20 font-family-aggro-b m-2">
                                            <span id="shareText">공유하기</span>
                                        </div>
                                        <div class="row m-2">
                                            <div class="row p-2" style="border: solid 1px rgba(0,0,0,0.3); border-radius: 5px; cursor: pointer" onclick="copyToClipboard(this)">
                                                <div class="col-9 font-family-aggro-m">
                                                    <span>https://mvsolutions.co.kr/ucc.do</span>
                                                </div>
                                                <div class="col-3 font-family-aggro-b" style="border-left: solid 1px rgba(0,0,0,0.3);">
                                                    <span style="color: red;">URL 복사</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12 font-size-64 font-size-sm-24 font-family-aggro-b" id="main-text">
                    <span class="font-white"><span style="color: red; !important;">장르 불문 </span>아티스트를 위한 놀이터!</span>
                </div>
            </div>
            <div class="row">
                <div class="col-12 font-size-24 font-size-sm-12 font-white d-flex flex-column">
                    <span>UCC에서 세상과 소통하고</span>
                    <span>다양한 활동을 통해 수익 창출의 기회를 만나보세요</span>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <a href="#section2" class="provider-btn-top btn px-lg-5" style="background: white;">
                        <span class="font-black font-size-24 font-size-sm-8 font-family-aggro-b">UCC에서 활동하기</span>
                    </a>
                </div>
            </div>
            <div class="row py-1 mr-0">
                <div id="text-1-1" class="col-9 font-white font-size-40 font-size-sm-16 flex-column d-flex">
                    <span>아티스트 활동,</span>
                    <span>처음 시작의 설레임은 여전하신가요?</span>
                </div>
                <div id="line-1" class="col-3 text-side-line-white"></div>
            </div>
            <div class="row">
                <div id="text-1-2" class="col-12 font-white font-size-24 font-size-sm-12 flex-column d-flex flex-column py-2">
                    <span>좋아서 시작한 아티스트 활동이지만</span>
                    <span>현실의 벽은 높기만 합니다.</span>
                </div>
            </div>
            <div class="row my-3">
                <div class="col-12 d-flex flex-column flex-xl-row justify-content-between align-items-center square-three-images">
                    <img src="/assets/images/ucc/artist-pain-1.png">
                    <img src="/assets/images/ucc/artist-pain-2.png">
                    <img src="/assets/images/ucc/artist-pain-3.png">
                </div>
            </div>
        </div>
    </main>
    <section id="section1" style="overflow: hidden;">
        <div class="container">
            <div class="row pt-lg-5 pt-4 mr-0">
                <div id="text-2-1" class="col-8 font-white font-size-40 font-size-sm-16 flex-column d-flex">
                    <span>열정과 재능이 있는 아티스트가</span>
                    <span>꿈을 이어갈 수 있도록!</span>
                </div>
                <div id="line-2" class="col-4 text-side-line-white"></div>
            </div>
            <div class="row">
                <div id="text-2-2" class="col-12 d-flex flex-column font-size-24 font-size-sm-12 font-white flex-column py-3">
                    <span>아티스트와 세상을 연결하고 수익 창출을 통해</span>
                    <span>작품 활동을 즐기는 놀이터 같은 공간을 마련했습니다.</span>
                </div>
            </div>
            <div class="row justify-content-lg-start justify-content-center position-relative" style="margin-top: 5%; margin-bottom:15%;">
                <img src="/assets/images/ucc/ucc-provider-mobile-screen-sample.png" class="artist-mobile-screen" style="object-fit: contain;">
                <img src="/assets/images/ucc/artists-circles.png" class="artists-circles" style="position: absolute;">
            </div>
        </div>
    </section>

    <section id="section2" style="background: #F0EDFF;" class="py-lg-5 py-4 position-relative">
        <img src="/assets/images/ucc/ucc-palette-logo.png" class="logo-middle">
        <div class="container">
            <div class="row flex-column flex-lg-row align-items-center">
                <div class="col-12 col-lg-6 mb-2 mb-lg-0">
                    <div class="row justify-content-center justify-content-lg-start font-size-24 font-size-sm-12">
                        <span>장르 불문&nbsp;</span><span style="color: #816BFF;">꿈과 열정을 가진</span>
                    </div>
                    <div class="row justify-content-center justify-content-lg-start font-size-32 font-size-sm-16">
                        <span>아티스트와 함께하길 기다립니다!</span>
                    </div>
                </div>
                <div class="col-12 col-lg-6 app-download-btns">
                    <div class="row justify-content-center">
                        <img src="/assets/images/ucc/google-play-button.png" onclick="window.location.href='https://play.google.com/store/apps/details?id=com.weart.ucc';" style="cursor: pointer;">
                        <img src="/assets/images/ucc/apple-store-button.png" onclick="window.location.href='https://apps.apple.com/kr/app/ucc/id1580818238';" style="cursor: pointer;">
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section id="section3">
        <div class="container pt-lg-5">
            <div class="row pt-5 mr-0">
                <div id="text-3-1" class="col-7 font-black font-size-40 font-size-sm-16 flex-column d-flex">
                    <span>다양한 활동으로 수익 창출</span>
                    <span>기회를 만나 보세요!</span>
                </div>
                <div id="line-3" class="col-5 text-side-line-black"></div>
            </div>
            <div class="row">
                <div id="text-3-2" class="col-12 d-flex font-size-24 font-size-sm-12 font-black flex-column py-3">
                    <span>아티스트 작품 활동을 응원하는 팬들의 후원과</span>
                    <span>가치를 볼 줄 아는 브랜드들과 협업을 경험해 보세요.</span>
                </div>
            </div>
        </div>
    </section>
    <section>
        <div class="container-fluid p-0 pt-4 pb-5">
            <div class="d-flex py-lg-5 py-4 justify-content-lg-center justify-content-between twin-mobile-screens">
                <div class="d-flex flex-column ">
                    <div id="text-4-1" class="font-black d-flex justify-content-center flex-column text-center" style="z-index: 1;">
                        <span class="font-size-32 font-size-sm-16 pb-3">아티스트 후원</span>
                        <span class="font-size-16 font-size-sm-10 mb-lg-5 mb-4 px-1">아티스트를 응원하는 팬들의 후원으로<br class="d-none d-md-none d-lg-none d-xl-block"> 작품 활동을 이어나가 보세요!</span>
                    </div>
                    <img class="slide-img" src="/assets/images/ucc/ucc-provider-mobile-screen-sample-1.png" style="width: 100%; object-fit: contain; opacity: 0;">
                </div>
                <div class="d-flex">
                    <img class="slide-img" src="/assets/images/ucc/ucc-provider-mobile-screen-sample-2.png" style="width: 100%; object-fit: contain; opacity: 0; object-fit: contain; object-position: top">
                </div>
            </div>
            <div class="d-flex py-lg-5 py-4 justify-content-lg-center justify-content-between twin-mobile-screens">
                <div class="d-flex">
                    <img class="slide-img" src="/assets/images/ucc/ucc-provider-mobile-screen-sample-3.png" style="width: 100%; object-fit: contain; opacity: 0; object-fit: contain; object-position: top">
                </div>
                <div class="d-flex flex-column">
                    <div id="text-4-2" class="d-flex justify-content-center flex-column text-center font-black" style="z-index: 1;">
                        <span class="font-size-32 font-size-sm-16 pb-3">콘테스트/협찬&광고</span>
                        <span class="font-size-16 font-size-sm-10 mb-lg-5 mb-4 px-3">다양한 브랜드와 작품 협업을 통해<br class="d-none d-md-none d-lg-none d-xl-block">  수익을 창출해 보세요!</span>
                    </div>
                    <img class="slide-img" src="/assets/images/ucc/ucc-provider-mobile-screen-sample-4.png" style="width: 100%; object-fit: contain; opacity: 0;">
                </div>
            </div>
        </div>
    </section>

    <section id="section4" style="background: #F0EDFF;" class="py-lg-5 py-4 position-relative">
        <img src="/assets/images/ucc/ucc-palette-logo.png" class="logo-middle">
        <div class="container">
            <div class="row flex-column flex-lg-row align-items-center">
                <div class="col-12 col-lg-6 mb-2 mb-lg-0">
                    <div class="row justify-content-center justify-content-lg-start font-size-24 font-size-sm-12">
                        <span style="color: #816BFF;">아티스트의 특별한 재능</span><span>을 통한</span>
                    </div>
                    <div class="row justify-content-center justify-content-lg-start font-size-32 font-size-sm-16">
                        <span>수익 창출의 기회를 놓치지 마세요!</span>
                    </div>
                </div>
                <div class="col-12 col-lg-6 app-download-btns">
                    <div class="row justify-content-center">
                        <img src="/assets/images/ucc/google-play-button.png" onclick="window.location.href='https://play.google.com/store/apps/details?id=com.weart.ucc';" style="cursor: pointer;">
                        <img src="/assets/images/ucc/apple-store-button.png" onclick="window.location.href='https://apps.apple.com/kr/app/ucc/id1580818238';" style="cursor: pointer;">
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section id="section5">
        <div class="container pt-lg-5">
            <div class="row pt-5 mr-0 p-0">
                <div id="text-5-1" class="col-7 font-white font-size-40 font-size-sm-16 flex-column d-flex">
                    <span>다양한 방법으로</span>
                    <span>나의 작품을 표현해보세요!</span>
                </div>
                <div id="line-5" class="col-5 text-side-line-white"></div>
            </div>
            <div class="row">
                <div id="text-5-2" class="col-12 d-flex font-size-24 font-size-sm-12 font-white flex-column py-3">
                    <span>UCC는 비디오는 물론, 이미지, 에디트 등</span>
                    <span>다양한 방법으로 작품 활동이 가능합니다!</span>
                </div>
            </div>
            <div id="three-circles" class="row justify-content-lg-between flex-lg-row flex-column align-items-center justify-content-center m-0 pt-lg-5 pt-3 pb-5 circle-three-images">
                <div class="row flex-column align-items-center m-0">
                    <img src="/assets/images/ucc/provider-content-icon-1.png">
                    <span class="font-black mt-3 font-size-24 font-size-sm-12">비디오</span>
                </div>
                <div class="row flex-column align-items-center m-0">
                    <img src="/assets/images/ucc/provider-content-icon-2.png">
                    <span class="font-black mt-3 font-size-24 font-size-sm-12">이미지</span>
                </div>
                <div class="row flex-column align-items-center m-0">
                    <img src="/assets/images/ucc/provider-content-icon-3.png">
                    <span class="font-black mt-3 font-size-24 font-size-sm-12">에디트</span>
                </div>
            </div>
        </div>
    </section>

    <section id="section6">
        <div class="container pt-lg-5">
            <div class="row pt-lg-5 mr-0">
                <div id="text-6-1" class="col-7 font-white font-size-40 font-size-sm-16 flex-column d-flex">
                    <span>예술을 사랑한다면 누구나</span>
                    <span>함께하는 공간 UCC</span>
                </div>
                <div id="line-6" class="col-5 text-side-line-white"></div>
            </div>
            <div id="text-6-2" class="row font-size-24 font-size-sm-12 font-white flex-column py-3">
                <div class="col-lg-7 col-10">
                    <span style="width: 50%;">UCC는 다양한 분야 아티스트, 예술을 사랑하는 사람들이 소통하고 공감하며 더 나은 예술 문화를 만들어 나갑니다.</span>
                </div>
            </div>
            <div class="row my-3">
                <div class="col-12 d-flex flex-column flex-xl-row justify-content-lg-between justify-content-center align-items-center square-three-images">
                    <img src="/assets/images/ucc/provider-content-square-1.png">
                    <img src="/assets/images/ucc/provider-content-square-2.png">
                    <img src="/assets/images/ucc/provider-content-square-3.png">
                </div>
            </div>
        </div>
    </section>
    <section id="section7">
        <div class="container pt-4">
            <div class="row pt-lg-5 pt-0 mr-0">
                <div id="text-7-1" class="col-7 col-md-9 col-lg-7 font-black font-size-40 font-size-sm-16 flex-column d-flex">
                    <span>이미 다양한 아티스트가</span>
                    <span>UCC에서 활동하고 있습니다</span>
                </div>
                <div id="line-7" class="col-5 col-md-3 col-lg-5 text-side-line-black"></div>
            </div>
            <div class="row">
                <div id="text-7-2" class="col-12 d-flex font-size-24 font-size-sm-12 font-black flex-column py-3">
                    <span>아티스트의 열정과 재능을 누구보다 사랑하는</span>
                    <span>UCC 피플들과 소통하며 작품 활동을 이어나가 보세요!</span>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <a href="#section4" class="provider-btn btn" style="background: black;">
                        <span class="font-white font-size-24 font-size-sm-8 font-family-aggro-b">UCC에서 활동하기</span>
                    </a>
                </div>
            </div>
        </div>
    </section>
    <section id="section8" style="overflow-x:hidden;">
        <div class="container-fluid" style="width: 110%; left: -5%; position:relative;">
            <div class="row">
                <div class="artist owl-carousel owl-theme col-12">
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
    </div>
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
        autoplay: true,
        // autoplay: false,
        dots: false,
        autoplayTimeout: 2000,
        autoplayHoverPause: true,
        responsive:{
            0:{
                items:3
            },
            768:{
                items:5
            },
            1200:{
                items:7
            }
        }
    });

    $(document).ready(()=>{ // 캐러셀 좌우 버튼

        window.addEventListener('resize', (event) => {
        });
    });




    $(window).scroll(function () {
    });

    function copyToClipboard(element) {
        let url = element.children[0].innerText;
        navigator.clipboard.writeText(url).then(function () {
            $('#shareText').text('복사되었습니다');
            setTimeout(() => {
                $('#shareText').text('공유하기');
            }, 800);
        }, function () {
            alert('복사 권한이 없습니다.')
        });
    }

    let text_1 = $('#text-1-1, #text-1-2');
    let text_2 = $('#text-2-1, #text-2-2');
    let text_3 = $('#text-3-1, #text-3-2');
    let text_4_1 = $('#text-4-1');
    let text_4_2 = $('#text-4-2');
    let text_5 = $('#text-5-1, #text-5-2');
    let text_6 = $('#text-6-1, #text-6-2');
    let text_7 = $('#text-7-1, #text-7-2');

    let line_1 = $('#line-1');
    let line_2 = $('#line-2');
    let line_3 = $('#line-3');
    let line_5 = $('#line-5');
    let line_6 = $('#line-6');
    let line_7 = $('#line-7');
    let lines = $('#line-1, #line-2, #line-3, #line-4, #line-5, #line-6, #line-7');

    let three_circles = $('#three-circles span');
    let width = window.innerWidth;

    changeFontColor(width);
    window.addEventListener('resize', (event) => {
        width = window.innerWidth;
        changeFontColor(width);
    });

    function changeFontColor(width) {
        if (width < 1396) {
            text_2.removeClass('font-white');
            text_2.addClass('font-black');
            line_2.removeClass('text-side-line-white');
            line_2.addClass('text-side-line-black');
        } else {
            text_2.addClass('font-white');
            text_2.removeClass('font-black');
            line_2.addClass('text-side-line-white');
            line_2.removeClass('text-side-line-black');
        }

        if (width > 2276 || (width < 1198 && width > 1036) || (width < 728 && width > 614)) {
            text_3.addClass('font-white');
            text_3.removeClass('font-black');
            line_3.addClass('text-side-line-white');
            line_3.removeClass('text-side-line-black');
        } else {
            text_3.removeClass('font-white');
            text_3.addClass('font-black');
            line_3.removeClass('text-side-line-white');
            line_3.addClass('text-side-line-black');
        }

        if (width > 2374 || (width < 1232 && width > 1068) || (width < 768 && width > 662)) {
            text_4_1.addClass('font-white');
            text_4_1.removeClass('font-black');
        } else {
            text_4_1.removeClass('font-white');
            text_4_1.addClass('font-black');
        }

        if ((width < 1604 && width > 1200) || (width<954 && width > 768) || (width<616 && width > 438)) {
            text_4_2.addClass('font-white');
            text_4_2.removeClass('font-black');
        } else {
            text_4_2.removeClass('font-white');
            text_4_2.addClass('font-black');
        }

        if (width > 2030 || (width < 1594 && width > 1090) || (width < 1024 && width > 760) || (width < 698 && width > 457) || width < 418) {
            text_5.removeClass('font-white');
            text_5.addClass('font-black');
            line_5.removeClass('text-side-line-white');
            line_5.addClass('text-side-line-black');
        } else {
            text_5.addClass('font-white');
            text_5.removeClass('font-black');
            line_5.addClass('text-side-line-white');
            line_5.removeClass('text-side-line-black');
        }

        if (width > 2350 || (width < 1798 && width > 1200) || (width < 1134 && width > 1022) || (width < 992 && width > 958) || (width < 910 && width > 650) || (width < 598 && width > 440) || (width < 416 && width > 0)) {
            text_6.removeClass('font-white');
            text_6.addClass('font-black');
            line_6.removeClass('text-side-line-white');
            line_6.addClass('text-side-line-black');
        } else {
            text_6.addClass('font-white');
            text_6.removeClass('font-black');
            line_6.addClass('text-side-line-white');
            line_6.removeClass('text-side-line-black');
        }

        if (width > 2025 || (width < 1314 && width > 1202) || (width < 868 && width > 837) || (width < 576 && width > 548) || (width < 408 && width > 388)) {
            text_7.addClass('font-white');
            text_7.removeClass('font-black');
            line_7.addClass('text-side-line-white');
            line_7.removeClass('text-side-line-black');
        } else {
            text_7.removeClass('font-white');
            text_7.addClass('font-black');
            line_7.removeClass('text-side-line-white');
            line_7.addClass('text-side-line-black');
        }

        if (width < 1674 || width > 2283) {
            three_circles.removeClass('font-white');
            three_circles.addClass('font-black');
        } else {
            three_circles.addClass('font-white');
            three_circles.removeClass('font-black');
        }
    }

    /* image slid start */
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
    });
    /* image slid end */

    $('a[href="#section2"]').click((e) => {
        e.preventDefault();
        let dest = $('#section2').offset().top - window.innerHeight / 3;

        $('html, body').animate({
            scrollTop: dest
        }, 700, 'swing');
    });
    $('a[href="#section4"]').click((e) => {
        e.preventDefault();
        let dest = $('#section4').offset().top - window.innerHeight / 3;

        $('html, body').animate({
            scrollTop: dest
        }, 700, 'swing');
    });
</script>
</body>
</html>
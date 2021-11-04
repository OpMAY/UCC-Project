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
    <title>[UCC] temp title</title>
<!--    <link rel="icon" href="../../../assets/images/ucc/favicon.ico">-->
<!--    <link rel="apple-touch-icon" href="../../../assets/images/ucc/favicon.ico"/>-->
    <link rel="stylesheet" href="/assets/css/owl.carousel.css">
</head>
<body>

<main style="height: 100vh;">
    <img class="logo-fixed" src="/assets/images/ucc/top-left-logo-white.png" height="41px" style="top:20px; left:20px; position: fixed; z-index: 100;">
    <div>
        <img class="logo-fixed2" data-toggle="modal" data-target="#shareModal" src="/assets/images/ucc/icon_share.svg" width="25px" style="cursor: pointer; top:20px; right:20px; position: fixed; z-index: 100;"><!-- onclick modal open -->
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
    <video playsinline muted loop autoplay style="width: 100%; height: 100vh; object-fit: cover; position: absolute; z-index: -1;">
        <source src="/assets/video/ucc_user_main_video_small.m4v" type="video/mp4">
        Sorry, your browser doesn't support embedded videos.
    </video>

    <div class="container d-flex justify-content-center h-100 align-items-end" style="z-index: 10;">
        <div class="row text-center">
            <div class="col-12 p-3">
                <div class="row flex-column flex-lg-row justify-content-center">
                    <span class="font-size-80 font-size-sm-40 font-white font-italic font-family-bm" style="color: red !important;">UCC&nbsp;</span>
                    <span class="font-size-80 font-size-sm-40 font-white font-italic font-family-bm">보물 찾기 시-작!</span>
                </div>
            </div>
            <div class="col-12 p-5">
                <span class="font-size-24 font-size-sm-16 font-white font-family-bm">댓글만 달아도 에어팟의 행운이?!</span>
            </div>
            <div class="col-12 p-3">
                <a href="#section1"><img src="/assets/images/ucc/icon_arrow_bottom.svg"></a>
            </div>
        </div>
    </div>
</main>

<section id="section1" style="overflow-x: hidden">
    <div class="container-fluid text-center p-lg-5 p-3">
        <div class="row flex-column font-size-40 font-size-sm-32 mt-lg-5 mt-3">
            <span>잃어버린</span>
            <span>아티스트를 찾아서</span>
        </div>
        <div class="row flex-column p-lg-3 p-1 font-size-sm-12">
            <span style="color: #FF004F;" class="font-family-aggro-b"><strong>2021.10.18(월) - 10.31(일)</strong></span>
        </div>
        <div class="row flex-column py-lg-3 py-1">
            <div class="row flex-column flex-lg-row justify-content-center">
                <span class="font-family-aggro-m">그동안 자극적이고 선정적인</span>
                <span class="font-family-aggro-m mb-lg-0 mb-3">콘텐츠에 지치시진 않으셨나요?</span>
            </div>
            <span class="font-family-aggro-m">UCC에서 전문성과 진정성이 담긴</span>
            <span class="font-family-aggro-m"><span style="color: red" class="font-family-aggro-b">보물 같은 아티스트</span>의 콘텐츠를 확인해 보세요!</span>
        </div>
        <div class="row justify-content-center py-5 my-3 position-relative">
            <img class="blink-image1" src="/assets/images/ucc/artist-circle-1.png">
            <img class="blink-image2" src="/assets/images/ucc/artist-circle-2.png">
            <img class="blink-image3" src="/assets/images/ucc/artist-circle-3.png">
            <img class="blink-image4" src="/assets/images/ucc/artist-circle-4.png">
            <img class="blink-image5" src="/assets/images/ucc/artist-circle-5.png">
            <img class="blink-image6" src="/assets/images/ucc/artist-circle-6.png">
            <img class="blink-image7" src="/assets/images/ucc/artist-circle-7.png">
            <img class="blink-image8" src="/assets/images/ucc/artist-circle-8.png">

            <img src="/assets/images/ucc/vod-example-mobile-screen.png" style="max-width: 328px; width: 60%;  z-index: 5;">
        </div>
        <div class="row justify-content-center">
            <div class="col-12 col-lg-4">
                <div class="row flex-column flex-lg-row p-5 text-center justify-content-center align-items-center">
                    <span class="font-family-aggro-m">UCC가 선정한 아티스트를 찾아 콘텐츠에</span>
                    <span class="font-family-aggro-m">응원의 한마디를 댓글로 남겨주시면</span>
                    <span class="font-family-aggro-m">추첨을 통해 <span class="font-family-aggro-b" style="color: red;">핫한 상품</span>을 선물로 드립니다.</span>
                </div>
            </div>
        </div>
        <div class="row flex-column flex-lg-row justify-content-center align-items-center">
            <img src="/assets/images/ucc/prize1st.png" style="max-width: 258px; width: 100%" class="m-3">
            <img src="/assets/images/ucc/prize2nd.png" style="max-width: 258px; width: 100%" class="m-3">
            <img src="/assets/images/ucc/prize3rd.png" style="max-width: 258px; width: 100%" class="m-3">
        </div>
    </div>
</section><!-- 잃어버린 아티스트를 찾아서 -->
<section id="section2" style="background: black;">
    <div class="container py-5 position-relative">
        <img class="position-absolute" style="top: 0; left: 50%; transform: translate(-50%, -50%); max-width: 375px; width: 60%" src="/assets/images/ucc/event-join-text.png">
        <div class="row flex-column flex-xl-row justify-content-center align-items-center py-5">
            <img class="m-3" src="/assets/images/ucc/event-process-1.png" style="width: 180px;">
            <img class="event-red-arrow m-3" src="/assets/images/ucc/icon-right-red-arrow.svg">
            <img class="m-3" src="/assets/images/ucc/event-process-2.png" style="width: 180px;">
            <img class="event-red-arrow m-3" src="/assets/images/ucc/icon-right-red-arrow.svg">
            <img class="m-3" src="/assets/images/ucc/event-process-3.png" style="width: 180px;">
            <img class="event-red-arrow m-3" src="/assets/images/ucc/icon-right-red-arrow.svg">
            <img class="m-3" src="/assets/images/ucc/event-process-4.png" style="width: 180px;">
        </div>
    </div>
</section><!-- 이벤트 참여 방법 -->
<section id="section3" class="p-lg-5 p-3" style="background-size: cover !important; background: url('/assets/images/ucc/backgroud-pictures.png') no-repeat center;">
    <div class="container p-3 p-lg-5">
        <div class="row justify-content-center flex-column flex-lg-row text-center font-family-aggro-b">
            <span class="font-size-40 font-size-sm-24">지금 바로&nbsp;</span>
            <span class="font-size-40 font-size-sm-24">보물 찾기에 참여하세요!</span>
        </div>
        <div class="row justify-content-center">
            <a href="https://play.google.com/store/apps/details?id=com.weart.ucc" class="col-6 pr-0">
                <div class="row justify-content-end mr-1">
                    <img src="/assets/images/ucc/google-play-button.png" width="60%" style="max-width: 220px; float: right;">
                </div>
            </a>
            <a href="https://apps.apple.com/kr/app/ucc/id1580818238" class="col-6 pl-0">
                <div class="row ml-1">
                    <img src="/assets/images/ucc/apple-store-button.png" width="60%" style="max-width: 220px; float: left;">
                </div>
            </a>
        </div>
    </div>
</section><!-- 보물찾기에 참여하세요 -->
<section id="section4" >
    <div class="container pt-lg-5 pt-3">
        <div class="row justify-content-center flex-column flex-lg-row text-center font-size-40 font-size-sm-24 p-lg-5 p-3 m-3 font-family-aggro-b">
            <span style="color: red !important;">UCC 보물&nbsp;</span>
            <span>이렇게 찾아보는 건 어떨까요?</span>
        </div>
        <div class="row flex-column flex-xl-row align-items-center">
            <div class="col-lg-4 col-12">
                <div class="row flex-column align-items-center text-center font-family-aggro-b pb-5 pb-xl-0">
                    <span>지금 가장 빛나는 재능을 가진</span>
                    <span><span style="color: red">아티스트</span>를 만나보세요!</span>
                    <img class="mt-2" src="/assets/images/ucc/mobile-screen-sample-1.png" style="max-width: 300px; width: 80%">
                </div>
            </div>
            <div class="col-lg-4 col-12">
                <div class="row flex-column align-items-center text-center font-family-aggro-b pb-5 pb-xl-0">
                    <span>아티스트의 진정성이 담긴</span>
                    <span><span style="color: red">콘텐츠</span>를 즐겨보세요!</span>
                    <img class="mt-2" src="/assets/images/ucc/mobile-screen-sample-2.png" style="max-width: 300px; width: 80%">
                </div>
            </div>
            <div class="col-lg-4 col-12">
                <div class="row flex-column align-items-center text-center font-family-aggro-b">
                    <span>아티스트의 팬이 되었다면</span>
                    <span><span style="color: red">소통</span>을 통해 유대감을 쌓아보세요!</span>
                    <img class="mt-2" src="/assets/images/ucc/mobile-screen-sample-3.png" style="max-width: 300px; width: 80%">
                </div>
            </div>
        </div>
    </div>
</section><!-- UCC 보물 이렇게 찾아보는건 -->
<section id="section5" style="background: black;">
    <div class="container pt-lg-5 pt-3">
        <div class="row font-size-40 font-size-sm-24 flex-column font-white text-center pt-4 font-white font-family-aggro-b">
            <span>“그동안 왜 몰랐지?”</span>
            <span>나만 알고 싶은 <span style="color: red !important;">UCC 아티스트</span></span>
        </div>
        <div class="row flex-column flex-lg-row font-white justify-content-center font-family-aggro-m my-3 pb-5 text-center">
            <span>장르에 제한이 없는&nbsp;</span>
            <span>다양한 분야 아티스트들을 만나보세요!</span>
        </div>
        <div class="row mt-5">
            <div class="owl-carousel owl-theme position-relative artists">
                <div class="item">
                    <div class="position-relative">
                        <img src="/assets/images/ucc/artist-1.png">
                        <div class="desc position-absolute w-100 h-100 flex-column justify-content-center align-items-center font-white p-5 text-center" style="top:0; left:0;">
                            <div class="row font-size-36 font-size-sm-24 m-3 font-family-aggro-b"><span>박성헌</span></div>
                            <div class="row font-family-aggro-b m-3"><span>연극배우</span></div>
                            <div class="row font-family-aggro-m font-size-sm-12"><span>불타는 열정을 가지고 연기하는 배우가 되겠습니다.</span></div>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="position-relative">
                        <img src="/assets/images/ucc/artist-2.png">
                        <div class="desc position-absolute w-100 h-100 flex-column justify-content-center align-items-center font-white p-5 text-center" style="top:0; left:0;">
                            <div class="row font-size-36 font-size-sm-24 m-3 font-family-aggro-b"><span>김태윤</span></div>
                            <div class="row font-family-aggro-b m-3"><span>벌룬 아트 디자이너</span></div>
                            <div class="row font-family-aggro-m font-size-sm-12"><span>풍선으로 한계가 없다는 것을 보여드리겠습니다.</span></div>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="position-relative">
                        <img src="/assets/images/ucc/artist-3.png">
                        <div class="desc position-absolute w-100 h-100 flex-column justify-content-center align-items-center font-white p-5 text-center" style="top:0; left:0;">
                            <div class="row font-size-36 font-size-sm-24 m-3 font-family-aggro-b"><span>윤진원</span></div>
                            <div class="row font-family-aggro-b m-3"><span>모델</span></div>
                            <div class="row font-family-aggro-m font-size-sm-12"><span>브랜드 'COKIE'의 최욱일 디자이너와 콜라보레이션한 모델 윤진원입니다.</span></div>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="position-relative">
                        <img src="/assets/images/ucc/artist-4.png">
                        <div class="desc position-absolute w-100 h-100 flex-column justify-content-center align-items-center font-white p-5 text-center" style="top:0; left:0;">
                            <div class="row font-size-36 font-size-sm-24 m-3 font-family-aggro-b"><span>김요한</span></div>
                            <div class="row font-family-aggro-b m-3"><span>가수</span></div>
                            <div class="row font-family-aggro-m font-size-sm-12"><span>잔잔한 청년 버스커 김요한입니다.</span></div>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="position-relative">
                        <img src="/assets/images/ucc/artist-5.png">
                        <div class="desc position-absolute w-100 h-100 flex-column justify-content-center align-items-center font-white p-5 text-center" style="top:0; left:0;">
                            <div class="row font-size-36 font-size-sm-24 m-3 font-family-aggro-b"><span>신아람</span></div>
                            <div class="row font-family-aggro-b m-3"><span>재즈 피아니스트</span></div>
                            <div class="row font-family-aggro-m font-size-sm-12"><span>저를 솔직하게 예술에 담아내는 그 날까지 연주하겠습니다.</span></div>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="position-relative">
                        <img src="/assets/images/ucc/artist-6.png">
                        <div class="desc position-absolute w-100 h-100 flex-column justify-content-center align-items-center font-white p-5 text-center" style="top:0; left:0;">
                            <div class="row font-size-36 font-size-sm-24 m-3 font-family-aggro-b"><span>길기현</span></div>
                            <div class="row font-family-aggro-b m-3"><span>웨이뮤직 대표</span></div>
                            <div class="row font-family-aggro-m font-size-sm-12"><span>웨이뮤직 및 비르투오소 소속 독일 유학파 작곡가, 피아니스트 길기현입니다.</span></div>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="position-relative">
                        <img src="/assets/images/ucc/artist-7.png">
                        <div class="desc position-absolute w-100 h-100 flex-column justify-content-center align-items-center font-white p-5 text-center" style="top:0; left:0;">
                            <div class="row font-size-36 font-size-sm-24 m-3 font-family-aggro-b"><span>영락</span></div>
                            <div class="row font-family-aggro-b m-3"><span>가수</span></div>
                            <div class="row font-family-aggro-m font-size-sm-12"><span>영원히 즐겁게 노래하는 가수가 되겠습니다.</span></div>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="position-relative">
                        <img src="/assets/images/ucc/artist-8.png">
                        <div class="desc position-absolute w-100 h-100 flex-column justify-content-center align-items-center font-white p-5 text-center" style="top:0; left:0;">
                            <div class="row font-size-36 font-size-sm-24 m-3 font-family-aggro-b"><span>육민재</span></div>
                            <div class="row font-family-aggro-b m-3"><span>프로듀서</span></div>
                            <div class="row font-family-aggro-m font-size-sm-12"><span>위로와 공감을 주는 프로듀서가 되겠습니다.</span></div>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="position-relative">
                        <img src="/assets/images/ucc/artist-9.png">
                        <div class="desc position-absolute w-100 h-100 flex-column justify-content-center align-items-center font-white p-5 text-center" style="top:0; left:0;">
                            <div class="row font-size-36 font-size-sm-24 m-3 font-family-aggro-b"><span>윤정인</span></div>
                            <div class="row font-family-aggro-b m-3"><span>배우</span></div>
                            <div class="row font-family-aggro-m font-size-sm-12"><span>배우 지망생 윤정인입니다.</span></div>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="position-relative">
                        <img src="/assets/images/ucc/artist-10.png">
                        <div class="desc position-absolute w-100 h-100 flex-column justify-content-center align-items-center font-white p-5 text-center" style="top:0; left:0;">
                            <div class="row font-size-36 font-size-sm-24 m-3 font-family-aggro-b"><span>피난</span></div>
                            <div class="row font-family-aggro-b m-3"><span>밴드</span></div>
                            <div class="row font-family-aggro-m font-size-sm-12"><span>불안한 청춘을 노래하는 밴드 피난입니다.</span></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- 그동안 왜 몰랐지? -->
<section id="section6" class="ucc-contents py-lg-5 py-3">
    <div class="container py-lg-5 py-3">
        <div class="row flex-column text-center font-size-40 font-size-sm-24 font-family-aggro-b">
            <span>“이렇게 신선한 콘텐츠가 있었다니!”</span>
            <span>나만 보고 싶은 <span style="color:red;">UCC 콘텐츠</span></span>
        </div>
        <div class="row flex-column flex-lg-row font-family-aggro-m justify-content-center text-center pb-lg-4 pb-2 pt-2 pt-lg-2">
            <span>지금 이 순간에도&nbsp;</span>
            <span>새로운 콘텐츠가 업로드되고 있어요!</span>
        </div>
        <div class="row mt-5 position-relative" style="padding-left: 10%; padding-right: 10%;">
            <div class="owl-carousel owl-theme position-relative contents">
                <div class="item">
                    <video playsinline muted loop autoplay class="carousel-item-video"
                    name="김태윤1" title="벌룬 아트 디자이너1" desc="풍선, 백호라는 이름으로 다시 태어나기까지1">
                        <source src="/assets/video/main_video_sample.mp4" type="video/mp4">
                        Sorry, your browser doesn't support embedded videos.
                    </video>
                </div>
                <div class="item">
                    <video playsinline muted loop autoplay class="carousel-item-video"
                    name="김태윤2" title="벌룬 아트 디자이너2" desc="풍선, 백호라는 이름으로 다시 태어나기까지2">
                        <source src="/assets/video/main_video_sample.mp4" type="video/mp4">
                        Sorry, your browser doesn't support embedded videos.
                    </video>
                </div>
                <div class="item">
                    <video playsinline muted loop autoplay class="carousel-item-video"
                    name="김태윤3" title="벌룬 아트 디자이너3" desc="풍선, 백호라는 이름으로 다시 태어나기까지3">
                        <source src="/assets/video/main_video_sample.mp4" type="video/mp4">
                        Sorry, your browser doesn't support embedded videos.
                    </video>
                </div>
                <div class="item">
                    <video playsinline muted loop autoplay class="carousel-item-video"
                    name="김태윤4" title="벌룬 아트 디자이너4" desc="풍선, 백호라는 이름으로 다시 태어나기까지4">
                        <source src="/assets/video/main_video_sample.mp4" type="video/mp4">
                        Sorry, your browser doesn't support embedded videos.
                    </video>
                </div>
            </div>
            <div id="info-box" class="font-white position-absolute text-center text-lg-left pt-lg-2 pt-1 pl-lg-3 pl-1 pr-lg-3 pr-2 pb-lg-4 pb-2">
                <span id="name" class="font-size-40 font-size-sm-20 font-family-aggro-b d-block">김태윤</span>
                <span id="title" class="font-family-aggro-b">벌룬 아트 디자이너</span>
            </div>
            <div class="position-absolute text-center w-100" style="left: 50%; bottom:5%; transform: translate(-50%, 0);">
                <span class="font-size-32 font-size-sm-16" id="desc">풍선, 백호라는 이름으로 다시 태어나기까지</span>
            </div>
        </div>
    </div>
</section><!-- 이렇게 신선한 콘텐츠가 있었다니 -->
<section id="section7" class="p-lg-5 p-3" style="background-size: cover!important; background: url('/assets/images/ucc/backgroud-pictures-dark.png') center;">
    <div class="container p-lg-5 p-3">
        <div class="row justify-content-center flex-column flex-lg-row text-center font-family-aggro-b">
            <span class="font-size-40 font-size-sm-24 font-white">더 많은 보물은 &nbsp;</span>
            <span class="font-size-40 font-size-sm-24 font-white">UCC에서 확인할 수 있습니다!</span>
        </div>
        <div class="row justify-content-center">
            <a href="https://play.google.com/store/apps/details?id=com.weart.ucc" class="col-6 pr-0">
                <div class="row justify-content-end mr-1">
                    <img src="/assets/images/ucc/google-play-button.png" width="60%" style="max-width: 220px; float: right;">
                </div>
            </a>
            <a href="https://apps.apple.com/kr/app/ucc/id1580818238" class="col-6 pl-0">
                <div class="row ml-1">
                    <img src="/assets/images/ucc/apple-store-button.png" width="60%" style="max-width: 220px; float: left;">
                </div>
            </a>
        </div>
    </div>
</section><!-- 더 많은 보물은 UCC에서 확인 할 수 있습니다 -->
<section id="section8" class="p-lg-5 p-3">
    <div class="container p-lg-5 p-3">
        <div class="row font-size-40 font-size-sm-16 justify-content-center text-center flex-column font-family-aggro-b">
            <span><span style="color: red">세상과 아티스트</span>를 연결하는 UCC는</span>
            <span>다양한 기업과 함께하고 있습니다</span>
        </div>
        <div class="row px-lg-4 px-2 py-lg-5 py-3">
            <img src="/assets/images/ucc/sponsors.png" width="100%">
        </div>
    </div>
</section><!-- 세상과 아티스트를 연결하는 UCC는 다양한 기업과 함께 -->
<section id="section9" class="py-lg-5 py-3 last-section" style="background: #FAFAFA;">
    <div class="container py-lg-5 py-3">
        <div class="row flex-column flex-column-reverse flex-lg-row justify-content-center align-items-center text-center">
            <div class="col-lg-6 col-12 my-4 mt-5">
                <div class="row justify-content-center">
                    <div class="tab-content">
                        <div class="tab-pane fade show active" id="artist1">
                            <div id="youtube1"></div>
                        </div>
                        <div class="tab-pane fade" id="artist2">
                            <div id="youtube2"></div>
                        </div>
                        <div class="tab-pane fade" id="artist3">
                            <div id="youtube3"></div>
                        </div>
                        <div class="tab-pane fade" id="artist4">
                            <div id="youtube4"></div>
                        </div>
                        <div class="tab-pane fade" id="artist5">
                            <div id="youtube5"></div>
                        </div>
                    </div>
                </div>
                <style>
                    .nav-tabs {
                        border: none;
                    }
                    .nav-tabs .nav-link {
                        border: none;
                    }
                    .nav-item {
                        width: 20%;
                    }
                    .nav-item a {
                        color: #89918F;
                    }
                    .nav-tabs .nav-item.show .nav-link, .nav-tabs .nav-link.active {
                        background: none;
                        color: black;
                    }
                </style>
                <div class="row pt-3">
                    <ul class="nav nav-tabs d-flex justify-content-between w-100 px-lg-5 px-3 mx-lg-3 mx-1">
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#artist1">
                                <div class="row flex-column font-size-10 font-size-sm-8 align-items-center font-family-aggro-m">
                                    <img class="mb-lg-3 mb-1" src="/assets/images/ucc/youtube-artist-1.png" width="30px">
                                    <span>재즈 피아니스트</span>
                                    <span>신아람</span>
                                </div>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#artist2">
                                <div class="row flex-column font-size-10 font-size-sm-8 align-items-center font-family-aggro-m">
                                    <img class="mb-lg-3 mb-1" src="/assets/images/ucc/youtube-artist-2.png" width="30px">
                                    <span>벌룬 아트 디자이너</span>
                                    <span>김태윤</span>
                                </div>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#artist3">
                                <div class="row flex-column font-size-10 font-size-sm-8 align-items-center font-family-aggro-m">
                                    <img class="mb-lg-3 mb-1" src="/assets/images/ucc/youtube-artist-3.png" width="30px">
                                    <span>아트플라이 대표</span>
                                    <span>이란</span>
                                </div>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#artist4">
                                <div class="row flex-column font-size-10 font-size-sm-8 align-items-center font-family-aggro-m">
                                    <img class="mb-lg-3 mb-1" src="/assets/images/ucc/youtube-artist-4.png" width="30px">
                                    <span>가수</span>
                                    <span>영락</span>
                                </div>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#artist5">
                                <div class="row flex-column font-size-10 font-size-sm-8 align-items-center font-family-aggro-m">
                                    <img class="mb-lg-3 mb-1" src="/assets/images/ucc/youtube-artist-5.png" width="30px">
                                    <span>프로듀서</span>
                                    <span>육민재</span>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-6 col-12 align-self-center">
                <div class="row justify-content-center flex-column text-center text-lg-left font-size-30 font-size-sm-16 font-family-aggro-b">
                    <span style="color: red">참여하고, 소통하고, 열려 있는 예술 문화</span>
                    <span>UCC가 만들어 나갑니다</span>
                </div>
                <div class="row justify-content-center justify-content-lg-start my-3">
                    <img class="download-btn left m-1" src="/assets/images/ucc/google-play-button.png" onclick="window.location.href='https://play.google.com/store/apps/details?id=com.weart.ucc';" style="max-width: 220px; cursor: pointer;">
                    <img class="download-btn right m-1" src="/assets/images/ucc/apple-store-button.png" onclick="window.location.href='https://apps.apple.com/kr/app/ucc/id1580818238';" style="max-width: 220px; cursor: pointer;">
                </div>
            </div>
        </div>
    </div>
</section><!-- 유튜브 영상 -->


<jsp:include page="partials/ucc-footer.jsp"/>

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>
<script src="/assets/js/owl.carousel.js"></script>
<script src="https://www.youtube.com/iframe_api"></script>
<script>
    $('.owl-carousel.contents').owlCarousel({
        loop:true,
        margin:50,
        nav:true,
        center: true,
        autoplay: false,
        onTranslated: function(event) {
            let idx = event.page.index;
            console.log(idx);
            let target = $('.owl-carousel.contents .item')[idx].querySelector('video');
            let name= target.getAttribute('name');
            let title= target.getAttribute('title');
            let desc= target.getAttribute('desc');
            $('#name').text(name);
            $('#title').text(title);
            $('#desc').text(desc);
        },
        responsive:{
            0:{
                items:1
            },
            600:{
                items:1
            },
            1000:{
                items:1
            }
        }
    });
    $('.owl-carousel.artists').owlCarousel({
        loop:true,
        margin:50,
        nav:true,
        center: true,
        dots: false,
        autoplay: false,
        responsive:{
            0:{
                items:3
            },
            600:{
                items:3
            },
            1000:{
                items:3
            }
        }
    });
    let ytb_bottom=0, video_played=false;
    $(document).ready(()=>{ // 캐러셀 좌우 버튼
       $('.owl-carousel.contents .owl-prev').html('<img src="/assets/images/ucc/icon-arrow-left.svg">');
       $('.owl-carousel.contents .owl-next').html('<img src="/assets/images/ucc/icon-arrow-right.svg">');
       $('.owl-carousel.artists .owl-prev').html('<img src="/assets/images/ucc/icon-arrow-left-white.svg">');
       $('.owl-carousel.artists .owl-next').html('<img src="/assets/images/ucc/icon-arrow-right-white.svg">');

       /* 캐러셀 이동시 텍스트 변경해주기 */
       $('.ucc-contents .owl-prev, .ucc-contents .owl-next').click(()=>{
           let target = $('.ucc-contents .owl-item.active video');
           let name = target.attr('name');
           let title = target.attr('title');
           let desc = target.attr('desc');
           $('#name').text(name);
           $('#title').text(title);
           $('#desc').text(desc);
       });


        let width = window.innerWidth;
        if (width < 1200) {
            $('.owl-carousel.contents .owl-nav').css('display', 'none');
            $('.owl-carousel.artists .owl-nav').css('display', 'none');
            $('#section5 .container').addClass('container-fluid');
            $('#section5 .container').removeClass('container');
        } else {
            $('.owl-carousel.contents .owl-nav').css('display', 'flex');
            $('.owl-carousel.artists .owl-nav').css('display', 'flex');
            $('#section5 .container-fluid').addClass('container');
            $('#section5 .container-fluid').removeClass('container-fluid');
        }

        window.addEventListener('resize', (event) => {
            let width = window.innerWidth;
            if (width < 1200) {
                $('.owl-carousel.contents .owl-nav').css('display', 'none');
                $('.owl-carousel.artists .owl-nav').css('display', 'none');
                $('#section5 .container').addClass('container-fluid');
                $('#section5 .container').removeClass('container');
            } else {
                $('.owl-carousel.contents .owl-nav').css('display', 'flex');
                $('.owl-carousel.artists .owl-nav').css('display', 'flex');
                $('#section5 .container-fluid').addClass('container');
                $('#section5 .container-fluid').removeClass('container-fluid');
            }
        });
        ytb_bottom = $('#section9 .tab-content').offset().top + $('#section9 .tab-content').height();

        /* 스크롤 이벤트 리스너 */
        $(window).scroll(function () {
            /* 로고 색상 바꾸기 */
            let height = $(document).scrollTop() + 20 + $('.logo-fixed').height();
            let ypos = [];
            let sections = $('section');
            sections.map((idx, section)=>{
                ypos.push(section.offsetTop);
            });
            if (height < ypos[0] ||
                (ypos[1] < height && height < ypos[2]) ||
                (ypos[4] < height && height < ypos[5]) ||
                (ypos[6] < height && height < ypos[7])
            ) {
                $('main .logo-fixed, main .logo-fixed2').removeClass('dark-logo');
            } else {
                $('main .logo-fixed, main .logo-fixed2').addClass('dark-logo');
            }

            /* 영상 위치 도달시 영상 재생 */
            let bottom_pos = $(document).scrollTop() + window.innerHeight;
            if (bottom_pos >= ytb_bottom && !video_played) {
                players[0].playVideo();
                setTimeout(()=>{video_played = true;}, 1000);
            }
        });
    });


    $('a[href^="#section1"]').click((e) => {
        e.preventDefault();
        let dest = $('#section1').offset().top;

        $('html, body').animate({
            scrollTop: dest
        }, 700, 'swing');
    });

    /* 유튜브 영상 불러오기 */
    let players = [];
    function onYouTubeIframeAPIReady() {
        let youtube_video_container_ids = ['youtube1', 'youtube2', 'youtube3', 'youtube4', 'youtube5' ];
        let youtube_video_ids = ['kT36llSWpU8', '5JC_ahlp4sI', '9Lcia-w_dTE', 'OXEJGpvGd6E', 'tixv_DAFVCo' ];
        for (let i=0; i<youtube_video_container_ids.length; i++) {
            player = new YT.Player(youtube_video_container_ids[i], {
                videoId: youtube_video_ids[i],
                mute: 1,
                events: {
                    'onReady': onPlayerReady,
                    'onStateChange': onPlayerStateChange
                }
            });
            players.push(player);
        }
    }

    function onPlayerReady(event) {
    }
    /* 비디오 종료시 다음 비디오 재생 */
    function onPlayerStateChange(event) {
        if (event.data == YT.PlayerState.ENDED) {
            let current = $('.last-section .nav .nav-item').index($('.last-section .nav .nav-item .nav-link.active').parent());
            $('.last-section .nav .nav-item')[current+1].querySelector('img').click();
        }
    }

    /* 유튜브 비디오 탭 클릭시 재생 */
    $('.last-section .nav .nav-item').click((e)=>{
        let target = e.currentTarget;
        let index = $('.last-section .nav .nav-item').index(target);

        for (let i=0; i<5; i++) {
            players[i].stopVideo();
        }
        players[index].playVideo();
    });

    /* 우측상단 링크 복사 */
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
</script>
</body>
</html>
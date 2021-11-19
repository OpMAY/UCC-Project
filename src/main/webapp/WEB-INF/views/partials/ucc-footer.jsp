<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>

    amplitude.getInstance().init("427bac641c699781882efcfe2f53be13");

    /*Amplitude Page View*/
    var event = 'page-views2';

    var google_download_clicked = 'google-download-clicked';  // Google Play Store
    var ios_download_clicked = 'ios-download-clicked';  // ios App Store
    var instagram_clicked = 'instagram-clicked';
    var youtube_clicked = 'youtube-clicked';


    function run_google_download_clicked() {
        amplitude.getInstance().logEvent(google_download_clicked);
        window.location.href='https://play.google.com/store/apps/details?id=com.weart.ucc';
    }

    function run_ios_download_clicked() {
        amplitude.getInstance().logEvent(ios_download_clicked);
        window.location.href='https://apps.apple.com/kr/app/ucc/id1580818238';
    }

    function run_instagram_download_clicked() {
        amplitude.getInstance().logEvent(instagram_clicked);
        window.location.href='https://www.instagram.com/we_art_collection/?hl=ko';
    }

    function run_youtube_download_clicked() {
        amplitude.getInstance().logEvent(youtube_clicked);
        window.location.href='https://www.youtube.com/channel/UCZhr-cQ1gPZBLr2djJNDNKg';
    }


</script>




<footer style="background: #020101;">
    <div class="container-fluid p-5">
        <div class="row font-white">
            <div class="col-12 col-lg-6">
                <div class="row pb-lg-5 pb-3">
                    <img src="/assets/images/ucc/footer-logo.svg" class="logo">
                </div>
                <div class="row font-size-20 font-size-sm-10 pb-3" style="gap: 5%">
                    <span>대표자 이성진</span>
                    <span>위아트 주식회사</span>
                    <span>사업자 등록 번호 714-86-02038</span>
                </div>
                <div class="row font-size-20 font-size-sm-10 pb-3"><span>세종특별자치시 나성동 758 노블비즈니스타운 1동 718호</span></div>
                <div class="row font-size-20 font-size-sm-10 pb-3"><span>pageofweart@gmail.com</span></div>
<%--                <div class="row font-size-sm-10" style="gap: 5%">--%>
<%--                    <a href="#" style="color: white;">서비스 이용약관</a>--%>
<%--                    <a href="#" class="privacy"><strong>개인정보 처리방침</strong></a>--%>
<%--                </div>--%>
            </div>
            <div class="col-12 col-lg-3 d-flex flex-column justify-content-end">
                <div class="row"><span class="font-size-42 font-size-sm-20"><strong>070-4906-1022</strong></span></div>
                <div class="row font-size-20 font-size-sm-10" style="gap: 3%">
                    <span>월-금 09:00 - 18:00</span>
                    <span>주말, 공휴일 휴무</span>
                </div>
            </div>
            <div class="col-12 col-lg-3 d-flex flex-column justify-content-between">
                <div class="row justify-content-start justify-content-md-end mt-3 align-items-center" style="gap: 5%; margin-right: 5px;">
                    <a href="javascript:run_youtube_download_clicked();">
                        <img class="media1" src="/assets/images/ucc/footer-youtube.svg"></a>
                    <a href="javascript:run_instagram_download_clicked();">
                        <img class="media2" src="/assets/images/ucc/footer-instagram.svg"></a>
                </div>
                <div class="row justify-content-start justify-content-md-end mt-3">
                    <div class="col-4 p-0">
                        <a href="javascript:run_google_download_clicked();">
                            <img src="/assets/images/ucc/google-play-button.png" width="100%"></a>
                    </div>
                    <div class="col-4 p-0">
                        <a href="javascript:run_ios_download_clicked();">
                            <img src="/assets/images/ucc/apple-store-button.png" width="100%"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>

<script>



</script>
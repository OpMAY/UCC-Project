$(function () {
    'use strict';

  $.fn.datepicker.dates['en'] = {
    days: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
    daysShort: ['일','월','화','수','목','금','토'],
    daysMin: ['일','월','화','수','목','금','토'],
    months:  ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
    monthsShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
    today: "Today",
    clear: "Clear",
    format: "yyyy-mm-dd",
    titleFormat: "yyyy년 mm월", /* Leverages same syntax as 'format' */
    weekStart: 0
  };

    /*if ($('#ban-start-date-text1').length) {
        console.log("in");
        var date = new Date();
        var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
        $('#ban-start-date-text1').datepicker({
            format: "yyyy-mm-dd",
            todayHighlight: true,
            autoclose: true
        });
        $('.ban-date-after').datepicker({
            format: "yyyy-mm-dd",
            todayHighlight: true,
            autoclose: true
        });
        $('#ban-start-date-text1').datepicker('setDate', today);


    }*/

    //$('.ban-date').datepicker();

    var date = new Date();
    var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
    $('.ban-date').datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true,
        autoclose: true
    });
    $('.start-date').datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true,
        autoclose: true
    });
    /*$('.ban-date').on('changeDate', function () {
        $('#ban-end-date-text1').val(
            $('#ban-start-date-text1').datepicker('getFormattedDate')
        )

    });*/
});
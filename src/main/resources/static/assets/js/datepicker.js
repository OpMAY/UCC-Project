$(function () {
    'use strict';

    $.fn.datepicker.dates['en'] = {
        days: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
        daysShort: ['일', '월', '화', '수', '목', '금', '토'],
        daysMin: ['일', '월', '화', '수', '목', '금', '토'],
        months: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthsShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
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
    $('#ban-start-date').datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true,
        autoclose: true,
    });
    $('#ban-start-date').datepicker('setDate', today);

    $('#index-date').datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true,
        endDate: today,
        autoclose: true,
    });
    $('#index-date').datepicker('setDate', today);

    $('#dp-loudsourcing-start-date').datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true,
        title: "시작일자",
        startDate: '0d',
        autoclose: true
    }).on("changeDate", function(selectedDate){
        $('#dp-loudsourcing-end-date').datepicker("setStartDate", nextDay(selectedDate.date));
        $('#dp-loudsourcing-judge-start-date').datepicker("setStartDate", nextDay(selectedDate.date));
        $('#dp-loudsourcing-process-end-date').datepicker("setStartDate", nextDay(selectedDate.date));
        $('#dp-loudsourcing-process-start-date').datepicker("setStartDate", nextDay(selectedDate.date));
        $('#dp-loudsourcing-recruitment-end-date').datepicker("setStartDate", nextDay(selectedDate.date));
    });

    $('#dp-loudsourcing-end-date').datepicker({
        format: "yyyy-mm-dd",
        title: "종료일자",
        autoclose: true
    }).on("changeDate", function(selectedDate){
        $('#dp-loudsourcing-start-date').datepicker("setEndDate", selectedDate.date);
        $('#dp-loudsourcing-process-end-date').datepicker("setEndDate", selectedDate.date);
        $('#dp-loudsourcing-process-start-date').datepicker("setEndDate", selectedDate.date);
        $('#dp-loudsourcing-recruitment-end-date').datepicker("setEndDate", selectedDate.date);
        $('#dp-loudsourcing-judge-start-date').datepicker("setEndDate", selectedDate.date);
    });

    $('#dp-loudsourcing-recruitment-end-date').datepicker({
        format: "yyyy-mm-dd",
        title: "모집 종료일자",
        autoclose: true
    }).on("changeDate", function(selectedDate){
        $('#dp-loudsourcing-process-start-date').datepicker("setStartDate", selectedDate.date);
        $('#dp-loudsourcing-process-end-date').datepicker("setStartDate", selectedDate.date);
        $('#dp-loudsourcing-judge-start-date').datepicker("setStartDate", selectedDate.date);
        $('#dp-loudsourcing-end-date').datepicker("setStartDate", selectedDate.date);
    });

    $('#dp-loudsourcing-process-start-date').datepicker({
        format: "yyyy-mm-dd",
        title: "진행 시작일자",
        autoclose: true
    }).on("changeDate", function (selectedDate) {
        $('#dp-loudsourcing-process-end-date').datepicker("setStartDate", selectedDate.date);
        $('#dp-loudsourcing-judge-start-date').datepicker("setStartDate", selectedDate.date);
        $('#dp-loudsourcing-end-date').datepicker("setStartDate", selectedDate.date);
    });
    $('#dp-loudsourcing-process-end-date').datepicker({
        format: "yyyy-mm-dd",
        title: "진행 종료일자",
        autoclose: true
    }).on("changeDate", function (selectedDate) {
        $('#dp-loudsourcing-judge-start-date').datepicker("setStartDate", selectedDate.date);
        $('#dp-loudsourcing-end-date').datepicker("setStartDate", selectedDate.date);
    });
    $('#dp-loudsourcing-judge-start-date').datepicker({
        format: "yyyy-mm-dd",
        title: "심사 일자",
        autoclose: true
    }).on("changeDate", function (selectedDate) {
        $('#dp-loudsourcing-end-date').datepicker("setStartDate", selectedDate.date);
    });

    $('#dp-edit-loudsourcing-start-date').datepicker({
        format: "yyyy-mm-dd",
        todayHighlight: true,
        title: "시작일자",
        startDate: '0d',
        autoclose: true
    }).on("changeDate", function(selectedDate){
        $('#dp-edit-loudsourcing-end-date').datepicker("setStartDate", nextDay(selectedDate.date));
        $('#dp-edit-loudsourcing-judge-start-date').datepicker("setStartDate", nextDay(selectedDate.date));
        $('#dp-edit-loudsourcing-process-end-date').datepicker("setStartDate", nextDay(selectedDate.date));
        $('#dp-edit-loudsourcing-process-start-date').datepicker("setStartDate", nextDay(selectedDate.date));
        $('#dp-edit-loudsourcing-recruitment-end-date').datepicker("setStartDate", nextDay(selectedDate.date));
    });

    let start_date = "";
    if($('input[name=loudsourcing-start-date]').val() != null){
        start_date = $('input[name=loudsourcing-start-date]').val();
    } else {
        start_date = $("#start-date").val();
    }
    console.log("start_date : " + start_date);

    $('#dp-edit-loudsourcing-end-date').datepicker({
        format: "yyyy-mm-dd",
        title: "종료일자",
        autoclose: true,
        startDate : start_date,
    }).on("changeDate", function(selectedDate){
        $('#dp-edit-loudsourcing-start-date').datepicker("setEndDate", prevDay(selectedDate.date));
        $('#dp-edit-loudsourcing-process-end-date').datepicker("setEndDate", prevDay(selectedDate.date));
        $('#dp-edit-loudsourcing-process-start-date').datepicker("setEndDate", prevDay(selectedDate.date));
        $('#dp-edit-loudsourcing-recruitment-end-date').datepicker("setEndDate", prevDay(selectedDate.date));
        $('#dp-edit-loudsourcing-judge-start-date').datepicker("setEndDate", prevDay(selectedDate.date));
    });

    let end_date = $('input[name=loudsourcing-end-date]').val();
    console.log("end_date : " + end_date);

    $('#dp-edit-loudsourcing-recruitment-end-date').datepicker({
        format: "yyyy-mm-dd",
        title: "모집 종료일자",
        startDate: start_date,
        endDate: end_date,
        autoclose: true
    }).on("changeDate", function(selectedDate){
        $('#dp-edit-loudsourcing-process-start-date').datepicker("setStartDate", nextDay(selectedDate.date));
        $('#dp-edit-loudsourcing-process-end-date').datepicker("setStartDate", nextDay(selectedDate.date));
        $('#dp-edit-loudsourcing-judge-start-date').datepicker("setStartDate", nextDay(selectedDate.date));
        $('#dp-edit-loudsourcing-end-date').datepicker("setStartDate", nextDay(selectedDate.date));
    });

    $('#dp-edit-loudsourcing-process-start-date').datepicker({
        format: "yyyy-mm-dd",
        title: "진행 시작일자",
        startDate: start_date,
        endDate: end_date,
        autoclose: true
    }).on("changeDate", function (selectedDate) {
        $('#dp-edit-loudsourcing-process-end-date').datepicker("setStartDate", selectedDate.date);
        $('#dp-edit-loudsourcing-judge-start-date').datepicker("setStartDate", selectedDate.date);
        $('#dp-edit-loudsourcing-end-date').datepicker("setStartDate", selectedDate.date);
    });
    $('#dp-edit-loudsourcing-process-end-date').datepicker({
        format: "yyyy-mm-dd",
        title: "진행 종료일자",
        startDate: start_date,
        endDate: end_date,
        autoclose: true
    }).on("changeDate", function (selectedDate) {
        $('#dp-edit-loudsourcing-judge-start-date').datepicker("setStartDate", selectedDate.date);
        $('#dp-edit-loudsourcing-end-date').datepicker("setStartDate", selectedDate.date);
    });
    $('#dp-edit-loudsourcing-judge-start-date').datepicker({
        format: "yyyy-mm-dd",
        title: "심사 일자",
        startDate: start_date,
        endDate: end_date,
        autoclose: true
    }).on("changeDate", function (selectedDate) {
        $('#dp-edit-loudsourcing-end-date').datepicker("setStartDate", selectedDate.date);
    });

    function nextDay(date) {
        let number = date.getDate() + 1;
        return new Date(date.getFullYear(), date.getMonth(), number);
    }

    function prevDay(date) {
        let number = date.getDate() - 1;
        return new Date(date.getFullYear(), date.getMonth(), number);
    }

    /*$('.ban-date').on('changeDate', function () {
        $('#ban-end-date-text1').val(
            $('#ban-start-date-text1').datepicker('getFormattedDate')
        )

    });*/
});
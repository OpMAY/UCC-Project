$(function() {
  'use strict';

  $(function() {
    $('#dataTableExample').DataTable({
      "aLengthMenu": [
        [10, 20, 30, 50, -1],
        [10, 20, 30, 50, "모두"]
      ],
      "iDisplayLength": 20,
      "language": {
        "decimal" : "",
        "emptyTable" : "데이터가 없습니다.",
        "info" : "_START_ 번 부터 _END_ 번까지 표시 중 (총 데이터 _TOTAL_ 개)",
        "infoEmpty" : "총 데이터 0개",
        "infoFiltered" : "(전체 _MAX_ 명 중 검색결과)",
        "infoPostFix" : "",
        "thousands" : ",",
        "lengthMenu" : "_MENU_ 개씩 보기",
        "loadingRecords" : "로딩중...",
        "processing" : "처리중...",
        "search" : "검색  ",
        "zeroRecords" : "검색된 데이터가 없습니다.",
        "paginate" : {
          "first" : "첫 페이지",
          "last" : "마지막 페이지",
          "next" : "다음",
          "previous" : "이전"
        },
        "aria" : {
          "sortAscending" : " :  오름차순 정렬",
          "sortDescending" : " :  내림차순 정렬"
        }
      }
    });
    $('#dataTableExample').each(function() {
      let datatable = $(this);
      // SEARCH - Add the placeholder for Search and Turn this into in-line form control
      let search_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] input');
      search_input.attr('placeholder', 'Search');
      search_input.removeClass('form-control-sm');
      // LENGTH - Inline-Form control
      let length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_length] select');
      length_sel.removeClass('form-control-sm');
    });


  });

});
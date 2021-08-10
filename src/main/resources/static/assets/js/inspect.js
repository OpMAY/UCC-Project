/**
 * <form method="POST" action="/handler.do" onsubmit="return checkValue()"></form>
 * checkValue(){
 *    return inspection('input id','inspect_id') && inspection('input id','inspect_pass');
 * }
 *
 * */

function matchInspection(inputId, matchInputId, message) {
    let input_value = document.getElementById(inputId).value;
    let match_input_value = document.getElementById(matchInputId).value;
    if (input_value === match_input_value) {
        return true;
    }
    matchInputId.value = '';
    document.getElementById(matchInputId).focus();
    alert(message);

    return false;
}

function inspection(inputId, what) {
    const inspect_id = /^[a-zA-Z0-9]{4,12}$/; // 아이디와 패스워드가 적합한지 검사할 정규식
    const inspect_pass = /^(?=.*[a-z])(?=.*[0-9])[0-9A-Za-z$&+,:;=?@#|'<>.^*()%!-]{8,16}$/;
    const inspect_email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    const inspect_phone = /^\d{2,3}-\d{3,4}-\d{4}$/;
    const inspect_search = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*]+$/;
    const inspect_name = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*]{2,16}$/;
    const inspect_title = /^.{1,30}/g;
    const inspect_birthday = /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
    const inspect_number = /^[0-9]/g;
    const inspect_money = /^\d+(?:[.]?[\d]?[\d])?$/;
    const inspect_price = /^(?:[1-9]\d{0,})(0)(0)/gi;
    const inspect_company_id = /^[0-9|\*]{10}$/; // 사업자 등록 번호, 10자리의 숫자
    const inspect_bank_name = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*]{2,16}$/; //은행 명
    const inspect_account_owner = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*]{2,16}$/; // 예금주 명
    const inspect_account_number = /^[0-9]/g; // 계좌번호
    const inspect_sale_rate = /^[0-9]{1,2}$/g; // 할인율 0~99

    const inspect_loudsourcing_name = /^.{2,30}$/;
    const inspect_host_name = /^.{2,30}$/;
    const reward = /^\d+(?:[.]?[\d]?[\d])?$/;;
    const total_recruit_number = /^[0-9]/g;
    const loudsourcing_content = /^.{10,2000}$/;
    const warning = /^.{10,2000}$/;



    const inspect_text30 = /^.{1,30}/g;
    const inspect_text60 = /^.{1,60}/g;
    const inspect_text250 = /^.{1,250}/g;

    switch (what) {
        case 'name':
            let id = document.getElementById(inputId);
            //이름은 2~16자의 영문,한글과 숫자로만 입력하셔야 합니다.
            if (!check(inspect_name, id, "이름은 띄어쓰기 없이 2~16글자의 영문, 한글과 숫자로만 입력하셔야 합니다.")) {
                return false;
            }
            break;
        case 'id':
            id = document.getElementById(inputId);
            //아이디는 4~12자의 영문 대소문자와 숫자로만 입력
            if (!check(inspect_id, id, "아이디는 4~12자의 영문 대소문자와 숫자로만 입력 가능합니다.")) {
                return false;
            }
            break;
        case 'password':
            let pw = document.getElementById(inputId);
            //8자 이상 16자 이하, 소문자에 숫자하나 필수지만 대문자나 특수문자가 들어갈 수 있는 비밀번호
            if (!check(inspect_pass, pw, "8자리 이상 16자 이하, 숫자가 반드시 필요합니다.")) {
                return false;
            }
            break;
        case 'email':
            let email = document.getElementById(inputId);
            if (!check(inspect_email, email, "이메일 형식이 아닙니다.")) {
                return false;
            }
            break;
        case 'phone':
            let phone = document.getElementById(inputId);
            if (!check(inspect_phone, phone, "휴대폰 번호를 예시와 같이 입력해주세요.\nEx) 000-0000-0000")) {
                return false;
            }
            break;
        case 'search':
            let search = document.getElementById(inputId);
            if (!check(inspect_search, search, "영어, 한글, 숫자만 입력 가능합니다.")) {
                return false;
            }
            break;
        case 'birthday':
            let birthday = document.getElementById(inputId);
            if (!check(inspect_birthday, birthday, "Invalid date of birth format. ex) 2000-01-01")) {
                return false;
            }
            break;
        case 'number':
            let number = document.getElementById(inputId);
            if (!check(inspect_number, number, "숫자만 입력해주세요.")) {
                return false;
            }
            break;
        case 'money':
            let money = document.getElementById(inputId);
            if (!check(inspect_money, money, "You have entered the wrong amount of money. (only an integer and two decimal places can be entered)")) {
                return false;
            }
            break;
        case 'title':
            let title = document.getElementById(inputId);
            if (!check(inspect_title, title, "이름은 30자 이하로만 입력 가능합니다.")) {
                return false;
            }
            break;
        case 'text30':
            let text_30 = document.getElementById(inputId);
            if (!check(inspect_text30, text_30, "30자 이상 입력하실 수 없습니다.")) {
                return false;
            }
            break;
        case 'text60':
            let text_60 = document.getElementById(inputId);
            if (!check(inspect_text60, text_60, "60자 이상 입력하실 수 없습니다.")) {
                return false;
            }
            break;
        case 'text250':
            let text_250 = document.getElementById(inputId);
            if (!check(inspect_text250, text_250, "250자 이상 입력하실 수 없습니다.")) {
                return false;
            }
            break;
        case 'textArea':
            let des_text_250 = document.getElementById(inputId);
            if (!check(inspect_text250, des_text_250, "You must enter no more than 250 characters.")) {
                return false;
            }
            break;
        case 'file':
            id = document.getElementById(inputId);
            if (id.value === '') {
                alert('파일을 첨부해주세요.');
                return false;
            }
            break;
        case 'price':
            id = document.getElementById(inputId);
            if (!check(inspect_price, id, "가격은 100원 단위로 입력가능합니다.")) {
                return false;
            }
            break;
        case 'content':
            id = document.getElementById(inputId);
            if (id.value === '') {
                alert("내용을 입력해주세요");
                return false;
            }
            break;
        case 'company_id':
            id = document.getElementById(inputId);
            if (!check(inspect_company_id, id, "10자리의 사업자등록번호를 입력해주세요.")) {
                return false;
            }
            break;
        case 'address':
            id = document.getElementById(inputId);
            if (id.value === '') {
                alert("주소를 입력해주세요");
                return false;
            }
            break;
        case 'address_detail':
            id = document.getElementById(inputId);
            if (id.value === '') {
                alert("상세주소를 입력해주세요");
                return false;
            }
            break;
        case 'bank_name':
            id = document.getElementById(inputId);
            if (!check(inspect_bank_name, id, "은행명은 2~16글자의 영문, 한글과 숫자로만 입력 해 주세요.")) {
                return false;
            }
            break;
        case 'account_owner':
            id = document.getElementById(inputId);
            if (!check(inspect_account_owner, id, "예금주 명은 2~16글자의 영문, 한글과 숫자로만 입력 해 주세요.")) {
                return false;
            }
            break;
        case 'account_number':
            id = document.getElementById(inputId);
            if (!check(inspect_account_number, id, "계좌번호는 숫자만 입력 해 주세요.")) {
                return false;
            }
            break;
        case 'sale_rate':
            id = document.getElementById(inputId);
            if (!check(inspect_sale_rate, id, "할인율은 0~99의 숫자만 가능합니다.")) {
                return false;
            }
            break;
        case 'loudsourcing_name':
            let loudsourcing_name = document.getElementById(inputId);
            if(!check(inspect_loudsourcing_name, loudsourcing_name, "크라우드 이름은 2~30자로 입력해주세요.")){
                return false;
            }
            break;
        case 'host_name':
            let host_name = document.getElementById(inputId);
            if(!check(inspect_host_name, host_name, "주최자 명칭은 2~30자로 입력해주세요.")){
                return false;
            }
            break;
        case 'reward':
            let rewards = document.getElementById(inputId);
            if(!check(reward, rewards, "올바른 금액을 입력해주세요.")){
                return false;
            }
            break;
        case 'total_recruit_number':
            let r_number = document.getElementById(inputId);
            if(!check(total_recruit_number, r_number, "상금 금액은 숫자만 입력가능합니다.")){
                return false;
            }
            break;
        case 'loudsourcing_content':
            let l_content = document.getElementById(inputId);
            if(!check(loudsourcing_content, l_content, "출품 안내는 최소 10자, 최대 2000자까지 입력해주세요.")){
                return false;
            }
            break;
        case 'warning':
            let l_warning = document.getElementById(inputId);
            if(!check(warning, l_warning, "주의 사항은 최소 10자, 최대 2000자까지 입력해주세요.")){
                return false;
            }
            break;
    }
    return true;
}

function check(inspect, what, message) {
    what.value = what.value.trim();
    if (what.value.length === 0) {
        alert('올바른 값이 없습니다. 다시 입력해주세요.');
        what.value = '';
        what.focus();
        return false;
    }
    if (inspect.test(what.value)) {
        return true;
    }

    alert(message);
    what.value = '';
    what.focus();
    return false;
}

function Enter_insert(inputID) {
    alert(inputID.text());
}
jQuery.validator.addMethod("jumin", function (value, element) {
    var sum = 0;
    if (this.optional(element) || value.match(/\d{2}[0-1]\d{1}[0-3]\d{1}\d{7}/)) {

        if (value.substr(6, 1) >= 5 && value.substr(6, 1) <= 8) { //외국인
            if (Number(value.substr(7, 2)) % 2 != 0) return false;
            for (var i = 0; i < 12; i++) {
                sum += Number(value.substr(i, 1)) * ((i % 8) + 2);
            }
            if ((((11 - (sum % 11)) % 10 + 2) % 10) == Number(value.substr(12, 1))) 
                return true;
            return false;

        } else { //내국인
            for (var i = 0; i < 12; i++) {
                sum += Number(value.substr(i, 1)) * ((i % 8) + 2);
            }
            if (((11 - (sum % 11)) % 10) == Number(value.substr(12, 1))) 
                return true;
            return false;
        }
    } else {
        return false;
    }
}, "주민등록번호가 정확하지 않습니다.");

jQuery.validator.addMethod("engNumber", function (value, element) {
    return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
}, "영문과 숫자만 입력가능합니다.");

jQuery.validator.addMethod("dateCustom", function (value, element) {
    return this.optional(element) || !/Invalid|NaN/.test(new Date(value.substring(0,4)+"/"+value.substring(4,6)+"/"+value.substring(6,8)));
}, "날짜형식은 yyyy.MM.dd 입니다.");

jQuery.validator.addMethod("phone", function (value, element) {
    return this.optional(element) || /^[0-9-]+$/.test(value);
}, "전화번호는 숫자와 -만 입력됩니다.");

jQuery.validator.addMethod("korEng", function (value, element) {
    return this.optional(element) || /^[가-힣a-zA-Z]+$/.test(value);
}, "한글과 영문만 입력가능합니다.");

jQuery.validator.addMethod("areaPhone1", function (value, element) {
    return this.optional(element) || (/(02|0[3-6]{1}[0-9]{1})$/).test(value);
}, "정확한 지역번호를 입력해주세요");

jQuery.validator.addMethod("cellPhone1", function (value, element) {
    return this.optional(element) || (/01[016789]$/).test(value);
}, "정확한 핸드폰 번호를 입력해주세요");

jQuery.validator.addMethod("maxbyte", function (value, element, param) {
    return this.optional(element) || this.getByte(value) <= param;
}, $.validator.format("{0}byte 이하로 입력해주세요."));

jQuery.validator.addMethod("minbyte", function (value, element, param) {
    return this.optional(element) || this.getByte(value) >= param;
}, $.validator.format("{0}byte 이상 입력해주세요."));

jQuery.validator.addMethod("rangebyte", function (value, element, param) {
	var bytesize = this.getByte(value) ;
    return this.optional(element) || ( bytesize >= param[0] && bytesize <= param[1] );
}, $.validator.format("{0}byte 이상 {1}byte 이하로 입력해주세요."));

jQuery.validator.addMethod('telNumber', function (value, element, param) {
    //Your Validation Here
	var isValid = true;
	if (value != "") {
		$(element).siblings('input').each(function(){
			if ($(this).val() === "") {
				isValid = false;
			}
		});
	}
    return this.optional(element) || isValid; // return bool here if valid or not.
}, '전화번호를 제대로 입력해주세요');

// require moment.js
jQuery.validator.addMethod("dateFormat", function (value, element, param) {
	var isValid = false;
	
	if(typeof param === "string"){
		// check valid date value
		isValid = moment(value, param, false).isValid();
		
		// check valid format
		isValid = isValid && moment(value, param, true).isValid();
	}
	
    return this.optional(element) || isValid;
}, "invalid date format");

jQuery.validator.addMethod("matchSum", function (value, element, param) {
	
	var sumValue = 0;
	var limitValue = Number(param[1]);
	
	$("[name='"+param[0]+"']:visible").each(function() {
		sumValue += Number($(this).val());
	});
	
    return this.optional(element) || limitValue === sumValue;
}, $.validator.format("합은 {0}이 되어야합니다."));

jQuery.validator.addMethod("urlFormat", function (value, element) {
	var matcher = /(^|\s)((https?:\/\/)?[\w-]+(\.[\w-]+)+\.?(:\d+)?(\/\S*)?)/gi;
	
    return this.optional(element) || value.match(matcher);
}, "invalid url format");
jQuery.validator.setDefaults({
	ignoreTitle: true,
	onkeyup:false,
	onclick:false,
	onfocusout:false,
	showErrors:function(errorMap, errorList){
		if(this.numberOfInvalids()) { // \uc5d0\ub7ec\uac00 \uc788\uc744 \ub54c\ub9cc..
			alert("'"+errorList[0].element.title+"' "+errorList[0].message);
			 $(errorList[0].element).focus();
// 				alert('[' + caption + ']' + errorList[0].message);
        }
	}
}); 


$.extend($.validator.messages, {
	required: "\ud544\uc218 \ud56d\ubaa9\uc785\ub2c8\ub2e4.",
	remote: "\ud56d\ubaa9\uc744 \uc218\uc815\ud558\uc138\uc694.",
	email: "\uc720\ud6a8\ud558\uc9c0 \uc54a\uc740 E-Mail\uc8fc\uc18c\uc785\ub2c8\ub2e4.",
	url: "\uc720\ud6a8\ud558\uc9c0 \uc54a\uc740 URL\uc785\ub2c8\ub2e4.",
	date: "\uc62c\ubc14\ub978 \ub0a0\uc9dc\ub97c \uc785\ub825\ud558\uc138\uc694.",
	dateISO: "\uc62c\ubc14\ub978 \ub0a0\uc9dc(ISO)\ub97c \uc785\ub825\ud558\uc138\uc694.",
	number: "\uc720\ud6a8\ud55c \uc22b\uc790\uac00 \uc544\ub2d9\ub2c8\ub2e4.",
	digits: "\uc22b\uc790\ub9cc \uc785\ub825 \uac00\ub2a5\ud569\ub2c8\ub2e4.",
	creditcard: "\uc2e0\uc6a9\uce74\ub4dc \ubc88\ud638\uac00 \ubc14\ub974\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4.",
	equalTo: "\uac19\uc740 \uac12\uc744 \ub2e4\uc2dc \uc785\ub825\ud558\uc138\uc694.",
	extension: "\uc62c\ubc14\ub978 \ud655\uc7a5\uc790\uac00 \uc544\ub2d9\ub2c8\ub2e4.",
	maxlength: $.validator.format("{0}\uc790\ub97c \ub118\uc744 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4. "),
	minlength: $.validator.format("{0}\uc790 \uc774\uc0c1 \uc785\ub825\ud558\uc138\uc694."),
	rangelength: $.validator.format("\ubb38\uc790 \uae38\uc774\uac00 {0} \uc5d0\uc11c {1} \uc0ac\uc774\uc758 \uac12\uc744 \uc785\ub825\ud558\uc138\uc694."),
	range: $.validator.format("{0} \uc5d0\uc11c {1} \uc0ac\uc774\uc758 \uac12\uc744 \uc785\ub825\ud558\uc138\uc694."),
	max: $.validator.format("{0} \uc774\ud558\uc758 \uac12\uc744 \uc785\ub825\ud558\uc138\uc694."),
	min: $.validator.format("{0} \uc774\uc0c1\uc758 \uac12\uc744 \uc785\ub825\ud558\uc138\uc694.")
});

$.validator.addMethod("numberhyphen", function(value, element) {
	return this.optional(element) || /^[0-9\-\s]+$/i.test(value);
}, "\uc22b\uc790\uc640 \ud558\uc774\ud508\ub9cc \uc785\ub825 \uac00\ub2a5\ud569\ub2c8\ub2e4.");
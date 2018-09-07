function fnUserIdRole(objName) {
	var obj = $("#"+objName);
    var alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    var number = "1234567890";
    var sChar = "-_=+\|()*&^%$#@!~`?></;,.:'";    
    var sChar_Count = 0;
    var alphaCheck = false;
    var numberCheck = false; 
    var id = obj.val();
    var retTxt = "";
    if(5 <= id.length && id.length <= 15){
        if(alpha.indexOf(id.charAt(0)) == -1){
            return retTxt = "아이디 첫자는 영문자로 해주세요.";
        }
        
        for(var i=0; i<id.length; i++){
            if(sChar.indexOf(id.charAt(i)) != -1){
                sChar_Count++;
            }
            if(alpha.indexOf(id.charAt(i)) != -1){
                alphaCheck = true;
            }
            if(number.indexOf(id.charAt(i)) != -1){
                numberCheck = true;
            }
        }
        if(sChar_Count > 0 || alphaCheck != true || numberCheck != true){
            return  retTxt = "아이디는 5~15자 영문,숫자로 조합해주세요.";
        } 
        var regId = /^[A-Za-z0-9]{5,15}$/;
        if(!regId.test(id)) {
            return  retTxt = "아이디는 5~15자 영문,숫자로 조합해주세요.";
        }
    }else{
    	retTxt = "아이디는 5~15자 영문,숫자로 조합해주세요.";
        return retTxt
    }
    return retTxt;
}

function fnUserPwdRole(objName) {
	var obj = $("#"+objName);
    var alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    var number = "1234567890";
    var sChar = " !@$%^&*#";
    var sChar_Count = 0;
    var alphaCheck = false;
    var numberCheck = false;
    var returnTxt = "";
    var pw = obj.val();
    if(9 <= pw.length && pw.length <= 15){
        for(var i=0; i<pw.length; i++){
            if(sChar.indexOf(pw.charAt(i)) != -1){
                sChar_Count++;
            }
            if(alpha.indexOf(pw.charAt(i)) != -1){
                alphaCheck = true;
            }
            if(number.indexOf(pw.charAt(i)) != -1){
                numberCheck = true;
            }
        }
        if(sChar_Count < 1 || alphaCheck != true || numberCheck != true){
            returnTxt = "비밀번호는 9~15자 영문,숫자,특수문자 1자 이상으로 조합해주세요.";
            return returnTxt;
        }
    }else{
        returnTxt = "비밀번호는 9~15자 영문,숫자,특수문자 1자 이상으로 조합해주세요.";
        return returnTxt;
    }
    return returnTxt;
}
function fnEmailChk(){
    var email = $("#userCtplc").val();
    var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;   
      
    if(regex.test(email) === false) {  
        return false;  
    } else{
        return true;
    }
}

function fnMsgShow(id,txt){
    var $this = $("input[name=" + id + "]");
    if(txt != "") {
    	alert(txt) ;
    }
    if($this.prop("type").toLowerCase() == "radio" || $this.prop("type").toLowerCase() == "checkbox"){
        $("input[name=" + id + "]:eq(0)").focus();
    }else{
        $("input[name=" + id + "]").focus();
    }
}
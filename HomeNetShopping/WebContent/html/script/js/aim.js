/**
	AIM기능을 가져다 쓴다.
	iframe을 이용한 동적로딩 기능 제공.
	
	ajax를 사용못하는 백그라운드 파일 업로드기능에 적용할수 있다.
*/
AIM = {   
  
    frame : function(c) {   
  
        var n = 'f' + Math.floor(Math.random() * 99999);   
        var d = document.createElement('DIV');   
        d.innerHTML = '<iframe style="display:none" src="about:blank" id="'+n+'" name="'+n+'" onload="AIM.loaded(\''+n+'\')"></iframe>';   
        document.body.appendChild(d);   
  
        var i = document.getElementById(n);   
        if (c && typeof(c.onComplete) == 'function') {   
            i.onComplete = c.onComplete;   
        }   
  
        return n;   
    },   
  
    form : function(f, name) {   
        f.setAttribute('target', name);   
    },   
  
    submit : function(f, c) {   
        AIM.form(f, AIM.frame(c));   
        if (c && typeof(c.onStart) == 'function') {   
            return c.onStart();   
        } else {   
            return true;   
        }   
    },   
  
    loaded : function(id) {   
        var i = document.getElementById(id);   
        if (i.contentDocument) {   
            var d = i.contentDocument;   
        } else if (i.contentWindow) {   
            var d = i.contentWindow.document;   
        } else {   
            var d = window.frames[id].document;   
        }   
        if (d.location.href == "about:blank") {   
            return;   
        }   
  
        if (typeof(i.onComplete) == 'function') {   
            i.onComplete(d.body.innerText);   
        }   
    } 
}  


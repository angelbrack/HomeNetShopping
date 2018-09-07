// Naver Smart Editor Crate
function fnCreateEditor(app, tgtId, skinURI) {
	try {
		nhn.husky.EZCreator.createInIFrame({ 
			oAppRef: app, 
			elPlaceHolder: tgtId,
			sSkinURI: "/smarteditor/SmartEditor2Skin.jsp", 
			//\ubcc0\uacbd \uc54c\ub9bc\ucc3d \uc81c\uac70
			htParams : {bUseToolbar : true, 
				fOnBeforeUnload : function(){}, 
				//boolean 
				fOnAppLoad : function(){}
			}, 
			fCreator: "createSEditor2" 
		});											 
	} catch (e) {
		alert("\uac8c\uc2dc\ud310 \ubaa8\ub4c8 \uc815\ubcf4\ub97c \uc815\uc0c1\uc801\uc73c\ub85c \uc77d\uc9c0 \ubabb\ud588\uc2b5\ub2c8\ub2e4.");
	} 
}
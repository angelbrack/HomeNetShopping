function createSEditor2(elIRField, htParams, elSeAppContainer){
	if(!window.$Jindo){
		parent.document.body.innerHTML="\uc9c4\ub3c4 \ud504\ub808\uc784\uc6cd\uc774 \ud544\uc694\ud569\ub2c8\ub2e4.<br>\n<a href='http://dev.naver.com/projects/jindo/download'>http://dev.naver.com/projects/jindo/download</a>\uc5d0\uc11c Jindo 1.5.3 \ubc84\uc804\uc758 jindo.min.js\ub97c \ub2e4\uc6b4\ub85c\ub4dc \ubc1b\uc544 /js \ud3f4\ub354\uc5d0 \ubcf5\uc0ac \ud574 \uc8fc\uc138\uc694.\n(\uc544\uc9c1 Jindo 2 \ub294 \uc9c0\uc6d0\ud558\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4.)";
		return;
	}
	var elAppContainer = (elSeAppContainer || jindo.$("smart_editor2"));	
	var elEditingArea = jindo.$$.getSingle("DIV.husky_seditor_editing_area_container", elAppContainer);
	var oWYSIWYGIFrame = jindo.$$.getSingle("IFRAME.se2_input_wysiwyg", elEditingArea);
	var oIRTextarea = elIRField?elIRField:jindo.$$.getSingle("TEXTAREA.blind", elEditingArea);
	var oHTMLSrc = jindo.$$.getSingle("TEXTAREA.se2_input_htmlsrc", elEditingArea);
	var oTextArea = jindo.$$.getSingle("TEXTAREA.se2_input_text", elEditingArea);
	
	if(!htParams){ 
		htParams = {}; 
		htParams.fOnBeforeUnload = null;
	}
	htParams.elAppContainer = elAppContainer;												// \uc5d0\ub514\ud130 UI \ucd5c\uc0c1\uc704 element \uc14b\ud305 
	htParams.oNavigator = jindo.$Agent().navigator();										// navigator \uac1d\uccb4 \uc14b\ud305
	
	var oEditor = new nhn.husky.HuskyCore(htParams);
	
	oEditor.registerPlugin(new nhn.husky.CorePlugin(htParams?htParams.fOnAppLoad:null));	
	oEditor.registerPlugin(new nhn.husky.StringConverterManager());

	var htDimension = {
		nMinHeight:205,
		nMinWidth:parseInt(elIRField.style.minWidth, 10)||570,
		nHeight:elIRField.style.height||elIRField.offsetHeight,
		nWidth:elIRField.style.width||elIRField.offsetWidth
	};
	oEditor.registerPlugin(new nhn.husky.SE_EditingAreaManager("WYSIWYG", oIRTextarea, htDimension,  htParams.fOnBeforeUnload, elAppContainer));
	oEditor.registerPlugin(new nhn.husky.SE_EditingArea_WYSIWYG(oWYSIWYGIFrame));			// Tab Editor \ubaa8\ub4dc
	oEditor.registerPlugin(new nhn.husky.SE_EditingArea_HTMLSrc(oHTMLSrc));					// Tab HTML \ubaa8\ub4dc
	oEditor.registerPlugin(new nhn.husky.SE_EditingArea_TEXT(oTextArea));					// Tab Text \ubaa8\ub4dc
	oEditor.registerPlugin(new nhn.husky.SE2M_EditingModeChanger(elAppContainer));			// \ubaa8\ub4dc\uac04 \ubcc0\uacbd(Editor, HTML, Text)
	
	oEditor.registerPlugin(new nhn.husky.HuskyRangeManager(oWYSIWYGIFrame));
	oEditor.registerPlugin(new nhn.husky.Utils());
	oEditor.registerPlugin(new nhn.husky.SE2M_UtilPlugin());
	oEditor.registerPlugin(new nhn.husky.SE_WYSIWYGStyler());
	oEditor.registerPlugin(new nhn.husky.SE2M_Toolbar(elAppContainer));
	
	oEditor.registerPlugin(new nhn.husky.Hotkey());											// \ub2e8\ucd95\ud0a4
	oEditor.registerPlugin(new nhn.husky.SE_EditingAreaVerticalResizer(elAppContainer));	// \ud3b8\uc9d1\uc601\uc5ed \ub9ac\uc0ac\uc774\uc988
	oEditor.registerPlugin(new nhn.husky.DialogLayerManager());
	oEditor.registerPlugin(new nhn.husky.ActiveLayerManager());
	oEditor.registerPlugin(new nhn.husky.SE_WYSIWYGStyleGetter());							// \ucee4\uc11c \uc704\uce58 \uc2a4\ud0c0\uc77c \uc815\ubcf4 \uac00\uc838\uc624\uae30

	oEditor.registerPlugin(new nhn.husky.SE2B_Customize_ToolBar(elAppContainer));			// \uc0c1\ub2e8 \ud234\ubc14 (Basic)
	oEditor.registerPlugin(new nhn.husky.SE_WYSIWYGEnterKey("P"));							// \uc5d4\ud130 \uc2dc \ucc98\ub9ac, \ud604\uc7ac\ub294 P\ub85c \ucc98\ub9ac
	
	oEditor.registerPlugin(new nhn.husky.SE2M_ColorPalette(elAppContainer));				// \uc0c9\uc0c1 \ud314\ub808\ud2b8
	oEditor.registerPlugin(new nhn.husky.SE2M_FontColor(elAppContainer));					// \uae00\uc790\uc0c9
	oEditor.registerPlugin(new nhn.husky.SE2M_BGColor(elAppContainer));						// \uae00\uc790\ubc30\uacbd\uc0c9
	oEditor.registerPlugin(new nhn.husky.SE2M_FontNameWithLayerUI(elAppContainer));			// \uae00\uaf34\uc885\ub958
	oEditor.registerPlugin(new nhn.husky.SE2M_FontSizeWithLayerUI(elAppContainer));			// \uae00\uaf34\ud06c\uae30
	
	oEditor.registerPlugin(new nhn.husky.SE2M_LineStyler());								 
	oEditor.registerPlugin(new nhn.husky.SE2M_ExecCommand(oWYSIWYGIFrame));
	oEditor.registerPlugin(new nhn.husky.SE2M_LineHeightWithLayerUI(elAppContainer));		// \uc904\uac04\uaca9	

	oEditor.registerPlugin(new nhn.husky.SE2M_Quote(elAppContainer));						// \uc778\uc6a9\uad6c
	oEditor.registerPlugin(new nhn.husky.SE2M_Hyperlink(elAppContainer));					// \ub9c1\ud06c
	oEditor.registerPlugin(new nhn.husky.SE2M_SCharacter(elAppContainer));					// \ud2b9\uc218\ubb38\uc790
	oEditor.registerPlugin(new nhn.husky.SE2M_FindReplacePlugin(elAppContainer));			// \ucc3e\uae30/\ubc14\uafb8\uae30
	oEditor.registerPlugin(new nhn.husky.SE2M_TableCreator(elAppContainer));				// \ud14c\uc774\ube14 \uc0dd\uc131
	oEditor.registerPlugin(new nhn.husky.SE2M_TableEditor(elAppContainer));					// \ud14c\uc774\ube14 \ud3b8\uc9d1
	oEditor.registerPlugin(new nhn.husky.SE2M_TableBlockStyler(elAppContainer));			// \ud14c\uc774\ube14 \uc2a4\ud0c0\uc77c
	oEditor.registerPlugin(new nhn.husky.SE2M_AttachQuickPhoto(elAppContainer));			// \uc0ac\uc9c4			

	oEditor.registerPlugin(new nhn.husky.MessageManager(oMessageMap));
	oEditor.registerPlugin(new nhn.husky.SE2M_QuickEditor_Common(elAppContainer));			// \ud035\uc5d0\ub514\ud130 \uacf5\ud1b5(\ud45c, \uc774\ubbf8\uc9c0)
	
	if(jindo.$Agent().navigator().ie){
		oEditor.registerPlugin(new nhn.husky.SE2M_ImgSizeRatioKeeper());					// \uc774\ubbf8\uc9c0 \uc120\ud0dd\ud55c \uc774\ud6c4 \ub9c8\uc6b0\uc2a4\ub85c \ud06c\uae30 \uc870\uc815\ud558\uba74 \uc815\ube44\uc728\ub85c \ubcc0\uacbd		
	}
		
	oEditor.registerPlugin(new nhn.husky.SE2B_CSSLoader());									// CSS lazy load
	oEditor.registerPlugin(new nhn.husky.SE_OuterIFrameControl(elAppContainer, 100));
	
	oEditor.registerPlugin(new nhn.husky.SE_ToolbarToggler(elAppContainer, htParams.bUseToolbar));
	
	// 이미지 리사이징 기능 추가 START [2018.08.10]
	oEditor.registerPlugin(new nhn.husky.SE_QuickEditor_Image(elAppContainer));
	//이미지 리사이징 기능 추가 START [2018.08.10]
	
	return oEditor;
}
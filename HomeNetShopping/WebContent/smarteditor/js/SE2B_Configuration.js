/*
 * Smart Editor 2 Configuration
 */
if(typeof window.nhn=='undefined'){window.nhn = {};}
if (!nhn.husky){nhn.husky = {};}
nhn.husky.SE2M_Configuration = {};

//이미지 리사이징 기능 추가 START [2018.08.10]
nhn.husky.SE2M_Configuration.QuickEditor = {
    common : {
        bUseConfig : false
    },
    Image : {
        nImageMaxWidthSize : 9999,
        nImageMaxHeightSize : 9999
    }
};
//이미지 리사이징 기능 추가 END [2018.08.10]
nhn.husky.SE2M_Configuration.Editor = {
	sJsBaseURL : './js_src',
	sImageBaseURL : './img'
};
nhn.husky.SE2M_Configuration.LinkageDomain = {
	sCommonAPI : 'http://api.se2.naver.com',
	sCommonStatic : 'http://static.se2.naver.com',
	sCommonImage : 'http://images.se2.naver.com'
};

nhn.husky.SE2M_Configuration.SE_EditingAreaManager = {
	sBlankPageURL : "smart_editor2_inputarea.html",
	sBlankPageURL_IE8 : "smart_editor2_inputarea_ie8.html"
};

nhn.husky.SE2M_Configuration.LazyLoad = {
	sJsBaseURI : "js_lazyload"
};

nhn.husky.SE2M_Configuration.SE2B_CSSLoader = {
	sCSSBaseURI : "./css"
};

nhn.husky.SE2M_Configuration.Quote = {
	sImageBaseURL : 'http://static.se2.naver.com/static/img'
};

nhn.husky.SE2M_Configuration.CustomObject = {
	sVersion			: 1,
	sClassName 			: '__se_object',
	sValueName 			: 'jsonvalue',
	sTagIdPrefix		: 'se_object_',
	sTailComment		: '<!--__se_object_end -->',
	sBlankTemplateURL 	: nhn.husky.SE2M_Configuration.LinkageDomain.sCommonStatic + '/static/db_attach/iframe_template_for_se1_obj.html',
	sAttributeOfEmpty	: 's_isempty="true"',
	sAttributeOfOldDB	: 's_olddb="true"',
	sBlock	 			: '<div class="_block" style="position:absolute;z-index:10000;background-color:#fff;"></div>',
	sBlokTemplate	  	:  '<div[\\s\\S]*?class=[\'"]?_block[\'"]?[\\s\\S]*?</div>',
	sHighlight 			: '<div class="_highlight" style="position:absolute;width:58px;height:16px;line-height:0;z-index:9999"><img src="' + nhn.husky.SE2M_Configuration.LinkageDomain.sCommonStatic + '/static/img/pencil2.png" alt="" width="58" height="16" style="vertical-align:top"></div>',
	sHighlightTemplate  :  '<div[\\s\\S]*?class=[\'"]?_highlight[\'"]?[\\s\\S]*?</div>',
	sHtmlTemplateStartTag : '<!-- se_object_template_start -->',
	sHtmlTemplateEndTag : '<!-- se_object_template_end -->',
	sHtmlFilterTag 		: '{=sType}_{=sSubType}_{=nSeq}',
	sTplHtmlFilterTag 	: '<!--{=sType}_{=sSubType}_(\\d+)-->',
	sImgComServerPath	: nhn.husky.SE2M_Configuration.LinkageDomain.sCommonStatic + '/static/img/reviewitem',
	nMaxWidth			: 548
};

nhn.husky.SE2M_Configuration.SE2M_ReEditAction = {
	bUsed : true,
	nSecDisplayDulationReEditMsg : 3,
	aReEditGuideMsg : [
	    '\uc774\ubbf8\uc9c0 \ud30c\uc77c\uc740 1\ud68c \ud074\ub9ad \uc2dc \ud06c\uae30 \uc870\uc808, \ub354\ube14\ud074\ub9ad \uc2dc \uc7ac\ud3b8\uc9d1\uc774 \uac00\ub2a5\ud569\ub2c8\ub2e4.',
	    '\ucca8\ubd80\ud55c \ud30c\uc77c\uc744 \ub354\ube14\ud074\ub9ad \uc2dc \uc7ac\ud3b8\uc9d1\uc774 \uac00\ub2a5\ud569\ub2c8\ub2e4.',
	    '\ucca8\ubd80\ud55c \uae00\uc591\uc2dd \ud14c\uc774\ube14\uc744 \ub4dc\ub798\uadf8\uc2dc \ud14c\uc774\ube14 \uc7ac\ud3b8\uc9d1\uc774 \uac00\ub2a5\ud569\ub2c8\ub2e4.',
	    '\ucca8\ubd80\ud55c \ud45c\ub97c \ub4dc\ub798\uadf8 \uc2dc \ud45c \uc7ac\ud3b8\uc9d1\uc774 \uac00\ub2a5\ud569\ub2c8\ub2e4.'
	]
};

nhn.husky.SE2M_Configuration.SE2M_ColorPalette = {
	bUseFlashModule : false
};
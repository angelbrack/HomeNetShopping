/**
 * @use \uac04\ub2e8 \ud3ec\ud1a0 \uc5c5\ub85c\ub4dc\uc6a9\uc73c\ub85c \uc81c\uc791\ub418\uc5c8\uc2b5\ub2c8\ub2e4.
 * @author cielo
 * @See nhn.husky.SE2M_Configuration 
 * @ \ud31d\uc5c5 \ub9c8\ud06c\uc5c5\uc740 SimplePhotoUpload.html\uacfc SimplePhotoUpload_html5.html\uc774 \uc788\uc2b5\ub2c8\ub2e4. 
 */

nhn.husky.SE2M_AttachQuickPhoto = jindo.$Class({		
	name : "SE2M_AttachQuickPhoto",

	$init : function(){},
	
	$ON_MSG_APP_READY : function(){
		this.oApp.exec("REGISTER_UI_EVENT", ["photo_attach", "click", "ATTACHPHOTO_OPEN_WINDOW"]);
	},
	
	$LOCAL_BEFORE_FIRST : function(sMsg){
		if(!!this.oPopupMgr){ return; }
		// Popup Manager\uc5d0\uc11c \uc0ac\uc6a9\ud560 param
		this.htPopupOption = {
			oApp : this.oApp,
			sName : this.name,
			bScroll : false,
			sProperties : "",
			sUrl : ""
		};
		this.oPopupMgr = nhn.husky.PopUpManager.getInstance(this.oApp);
	},
	
	/**
	 * \ud3ec\ud1a0 \uc6f9\ud0d1 \uc624\ud508
	 */
	$ON_ATTACHPHOTO_OPEN_WINDOW : function(){			
		this.htPopupOption.sUrl = this.makePopupURL();
		this.htPopupOption.sProperties = "left=0,top=0,width=383,height=339,scrollbars=no,location=no,status=0,resizable=no";

		this.oPopupWindow = this.oPopupMgr.openWindow(this.htPopupOption);
		
		// \ucc98\uc74c \ub85c\ub529\ud558\uace0 IE\uc5d0\uc11c \ucee4\uc11c\uac00 \uc804\ud600 \uc5c6\ub294 \uacbd\uc6b0
		// \ubcf5\uc218 \uc5c5\ub85c\ub4dc\uc2dc\uc5d0 \uc21c\uc11c\uac00 \ubc14\ub01c	
		this.oApp.exec('FOCUS');
		return (!!this.oPopupWindow ? true : false);
	},
	
	/**
	 * \uc11c\ube44\uc2a4\ubcc4\ub85c \ud31d\uc5c5\uc5d0  parameter\ub97c \ucd94\uac00\ud558\uc5ec URL\uc744 \uc0dd\uc131\ud558\ub294 \ud568\uc218	 
	 * nhn.husky.SE2M_AttachQuickPhoto.prototype.makePopupURL\ub85c \ub36e\uc5b4\uc368\uc11c \uc0ac\uc6a9\ud558\uc2dc\uba74 \ub428.
	 */
	makePopupURL : function(){
		var sPopupUrl = "./popup/quick_photo/Photo_Quick_UploadPopup.jsp"; 
		return sPopupUrl;
	},
	
	/**
	 * \ud31d\uc5c5\uc5d0\uc11c \ud638\ucd9c\ub418\ub294 \uba54\uc138\uc9c0.
	 */
	$ON_SET_PHOTO : function(aPhotoData){
		var sContents, 
			aPhotoInfo,
			htData;
		
		if( !aPhotoData ){ 
			return; 
		}
		
		try{
			sContents = "";
			for(var i = 0; i <aPhotoData.length; i++){				
				htData = aPhotoData[i];
				
				if(!htData.sAlign){
					htData.sAlign = "";
				}
				
				aPhotoInfo = {
				    sName : htData.sFileName || "",
				    sOriginalImageURL : htData.sFileURL,
					bNewLine : htData.bNewLine || false 
				};
				
				sContents += this._getPhotoTag(aPhotoInfo);
			}

			this.oApp.exec("PASTE_HTML", [sContents]); // \uc704\uc990 \ucca8\ubd80 \ud30c\uc77c \ubd80\ubd84 \ud655\uc778
		}catch(e){
			// upload\uc2dc error\ubc1c\uc0dd\uc5d0 \ub300\ud574\uc11c skip\ud568
			return false;
		}
	},

	/**
	 * @use \uc77c\ubc18 \ud3ec\ud1a0 tag \uc0dd\uc131
	 */
	_getPhotoTag : function(htPhotoInfo){
		// id\uc640 class\ub294 \uc378\ub124\uc77c\uacfc \uc5f0\uad00\uc774 \ub9ce\uc2b5\ub2c8\ub2e4. \uc218\uc815\uc2dc \uc378\ub124\uc77c \uc601\uc5ed\ub3c4 Test
		var sTag = '<img src="{=sOriginalImageURL}" title="{=sName}">';
		//var sTag = '<img src="{=sOriginalImageURL}" title="{=sName}" width="600px">';
		if(htPhotoInfo.bNewLine){
			sTag += '<br style="clear:both;">';
		}
		sTag = jindo.$Template(sTag).process(htPhotoInfo);
		
		return sTag;
	}
});
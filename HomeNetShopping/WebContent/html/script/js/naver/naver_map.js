//Naver Map Crate (View)
function fnCreateViewMap(stoId){
	try{
		var oPoint = new nhn.api.map.LatLng(dbLat, dbLon);
		nhn.api.map.setDefaultPoint('LatLng');
		oMap = new nhn.api.map.Map('testMap', {
				center : oPoint,
				level : 10, // - \ucd08\uae30 \uc90c \ub808\ubca8
				enableWheelZoom : true,
				enableDragPan : true,
				enableDblClickZoom : false,
				mapMode : 0,
				activateTrafficMap : false,
				activateBicycleMap : false,
				activateRealtyMap : true,
				minMaxLevel : [ 1, 14 ],
				size : new nhn.api.map.Size(600, 400)
		});

		var mapZoom = new nhn.api.map.ZoomControl(); // \uc90c \ucee8\ud2b8\ub864 \uc120\uc5b8
		mapZoom.setPosition({left:30, bottom:30}); // \uc90c \ucee8\ud2b8\ub864 \uc704\uce58 \uc9c0\uc815
		oMap.addControl(mapZoom); // - \uc90c \ucee8\ud2b8\ub864 \ucd94\uac00
		
		var oSize = new nhn.api.map.Size(28, 37);
        var oOffset = new nhn.api.map.Size(14, 37);
        var oIcon = new nhn.api.map.Icon('http://static.naver.com/maps2/icons/pin_spot2.png', oSize, oOffset);

        var oMarker = new nhn.api.map.Marker(oIcon, { title : stoId });  //\ub9c8\ucee4 \uc0dd\uc131, stoId=\uae30\uc5c5\uba85 \ud45c\uc2dc
        oMarker.setPoint(oPoint);
        oMap.addOverlay(oMarker);
        var oLabel = new nhn.api.map.MarkerLabel(); // - \ub9c8\ucee4 \ub77c\ubca8 \uc120\uc5b8.
        oMap.addOverlay(oLabel); // - \ub9c8\ucee4 \ub77c\ubca8 \uc9c0\ub3c4\uc5d0 \ucd94\uac00. \uae30\ubcf8\uc740 \ub77c\ubca8\uc774 \ubcf4\uc774\uc9c0 \uc54a\ub294 \uc0c1\ud0dc\ub85c \ucd94\uac00\ub428.
        oLabel.setVisible(true, oMarker); // \ub9c8\ucee4 \ub77c\ubca8 \ubcf4\uc774\uae30
	}catch(e){
		alert("\uac8c\uc2dc\ud310 \ubaa8\ub4c8 \uc815\ubcf4\ub97c \uc815\uc0c1\uc801\uc73c\ub85c \uc77d\uc9c0 \ubabb\ud588\uc2b5\ub2c8\ub2e4.");
	}
}


//Naver Map Crate (Modification)
function fnCreateModMap(stoId){
	try{
		var oPoint = new nhn.api.map.LatLng(dbLat, dbLon);
		nhn.api.map.setDefaultPoint('LatLng');
		oMap = new nhn.api.map.Map('testMap', {
					center : oPoint,
					level : 10, // - \ucd08\uae30 \uc90c \ub808\ubca8
					enableWheelZoom : true,
					enableDragPan : true,
					enableDblClickZoom : false,
					mapMode : 0,
					activateTrafficMap : false,
					activateBicycleMap : false,
					activateRealtyMap : true,
					minMaxLevel : [ 1, 14 ],
					size : new nhn.api.map.Size(600, 400)
		});
		var mapZoom = new nhn.api.map.ZoomControl(); // \uc90c \ucee8\ud2b8\ub864 \uc120\uc5b8
		mapZoom.setPosition({left:30, bottom:30}); // \uc90c \ucee8\ud2b8\ub864 \uc704\uce58 \uc9c0\uc815.
		oMap.addControl(mapZoom); // - \uc90c \ucee8\ud2b8\ub864 \ucd94\uac00.
		
		var oSize = new nhn.api.map.Size(28, 37);
		var oOffset = new nhn.api.map.Size(14, 37);
		var oIcon = new nhn.api.map.Icon('http://static.naver.com/maps2/icons/pin_spot2.png', oSize, oOffset);
		var testdefaultSpriteIcon = {
				url:"http://static.naver.com/maps2/icons/pin_spot2.png", 
				size:{width:28, height:37},
				spriteSize:{width:28, height:37},
				imgOrder: 1, 
				offset : {width: 15, height: 38}
		};
		// - \uc704\uc5d0\uc11c \uc9c0\uc815\ud55c \uae30\ubcf8\uac12\uc744 \uc774\uc6a9\ud574 \uc2e4\uc81c\ub85c SpriteIcon \uc744 \uc0dd\uc131\ud55c\ub2e4.
		var testDupSpriteIcon_first = new nhn.api.map.SpriteIcon(testdefaultSpriteIcon.url, testdefaultSpriteIcon.size, testdefaultSpriteIcon.spriteSize, 0, testdefaultSpriteIcon.offset); 
	
		// \uc9c0\ub3c4\uc0dd\uc131
		DraggableMarker = new nhn.api.map.DraggableMarker(testDupSpriteIcon_first , {	
			title : stoId,
			point : oPoint,
			zIndex : 1,
			smallSrc :  testDupSpriteIcon_first});		
		oMap.addOverlay(DraggableMarker);
		
		var mapInfoTestWindow = new nhn.api.map.InfoWindow(); // - info window \uc0dd\uc131.
		mapInfoTestWindow.setVisible(false); // - infowindow \ud45c\uc2dc \uc5ec\ubd80 \uc9c0\uc815.
		oMap.addOverlay(mapInfoTestWindow);	// - \uc9c0\ub3c4\uc5d0 info window\ub97c \ucd94\uac00\ud55c\ub2e4.	 
		
		oMap.attach('click', function(oCustomEvent) {
			var oPoint = oCustomEvent.point;
			var oTarget = oCustomEvent.target;
			mapInfoTestWindow.setVisible(false);
			// \ub9c8\ucee4\ub97c \ud074\ub9ad\ud588\uc744 \ub54c.
			if (oTarget instanceof nhn.api.map.DraggableMarker) {
				// \uacb9\uce68 \ub9c8\ucee4\ub97c \ud074\ub9ad\ud588\uc744 \ub54c.
				if (oCustomEvent.clickCoveredMarker) {
					return;
				}
				mapInfoTestWindow.setContent('<DIV style="border-top:1px solid; border-bottom:2px groove black; border-left:1px solid; border-right:2px groove black;margin-bottom:1px;color:black;background-color:white; width:auto; height:auto;">'+
						'<span style="color: #000000 !important;display: inline-block;font-size: 12px !important;font-weight: bold !important;letter-spacing: -1px !important;white-space: nowrap !important; padding: 2px 5px 2px 2px !important">' + 
						'\uacbd\ub3c4/\uc704\ub3c4<br /> ' + oTarget.getPoint()
						+'<span></div>');
				mapInfoTestWindow.setPoint(oTarget.getPoint());
				mapInfoTestWindow.setVisible(true);
				mapInfoTestWindow.autoPosition();
				return;
			}
		});
		
		var oLabel = new nhn.api.map.MarkerLabel(); // - \ub9c8\ucee4 \ub77c\ubca8 \uc120\uc5b8
		oMap.addOverlay(oLabel); // - \ub9c8\ucee4 \ub77c\ubca8\uc744 \uc9c0\ub3c4\uc5d0 \ucd94\uac00\ud55c\ub2e4. \uae30\ubcf8\uc740 \ub77c\ubca8\uc774 \ubcf4\uc774\uc9c0 \uc54a\ub294 \uc0c1\ud0dc\ub85c \ucd94\uac00\ub428.
	
		oMap.attach("mouseenter", function(oEvent){
			var oTarget = oEvent.target;
			if(oTarget instanceof nhn.api.map.DraggableMarker){
				oLabel.setVisible(true, oTarget); // - \ud2b9\uc815 \ub9c8\ucee4\ub97c \uc9c0\uc815\ud558\uc5ec \ud574\ub2f9 \ub9c8\ucee4\uc758 title\uc744 \ubcf4\uc5ec\uc900\ub2e4.
			}
		});
		
		oMap.attach("mouseleave", function(oEvent) {
			var oTarget = oEvent.target;
			if(oTarget instanceof nhn.api.map.DraggableMarker){
				oLabel.setVisible(false);
			}
		});
		/*
		* draggable marker \uc758 \uc790\uccb4 \uc774\ubca4\ud2b8\ub85c changePosition \uc774 \uc788\ub2e4.
		* \uc774 \uc774\ubca4\ud2b8\ub294, \uc0ac\uc6a9\uc790\uac00 drag \ub97c \uc774\uc6a9\ud574 \ub9c8\ucee4\uc758 \uc704\uce58\ub97c \uc62e\uae38 \ub54c \ubc1c\uc0dd\ud55c\ub2e4.
		* oEvent \uc758 \ud30c\ub77c\ubbf8\ud130\ub294 \ub450 \uac1c\ub85c, oldPoint \uc640 newPoint \uc774\ub2e4.
		* oldPoint \ub294 drag \ud558\uae30 \uc804\uc758 \ub9c8\ucee4 \uc704\uce58\uc774\uba70, newPoint \ub294 drag \ud55c \uc774\ud6c4\uc758 \ub9c8\ucee4 \uc704\uce58\uac00 \ub41c\ub2e4..
		*/
		DraggableMarker.attach("changePosition" , function (oEvent) {
			//alert("oEvent.newPoint()"+oEvent.newPoint);
			if(mapInfoTestWindow.getVisible() != false){
				mapInfoTestWindow.setVisible(false); // - infowindow \uc758 \ud45c\uc2dc \uc5ec\ubd80 \uc9c0\uc815.
				// - infoWindow \uc758 \ub0b4\uc6a9\uc740 \uc0ac\uc6a9\uc790\uac00 \uc784\uc758\ub85c \uc9c0\uc815\ud560 \uc218 \uc788\uc2b5\ub2c8\ub2e4. \ub2e8 HTML \ub85c \uc9c0\uc815\uc744 \ud558\uc154\uc57c \ud569\ub2c8\ub2e4. 
				mapInfoTestWindow.setContent('<DIV style="border-top:1px solid; border-bottom:2px groove black; border-left:1px solid; border-right:2px groove black;margin-bottom:1px;color:black;background-color:white; width:auto; height:auto;">'+
						'<span style="color: #000000 !important;display: inline-block;font-size: 12px !important;font-weight: bold !important;letter-spacing: -1px !important;white-space: nowrap !important; padding: 2px 5px 2px 2px !important">' + 
						'\uacbd\ub3c4/\uc704\ub3c4 <br /> ' + oEvent.newPoint
						+'<span></div>');
				mapInfoTestWindow.setPoint(oEvent.newPoint);
				mapInfoTestWindow.setVisible(true);
				mapInfoTestWindow.autoPosition();
			}
			$("#latitude").val(oEvent.newPoint.y);
			$("#longitude").val(oEvent.newPoint.x);
			//alert(oEvent.newPoint.y + "," + oEvent.newPoint.x);
		});	
		
	}catch(e){
		alert("\uac8c\uc2dc\ud310 \ubaa8\ub4c8 \uc815\ubcf4\ub97c \uc815\uc0c1\uc801\uc73c\ub85c \uc77d\uc9c0 \ubabb\ud588\uc2b5\ub2c8\ub2e4.");
	} 
}
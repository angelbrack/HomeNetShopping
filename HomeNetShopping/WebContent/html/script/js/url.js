/*
	URL과 parameter를 다루는 기능 모음.
*/

function addParam(theURL, nm, val){
	var ap = 0;
	var param = (nm+"="+escape(val));
    if((ap = theURL.indexOf("?")) != -1){
        theURL += ("&"+param);
    } else {
    	theURL += ("?"+param);
    }
	return theURL;
}



function stripWebFile(theURL){
	var ap = 0, ep = 0;
	var srchPath;
	var pathToks;
	var lastPathNm;
	var progId;
	
    if((ap = theURL.indexOf("?")) != -1 
            || (ap = theURL.indexOf("#")) != -1){
        srchPath = theURL.substring(0, ap);
    } else {
        srchPath = theURL;
    }
    
    pathToks = srchPath.split("/");
    if(pathToks != null && pathToks.length > 0){
        lastPathNm = pathToks[pathToks.length-1];
        if(lastPathNm != null){
            progId = lastPathNm;
        }
    }
    
    return progId;
}
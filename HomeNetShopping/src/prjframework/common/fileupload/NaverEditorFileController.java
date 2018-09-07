package prjframework.common.fileupload;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 네이버 에디터에서 사용하는 이미지 삽입 처리 컨트롤러
 * FileUploadInterceptor 에서 이미 업로드된 결과를 받아 네이버의 스마트에디터에 처리결과를 전송 한다. 
 * @author oops
 *
 */
@Controller
public class NaverEditorFileController {

	@Resource(name = "fileUploadProperties")
	Properties fileUploadProperties;

    @RequestMapping("/smart/NaverImageUpload.do")
    public String naverImgageUpload(
			HttpServletRequest request,
			ModelMap model)
	throws Exception {
    	
		List list = (List) request.getAttribute("FILE_LIST");
		String url = request.getParameter("callback");
		String callback_func = request.getParameter("callback_func");
		url += "?callback_func=" + callback_func;
		String curMonth = request.getParameter("curMonth");
		
		String fullPathKey = request.getParameter("filePathKey")+".PATH";
		
		String uploadLastPath = fileUploadProperties.getProperty(fullPathKey)+"/"+curMonth;
		
		if (list != null) {
			Map<String, Object> fileMap = (HashMap<String, Object>) list.get(0);
			String chgFileName = (String) fileMap.get("CHG_FILE_NAME");
		
			url += "&bNewLine=true&sFileName="+chgFileName;
			url += "&size=0";
			url += "&url="+uploadLastPath+"/"+chgFileName;
			url += "&sFileURL="+uploadLastPath+"/"+chgFileName;
			url += "&curMonth="+curMonth;

		} else {
			url += "errstr=error";
		}
		
//		getLogger().debug("URL : " + url);
		return "redirect:"+url;
	}
}

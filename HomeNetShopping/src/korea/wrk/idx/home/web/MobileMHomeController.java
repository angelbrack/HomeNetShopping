package korea.wrk.idx.home.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import korea.wrk.idx.home.service.UserMHomeVO;

@Controller
public class MobileMHomeController {
	


	/**
	 * 사용자 메인화면
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mobile/index/home/homeMain.do")
	public String getMain(@ModelAttribute("searchVO") UserMHomeVO paramVO, UserMHomeVO vo, ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

		
		return "mobile/index/main/indexAHomeMain";
	}
	
	
}

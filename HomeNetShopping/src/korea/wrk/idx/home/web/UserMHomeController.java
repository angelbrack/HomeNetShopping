package korea.wrk.idx.home.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import korea.wrk.login.service.LoginService;
import korea.wrk.login.service.RoleVO;
import korea.wrk.login.service.UserLoginVO;
import prjframework.common.annotation.SessionChecker;
import prjframework.common.dataaccess.util.DataMap;
import prjframework.common.util.PageMove;
import prjframework.common.util.SessionUtil;
import site.home.menu.service.HomeMenuVO;
import site.home.menu.service.SiteHomeMenuService;
import korea.wrk.brd.common.service.UserBCommonService;
import korea.wrk.brd.common.service.UserBoardVO;
import korea.wrk.idx.home.service.UserMHomeService;
import korea.wrk.idx.home.service.UserMHomeVO;

@Controller
public class UserMHomeController {
	
	@Resource(name = "UserMHomeService")
	private UserMHomeService UserMHomeService;
	
	@Resource(name = "siteHomeMenuService")
    private SiteHomeMenuService siteHomeMenuService;
	
	@Resource(name = "userBCommonService")
	private UserBCommonService userBCommonService;
	
	@Resource(name = "configPropService")
	protected EgovPropertyService configPropService;
	
	

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "loginService")
	protected LoginService loginService;
	

	/**
	 * 사용자 메인화면
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/index/home/homeMain.do")
	public String getMain(@ModelAttribute("searchVO") UserMHomeVO paramVO, UserMHomeVO vo, ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

		//메인배너
		vo.setBanrKndc("01");
		List<EgovMap> bannerList = UserMHomeService.retrieveBannerList(vo);
		
		//상단 취업 프로그램 
		List<EgovMap> mainProList = UserMHomeService.retrieveMainProList(vo);
		
		//즁간취업교육
		List<EgovMap> proList = UserMHomeService.retrieveProList(vo);
		
		//취업행사일정
		List<EgovMap> dayList = UserMHomeService.retrieveDayList(vo);
		
		//공지사항
		UserBoardVO brdVo = new UserBoardVO();
		brdVo.setBoardType("notice");
		brdVo.setBoardType2("Notice");
		brdVo.setBlbdDvCd("001");
		brdVo.setFirstIndex(0);
		brdVo.setRecordCountPerPage(5) ;
		brdVo.setSearchToppNotcYn("N");
		List<EgovMap> notiList = userBCommonService.retrieveList(brdVo);
		
		
		String[] searchRecuAprvDcList = new String[3];
		searchRecuAprvDcList[0]	= "002";	// 승인
		searchRecuAprvDcList[1]	= "003";	// 마감
		searchRecuAprvDcList[2]	= "005";	// 확정발료
		
		model.addAttribute("bannerList", bannerList);
		model.addAttribute("mainProList", mainProList);
		model.addAttribute("proList", proList);
		model.addAttribute("dayList", dayList);
		model.addAttribute("notiList", notiList);
		
		return "user/index/main/indexHomeMain";
	}
	
	@SessionChecker
	@RequestMapping(value="/user/index/home/homeMain1.do")
	public String getMain1(ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

		
		return "user/index/main/indexHomeMain";
	}
	
}

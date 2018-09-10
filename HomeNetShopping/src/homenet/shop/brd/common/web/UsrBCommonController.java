package homenet.shop.brd.common.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import homenet.shop.brd.common.service.UserBCommonService;
import homenet.shop.brd.common.service.UserBoardVO;
import homenet.shop.login.service.UserLoginVO;
import prjframework.common.annotation.SessionChecker;
import prjframework.common.util.Casting;
import prjframework.common.util.PageMove;
import prjframework.common.util.SessionUtil;
import prjframework.common.util.WebUtil;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 최초작성자 : KJS
 * 최초작성일 : 2017.10.11
 * 최종변경일 : 2017.10.11
 * 목적 : 공통게시판 컨트롤
 * 개정이력 : 없음
 * 저작권 : 한국방송통신대학교
 */
@Controller
public class UsrBCommonController {

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "userBCommonService")
	private UserBCommonService userBCommonService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;


	/**
	 * 목적 : 공통게시판 화면 오픈
	 * 매개변수 : ModelMap
	 * 매개변수 : UBDBoardoutVO
	 * 매개변수 : HttpServletRequest
	 * 반환값 : String
	 * 개정이력 : 없음
	 */	
	@RequestMapping(value = { "/user/brd/notice/notice.do"				// 공지사항
			, "/user/brd/faq/faq.do" 									// 자주묻는질문
			, "/user/brd/qna/qna.do"									// 문의내역 
			, "/user/brd/data/data.do"									// 취업노하우
	})
	public String initUBDBoardout(@ModelAttribute("searchVO") UserBoardVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		HttpSession session = request.getSession();
		
		UserLoginVO userLoginVO = (UserLoginVO)session.getAttribute("UserLoginVO");
		if (null!=userLoginVO) {
			vo.setWrtPnNo(userLoginVO.getUserNo());
		}
		
		List<EgovMap> topdata = null; 
		List<EgovMap> codelist = null; 
		
		String urlPath = request.getRequestURI();

		if (urlPath.indexOf("/user/brd/notice/notice.do") > -1) {			// 공지사항
			vo.setBoardType("notice");
			vo.setBoardType2("Notice");
			vo.setBlbdDvCd("001");
			vo.setSearchNotcYn("Y");
			vo.setListUrl("/user/brd/notice/notice.do");
			vo.setViewUrl("/user/brd/notice/noticeView.do");
		} else if (urlPath.indexOf("/user/brd/faq/faq.do") > -1) {			// 자주묻는질문
			vo.setBoardType("faq");
			vo.setBoardType2("Faq");
			vo.setBlbdDvCd("002");
			vo.setSearchNotcYn("Y");
			vo.setListUrl("/user/brd/faq/faq.do");
			vo.setViewUrl("/user/brd/faq/faqView.do");
		} else if (urlPath.indexOf("/user/brd/qna/qna.do") > -1) {	// 문의내역
			vo.setBoardType("qna");
			vo.setBoardType2("Qna");
			vo.setBlbdDvCd("003");
			vo.setSearchNotcYn("Y");
			vo.setListUrl("/user/brd/qna/qna.do");
			vo.setViewUrl("/user/brd/qna/qnaView.do");
		} else if (urlPath.indexOf("/user/brd/data/data.do") > -1) {		// 취업노하우
			vo.setBoardType("data");
			vo.setBoardType2("Data");
			vo.setBlbdDvCd("004");
			vo.setSearchNotcYn("Y");
			vo.setListUrl("/user/brd/data/data.do");
			vo.setViewUrl("/user/brd/data/dataView.do");
		}

		
		/*topdata = userBCommonService.retrieveListTop(vo);
		model.addAttribute("toplist", topdata);*/
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		paginationInfo.setPageSize(vo.getPageSize());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		// 공지사항
		if(vo.getBlbdDvCd().equals("001")) {
			// 대상구분(ALL:전체, KR:한국어, EN:영어)
			vo.setSearchTgtDc("KR");
		}
		
		//자주묻는질문 카테고리
		if(vo.getBlbdDvCd().equals("002")) {
			
			if(vo.getSearchCls1Cd() == null || vo.getSearchCls1Cd().equals("")) {
				vo.setSearchCls1Cd("001");
			}
			
			codelist = userBCommonService.retrieveListCode(vo);
			model.addAttribute("codelist", codelist);
		}
		
		vo.setSearchToppNotcYn("N");
		List<EgovMap> list = userBCommonService.retrieveList(vo);
		int totalCount = userBCommonService.retrieveCount(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		UserBoardVO notiParamVO	= new UserBoardVO();
		notiParamVO.setSearchToppNotcYn("Y");
		notiParamVO.setBlbdDvCd(vo.getBlbdDvCd());
		notiParamVO.setSearchTgtDc(vo.getSearchTgtDc());
		notiParamVO.setSearchNotcYn(vo.getSearchNotcYn());
		notiParamVO.setFirstIndex(0);
		notiParamVO.setLastIndex(0);
		notiParamVO.setRecordCountPerPage(0);
		List<EgovMap> notiList = userBCommonService.retrieveList(notiParamVO);

		model.addAttribute("paginationInfo", 	paginationInfo);
		model.addAttribute("list", 				list);
		model.addAttribute("totalCount", 		totalCount);
		model.addAttribute("notiList", 			notiList);
		
		return "/user/brd/" + vo.getBoardType() + "/brd" + vo.getBoardType2() + "List";
	}
	
	/**
	 * 목적 : 공통게시판 화면 오픈 - 영문
	 * 매개변수 : ModelMap
	 * 매개변수 : UBDBoardoutVO
	 * 매개변수 : HttpServletRequest
	 * 반환값 : String
	 * 개정이력 : 없음
	 */	
	@RequestMapping(value = { "/user/brd/notice/engNotice.do"			// 공지사항
			, "/user/brd/faq/engFaq.do" 									// 자주묻는질문
			, "/user/brd/qna/engQna.do"									// 문의내역 
			, "/user/brd/data/engData.do"									// 취업노하우
	})
	public String initUBDEngBoardout(@ModelAttribute("searchVO") UserBoardVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		HttpSession session = request.getSession();
		
		UserLoginVO userLoginVO = (UserLoginVO)session.getAttribute("UserLoginVO");
		if (null!=userLoginVO) {
			vo.setWrtPnNo(userLoginVO.getUserNo());
		}
		
		List<EgovMap> topdata = null; 
		List<EgovMap> codelist = null; 
		
		String urlPath = request.getRequestURI();

		if (urlPath.indexOf("/user/brd/notice/engNotice.do") > -1) {			// 공지사항
			vo.setBoardType("notice");
			vo.setBoardType2("Notice");
			vo.setBlbdDvCd("001");
			vo.setSearchNotcYn("Y");
			vo.setListUrl("/user/brd/notice/engNotice.do");
			vo.setViewUrl("/user/brd/notice/engNoticeView.do");
		} else if (urlPath.indexOf("/user/brd/faq/engFaq.do") > -1) {			// C
			vo.setBoardType("faq");
			vo.setBoardType2("Faq");
			vo.setBlbdDvCd("002");
			vo.setSearchNotcYn("Y");
			vo.setListUrl("/user/brd/faq/engFaq.do");
			vo.setViewUrl("/user/brd/faq/engFaqView.do");
		} else if (urlPath.indexOf("/user/brd/qna/engQna.do") > -1) {	// 문의내역
			vo.setBoardType("qna");
			vo.setBoardType2("Qna");
			vo.setBlbdDvCd("003");
			vo.setListUrl("/user/brd/qna/engQna.do");
			vo.setViewUrl("/user/brd/qna/engQnaView.do");
		} else if (urlPath.indexOf("/user/brd/data/engData.do") > -1) {		// 취업노하우
			vo.setBoardType("data");
			vo.setBoardType2("Data");
			vo.setBlbdDvCd("004");
			vo.setSearchNotcYn("Y");
			vo.setListUrl("/user/brd/data/engData.do");
			vo.setViewUrl("/user/brd/data/engDataView.do");
		}

		
		/*topdata = userBCommonService.retrieveListTop(vo);
		model.addAttribute("toplist", topdata);*/
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		paginationInfo.setPageSize(vo.getPageSize());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		// 공지사항
		if(vo.getBlbdDvCd().equals("001")) {
			// 대상구분(ALL:전체, KR:한국어, EN:영어)
			vo.setSearchTgtDc("EN");
		}
		
		//자주묻는질문 카테고리
		if(vo.getBlbdDvCd().equals("002")) {
			
			if(vo.getSearchCls1Cd() == null || vo.getSearchCls1Cd().equals("")) {
				vo.setSearchCls1Cd("001");
			}
			
			codelist = userBCommonService.retrieveListCode(vo);
			model.addAttribute("codelist", codelist);
		}
		
		vo.setSearchToppNotcYn("N");
		List<EgovMap> list = userBCommonService.retrieveList(vo);
		int totalCount = userBCommonService.retrieveCount(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		UserBoardVO notiParamVO	= new UserBoardVO();
		notiParamVO.setSearchToppNotcYn("Y");
		notiParamVO.setBlbdDvCd(vo.getBlbdDvCd());
		notiParamVO.setSearchTgtDc(vo.getSearchTgtDc());
		notiParamVO.setSearchNotcYn(vo.getSearchNotcYn());
		notiParamVO.setFirstIndex(0);
		notiParamVO.setLastIndex(0);
		notiParamVO.setRecordCountPerPage(0);
		List<EgovMap> notiList = userBCommonService.retrieveList(notiParamVO);

		model.addAttribute("paginationInfo", 	paginationInfo);
		model.addAttribute("list", 				list);
		model.addAttribute("totalCount", 		totalCount);
		model.addAttribute("notiList", 			notiList);
		
		return "/user/brd/" + vo.getBoardType() + "/brdEng" + vo.getBoardType2() + "List";
	}

	/**
	 * 목적 : 공통게시판 상세조회
	 * 매개변수 : ModelMap
	 * 매개변수 : UBDBoardoutVO
	 * 매개변수 : HttpServletRequest
	 * 반환값 : String
	 * 개정이력 : 없음
	 */
	@RequestMapping(value = { "/user/brd/notice/noticeView.do"			// 공지사항
			, "/user/brd/faq/faqView.do" 								// 자주묻는질문
			, "/user/brd/qna/qnaView.do"								// 문의내역
			, "/user/brd/data/dataView.do"								// 취업노하우
	})
	public String retrieveUBDBoardout(ModelMap model, @ModelAttribute("searchVO") UserBoardVO vo, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		UserLoginVO userLoginVO = (UserLoginVO)session.getAttribute("UserLoginVO");
		if (null!=userLoginVO) {
			vo.setWrtPnNo(userLoginVO.getUserNo());
		}

		String urlPath = request.getRequestURI();

		if (urlPath.indexOf("/user/brd/notice/noticeView.do") > -1) {			// 공지사항
			vo.setBoardType("notice");
			vo.setBoardType2("Notice");
			vo.setBlbdDvCd("001");
			vo.setListUrl("/user/brd/notice/notice.do");
			vo.setViewUrl("/user/brd/notice/noticeView.do");
		} else if (urlPath.indexOf("/user/brd/faq/faqView.do") > -1) {			// 자주묻는질문
			vo.setBoardType("faq");
			vo.setBoardType2("Faq");
			vo.setBlbdDvCd("002");
			vo.setListUrl("/user/brd/faq/faq.do");
			vo.setViewUrl("/user/brd/faq/faqView.do");
		} else if (urlPath.indexOf("/user/brd/qna/qnaView.do") > -1) {	// 문의내역
			vo.setBoardType("qna");
			vo.setBoardType2("Qna");
			vo.setBlbdDvCd("003");
			vo.setListUrl("/user/brd/qna/qna.do");
			vo.setViewUrl("/user/brd/qna/qnaView.do");
		} else if (urlPath.indexOf("/user/brd/data/dataView.do") > -1) {		// 취업노하우
			vo.setBoardType("data");
			vo.setBoardType2("Data");
			vo.setBlbdDvCd("004");
			vo.setListUrl("/user/brd/data/data.do");
			vo.setViewUrl("/user/brd/data/dataView.do");
		}
		
		UserBoardVO info = new UserBoardVO();
		List<EgovMap> fileList = null;
		List<EgovMap> fileList2 = null;
		
		info = userBCommonService.retrieveInfo(vo);
		model.addAttribute("info", info);
		
		fileList = userBCommonService.retrieveFile(vo);
		fileList2 = userBCommonService.retrieveFile2(vo);
			
		model.addAttribute("fileList", 	Casting.listMapToJSonString(fileList));
		model.addAttribute("fileList2", Casting.listMapToJSonString(fileList2));
		
		// 조회수 저장
		userBCommonService.modifyCount(vo);
		
		
		return "/user/brd/" + vo.getBoardType() + "/brd" + vo.getBoardType2() + "View";
	}
	
	/**
	 * 목적 : 공통게시판 상세조회 - 영문
	 * 매개변수 : ModelMap
	 * 매개변수 : UBDBoardoutVO
	 * 매개변수 : HttpServletRequest
	 * 반환값 : String
	 * 개정이력 : 없음
	 */
	@RequestMapping(value = { "/user/brd/notice/engNoticeView.do"			// 공지사항
			, "/user/brd/faq/engFaqView.do" 								// 자주묻는질문
			, "/user/brd/qna/engQnaView.do"								// 문의내역
			, "/user/brd/data/engDataView.do"								// 취업노하우
	})
	public String retrieveUBDEngBoardout(ModelMap model, @ModelAttribute("searchVO") UserBoardVO vo, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		UserLoginVO userLoginVO = (UserLoginVO)session.getAttribute("UserLoginVO");
		if (null!=userLoginVO) {
			vo.setWrtPnNo(userLoginVO.getUserNo());
		}

		String urlPath = request.getRequestURI();

		if (urlPath.indexOf("/user/brd/notice/engNoticeView.do") > -1) {			// 공지사항
			vo.setBoardType("notice");
			vo.setBoardType2("Notice");
			vo.setBlbdDvCd("001");
			vo.setListUrl("/user/brd/notice/engNotice.do");
			vo.setViewUrl("/user/brd/notice/engNoticeView.do");
		} else if (urlPath.indexOf("/user/brd/faq/engFaqView.do") > -1) {			// 자주묻는질문
			vo.setBoardType("faq");
			vo.setBoardType2("Faq");
			vo.setBlbdDvCd("002");
			vo.setListUrl("/user/brd/faq/engFaq.do");
			vo.setViewUrl("/user/brd/faq/engFaqView.do");
		} else if (urlPath.indexOf("/user/brd/qna/engQnaView.do") > -1) {	// 문의내역
			vo.setBoardType("qna");
			vo.setBoardType2("Qna");
			vo.setBlbdDvCd("003");
			vo.setListUrl("/user/brd/qna/engQna.do");
			vo.setViewUrl("/user/brd/qna/engQnaView.do");
		} else if (urlPath.indexOf("/user/brd/data/engDataView.do") > -1) {		// 취업노하우
			vo.setBoardType("data");
			vo.setBoardType2("Data");
			vo.setBlbdDvCd("004");
			vo.setListUrl("/user/brd/data/engData.do");
			vo.setViewUrl("/user/brd/data/engDataView.do");
		}
		
		UserBoardVO info = new UserBoardVO();
		List<EgovMap> fileList = null;
		List<EgovMap> fileList2 = null;
		
		info = userBCommonService.retrieveInfo(vo);
		model.addAttribute("info", info);
		
		fileList = userBCommonService.retrieveFile(vo);
		fileList2 = userBCommonService.retrieveFile2(vo);
			
		model.addAttribute("fileList", 	Casting.listMapToJSonString(fileList));
		model.addAttribute("fileList2", Casting.listMapToJSonString(fileList2));
		
		// 조회수 저장
		userBCommonService.modifyCount(vo);
		
		
		return "/user/brd/" + vo.getBoardType() + "/brdEng" + vo.getBoardType2() + "View";
	}
	
	/**
	 * 목적 : 공통게시판 등록/수정
	 * 매개변수 : ModelMap
	 * 매개변수 : UBDBoardoutVO
	 * 매개변수 : HttpServletRequest
	 * 반환값 : String
	 * 개정이력 : 없음
	 */
	@SessionChecker
	@RequestMapping(value = {"/user/brd/qna/qnaHandle.do"})
	public String handleUBDQanda(ModelMap model, @ModelAttribute("searchVO") UserBoardVO vo, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		UserLoginVO userLoginVO = (UserLoginVO)session.getAttribute("UserLoginVO");
		if (null!=userLoginVO) {
			vo.setWrtPnNm(userLoginVO.getUserNm());
			vo.setUserNo(userLoginVO.getUserNo());
		}
		
		String urlPath = request.getRequestURI();
		
		if (urlPath.indexOf("/user/brd/qna/qnaHandle.do") > -1) {	//문의하기
			vo.setBoardType("qna");
			vo.setBoardType2("Qna");
			vo.setBlbdDvCd("003");
			
			UserBoardVO userInfo = new UserBoardVO();
			userInfo = userBCommonService.retrieveBoardUserInfo(vo);
			model.addAttribute("userInfo", userInfo);
			
			UserBoardVO info = new UserBoardVO();
			List<EgovMap> fileList = null;
			List<EgovMap> fileList2 = null;
			
			info = userBCommonService.retrieveInfo(vo);
			model.addAttribute("info", info);
			
			fileList = userBCommonService.retrieveFile(vo);
			fileList2 = userBCommonService.retrieveFile2(vo);
			
			model.addAttribute("fileList", 	Casting.listMapToJSonString(fileList));
			model.addAttribute("fileList2", Casting.listMapToJSonString(fileList2));
			
		}

		return "/user/brd/" + vo.getBoardType() + "/brd" + vo.getBoardType2() + "Handle";
	}

	/**
	 * 목적 : 공통게시판 저장
	 * 매개변수 : ModelMap
	 * 매개변수 : UBDBoardoutVO
	 * 매개변수 : HttpServletRequest
	 * 반환값 : ModelAndView
	 * 개정이력 : 없음
	 */
	@SessionChecker
	@RequestMapping(value = "/user/brd/qna/qnaSave.do")	
	public ModelAndView registerQanda(@ModelAttribute("searchVO") UserBoardVO searchVO, UserBoardVO vo, BindingResult bindingResult, 
			ModelMap model, SessionStatus status, HttpServletRequest request, @RequestParam Map<Object, Object> param) throws Exception {

		HttpSession session = request.getSession();

		UserLoginVO userLoginVO = (UserLoginVO)session.getAttribute("UserLoginVO");

		String resultMsg = "";
		String resultPage = "";
		int result = 0;
		
		vo.setWrtPnNm(userLoginVO.getUserNm());
		vo.setWrtPnIp(WebUtil.getRemoteAddr(request));
		vo.setWrtPnNo(userLoginVO.getUserNo());
		vo.setUpdtPnIp(WebUtil.getRemoteAddr(request));
		vo.setUpdtPnNo(userLoginVO.getUserNo());
		
		String resultString = "";
		
		String bdotSeq = vo.getBdotSeq();
		if ( bdotSeq != null && !"".equals(bdotSeq) ) {
			vo.setBdotDc("02");
			result = userBCommonService.modifyQanda(vo);
		} else {
			resultString = userBCommonService.bdotSeq();
			vo.setBdotSeq(resultString);
			
			vo.setBdotDc("01");
			//vo.setBlbdHistDc("I");
			result = userBCommonService.registerQanda(vo);
		}

		if ( bdotSeq != null && !"".equals(bdotSeq) ) {
			if (result > 0) {
				resultMsg = egovMessageSource.getMessage("success.common.update");
			} else {
				resultMsg = egovMessageSource.getMessage("fail.common.update");
			}
			
			resultPage = "/user/brd/qna/qnaHandle.do";
		} else {
		
			if (result > 0) {
				resultMsg = egovMessageSource.getMessage("success.common.insert");
				resultPage = "/user/brd/qna/qna.do";
				
				//param.put("bdotSeq", vo.getBdotSeq());
				
				param.clear();
			} else {
				resultMsg = egovMessageSource.getMessage("fail.common.insert");
				resultPage = "/user/brd/qna/qnaHandle.do";
				
				//param.clear();
			}
		}
		
		return PageMove.alertAndRedirectPost(model, resultPage, resultMsg, param);
	}
	
	/**
	  * 목적 		: 공통게시판 삭제 처리
	  * 매개변수 	: ModelMap 
	  * 매개변수 	: NWrk300VO 
	  * 매개변수 	: HttpServletRequest 
	  * 반환값 	: String
	  * 개정이력 	: 없음
	  */
	@SessionChecker
	@RequestMapping(value = "/user/brd/qna/qnaDelete.json", headers="Accept=application/json" )
	public ModelAndView recruitDelete(ModelMap model, @RequestBody UserBoardVO paramVO, HttpServletRequest request) throws Exception {
		
		String resultMsg 				= "OK";
		String completeYn 				= "Y";
		
		int result						= 0;
		
		paramVO.setWrtPnNo(SessionUtil.getUserNo());
		paramVO.setUpdtPnNo(SessionUtil.getUserNo());
		paramVO.setWrtPnIp(WebUtil.getRemoteAddr(request));
		paramVO.setUpdtPnIp(WebUtil.getRemoteAddr(request));
		
		UserBoardVO info = userBCommonService.retrieveInfo(paramVO);
		if ( info == null ) {
			resultMsg 	= "QNA 상세정보가 없습니다.";
			completeYn	= "N";
		}
		if ( !"N".equals(completeYn) && info.getBdotCn2() != null && !"".equals(info.getBdotCn2()) ) {
			resultMsg 	= "답변이 달려 있어서 삭제할 수 없읍니다.";
			completeYn	= "N";
		}
		
		if ( !"N".equals(completeYn) ) {
			paramVO.setBdotDc("01");
			// 삭제
			result = userBCommonService.delete(paramVO);
			if(result > 0) {
				resultMsg 	= egovMessageSource.getMessage("success.common.delete");
				completeYn	= "Y";
			} else {
				resultMsg 	= egovMessageSource.getMessage("fail.common.delete");
				completeYn	= "N";
			}
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("resultMsg", 	resultMsg);
		resultMap.put("completeYn", completeYn);
		
		return new ModelAndView("jsonView", resultMap);
	}
}

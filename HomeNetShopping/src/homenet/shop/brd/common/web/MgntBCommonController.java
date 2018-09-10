package homenet.shop.brd.common.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import homenet.shop.brd.common.service.BoardVO;
import homenet.shop.brd.common.service.MgntBCommonService;
import prjframework.common.util.Casting;
import prjframework.common.util.PageMove;
import prjframework.common.util.SessionUtil;
import prjframework.common.util.StringUtil;
import prjframework.common.util.WebUtil;

/**
 * -----------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------------
 * 소속 : 고려대학교 경력개발센터
 * 수정일 : 2018.03.15
 * 수정자 : 엄재덕
 * 수정내용 : 최초생성
 * ------------------------------------------------------------------------
 */
@Controller
public class MgntBCommonController {
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "mgntBCommonService")
	private MgntBCommonService mgntBCommonService;
	
	/**
	 * 목적 : 게시판 화면 오픈
	 * 매개변수 : ModelMap
	 * 매개변수 : BoardoutVO
	 * 매개변수 : HttpServletRequest
	 * 반환값 : String
	 * 개정이력 : 없음
	 */	
	@RequestMapping(value = { "/mgnt/brd/notice/notice.do"		// 공지사항 
			, "/mgnt/brd/faq/faq.do"							// FAQ 
			, "/mgnt/brd/qa/qa.do"								// QNA 
			, "/mgnt/brd/data/data.do"							// 취업노하우 
			})
	public String initBoardout(@ModelAttribute("searchVO") BoardVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		//BoardVO chkBoardVO = new BoardVO();

		String urlPath = request.getRequestURI();

		if (urlPath.indexOf("/mgnt/brd/notice/notice.do") > -1) {		// 공지사항
			vo.setBoardType("notice");
			vo.setBoardType2("Notice");
			vo.setBlbdDvCd("001");
			vo.setListUrl("/mgnt/brd/notice/notice.do");
			vo.setHandleUrl("/mgnt/brd/notice/noticeHandle.do");
		} else if (urlPath.indexOf("/mgnt/brd/faq/faq.do") > -1) {		// FAQ
			vo.setBoardType("faq");
			vo.setBoardType2("Faq");
			vo.setBlbdDvCd("002");
			vo.setListUrl("/mgnt/brd/faq/faq.do");
			vo.setHandleUrl("/mgnt/brd/faq/faqHandle.do");
		} else if (urlPath.indexOf("/mgnt/brd/qa/qa.do") > -1) {		// QNA
			vo.setBoardType("qa");
			vo.setBoardType2("Qa");
			vo.setBlbdDvCd("003");
			vo.setListUrl("/mgnt/brd/qa/qa.do");
			vo.setHandleUrl("/mgnt/brd/qa/qaHandle.do");
		} else if (urlPath.indexOf("/mgnt/brd/data/data.do") > -1) {	// 취업노하우
			vo.setBoardType("data");
			vo.setBoardType2("Data");
			vo.setBlbdDvCd("004");
			vo.setListUrl("/mgnt/brd/data/data.do");
			vo.setHandleUrl("/mgnt/brd/data/dataHandle.do");
		}

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(vo.getCurrentPage());
		paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		paginationInfo.setPageSize(vo.getPageSize());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		vo.setSearchToppNotcYn("N");
		List<EgovMap> list = mgntBCommonService.retrieveList(vo);
		int totalCount = mgntBCommonService.retrieveCount(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		BoardVO notiParamVO	= new BoardVO();
		notiParamVO.setSearchToppNotcYn("Y");
		notiParamVO.setBlbdDvCd(vo.getBlbdDvCd());
		notiParamVO.setFirstIndex(0);
		notiParamVO.setLastIndex(0);
		notiParamVO.setRecordCountPerPage(0);
		List<EgovMap> notiList = mgntBCommonService.retrieveList(notiParamVO);
		
		
		model.addAttribute("resultList", 		list);
		model.addAttribute("resultCnt", 		totalCount);
        model.addAttribute("paginationInfo", 	paginationInfo);
        model.addAttribute("searchVO", 			vo);
        model.addAttribute("resultNotiList", 	notiList);
		

		return "mgnt/brd/" + vo.getBoardType() + "/brd" + vo.getBoardType2() + "List";
	}

	/**
	 * 목적 : 게시판 등록/수정
	 * 매개변수 : ModelMap
	 * 매개변수 : BoardoutVO
	 * 매개변수 : HttpServletRequest
	 * 반환값 : String
	 * 개정이력 : 없음
	 */
	@RequestMapping(value = { "/mgnt/brd/notice/noticeHandle.do" 		// 공지사항
			, "/mgnt/brd/faq/faqHandle.do"								// FAQ 	
			, "/mgnt/brd/qa/qaHandle.do"								// QNA
			, "/mgnt/brd/data/dataHandle.do"							// 취업노하우
		})
	public String retrieveBoard(ModelMap model, @ModelAttribute("searchVO") BoardVO vo, HttpServletRequest request) throws Exception {

		String urlPath = request.getRequestURI();

		if (urlPath.indexOf("/mgnt/brd/notice/noticeHandle.do") > -1) {		// 공지사항
			vo.setBoardType("notice");
			vo.setBoardType2("Notice");
			vo.setListUrl("/mgnt/brd/notice/notice.do");
			vo.setHandleUrl("/mgnt/brd/notice/noticeHandle.do");
		} else if (urlPath.indexOf("/mgnt/brd/faq/faqHandle.do") > -1) {	// FAQ
			vo.setBoardType("faq");
			vo.setBoardType2("Faq");
			vo.setListUrl("/mgnt/brd/faq/faq.do");
			vo.setHandleUrl("/mgnt/brd/faq/faqHandle.do");
		} else if (urlPath.indexOf("/mgnt/brd/qa/qaHandle.do") > -1) {		// QNA
			vo.setBoardType("qa");
			vo.setBoardType2("Qa");
			vo.setListUrl("/mgnt/brd/qa/qa.do");
			vo.setHandleUrl("/mgnt/brd/qa/qaHandle.do");
		} else if (urlPath.indexOf("/mgnt/brd/data/dataHandle.do") > -1) {		// 취업노하우
			vo.setBoardType("data");
			vo.setBoardType2("Data");
			vo.setListUrl("/mgnt/brd/data/data.do");
			vo.setHandleUrl("/mgnt/brd/data/dataHandle.do");
		}

		BoardVO info = new BoardVO();
		List<EgovMap> fileList = null;
		List<EgovMap> fileList1 = null;
		List<EgovMap> fileList2 = null;

		if (vo.getCmd().equals("U")) {
			
			if (vo.getBlbdDvCd() != null && vo.getBlbdDvCd().equals("001") && vo.getAnsYn().equals("N")) {

				vo.setUpdtPnNo(SessionUtil.getUserNo());

				mgntBCommonService.modifyAns(vo);
			}

			info = mgntBCommonService.retrieveInfo(vo);
			fileList = mgntBCommonService.retrieveFile(vo);
			fileList1 = mgntBCommonService.retrieveFileThumb(vo);
			fileList2 = mgntBCommonService.retrieveFileAns(vo);
			
			model.addAttribute("fileList", 	Casting.listMapToJSonString(fileList));
			model.addAttribute("fileList1", Casting.listMapToJSonString(fileList1));
			model.addAttribute("fileList2", Casting.listMapToJSonString(fileList2));
		}

		model.addAttribute("info", info);

		return "mgnt/brd/" + vo.getBoardType() + "/brd" + vo.getBoardType2() + "Handle";
	}

	/**
	 * 목적 : 게시판 저장
	 * 매개변수 : ModelMap
	 * 매개변수 : BoardoutVO
	 * 매개변수 : HttpServletRequest
	 * 반환값 : ModelAndView
	 * 개정이력 : 없음
	 */
	@RequestMapping(value = "/mgnt/brd/common/saveBoard.do")
	public ModelAndView register(@ModelAttribute("searchVO") BoardVO searchVO, BoardVO vo, BindingResult bindingResult
			, ModelMap model, SessionStatus status, HttpServletRequest request, @RequestParam Map<Object, Object> param) throws Exception {

		String listUrl = vo.getListUrl();
		String hadnleUrl = vo.getHandleUrl();

		vo.setWrtPnIp(WebUtil.getRemoteAddr(request));
		vo.setWrtPnNo(SessionUtil.getUserNo());
		vo.setUpdtPnNo(SessionUtil.getUserNo());

		String resultMsg = "";
		String resultPage = "";
		int result = 0;

		String notcSdt = vo.getNotcSdt();
		notcSdt	= StringUtil.replace(notcSdt, "-", "");
		notcSdt	= StringUtil.replace(notcSdt, ".", "");
		vo.setNotcSdt(notcSdt);
		
		String notcEdt = vo.getNotcEdt();
		notcEdt	= StringUtil.replace(notcEdt, "-", "");
		notcEdt	= StringUtil.replace(notcEdt, ".", "");
		vo.setNotcEdt(notcEdt);
		
		if (vo.getCmd().equals("I")) {

			String resultString = mgntBCommonService.bdotSeq();
			vo.setBdotSeq(resultString);

			vo.setBdotDc("01");
			vo.setBlbdHistDc("I");

			result = mgntBCommonService.register(vo);

			if (result > 0) {
				resultMsg = egovMessageSource.getMessage("success.common.insert");
				resultPage = listUrl;
			} else {
				resultMsg = egovMessageSource.getMessage("fail.common.insert");
				resultPage = hadnleUrl;
			}
			
			param.clear();
		} else {

			vo.setBdotDc("01");
			vo.setBlbdHistDc("U");

			result = mgntBCommonService.modify(vo, request);

			if (result > 0) {
				if (vo.getAnsUseYn() != null && vo.getAnsUseYn().equals("Y")) {
					resultMsg = egovMessageSource.getMessage("success.common.insert");
				} else {
					resultMsg = egovMessageSource.getMessage("success.common.update");
				}

				resultPage = listUrl;
			} else {

				if (vo.getAnsUseYn() != null && vo.getAnsUseYn().equals("Y")) {
					resultMsg = egovMessageSource.getMessage("fail.common.insert");
				} else {
					resultMsg = egovMessageSource.getMessage("fail.common.update");
				}

				resultPage = hadnleUrl;
			}
		}

		return PageMove.alertAndRedirectNoPost(model, resultPage, resultMsg, param);
	}

	/**
	 * 목적 : 게시판 삭제
	 * 매개변수 : ModelMap
	 * 매개변수 : BoardoutVO
	 * 매개변수 : HttpServletRequest
	 * 반환값 : ModelAndView
	 * 개정이력 : 없음
	 */
	@RequestMapping(value = "/mgnt/brd/common/deleteBoard.do")
	public ModelAndView delete(@ModelAttribute("searchVO") BoardVO searchVO, BoardVO vo, ModelMap model
			, SessionStatus status, HttpServletRequest request, @RequestParam Map<Object, Object> param) throws Exception {

		String listUrl = vo.getListUrl();
		String hadnleUrl = vo.getHandleUrl();

		HttpSession session = request.getSession();

		vo.setUpdtPnNo(SessionUtil.getUserNo());
		vo.setDelPnNo(SessionUtil.getUserNo());

		String resultMsg = "";
		String resultPage = "";
		int result = 0;

		vo.setBlbdHistDc("D");

		result = mgntBCommonService.delete(vo);

		if (result > 0) {
			resultMsg = egovMessageSource.getMessage("success.common.delete");
			resultPage = listUrl;
		} else {
			resultMsg = egovMessageSource.getMessage("fail.common.delete");
			resultPage = hadnleUrl;
		}

		return PageMove.alertAndRedirectNoPost(model, resultPage, resultMsg, param);
	}

}
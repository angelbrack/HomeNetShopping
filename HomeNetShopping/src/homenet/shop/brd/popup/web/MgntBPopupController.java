package homenet.shop.brd.popup.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import homenet.shop.brd.popup.service.MgntBPopupService;
import homenet.shop.brd.popup.service.MgntBPopupVO;
import prjframework.common.file.service.PrjframeworkCmmnFileService;
import prjframework.common.util.Casting;
import prjframework.common.util.PageMove;
import prjframework.common.util.SessionUtil;

@Controller
public class MgntBPopupController {

	@Resource(name = "MgntBPopupService")
	private MgntBPopupService MgntBPopupService; 

	@Resource(name = "prjframeworkCmmnFileService")
	private PrjframeworkCmmnFileService prjframeworkCmmnFileService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@RequestMapping(value = "/mgnt/brd/initMgntBPopup.do")
	public String init(@ModelAttribute("searchVO") MgntBPopupVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(vo.getCurrentPage());
		paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		paginationInfo.setPageSize(vo.getPageSize());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<EgovMap> list = MgntBPopupService.retrieveList(vo);
		int totalCount = MgntBPopupService.retrieveCount(vo);

		paginationInfo.setTotalRecordCount(totalCount);
		model.addAttribute("resultList", list);
		model.addAttribute("resultCnt", totalCount);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("searchVO", vo);
        
		return "mgnt/brd/popup/brdPopupList";
	}


	@RequestMapping(value = "/mgnt/brd/handleMgntBPopup.do")
	public String retrieve(@ModelAttribute("searchVO") MgntBPopupVO vo, Model model, HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		MgntBPopupVO info = new MgntBPopupVO();
		List<EgovMap> fileList = null;

		if (vo.getCmd().equals("U")) {
			info = MgntBPopupService.retrieveInfo(vo);
			fileList = MgntBPopupService.retrieveFile(vo);
			model.addAttribute("fileList", Casting.listMapToJSonString(fileList));
		}

		model.addAttribute("info", info);
		return "mgnt/brd/popup/brdPopupHandle";
	}

	
	@RequestMapping(value = "/mgnt/brd/registerMgntBPopup.do")
	public ModelAndView register(@ModelAttribute("searchVO") MgntBPopupVO searchVO, MgntBPopupVO vo, BindingResult bindingResult
			, ModelMap model, SessionStatus status, HttpServletRequest request, @RequestParam Map<Object, Object> param) throws Exception {

		vo.setWrtPnIp(request.getRemoteAddr());
		vo.setWrtPnNo(SessionUtil.getUserNo());
		vo.setUpdtPnNo(SessionUtil.getUserNo());

		String resultMsg = null;
		String resultPage = "";
		int result = 0;

		if (vo.getCmd().equals("I")) {
			result = MgntBPopupService.register(vo);

			if (result > 0) {
				resultMsg = egovMessageSource.getMessage("success.common.insert");
				resultPage = "/mgnt/brd/initMgntBPopup.do";
			} else {
				resultMsg = egovMessageSource.getMessage("fail.common.insert");
				resultPage = "/mgnt/brd/handleMgntBPopup.do";
			}
		} else {
			result = MgntBPopupService.modify(vo);

			if (result > 0) {
				resultMsg = egovMessageSource.getMessage("success.common.update");
				resultPage = "/mgnt/brd/initMgntBPopup.do";
			} else {
				resultMsg = egovMessageSource.getMessage("fail.common.update");
				resultPage = "/mgnt/brd/handleMgntBPopup.do";
			}
		}

		return PageMove.alertAndRedirectNoPost(model, resultPage, resultMsg, param);
	}

	@RequestMapping(value = "/mgnt/brd/deleteMgntBPopup.do")
	public ModelAndView delete(@ModelAttribute("searchVO") MgntBPopupVO searchVO, MgntBPopupVO vo, ModelMap model
			, SessionStatus status, HttpServletRequest request, @RequestParam Map<Object, Object> param) throws Exception {

		String resultMsg = null;
		String resultPage = "";

		int result = 0;

		result = MgntBPopupService.delete(vo);

		if (result > 0) {
			resultMsg = egovMessageSource.getMessage("success.common.delete");
			resultPage = "/mgnt/brd/initMgntBPopup.do";
		} else {
			resultMsg = egovMessageSource.getMessage("fail.common.delete");
			resultPage = "/mgnt/brd/handleMgntBPopup.do";
		}

		return PageMove.alertAndRedirectNoPost(model, resultPage, resultMsg, param);

	}

	
}
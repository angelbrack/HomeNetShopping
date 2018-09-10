package homenet.shop.brd.banner.web;

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
import homenet.shop.brd.banner.service.MgntBBannerService;
import homenet.shop.brd.banner.service.MgntBBannerVO;
import prjframework.common.file.service.PrjframeworkCmmnFileService;
import prjframework.common.util.Casting;
import prjframework.common.util.PageMove;
import prjframework.common.util.SessionUtil;

@Controller
public class MgntBBannerController {

	@Resource(name = "MgntBBannerService")
	private MgntBBannerService MgntBBannerService; 

	@Resource(name = "prjframeworkCmmnFileService")
	private PrjframeworkCmmnFileService prjframeworkCmmnFileService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@RequestMapping(value = "/mgnt/brd/initMgntBBanner.do")
	public String init(@ModelAttribute("searchVO") MgntBBannerVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(vo.getCurrentPage());
		paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		paginationInfo.setPageSize(vo.getPageSize());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<EgovMap> list = MgntBBannerService.retrieveList(vo);
		int totalCount = MgntBBannerService.retrieveCount(vo);

		paginationInfo.setTotalRecordCount(totalCount);
		model.addAttribute("resultList", list);
		model.addAttribute("resultCnt", totalCount);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("searchVO", vo);
        
		return "mgnt/brd/banner/brdBannerList";
	}


	@RequestMapping(value = "/mgnt/brd/handleMgntBBanner.do")
	public String retrieve(@ModelAttribute("searchVO") MgntBBannerVO vo, Model model, HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		MgntBBannerVO info = new MgntBBannerVO();
		List<EgovMap> fileList = null;

		if (vo.getCmd().equals("U")) {
			info = MgntBBannerService.retrieveInfo(vo);
			fileList = MgntBBannerService.retrieveFile(vo);
			model.addAttribute("fileList", Casting.listMapToJSonString(fileList));
		}

		model.addAttribute("info", info);
		return "mgnt/brd/banner/brdBannerHandle";
	}

	
	@RequestMapping(value = "/mgnt/brd/registerMgntBBanner.do")
	public ModelAndView register(@ModelAttribute("searchVO") MgntBBannerVO searchVO, MgntBBannerVO vo, BindingResult bindingResult
			, ModelMap model, SessionStatus status, HttpServletRequest request, @RequestParam Map<Object, Object> param) throws Exception {

		vo.setWrtPnIp(request.getRemoteAddr());
		vo.setWrtPnNo(SessionUtil.getUserNo());
		vo.setUpdtPnNo(SessionUtil.getUserNo());

		String resultMsg = null;
		String resultPage = "";
		int result = 0;

		if (vo.getCmd().equals("I")) {
			result = MgntBBannerService.register(vo);

			if (result > 0) {
				resultMsg = egovMessageSource.getMessage("success.common.insert");
				resultPage = "/mgnt/brd/initMgntBBanner.do";
			} else {
				resultMsg = egovMessageSource.getMessage("fail.common.insert");
				resultPage = "/mgnt/brd/handleMgntBBanner.do";
			}
		} else {
			result = MgntBBannerService.modify(vo);

			if (result > 0) {
				resultMsg = egovMessageSource.getMessage("success.common.update");
				resultPage = "/mgnt/brd/initMgntBBanner.do";
			} else {
				resultMsg = egovMessageSource.getMessage("fail.common.update");
				resultPage = "/mgnt/brd/handleMgntBBanner.do";
			}
		}

		return PageMove.alertAndRedirectNoPost(model, resultPage, resultMsg, param);
	}

	@RequestMapping(value = "/mgnt/brd/deleteMgntBBanner.do")
	public ModelAndView delete(@ModelAttribute("searchVO") MgntBBannerVO searchVO, MgntBBannerVO vo, ModelMap model
			, SessionStatus status, HttpServletRequest request, @RequestParam Map<Object, Object> param) throws Exception {

		String resultMsg = null;
		String resultPage = "";

		int result = 0;

		result = MgntBBannerService.delete(vo);

		if (result > 0) {
			resultMsg = egovMessageSource.getMessage("success.common.delete");
			resultPage = "/mgnt/brd/initMgntBBanner.do";
		} else {
			resultMsg = egovMessageSource.getMessage("fail.common.delete");
			resultPage = "/mgnt/brd/handleMgntBBanner.do";
		}

		return PageMove.alertAndRedirectNoPost(model, resultPage, resultMsg, param);

	}

	
}
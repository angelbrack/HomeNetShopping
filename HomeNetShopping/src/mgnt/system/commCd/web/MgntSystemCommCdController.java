package mgnt.system.commCd.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import prjframework.common.annotation.ValidSaveToken;
import prjframework.common.util.PageMove;
import prjframework.common.util.SessionUtil;
import site.code.service.CodeService;
import site.code.service.CodeVO;

@Controller
public class MgntSystemCommCdController {
	
	@Resource(name = "codeService")
	private CodeService codeService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@RequestMapping("/mgnt/system/CommCdListAction.do")
	public String listCommCd(@ModelAttribute("searchVO") CodeVO codeVO, ModelMap model,
			HttpServletRequest request) throws Exception {

		/** 관리자 로그인 체크 start */
		if(!SessionUtil.isLogin()) {
			return "mgnt/home/login/login";
		}
		/** 관리자 로그인 체크 end */
		
		codeVO.setPageUnit(codeVO.getPageUnit());
		codeVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(codeVO.getCurrentPage());
		paginationInfo.setRecordCountPerPage(codeVO.getRecordCountPerPage());
		paginationInfo.setPageSize(codeVO.getPageSize());

		codeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		codeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		codeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		Map<String, Object> map = codeService.listCommon(codeVO);

		paginationInfo.setTotalRecordCount(Integer.parseInt((String) map.get("resultCount")));

		model.addAttribute("backCommonCodeVO", 	codeVO);
		model.addAttribute("resultList", 		map.get("resultList"));
		model.addAttribute("resultCount", 		map.get("resultCount"));
		model.addAttribute("idList", 			map.get("idList"));
		model.addAttribute("paginationInfo", 	paginationInfo);

		return "mgnt/system/commCd/sysCommCdList";
	}
	
	
	@RequestMapping(value = "/mgnt/system/CommCdListAction.ajax", headers="Accept=application/json" )
	public ModelAndView commonCodeList(ModelMap model, @RequestBody CodeVO codeVO, HttpServletRequest request) throws Exception {
		
		List<CodeVO> codeList = codeService.getCodeGroupSubList(codeVO);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("codeList", 	codeList);
		
		return new ModelAndView("jsonView", resultMap);		
	}
	
	@RequestMapping("/mgnt/system/CommCdEditAction.do")
	public String editCommon(@ModelAttribute("searchVO") CodeVO codeVO, ModelMap model,
			HttpServletRequest request) throws Exception {
	
		/** 관리자 로그인 체크 start */
		if(!SessionUtil.isLogin()) {
			return "mgnt/home/login/login";
		}
		/** 관리자 로그인 체크 end */
		
		if(codeVO.getCmd().equals("U")) {			
			CodeVO vo = codeService.editCommon(codeVO);
			
			vo.setCmd("U");			
			
			model.addAttribute("info", vo);
		}
		
		
		return "mgnt/system/commCd/sysCommCdHandle";
	}
	
	@ValidSaveToken	//sub 토큰 사용시 사용
	@RequestMapping("/mgnt/system/CommCdSaveAction.do")
	public ModelAndView saveCommon(@ModelAttribute("searchVO") @Valid CodeVO codeVO,
			BindingResult bindingResult, SessionStatus status, ModelMap model, HttpServletRequest request, @RequestParam Map<Object, Object> param) throws Exception {
		
		String msg = "";
		String userNo = "";
		/** 관리자 로그인 체크 start */
		if(SessionUtil.isLogin()) {
			userNo = SessionUtil.getUserNo();
		}
		/** 관리자 로그인 체크 end */
		
		codeVO.setWrtPnNo(userNo);
		codeVO.setUpdtPnNo(userNo);

		//VO Valid check
		if(bindingResult.hasErrors()) {
			return new ModelAndView("mgnt/system/commCd/sysCommCdHandle");
		}
		
		if(codeVO.getCmd().equals("I")) {
			codeService.insertCommonInfo(codeVO);
			msg = egovMessageSource.getMessage("success.common.insert");
		} else {
			codeService.updateCommonInfo(codeVO);
			msg = egovMessageSource.getMessage("success.common.update");
		}
		
		
		 return PageMove.alertAndRedirectNoPost(model, "/mgnt/system/CommCdListAction.do", msg, param);
	}
	
	
	@RequestMapping("/mgnt/system/CommCdDeleteAction.do")
	public ModelAndView deleteCommon(@ModelAttribute("searchVO") CodeVO codeVO,
			BindingResult bindingResult, SessionStatus status, ModelMap model, HttpServletRequest request, @RequestParam Map<Object, Object> param) throws Exception {
		
		String msg = "";
		
		codeService.deleteCommonInfo(codeVO);
		msg = egovMessageSource.getMessage("success.common.delete");
		
		 return PageMove.alertAndRedirectNoPost(model, "/mgnt/system/CommCdListAction.do", msg, param);
	}

}

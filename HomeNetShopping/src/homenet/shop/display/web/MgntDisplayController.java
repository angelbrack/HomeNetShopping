package homenet.shop.display.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import homenet.shop.display.service.DispImgInfoVO;
import homenet.shop.display.service.DispShopBaseVO;
import homenet.shop.display.service.DisplayService;
import prjframework.common.util.Casting;
import prjframework.common.util.SessionUtil;
import prjframework.common.util.WebUtil;

/**
 * <p>전시 관리 Controller</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 18.</li>
 * </ul>
 */
@Controller
public class MgntDisplayController {
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Autowired
	@Qualifier("displayService")
	private DisplayService displayService;
	
	/*
	 * 전시매장 리스트
	 * 
	 * @param  : DispShopBaseVO paramVO
	 * @param  : ModelMap model
	 * @param  : HttpServletRequest request
	 * @param  : HttpServletResponse response
	 * @return : String 
	 */
	@RequestMapping(value="/mgnt/display/initMgntDisplay.do")
	public String initMgntDisplay(@ModelAttribute("searchVO") DispShopBaseVO paramVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(paramVO.getCurrentPage());
		paginationInfo.setRecordCountPerPage(paramVO.getRecordCountPerPage());
		paginationInfo.setPageSize(paramVO.getPageSize());
		
		paramVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		paramVO.setLastIndex(paginationInfo.getLastRecordIndex());
		paramVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totalCount = 0;
		List<DispShopBaseVO> list = displayService.selectDisplayList(paramVO);
		
		if ( list != null && list.size() > 0 ) {
			totalCount = list.get(0).getTotalCount();
		}
		
		paginationInfo.setTotalRecordCount(totalCount);
		
		model.addAttribute("list", 				list);
		model.addAttribute("totalCount", 		totalCount);
        model.addAttribute("paginationInfo", 	paginationInfo);
		
        return "mgnt/display/displayList";
	}
	
	/**
	  * 목적 		: 전시매장 등록/수정 화면 - 팝업
	  * @param 	: DispShopBaseVO paramVO
	  * @param 	: ModelMap model
	  * @param  : HttpServletRequest request
	  * @param  : HttpServletResponse response
	  * @return : String
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = "/mgnt/display/displayHandlePopup.do")
	public String displayHandlePopup(@ModelAttribute("searchVO") DispShopBaseVO paramVO, ModelMap model, 
			HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		DispShopBaseVO info = null;
	
		paramVO.setFirstIndex(0);
		paramVO.setLastIndex(0);
		paramVO.setRecordCountPerPage(0);
		
		if (paramVO.getCmd().equals("U")) {
			info = displayService.selectDisplayInfo(paramVO);
			
			List<DispImgInfoVO> dispImgInfoList = displayService.selectDisplayImgInfoList(paramVO);
			if ( dispImgInfoList != null ) {
				model.addAttribute("fileList", 			Casting.listToJSonString(dispImgInfoList));
			}
		}

		model.addAttribute("info", info);
		return "mgnt/display/displayHandlePopup";
	}
	
	/**
	  * 목적 		: 전시매장정보 저장 처리
	  * @param 	: DispShopBaseVO paramVO
	  * @param  : HttpServletRequest request
	  * @return : ModelAndView json
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = "/mgnt/display/displaySave.json", headers="Accept=application/json" )
	public ModelAndView recruitSave(ModelMap model, @RequestBody DispShopBaseVO paramVO, HttpServletRequest request) throws Exception {
		
		String resultMsg 				= "OK";
		String completeYn 				= "Y";
		
		int result						= 0;
		
		paramVO.setWrtPnNo(SessionUtil.getUserNo());
		paramVO.setUpdtPnNo(SessionUtil.getUserNo());
		paramVO.setWrtPnIp(WebUtil.getRemoteAddr(request));
		paramVO.setUpdtPnIp(WebUtil.getRemoteAddr(request));
		
		// 저장
		result = displayService.saveDisplay(paramVO);
		if("I".equals(paramVO.getCmd())) {
			if(result > 0) {
				resultMsg 	= egovMessageSource.getMessage("success.common.insert");
				completeYn	= "Y";
			} else {
				resultMsg 	= egovMessageSource.getMessage("fail.common.insert");
				completeYn	= "N";
			}
		} else {
			if(result > 0) {
				resultMsg 	= egovMessageSource.getMessage("success.common.update");
				completeYn	= "Y";
			} else {
				resultMsg 	= egovMessageSource.getMessage("fail.common.update");
				completeYn	= "N";
			}
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("resultMsg", 	resultMsg);
		resultMap.put("completeYn", completeYn);
		
		return new ModelAndView("jsonView", resultMap);
	}
	
	/**
	  * 목적 		: 전시매장정보 삭제 처리
	  * @param 	: DispShopBaseVO paramVO
	  * @param  : HttpServletRequest request
	  * @return : ModelAndView json
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = "/mgnt/display/displayDelete.json", headers="Accept=application/json" )
	public ModelAndView recruitDelete(ModelMap model, @RequestBody DispShopBaseVO paramVO, HttpServletRequest request) throws Exception {
		
		String resultMsg 				= "OK";
		String completeYn 				= "Y";
		
		int result						= 0;
		
		paramVO.setWrtPnNo(SessionUtil.getUserNo());
		paramVO.setUpdtPnNo(SessionUtil.getUserNo());
		paramVO.setWrtPnIp(WebUtil.getRemoteAddr(request));
		paramVO.setUpdtPnIp(WebUtil.getRemoteAddr(request));
		
		// 삭제
		result = displayService.deleteDisplayBase(paramVO);
		if(result > 0) {
			resultMsg 	= egovMessageSource.getMessage("success.common.delete");
			completeYn	= "Y";
		} else {
			resultMsg 	= egovMessageSource.getMessage("fail.common.delete");
			completeYn	= "N";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("resultMsg", 	resultMsg);
		resultMap.put("completeYn", completeYn);
		
		return new ModelAndView("jsonView", resultMap);
	}

}

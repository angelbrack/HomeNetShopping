
package homenet.shop.brand.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import homenet.shop.brand.service.BrandService;
import homenet.shop.brand.service.BrndBaseVO;
import homenet.shop.brand.service.BrndImgInfoVO;
import prjframework.common.util.Casting;
import prjframework.common.util.SessionUtil;
import prjframework.common.util.WebUtil;

/**
 * <p>브랜드 관리 Controller</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 18.</li>
 * </ul>
 */
@Controller
public class MgntBrandController {
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Autowired
	@Qualifier("brandService")
	private BrandService brandService;
	
	/*
	 * 브랜드 리스트
	 * 
	 * @param  : BrndBaseVO paramVO
	 * @param  : ModelMap model
	 * @param  : HttpServletRequest request
	 * @param  : HttpServletResponse response
	 * @return : String 
	 */
	@RequestMapping(value="/mgnt/brand/initMgntBrand.do")
	public String initMgntBrand(@ModelAttribute("searchVO") BrndBaseVO paramVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(paramVO.getCurrentPage());
		paginationInfo.setRecordCountPerPage(paramVO.getRecordCountPerPage());
		paginationInfo.setPageSize(paramVO.getPageSize());
		
		paramVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		paramVO.setLastIndex(paginationInfo.getLastRecordIndex());
		paramVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totalCount = 0;
		List<BrndBaseVO> list = brandService.selectBrandList(paramVO);
		
		if ( list != null && list.size() > 0 ) {
			totalCount = list.get(0).getTotalCount();
		}
		
		paginationInfo.setTotalRecordCount(totalCount);
		
		model.addAttribute("list", 				list);
		model.addAttribute("totalCount", 		totalCount);
        model.addAttribute("paginationInfo", 	paginationInfo);
		
        return "mgnt/brand/brandList";
	}
	
	/**
	  * 목적 		: 브랜드 등록/수정 화면
	  * @param 	: BrndBaseVO paramVO
	  * @param 	: ModelMap model
	  * @param  : HttpServletRequest request
	  * @param  : HttpServletResponse response
	  * @return : String
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = "/mgnt/brand/brandHandle.do")
	public String brandHandle(@ModelAttribute("searchVO") BrndBaseVO paramVO, ModelMap model, 
			HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		BrndBaseVO info = null;
	
		paramVO.setFirstIndex(0);
		paramVO.setLastIndex(0);
		paramVO.setRecordCountPerPage(0);
		
		if (paramVO.getCmd().equals("U")) {
			info = brandService.selectBrandInfo(paramVO);
			
			BrndImgInfoVO brndImgInfoParamVO = new BrndImgInfoVO();
			brndImgInfoParamVO.setBrndNo(paramVO.getBrndNo());
			brndImgInfoParamVO.setFirstIndex(0);
			brndImgInfoParamVO.setRecordCountPerPage(0);
			
			List<BrndImgInfoVO> brndImgInfoList = brandService.selectBrandImgList(brndImgInfoParamVO);
			if ( brndImgInfoList != null ) {
				model.addAttribute("fileList", 			Casting.listToJSonString(brndImgInfoList));
			}
		}

		model.addAttribute("info", info);
		return "mgnt/brand/brandHandle";
	}
	
	/**
	  * 목적 		: 브랜드정보 저장 처리
	  * @param 	: BrndBaseVO paramVO
	  * @param  : HttpServletRequest request
	  * @return : ModelAndView json
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = "/mgnt/brand/brandSave.json", headers="Accept=application/json" )
	public ModelAndView recruitSave(ModelMap model, @RequestBody BrndBaseVO paramVO, HttpServletRequest request) throws Exception {
		
		String resultMsg 				= "OK";
		String completeYn 				= "Y";
		
		int result						= 0;
		
		paramVO.setWrtPnNo(SessionUtil.getUserNo());
		paramVO.setUpdtPnNo(SessionUtil.getUserNo());
		paramVO.setWrtPnIp(WebUtil.getRemoteAddr(request));
		paramVO.setUpdtPnIp(WebUtil.getRemoteAddr(request));
		
		// 저장
		result = brandService.saveBrand(paramVO);
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
	  * 목적 		: 브랜드정보 삭제 처리
	  * @param 	: BrndBaseVO paramVO
	  * @param  : HttpServletRequest request
	  * @return : ModelAndView json
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = "/mgnt/brand/brandDelete.json", headers="Accept=application/json" )
	public ModelAndView recruitDelete(ModelMap model, @RequestBody BrndBaseVO paramVO, HttpServletRequest request) throws Exception {
		
		String resultMsg 				= "OK";
		String completeYn 				= "Y";
		
		int result						= 0;
		
		paramVO.setWrtPnNo(SessionUtil.getUserNo());
		paramVO.setUpdtPnNo(SessionUtil.getUserNo());
		paramVO.setWrtPnIp(WebUtil.getRemoteAddr(request));
		paramVO.setUpdtPnIp(WebUtil.getRemoteAddr(request));
		
		// 삭제
		result = brandService.deleteBrand(paramVO);
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

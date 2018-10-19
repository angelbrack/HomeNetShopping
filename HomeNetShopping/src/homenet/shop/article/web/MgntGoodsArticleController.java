package homenet.shop.article.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import homenet.shop.article.service.GoodsArtcCdVO;
import homenet.shop.article.service.GoodsArticleService;
import homenet.shop.brand.service.BrndBaseVO;
import homenet.shop.brand.service.BrndImgInfoVO;
import prjframework.common.util.Casting;
import prjframework.common.util.SessionUtil;
import prjframework.common.util.WebUtil;
import sample.HTTPS_Example;

/**
 * <p>품목군 관리 Controller</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 10. 04.</li>
 * </ul>
 */
@Controller
public class MgntGoodsArticleController {
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Autowired
	@Qualifier("goodsArticleService")
	private GoodsArticleService goodsArticleService;
	
	/*
	 * 품목군 리스트
	 * 
	 * @param  : GoodsArtcCdVO paramVO
	 * @param  : ModelMap model
	 * @param  : HttpServletRequest request
	 * @param  : HttpServletResponse response
	 * @return : String 
	 */
	@RequestMapping(value="/mgnt/article/initMgntArticle.do")
	public String initMgntBrand(@ModelAttribute("searchVO") GoodsArtcCdVO paramVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "mgnt/article/articleHandle";
	}
	
	/*
	 * 품목군 리스트
	 * 
	 * @param  : GoodsArtcCdVO paramVO
	 * @param  : ModelMap model
	 * @param  : HttpServletRequest request
	 * @param  : HttpServletResponse response
	 * @return : String 
	 */
	@RequestMapping(value="/mgnt/article/selectGoodsArtcCdList.do")
	public String selectGoodsArtcCdList(@ModelAttribute("searchVO") GoodsArtcCdVO paramVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(paramVO.getCurrentPage());
		paginationInfo.setRecordCountPerPage(paramVO.getRecordCountPerPage());
		paginationInfo.setPageSize(paramVO.getPageSize());
		
		paramVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		paramVO.setLastIndex(paginationInfo.getLastRecordIndex());
		paramVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totalCount = 0;
		List<GoodsArtcCdVO> list = goodsArticleService.selectGoodsArtcCdList(paramVO);
		
		if ( list != null && list.size() > 0 ) {
			totalCount = list.size();
		}
		
		paginationInfo.setTotalRecordCount(totalCount);
		
		model.addAttribute("list", 				list);
		model.addAttribute("totalCount", 		totalCount);
        model.addAttribute("paginationInfo", 	paginationInfo);
		
        return "mgnt/article/articleList";
	}
	
	/**
	  * 목적 		: 품목군 Tree 리스트
	  * @param 	: GoodsArtcCdVO paramVO
	  * @param 	: ModelMap model
	  * @param  : HttpServletRequest request
	  * @param  : HttpServletResponse response
	  * @return : ModelAndView
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = "/mgnt/article/selectGoodsArtcCdTreeList.json", headers="Accept=application/json" )
	public ModelAndView selectGoodsArtcCdTreeList(ModelMap model, @RequestBody GoodsArtcCdVO paramVO, HttpServletRequest request) throws Exception {
		
		List<GoodsArtcCdVO> list = goodsArticleService.selectGoodsArtcCdTreeList(paramVO);
		
		model.addAttribute("list", 		list);
		
		return new ModelAndView("jsonView", model);
	}
	
	/**
	  * 목적 		: 품목군 상세 정보
	  * @param 	: GoodsArtcCdVO paramVO
	  * @param 	: ModelMap model
	  * @param  : HttpServletRequest request
	  * @param  : HttpServletResponse response
	  * @return : ModelAndView
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = "/mgnt/article/selectGoodsArtcCdInfo.json", headers="Accept=application/json" )
	public ModelAndView selectGoodsArtcCdInfo(ModelMap model, @RequestBody GoodsArtcCdVO paramVO, HttpServletRequest request) throws Exception {
		
		GoodsArtcCdVO info = goodsArticleService.selectGoodsArtcCdInfo(paramVO);
		
		model.addAttribute("info", 		info);
		
		return new ModelAndView("jsonView", model);
	}
	
	/**
	  * 목적 		: 품목군 저장
	  * @param 	: GoodsArtcCdVO paramVO
	  * @param 	: ModelMap model
	  * @param  : HttpServletRequest request
	  * @param  : HttpServletResponse response
	  * @return : ModelAndView
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = "/mgnt/article/articleSave.json", headers="Accept=application/json" )
	public ModelAndView articleSave(ModelMap model, @RequestBody GoodsArtcCdVO paramVO, HttpServletRequest request) throws Exception {

		String resultMsg 				= "OK";
		String completeYn 				= "Y";
		
		int result						= 0;
		
		paramVO.setWrtPnNo(SessionUtil.getUserNo());
		paramVO.setUpdtPnNo(SessionUtil.getUserNo());
		paramVO.setWrtPnIp(WebUtil.getRemoteAddr(request));
		paramVO.setUpdtPnIp(WebUtil.getRemoteAddr(request));
		
		// 저장
		result = goodsArticleService.saveGoodsArtcCd(paramVO);
		if(result > 0) {
			resultMsg 	= egovMessageSource.getMessage("success.common.insert");
			completeYn	= "Y";
		} else {
			resultMsg 	= egovMessageSource.getMessage("fail.common.insert");
			completeYn	= "N";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("resultMsg", 	resultMsg);
		resultMap.put("completeYn", completeYn);
		
		return new ModelAndView("jsonView", resultMap);
	}
	
	/**
	  * 목적 		: 품목군 저장
	  * @param 	: GoodsArtcCdVO paramVO
	  * @param 	: ModelMap model
	  * @param  : HttpServletRequest request
	  * @param  : HttpServletResponse response
	  * @return : ModelAndView
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = "/mgnt/article/articleDelete.json", headers="Accept=application/json" )
	public ModelAndView articleDelete(ModelMap model, @RequestBody GoodsArtcCdVO paramVO, HttpServletRequest request) throws Exception {

		String resultMsg 				= "OK";
		String completeYn 				= "Y";
		
		int result						= 0;
		
		// 저장
		result = goodsArticleService.deleteArticle(paramVO);
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
	
	/**
	  * 목적 		: 품목군 초기 등록
	  * @param 	: GoodsArtcCdVO paramVO
	  * @param 	: ModelMap model
	  * @param  : HttpServletRequest request
	  * @param  : HttpServletResponse response
	  * @return : ModelAndView
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = "/mgnt/article/initArticleSaveList.json", headers="Accept=application/json" )
	public ModelAndView articleSaveList(ModelMap model, @RequestBody GoodsArtcCdVO paramVO, HttpServletRequest request) throws Exception {

		String resultMsg 				= "OK";
		String completeYn 				= "Y";
		
		int result						= 0;
		
		HTTPS_Example obj = new HTTPS_Example();
		  
		String jsonString = obj.getHttpsConnection();
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
      
		JSONObject jsonData = (JSONObject) jsonObject.get("data");
		String wstEvtYn = (String)jsonData.get("wstEvtYn");
		JSONArray listGoodsArticleDto = (JSONArray) jsonData.get("listGoodsArticleDto");
		
		List<GoodsArtcCdVO> list	= new ArrayList<GoodsArtcCdVO>();
		GoodsArtcCdVO goodsArtcCdVO = null;
		
		for ( int i=0; i<listGoodsArticleDto.size(); i++ ) {
			JSONObject jsonArticle = (JSONObject)listGoodsArticleDto.get(i);
			
			String artcCd 			= (String) jsonArticle.get("artcCd");
			String ecArtcCd 		= (String) jsonArticle.get("ecArtcCd");
			String uprArtcCd 		= (String) jsonArticle.get("uprArtcCd");
			Long artcDpthNo 		= (Long) jsonArticle.get("artcDpthNo");
			String artcNm 			= (String) jsonArticle.get("artcNm");
			String artcFullNm 		= (String) jsonArticle.get("artcFullNm");
			Long onlBrchInvRt 		= (Long) jsonArticle.get("onlBrchInvRt");
			Long maxLmtQty 			= (Long) jsonArticle.get("maxLmtQty");
			Integer goodsMrgnRt 	= (Integer) jsonArticle.get("goodsMrgnRt");
			
			goodsArtcCdVO			= new GoodsArtcCdVO();
		
			goodsArtcCdVO.setArtcCd(artcCd);
			goodsArtcCdVO.setEcArtcCd(ecArtcCd);
			goodsArtcCdVO.setUprArtcCd(uprArtcCd);
			if ( artcDpthNo != null ) {
				goodsArtcCdVO.setArtcDpthNo(Integer.valueOf(artcDpthNo.intValue()));
			}
			goodsArtcCdVO.setArtcNm(artcNm);
			goodsArtcCdVO.setArtcFullNm(artcFullNm);
			if ( onlBrchInvRt != null ) {
				goodsArtcCdVO.setOnlBrchInvRt(Integer.valueOf(onlBrchInvRt.intValue()));
			}
			if ( maxLmtQty != null ) {
				goodsArtcCdVO.setMaxLmtQty(Integer.valueOf(maxLmtQty.intValue()));
			}
			goodsArtcCdVO.setGoodsMrgnRt(goodsMrgnRt);
			
			//goodsArtcCdVO.setWrtPnNo(SessionUtil.getUserNo());
			goodsArtcCdVO.setUpdtPnNo(SessionUtil.getUserNo());
			goodsArtcCdVO.setWrtPnNo("0");
			goodsArtcCdVO.setUpdtPnNo("0");
			goodsArtcCdVO.setWrtPnIp(WebUtil.getRemoteAddr(request));
			goodsArtcCdVO.setUpdtPnIp(WebUtil.getRemoteAddr(request));
			
			list.add(goodsArtcCdVO);
		}
		
		
		// 저장
		result = goodsArticleService.saveGoodsArtcCdList(list);
		if(result > 0) {
			resultMsg 	= egovMessageSource.getMessage("success.common.insert");
			completeYn	= "Y";
		} else {
			resultMsg 	= egovMessageSource.getMessage("fail.common.insert");
			completeYn	= "N";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("resultMsg", 	resultMsg);
		resultMap.put("completeYn", completeYn);
		
		return new ModelAndView("jsonView", resultMap);
	}

}

package homenet.shop.article.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	
	/**
	  * 목적 		: 브랜드 등록/수정 화면
	  * @param 	: GoodsArtcCdVO paramVO
	  * @param 	: ModelMap model
	  * @param  : HttpServletRequest request
	  * @param  : HttpServletResponse response
	  * @return : String
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = "/mgnt/article/articleSaveList.json", headers="Accept=application/json" )
	public ModelAndView recruitSave(ModelMap model, @RequestBody GoodsArtcCdVO paramVO, HttpServletRequest request) throws Exception {

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

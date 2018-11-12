package homenet.shop.goods.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import homenet.shop.goods.service.GoodsBaseVO;

/**
 * <p>전시 관리 Controller</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 18.</li>
 * </ul>
 */
@Controller
public class MgntGoodsController {
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/*
	 * 상품 리스트 화면
	 * 
	 * @param  : GoodsBaseVO paramVO
	 * @param  : ModelMap model
	 * @param  : HttpServletRequest request
	 * @param  : HttpServletResponse response
	 * @return : String 
	 */
	@RequestMapping(value="/mgnt/goods/initMgntGoods.do")
	public String initMgntDisplay(@ModelAttribute("searchVO") GoodsBaseVO paramVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		

        return "mgnt/goods/goodsList";
	}
	
	/*
	 * 상품 등록/수정 화면
	 * 
	 * @param  : GoodsBaseVO paramVO
	 * @param  : ModelMap model
	 * @param  : HttpServletRequest request
	 * @param  : HttpServletResponse response
	 * @return : String 
	 */
	@RequestMapping(value="/mgnt/goods/goodsHandle.do")
	public String goodsHandle(@ModelAttribute("searchVO") GoodsBaseVO paramVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		

        return "mgnt/goods/goodsHandle";
	}

}

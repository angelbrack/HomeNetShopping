package homenet.shop.brand.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import homenet.shop.brand.service.BrandService;
import homenet.shop.brand.service.BrndBaseVO;

/**
 * <p>브랜드 관리 Controller</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 18.</li>
 * </ul>
 */
@Controller
public class MgntBrandController {
	
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

}

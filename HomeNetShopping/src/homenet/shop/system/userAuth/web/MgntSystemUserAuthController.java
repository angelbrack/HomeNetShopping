package homenet.shop.system.userAuth.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import prjframework.common.annotation.ValidSaveToken;
import prjframework.common.dataaccess.util.DataMap;
import prjframework.common.util.PageMove;
import prjframework.common.util.SessionUtil;
import site.auth.service.AuthSearchVO;
import site.auth.service.AuthService;
import site.auth.service.AuthVO;
import site.code.service.CodeService;
import site.common.service.CommonIndvInfoInqHistService;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MgntSystemUserAuthController {
	
    /** 사용자권한 관리 **/
    @Resource(name = "authService")
    protected AuthService authService;
    
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	 
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	
    @Resource(name = "codeService")
    protected CodeService commonCodeService;
    
    //sub 개인정보이력 start
	@Resource(name = "commonIndvInfoInqHistService")
	protected CommonIndvInfoInqHistService commonIndvInfoInqHistService;
	
    /**
     * 공통으로 사용할 검색 모델 권한역할
     */
    @ModelAttribute(value = "optrAuthNoList")
    public List<Map<String, Object>> getAuthRoleList() throws Exception {
    	AuthSearchVO vo = new AuthSearchVO();
    	return authService.getAuthRoleList(vo);
    }
    
    
	/**
	 * 사용자 권한 조회 화면
	 * @return
	 */
	@RequestMapping(value = "/mgnt/system/UserAuthListAction.do")
	public String userList(ModelMap model, @ModelAttribute(value = "authSearchVO") AuthSearchVO authSearchVO,
			HttpServletRequest request) throws Exception {

		/** 관리자 로그인 체크 start */
		if(!SessionUtil.isLogin()) {
			return "mgnt/home/login/login";
		}
		/** 관리자 로그인 체크 end */
		
		/** pageing */
		authSearchVO.setPageUnit(authSearchVO.getPageUnit());
		authSearchVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(authSearchVO.getCurrentPage());
		paginationInfo.setRecordCountPerPage(authSearchVO.getRecordCountPerPage());
		paginationInfo.setPageSize(authSearchVO.getPageSize());

		authSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<Map<String, Object>> code550 = commonCodeService.getCommonCodeList("550");
		model.addAttribute("code550", code550);
		
		List<Map<String, Object>> code560 = commonCodeService.getCommonCodeList("560");
		model.addAttribute("code560", code560);
		
		List<Map<String, Object>> list = null;
		
		list = authService.selectUserList(authSearchVO);
		int totCnt = authService.selectUserListTotCnt(authSearchVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("list", list);
		model.addAttribute("authSearchVO", authSearchVO);
		model.addAttribute("paginationInfo", paginationInfo);
		
        
		return "/mgnt/system/userAuth/sysUserAuthList";
	}
	
	/**
	 * 사용자 권한 편집
	 * @return
	 */
	@RequestMapping(value="/mgnt/system/UserAuthHandleAction.do")
	public String userEdit(ModelMap model, @ModelAttribute(value = "authVO") AuthVO authVO, @ModelAttribute(value = "authSearchVO") AuthSearchVO authSearchVO,
			HttpServletRequest request, @RequestParam Map param) throws Exception {
		
		/** 관리자 로그인 체크 start */
		if(!SessionUtil.isLogin()) {
			return "mgnt/home/login/login";
		}
		/** 관리자 로그인 체크 end */
		
		/* 개인정보조회 이력 저장*/
		/*DataMap userMap = bureauUserService.selectUserInfo(bureauUserVO);
		
		String queryCn = (String) userMap.get("queryCn");
		
		param.put("USER_PGM_AUTH", (Map<String, String>)request.getAttribute("USER_PGM_AUTH"));
		param.put("queryCn", queryCn);
		
		commonIndvInfoInqHistService.insertIndvInfoInqHist(param);*/
		/* 개인정보조회 이력 저장*/
		
		// 사용자 정보
		Map<String, Object> userInfo = authService.selectUserInfo(authSearchVO);
		model.addAttribute("userInfo", userInfo);

		// 사용자 권한 정보
		List<Map<String, Object>> userAuthInfo = authService.selectAuthRole(authSearchVO);
		model.addAttribute("userAuthInfo", userAuthInfo);

		List<Map<String, Object>> code550 = commonCodeService.getCommonCodeList("550");
		model.addAttribute("code550", code550);

		List<Map<String, Object>> code670 = commonCodeService.getCommonCodeList("670");
		model.addAttribute("code670", code670);
		
		return "/mgnt/system/userAuth/sysUserAuthHandle";
	}
	
	/**
	 * 사용자 권한 저장
	 * @return
	 */
	@ValidSaveToken	//sub 토큰 사용시 사용
	@RequestMapping(value="/mgnt/system/UserAuthUpdateUserInfo.do")
	public ModelAndView updateUserInfo(HttpServletRequest request, @ModelAttribute(value = "authSearchVO") AuthSearchVO authSearchVO
			, @ModelAttribute(value = "authVO") @Valid AuthVO authVO , BindingResult bindingResult,  ModelMap model, @RequestParam Map<Object, Object> param) throws Exception {
		
		String msg = egovMessageSource.getMessage("fail.common.update");
		String userNo = "";
		/** 관리자 로그인 체크 start */
		if(SessionUtil.isLogin()) {
			userNo = SessionUtil.getUserNo();
		}
		/** 관리자 로그인 체크 end */
		
		authVO.setWrtPerNo(userNo);
		authVO.setRemoteAddr(request.getRemoteAddr());
		
		//VO Valid check
		if(bindingResult.hasErrors()) {
			return new ModelAndView("/mgnt/system/userAuth/sysUserAuthHandle");
		}
		
		int resultCount = authService.updateUserInfo(authVO);
		if (resultCount > 0) {
			msg = egovMessageSource.getMessage("success.common.update");
		}
		model.addAttribute("authSearchVO", authSearchVO);
		
		return PageMove.alertAndRedirectNoPost(model, "/mgnt/system/UserAuthListAction.do", msg, param);
	}
}

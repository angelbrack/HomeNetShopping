package mgnt.system.userRole.web;

import java.util.HashMap;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import prjframework.common.annotation.ValidSaveToken;
import prjframework.common.util.PageMove;
import prjframework.common.util.SessionUtil;
import site.code.service.CodeService;
import site.menu.service.MenuService;
import site.menu.service.MenuVO;
import site.userRole.service.UserRoleService;
import site.userRole.service.UserRoleVO;
import egovframework.com.cmm.EgovMessageSource;


@Controller
public class MgntSystemUserRoleController {

	/** 롤 관리 서비스 **/
    @Resource(name = "userRoleService")
    protected UserRoleService userRoleService;
    
	/** MenuService */
    @Resource(name = "menuService")
    protected MenuService menuService;
    
    @Resource(name = "codeService")
    protected CodeService commonCodeService;
    
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
    /**
     * 시스템 역할 목록 조회
     * @param model
     * @param vo
     * @return
     */
	@RequestMapping(value="/mgnt/system/UserRoleListAction.do")
	public String roleList(ModelMap model, UserRoleVO vo, 
			HttpServletRequest request) throws Exception {
		
		/** 관리자 로그인 체크 start */
		if(!SessionUtil.isLogin()) {
			return "mgnt/home/login/login";
		}
		/** 관리자 로그인 체크 end */
		
		List<Map<String, Object>> list = userRoleService.selectRoleList(vo);
		model.addAttribute("list", list);
		return "mgnt/system/userRole/sysUserRoleList";
	}
	
	/**
	 * 시스템 역할 정보 조회
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/mgnt/system/UserRoleHandleAction.do")
	public String roleEdit(@ModelAttribute("searchVO") UserRoleVO vo,
			BindingResult bindingResult, SessionStatus status, ModelMap model, HttpServletRequest request, @RequestParam Map<Object, Object> param) throws Exception {
		
		/** 관리자 로그인 체크 start */
		if(!SessionUtil.isLogin()) {
			return "mgnt/home/login/login";
		}
		
		MenuVO menuVo = new MenuVO();
		menuVo.setUseYn("Y");
		List<MenuVO> menuList = menuService.list(menuVo);
		model.put("menuList", menuList);
		
		//List<Map<String, Object>> subList = (List<Map<String,Object>>) userRoleService.selectRoleSubInfo(vo);
		//model.addAttribute("subList", subList);
		
		List<Map<String, Object>> authList = commonCodeService.getCommonCodeList("USER_AUTH_CD");
		model.addAttribute("authList", authList);
		UserRoleVO info = new UserRoleVO();
		if ("U".equals(vo.getCmd())) {
			info = userRoleService.selectRoleInfo(vo);
		}
		model.addAttribute("info", info);
		
		return "mgnt/system/userRole/sysUserRoleHandle";
	}
	
	/**
	 * 시스템 역할 정보 등록
	 * @param model
	 * @param vo
	 * @return
	 */
	@ValidSaveToken	//sub 토큰 사용시 사용
	@RequestMapping(value="/mgnt/system/UserRoleSaveAction.do")
	public ModelAndView roleSave(@ModelAttribute("searchVO") @Valid UserRoleVO vo,
			BindingResult bindingResult, SessionStatus status, ModelMap model, HttpServletRequest request, @RequestParam Map<Object, Object> param) throws Exception {
		String msg = egovMessageSource.getMessage("success.common.insert");
		String cmd = vo.getCmd();
		
		/** 관리자 로그인 체크 end */
		String userNo = "";
		/** 관리자 로그인 체크 start */
		if(SessionUtil.isLogin()) {
			userNo = SessionUtil.getUserNo();
		}
		/** 관리자 로그인 체크 end */
		
		vo.setLoginUserNo(userNo);
		vo.setRemoteAddr(request.getRemoteAddr());
		
		//VO Valid check
		if(bindingResult.hasErrors()) {
			MenuVO menuVo = new MenuVO();
			menuVo.setUseYn("Y");
			List<MenuVO> menuList = menuService.list(menuVo);
			model.put("menuList", menuList);
			
			//List<Map<String, Object>> subList = (List<Map<String,Object>>) userRoleService.selectRoleSubInfo(vo);
			//model.addAttribute("subList", subList);
			
			List<Map<String, Object>> authList = commonCodeService.getCommonCodeList("USER_AUTH_CD");
			model.addAttribute("authList", authList);
			UserRoleVO info = new UserRoleVO();
			if ("U".equals(vo.getCmd())) {
				info = userRoleService.selectRoleInfo(vo);
			}
			model.addAttribute("info", info);
			
			return new ModelAndView("mgnt/system/userRole/sysUserRoleHandle");
		}
		
		if ("U".equals(cmd)) {
			userRoleService.updateRole(vo);
		} else if ("I".equals(cmd)) {
			userRoleService.insertRole(vo);
		} else {
			throw new Exception();
		}
		return PageMove.alertAndRedirectPost(model, "/mgnt/system/UserRoleListAction.do", msg, param);
	}
	
	/**
	 * 시스템 역할 정보 삭제
	 * @param model
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/mgnt/system/UserRoleDeleteAction.do")
	public ModelAndView roleDelete(ModelMap model, 
			@RequestParam("optrAuthNo") String optrAuthNo,
			UserRoleVO vo, @RequestParam Map<Object, Object> param) throws Exception {
		
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("pOptrAuthNo", optrAuthNo);
		
		userRoleService.deleteRole(par);
		
		String oResult = (String) par.get("oResult");
		String msg = null;
		
		if ("Y".equals(oResult)) {
			msg = egovMessageSource.getMessage("success.common.delete");
		} else {
			msg = egovMessageSource.getMessage("fail.common.delete");
		}
		
		return PageMove.alertAndRedirectPost(model, "/mgnt/system/UserRoleListAction.do", msg, param);
	}
	
	
	/**
	 * 시스템 역할 정보 복사
	 * @param model
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/mgnt/system/UserRoleCopyAction.do")
	public ModelAndView roleCopy(ModelMap model, 
			@RequestParam("optrAuthNo") String optrAuthNo,
			UserRoleVO vo, @RequestParam Map<Object, Object> param) throws Exception {
		
		/** 관리자 로그인 체크 end */
		String userNo = "";
		/** 관리자 로그인 체크 start */
		if(SessionUtil.isLogin()) {
			userNo = SessionUtil.getUserNo();
		}
		/** 관리자 로그인 체크 end */
		
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("pOptrAuthNo", optrAuthNo);
		par.put("pLoginUserNo", userNo);
		
		userRoleService.copyRole(par);
		
		String oResult = (String) par.get("oResult");
		String msg = null;
		
		if ("Y".equals(oResult)) {
			msg = egovMessageSource.getMessage("success.common.insert");
		} else {
			msg = egovMessageSource.getMessage("fail.common.insert");
		}
		
		return PageMove.alertAndRedirectPost(model, "/mgnt/system/UserRoleListAction.do", msg, param);
	}
	
	
}

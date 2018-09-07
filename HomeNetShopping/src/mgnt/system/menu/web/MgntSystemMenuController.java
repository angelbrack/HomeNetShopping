package mgnt.system.menu.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import prjframework.common.util.PageMove;
import prjframework.common.util.SessionUtil;
import site.code.service.CodeService;
import site.menu.service.MenuService;
import site.menu.service.MenuVO;
import egovframework.com.cmm.EgovMessageSource;


@Controller
public class MgntSystemMenuController {
	/** MenuService */
    @Resource(name = "menuService")
    protected MenuService menuService;
 
	/** MenuService */
    @Resource(name = "codeService")
    protected CodeService commonCodeService;
    
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	/**
	 * 시스템 메뉴 관리
	 * @return
	 */ 
	@RequestMapping("/mgnt/system/MenuHandleAction.do")
	public String menuEdit(ModelMap model, @ModelAttribute("menuVO") MenuVO menuVO, HttpServletRequest request) throws Exception {
		
		/** 관리자 로그인 체크 start */
		if(!SessionUtil.isLogin()) {
			return "mgnt/home/login/login";
		}
		/** 관리자 로그인 체크 end */
		
		List<MenuVO> list = menuService.list(menuVO);
		model.put("list", list);
		
		return "mgnt/system/menu/sysMenuHandle";
	}
	
	/**
	 * 시스템 메뉴 수정
	 * @return
	 */
	@ValidSaveToken	//sub 토큰 사용시 사용
	@RequestMapping("/mgnt/system/MenuUpdateAction.do")
	public ModelAndView updateAuthMenu(ModelMap model, @ModelAttribute("menuVO") @Valid MenuVO menuVO, BindingResult bindingResult,  
			HttpServletRequest request, @RequestParam Map<Object, Object> param) throws Exception {

		String msg = egovMessageSource.getMessage("fail.common.update");
		String userNo = "";
		/** 관리자 로그인 체크 start */
		if(SessionUtil.isLogin()) {
			userNo = SessionUtil.getUserNo();
		}
		/** 관리자 로그인 체크 end */
		
		menuVO.setLoginUserNo(userNo);
		menuVO.setRemoteAddr(request.getRemoteAddr());
		
		//VO Valid check
		if(bindingResult.hasErrors()) {
			//sub check message 메뉴관리는 리턴 후 스크립트를 제어할 수 없어  valid check 후 실패 메세지로 대체하도록 한다.
			return PageMove.alertAndRedirectPost(model, "/mgnt/system/MenuHandleAction.do", msg, param);
		}
		
		int resultCount = menuService.update(menuVO);
		
		if (resultCount > 0) {
			msg = egovMessageSource.getMessage("success.common.update");
		}
		
		return PageMove.alertAndRedirectNoPost(model, "/mgnt/system/MenuHandleAction.do", msg, param);
	}
	
	/**
	 * 메뉴삭제
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/mgnt/system/MenuDeleteAction.do")
	public ResponseEntity<String> deleteMenu(ModelMap model, HttpServletRequest request, @ModelAttribute("menuVO") MenuVO vo) throws Exception {
		
		String userNo = "";
		/** 관리자 로그인 체크 start */
		if(SessionUtil.isLogin()) {
			userNo = SessionUtil.getUserNo();
		}
		/** 관리자 로그인 체크 end */

		vo.setLoginUserNo(userNo);
		
		String completeYn = "N";
		String nodeNo;
		nodeNo = request.getParameter("nodeNo");
		if (nodeNo != null && nodeNo != "") {
			vo.setMenuNo(nodeNo);
		}
	
		int resultCount = menuService.deleteMenu(vo);
		
		if (resultCount > 0) {
			completeYn = "Y";
		}
		
		JSONObject obj = new JSONObject();
		obj.put("completeYn", completeYn);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(obj.toJSONString(), responseHeaders, HttpStatus.CREATED);
	}
	
	/**
	 * 메뉴생성
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/mgnt/system/MenuCreateAction.do")
	public ResponseEntity<String> createMenu(HttpServletRequest request, ModelMap model, @ModelAttribute("menuVO") MenuVO menuVO) throws Exception {
		
		String userNo = "";
		/** 관리자 로그인 체크 start */
		if(SessionUtil.isLogin()) {
			userNo = SessionUtil.getUserNo();
		}
		/** 관리자 로그인 체크 end */
		
		menuVO.setLoginUserNo(userNo);
		
		Map<String, Object> menuInfo = null;
		String completeYn = "N";
	
		String parentNo;
		String nodeNm;
		parentNo	= request.getParameter("parentNo");
		nodeNm		= request.getParameter("nodeNm");
		menuVO.setHgrkMenuNo(parentNo);	// 상위분류코드
		menuVO.setMenuNm(nodeNm);		// 분류명
		
		menuVO.setUseYn("Y");
		menuVO.setMenuDivCd("0");
		menuVO.setRemoteAddr(request.getRemoteAddr());
		
		String resultString = "";
				
		if(!parentNo.equals("0")) {
			resultString = menuService.createMenu(menuVO);
		} else {
			resultString = menuService.createMenu2(menuVO);
		}
		
		if (StringUtils.isNotEmpty(resultString)) {
			Map<String, Object> param = new HashMap<String, Object>();
			
			param.put("menuNo", resultString);
			menuInfo = menuService.getMenuInfo(param);
			completeYn = "Y";
		}
		
		JSONObject obj = new JSONObject();
		obj.put("completeYn", completeYn);
		obj.put("menuInfo", menuInfo);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(obj.toJSONString(), responseHeaders, HttpStatus.CREATED);
	}
	
}

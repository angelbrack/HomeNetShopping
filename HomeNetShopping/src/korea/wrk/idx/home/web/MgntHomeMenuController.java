package korea.wrk.idx.home.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import korea.wrk.login.service.LoginService;
import korea.wrk.login.service.RoleVO;
import korea.wrk.login.service.UserLoginVO;
import prjframework.common.dataaccess.util.DataMap;
import prjframework.common.util.PageMove;
import prjframework.common.util.SessionUtil;
import site.home.menu.service.HomeMenuVO;
import site.home.menu.service.SiteHomeMenuService;

@Controller
public class MgntHomeMenuController {
	
	@Resource(name = "siteHomeMenuService")
    private SiteHomeMenuService siteHomeMenuService;
	
	@Resource(name = "configPropService")
	protected EgovPropertyService configPropService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "loginService")
	protected LoginService loginService;
	
	@RequestMapping(value="/mgnt/home/menuMainAction.do")
	public ModelAndView getMain(ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		session = request.getSession(true);
		List<RoleVO> roleList = null;
		if(SessionUtil.isLogin()) {
			roleList = loginService.selectUserRole(SessionUtil.getUserNo());
			
			if(SessionUtil.getOptrAuthNo() == null && roleList != null && roleList.size() > 0) {
				session.setAttribute("optrAuthNo", roleList.get(0).getOptrAuthNo());
				session.setAttribute("optrAuthNm", roleList.get(0).getOptrAuthNm());
				session.setAttribute("roleList", roleList);
			}
		 } else {
			try {
				session.invalidate();
			} catch(Exception e) {
			}
			 return PageMove.alertAndRedirect(model, "/mgnt/home/loginAction.do",  null, null);
		 }
		
		return new ModelAndView("mgnt/home/frame/mgntHomeFrameMain");
	}

	/**
	 * 관리자 TOP
	 * 상위 대메뉴명을 권한에 따라 출력
	 */
	@RequestMapping(value="/mgnt/home/menuTopAction.do")
	public String getTop(ModelMap map, HttpServletRequest request, HttpSession session) throws Exception {
		session = request.getSession(true);
		if(!SessionUtil.isLogin()) {
			return "/mgnt/home/loginAction.do";
		}
		
		// 설정된 권한 정보가 존재하지 않으면 세션 정보를 삭제후 로그인 화면으로 보냄
		if (StringUtils.isEmpty(SessionUtil.getOptrAuthNo())) {
			try {
				session.invalidate();
			} catch(Exception e) {
			}
			return "/mgnt/home/loginAction.do";
		}
		
		
		// 메뉴 목록
		DataMap param = new DataMap();
		param.put("optrAuthNo", SessionUtil.getOptrAuthNo());
		param.put("menuDivCd", "001");
		
		List<DataMap> list = (List<DataMap>) siteHomeMenuService.selectTopList(param);
		
		// 현재 선택한 메뉴
		String currentMenuNo = StringUtils.trim((String) request.getParameter("currentMenuNo"));

		if(StringUtils.isEmpty(currentMenuNo)) {
			if(list != null && list.size()>0) {
				currentMenuNo = String.valueOf(list.get(0).get("menuNo"));
			}
		}
		
		map.addAttribute("list", list);
		map.addAttribute("currentMenuNo", currentMenuNo);
		
		return "mgnt/home/frame/mgntHomeFrameTop";
	}

	/**
	 * 관리자 LEFT
	 * 왼쪽 서브메뉴를 권한에 따라 출력
	 * @throws Exception 
	 */
	@RequestMapping(value="/mgnt/home/menuLeftAction.do")
	public String getLeft(ModelMap map, HttpServletRequest request, HttpSession session) throws Exception {
		
		session = request.getSession(true);
		if(!SessionUtil.isLogin()) {
			return "/mgnt/home/loginAction.do";
		}
		
		// 설정된 권한 정보가 존재하지 않으면 세션 정보를 삭제후 로그인 화면으로 보냄
		if (StringUtils.isEmpty(SessionUtil.getOptrAuthNo())) {
			try {
				session.invalidate();
			} catch(Exception e) {
			}
			return "/mgnt/home/loginAction.do";
		}
		
		
		String hgrkMenuNo = (String) request.getParameter("currentMenuNo");
		
		
		// 메뉴 목록
		DataMap param = new DataMap();
		param.put("optrAuthNo", SessionUtil.getOptrAuthNo());
		param.put("menuDivCd", "001");
		param.put("hgrkMenuNo", hgrkMenuNo);
		
		List<HomeMenuVO> list = null;
		String topMenuNm = null;
		if(hgrkMenuNo != null && !hgrkMenuNo.equals("")) {
			list = (List<HomeMenuVO>) siteHomeMenuService.selectLeftList(param);
			
			if (list != null && list.size() > 0) {
				HomeMenuVO menuInfo = (HomeMenuVO) list.get(0);
				topMenuNm = menuInfo.getMenuNm();
			}
		}
		map.addAttribute("list", list);
		map.addAttribute("topMenuNm", topMenuNm);
		
		return "mgnt/home/frame/mgntHomeFrameLeft";
	}
	
	@RequestMapping(value="/mgnt/home/menuLeftAction.ajax")
	public String manfrmLeftAjaxViewAction(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) throws Exception {

		session = request.getSession(true);
		String optrAuthNo = "" ;
		if(SessionUtil.isLogin()) {
			if (!StringUtils.isEmpty(SessionUtil.getOptrAuthNo())) {
				optrAuthNo = SessionUtil.getOptrAuthNo() ;
			}
		}
		System.out.println("optrAuthNo "+optrAuthNo);;
		String hgrkMenuNo = (String) request.getParameter("currentMenuNo");
		
		DataMap param = new DataMap();
		param.put("optrAuthNo", SessionUtil.getOptrAuthNo());
		param.put("menuDivCd", "001");
		param.put("hgrkMenuNo", hgrkMenuNo);
		
		
		
		List<HomeMenuVO> list = null;
		String topMenuNm = null;
		if(hgrkMenuNo != null && !hgrkMenuNo.equals("")) {
			list = (List<HomeMenuVO>) siteHomeMenuService.selectLeftList(param);
		}
		model.addAttribute("row", list);
		
		return "jsonView";
	} 
	
	
	

	/**
	 * 관리자 BODY
	 * 바디메뉴를 권한에 따라 출력
	 */
	@RequestMapping(value="/mgnt/home/menuBodyAction.do")
	public String getBody(ModelMap model, HttpSession session, HttpServletRequest request) {
		session = request.getSession(true);
		if(!SessionUtil.isLogin()) {
			return "/mgnt/home/loginAction.do";
		}
		
		// 설정된 권한 정보가 존재하지 않으면 세션 정보를 삭제후 로그인 화면으로 보냄
		if (StringUtils.isEmpty(SessionUtil.getOptrAuthNo())) {
			try {
				session.invalidate();
			} catch(Exception e) {
			}
			return "/mgnt/home/loginAction.do";
		}
		

		return "mgnt/home/frame/mgntHomeFrameBody";
	}

	/**
	 * 로그아웃
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/mgnt/home/logoutAction.do")
	public ModelAndView logout(ModelMap model, HttpSession session) throws Exception{

		try {
			session.invalidate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return PageMove.alertAndRedirectPost(model, "/mgnt/home/loginAction.do", null, null);
	}
	
	/**
	 * 사용자 권한 변경
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/mgnt/home/menuChangeRoleAction.do")
	public String chageRole(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam("roleId") String roleId
			) {
		UserLoginVO userLoginVO = (UserLoginVO)session.getAttribute("UserLoginVO");
		RoleVO roleInfo = null;
		
		List<RoleVO> roleList = (List<RoleVO>)session.getAttribute("roleList");
		
		if (roleList != null && roleList.size()>0) {
			for (int i=0; i < roleList.size(); i++) {
				roleInfo = roleList.get(i);
				
				if (roleInfo != null) {
					if(roleId.equals(String.valueOf(roleInfo.getOptrAuthNo()))) {
						userLoginVO.setOptrAuthCd(String.valueOf(roleInfo.getOptrAuthCd()));

						session.setAttribute("UserLoginVO", userLoginVO);
						session.setAttribute("optrAuthNo", String.valueOf(roleInfo.getOptrAuthNo()));
						session.setAttribute("optrAuthNm", String.valueOf(roleInfo.getOptrAuthNm()));
					}
				}
			}
		}
		
		return "redirect:/mgnt/home/menuMainAction.do";
	}
	
	
	
}

package homenet.shop.login.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import homenet.shop.login.service.LoginService;
import homenet.shop.login.service.UserLoginVO;
import prjframework.common.util.DecodeEncryptor168;
import prjframework.common.util.PageMove;
import prjframework.common.util.StringUtil;
import prjframework.common.util.WebUtil;

@Controller
public class UserLoginController {

	
	
	@Resource(name = "loginService")
	protected LoginService loginService;
	
	
	@Resource(name = "configPropService")
	protected EgovPropertyService configPropService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@RequestMapping(value="/user/login/login/initLogin.do")
	public String portalLogin(HttpServletRequest request, ModelMap model) throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("UserLoginVO") != null) {
			session.invalidate();
		}		
        return "user/login/login/initLogin";
    }
	
	@RequestMapping(value="/user/login/login/logout.do")
	public ModelAndView portalLogout(ModelMap model, HttpSession session) throws Exception{

		try {
			session.invalidate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return PageMove.alertAndRedirectPost(model, "/user/index/home/homeMain.do", null, null);
	}
	
	@RequestMapping(value="/user/login/loginAction.do")
	public ModelAndView userLoginAction(@ModelAttribute("searchVO") UserLoginVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("UserLoginVO") != null) {
			session.invalidate();
			
		}
		session = request.getSession();
		int loginFailCnt = configPropService.getInt("BACK.LOGIN.FAIL.CNT");
		
		String cnntInfo = request.getHeader("User-Agent");
		String osCd = WebUtil.getOsCd(cnntInfo);
		String remoteAddr = WebUtil.getRemoteAddr(request);
		vo.setRemoteAddr(remoteAddr);
		
        int resultNo = 0; // 로그인 결과
        String resultMsg = null; // 로그인 메세지결과
        String resultPage = "/user/login/login/initLogin.do"; // 결과 페이지
        int nLoginChk = -99 ;
        
		//loginDiv
		String loginDiv = StringUtil.NullCheck(vo.getLoginDiv());
		UserLoginVO userLoginVO = new UserLoginVO();
		
		
		if("K".equals(loginDiv)) {
			vo.setLoginId(vo.getLoginIdK());
			vo.setLoginPwd(vo.getLoginPwdK());
			
			EgovMap scafUser = loginService.selectScafLoginKInfo(vo) ;
			
			if(scafUser != null) {
				/* 로그인성공 */
				nLoginChk = 1 ;
	        	int nKJobUser = loginService.selectJobKUserCheck(vo) ;
	        	vo.setUserNm((String) scafUser.get("wrk002KorNm"));
        		vo.setRpsStno((String) scafUser.get("wrk002StdId"));
        		
        		
	        	if(nKJobUser < 1) {
	        		//회원인서트
	        		String strUserNoNext = loginService.selectUserNoNext();
	        		vo.setUserNo(strUserNoNext);
	        		
	        		loginService.insertNwrk100tl(vo);
	        		loginService.insertNwrk101tl(vo);
	        		
	        		userLoginVO.setUserNo(strUserNoNext);
	        		
	        	}else{
	        		loginService.updateNwrk100tl(vo);
	        		userLoginVO.setUserNo(String.valueOf(scafUser.get("userNo"))); 
	        	}
	        	userLoginVO.setUserNm((String) scafUser.get("wrk002KorNm"));
        		userLoginVO.setRpsStno((String) scafUser.get("wrk002StdId"));
	        	userLoginVO.setMbrDc("K");
	        	userLoginVO.setCnntInfo(cnntInfo);
	        	userLoginVO.setOsCd(osCd);
	        	userLoginVO.setRemoteAddr(remoteAddr);
	        	
	        	//마이페이지로 가야됨, 현재 메인으로 가게
	        	resultPage = "/user/index/home/homeMain.do" ;
	        	resultMsg = null;
	        	
	        	
	        	
	        	
	        	
	        }else{
	        	//로그인 실패
	        	nLoginChk = -99 ;
	        }
			
			
		}else {
			if("C".equals(loginDiv)) {
				vo.setLoginId(vo.getLoginIdC());
				vo.setLoginPwd(vo.getLoginPwdC());
			}else if("G".equals(loginDiv)) {
				vo.setLoginId(vo.getLoginIdG());
				vo.setLoginPwd(vo.getLoginPwdG());
			}
			userLoginVO = loginService.selectLoginInfo(vo);
			if(userLoginVO != null) {
				//기업이 삭제된경우
				if(vo.getNwrk200tlDelYn() != null && "Y".equals(vo.getNwrk200tlDelYn())) {
					nLoginChk = -80 ;
				}else{
					int nPwdFailCnt = Integer.parseInt(userLoginVO.getPwdFailTms());
					//패스워드 fail 체크
					if(nPwdFailCnt >= loginFailCnt) {
						nLoginChk = -5 ;
					}else{
						nLoginChk = 1 ;
						
					}
					
					System.out.println("nPwdFailCnt "+nPwdFailCnt);
					//임시비밀번호만료체크, 이부분은 기획서에 없었음
					if(vo.getTmpPwdLimitYn() != null && "Y".equals(vo.getTmpPwdLimitYn())) {
						nLoginChk = -10 ;
					}
					
					//세션 set
					if(nLoginChk == 1 ) {
			        	userLoginVO.setCnntInfo(cnntInfo);
			        	userLoginVO.setOsCd(osCd);
			        	userLoginVO.setRemoteAddr(remoteAddr);
					}
					
					//패스워드 변경화면 체크
					if(vo.getPwdChngLimitYn() != null && "Y".equals(vo.getPwdChngLimitYn())) {
						nLoginChk = -3 ;
					}
					
				}
				
				
				
				
				
				
			}else{
				//로그인 실패
				nLoginChk = -99 ;
	        	
	        	
			}
			
			
		}
		
		
		//로그인성공시
		if(nLoginChk == 1) {
			Device device = DeviceUtils.getCurrentDevice(request);
			if(device == null) {
				userLoginVO.setMblYn("N");
			}else{
				if(device.isNormal()) {
					userLoginVO.setMblYn("N");
				} else if(device.isMobile()) {
					userLoginVO.setMblYn("Y");
				} else if(device.isTablet()) {
					userLoginVO.setMblYn("Y");
				}
			}
			session.setAttribute("UserLoginVO", userLoginVO);
			resultPage = "/user/index/home/homeMain.do" ;
        	resultMsg = null;
        	loginService.updateLoginInfo(userLoginVO);
        	loginService.updatePwdTms(userLoginVO.getUserNo()) ;
		}else if(nLoginChk == -99) {
			resultPage = "/user/login/login/initLogin.do"; // 결과 페이지
        	resultMsg = "아이디 또는 비밀번호를 다시 확인하세요." ;
        	loginService.updatePwdFail(vo) ;
		}else if(nLoginChk == -5) { //횟수만료
			resultMsg = "패스워드가 5회이상 틀려서 계정의 로그인이 잠겼습니다." ;
			resultPage = "/user/login/login/initLogin.do"; // 결과 페이지
		}else if(nLoginChk == -3) { //패스워드만기
			resultPage = "/user/index/home/homeMain.do" ;
		}else if(nLoginChk == -10) { //임시패스워드
			resultPage = "/user/index/home/homeMain.do" ;
		}else if(nLoginChk == -80) { //임시패스워드
			resultPage = "/user/login/login/initLogin.do"; // 결과 페이지
        	resultMsg = "해당 기업이 삭제되어 로그인 하실수 없습니다." ;
		}
        
		return PageMove.alertAndRedirect(model, resultPage, resultMsg, null);
		
    }
	
	@RequestMapping(value="/user/login/ssoLoginAction.do")
	public ModelAndView userSsoLoginAction(@ModelAttribute("searchVO") UserLoginVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("UserLoginVO") != null) {
			session.invalidate();
			
		}
		session = request.getSession();
		
		String cnntInfo = request.getHeader("User-Agent");
		String osCd = WebUtil.getOsCd(cnntInfo);
		String remoteAddr = WebUtil.getRemoteAddr(request);
		vo.setRemoteAddr(remoteAddr);
		
        String resultMsg = null; // 로그인 메세지결과
        String resultPage = "/user/login/login/initLogin.do"; // 결과 페이지
        int nLoginChk = -99 ;
        
		//loginDiv
		String loginDiv = "K" ;
		UserLoginVO userLoginVO = new UserLoginVO();
		String strMsg = vo.getSsoMsg() ;
		String strUserId = "" ;
		System.out.println("strMsg111 "+strMsg);
		strMsg = DecodeEncryptor168.getDecryptedValue(strMsg) ;
		
		System.out.println("strMsg2222 "+strMsg);
		
		
		String strList[] = strMsg.split("&") ;
		
		if(strList != null) {
			for(int i=0; i<strList.length; i++) {
				String strParamList[] = strList[i].split("=") ;
				if(strParamList != null) {
					System.out.println("1111  "+strParamList[0]);
					System.out.println("2222  "+strParamList[1]);
					if("sUserid".equals(strParamList[0])) {
						strUserId = strParamList[1] ;
						break;
					}
				}
			}
			/*
			PotalLoginAct act = new PotalLoginAct();
			act.exec(request, strUserId);
			strReturnMsg = (String)request.getAttribute("MSG") ;
			strReturnUrl = (String)request.getAttribute("RETURN_URL") ;
			*/
		}else{
			nLoginChk = -99 ;
			
		}
		
		
		if("K".equals(loginDiv)) {
			vo.setLoginId(strUserId);
			
			EgovMap scafUser = loginService.selectSsoLoginInfo(vo) ;
			
			if(scafUser != null) {
				/* 로그인성공 */
				nLoginChk = 1 ;
	        	int nKJobUser = loginService.selectJobKUserCheck(vo) ;
	        	vo.setUserNm((String) scafUser.get("wrk002KorNm"));
        		vo.setRpsStno((String) scafUser.get("wrk002StdId"));
        		
        		
	        	if(nKJobUser < 1) {
	        		//회원인서트
	        		String strUserNoNext = loginService.selectUserNoNext();
	        		vo.setUserNo(strUserNoNext);
	        		
	        		loginService.insertNwrk100tl(vo);
	        		loginService.insertNwrk101tl(vo);
	        		
	        		userLoginVO.setUserNo(strUserNoNext);
	        		
	        	}else{
	        		loginService.updateNwrk100tl(vo);
	        		userLoginVO.setUserNo(String.valueOf(scafUser.get("userNo"))); 
	        	}
	        	userLoginVO.setUserNm((String) scafUser.get("wrk002KorNm"));
        		userLoginVO.setRpsStno((String) scafUser.get("wrk002StdId"));
	        	userLoginVO.setMbrDc("K");
	        	userLoginVO.setCnntInfo(cnntInfo);
	        	userLoginVO.setOsCd(osCd);
	        	userLoginVO.setRemoteAddr(remoteAddr);
	        	
	        	//마이페이지로 가야됨, 현재 메인으로 가게
	        	resultPage = "/user/index/home/homeMain.do" ;
	        	resultMsg = null;
	        	
	        	
	        	
	        	
	        	
	        }else{
	        	//로그인 실패
	        	nLoginChk = -99 ;
	        }
			
			
		}
		
		//로그인성공시
		if(nLoginChk == 1) {
			Device device = DeviceUtils.getCurrentDevice(request);
			if(device == null) {
				userLoginVO.setMblYn("N");
			}else{
				if(device.isNormal()) {
					userLoginVO.setMblYn("N");
				} else if(device.isMobile()) {
					userLoginVO.setMblYn("Y");
				} else if(device.isTablet()) {
					userLoginVO.setMblYn("Y");
				}
			}
			session.setAttribute("UserLoginVO", userLoginVO);
			resultPage = "/user/index/home/homeMain.do" ;
        	resultMsg = null;
        	loginService.updateLoginInfo(userLoginVO);
        	loginService.updatePwdTms(userLoginVO.getUserNo()) ;
		}else if(nLoginChk == -99) {
			resultPage = "/user/login/login/initLogin.do"; // 결과 페이지
        	resultMsg = "아이디 또는 비밀번호를 다시 확인하세요." ;
        	loginService.updatePwdFail(vo) ;
		}else if(nLoginChk == -5) { //횟수만료
			resultMsg = "패스워드가 5회이상 틀려서 계정의 로그인이 잠겼습니다." ;
			resultPage = "/user/login/login/initLogin.do"; // 결과 페이지
		}else if(nLoginChk == -3) { //패스워드만기
			resultPage = "/user/index/home/homeMain.do" ;
		}else if(nLoginChk == -10) { //임시패스워드
			resultPage = "/user/index/home/homeMain.do" ;
		}else if(nLoginChk == -80) { //임시패스워드
			resultPage = "/user/login/login/initLogin.do"; // 결과 페이지
        	resultMsg = "해당 기업이 삭제되어 로그인 하실수 없습니다." ;
		}
        
		return PageMove.alertAndRedirect(model, resultPage, resultMsg, null);
		
    }
	
	
}	

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
import prjframework.common.util.PageMove;
import prjframework.common.util.StringUtil;
import prjframework.common.util.WebUtil;

@Controller
public class MgntLoginController {

	
	
	@Resource(name = "loginService")
	protected LoginService loginService;
	
	
	@Resource(name = "configPropService")
	protected EgovPropertyService configPropService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@RequestMapping("/mgnt/home/loginAction.do")
	public String mgntLogin(HttpServletRequest request, ModelMap model) throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("UserLoginVO") != null) {
			session.invalidate();
			
		}		
		return "mgnt/home/login/login";
	}
	

	/**
	 * 관리자 로그인
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mgnt/login/loginAction.do")
	public ModelAndView loginAction(@ModelAttribute("searchVO") UserLoginVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("UserLoginVO") != null) {
			session.invalidate();
		}
		session = request.getSession();
		
		int loginFailCnt = configPropService.getInt("BACK.LOGIN.FAIL.CNT");
		
        int resultNo = 0; // 로그인 결과
        String resultMsg = null; // 로그인 메세지결과
        String resultPage = ""; // 결과 페이지
        boolean bLoginChk = false ;
        
        UserLoginVO userLoginVO = null;
        
        //admin 권한체크
        //관리자권한 체크 
        //패스워드 체크
        
        String strMbrDc = StringUtil.NullCheck(vo.getMbrDc());
        userLoginVO = loginService.selectAdmLoginInfo(vo) ;
        
        //resultNo -1 : 로그인정보 없음
        //resultNo -2 : 패스워드 틀림
        //resultNo -3 : 권한만료, -4: 패스워드 5회틀림
        if(userLoginVO != null) {
        	
        	if ( !vo.getLoginPwd().equals(userLoginVO.getLoginPwd()) ) {
        		// 패스워드 체크
        		resultNo = -2;  // 비밀번호가 틀림
            	resultMsg = "비밀번호가  올바르지 않습니다." ;
            	resultPage = "/mgnt/home/loginAction.do" ;
        	} else {
        	
	        	if("Y".equals(userLoginVO.getOptrExpireYn())) {
	        		resultNo = -3 ;
	        		resultMsg = egovMessageSource.getMessage("login.notApprove");
	        	} else {
	        		//일반회원일때는 패스워드 기간 및 실패횟수를 체크한다.
	        		if(!"01".equals(strMbrDc)) {
	        			if(Integer.parseInt(userLoginVO.getPwdFailTms()) >= loginFailCnt) {
	        				bLoginChk = true ;
	        				resultNo = -4 ;
	        				resultMsg = "패스워드가 5회이상 틀려서 로그인이 제한됩니다." ;
	        				resultPage = "/mgnt/home/loginAction.do" ;
	        			}
	        			
	        		}
	        		
	        		if(!bLoginChk) {
	        			String cnntInfo = request.getHeader("User-Agent");
	        			userLoginVO.setCnntInfo(cnntInfo);
						// OS 구분 (01 : window, 02 : mac, 03 : linux, 04 :기타)
						String osCd = WebUtil.getOsCd(cnntInfo);
						userLoginVO.setOsCd(osCd);
						String remoteAddr = WebUtil.getRemoteAddr(request);
						userLoginVO.setRemoteAddr(remoteAddr);
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
						loginService.updateAdminLoginInfo(userLoginVO);
						//userLoginVO.setLoginPwd(""); //비밀번호 정보 삭제
						session.setAttribute("UserLoginVO", userLoginVO);
						resultMsg = userLoginVO.getUserNm() + egovMessageSource.getMessage("login.user.success")+"." ;
						//패스워드 변경화면 이동
						if("Y".equals(userLoginVO.getPwdChgYn())) {
	        				
	        			}
	        		}
	        	}
        	}
        	
        } else{
        	resultNo = -1;  //사용자ID
        	resultMsg = "로그인 정보가 올바르지 않습니다." ;
        	resultPage = "/mgnt/home/loginAction.do" ;
        	//패스워드 실패횟수 체크해야함
        	
        }
        
        if(!bLoginChk) {
        	resultPage = "/mgnt/home/menuMainAction.do";
        }
        
        return PageMove.alertAndRedirect(model, resultPage, null, null);
    }
	
	@RequestMapping(value="/result")
	public String result(ModelMap model) throws Exception {
    	
    	//System.out.println(">>>> /result");
    	
        return "NON.result";
    }
}	

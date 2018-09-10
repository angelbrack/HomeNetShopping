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
		
		/*
    	String loginFailCntUseYn = configPropService.getString("BACK.LOGIN.FAIL.CNT.USEYN");
    	String datetimeForm = configPropService.getString("FORMAT.DATETIME.DB");
    	String datebtimeForm = configPropService.getString("FORMAT.DATEBTIME.JAVA");
    	
    	
        String loginId = StringUtil.NullCheck(request.getParameter("loginId"));
        String loginDiv = StringUtil.NullCheck(request.getParameter("loginDiv"));
        String loginPwd = StringUtil.NullCheck(request.getParameter("loginPwd"));
        */
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
        if("K".equals(strMbrDc)) {
        	vo.setOptrAuthCd("01");
        	userLoginVO = loginService.selectAdmLoginKInfo(vo) ;
        }else if("G".equals(strMbrDc)){
        	userLoginVO = loginService.selectAdmLoginGInfo(vo) ;
        }
        
        //resultNo -1 : 로그인정보 없음, 패스워드 틀리거나 권한없음
        //resultNo -3 : 권한만료, -4: 패스워드 5회틀림
        if(userLoginVO != null) {
        	
        	if("Y".equals(userLoginVO.getOptrExpireYn())) {
        		resultNo = -3 ;
        		resultMsg = egovMessageSource.getMessage("login.notApprove");
        	}else{
        		//일반회원일때는 패스워드 기간 및 실패횟수를 체크한다.
        		if("G".equals(strMbrDc)) {
        			//userLoginVO.getPwdFailTms() 쿼리에서 nvl처리 되어 있음
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
        	
        	
        }else{
        	resultNo = -1;  //사용자ID
        	resultMsg = "로그인 정보가 올바르지 않습니다." ;
        	resultPage = "/mgnt/home/loginAction.do" ;
        	//패스워드 실패횟수 체크해야함
        	
        }
        if(!bLoginChk) {
        	resultPage = "/mgnt/home/menuMainAction.do";
        }
        
        //userLoginVO = loginService.selectLoginInfo(loginId);
        
        
        
        
        /*
        
        
        

        
        
        
        
        
        
        
        
        if (userLoginVO != null){
        	//운영자로그인 경우 승인여부 체크
        	
        	if("1".equals(loginDiv) && !"Y".equals(userLoginVO.getExprYn())) {
        		resultNo = -3;//승인안됨. 기간만료
        		resultMsg = egovMessageSource.getMessage("login.notApprove");
        	} else {
        		if("N".equals(loginFailCntUseYn) || loginFailCnt >= Integer.parseInt(userLoginVO.getPwdFailTms())) {		//로그인 실패 사용여부와 실패 카운트 확인
    				if((userLoginVO.getLoginPwd() != null && (userLoginVO.getLoginPwd()).equals(SHA256Util.encrypt(loginPwd)))) { // 패스워드가 맞는지 확인
    					String agentInfo = request.getHeader("User-Agent");
    					// OS 구분
    					String osCd = null;
    					if(agentInfo.indexOf("NT 6.0") != -1) osCd = "01"; //"Windows Vista/Server 2008";
    					else if(agentInfo.indexOf("NT 5.2") != -1) osCd = "02"; //"Windows Server 2003";
    					else if(agentInfo.indexOf("NT 5.1") != -1) osCd = "03"; //"Windows XP";
    					else if(agentInfo.indexOf("NT 5.0") != -1) osCd = "04"; //"Windows 2000";
    					else if(agentInfo.indexOf("NT") != -1) osCd = "05"; //"Windows NT";
    					else if(agentInfo.indexOf("9x 4.90") != -1) osCd = "06"; //"Windows Me";
    					else if(agentInfo.indexOf("98") != -1) osCd = "07"; //"Windows 98";
    					else if(agentInfo.indexOf("95") != -1) osCd = "08"; //"Windows 95";
    					else if(agentInfo.indexOf("Win16") != -1) osCd = "09"; //"Windows 3.x";
    					else if(agentInfo.indexOf("Windows") != -1) osCd = "10"; //"Windows";
    					else if(agentInfo.indexOf("Linux") != -1) osCd = "11"; //"Linux";
    					else if(agentInfo.indexOf("Macintosh") != -1) osCd = "12"; //"Macintosh";

    					String currentDateTime = WebUtil.toStr(datetimeForm, new Date());
    					String remoteAddr = request.getRemoteAddr();
    					
    					userLoginVO.setLoginTime(currentDateTime);
    					userLoginVO.setRemoteAddr(remoteAddr);
    					userLoginVO.setMblYn("N");
    					userLoginVO.setLoginDiv(loginDiv);
    					userLoginVO.setCnntInfo(agentInfo);       
    					userLoginVO.setOsCd(osCd);           
    					loginService.updateLoginInfo(userLoginVO);
    					
    					userLoginVO.setLoginPwd(""); //비밀번호 정보 삭제
    					
    					session.setAttribute("UserLoginVO", userLoginVO);
    					
                        resultNo = 1;// 로그인성공
                        if("1".equals(loginDiv) || "2".equals(loginDiv)) {
                        	resultMsg = userLoginVO.getUserNm() + egovMessageSource.getMessage("login.user.success") + " " + egovMessageSource.getMessage("login.success.time") + " : " + WebUtil.toStr(datebtimeForm, new Date());
                        }
    				} else {
    					resultNo = -2;// 패스워드가 틀림
    					resultMsg = egovMessageSource.getMessage("fail.common.login");
                        loginService.updateLoginFailCnt(userLoginVO);
    				}
        		} else {
        			resultNo = -99;
        			resultMsg = egovMessageSource.getMessage("fail.common.login");
        		}
        	}

        }else{
        	resultNo = -1;// 사용자ID없음
        	resultMsg = egovMessageSource.getMessage("fail.common.login");
        }
        
        //model.addAttribute("resultNo", resultNo);
        
        if("1".equals(loginDiv)) {
        	if(resultNo == 1 || resultNo == 6) {
        		resultPage = "/mgnt/home/menuMainAction.do";
        	} else {
        		resultPage = "/mgnt/home/loginAction.do";
        	}
        } else if("2".equals(loginDiv)) {        	
        	if(resultNo == 1 || resultNo == 6) {
        		resultPage = "/support/home/menuMainAction.do";
        	} else {
        		resultPage = "/support/home/loginAction.do";
        	}
        } else {
        	if(resultNo == 1 || resultNo == 6) {
        		resultPage = "/portal/home/mainIntroAction.do";
        	} else {
        		resultPage = "/portal/home/loginAction.do";
        	}
        }
        */
        
        return PageMove.alertAndRedirect(model, resultPage, null, null);
    }
	
	@RequestMapping(value="/result")
	public String result(ModelMap model) throws Exception {
    	
    	//System.out.println(">>>> /result");
    	
        return "NON.result";
    }
}	

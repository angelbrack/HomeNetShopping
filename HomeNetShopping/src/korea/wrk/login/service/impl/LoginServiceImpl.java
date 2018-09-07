package korea.wrk.login.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import korea.wrk.login.service.LoginService;
import korea.wrk.login.service.RoleVO;
import korea.wrk.login.service.UserLoginVO;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import prjframework.common.file.service.PrjframeworkCmmnFileService;

/**
 * -----------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------------
 * 소속 : 고려대학교 경력개발센터
 * 수정일 : 2018.03.28
 * 수정자 : 이민섭
 * 목적 :  교내 아르바이트 관리
 * ------------------------------------------------------------------------
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Resource(name = "configPropService")
	protected EgovPropertyService configPropService;
	
	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
	
	
	@Resource(name = "prjframeworkCmmnFileService")
	private PrjframeworkCmmnFileService prjframeworkCmmnFileService;
	
	public UserLoginVO selectAdmLoginKInfo(UserLoginVO vo) throws Exception {
		return (UserLoginVO) mybatisDataAccessDAO.select("korea.wrk.login.service.LoginService.selectLogin_S02", vo);
	}
	
	public UserLoginVO selectAdmLoginGInfo(UserLoginVO vo) throws Exception {
		return (UserLoginVO) mybatisDataAccessDAO.select("korea.wrk.login.service.LoginService.selectLogin_S01", vo);
	}
	
	public List<RoleVO> selectUserRole(String userNo) throws Exception {
		return mybatisDataAccessDAO.list("korea.wrk.login.service.LoginService.selectLogin_S03", userNo);
	}
	
	public void updateAdminLoginInfo(UserLoginVO vo) throws Exception {
		mybatisDataAccessDAO.insert("korea.wrk.login.service.LoginService.insertLogin_I01", vo) ;
	}
	
	public EgovMap selectScafLoginKInfo(UserLoginVO vo) throws Exception {
		return (EgovMap) mybatisDataAccessDAO.select("korea.wrk.login.service.LoginService.selectLogin_S04", vo);
	}
	
	public int selectJobKUserCheck(UserLoginVO vo) throws Exception {
		return (Integer) mybatisDataAccessDAO.select("korea.wrk.login.service.LoginService.selectLogin_S05", vo);
	}
	
	public String selectUserNoNext() throws Exception {
		return (String) mybatisDataAccessDAO.select("korea.wrk.login.service.LoginService.selectLogin_S06", null);
	}
	
	public void insertNwrk100tl(UserLoginVO vo) throws Exception {
		mybatisDataAccessDAO.insert("korea.wrk.login.service.LoginService.insertLogin_I02", vo) ;
	}
	
	public void insertNwrk101tl(UserLoginVO vo) throws Exception {
		mybatisDataAccessDAO.insert("korea.wrk.login.service.LoginService.insertLogin_I03", vo) ;
	}
	
	public void updateNwrk100tl(UserLoginVO vo) throws Exception {
		mybatisDataAccessDAO.update("korea.wrk.login.service.LoginService.updateLogin_U01", vo) ;
	}
	
	public UserLoginVO selectLoginInfo(UserLoginVO vo) throws Exception {
		return (UserLoginVO) mybatisDataAccessDAO.select("korea.wrk.login.service.LoginService.selectLogin_S07", vo);
	}
	
	public void updatePwdFail(UserLoginVO vo) throws Exception {
		mybatisDataAccessDAO.update("korea.wrk.login.service.LoginService.updateLogin_U02", vo) ;
	}
	
	public void updatePwdTms(String userNo) throws Exception {
		mybatisDataAccessDAO.update("korea.wrk.login.service.LoginService.updateLogin_U03", userNo) ;
	}
	
	public void updateLoginInfo(UserLoginVO vo) throws Exception {
		mybatisDataAccessDAO.insert("korea.wrk.login.service.LoginService.insertLogin_I04", vo) ;
	}
	
	public EgovMap selectSsoLoginInfo(UserLoginVO vo) throws Exception {
		return (EgovMap) mybatisDataAccessDAO.select("korea.wrk.login.service.LoginService.selectLogin_S08", vo);
	}

}

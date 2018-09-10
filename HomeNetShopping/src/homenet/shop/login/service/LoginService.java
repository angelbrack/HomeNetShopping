package homenet.shop.login.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface LoginService {

	//사용자 로그인 정보 조회
	//public UserLoginVO selectLoginInfo(String loginId) throws Exception;
	
	//사용자 권한 조회
	public List<RoleVO> selectUserRole(String userNo) throws Exception;
	
	//운영자 접속기록 갱신
	public void updateAdminLoginInfo(UserLoginVO vo) throws Exception;
	
	//로그인 실패 기록
	//public void updateLoginFailCnt(UserLoginVO vo) throws Exception;
	
	
	public UserLoginVO selectAdmLoginGInfo(UserLoginVO vo) throws Exception;
	
	public UserLoginVO selectAdmLoginKInfo(UserLoginVO vo) throws Exception;
	
	public EgovMap selectScafLoginKInfo(UserLoginVO vo) throws Exception ;
	
	public int selectJobKUserCheck(UserLoginVO vo) throws Exception ;
	
	public String selectUserNoNext() throws Exception ;
	
	public void insertNwrk100tl(UserLoginVO vo) throws Exception ;
	
	public void insertNwrk101tl(UserLoginVO vo) throws Exception ;
	
	public void updateNwrk100tl(UserLoginVO vo) throws Exception ;
	
	public UserLoginVO selectLoginInfo(UserLoginVO vo) throws Exception;
	
	public void updatePwdFail(UserLoginVO vo) throws Exception ;
	
	public void updatePwdTms(String userNo) throws Exception ;
	
	public void updateLoginInfo(UserLoginVO vo) throws Exception ;
	
	public EgovMap selectSsoLoginInfo(UserLoginVO vo) throws Exception ;
	
	
}

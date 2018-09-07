package site.auth.service;

import java.util.List;
import java.util.Map;

public interface AuthService {

	/**
	 * 사용자 권한 목록
	 * @param UserVo
	 * @return
	 */
	public List<Map<String, Object>> selectUserList(AuthSearchVO vo) throws Exception;
	
	/**
	 * 사용자 권한 목록 전체 건수
	 * @param
	 * @return
	 */
	public int selectUserListTotCnt(AuthSearchVO vo) throws Exception;
	
	/**
	 * 권한 전체 목록
 	 * @param
	 * @return
	 */
	public List<Map<String, Object>> getAuthRoleList(AuthSearchVO vo) throws Exception;
	
	/**
	 * 사용자 권한 조회
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> selectAuthRole(AuthSearchVO vo) throws Exception;
	
	/**
	 * 사용자 정보 조회
	 * @param
	 * @return
	 */
	public Map<String, Object> selectUserInfo(AuthSearchVO vo) throws Exception;

	/**
	 * 사용자 승인상태 조회
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> selectUserOptrApprStatCd(AuthSearchVO vo) throws Exception;

	/**
	 * 사용자 정보 저장
	 * @param
	 * @return
	 */
	public int updateUserInfo(AuthVO authVo) throws Exception;
}

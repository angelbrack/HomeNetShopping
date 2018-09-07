package site.userRole.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface UserRoleService {
	
	/**
	 * 시스템 역할 목록 조회
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> selectRoleList(UserRoleVO vo);
	
	/**
	 * 시스템 역할정보 등록
	 * @param vo
	 */
	public void insertRole(UserRoleVO vo) throws Exception;
	
	/**
	 * 시스템 역할정보 수정
	 * @param vo
	 */
	public void updateRole(UserRoleVO vo) throws Exception;
	
	/**
	 * 시스템 역할정보 삭제
	 * @param vo
	 */
	public Object deleteRole(Map<String, Object> param);
	
	/**
	 * 시스템 역할정보 복사
	 * @param vo
	 */
	public Object copyRole(Map<String, Object> param);
	
	/**
	 * 시스템 역할정보 조회
	 * @param vo
	 * @return
	 */
	public UserRoleVO selectRoleInfo(UserRoleVO vo);
	
	
	/**
	 * 시스템 역할에 따른 프로그램 목록 조회
	 * @param vo
	 * @return
	 */
	public List<EgovMap> selectRoleProgram(UserRoleVO vo) throws Exception;
	
}

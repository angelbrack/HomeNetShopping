package site.userRole.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import site.userRole.service.UserRoleService;
import site.userRole.service.UserRoleVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("userRoleService")
public class UserRoleServiceImpl extends AbstractServiceImpl implements UserRoleService {

	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
	
    /**
     * 시스템 역할 목록을 조회 한다.
     */
    @SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectRoleList(UserRoleVO vo) {
    	return mybatisDataAccessDAO.list("site.userRole.service.UserRoleService.selectRoleList", vo);
	}

    /**
     * 시스템 역할 정보를 신규 등록 한다.
     */
	public void insertRole(UserRoleVO vo) throws Exception {
		mybatisDataAccessDAO.insert("site.userRole.service.UserRoleService.insertRole", vo);
		insertRoleProgram(vo);
	}

	/**
	 * 시스템 역할 정보를 수정 한다.
	 */
	public void updateRole(UserRoleVO vo) throws Exception {
		mybatisDataAccessDAO.insert("site.userRole.service.UserRoleService.updateRole", vo);
		insertRoleProgram(vo);
	}
	
	private void insertRoleProgram(UserRoleVO vo) throws Exception {
		mybatisDataAccessDAO.delete("site.userRole.service.UserRoleService.deleteRoleProgramInfo", vo);
		String optrPgmNo = vo.getOptrPgmNo();
		
		if (StringUtils.isNotEmpty(optrPgmNo)) {
			vo.setOptrPgmNos(optrPgmNo.split(","));
			mybatisDataAccessDAO.insert("site.userRole.service.UserRoleService.insertRoleProgramInfo", vo);
		}
	}

	/**
	 * 시스템 역할 정보를 삭제한다.
	 */ 
	public Object deleteRole(Map<String, Object> param) {
		return mybatisDataAccessDAO.selectByPk("site.userRole.service.UserRoleService.deleteRole", param);
	}
	
	/**
	 * 시스템 역할 정보를 복사한다.
	 */ 
	public Object copyRole(Map<String, Object> param) {
		return mybatisDataAccessDAO.selectByPk("site.userRole.service.UserRoleService.copyRole", param);
	}

	/**
	 * 시스템 역할 정보를 조회 한다.
	 */
	@SuppressWarnings("unchecked")
	public UserRoleVO selectRoleInfo(UserRoleVO vo) {
		return (UserRoleVO) mybatisDataAccessDAO.selectByPk("site.userRole.service.UserRoleService.selectRoleInfo", vo);
	}
	
	/**
	 * 시스템 역할에 따른 프로그램 목록 조회
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectRoleProgram(UserRoleVO vo) throws Exception {
		return mybatisDataAccessDAO.list("site.userRole.service.UserRoleService.selectRoleProgram", vo);
	}
		
}

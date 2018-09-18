package site.auth.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


import site.auth.service.AuthSearchVO;
import site.auth.service.AuthService;
import site.auth.service.AuthVO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;

@Service("authService")
public class AuthServiceImpl extends AbstractServiceImpl implements AuthService {
	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
	
	// 사용자 권한 목록 조회
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectUserList(AuthSearchVO searchVo) throws Exception {
		return mybatisDataAccessDAO.list("site.auth.service.AuthService.selectUserList", searchVo) ;
	}
	
	// 사용자 권한 목록 전체 건수 조회
	public int selectUserListTotCnt(AuthSearchVO searchVo) throws Exception {
		return (Integer) mybatisDataAccessDAO.select("site.auth.service.AuthService.selectUserListTotCnt", searchVo);
	}
	
	// 사용자 정보 조회
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectUserInfo(AuthSearchVO searchVo) throws Exception {
		return (Map<String, Object>) mybatisDataAccessDAO.selectByPk("site.auth.service.AuthService.selectUserInfo", searchVo) ;
	}
	
	// 권한 전체 조회
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAuthRoleList(AuthSearchVO vo) throws Exception {
		return mybatisDataAccessDAO.list("site.auth.service.AuthService.getAuthRoleList", vo) ;
	}
	
	// 사용자 권한 조회
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectAuthRole(AuthSearchVO searchVo) throws Exception {
		return mybatisDataAccessDAO.list("site.auth.service.AuthService.selectAuthRole", searchVo) ;
	}

	// 사용자 승인상태 조회
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectUserOptrApprStatCd(AuthSearchVO searchVo) throws Exception {
		
		// 프로퍼티에서 코드값을 가지고 와서 세팅
		searchVo.setSearchCdId("160");
		
		return mybatisDataAccessDAO.list("site.auth.service.AuthService.selectUserOptrApprStatCd", searchVo) ;
	}

	// 사용자 정보 수정
	public int updateUserInfo(AuthVO authVO) throws Exception {
		
		int resultCount = 0;
		String resultString = null;
		
		// 권한 삭제
		mybatisDataAccessDAO.delete("site.auth.service.AuthService.deleteUserAuth",authVO);	
		
		// 권한 추가
		// optrAuthNo 만큼 루프 돌면서 신규 권한 저장
		String optrAuthNos[] = authVO.getOptrAuthNo();
		
    	if (optrAuthNos != null && optrAuthNos.length > 0) {
    		
    		int optrAuthNosCount = optrAuthNos.length;
    		for (int i = 0; i < optrAuthNosCount; i++) {
    			authVO.setOptrAuthNoEach(optrAuthNos[i]);
    			
    			resultString = (String) mybatisDataAccessDAO.insert("site.auth.service.AuthService.insertUserAuth", authVO);
    			
    			if (StringUtils.isEmpty(resultString)) {
    				resultCount += 1;    				
    			}
    		}
    		
    	}
		
		// 사용자정보 저장
		resultCount += mybatisDataAccessDAO.update("site.auth.service.AuthService.updateUserInfo",authVO);	
		
		return resultCount;
	}
}

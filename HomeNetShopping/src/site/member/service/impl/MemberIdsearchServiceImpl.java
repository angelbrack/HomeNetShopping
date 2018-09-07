package site.member.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import prjframework.common.crypt.AES256Util;
import prjframework.common.crypt.SHA256Util;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import site.member.service.MemberIdsearchService;
import site.member.service.MemberIdsearchVO;


import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("memberIdsearchService")
public class MemberIdsearchServiceImpl extends AbstractServiceImpl implements MemberIdsearchService {

	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
	
	public String selectUserCheck(MemberIdsearchVO memberIdsearchVO) throws Exception {
		return (String) mybatisDataAccessDAO.select("MemberIdsearchService.selectUserCheck", memberIdsearchVO);
	}
	
	public String selectUserCheck2(MemberIdsearchVO memberIdsearchVO) throws Exception {
		return (String) mybatisDataAccessDAO.select("MemberIdsearchService.selectUserCheck2", memberIdsearchVO);
	}
	
	public void updateHandlePwd(MemberIdsearchVO memberIdsearchVO) throws Exception {
		
		AES256Util aes256util = new AES256Util();
		
		memberIdsearchVO.setLoginPwdA(aes256util.encrypt(memberIdsearchVO.getPassword()));
		memberIdsearchVO.setLoginPwdS(SHA256Util.encrypt(memberIdsearchVO.getPassword()));
		
		mybatisDataAccessDAO.update("MemberJoinService.updateHandleUserLogin", memberIdsearchVO);	
		mybatisDataAccessDAO.update("MemberJoinService.updateHandleUserAddCn", memberIdsearchVO);	
	}
}
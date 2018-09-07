package site.member.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import prjframework.common.crypt.AES256Util;
import prjframework.common.crypt.SHA256Util;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import prjframework.common.dataaccess.util.DataMap;
import prjframework.common.util.SessionUtil;
import site.member.service.MemberJoinVO;
import site.member.service.MemberService;
import site.member.service.MemberVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("memberService")
public class MemberServiceImpl extends AbstractServiceImpl implements MemberService {

	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
	
	public int selectCheckMemberTotCnt(MemberVO memberVO) throws Exception {
		memberVO.setLoginPwd(SHA256Util.encrypt(memberVO.getLoginPwd()));
		return (Integer) mybatisDataAccessDAO.selectByPk("MemberService.selectCheckMemberTotCnt", memberVO) ;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectHandleMemberInfo(MemberVO memberVO) throws Exception {
		return (Map<String, Object>) mybatisDataAccessDAO.selectByPk("MemberService.selectHandleMemberInfo", memberVO) ;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectHandleMemberInfoInput(MemberVO memberVO) throws Exception {
		return (Map<String, Object>) mybatisDataAccessDAO.selectByPk("MemberService.selectHandleMemberInfoInput", memberVO) ;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectHandleMemberTel(MemberVO memberVO) throws Exception {
		return (Map<String, Object>) mybatisDataAccessDAO.selectByPk("MemberService.selectHandleMemberTel", memberVO) ;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectHandleMemberHp(MemberVO memberVO) throws Exception {
		return (Map<String, Object>) mybatisDataAccessDAO.selectByPk("MemberService.selectHandleMemberHp", memberVO) ;
	}
	
	@SuppressWarnings("unchecked")
	public List<DataMap> selectHandleMemberCert(String userNo) throws Exception {
		return (List<DataMap>) mybatisDataAccessDAO.list("MemberService.selectHandleMemberCert", userNo) ;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectHandleMemberPopupInfo(MemberVO memberVO) throws Exception {
		return (Map<String, Object>) mybatisDataAccessDAO.selectByPk("MemberService.selectHandleMemberPopupInfo", memberVO) ;
	}
	
	/**
	 * 회원가입
	 * @throws Exception 
	 */
	public void insertHandle(MemberVO memberVO) throws Exception {

		AES256Util aes256util = new AES256Util();
		
		String userNo = SessionUtil.getUserNo();
		
		String tel1 = memberVO.getTelNo1();
		String tel2 = memberVO.getTelNo2();
		String tel3 = memberVO.getTelNo3();
		String hpNo1 = memberVO.getHpNo1();
		String hpNo2 = memberVO.getHpNo2();
		String hpNo3 = memberVO.getHpNo3();
		
		memberVO.setLoginPwdA(aes256util.encrypt(memberVO.getLoginPwd()));
		memberVO.setLoginPwdS(SHA256Util.encrypt(memberVO.getLoginPwd()));
		
		memberVO.setUserNo(userNo);
		memberVO.setWrtPerNo(userNo);
		memberVO.setUpdtPnNo(userNo);
		
		mybatisDataAccessDAO.update("MemberService.updateHandleUser", memberVO);	
		mybatisDataAccessDAO.update("MemberService.updateHandleUserLogin", memberVO);
		mybatisDataAccessDAO.update("MemberService.updateHandleUserAddCn", memberVO);
		mybatisDataAccessDAO.update("MemberService.updateHandleUserAddr", memberVO);
		mybatisDataAccessDAO.delete("MemberService.deleteHandleUserTel", memberVO);
		
		if(!tel1.equals("") && !tel2.equals("") && !tel3.equals("")) {
			mybatisDataAccessDAO.insert("MemberService.insertHandleUserTelNo.01", memberVO);
		}
		
		if(!hpNo1.equals("") && !hpNo2.equals("") && !hpNo3.equals("")) {
			mybatisDataAccessDAO.insert("MemberService.insertHandleUserTelNo.02", memberVO);
		}
		
		//자격증입력
		MemberJoinVO certInfo = new MemberJoinVO();
		certInfo.setUserNo(userNo);
		certInfo.setWrtPerNo(userNo);
		certInfo.setUpdtPnNo(userNo);
		
		mybatisDataAccessDAO.delete("MemberJoinService.deleteHandleJoinUserCert", certInfo);
		
		if(memberVO.getCertCds() != null && memberVO.getCertCds().length > 0) {
			for(String certCd : memberVO.getCertCds()) {
				certInfo.setCertCd(certCd);
				mybatisDataAccessDAO.insert("MemberJoinService.insertHandleJoinUserCert", certInfo);
			}
		}
		
	}
	
	/**
	 * 회원탈퇴
	 * @throws Exception 
	 */
	public void insertWithdrawal(MemberVO memberVO) throws Exception {

		mybatisDataAccessDAO.update("MemberService.updateWithdrawalUser", memberVO);
		mybatisDataAccessDAO.update("MemberService.updateWithdrawalUserLogin", memberVO);	
	}
	
	/**
	 * 교육기관담당자 승인요청
	 * @throws Exception 
	 */
	public void updateMemberAppr(MemberVO memberVO) throws Exception  {
		mybatisDataAccessDAO.update("MemberService.updateMemberAppr",memberVO);	
	}
	
	/**
	 * 파일을 저장한다.
	 * @throws Exception 
	 */
	public void insertAtchFile(MemberVO memberVO) throws Exception  {
		mybatisDataAccessDAO.update("MemberService.insertAtchFile",memberVO);	
	}
	
	/**
	 * 파일을 삭제한다.
	 * @throws Exception 
	 */
	public void deleteAtchFile(MemberVO memberVO) throws Exception  {
		mybatisDataAccessDAO.update("MemberService.deleteAtchFile",memberVO);	
	}
}
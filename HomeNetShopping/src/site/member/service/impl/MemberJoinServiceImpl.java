package site.member.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import prjframework.common.crypt.AES256Util;
import prjframework.common.crypt.SHA256Util;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import site.member.service.MemberJoinService;
import site.member.service.MemberJoinVO;


import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("memberJoinService")
public class MemberJoinServiceImpl extends AbstractServiceImpl implements MemberJoinService {

	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
	
	public int checkIdDplct(String checkId) throws Exception {
		return (Integer) mybatisDataAccessDAO.selectByPk("MemberJoinService.checkIdDplct", checkId) ;
	}
	
	public int checkEmailDplct(String checkEmail) throws Exception {
		return (Integer) mybatisDataAccessDAO.selectByPk("MemberJoinService.checkEmailDplct", checkEmail) ;
	}
	
    /**
     * 소속기관 목록을 조회
     */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBlngList(MemberJoinVO memberJoinVO) throws Exception {
		return mybatisDataAccessDAO.list("MemberJoinService.selectBlngList", memberJoinVO) ;
	}
	
	/**
	 * 소속기관 총 카운트를 조회
	 */
	public int selectBlngListTotCnt(MemberJoinVO memberJoinVO) throws Exception {
		return (Integer) mybatisDataAccessDAO.select("MemberJoinService.selectBlngListTotCnt", memberJoinVO);
	}
	
	/**
	 * 회원가입
	 * @throws Exception 
	 */
	public void insertHandleJoin(MemberJoinVO memberJoinVO) throws Exception {

		AES256Util aes256util = new AES256Util();
		
		String userNo = (String) mybatisDataAccessDAO.selectByPk("MemberJoinService.selectUserNoSequence", "");
		String frnYn = memberJoinVO.getFrnYn();
		String tel1 = memberJoinVO.getTelNo1();
		String tel2 = memberJoinVO.getTelNo2();
		String tel3 = memberJoinVO.getTelNo3();
		String hpNo1 = memberJoinVO.getHpNo1();
		String hpNo2 = memberJoinVO.getHpNo2();
		String hpNo3 = memberJoinVO.getHpNo3();
		
		memberJoinVO.setLoginPwdA(aes256util.encrypt(memberJoinVO.getLoginPwd()));
		memberJoinVO.setLoginPwdS(SHA256Util.encrypt(memberJoinVO.getLoginPwd()));
		
		memberJoinVO.setUserNo(userNo);
		memberJoinVO.setWrtPerNo(userNo);
		memberJoinVO.setUpdtPnNo(userNo);
		
		memberJoinVO.setMbrDivDd("1");
		memberJoinVO.setMbrRegTpCd("1");
		memberJoinVO.setOptrAuthCd("99");
		memberJoinVO.setExfireYn("N");
		memberJoinVO.setOptrApprYn("N");
		memberJoinVO.setUserAddrDivCd("1");

		if(frnYn != null && frnYn.equals("Y")) {
			memberJoinVO.setMbrYn("N");
		} else {
			memberJoinVO.setMbrYn("Y");
		}
		
		mybatisDataAccessDAO.insert("MemberJoinService.insertHandleJoinUser", memberJoinVO);	
		mybatisDataAccessDAO.insert("MemberJoinService.insertHandleJoinUserLogin", memberJoinVO);
		mybatisDataAccessDAO.insert("MemberJoinService.insertHandleJoinUserAddCn", memberJoinVO);
		mybatisDataAccessDAO.insert("MemberJoinService.insertHandleJoinUserAddr", memberJoinVO);
		
		if(frnYn != null && frnYn.equals("N")) {
			mybatisDataAccessDAO.insert("MemberJoinService.insertHandleJoinUserCi", memberJoinVO);
		}

		if(!tel1.equals("") && !tel2.equals("") && !tel3.equals("")) {
			mybatisDataAccessDAO.insert("MemberJoinService.insertHandleJoinUserTelNo.01", memberJoinVO);
		}
		
		if(!hpNo1.equals("") && !hpNo2.equals("") && !hpNo3.equals("")) {
			mybatisDataAccessDAO.insert("MemberJoinService.insertHandleJoinUserTelNo.02", memberJoinVO);
		}	
		
		//sub check 수강생 이력 정보가 있는 경우에는 매핑처리
		int complHistCnt = (Integer) mybatisDataAccessDAO.select("MemberJoinService.selectComplHistTotCnt", memberJoinVO);
		
		if(complHistCnt > 0) {
			mybatisDataAccessDAO.update("MemberJoinService.updateHandleJoinUserComplHist", memberJoinVO);	
			mybatisDataAccessDAO.insert("MemberJoinService.updateHandleJoinUserComplHistLog", memberJoinVO);	
		}
		
		//자격증입력
		if(memberJoinVO.getCertCds() != null && memberJoinVO.getCertCds().length > 0) {
			for(String certCd : memberJoinVO.getCertCds()) {
				MemberJoinVO certInfo = new MemberJoinVO();
				certInfo.setUserNo(userNo);
				certInfo.setCertCd(certCd);
				certInfo.setWrtPerNo(userNo);
				certInfo.setUpdtPnNo(userNo);
				mybatisDataAccessDAO.insert("MemberJoinService.insertHandleJoinUserCert", certInfo);
			}
		}
	}
	
	/**
     * 외국인본인인증
     */
	public void updateHandleMail(String loginId) throws Exception {
		mybatisDataAccessDAO.update("MemberJoinService.updateHandleMail", loginId);	
	}
	
}
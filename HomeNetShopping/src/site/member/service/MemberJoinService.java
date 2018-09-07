package site.member.service;

import java.util.List;
import java.util.Map;

import site.member.service.MemberJoinVO;

public interface MemberJoinService {
	/**
	 * ID 중복체크
	 * @return
	 * @throws Exception
	 */
	public int checkIdDplct(String checkId) throws Exception;
	
	/**
	 * 이메일 중복체크
	 * @return
	 * @throws Exception
	 */
	public int checkEmailDplct(String checkEmail) throws Exception;
	
	/**
	 * 소속기관 목록 조회
	 * @param memberJoinVO
	 * @return
	 */
	public List<Map<String, Object>> selectBlngList(MemberJoinVO memberJoinVO) throws Exception;
	
	/**
	 * 소속기관 총 조회 건수
	 * @param memberJoinVO
	 * @return
	 */
	public int selectBlngListTotCnt(MemberJoinVO memberJoinVO)throws Exception;
	
	/**
	 * 회원가입
	 * @param memberJoinVO
	 */
	public void insertHandleJoin(MemberJoinVO memberJoinVO)throws Exception;
	
	/**
	 * 외국인본인인증
	 * @param memberJoinVO
	 */
	public void updateHandleMail(String loginId)throws Exception;
	
}

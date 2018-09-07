package site.member.service;

import java.util.List;
import java.util.Map;

import prjframework.common.dataaccess.util.DataMap;

public interface MemberService {
	/**
	 * 비밀번호 재확인
	 * @return
	 * @throws Exception
	 */
	public int selectCheckMemberTotCnt(MemberVO memberVO) throws Exception;
	
	/**
	 * 회원정보 상세조회
	 * @param memberVO
	 * @return
	 */
	public Map<String, Object> selectHandleMemberInfo(MemberVO memberVO)throws Exception;
	
	/**
	 * 회원정보 상세조회
	 * @param memberVO
	 * @return
	 */
	public Map<String, Object> selectHandleMemberInfoInput(MemberVO memberVO)throws Exception;
	
	
	/**
	 * 회원정보 전화번호
	 * @param memberVO
	 * @return
	 */
	public Map<String, Object> selectHandleMemberTel(MemberVO memberVO)throws Exception;
	
	/**
	 * 회원정보 휴대폰
	 * @param memberVO
	 * @return
	 */
	public Map<String, Object> selectHandleMemberHp(MemberVO memberVO)throws Exception;

	/**
	 * 회원정보 자격증정보
	 * @param memberVO
	 * @return
	 */
	public List<DataMap> selectHandleMemberCert(String userNo) throws Exception;
	
	/**
	 * 회원정보 조회
	 * @param memberVO
	 * @return
	 */
	public Map<String, Object> selectHandleMemberPopupInfo(MemberVO memberVO)throws Exception;
	
	/**
	 * 회원정보수정
	 * @param memberVO
	 */
	public void insertHandle(MemberVO memberVO)throws Exception;
	
	/**
	 * 교육기관담당자 승인요청
	 * @param memberVO
	 */
	public void updateMemberAppr(MemberVO memberVO)throws Exception;
	
	/**
	 * 회원탈퇴
	 * @param memberVO
	 */
	public void insertWithdrawal(MemberVO memberVO)throws Exception;
	
	/**
	 * 파일 저장
	 * @param memberVO
	 */
	public void insertAtchFile(MemberVO memberVO)throws Exception;
	
	
	/**
	 * 파일 삭제
	 * @param memberVO
	 */
	public void deleteAtchFile(MemberVO memberVO)throws Exception;
	
}
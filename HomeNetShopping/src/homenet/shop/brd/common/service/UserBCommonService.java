package homenet.shop.brd.common.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * -----------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------------
 * 소속 : 고려대학교 경력개발센터
 * 수정일 : 2018.03.15
 * 수정자 : 엄재덕
 * 수정내용 : 최초생성
 * ------------------------------------------------------------------------
 */
public interface UserBCommonService {

	/**
	 * 목적 : 공통게시판 옵션 조회
	 * 매개변수 : UserBoardVO
	 * 반환값 : UserBoardVO
	 * 개정이력 : 없음
	 */
	public UserBoardVO retrieveBoardOtpInfo(UserBoardVO vo) throws Exception;
	
	/**
	 * 목적 : 공통게시판 조회
	 * 매개변수 : UserBoardVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	public List<EgovMap> retrieveList(UserBoardVO vo) throws Exception;

	/**
	 * 목적 : 공통게시판 조회 카운트
	 * 매개변수 : UserBoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int retrieveCount(UserBoardVO vo) throws Exception;

	/**
	 * 목적 : 공통게시판 상단공지 조회
	 * 매개변수 : UserBoardVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	public List<EgovMap> retrieveListTop(UserBoardVO vo) throws Exception;

	/**
	 * 목적 : 공통게시판 코드 조회
	 * 매개변수 : UserBoardVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	public List<EgovMap> retrieveListCode(UserBoardVO vo) throws Exception;
	
	/**
	 * 목적 : 공통게시판 조회
	 * 매개변수 : UserBoardVO
	 * 반환값 : UserBoardVO
	 * 개정이력 : 없음
	 */
	public UserBoardVO retrieveInfo(UserBoardVO vo) throws Exception;

	/**
	 * 목적 : 파일첨부 조회
	 * 매개변수 : UserBoardVO
	 * 반환값 : UserBoardVO
	 * 개정이력 : 없음
	 */
	public List<EgovMap> retrieveFile(UserBoardVO vo) throws Exception;
	
	/**
	 * 목적 : 파일첨부 조회
	 * 매개변수 : UserBoardVO
	 * 반환값 : UserBoardVO
	 * 개정이력 : 없음
	 */
	public List<EgovMap> retrieveFile2(UserBoardVO vo) throws Exception;

	/**
	 * 목적 : 공통게시판 상세 카운트
	 * 매개변수 : UserBoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int modifyCount(UserBoardVO vo) throws Exception;
	
	/**
	 * 목적 : 공통게시판 등록 key생성
	 * 매개변수 : UserBoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public String bdotSeq() throws Exception;

	/**
	 * 목적 : 공통게시판 등록
	 * 매개변수 : UserBoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int registerQanda(UserBoardVO vo) throws Exception;
	
	/**
	 * 목적 : 사용자정보 조회
	 * 매개변수 : UserBoardVO
	 * 반환값 : UserBoardVO
	 * 개정이력 : 없음
	 */
	public UserBoardVO retrieveBoardUserInfo(UserBoardVO vo) throws Exception;
	
	/**
	 * 목적 : 공통게시판 수정
	 * 매개변수 : UserBoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int modifyQanda(UserBoardVO vo) throws Exception;
	
	/**
	 * 목적 : 게시판 삭제
	 * 매개변수 : UserBoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int delete(UserBoardVO vo) throws Exception;
}

package homenet.shop.brd.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
public interface MgntBCommonService {
	/**
	 * 목적 : 게시판 조회
	 * 매개변수 : BoardVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	public List<EgovMap> retrieveList(BoardVO vo) throws Exception;

	/**
	 * 목적 : 게시판 조회 카운트
	 * 매개변수 : BoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int retrieveCount(BoardVO vo) throws Exception;

	/**
	 * 목적 : 검색어 조회
	 * 매개변수 : BoardVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	public List<EgovMap> retrieveListSrchwrd(BoardVO vo) throws Exception;

	/**
	 * 목적 : 공통게시판 옵션 조회
	 * 매개변수 : BoardVO
	 * 반환값 : BoardVO
	 * 개정이력 : 없음
	 */
	public BoardVO retrieveBoardOtpInfo(BoardVO vo) throws Exception;

	/**
	 * 목적 : 게시판 조회
	 * 매개변수 : BoardVO
	 * 반환값 : BoardVO
	 * 개정이력 : 없음
	 */
	public BoardVO retrieveInfo(BoardVO vo) throws Exception;

	/**
	 * 목적 : 파일첨부 조회
	 * 매개변수 : BoardVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	public List<EgovMap> retrieveFile(BoardVO vo) throws Exception;

	/**
	 * 목적 : 썸네일 조회
	 * 매개변수 : BoardVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	public List<EgovMap> retrieveFileThumb(BoardVO vo) throws Exception;
	
	/**
	 * 목적 : 답변자 첨부파일 조회
	 * 매개변수 : BoardVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	public List<EgovMap> retrieveFileAns(BoardVO vo) throws Exception;

	/**
	 * 목적 : 게시판 등록 key생성
	 * 매개변수 : BoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public String bdotSeq() throws Exception;

	/**
	 * 목적 : 게시판 등록
	 * 매개변수 : BoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int register(BoardVO vo) throws Exception;

	/**
	 * 목적 : 게시판 수정
	 * 매개변수 : BoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int modify(BoardVO vo, HttpServletRequest request) throws Exception;

	/**
	 * 목적 : 답변구분 등록
	 * 매개변수 : BoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int modifyAns(BoardVO vo) throws Exception;

	/**
	 * 목적 : 게시판 삭제
	 * 매개변수 : BoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int delete(BoardVO vo) throws Exception;
}

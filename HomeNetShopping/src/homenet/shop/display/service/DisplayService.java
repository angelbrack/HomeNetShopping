package homenet.shop.display.service;

import java.util.List;

/**
 * <p>전시 관리 Service</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 21.</li>
 * </ul>
 */
public interface DisplayService {
	
	/**
	 * 목적 : 전시매장 정보   조회
	 * 매개변수 : DispShopBaseVO paramVO 조회조건
	 * 반환값 : List<DispShopBaseVO> 전시매장 정보 리스트
	 * 개정이력 : 없음
	 */
	List<DispShopBaseVO> selectDisplayList(DispShopBaseVO paramVO) throws Exception;
	
	/**
	 * 목적 : 전시매장 정보   상세조회
	 * 매개변수 : DispShopBaseVO paramVO 조회조건
	 * 반환값 : List<DispShopBaseVO> 전시매장 정보 리스트
	 * 개정이력 : 없음
	 */
	DispShopBaseVO selectDisplayInfo(DispShopBaseVO paramVO) throws Exception;
	
	/*
	 * 전시매장 생성
	 * @param  : DispShopBaseVO 등록 정보
	 * 
	 * @return : Integer 전시 번호
	 */
	Integer selectDisplayPrimaryKeySequence(DispShopBaseVO paramVO) throws Exception;
	
	/*
	 * 전시매장 저장
	 * @param  : DispShopBaseVO 등록 정보
	 * 
	 * @return : Integer 전시매장 저장 결과
	 */
	Integer saveDisplay(DispShopBaseVO paramVO) throws Exception;
	
	/*
	 * 전시매장 등록
	 * @param  : DispShopBaseVO 등록 정보
	 * 
	 * @return : Integer 전시매장 등록 결과
	 */
	Integer insertDisplayBase(DispShopBaseVO paramVO) throws Exception;
	
	/*
	 * 전시매장 수정
	 * @param  : DispShopBaseVO 수정 정보
	 * 
	 * @return : Integer 전시매장 수정 결과
	 */
	Integer updateDisplayBase(DispShopBaseVO paramVO) throws Exception;
	
	/*
	 * 전시매장 삭제
	 * @param  : DispShopBaseVO 삭제 정보
	 * 
	 * @return : Integer 전시매장 삭제 결과
	 */
	Integer deleteDisplayBase(DispShopBaseVO paramVO) throws Exception;
	
	/**
	 * 목적 : 전시 이미지 정보   조회
	 * 매개변수 : DispShopBaseVO paramVO 조회조건
	 * 반환값 : List<DispImgInfoVO> 전시 이미지 정보 리스트
	 * 개정이력 : 없음
	 */
	List<DispImgInfoVO> selectDisplayImgInfoList(DispShopBaseVO paramVO) throws Exception;
	
	/*
	 * 전시 이미지 등록
	 * @param  : DispShopBaseVO 등록 정보
	 * 
	 * @return : Integer 전시 이미지 등록 결과
	 */
	Integer insertDisplayImgInfo(DispImgInfoVO paramVO) throws Exception;
	
	/*
	 * 전시 이미지 삭제
	 * @param  : DispShopBaseVO 삭제 정보
	 * 
	 * @return : Integer 전시 이미지 삭제 결과
	 */
	Integer deleteDisplayImgInfo(DispImgInfoVO paramVO) throws Exception;

}

package homenet.shop.brand.service;

import java.util.List;

/**
 * <p>브랜드 관리 Service</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 18.</li>
 * </ul>
 */
public interface BrandService {
	
	/**
	 * 목적 : 브랜드 정보   조회
	 * 매개변수 : BrndBaseVO paramVO 조회조건
	 * 반환값 : List<BrndBaseVO> 브랜드 정보 리스트
	 * 개정이력 : 없음
	 */
	List<BrndBaseVO> selectBrandList(BrndBaseVO paramVO) throws Exception;
	
	/**
	 * 목적 : 브랜드 상세정보   조회
	 * 매개변수 : BrndBaseVO paramVO
	 * 반환값 : BrndBaseVO 브랜드 상세정보
	 * 개정이력 : 없음
	 */
	BrndBaseVO selectBrandInfo(BrndBaseVO paramVO) throws Exception;

	/**
	 * 목적 : 브랜드 이미지 정보   조회
	 * 매개변수 : BrndImgInfoVO paramVO 조회조건
	 * 반환값 : List<BrndImgInfoVO> 브랜드 이미지 정보 리스트
	 * 개정이력 : 없음
	 */
	List<BrndImgInfoVO> selectBrandImgList(BrndImgInfoVO paramVO) throws Exception;
	
	/*
	 * 브랜드번호 생성
	 * @param  : BrndBaseVO 등록 정보
	 * 
	 * @return : Integer 브랜드번호
	 */
	Integer selectBrandNoPk(BrndBaseVO paramVO) throws Exception;
	
	/*
	 * 브랜드 저장
	 * @param  : BrndBaseVO 등록 정보
	 * 
	 * @return : Integer 브랜드 저장 결과
	 */
	Integer saveBrand(BrndBaseVO paramVO) throws Exception;
	
	/*
	 * 브랜드 삭제
	 * @param  : BrndBaseVO 삭제 정보
	 * 
	 * @return : Integer 브랜드 삭제 결과
	 */
	Integer deleteBrand(BrndBaseVO paramVO) throws Exception;
	
	/*
	 * 브랜드 등록
	 * @param  : BrndBaseVO 등록 정보
	 * 
	 * @return : Integer 브랜드 등록 결과
	 */
	Integer insertBrandBase(BrndBaseVO paramVO) throws Exception;
	
	/*
	 * 브랜드 수정
	 * @param  : BrndBaseVO 수정 정보
	 * 
	 * @return : Integer 브랜드 수정 결과
	 */
	Integer updateBrandBase(BrndBaseVO paramVO) throws Exception;
	
	/*
	 * 브랜드 삭제
	 * @param  : BrndBaseVO 삭제 정보
	 * 
	 * @return : Integer 브랜드 삭제 결과
	 */
	Integer deleteBrandBase(BrndBaseVO paramVO) throws Exception;
	
	/*
	 * 브랜드 이미지 저장
	 * @param  : BrndBaseVO 등록 정보
	 * 
	 * @return : Integer 브랜드 이미지 저장 결과
	 */
	Integer saveBrandImgInfo(BrndBaseVO paramVO) throws Exception;
	
	/*
	 * 브랜드 이미지 등록
	 * @param  : BrndBaseVO 등록 정보
	 * 
	 * @return : Integer 브랜드 등록 결과
	 */
	Integer insertBrandImgInfo(BrndImgInfoVO paramVO) throws Exception;
	
	/*
	 * 브랜드 이미지 수정
	 * @param  : BrndBaseVO 수정 정보
	 * 
	 * @return : Integer 브랜드 수정 결과
	 */
	Integer updateBrandImgInfo(BrndImgInfoVO paramVO) throws Exception;
	
	/*
	 * 브랜드 이미지 삭제
	 * @param  : BrndBaseVO 삭제 정보
	 * 
	 * @return : Integer 브랜드 삭제 결과
	 */
	Integer deleteBrandImgInfo(BrndBaseVO paramVO) throws Exception;
}

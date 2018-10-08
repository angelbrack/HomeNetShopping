package homenet.shop.article.service;

import java.util.List;

public interface GoodsArticleService {
	
	/**
	 * 품목군 정보  조회
	 * @param  	: GoodsArtcCdVO paramVO 조회조건
	 * @return 	: List<GoodsArtcCdVO> 품목군 정보 리스트
	 * 개정이력 	: 없음
	 */
	List<GoodsArtcCdVO> selectGoodsArtcCdList(GoodsArtcCdVO paramVO) throws Exception;
	
	/**
	 * 품목군 Tree 정보  조회
	 * @param  	: GoodsArtcCdVO paramVO 조회조건
	 * @return 	: List<GoodsArtcCdVO> 품목군 Tree 정보 리스트
	 * 개정이력 	: 없음
	 */
	List<GoodsArtcCdVO> selectGoodsArtcCdTreeList(GoodsArtcCdVO paramVO) throws Exception;
	
	/**
	 * 품목군 상세 정보  조회
	 * @param  	: GoodsArtcCdVO paramVO 조회조건
	 * @return 	: List<GoodsArtcCdVO> 품목군 상세 정보 리스트
	 * 개정이력 	: 없음
	 */
	GoodsArtcCdVO selectGoodsArtcCdInfo(GoodsArtcCdVO paramVO) throws Exception;
	
	/*
	 * 품목군 저장
	 * @param  	: List<GoodsArtcCdVO> 저장 정보
	 * @return 	: Integer 품목군 등록 결과
	 * 개정이력 	: 없음
	 */
	Integer saveGoodsArtcCdList(List<GoodsArtcCdVO> list) throws Exception;
	
	/*
	 * 품목군 등록
	 * @param  	: GoodsArtcCdVO 등록 정보
	 * @return 	: Integer 품목군 등록 결과
	 * 개정이력 	: 없음
	 */
	Integer insertGoodsArtcCd(GoodsArtcCdVO paramVO) throws Exception;
	
	/*
	 * 품목군 수정
	 * @param  	: GoodsArtcCdVO 등록 정보
	 * @return 	: Integer 품목군 등록 결과
	 * 개정이력 	: 없음
	 */
	Integer updateGoodsArtcCd(GoodsArtcCdVO paramVO) throws Exception;
	
	/*
	 * 품목군 삭제
	 * @param  	: GoodsArtcCdVO 등록 정보
	 * @return 	: Integer 품목군 등록 결과
	 * 개정이력 	: 없음
	 */
	Integer deleteGoodsArtcCd(GoodsArtcCdVO paramVO) throws Exception;

}

package homenet.shop.article.service;

import java.util.List;

import homenet.shop.brand.service.BrndBaseVO;

public interface GoodsArticleService {
	
	
	/*
	 * 품목군 저장
	 * @param  : List<GoodsArtcCdVO> 저장 정보
	 * 
	 * @return : Integer 품목군 등록 결과
	 */
	Integer saveGoodsArtcCdList(List<GoodsArtcCdVO> list) throws Exception;
	
	/*
	 * 품목군 등록
	 * @param  : GoodsArtcCdVO 등록 정보
	 * 
	 * @return : Integer 품목군 등록 결과
	 */
	Integer insertGoodsArtcCd(GoodsArtcCdVO paramVO) throws Exception;

}

package homenet.shop.article.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import homenet.shop.article.service.GoodsArtcCdVO;
import homenet.shop.article.service.GoodsArticleService;
import homenet.shop.brand.service.impl.BrandServiceImpl;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;

/**
 * <p>품목군 관리 Service 구현</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 10. 04.</li>
 * </ul>
 */
@Service
@Qualifier("goodsArticleService")
public class GoodsArticleServiceImpl implements GoodsArticleService {
	
	private static final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);
	
	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;

	/**
	 * 품목군 정보  조회
	 * @param  	: GoodsArtcCdVO paramVO 조회조건
	 * @return 	: List<GoodsArtcCdVO> 품목군 정보 리스트
	 * 개정이력 	: 없음
	 */
	public List<GoodsArtcCdVO> selectGoodsArtcCdList(GoodsArtcCdVO paramVO) throws Exception {
		return (List<GoodsArtcCdVO>) mybatisDataAccessDAO.list("homenet.shop.article.service.GoodsArticleService.selectGoodsArtcCdList", paramVO);
	}
	
	/**
	 * 품목군 Tree 정보  조회
	 * @param  	: GoodsArtcCdVO paramVO 조회조건
	 * @return 	: List<GoodsArtcCdVO> 품목군 Tree 정보 리스트
	 * 개정이력 	: 없음
	 */
	public List<GoodsArtcCdVO> selectGoodsArtcCdTreeList(GoodsArtcCdVO paramVO) throws Exception {
		return (List<GoodsArtcCdVO>) mybatisDataAccessDAO.list("homenet.shop.article.service.GoodsArticleService.selectGoodsArtcCdTreeList", paramVO);
	}
	
	/**
	 * 품목군 상세 정보  조회
	 * @param  	: GoodsArtcCdVO paramVO 조회조건
	 * @return 	: List<GoodsArtcCdVO> 품목군 상세 정보 리스트
	 * 개정이력 	: 없음
	 */
	public GoodsArtcCdVO selectGoodsArtcCdInfo(GoodsArtcCdVO paramVO) throws Exception {
		return (GoodsArtcCdVO) mybatisDataAccessDAO.select("homenet.shop.article.service.GoodsArticleService.selectGoodsArtcCdInfo", paramVO);
	}
	
	/*
	 * 품목군 저장 - 리스트
	 * @param  : List<GoodsArtcCdVO> 저장 정보
	 * @return : Integer 품목군 등록 결과
	 */
	public Integer saveGoodsArtcCdList(List<GoodsArtcCdVO> list) throws Exception {
		int result = 0;
		
		for( GoodsArtcCdVO goodsArtcCdVO : list ) {
			result += insertGoodsArtcCd(goodsArtcCdVO);
		}
		
		return result;
	}
	
	/*
	 * 품목군 저장
	 * @param  : GoodsArtcCdVO 저장 정보
	 * @return : Integer 품목군 등록 결과
	 */
	public Integer saveGoodsArtcCd(GoodsArtcCdVO paramVO) throws Exception {
		int result = 0;
		
		if ( "I".equals(paramVO.getCmd()) ) {
			result += insertGoodsArtcCd(paramVO);
		} else {
			result += updateGoodsArtcCd(paramVO);
		}
		return result;
	}
	
	/*
	 * 품목군 등록
	 * @param  : GoodsArtcCdVO 등록 정보
	 * @return : Integer 품목군 등록 결과
	 */
	public Integer insertGoodsArtcCd(GoodsArtcCdVO paramVO) throws Exception {
		return (Integer) mybatisDataAccessDAO.insert("homenet.shop.article.service.GoodsArticleService.insertGoodsArtcCd", paramVO);
	}
	
	/*
	 * 품목군 수정
	 * @param  	: GoodsArtcCdVO 등록 정보
	 * @return 	: Integer 품목군 등록 결과
	 * 개정이력 	: 없음
	 */
	public Integer updateGoodsArtcCd(GoodsArtcCdVO paramVO) throws Exception {
		return mybatisDataAccessDAO.update("homenet.shop.article.service.GoodsArticleService.updateGoodsArtcCd", paramVO);
	}
	
	/*
	 * 품목군 관련 정보 삭제
	 * @param  	: GoodsArtcCdVO 삭제 정보
	 * @return 	: Integer 품목군 삭제 결과
	 * 개정이력 	: 없음
	 */
	public Integer deleteArticle(GoodsArtcCdVO paramVO) throws Exception {
		int result = 0;
		
		result += deleteGoodsArtcCd(paramVO);
		
		return result;
	}
	
	/*
	 * 품목군 삭제
	 * @param  	: GoodsArtcCdVO 등록 정보
	 * @return 	: Integer 품목군 등록 결과
	 * 개정이력 	: 없음
	 */
	public Integer deleteGoodsArtcCd(GoodsArtcCdVO paramVO) throws Exception {
		return mybatisDataAccessDAO.delete("homenet.shop.article.service.GoodsArticleService.deleteGoodsArtcCd", paramVO);
	}
	
}

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

	/*
	 * 품목군 저장
	 * @param  : List<GoodsArtcCdVO> 저장 정보
	 * 
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
	 * 품목군 등록
	 * @param  : GoodsArtcCdVO 등록 정보
	 * 
	 * @return : Integer 품목군 등록 결과
	 */
	public Integer insertGoodsArtcCd(GoodsArtcCdVO paramVO) throws Exception {
		return (Integer) mybatisDataAccessDAO.insert("homenet.shop.article.service.GoodsArtcCdService.insertGoodsArtcCd", paramVO);
	}
	
}

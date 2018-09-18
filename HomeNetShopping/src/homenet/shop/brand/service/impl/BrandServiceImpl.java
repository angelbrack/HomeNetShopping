package homenet.shop.brand.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import homenet.shop.brand.service.BrandService;
import homenet.shop.brand.service.BrndBaseVO;
import homenet.shop.brand.service.BrndImgInfoVO;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;

/**
 * <p>브랜드 관리 Service 구현</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 18.</li>
 * </ul>
 */
@Service
@Qualifier("brandService")
public class BrandServiceImpl implements BrandService {
	
	private static final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);
	
	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
	
	/**
	 * 목적 : 브랜드 정보   조회
	 * 매개변수 : BrndBaseVO paramVO 조회조건
	 * 반환값 : List<BrndBaseVO> 브랜드 정보 리스트
	 * 개정이력 : 없음
	 */
	public List<BrndBaseVO> selectBrandList(BrndBaseVO paramVO) throws Exception {
		return (List<BrndBaseVO>) mybatisDataAccessDAO.list("homenet.shop.brand.service.BrandService.selectBrandList", paramVO);
	}
	
	/**
	 * 목적 : 브랜드 상세정보   조회
	 * 매개변수 : BrndBaseVO paramVO
	 * 반환값 : BrndBaseVO 브랜드 상세정보
	 * 개정이력 : 없음
	 */
	public BrndBaseVO selectBrandInfo(BrndBaseVO paramVO) throws Exception {
		return (BrndBaseVO) mybatisDataAccessDAO.select("homenet.shop.brand.service.BrandService.selectBrandInfo", paramVO);
	}

	/**
	 * 목적 : 브랜드 이미지 정보   조회
	 * 매개변수 : BrndImgInfoVO paramVO 조회조건
	 * 반환값 : List<BrndImgInfoVO> 브랜드 이미지 정보 리스트
	 * 개정이력 : 없음
	 */
	public List<BrndImgInfoVO> selectBrandImgList(BrndImgInfoVO paramVO) throws Exception {
		return (List<BrndImgInfoVO>) mybatisDataAccessDAO.list("homenet.shop.brand.service.BrandService.selectBrandImgList", paramVO);
	}

}

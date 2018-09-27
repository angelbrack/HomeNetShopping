package homenet.shop.display.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import homenet.shop.brand.service.BrandService;
import homenet.shop.brand.service.impl.BrandServiceImpl;
import homenet.shop.display.service.DispImgInfoVO;
import homenet.shop.display.service.DispShopBaseVO;
import homenet.shop.display.service.DisplayService;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;

/**
 * <p>전시 관리 Service 구현</p>
 *
 * <ul>
 * <li>Created by EumJaeDuck, 2018. 9. 21.</li>
 * </ul>
 */

@Service
@Qualifier("displayService")
public class DisplayServiceImpl implements DisplayService {
	
	private static final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);
	
	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
	
	/**
	 * 목적 : 전시매장 정보   조회
	 * 매개변수 : DispShopBaseVO paramVO 조회조건
	 * 반환값 : List<DispShopBaseVO> 전시매장 정보 리스트
	 * 개정이력 : 없음
	 */
	public List<DispShopBaseVO> selectDisplayList(DispShopBaseVO paramVO) throws Exception {
		return (List<DispShopBaseVO>) mybatisDataAccessDAO.list("homenet.shop.display.service.DisplayService.selectDisplayList", paramVO);
	}
	
	/**
	 * 목적 : 전시매장 정보   상세조회
	 * 매개변수 : DispShopBaseVO paramVO 조회조건
	 * 반환값 : List<DispShopBaseVO> 전시매장 정보 리스트
	 * 개정이력 : 없음
	 */
	public DispShopBaseVO selectDisplayInfo(DispShopBaseVO paramVO) throws Exception {
		return (DispShopBaseVO) mybatisDataAccessDAO.select("homenet.shop.display.service.DisplayService.selectDisplayInfo", paramVO);
	}
	
	/*
	 * 전시매장 생성
	 * @param  : DispShopBaseVO 등록 정보
	 * 
	 * @return : Integer 전시 번호
	 */
	public Integer selectDisplayPrimaryKeySequence(DispShopBaseVO paramVO) throws Exception {
		return (Integer) mybatisDataAccessDAO.select("homenet.shop.display.service.DisplayService.selectDisplayPrimaryKeySequence", paramVO);
	}
	
	/*
	 * 전시매장 저장
	 * @param  : DispShopBaseVO 등록 정보
	 * 
	 * @return : Integer 전시매장 저장 결과
	 */
	public Integer saveDisplay(DispShopBaseVO paramVO) throws Exception {
		
		int result	= 0;
		
		
		return result;
	}
	
	/*
	 * 전시매장 등록
	 * @param  : DispShopBaseVO 등록 정보
	 * 
	 * @return : Integer 전시매장 등록 결과
	 */
	public Integer insertDisplayBase(DispShopBaseVO paramVO) throws Exception {
		return (Integer) mybatisDataAccessDAO.insert("homenet.shop.display.service.DisplayService.insertDisplayBase", paramVO);
	}
	
	/*
	 * 전시매장 수정
	 * @param  : DispShopBaseVO 수정 정보
	 * 
	 * @return : Integer 전시매장 수정 결과
	 */
	public Integer updateDisplayBase(DispShopBaseVO paramVO) throws Exception {
		return mybatisDataAccessDAO.update("homenet.shop.display.service.DisplayService.updateDisplayBase", paramVO);
	}
	
	/*
	 * 전시매장 삭제
	 * @param  : DispShopBaseVO 삭제 정보
	 * 
	 * @return : Integer 전시매장 삭제 결과
	 */
	public Integer deleteDisplayBase(DispShopBaseVO paramVO) throws Exception {
		return mybatisDataAccessDAO.delete("homenet.shop.display.service.DisplayService.deleteDisplayBase", paramVO);
	}
	
	/**
	 * 목적 : 전시 이미지 정보   조회
	 * 매개변수 : DispShopBaseVO paramVO 조회조건
	 * 반환값 : List<DispImgInfoVO> 전시 이미지 정보 리스트
	 * 개정이력 : 없음
	 */
	public List<DispImgInfoVO> selectDisplayImgInfoList(DispShopBaseVO paramVO) throws Exception {
		return (List<DispImgInfoVO>) mybatisDataAccessDAO.select("homenet.shop.display.service.DisplayService.insertDisplayImgInfo", paramVO);
	}
	
	/*
	 * 전시 이미지 등록
	 * @param  : DispShopBaseVO 등록 정보
	 * 
	 * @return : Integer 전시 이미지 등록 결과
	 */
	public Integer insertDisplayImgInfo(DispImgInfoVO paramVO) throws Exception {
		return (Integer) mybatisDataAccessDAO.insert("homenet.shop.display.service.DisplayService.insertDisplayImgInfo", paramVO);
	}
	
	/*
	 * 전시 이미지 삭제
	 * @param  : DispShopBaseVO 삭제 정보
	 * 
	 * @return : Integer 전시 이미지 삭제 결과
	 */
	public Integer deleteDisplayImgInfo(DispImgInfoVO paramVO) throws Exception {
		return mybatisDataAccessDAO.delete("homenet.shop.display.service.DisplayService.deleteDisplayImgInfo", paramVO);
	}

}

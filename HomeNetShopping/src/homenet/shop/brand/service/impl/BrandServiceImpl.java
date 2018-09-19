package homenet.shop.brand.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hsqldb.lib.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import homenet.shop.brand.service.BrandService;
import homenet.shop.brand.service.BrndBaseVO;
import homenet.shop.brand.service.BrndImgInfoVO;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import prjframework.common.util.SessionUtil;
import prjframework.common.util.WebUtil;

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
	
	/*
	 * 브랜드번호 생성
	 * @param  : BrndBaseVO 등록 정보
	 * 
	 * @return : Integer 브랜드번호
	 */
	public Integer selectBrandNoPk(BrndBaseVO paramVO) throws Exception {
		return (Integer) mybatisDataAccessDAO.select("homenet.shop.brand.service.BrandService.selectBrandNoPk", paramVO);
	}
	
	/*
	 * 브랜드 저장
	 * @param  : BrndBaseVO 등록 정보
	 * 
	 * @return : Integer 브랜드 저장 결과
	 */
	public Integer saveBrand(BrndBaseVO paramVO) throws Exception {
		int result	= 0;
		
		// 브랜드번호 생성
		Integer brndNo	= selectBrandNoPk(paramVO);
		paramVO.setBrndNo(brndNo);
		
		if ( "I".equals(paramVO.getCmd()) ) {
			result += insertBrandBase(paramVO);
		} else {
			result += updateBrandBase(paramVO);
		}
		
		result += saveBrandImgInfo(paramVO);
		
		return result;
	}
	
	/*
	 * 브랜드 삭제
	 * @param  : BrndBaseVO 삭제 정보
	 * 
	 * @return : Integer 브랜드 삭제 결과
	 */
	public Integer deleteBrand(BrndBaseVO paramVO) throws Exception {
		int result	= 0;
		
		// 브랜드 이미지 삭제
		result += deleteBrandImgInfo(paramVO);
		// 브랜드 삭제
		result += deleteBrandBase(paramVO);
		
		return result;
	}
	
	/*
	 * 브랜드 등록
	 * @param  : BrndBaseVO 등록 정보
	 * 
	 * @return : Integer 브랜드 등록 결과
	 */
	public Integer insertBrandBase(BrndBaseVO paramVO) throws Exception {
		return (Integer) mybatisDataAccessDAO.insert("homenet.shop.brand.service.BrandService.insertBrandBase", paramVO);
	}
	
	/*
	 * 브랜드 수정
	 * @param  : BrndBaseVO 수정 정보
	 * 
	 * @return : Integer 브랜드 수정 결과
	 */
	public Integer updateBrandBase(BrndBaseVO paramVO) throws Exception {
		return mybatisDataAccessDAO.update("homenet.shop.brand.service.BrandService.updateBrandBase", paramVO);
	}
	
	/*
	 * 브랜드 삭제
	 * @param  : BrndBaseVO 삭제 정보
	 * 
	 * @return : Integer 브랜드 삭제 결과
	 */
	public Integer deleteBrandBase(BrndBaseVO paramVO) throws Exception {
		return mybatisDataAccessDAO.delete("homenet.shop.brand.service.BrandService.deleteBrandBase", paramVO);
	}
	
	/*
	 * 브랜드 이미지 저장
	 * @param  : BrndBaseVO 등록 정보
	 * 
	 * @return : Integer 브랜드 이미지 저장 결과
	 */
	public Integer saveBrandImgInfo(BrndBaseVO paramVO) throws Exception {
		int result = 0;
		
		String[] arrFileInfo = null;
		String[] arrFileSplitInfo = null;
		
		BrndImgInfoVO brndImgInfoVO = null;
		
		if(paramVO != null) {
			arrFileInfo = paramVO.getAddFileList();
			
			if(arrFileInfo != null && arrFileInfo.length > 0) {
				
				if ( "I".equals(paramVO.getCmd()) ) {
					for(int i = 0; i < arrFileInfo.length; i++) {
						brndImgInfoVO = new BrndImgInfoVO();
						
						arrFileSplitInfo = StringUtil.split(arrFileInfo[i], "|");
						
						brndImgInfoVO.setBrndNo(paramVO.getBrndNo());
						brndImgInfoVO.setImgNm(arrFileSplitInfo[0]);
						brndImgInfoVO.setImgFileNm(arrFileSplitInfo[1]);
						brndImgInfoVO.setImgPathNm(arrFileSplitInfo[4]);
						
						brndImgInfoVO.setWrtPnNo(paramVO.getWrtPnNo());
						brndImgInfoVO.setUpdtPnNo(paramVO.getUpdtPnNo());
						brndImgInfoVO.setWrtPnIp(paramVO.getWrtPnIp());
						brndImgInfoVO.setUpdtPnIp(paramVO.getUpdtPnIp());
						
						result += insertBrandImgInfo(brndImgInfoVO);
					}
				} else {
					
					result += deleteBrandImgInfo(paramVO);
					
					for(int i = 0; i < arrFileInfo.length; i++) {
						brndImgInfoVO = new BrndImgInfoVO();
						
						arrFileSplitInfo = StringUtil.split(arrFileInfo[i], "|");
						
						brndImgInfoVO.setBrndNo(paramVO.getBrndNo());
						brndImgInfoVO.setImgNm(arrFileSplitInfo[0]);
						brndImgInfoVO.setImgFileNm(arrFileSplitInfo[1]);
						brndImgInfoVO.setImgPathNm(arrFileSplitInfo[4]);
						
						brndImgInfoVO.setWrtPnNo(paramVO.getWrtPnNo());
						brndImgInfoVO.setUpdtPnNo(paramVO.getUpdtPnNo());
						brndImgInfoVO.setWrtPnIp(paramVO.getWrtPnIp());
						brndImgInfoVO.setUpdtPnIp(paramVO.getUpdtPnIp());
						
						result += insertBrandImgInfo(brndImgInfoVO);
					}
				}
				
			}
		}
		
		return result;
	}
	
	/*
	 * 브랜드 이미지 등록
	 * @param  : BrndBaseVO 등록 정보
	 * 
	 * @return : Integer 브랜드 등록 결과
	 */
	public Integer insertBrandImgInfo(BrndImgInfoVO paramVO) throws Exception {
		return (Integer) mybatisDataAccessDAO.insert("homenet.shop.brand.service.BrandService.insertBrandImgInfo", paramVO);
	}
	
	/*
	 * 브랜드 이미지 수정
	 * @param  : BrndBaseVO 수정 정보
	 * 
	 * @return : Integer 브랜드 수정 결과
	 */
	public Integer updateBrandImgInfo(BrndImgInfoVO paramVO) throws Exception {
		return mybatisDataAccessDAO.update("homenet.shop.brand.service.BrandService.updateBrandImgInfo", paramVO);
	}
	
	/*
	 * 브랜드 이미지 삭제
	 * @param  : BrndBaseVO 삭제 정보
	 * 
	 * @return : Integer 브랜드 삭제 결과
	 */
	public Integer deleteBrandImgInfo(BrndBaseVO paramVO) throws Exception {
		return mybatisDataAccessDAO.delete("homenet.shop.brand.service.BrandService.deleteBrandImgInfo", paramVO);
	}

}

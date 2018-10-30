package homenet.shop.display.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hsqldb.lib.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import homenet.shop.brand.service.BrandService;
import homenet.shop.brand.service.BrndImgInfoVO;
import homenet.shop.brand.service.impl.BrandServiceImpl;
import homenet.shop.display.service.DispImgInfoVO;
import homenet.shop.display.service.DispShopBaseVO;
import homenet.shop.display.service.DisplayService;
import homenet.shop.display.service.DpmlBaseVO;
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
	 * 목적 : 전시몰 정보   조회
	 * 매개변수 : DpmlBaseVO paramVO 조회조건
	 * 반환값 : List<DpmlBaseVO> 전시몰 정보 리스트
	 * 개정이력 : 없음
	 */
	@Override
	public List<DpmlBaseVO> selectDisplayMallList(DpmlBaseVO paramVO) throws Exception {
		return (List<DpmlBaseVO>) mybatisDataAccessDAO.list("homenet.shop.display.service.DisplayService.selectDisplayMallList", paramVO);
	}
	
	/**
	 * 목적 : 전시매장 정보   조회
	 * 매개변수 : DispShopBaseVO paramVO 조회조건
	 * 반환값 : List<DispShopBaseVO> 전시매장 정보 리스트
	 * 개정이력 : 없음
	 */
	@Override
	public List<DispShopBaseVO> selectDisplayList(DispShopBaseVO paramVO) throws Exception {
		return (List<DispShopBaseVO>) mybatisDataAccessDAO.list("homenet.shop.display.service.DisplayService.selectDisplayList", paramVO);
	}
	
	/**
	 * 목적 : 전시매장 Tree 정보   조회
	 * 매개변수 : DispShopBaseVO paramVO 조회조건
	 * 반환값 : List<DispShopBaseVO> 전시매장 정보 리스트
	 * 개정이력 : 없음
	 */
	@Override
	public List<DispShopBaseVO> selectDisplayTreeList(DispShopBaseVO paramVO) throws Exception {
		return (List<DispShopBaseVO>) mybatisDataAccessDAO.list("homenet.shop.display.service.DisplayService.selectDisplayTreeList", paramVO);
	}
	
	/**
	 * 목적 : 전시매장 정보   상세조회
	 * 매개변수 : DispShopBaseVO paramVO 조회조건
	 * 반환값 : List<DispShopBaseVO> 전시매장 정보 리스트
	 * 개정이력 : 없음
	 */
	@Override
	public DispShopBaseVO selectDisplayInfo(DispShopBaseVO paramVO) throws Exception {
		return (DispShopBaseVO) mybatisDataAccessDAO.select("homenet.shop.display.service.DisplayService.selectDisplayInfo", paramVO);
	}
	
	/*
	 * 전시매장 생성
	 * @param  : DispShopBaseVO 등록 정보
	 * 
	 * @return : Integer 전시 번호
	 */
	@Override
	public Integer selectDisplayPrimaryKeySequence(DispShopBaseVO paramVO) throws Exception {
		return (Integer) mybatisDataAccessDAO.select("homenet.shop.display.service.DisplayService.selectDisplayPrimaryKeySequence", paramVO);
	}
	
	/*
	 * 전시매장 저장
	 * @param  : DispShopBaseVO 등록 정보
	 * 
	 * @return : Integer 전시매장 저장 결과
	 */
	@Override
	public Integer saveDisplay(DispShopBaseVO paramVO) throws Exception {
		
		int result	= 0;
		
		if ( "I".equals(paramVO.getCmd()) ) {
			// 전시번호 생성
			Integer dispNo	= selectDisplayPrimaryKeySequence(paramVO);
			paramVO.setDispNo(dispNo);
			
			if ( paramVO.getDpthNo() == 1 ) {
				paramVO.setDispLrgNo(dispNo);
			} else if ( paramVO.getDpthNo() == 2 ) {
				paramVO.setDispMidNo(dispNo);
			} else if ( paramVO.getDpthNo() == 3 ) {
				paramVO.setDispSmlNo(dispNo);		
			} else if ( paramVO.getDpthNo() == 4 ) {
				paramVO.setDispThnNo(dispNo);
			}
			
			result += insertDisplayBase(paramVO);
		} else {
			result += updateDisplayBase(paramVO);
		}
		
		result += saveDisplayImgInfo(paramVO);
		
		return result;
	}
	
	/*
	 * 전시매장 등록
	 * @param  : DispShopBaseVO 등록 정보
	 * 
	 * @return : Integer 전시매장 등록 결과
	 */
	@Override
	public Integer insertDisplayBase(DispShopBaseVO paramVO) throws Exception {
		return (Integer) mybatisDataAccessDAO.insert("homenet.shop.display.service.DisplayService.insertDisplayBase", paramVO);
	}
	
	/*
	 * 전시매장 수정
	 * @param  : DispShopBaseVO 수정 정보
	 * 
	 * @return : Integer 전시매장 수정 결과
	 */
	@Override
	public Integer updateDisplayBase(DispShopBaseVO paramVO) throws Exception {
		return mybatisDataAccessDAO.update("homenet.shop.display.service.DisplayService.updateDisplayBase", paramVO);
	}
	
	/*
	 * 전시매장 삭제
	 * @param  : DispShopBaseVO 삭제 정보
	 * 
	 * @return : Integer 전시매장 삭제 결과
	 */
	@Override
	public Integer deleteDisplay(DispShopBaseVO paramVO) throws Exception {
		int result = 0;
		
		// 전시 이미지 삭제
		result += deleteDisplayImgInfo(paramVO);
		
		// 전시매장 삭제
		result += deleteDisplayBase(paramVO);
		
		return result;
	}
	
	/*
	 * 전시매장 삭제
	 * @param  : DispShopBaseVO 삭제 정보
	 * 
	 * @return : Integer 전시매장 삭제 결과
	 */
	@Override
	public Integer deleteDisplayBase(DispShopBaseVO paramVO) throws Exception {
		return mybatisDataAccessDAO.delete("homenet.shop.display.service.DisplayService.deleteDisplayBase", paramVO);
	}
	
	/**
	 * 목적 : 전시 이미지 정보   조회
	 * 매개변수 : DispImgInfoVO paramVO 조회조건
	 * 반환값 : List<DispImgInfoVO> 전시 이미지 정보 리스트
	 * 개정이력 : 없음
	 */
	@Override
	public List<DispImgInfoVO> selectDisplayImgInfoList(DispImgInfoVO paramVO) throws Exception {
		return (List<DispImgInfoVO>) mybatisDataAccessDAO.select("homenet.shop.display.service.DisplayService.selectDisplayImgInfoList", paramVO);
	}
	
	/*
	 * 전시 이미지 저장
	 * @param  : DispShopBaseVO 등록 정보
	 * 
	 * @return : Integer 전시 이미지 저장 결과
	 */
	@Override
	public Integer saveDisplayImgInfo(DispShopBaseVO paramVO) throws Exception {
		int result = 0;
		
		String[] arrTitleImgInfo 		= null;
		String[] arrTitleImgSplitInfo 	= null;
		
		String[] arrGnbImgInfo 			= null;
		String[] arrGnbImgSplitInfo 	= null;
		
		String[] arrHeaderImgInfo 		= null;
		String[] arrHeaderImgSplitInfo 	= null;
		
		DispImgInfoVO dispImgInfoVO 	= null;
		
		if(paramVO != null) {
			arrTitleImgInfo 	= paramVO.getAddTitleImgList();
			arrGnbImgInfo		= paramVO.getAddGnbImgList();
			arrHeaderImgInfo	= paramVO.getAddHeaderImgList();
			
			if(arrTitleImgInfo != null && arrTitleImgInfo.length > 0) {
				
				if ( "I".equals(paramVO.getCmd()) ) {
					for(int i = 0; i < arrTitleImgInfo.length; i++) {
						dispImgInfoVO = new DispImgInfoVO();
						
						arrTitleImgSplitInfo = StringUtil.split(arrTitleImgInfo[i], "|");
						
						dispImgInfoVO.setDispNo(paramVO.getDispNo());
						dispImgInfoVO.setDispImgTpCd("04");		// 04 전시매장대표
						dispImgInfoVO.setDispShopSctCd("01");	// 01 전시매장카테고리
						dispImgInfoVO.setBnrImgFileNm(arrTitleImgInfo[1]);
						dispImgInfoVO.setBnrImgPathNm(arrTitleImgInfo[4]);
						
						dispImgInfoVO.setWrtPnNo(paramVO.getWrtPnNo());
						dispImgInfoVO.setUpdtPnNo(paramVO.getUpdtPnNo());
						dispImgInfoVO.setWrtPnIp(paramVO.getWrtPnIp());
						dispImgInfoVO.setUpdtPnIp(paramVO.getUpdtPnIp());
						
						result += insertDisplayImgInfo(dispImgInfoVO);
					}
					
					for(int i = 0; i < arrGnbImgInfo.length; i++) {
						dispImgInfoVO = new DispImgInfoVO();
						
						arrGnbImgSplitInfo = StringUtil.split(arrGnbImgInfo[i], "|");
						
						dispImgInfoVO.setDispNo(paramVO.getDispNo());
						dispImgInfoVO.setDispImgTpCd("05");		// 05 전시매장 GNB
						dispImgInfoVO.setDispShopSctCd("01");	// 01 전시매장카테고리
						dispImgInfoVO.setBnrImgFileNm(arrGnbImgSplitInfo[1]);
						dispImgInfoVO.setBnrImgPathNm(arrGnbImgSplitInfo[4]);
						
						dispImgInfoVO.setWrtPnNo(paramVO.getWrtPnNo());
						dispImgInfoVO.setUpdtPnNo(paramVO.getUpdtPnNo());
						dispImgInfoVO.setWrtPnIp(paramVO.getWrtPnIp());
						dispImgInfoVO.setUpdtPnIp(paramVO.getUpdtPnIp());
						
						result += insertDisplayImgInfo(dispImgInfoVO);
					}
					
					for(int i = 0; i < arrHeaderImgInfo.length; i++) {
						dispImgInfoVO = new DispImgInfoVO();
						
						arrHeaderImgSplitInfo = StringUtil.split(arrHeaderImgInfo[i], "|");
						
						dispImgInfoVO.setDispNo(paramVO.getDispNo());
						dispImgInfoVO.setDispImgTpCd("03");		// 03 전시매장명
						dispImgInfoVO.setDispShopSctCd("01");	// 01 전시매장카테고리
						dispImgInfoVO.setBnrImgFileNm(arrHeaderImgSplitInfo[1]);
						dispImgInfoVO.setBnrImgPathNm(arrHeaderImgSplitInfo[4]);
						
						dispImgInfoVO.setWrtPnNo(paramVO.getWrtPnNo());
						dispImgInfoVO.setUpdtPnNo(paramVO.getUpdtPnNo());
						dispImgInfoVO.setWrtPnIp(paramVO.getWrtPnIp());
						dispImgInfoVO.setUpdtPnIp(paramVO.getUpdtPnIp());
						
						result += insertDisplayImgInfo(dispImgInfoVO);
					}
					
				} else {
					
					result += deleteDisplayImgInfo(paramVO);
					
					for(int i = 0; i < arrTitleImgInfo.length; i++) {
						dispImgInfoVO = new DispImgInfoVO();
						
						arrTitleImgSplitInfo = StringUtil.split(arrTitleImgInfo[i], "|");
						
						dispImgInfoVO.setDispNo(paramVO.getDispNo());
						dispImgInfoVO.setDispImgTpCd("05");		// 05 전시매장대표
						dispImgInfoVO.setDispShopSctCd("01");	// 01 전시매장카테고리
						dispImgInfoVO.setBnrImgFileNm(arrTitleImgInfo[1]);
						dispImgInfoVO.setBnrImgPathNm(arrTitleImgInfo[4]);
						
						dispImgInfoVO.setWrtPnNo(paramVO.getWrtPnNo());
						dispImgInfoVO.setUpdtPnNo(paramVO.getUpdtPnNo());
						dispImgInfoVO.setWrtPnIp(paramVO.getWrtPnIp());
						dispImgInfoVO.setUpdtPnIp(paramVO.getUpdtPnIp());
						
						result += insertDisplayImgInfo(dispImgInfoVO);
					}
					
					for(int i = 0; i < arrGnbImgInfo.length; i++) {
						dispImgInfoVO = new DispImgInfoVO();
						
						arrGnbImgSplitInfo = StringUtil.split(arrGnbImgInfo[i], "|");
						
						dispImgInfoVO.setDispNo(paramVO.getDispNo());
						dispImgInfoVO.setDispImgTpCd("03");		// 03 전시매장 GNB
						dispImgInfoVO.setDispShopSctCd("01");	// 01 전시매장카테고리
						dispImgInfoVO.setBnrImgFileNm(arrGnbImgSplitInfo[1]);
						dispImgInfoVO.setBnrImgPathNm(arrGnbImgSplitInfo[4]);
						
						dispImgInfoVO.setWrtPnNo(paramVO.getWrtPnNo());
						dispImgInfoVO.setUpdtPnNo(paramVO.getUpdtPnNo());
						dispImgInfoVO.setWrtPnIp(paramVO.getWrtPnIp());
						dispImgInfoVO.setUpdtPnIp(paramVO.getUpdtPnIp());
						
						result += insertDisplayImgInfo(dispImgInfoVO);
					}
					
					for(int i = 0; i < arrHeaderImgInfo.length; i++) {
						dispImgInfoVO = new DispImgInfoVO();
						
						arrHeaderImgSplitInfo = StringUtil.split(arrHeaderImgInfo[i], "|");
						
						dispImgInfoVO.setDispNo(paramVO.getDispNo());
						dispImgInfoVO.setDispImgTpCd("04");		// 04 전시매장타이틀
						dispImgInfoVO.setDispShopSctCd("01");	// 01 전시매장카테고리
						dispImgInfoVO.setBnrImgFileNm(arrHeaderImgSplitInfo[1]);
						dispImgInfoVO.setBnrImgPathNm(arrHeaderImgSplitInfo[4]);
						
						dispImgInfoVO.setWrtPnNo(paramVO.getWrtPnNo());
						dispImgInfoVO.setUpdtPnNo(paramVO.getUpdtPnNo());
						dispImgInfoVO.setWrtPnIp(paramVO.getWrtPnIp());
						dispImgInfoVO.setUpdtPnIp(paramVO.getUpdtPnIp());
						
						result += insertDisplayImgInfo(dispImgInfoVO);
					}
				}
				
			}
		}
		
		return result;
	}
	
	/*
	 * 전시 이미지 등록
	 * @param  : DispShopBaseVO 등록 정보
	 * 
	 * @return : Integer 전시 이미지 등록 결과
	 */
	@Override
	public Integer insertDisplayImgInfo(DispImgInfoVO paramVO) throws Exception {
		return (Integer) mybatisDataAccessDAO.insert("homenet.shop.display.service.DisplayService.insertDisplayImgInfo", paramVO);
	}
	
	/*
	 * 전시 이미지 삭제
	 * @param  : DispShopBaseVO 삭제 정보
	 * 
	 * @return : Integer 전시 이미지 삭제 결과
	 */
	@Override
	public Integer deleteDisplayImgInfo(DispShopBaseVO paramVO) throws Exception {
		return mybatisDataAccessDAO.delete("homenet.shop.display.service.DisplayService.deleteDisplayImgInfo", paramVO);
	}

}

package homenet.shop.brd.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import homenet.shop.brd.common.service.UserBCommonService;
import homenet.shop.brd.common.service.UserBoardVO;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import prjframework.common.file.service.PrjframeworkCmmnFileService;
import prjframework.common.file.service.PrjframeworkCmmnFileVO;

/**
 * -----------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------------
 * 소속 : 고려대학교 경력개발센터
 * 수정일 : 2018.03.15
 * 수정자 : 엄재덕
 * 수정내용 : 최초생성
 * ------------------------------------------------------------------------
 */
@Service("userBCommonService")
public class UserBCommonServiceImpl implements UserBCommonService {

	@Resource(name = "configPropService")
	protected EgovPropertyService configPropService;
	
	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
	
	
	@Resource(name = "prjframeworkCmmnFileService")
	private PrjframeworkCmmnFileService prjframeworkCmmnFileService;


	/**
	 * 목적 : 공통게시판 옵션 조회
	 * 매개변수 : UBDBoardoutVO
	 * 반환값 : UBDBoardoutVO
	 * 개정이력 : 없음
	 */
	public UserBoardVO retrieveBoardOtpInfo(UserBoardVO vo) throws Exception {
		return (UserBoardVO) mybatisDataAccessDAO.select("homenet.shop.brd.common.service.UserBCommonService.selectUBDBoardoutService_S06", vo);
	}
	
	/**
	 * 목적 : 공통게시판 조회
	 * 매개변수 : UBDBoardoutVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	//@Override
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveList(UserBoardVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.brd.common.service.UserBCommonService.selectUBDBoardoutService_S01", vo);
	}

	/**
	 * 목적 : 공통게시판 조회 카운트
	 * 매개변수 : UBDBoardoutVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	//@Override
	public int retrieveCount(UserBoardVO vo) throws Exception {
		return (Integer) mybatisDataAccessDAO.select("homenet.shop.brd.common.service.UserBCommonService.selectUBDBoardoutService_S02", vo);
	}

	/**
	 * 목적 : 공통게시판 상단공지 조회
	 * 매개변수 : UBDBoardoutVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	//@Override
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveListTop(UserBoardVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.brd.common.service.UserBCommonService.selectUBDBoardoutService_S09", vo);
	}

	/**
	 * 목적 : 공통게시판 코드 조회
	 * 매개변수 : UBDBoardoutVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	//@Override
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveListCode(UserBoardVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.brd.common.service.UserBCommonService.selectUBDBoardoutService_S09", vo);
	}	
	/**
	 * 목적 : 공통게시판 조회
	 * 매개변수 : UBDBoardoutVO
	 * 반환값 : UBDBoardoutVO
	 * 개정이력 : 없음
	 */
	public UserBoardVO retrieveInfo(UserBoardVO vo) throws Exception {
		return (UserBoardVO) mybatisDataAccessDAO.select("homenet.shop.brd.common.service.UserBCommonService.selectUBDBoardoutService_S03", vo);
	}

	/**
	 * 목적 : 파일첨부 조회
	 * 매개변수 : UBDBoardoutVO
	 * 반환값 : UBDBoardoutVO
	 * 개정이력 : 없음
	 */
	//@Override
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveFile(UserBoardVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.brd.common.service.UserBCommonService.selectUBDBoardoutService_S05", vo);
	}
	
	/**
	 * 목적 : 파일첨부 조회
	 * 매개변수 : UBDBoardoutVO
	 * 반환값 : UBDBoardoutVO
	 * 개정이력 : 없음
	 */
	//@Override
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveFile2(UserBoardVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.brd.common.service.UserBCommonService.selectUBDBoardoutService_S10", vo);
	}

	/**
	 * 목적 : 공통게시판 상세조회 카운트
	 * 매개변수 : UBDBoardoutVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int modifyCount(UserBoardVO vo) throws Exception {
		int result = 0;

		result += (Integer) mybatisDataAccessDAO.update("homenet.shop.brd.common.service.UserBCommonService.updateUBDBoardoutService_U04", vo);

		return result;
	}
	
	/**
	 * 목적 : 공통게시판 등록 key생성
	 * 매개변수 : UBDBoardoutVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public String bdotSeq() throws Exception {
		return (String) mybatisDataAccessDAO.select("homenet.shop.brd.common.service.UserBCommonService.selectUBDBoardoutService_S04", null);
	}

	/**
	 * 목적 : 공통게시판 등록
	 * 매개변수 : UBDBoardoutVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int registerQanda(UserBoardVO vo) throws Exception {
		int result = 0;
		
		PrjframeworkCmmnFileVO prjframeworkCmmnFileVO = new PrjframeworkCmmnFileVO();
		prjframeworkCmmnFileVO.setAddFileList(vo.getAddFileList());
		prjframeworkCmmnFileVO.setWrtPnIp(vo.getWrtPnIp());
		prjframeworkCmmnFileVO.setWrtPnNo(vo.getWrtPnNo());
		String apndFileNo = prjframeworkCmmnFileService.insertAtchFile(prjframeworkCmmnFileVO);
		vo.setApndFileNo(apndFileNo);
		
		result += (Integer) mybatisDataAccessDAO.insert("homenet.shop.brd.common.service.UserBCommonService.insertUBDBoardoutService_I01", vo);
		result += (Integer) mybatisDataAccessDAO.insert("homenet.shop.brd.common.service.UserBCommonService.insertUBDBoardoutService_I02", vo);
		
		return result;
	}

	/**
	 * 목적 : 사용자정보 조회
	 * 매개변수 : UBDBoardoutVO
	 * 반환값 : UBDBoardoutVO
	 * 개정이력 : 없음
	 */
	public UserBoardVO retrieveBoardUserInfo(UserBoardVO vo) throws Exception {
		return (UserBoardVO) mybatisDataAccessDAO.select("homenet.shop.brd.common.service.UserBCommonService.selectUBDBoardoutService_S08", vo);
	}
	
	/**
	 * 목적 : 공통게시판 수정
	 * 매개변수 : UserBoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int modifyQanda(UserBoardVO vo) throws Exception {
int result = 0;
		
		PrjframeworkCmmnFileVO prjframeworkCmmnFileVO = new PrjframeworkCmmnFileVO();
		prjframeworkCmmnFileVO.setAddFileList(vo.getAddFileList());
		prjframeworkCmmnFileVO.setWrtPnIp(vo.getWrtPnIp());
		prjframeworkCmmnFileVO.setWrtPnNo(vo.getWrtPnNo());
		String apndFileNo = prjframeworkCmmnFileService.insertAtchFile(prjframeworkCmmnFileVO);
		vo.setApndFileNo(apndFileNo);
		
		result += (Integer) mybatisDataAccessDAO.insert("homenet.shop.brd.common.service.UserBCommonService.updateUBDBoardoutService_U01", vo);
		result += (Integer) mybatisDataAccessDAO.insert("homenet.shop.brd.common.service.UserBCommonService.updateUBDBoardoutService_U02", vo);
		
		return result;
	}
	
	/**
	 * 목적 : 게시판 삭제
	 * 매개변수 : UserBoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int delete(UserBoardVO vo) throws Exception {
		int result = 0;

		result += (Integer) mybatisDataAccessDAO.update("homenet.shop.brd.common.service.UserBCommonService.updateUBDBoardoutService_U03", vo);
		result += (Integer) mybatisDataAccessDAO.update("homenet.shop.brd.common.service.UserBCommonService.deleteUBDBoardoutService_D01", vo);
		
		return result;
	}
}

package homenet.shop.brd.common.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import homenet.shop.brd.common.service.BoardVO;
import homenet.shop.brd.common.service.MgntBCommonService;
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
@Service("mgntBCommonService")
public class MgntBCommonServiceImpl implements MgntBCommonService {
	
	@Resource(name = "configPropService")
	protected EgovPropertyService configPropService;
	
	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
	
	
	@Resource(name = "prjframeworkCmmnFileService")
	private PrjframeworkCmmnFileService prjframeworkCmmnFileService;
	
	/**
	 * 목적 : 게시판 조회
	 * 매개변수 : BoardVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	//@Override
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveList(BoardVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.brd.common.service.MgntBCommonService.selectMBOBoardoutService_S01", vo);
	}

	/**
	 * 목적 : 게시판 조회 카운트
	 * 매개변수 : BoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	//@Override
	public int retrieveCount(BoardVO vo) throws Exception {
		return (Integer) mybatisDataAccessDAO.select("homenet.shop.brd.common.service.MgntBCommonService.selectMBOBoardoutService_S02", vo);
	}

	/**
	 * 목적 : 검색어 조회
	 * 매개변수 : BoardVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	//@Override
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveListSrchwrd(BoardVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.brd.common.service.MgntBCommonService.selectMBOBoardoutService_S08", vo);
	}

	/**
	 * 목적 : 공통게시판 옵션 조회
	 * 매개변수 : BoardVO
	 * 반환값 : BoardVO
	 * 개정이력 : 없음
	 */
	public BoardVO retrieveBoardOtpInfo(BoardVO vo) throws Exception {
		return (BoardVO) mybatisDataAccessDAO.select("homenet.shop.brd.common.service.MgntBCommonService.selectMBOBoardoutService_S03", vo);
	}

	/**
	 * 목적 : 게시판 조회
	 * 매개변수 : BoardVO
	 * 반환값 : BoardVO
	 * 개정이력 : 없음
	 */
	public BoardVO retrieveInfo(BoardVO vo) throws Exception {
		return (BoardVO) mybatisDataAccessDAO.select("homenet.shop.brd.common.service.MgntBCommonService.selectMBOBoardoutService_S05", vo);
	}

	/**
	 * 목적 : 파일첨부 조회
	 * 매개변수 : BoardVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	//@Override
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveFile(BoardVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.brd.common.service.MgntBCommonService.selectMBOBoardoutService_S06", vo);
	}

	/**
	 * 목적 : 썸네일 조회
	 * 매개변수 : BoardVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	//@Override
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveFileThumb(BoardVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.brd.common.service.MgntBCommonService.selectMBOBoardoutService_S07", vo);
	}

	/**
	 * 목적 : 답변자 첨부파일 조회
	 * 매개변수 : BoardVO
	 * 반환값 : List<EgovMap>
	 * 개정이력 : 없음
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveFileAns(BoardVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.brd.common.service.MgntBCommonService.selectMBOBoardoutService_S09", vo);
	}
	
	/**
	 * 목적 : 게시판 등록 key생성
	 * 매개변수 : BoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public String bdotSeq() throws Exception {
		return (String) mybatisDataAccessDAO.select("homenet.shop.brd.common.service.MgntBCommonService.selectMBOBoardoutService_S04", null);
	}

	/**
	 * 목적 : 게시판 등록
	 * 매개변수 : BoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int register(BoardVO vo) throws Exception {
		int result = 0;

		// 첨부파일 등록
		PrjframeworkCmmnFileVO prjframeworkCmmnFileVO = new PrjframeworkCmmnFileVO();
		prjframeworkCmmnFileVO.setAddFileList(vo.getAddFileList());
		prjframeworkCmmnFileVO.setWrtPnNo(vo.getWrtPnNo());
		prjframeworkCmmnFileVO.setWrtPnIp(vo.getWrtPnIp());
		String apndFileNo = prjframeworkCmmnFileService.insertAtchFile(prjframeworkCmmnFileVO);
		vo.setApndFileNo(apndFileNo);
		
		// 썸네일 첨부파일 등록
		PrjframeworkCmmnFileVO prjframeworkCmmnFileVO1 = new PrjframeworkCmmnFileVO();
		prjframeworkCmmnFileVO1.setAddFileList(vo.getAddFileList1());
		prjframeworkCmmnFileVO1.setWrtPnNo(vo.getWrtPnNo());
		prjframeworkCmmnFileVO1.setWrtPnIp(vo.getWrtPnIp());
		String thumbApndFileNo = prjframeworkCmmnFileService.insertAtchFile(prjframeworkCmmnFileVO);
		vo.setThumbApndFileNo(thumbApndFileNo);

		if (vo.getAnsUseYn() != null && vo.getAnsUseYn().equals("Y")) {
			vo.setBdotDc("02");
		} else {
			vo.setBdotDc("01");
		}

		result += (Integer) mybatisDataAccessDAO.insert("homenet.shop.brd.common.service.MgntBCommonService.insertMBOBoardoutService_I01", vo);
		result += (Integer) mybatisDataAccessDAO.insert("homenet.shop.brd.common.service.MgntBCommonService.insertMBOBoardoutService_I02", vo);
		
		return result;
	}

	/**
	 * 목적 : 게시판 수정
	 * 매개변수 : BoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int modify(BoardVO vo, HttpServletRequest request) throws Exception {
		int result = 0;

		// 썸네일 첨부파일 등록1
		PrjframeworkCmmnFileVO prjframeworkCmmnFileVO1 = new PrjframeworkCmmnFileVO();
		prjframeworkCmmnFileVO1.setAddFileList(vo.getAddFileList1());
		prjframeworkCmmnFileVO1.setAtchmnflNo(vo.getThumbApndFileNo());	
		prjframeworkCmmnFileVO1.setWrtPnNo(vo.getWrtPnNo());
		prjframeworkCmmnFileVO1.setWrtPnIp(vo.getWrtPnIp());
		String thumbApndFileNo = prjframeworkCmmnFileService.insertAtchFile(prjframeworkCmmnFileVO1);
		vo.setThumbApndFileNo(thumbApndFileNo);
		
		if (vo.getAnsUseYn() != null && vo.getAnsUseYn().equals("Y")) {
			vo.setBdotDc("02");
			
			// 첨부파일 등록
			PrjframeworkCmmnFileVO prjframeworkCmmnFileVO2 = new PrjframeworkCmmnFileVO();
			prjframeworkCmmnFileVO2.setAddFileList(vo.getAddFileList2());
			prjframeworkCmmnFileVO2.setAtchmnflNo(vo.getAnsApndFileNo());
			prjframeworkCmmnFileVO2.setWrtPnNo(vo.getWrtPnNo());
			prjframeworkCmmnFileVO2.setWrtPnIp(vo.getWrtPnIp());
			String ansApndFileNo = prjframeworkCmmnFileService.insertAtchFile(prjframeworkCmmnFileVO2);
			vo.setAnsApndFileNo(ansApndFileNo);
			
		} else {
			vo.setBdotDc("01");
			
			// 첨부파일 등록
			PrjframeworkCmmnFileVO prjframeworkCmmnFileVO = new PrjframeworkCmmnFileVO();
			prjframeworkCmmnFileVO.setAddFileList(vo.getAddFileList());
			prjframeworkCmmnFileVO.setAtchmnflNo(vo.getApndFileNo());
			prjframeworkCmmnFileVO.setWrtPnNo(vo.getWrtPnNo());
			prjframeworkCmmnFileVO.setWrtPnIp(vo.getWrtPnIp());
			String apndFileNo = prjframeworkCmmnFileService.insertAtchFile(prjframeworkCmmnFileVO);
			vo.setApndFileNo(apndFileNo);
			
		}

		result += (Integer) mybatisDataAccessDAO.update("homenet.shop.brd.common.service.MgntBCommonService.updateMBOBoardoutService_U01", vo);

		if (vo.getAnsUseYn() != null && vo.getAnsUseYn().equals("Y")) {
			result += (Integer) mybatisDataAccessDAO.delete("homenet.shop.brd.common.service.MgntBCommonService.deleteMBOBoardoutService_D01", vo);
			result += (Integer) mybatisDataAccessDAO.insert("homenet.shop.brd.common.service.MgntBCommonService.insertMBOBoardoutService_I02", vo);
			
			
			/*MailVO mailVO = new MailVO();
			CSSSmsVO cSSSmsVO = new CSSSmsVO();
			Map<String, Object> textParams = new HashMap<String, Object>();
			
			if(vo.getAnsYn() != null && vo.getAnsYn().equals("S")) { //문의하기 처리중일 시에만 답변 sms, 메일을 발송한다.
    			if (vo.getAnsSendCd() != null) {
    				if ("2".equals(vo.getAnsSendCd())) {	// SMS
    			        
    					//SMS발송
    					cSSSmsVO.setSmsTitNm("고객센터 문의 답변");
    					cSSSmsVO.setSmsCn("[U-KNOU캠퍼스] 고객님께서 문의하신 답변이 등록되었습니다.");
    					
    					cSSSmsVO.setFwpName(globalsProperties.getProperty("SMS.COMMON.NAME"));			//발송자이름		사용자가 발송하는 경우  KNOUTray_Util.getCn(request) 를 넣어준다.
    					cSSSmsVO.setFwpUserTpId(globalsProperties.getProperty("SMS.COMMON.RUID"));		//발송자ID		시스템발송만 프로퍼티에서 가져온다. 사용자가 발송하는 경우  KNOUTray_Util.getKnuserid(request) 를 넣어준다.
    					cSSSmsVO.setSendUserNo(vo.getUpdtPnNo());									//사용자번호		시스템인경우 0
    					cSSSmsVO.setWrtPnNo(vo.getUpdtPnNo());									//사용자번호		시스템인경우 0	
    					cSSSmsVO.setWrtPnIp(request.getRemoteAddr());									//작성자IP		시스템인경우 0
    					
    					cSSSmsVO.setTargetList(new String[]{vo.getWrtPnNm()+":"+vo.getWrtPnTlno()+":::::"});    					
    					cSSSmsService.insertSmsSend(cSSSmsVO, request);
    					
    				} else if ("3".equals(vo.getAnsSendCd())) {	// 메일

    					//메일발송
    					textParams.put("title", vo.getTitNm());
    					textParams.put("date", vo.getWrtDttm());
    					textParams.put("bdotCn", vo.getBdotCn());

    					mailVO.setEmailTempCd("04");						// 이메일템플릿코드
    					mailVO.setMailSubject("[U-KNOU캠퍼스] 고객님께서 문의하신 답변이 등록되었습니다.");				// 메일제목
    					mailVO.setTextParams(textParams);					// 메일내용
    					mailVO.setMailFrom(globalsProperties.getProperty("MAIL.SMTP.ID"));				// 보내는사람
    					mailVO.setMailTo(vo.getWrtPnEmail());				// 받는사람
    					
    					mailVO.setFwpUserNo(vo.getUpdtPnNo());		//발송자사용자번호
    					mailVO.setRvpnUserNo(vo.getWrtEmailUserNo());		//수신자사용자번호
    					mailVO.setWrtPnIp(request.getRemoteAddr());		//작성자IP
    					
    					cSMMailService.insertMailSend(mailVO);
    					
    				} else if ("4".equals(vo.getAnsSendCd())) {	// SMS+메일

    					//SMS발송
    					cSSSmsVO.setSmsTitNm("고객센터 문의 답변");
    					cSSSmsVO.setSmsCn("[U-KNOU캠퍼스] 고객님께서 문의하신 답변이 등록되었습니다.");
    					
    					cSSSmsVO.setFwpName(globalsProperties.getProperty("SMS.COMMON.NAME"));			//발송자이름		사용자가 발송하는 경우  KNOUTray_Util.getCn(request) 를 넣어준다.
    					cSSSmsVO.setFwpUserTpId(globalsProperties.getProperty("SMS.COMMON.RUID"));		//발송자ID		시스템발송만 프로퍼티에서 가져온다. 사용자가 발송하는 경우  KNOUTray_Util.getKnuserid(request) 를 넣어준다.
    					cSSSmsVO.setSendUserNo(vo.getUpdtPnNo());									//사용자번호		시스템인경우 0
    					cSSSmsVO.setWrtPnNo(vo.getUpdtPnNo());									//사용자번호		시스템인경우 0	
    					cSSSmsVO.setWrtPnIp(request.getRemoteAddr());									//작성자IP		시스템인경우 0
    					
    					cSSSmsVO.setTargetList(new String[]{vo.getWrtPnNm()+":"+vo.getWrtPnTlno()+":::::"});    					
    					cSSSmsService.insertSmsSend(cSSSmsVO, request);
  
    					//메일발송
    					textParams.put("title", vo.getTitNm());
    					textParams.put("date", vo.getWrtDttm());
    					textParams.put("bdotCn", vo.getBdotCn());

    					mailVO.setEmailTempCd("04");						// 이메일템플릿코드
    					mailVO.setMailSubject("[U-KNOU캠퍼스] 고객님께서 문의하신 답변이 등록되었습니다.");				// 메일제목
    					mailVO.setTextParams(textParams);					// 메일내용
    					mailVO.setMailFrom(globalsProperties.getProperty("MAIL.SMTP.ID"));				// 보내는사람
    					mailVO.setMailTo(vo.getWrtPnEmail());				// 받는사람
    					
    					mailVO.setFwpUserNo(vo.getUpdtPnNo());		//발송자사용자번호
    					mailVO.setRvpnUserNo(vo.getWrtEmailUserNo());		//수신자사용자번호
    					mailVO.setWrtPnIp(request.getRemoteAddr());		//작성자IP
    					
    					cSMMailService.insertMailSend(mailVO); 	
    					
    				}
    			}
			}*/
			
		} else {
			result += (Integer) mybatisDataAccessDAO.insert("homenet.shop.brd.common.service.MgntBCommonService.updateMBOBoardoutService_U02", vo);
		}

		return result;
	}

	/**
	 * 목적 : 답변구분 등록
	 * 매개변수 : BoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int modifyAns(BoardVO vo) throws Exception {
		int result = 0;

		result += (Integer) mybatisDataAccessDAO.update("homenet.shop.brd.common.service.MgntBCommonService.updateMBOBoardoutService_U04", vo);

		return result;
	}

	/**
	 * 목적 : 게시판 삭제
	 * 매개변수 : BoardVO
	 * 반환값 : int
	 * 개정이력 : 없음
	 */
	public int delete(BoardVO vo) throws Exception {
		int result = 0;

		result += (Integer) mybatisDataAccessDAO.update("homenet.shop.brd.common.service.MgntBCommonService.updateMBOBoardoutService_U03", vo);
		
		return result;
	}

}

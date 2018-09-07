package korea.wrk.brd.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import korea.wrk.brd.popup.service.MgntBPopupService;
import korea.wrk.brd.popup.service.MgntBPopupVO;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import prjframework.common.file.service.PrjframeworkCmmnFileService;
import prjframework.common.file.service.PrjframeworkCmmnFileVO;

@Service("MgntBPopupService")
public class MgntBPopupServiceImpl implements MgntBPopupService {

	@Resource(name = "configPropService")
	protected EgovPropertyService configPropService;
	
	@Resource(name = "mybatisDataAccessDAO") 
	private MybatisDataAccessDAO mybatisDataAccessDAO;

	@Resource(name = "prjframeworkCmmnFileService")
	private PrjframeworkCmmnFileService prjframeworkCmmnFileService;

	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveList(MgntBPopupVO vo) throws Exception {
		return mybatisDataAccessDAO.list("korea.wrk.brd.common.service.MgntBPopupService.selectPopup_S01", vo);
	}

	public int retrieveCount(MgntBPopupVO vo) throws Exception {
		return (Integer) mybatisDataAccessDAO.select("korea.wrk.brd.common.service.MgntBPopupService.selectPopup_S02", vo);
	}

	public MgntBPopupVO retrieveInfo(MgntBPopupVO vo) throws Exception {
		return (MgntBPopupVO) mybatisDataAccessDAO.select("korea.wrk.brd.common.service.MgntBPopupService.selectPopup_S03", vo);
	}

	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveFile(MgntBPopupVO vo) throws Exception {
		return mybatisDataAccessDAO.list("korea.wrk.brd.common.service.MgntBPopupService.selectPopup_S04", vo);
	}

	public int register(MgntBPopupVO vo) throws Exception {
		int result = 0;

		PrjframeworkCmmnFileVO prjctfrmwrkCmmnFileVO = new PrjframeworkCmmnFileVO();
		prjctfrmwrkCmmnFileVO.setAddFileList(vo.getAddFileList());
		prjctfrmwrkCmmnFileVO.setWrtPnIp(vo.getWrtPnIp());
		prjctfrmwrkCmmnFileVO.setWrtPnNo(vo.getWrtPnNo());

		String apndFileNo = prjframeworkCmmnFileService.insertAtchFile(prjctfrmwrkCmmnFileVO);

		vo.setApndFileNo(apndFileNo);

		result += (Integer) mybatisDataAccessDAO.insert("korea.wrk.brd.common.service.MgntBPopupService.insertPopup_I01", vo);

		return result;
	}

	public int modify(MgntBPopupVO vo) throws Exception {
		int result = 0;

		PrjframeworkCmmnFileVO prjctfrmwrkCmmnFileVO = new PrjframeworkCmmnFileVO();
		prjctfrmwrkCmmnFileVO.setAddFileList(vo.getAddFileList());
		prjctfrmwrkCmmnFileVO.setAtchmnflNo(vo.getApndFileNo());
		prjctfrmwrkCmmnFileVO.setWrtPnIp(vo.getWrtPnIp());
		prjctfrmwrkCmmnFileVO.setWrtPnNo(vo.getWrtPnNo());

		String apndFileNo = prjframeworkCmmnFileService.insertAtchFile(prjctfrmwrkCmmnFileVO);

		vo.setApndFileNo(apndFileNo);

		result += (Integer) mybatisDataAccessDAO.insert("korea.wrk.brd.common.service.MgntBPopupService.updatePopup_U01", vo);

		return result;
	}

	public int delete(MgntBPopupVO vo) throws Exception {
		return mybatisDataAccessDAO.delete("korea.wrk.brd.common.service.MgntBPopupService.deletePopup_D01", vo);
	}

	
}

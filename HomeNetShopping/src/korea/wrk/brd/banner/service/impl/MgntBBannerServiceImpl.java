package korea.wrk.brd.banner.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import korea.wrk.brd.banner.service.MgntBBannerService;
import korea.wrk.brd.banner.service.MgntBBannerVO;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import prjframework.common.file.service.PrjframeworkCmmnFileService;
import prjframework.common.file.service.PrjframeworkCmmnFileVO;

@Service("MgntBBannerService")
public class MgntBBannerServiceImpl implements MgntBBannerService {

	@Resource(name = "configPropService")
	protected EgovPropertyService configPropService;
	
	@Resource(name = "mybatisDataAccessDAO") 
	private MybatisDataAccessDAO mybatisDataAccessDAO;

	@Resource(name = "prjframeworkCmmnFileService")
	private PrjframeworkCmmnFileService prjframeworkCmmnFileService;

	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveList(MgntBBannerVO vo) throws Exception {
		return mybatisDataAccessDAO.list("korea.wrk.brd.common.service.MgntBBannerService.selectBanner_S01", vo);
	}

	public int retrieveCount(MgntBBannerVO vo) throws Exception {
		return (Integer) mybatisDataAccessDAO.select("korea.wrk.brd.common.service.MgntBBannerService.selectBanner_S02", vo);
	}

	public MgntBBannerVO retrieveInfo(MgntBBannerVO vo) throws Exception {
		return (MgntBBannerVO) mybatisDataAccessDAO.select("korea.wrk.brd.common.service.MgntBBannerService.selectBanner_S03", vo);
	}

	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveFile(MgntBBannerVO vo) throws Exception {
		return mybatisDataAccessDAO.list("korea.wrk.brd.common.service.MgntBBannerService.selectBanner_S04", vo);
	}

	public int register(MgntBBannerVO vo) throws Exception {
		int result = 0;

		PrjframeworkCmmnFileVO prjctfrmwrkCmmnFileVO = new PrjframeworkCmmnFileVO();
		prjctfrmwrkCmmnFileVO.setAddFileList(vo.getAddFileList());
		prjctfrmwrkCmmnFileVO.setWrtPnIp(vo.getWrtPnIp());
		prjctfrmwrkCmmnFileVO.setWrtPnNo(vo.getWrtPnNo());

		String apndFileNo = prjframeworkCmmnFileService.insertAtchFile(prjctfrmwrkCmmnFileVO);

		vo.setApndFileNo(apndFileNo);

		result += (Integer) mybatisDataAccessDAO.insert("korea.wrk.brd.common.service.MgntBBannerService.insertBanner_I01", vo);

		return result;
	}

	public int modify(MgntBBannerVO vo) throws Exception {
		int result = 0;

		PrjframeworkCmmnFileVO prjctfrmwrkCmmnFileVO = new PrjframeworkCmmnFileVO();
		prjctfrmwrkCmmnFileVO.setAddFileList(vo.getAddFileList());
		prjctfrmwrkCmmnFileVO.setAtchmnflNo(vo.getApndFileNo());
		prjctfrmwrkCmmnFileVO.setWrtPnIp(vo.getWrtPnIp());
		prjctfrmwrkCmmnFileVO.setWrtPnNo(vo.getWrtPnNo());

		String apndFileNo = prjframeworkCmmnFileService.insertAtchFile(prjctfrmwrkCmmnFileVO);

		vo.setApndFileNo(apndFileNo);

		result += (Integer) mybatisDataAccessDAO.insert("korea.wrk.brd.common.service.MgntBBannerService.updateBanner_U01", vo);

		return result;
	}

	public int delete(MgntBBannerVO vo) throws Exception {
		return mybatisDataAccessDAO.delete("korea.wrk.brd.common.service.MgntBBannerService.deleteBanner_D01", vo);
	}

	
}

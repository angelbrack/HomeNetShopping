package homenet.shop.idx.home.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import homenet.shop.idx.home.service.UserMHomeService;
import homenet.shop.idx.home.service.UserMHomeVO;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import prjframework.common.file.service.PrjframeworkCmmnFileService;

@Service("UserMHomeService")
public class UserMHomeServiceImpl implements UserMHomeService {
	
	@Resource(name = "configPropService")
	protected EgovPropertyService configPropService;
	
	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
	
	@Resource(name = "prjframeworkCmmnFileService")
	private PrjframeworkCmmnFileService prjframeworkCmmnFileService;

	//메인배너
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveBannerList(UserMHomeVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.idx.home.service.UserMHomeService.selectUserMHomeService_S01", vo);
	}

	//취업교육
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveProList(UserMHomeVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.idx.home.service.UserMHomeService.selectUserMHomeService_S02", vo);
	}

	
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveMainProList(UserMHomeVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.idx.home.service.UserMHomeService.selectUserMHomeService_S04", vo);
	}

	
	//취업행사일정
	@SuppressWarnings("unchecked")
	public List<EgovMap> retrieveDayList(UserMHomeVO vo) throws Exception {
		return mybatisDataAccessDAO.list("homenet.shop.idx.home.service.UserMHomeService.selectUserMHomeService_S03", vo);
	}	

}

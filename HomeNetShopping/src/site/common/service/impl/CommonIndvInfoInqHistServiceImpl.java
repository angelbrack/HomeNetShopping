package site.common.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import prjframework.common.dataaccess.util.DataMap;
import prjframework.common.util.SessionUtil;
import site.common.service.CommonIndvInfoInqHistService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("commonIndvInfoInqHistService")
public class CommonIndvInfoInqHistServiceImpl extends AbstractServiceImpl implements CommonIndvInfoInqHistService {

	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;

	public void insertIndvInfoInqHist(Map param) throws Exception {
		IndvInfoVO indvInfoVO = new IndvInfoVO();
		DataMap mapInfo = (DataMap)param.get("USER_PGM_AUTH");
		
		indvInfoVO.setIndvInfoInqRsnCn(String.valueOf(param.get("indvInfoInqRsnCn")));
		indvInfoVO.setQueryExeCn((String)param.get("queryCn"));
		indvInfoVO.setConnUserNo(SessionUtil.getUserNo());
		indvInfoVO.setConnUserNm(SessionUtil.getUserNm());
		indvInfoVO.setConnIp(SessionUtil.getLoginIp());
		indvInfoVO.setOptrPgmNo(String.valueOf(mapInfo.get("optrPgmNo")));
		indvInfoVO.setOptrPgmNm(String.valueOf(mapInfo.get("optrPgmNm")));
		indvInfoVO.setOptrPgmUrlV(String.valueOf(mapInfo.get("optrPgmUrlV")));
		
		mybatisDataAccessDAO.insert("site.home.menu.service.SiteHomeMenuService.insertIndvInfoInqHist_I01", indvInfoVO) ;
	}
}
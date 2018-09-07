package site.home.menu.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import prjframework.common.dataaccess.util.DataMap;
import site.home.menu.service.HomeMenuVO;
import site.home.menu.service.SiteHomeMenuService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("siteHomeMenuService")
public class SiteHomeMenuServiceImpl extends AbstractServiceImpl implements SiteHomeMenuService{
	
	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
	
	@SuppressWarnings("unchecked")
	public List<DataMap> selectTopList(DataMap dataMap) throws Exception {
		return mybatisDataAccessDAO.list("site.home.menu.service.SiteHomeMenuService.selectTopMenuList", dataMap) ;
	}
	
	@SuppressWarnings("unchecked")
	public List<HomeMenuVO> selectLeftList(DataMap dataMap) throws Exception {
		return (List<HomeMenuVO>) mybatisDataAccessDAO.list("site.home.menu.service.SiteHomeMenuService.selectLeftMenuList", dataMap) ;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, List<DataMap>> selectMainList(DataMap dataMap) throws Exception {
		HashMap<String, List<DataMap>> result = new HashMap<String, List<DataMap>>();
		
		result.put("eduUser", mybatisDataAccessDAO.list("site.home.menu.service.SiteHomeMenuService.selectMainEduUserList", null));
		result.put("user", mybatisDataAccessDAO.list("site.home.menu.service.SiteHomeMenuService.selectMainUserList", null));
		
		return result;
	}
}

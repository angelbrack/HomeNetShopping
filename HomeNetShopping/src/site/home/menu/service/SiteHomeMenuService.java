package site.home.menu.service;

import java.util.HashMap;
import java.util.List;

import prjframework.common.dataaccess.util.DataMap;

public interface SiteHomeMenuService {
	public List<DataMap> selectTopList(DataMap dataMap) throws Exception;

	public List<HomeMenuVO> selectLeftList(DataMap param) throws Exception;
	
	public HashMap<String, List<DataMap>> selectMainList(DataMap param) throws Exception;
}

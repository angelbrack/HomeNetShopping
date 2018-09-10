package homenet.shop.idx.home.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface UserMHomeService {
	
	//메인배너
	public List<EgovMap> retrieveBannerList(UserMHomeVO vo) throws Exception;
	
	//취업교육
	public List<EgovMap> retrieveProList(UserMHomeVO vo) throws Exception;
	
	public List<EgovMap> retrieveMainProList(UserMHomeVO vo) throws Exception;
	
	//취업행사일정
	public List<EgovMap> retrieveDayList(UserMHomeVO vo) throws Exception;
		
}

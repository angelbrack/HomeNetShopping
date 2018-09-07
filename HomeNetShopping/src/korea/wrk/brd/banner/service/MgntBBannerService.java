package korea.wrk.brd.banner.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface MgntBBannerService {

	public List<EgovMap> retrieveList(MgntBBannerVO vo) throws Exception;
	public int retrieveCount(MgntBBannerVO vo) throws Exception;
	public MgntBBannerVO retrieveInfo(MgntBBannerVO vo) throws Exception;
	public List<EgovMap> retrieveFile(MgntBBannerVO vo) throws Exception;
	public int register(MgntBBannerVO vo) throws Exception;
	public int modify(MgntBBannerVO vo) throws Exception;
	public int delete(MgntBBannerVO vo) throws Exception;
	
 
}

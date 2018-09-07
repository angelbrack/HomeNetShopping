package korea.wrk.brd.popup.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface MgntBPopupService {

	public List<EgovMap> retrieveList(MgntBPopupVO vo) throws Exception;
	public int retrieveCount(MgntBPopupVO vo) throws Exception;
	public MgntBPopupVO retrieveInfo(MgntBPopupVO vo) throws Exception;
	public List<EgovMap> retrieveFile(MgntBPopupVO vo) throws Exception;
	public int register(MgntBPopupVO vo) throws Exception;
	public int modify(MgntBPopupVO vo) throws Exception;
	public int delete(MgntBPopupVO vo) throws Exception;
	
 
}

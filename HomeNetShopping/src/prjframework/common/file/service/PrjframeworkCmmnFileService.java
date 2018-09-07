package prjframework.common.file.service;

import java.util.List;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**-----------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------
 * 소속 : 고려대학교
 * 수정일 : 2018.08.09
 * 수정자 : 엄재덕
 * 수정내용 : 최초생성
 *------------------------------------------------------------------------
 */
public interface PrjframeworkCmmnFileService {
	
	public List<EgovMap> selectAtchFileList(String atchmnflNo) throws Exception;

	public String insertAtchFile(PrjframeworkCmmnFileVO vo) throws Exception;
}

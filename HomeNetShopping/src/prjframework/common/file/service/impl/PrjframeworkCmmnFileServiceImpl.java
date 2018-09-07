package prjframework.common.file.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.hsqldb.lib.StringUtil;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import prjframework.common.file.service.PrjframeworkCmmnFileService;
import prjframework.common.file.service.PrjframeworkCmmnFileVO;

@Service("prjframeworkCmmnFileService")
public class PrjframeworkCmmnFileServiceImpl implements PrjframeworkCmmnFileService {
	
	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
	
	/** log4j	*/
	protected Log logger = LogFactory.getLog(this.getClass());

	@SuppressWarnings("unchecked")
	public List<EgovMap> selectAtchFileList(String atchmnflNo) throws Exception {
		return mybatisDataAccessDAO.list("prjframework.common.file.service.PrjframeworkCmmnFileService.CmmnFileService_S01", atchmnflNo);
	}

	@SuppressWarnings("unchecked")
	public String insertAtchFile(PrjframeworkCmmnFileVO vo) throws Exception {
		String[] arrFileInfo = null;
		String[] arrFileSplitInfo = null;
		int result = 0;
		String atchmnflNo = vo.getAtchmnflNo();
		if(vo != null) {
			arrFileInfo = vo.getAddFileList();
			if(StringUtil.isEmpty(vo.getAtchmnflNo())) {
				if(arrFileInfo != null && arrFileInfo.length > 0) {
					atchmnflNo = String.valueOf(mybatisDataAccessDAO.select("prjframework.common.file.service.PrjframeworkCmmnFileService.CmmnFileService_S02", null));
					vo.setAtchmnflNo(atchmnflNo);

					result += (Integer)mybatisDataAccessDAO.insert("prjframework.common.file.service.PrjframeworkCmmnFileService.CmmnFileService_I01", vo);

					for(int i = 0; i < arrFileInfo.length; i++) {
						arrFileSplitInfo = StringUtil.split(arrFileInfo[i], "|");

						vo.setAtchmnflNm(arrFileSplitInfo[0]);
						vo.setAtchmnflStreNm(arrFileSplitInfo[1]);
						vo.setAtchmnflMg(arrFileSplitInfo[3]);

						result += (Integer)mybatisDataAccessDAO.insert("prjframework.common.file.service.PrjframeworkCmmnFileService.CmmnFileService_I02", vo);
					}
				}
			} else {

				// 첨부파일 상세파일 삭제
				mybatisDataAccessDAO.delete("prjframework.common.file.service.PrjframeworkCmmnFileService.CmmnFileService_D02", vo);

				// 첨부파일 상세파일 저장
				if(arrFileInfo != null && arrFileInfo.length > 0) {
					for(int i = 0; i < arrFileInfo.length; i++) {
						arrFileSplitInfo = StringUtil.split(arrFileInfo[i], "|");

						vo.setAtchmnflNm(arrFileSplitInfo[0]);
						vo.setAtchmnflStreNm(arrFileSplitInfo[1]);
						vo.setAtchmnflMg(arrFileSplitInfo[3]);

						result += (Integer)mybatisDataAccessDAO.insert("prjframework.common.file.service.PrjframeworkCmmnFileService.CmmnFileService_I02", vo);
					}
				} else {
					// 첨부파일이 없는 경우 삭제
					result += mybatisDataAccessDAO.delete("prjframework.common.file.service.PrjframeworkCmmnFileService.CmmnFileService_D01", vo);
					atchmnflNo = "";
				}
			}
		}
		if(result > 0) {
			logger.debug("첨부파일 없음.");
		}

		return atchmnflNo;
	}

}

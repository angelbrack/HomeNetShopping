package site.code.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import site.code.service.CodeService;
import site.code.service.CodeVO;


import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;

@Service("codeService")
public class CodeServiceImpl extends AbstractServiceImpl implements CodeService {

	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@SuppressWarnings("unchecked")
	public Map<String, Object> listCommon(CodeVO codeVO) throws Exception {
		List<CodeVO> list = mybatisDataAccessDAO.list("site.code.service.CodeService.selectCommonList_S01", codeVO);
		int count = (Integer) mybatisDataAccessDAO.select("site.code.service.CodeService.selectCommonList_S02", codeVO);
		
		List<CodeVO> idList = mybatisDataAccessDAO.list("site.code.service.CodeService.selectCommonList_S03", codeVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCount", Integer.toString(count));
		map.put("idList", idList);
		
		return map;
	}
	
	public CodeVO editCommon(CodeVO codeVO) throws Exception {
		return (CodeVO) mybatisDataAccessDAO.select("site.code.service.CodeService.selectCommonInfo_S01", codeVO) ;
	}
	
	public void insertCommonInfo(CodeVO codeVO) throws Exception {
		mybatisDataAccessDAO.insert("site.code.service.CodeService.insertCommonInfo_I01",codeVO);	
    }
	
    public void updateCommonInfo(CodeVO codeVO) throws Exception {
    	mybatisDataAccessDAO.update("site.code.service.CodeService.updateCommonInfo_U01",codeVO);	
    }
    
    public void deleteCommonInfo(CodeVO codeVO) throws Exception {
    	mybatisDataAccessDAO.delete("site.code.service.CodeService.deleteCommonInfo_D01",codeVO);	
    }
    
    @SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCommonCodeList(String cdId) throws Exception {
    	 return (List<Map<String, Object>>)mybatisDataAccessDAO.list("site.code.service.CodeService.getCommonCodeList", cdId);
    }
    
    @SuppressWarnings("unchecked")
    public List<CodeVO> getCommonCodeVOList(String cdId) throws Exception {
    	return (List<CodeVO>)mybatisDataAccessDAO.list("site.code.service.CodeService.getCommonCodeList2", cdId);
    }
    
    @SuppressWarnings("unchecked")
    public List<CodeVO> getCodeGroupList(String grpCdId) throws Exception {
    	return (List<CodeVO>)mybatisDataAccessDAO.list("site.code.service.CodeService.getCodeGroupList", grpCdId);
    }
    
    @SuppressWarnings("unchecked")
    public List<CodeVO> getCodeGroupSubList(CodeVO codeVO) throws Exception {
    	return (List<CodeVO>)mybatisDataAccessDAO.list("site.code.service.CodeService.getCodeGroupSubList", codeVO);
    }
    
}

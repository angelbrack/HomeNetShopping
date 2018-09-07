package site.code.service;

import java.util.List;
import java.util.Map;

public interface CodeService {

	public Map<String, Object> listCommon(CodeVO codeVO) throws Exception;	
	
	public CodeVO editCommon(CodeVO codeVO) throws Exception;
	
	public void insertCommonInfo(CodeVO codeVO) throws Exception;

	public void updateCommonInfo(CodeVO codeVO) throws Exception;

	public void deleteCommonInfo(CodeVO codeVO) throws Exception;
	
	public List<Map<String, Object>> getCommonCodeList(String cdId) throws Exception;
	
	public List<CodeVO> getCommonCodeVOList(String cdId) throws Exception;
	
	public List<CodeVO> getCodeGroupList(String grpCdId) throws Exception;
	
    public List<CodeVO> getCodeGroupSubList(CodeVO codeVO) throws Exception;
}

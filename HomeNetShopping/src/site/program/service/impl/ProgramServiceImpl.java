package site.program.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import site.program.service.ProgramService;
import site.program.service.ProgramVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;

@Service("programService")
public class ProgramServiceImpl extends AbstractServiceImpl implements ProgramService {

	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
    
    /**
     * 등록되어 있는 프로그램 목록을 조회
     */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectProgramList(ProgramVO vo) throws Exception {
		return mybatisDataAccessDAO.list("site.program.service.ProgramService.selectProgramList", vo) ;
	}
	
	/**
	 * 등록되어 있는 프로그램의 총 카운트를 조회
	 */
	public int selectProgramListTotCnt(ProgramVO vo) throws Exception {
		return (Integer) mybatisDataAccessDAO.select("site.program.service.ProgramService.selectProgramListTotCnt", vo);
	}
	
	/**
	 * 선택한 프로그램의 정보를 조회
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectProgramInfo(ProgramVO vo) throws Exception {
		return (Map<String, Object>) mybatisDataAccessDAO.selectByPk("site.program.service.ProgramService.selectProgramInfo", vo) ;
	}
	/**
	 * 선택한 프로그램을 수정한다.
	 * @throws Exception 
	 */
	public void updateProgramHandle(ProgramVO vo) throws Exception  {
		mybatisDataAccessDAO.update("site.program.service.ProgramService.updateProgramHandle", vo);	
	}

	/**
	 * 새로운 프로그램 정보를 등록한다.
	 * @throws Exception 
	 */
	public void insertProgramHandle(ProgramVO vo) throws Exception {
		String optrPgmNo = (String)mybatisDataAccessDAO.selectByPk("site.program.service.ProgramService.selectProgramNo", null);
		vo.setOptrPgmNo(optrPgmNo);
		
		mybatisDataAccessDAO.insert("site.program.service.ProgramService.insertProgramHandle", vo);
	}
	/**
	 * 선택한 프로그램 목록을 삭제한다.
	 */
	public void deleteProgramHandle(ProgramVO vo) {
    	String[] progNo = null;
    	if (vo != null) {
    		progNo = vo.getProgNo();
    		if (progNo != null && progNo.length>0) {
    			
    			for (int i=0; i<progNo.length; i++) {
    				// 삭제
    				vo.setOptrPgmNo(progNo[i]);
    				mybatisDataAccessDAO.delete("site.program.service.ProgramService.deleteProgramAuth", vo);
    				mybatisDataAccessDAO.delete("site.program.service.ProgramService.deleteProgram", vo);
    			}
    		}else{
    			mybatisDataAccessDAO.delete("site.program.service.ProgramService.deleteProgramAuth", vo);
    			mybatisDataAccessDAO.delete("site.program.service.ProgramService.deleteProgram", vo);
    		}
    	}
	}	
}

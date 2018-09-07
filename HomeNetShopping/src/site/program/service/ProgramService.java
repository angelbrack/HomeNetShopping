package site.program.service;

import java.util.List;
import java.util.Map;

public interface ProgramService {

	/**
	 * 프로그램 목록 조회
	 * @param programVo
	 * @return
	 */
	public List<Map<String, Object>> selectProgramList(ProgramVO vo) throws Exception;
	
	/**
	 * 프로그램의 총 조회 건수
	 * @param programVo
	 * @return
	 */
	public int selectProgramListTotCnt(ProgramVO vo)throws Exception;
	
	/**
	 * 프로그램 정보 상세조회
	 * @param programVo
	 * @return
	 */
	public Map<String, Object> selectProgramInfo(ProgramVO vo)throws Exception;
	
	/**
	 * 프로그램 정보 수정
	 * @param programVo
	 */
	public void updateProgramHandle(ProgramVO vo)throws Exception;
	
	/**
	 * 프로그램 정보 등록
	 * @param programVo
	 */
	public void insertProgramHandle(ProgramVO vo)throws Exception;
	/**
	 * 프로그램 정보 삭제
	 * @param programVo
	 */
	public void deleteProgramHandle(ProgramVO vo)throws Exception;
}

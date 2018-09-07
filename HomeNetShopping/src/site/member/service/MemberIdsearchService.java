package site.member.service;

import java.util.List;
import java.util.Map;

import site.member.service.MemberIdsearchVO;

public interface MemberIdsearchService {
	
	/**
	 * id찾기
	 * @return
	 * @throws Exception
	 */
	public String selectUserCheck(MemberIdsearchVO memberIdsearchVO) throws Exception;
	
	/**
	 * password찾기
	 * @return
	 * @throws Exception
	 */
	public String selectUserCheck2(MemberIdsearchVO memberIdsearchVO) throws Exception;
	
	public void updateHandlePwd(MemberIdsearchVO memberIdsearchVO)throws Exception;
}
package prjframework.common.interceptor.service;

import java.util.Map;

import prjframework.common.dataaccess.util.DataMap;
import prjframework.common.exception.UserAuthNotExistsException;

public interface UserAuthCheckService {
	public Map<String, String> authCheck(DataMap param) throws UserAuthNotExistsException;	
}

package prjframework.common.interceptor.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import prjframework.common.dataaccess.util.DataMap;
import prjframework.common.exception.UserAuthNotExistsException;
import prjframework.common.interceptor.service.UserAuthCheckService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("userAuthCheckService")
public class UserAuthCheckServiceImpl extends AbstractServiceImpl implements UserAuthCheckService {
	
	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
    
    private boolean check;
    
    public void setCheck(boolean check) {
    	this.check = check;
    }
    
	@SuppressWarnings("unchecked")
	public Map<String, String> authCheck(DataMap param) throws UserAuthNotExistsException {
		Map<String, String> userAuth = null;
		if (check) {
			List<DataMap> list = (List<DataMap>)mybatisDataAccessDAO.list("site.home.menu.service.SiteHomeMenuService.selectCheckAuthList", param);
			
			Map<String, String> progInfo = null;
			boolean isSucc = false;
			String infoTp = null;
			String optrPgmGrpNo = null;
			
			if (list != null && list.size() > 0) {
				for (int i=0; i<list.size(); i++) {
					progInfo = (Map<String, String>) list.get(i);
					
					// 1 : 프로그램 정보, 2 : 권한정보
					infoTp = String.valueOf(progInfo.get("infoTp"));
					optrPgmGrpNo = String.valueOf(progInfo.get("optrPgmGrpNo"));
					
					if ("2".equals(infoTp)) {
						isSucc = true;
						userAuth = progInfo;
					} else if ("1".equals(infoTp)) {
							log.debug("\n=============================================");
							log.debug("\n             요청페이지 정보                                       ");
							log.debug("\n=============================================");
							log.debug("\n"+progInfo);
					}
					
					// 0 번인 그룹번호는 권한 체크에서 제외
					if ("0".equals(optrPgmGrpNo)) {
						isSucc = true;
					}
				}
			} else {
				/**
				 * 프로그램 등록되지 않은 요청에대해서 free pass
				 */
				isSucc = true;
				
					log.debug("\n※ 등록되지 않은 프로그램 권한체크를 하지 않습니다.");
			}
			
				log.debug("\n=============================================");
				log.debug("\n화면 접근 관한 : " + isSucc);
				log.debug("\n=============================================");
				
			
			if (!isSucc) {
				throw new UserAuthNotExistsException("사용자 권한이 존재하지 않음");
			}
		}
		return userAuth;
	}

}

package site.menu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import prjframework.common.dataaccess.dao.MybatisDataAccessDAO;
import site.menu.service.MenuService;
import site.menu.service.MenuVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("menuService")
public class MenuServiceImpl extends AbstractServiceImpl implements MenuService {
	
	@Resource(name = "mybatisDataAccessDAO")
	private MybatisDataAccessDAO mybatisDataAccessDAO;
    
	// 메뉴목록 조회
	@SuppressWarnings("unchecked")
	public List<MenuVO> list(MenuVO menuVO) {
		return mybatisDataAccessDAO.list("site.menu.service.MenuService.list", menuVO) ;
	}

	// 메뉴목록 수정
	public int update(MenuVO menuVO) {
		return mybatisDataAccessDAO.update("site.menu.service.MenuService.update",menuVO);
	}

	public int deleteMenu(MenuVO menuVO) {
		return mybatisDataAccessDAO.delete("site.menu.service.MenuService.deleteMenu",menuVO);
	}

	// 메뉴목록 생성
	public String createMenu(MenuVO menuVO) {
		String newMenuNo = null;
		mybatisDataAccessDAO.insert("site.menu.service.MenuService.createMenu",menuVO);
		
		newMenuNo = menuVO.getMenuNo();
		
		return newMenuNo; 
	}
	
	// 메뉴목록 생성
	public String createMenu2(MenuVO menuVO) {
		String newMenuNo = null;
		mybatisDataAccessDAO.insert("site.menu.service.MenuService.createMenu2",menuVO);
		
		newMenuNo = menuVO.getMenuNo();
		
		return newMenuNo; 
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getMenuInfo(Map<String, Object> param) {
		return (Map<String, Object>) mybatisDataAccessDAO.selectByPk("site.menu.service.MenuService.getMenuInfo", param);
	}
}

package site.menu.service;

import java.util.List;
import java.util.Map;


public interface MenuService {

	// 메뉴목록 조회
	public List<MenuVO> list(MenuVO menuVO);
	
	// 메뉴정보 수정
	public int update(MenuVO menuVO);
	
	// 메뉴 삭제
	public int deleteMenu(MenuVO menuVO);
	
	// 메뉴 생성
	public String createMenu(MenuVO menuVO);
	
	// 메뉴 생성2
	public String createMenu2(MenuVO menuVO);
	
	// 메뉴 단건 조회
	public Map<String, Object> getMenuInfo(Map<String, Object> menuNo);
}

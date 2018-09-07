package mgnt.system.pgm.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import prjframework.common.annotation.ValidSaveToken;
import prjframework.common.util.PageMove;
import prjframework.common.util.SessionUtil;
import site.program.service.ProgramService;
import site.program.service.ProgramVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MgntSystemPgmController {
	
    /** 프로그램 관리 서비스 **/
    @Resource(name = "programService")
    protected ProgramService programService;
    
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	

	/**
	 * 프로그램 목록조회 요청을 받아 프로그램 목록 화면으로 이동시킨다.
	 * @return
	 */
	@RequestMapping(value="/mgnt/system/PgmListAction.do")
	public String programList(ModelMap model, HttpServletRequest request, @ModelAttribute("searchVO") ProgramVO vo)
		throws Exception {
		
		/** 관리자 로그인 체크 start */
		if(!SessionUtil.isLogin()) {
			return "mgnt/home/login/login";
		}
		/** 관리자 로그인 체크 end */
		
		vo.setPageUnit(vo.getPageUnit());
		vo.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(vo.getCurrentPage());
		paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		paginationInfo.setPageSize(vo.getPageSize());

		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<Map<String, Object>> list = programService.selectProgramList(vo);
		model.addAttribute("list", list);
		
        int totCnt = programService.selectProgramListTotCnt(vo);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("searchVO", vo);
        
		return "mgnt/system/pgm/sysPgmList";
	}
	/**
	 * 프로그램 목록조회 요청을 받아 프로그램 목록 화면으로 이동시킨다.
	 * @return
	 */
	@RequestMapping(value="/mgnt/system/PgmSearchAction.do")
	public String programSearch(ModelMap model, 
			@ModelAttribute("searchVO") ProgramVO vo,
			HttpServletRequest request) throws Exception {
		
		/** 관리자 로그인 체크 start */
		if(!SessionUtil.isLogin()) {
			return "mgnt/home/login/login";
		}
		/** 관리자 로그인 체크 end */
		
		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurrentPage()); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		paginationInfo.setPageSize(vo.getPageSize());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		vo.setSearchMenuUseYn("Y");
		
		List<Map<String, Object>> list = programService.selectProgramList(vo);
		model.addAttribute("list", list);

		int totCnt = programService.selectProgramListTotCnt(vo);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("searchVO", vo);
		
		return "mgnt/system/pgm/sysPgmSearch";
	}
	/**
	 * 프로그램을 등록/수정 할 수 있는 화면으로 이동한다.
	 * @return
	 */
	@RequestMapping(value="/mgnt/system/PgmHandleAction.do")
	public String programEdit(ModelMap model,
			@ModelAttribute("searchVO") ProgramVO vo,
			HttpServletRequest request) throws Exception{
		
		Map<String, Object> info = programService.selectProgramInfo(vo);
		
		model.addAttribute("info", info);
		return "mgnt/system/pgm/sysPgmHandle";
	}
	/**
	 * 프로그램 정보를 등록/수정 처리 한 후 프로그램 목록 조회 화면으로 이동시킨다.
	 * @return
	 * @throws Exception 
	 */
	@ValidSaveToken	//sub 토큰 사용시 사용
	@RequestMapping(value="/mgnt/system/PgmSaveAction.do")
		public ModelAndView programSave(@ModelAttribute("searchVO") @Valid ProgramVO vo,
				BindingResult bindingResult, SessionStatus status, ModelMap model, HttpServletRequest request, @RequestParam Map<Object, Object> param) throws Exception {
			
		String msg = "";
		/** 관리자 로그인 체크 end */
		String userNo = "";
		/** 관리자 로그인 체크 start */
		if(SessionUtil.isLogin()) {
			userNo = SessionUtil.getUserNo();
		}
		/** 관리자 로그인 체크 end */
		
		vo.setLoginUserNo(userNo);
		vo.setRemoteAddr(request.getRemoteAddr());
		
		//VO Valid check
		if(bindingResult.hasErrors()) {
			return new ModelAndView("mgnt/system/pgm/sysPgmHandle");
		}
		
		if (StringUtils.isNotEmpty(vo.getOptrPgmNo())) {
			programService.updateProgramHandle(vo);
			msg = egovMessageSource.getMessage("success.common.update");
		} else {
			programService.insertProgramHandle(vo);
			msg = egovMessageSource.getMessage("success.common.insert");
		}		
		
		return PageMove.alertAndRedirectNoPost(model, "/mgnt/system/PgmListAction.do", msg, param);
	}
	
	/**
	 * 프로그램 정보를 삭제 한다.
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/mgnt/system/PgmDeleteAction.do")
	public ModelAndView programDelete(ModelMap model, @ModelAttribute("searchVO") ProgramVO vo, @RequestParam Map<Object, Object> param) throws Exception {
		programService.deleteProgramHandle(vo);
		String msg = egovMessageSource.getMessage("success.common.delete");
		
		return PageMove.alertAndRedirectNoPost(model, "/mgnt/system/PgmListAction.do", msg, param);
	}
	
	@RequestMapping(value="/common/munual/commManualAction.do")
	public String commManual(ModelMap model, @ModelAttribute("searchVO") ProgramVO vo, @RequestParam Map<Object, Object> param) throws Exception {
		Map<String, Object> info = programService.selectProgramInfo(vo);
		model.addAttribute("info", info);
		return "common/commonManual";
	}
	
}

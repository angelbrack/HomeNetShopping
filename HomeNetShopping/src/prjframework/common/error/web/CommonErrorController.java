package prjframework.common.error.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonErrorController {
	
	
	@RequestMapping(value = "/common/error.do")
	public String getError(ModelMap model, HttpServletRequest request)
	throws Exception {
		return "error";
	}
	@RequestMapping(value = "/common/error404.do")
	public String getError404(ModelMap model, HttpServletRequest request)
			throws Exception {
		return "error404";
	}
	@RequestMapping(value = "/common/error500.do")
	public String getError500(ModelMap model, HttpServletRequest request)
			throws Exception {
		return "error500";
	}
}

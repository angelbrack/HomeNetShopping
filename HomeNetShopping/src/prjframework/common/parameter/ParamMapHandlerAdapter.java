package prjframework.common.parameter;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;







import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;


public class ParamMapHandlerAdapter implements WebArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter
			, NativeWebRequest webRequest) throws Exception {
		
		
		
		Class<?> clz = methodParameter.getParameterType();
		String paramName = methodParameter.getParameterName();
		Map paramMap = new HashMap();
		if ((clz.equals(Map.class)) && (paramName.equals("paramdMap"))) {
			HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
			Enumeration enumeration = request.getParameterNames();
		    while (enumeration.hasMoreElements()) {
		    	String key = (String)enumeration.nextElement();
		    	String[] values = request.getParameterValues(key);
		    	if (values != null) {
		    		paramMap.put(key, (values.length > 1) ? values : values[0]);
		    	}
		    }
		}
		// TODO Auto-generated method stub
		return paramMap ;
	}

}

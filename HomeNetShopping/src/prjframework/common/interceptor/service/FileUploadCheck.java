package prjframework.common.interceptor.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author oops
 * 파일 업로드를 처리하는 인터페이스
 */
public interface FileUploadCheck {
	public Map<String, Object> check(MultipartFile file, String pathKey);
}

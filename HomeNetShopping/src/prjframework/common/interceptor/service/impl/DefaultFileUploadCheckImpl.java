package prjframework.common.interceptor.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import prjframework.common.interceptor.service.FileUploadCheck;


/**
 * <pre>
 * 기본 파일 업로드 체크
 * @author oops
 * </pre>
 */
public class DefaultFileUploadCheckImpl implements FileUploadCheck {

	@Resource(name = "fileUploadProperties")
	Properties fileUploadProperties;
	
	/**
	 * COMMON.BOARD.PATH = /repository/uploadfiles/common/board/src
	 * COMMON.BOARD.AUTH = N
	 * COMMON.BOARD.SIZE = 30720
	 * COMMON.BOARD.UPTMP = N
	 */
	public Map<String, Object> check(MultipartFile file, String pathKey) {
		Map<String, Object> map = new HashMap<String, Object>();
		String authCheckYn = "";
		String checkSize = "0";
		String commonIgnoreFileType = null;
		String fileType = null;
		String checkYn = "Y";
		String errorCode = null;
		
		authCheckYn = fileUploadProperties.getProperty(pathKey+".AUTH");
		checkSize = fileUploadProperties.getProperty(pathKey+".SIZE", "0");
		commonIgnoreFileType = fileUploadProperties.getProperty("COMMON.IGNORE.FILE.TYPE");
		fileType = fileUploadProperties.getProperty(pathKey + ".FILE.TYPE" , "");
		long fileRealSize = file.getSize();

		if ("Y".equals(authCheckYn)) {
			//TODO: 권한 체크
			errorCode = "AUTH_CHECK";
		}
		
		if (!"0".equals(checkSize)) {
			long checkFileSize = 0;
			
			try {
				checkFileSize = Long.parseLong(checkSize);
				
				if (fileRealSize > checkFileSize) {
					errorCode = "SIZE_CHECK";
				}
			} catch(Exception e) {
				//TODO: 사이즈 변환 오류시 업로드 제한을 두지 않음
				e.printStackTrace();
			}
		}
		
		//파일 확장자 체크
		String fileExt = null;
		
		if (file.getOriginalFilename().lastIndexOf(".") != -1) {
			fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		}
		
		String[] commonIgnore = StringUtils.commaDelimitedListToStringArray(commonIgnoreFileType);
		String[] fileTypes = StringUtils.commaDelimitedListToStringArray(fileType);
		
		boolean bFileTypeCheck = true;
		
		// 허용되지 않은 파일 확장자 체크
		if (fileExt != null && !"".equals(fileExt)) {
			for (int i=0;i<commonIgnore.length; i++) {
				if (fileExt.equalsIgnoreCase(commonIgnore[i])) {
					errorCode = "FILE_TYPE";
					bFileTypeCheck = false;
					break;
				}
			}
		}
		
		if (bFileTypeCheck) {
			// 허용된 파일 확장자 체크
			boolean bFileCheck = false;
			if (fileTypes == null || fileTypes.length == 0) {
				// 첨부파일가능확장자가 등록되지 않은경우는 모든 파일 허용 함(공통으로 허용하지 않는 확장자 제외)
				bFileCheck = true;
			} else {
				if (fileExt != null && !"".equals(fileExt)) {
					for (int i=0;i<fileTypes.length; i++) {
						if ("*".equals(fileTypes[i]) || fileExt.equalsIgnoreCase(fileTypes[i])) {
							bFileCheck = true;
							break;
						}
					}
				}
			}
			
			if (!bFileCheck) {
				errorCode = "FILE_TYPE";
			}
		}
		
		if (errorCode != null && !"".equals(errorCode)) {
			checkYn = "N";
		}
		
		map.put("ERROR_CODE", errorCode);
		map.put("CHECK_YN", checkYn);
		return map;
	}
}

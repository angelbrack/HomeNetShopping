package prjframework.common.interceptor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import prjframework.common.exception.CommonAlertException;
import prjframework.common.interceptor.service.FileUploadCheck;


/**
 * <pre>
 * 	파일업로드 처리를 하기위해 MultipartRequest를 중간에 Interceptor 하여 파일업로드 처리를 한다.
 * 	
 * 	
 *	1> 파일업로드 설정 파일
	==============================================================================================================================
 	설정 파일 : context-fileupload.xml
	==============================================================================================================================
	<!-- 파일업로드를 위한 MultipartResolver 정의 -->
	<bean id="spring.RegularCommonsMultipartResolver"
		class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="maxUploadSize" value="10485760" />
		<property name="maxInMemorySize" value="100000000" />
	</bean>

	<alias name="spring.RegularCommonsMultipartResolver" alias="multipartResolver" />
	
 	<util:properties id="fileUploadProperties" location="classpath:/humanwise/properties/fileUploadProperties.properties" />
	==============================================================================================================================
 	설정 파일 : dispatcher-servlet.xml 
 	==============================================================================================================================
	<bean class="org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping" p:order="1">
    	<property name="alwaysUseFullPath" value="true"/>
        <property name="interceptors">
            <list>
                <ref bean="fileUploadInterceptor" />
            </list>
        </property>
    </bean>
    
	<!-- 파일업로드 시작 -->
	<bean id="fileUploadInterceptor" class="prjframework.common.interceptor.FileUploadInterceptor" />
	<!-- 파일업로드 유효성 체크 -->
	<bean id="fileUploadCheck" class="prjframework.common.interceptor.service.impl.DefaultFileUploadCheckImpl" />
	==============================================================================================================================
 * @author oops
 * </pre>
 */
public class FileUploadInterceptor extends HandlerInterceptorAdapter {
	private Log log = LogFactory.getLog(getClass());
	
	@Resource(name = "fileUploadCheck")
	private FileUploadCheck fileUploadCheck;

	@Resource(name = "fileUploadProperties")
	Properties fileUploadProperties;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String smartImgYn = request.getParameter("smartImgYn");
		String curMonth = request.getParameter("curMonth");
		
		log.debug("smartImgYn       : " + smartImgYn);
		if("Y".equals(smartImgYn)) {
			if (request instanceof MultipartHttpServletRequest) {
				
				if (log.isDebugEnabled()) {
					log.debug("==========================================================================");
					log.debug(".....................       FILE UPLOAD START        .....................");
				}
				
				final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	
				// extract files
				final Map<String, MultipartFile> files = multiRequest.getFileMap();
				
				String filePathKey = request.getParameter("filePathKey");
				String uploadPathType  = fileUploadProperties.getProperty(filePathKey+".PATH.TYPE", "FILE");
				String fullPathKey = filePathKey + ".PATH";
				
				// process files
				String uploadLastPath = null;
				
				String tmpUseYn = fileUploadProperties.getProperty(filePathKey+".USETMP", "N");
				
				/**
				 * 임시 업로드 경로를 사용할 경우 업무 진행시 실제 업로드 경로로 파일을 복사해줘야 함
				 */
				if ("N".equals(tmpUseYn)) {
					uploadLastPath = fileUploadProperties.getProperty(fullPathKey);
				} else {
					uploadLastPath = fileUploadProperties.getProperty("IO.TMPDIR");
				}
				
				if (uploadLastPath == null) {
					throw new CommonAlertException("잘못된 업로드 정보를 호출하였습니다.");
				}
				
				log.debug("tmpUseYn       : " + tmpUseYn);
				log.debug("uploadPathType     : " + uploadPathType);
				log.debug("filePathKey    : " + filePathKey);			
				log.debug("uploadLastPath : " + uploadLastPath);
				
				String uploadPath = null;
				
				if ("LINK".equals(uploadPathType)) {
					uploadPath = request.getSession().getServletContext().getRealPath("/") + uploadLastPath;
				} else {
					uploadPath = uploadLastPath;
				}
				
		        uploadPath = uploadPath + "/" + curMonth;	//현재달로 경로를 정한다.
		        
				File saveFolder = new File(uploadPath);
				String originalFileName = null;
				String changeFileName = null;
				
				List<Object> result = new ArrayList<Object>();
				// 디렉토리 생성
				boolean isDir = false;
	
				if (!saveFolder.exists() || saveFolder.isFile()) {
					saveFolder.mkdirs();
				}
	
				if (!isDir) {
					Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
					MultipartFile file;
					String filePath;
					String ext = "";
					Entry<String, MultipartFile> entry = null;
					Map<String, Object> fileMap = null;
					
					while (itr.hasNext()) {
						entry = itr.next();
						file = entry.getValue();
						originalFileName = file.getOriginalFilename();
						
						/**
						 * 파일 업로드 유효성 체크 
						 */
						if (!"".equals(originalFileName)) {
							fileMap = fileUploadCheck.check(file, filePathKey);
							
							if (fileMap == null) {
								throw new CommonAlertException("업로드 유효성 체크중 오류가 발생하였습니다.");
							}
							fileMap.put("FILE_EXIST_CD", "Y".equals(tmpUseYn)?"01":"02");
	
							// 파일확장자 추출
							if (originalFileName.lastIndexOf(".") != -1) {
								ext = originalFileName.substring(originalFileName.lastIndexOf("."));
							}
	
							/**
							 * 파일업로드 유효성 체크
							 */
							String checkYn = (String) fileMap.get("CHECK_YN");
							
							log.debug("업로드 완료여부 : " + checkYn);
	
							if ("Y".equals(checkYn)) {
								//changeFileName = file.getName() + System.currentTimeMillis() + ext;
								changeFileName = UUID.randomUUID().toString()+ext;
								filePath = uploadPath + File.separator + changeFileName;
								file.transferTo(new File(filePath));
								
								fileMap.put("ORI_FILE_NAME", originalFileName);
								fileMap.put("CHG_FILE_NAME", changeFileName);
								fileMap.put("FILE_SIZE", file.getSize()+"");
								fileMap.put("FILE_EXT", originalFileName.substring(originalFileName.lastIndexOf(".")+1));
								
								result.add(fileMap);
								
								if (log.isDebugEnabled()) {
									log.debug("COLUMN_NAME : " + file.getName());
									log.debug("filePathKey : " + filePathKey);
									log.debug("ORIGINAL_FILE_NAME : " + originalFileName);
									log.debug("CHANGE_FILE_NAME : " + changeFileName);
								}
							} else {
								String errorCode = (String)fileMap.get("ERROR_CODE");
								String errorMsg = null;
								
								if ("AUTH_CHECK".equals(errorCode)) {
									errorMsg = "업로드권한이 없습니다.";
								} else if ("SIZE_CHECK".equals(errorCode)) {
									errorMsg = "업로드 가능 용량을 초과하였습니다.";
								} else if ("FILE_TYPE".equals(errorCode)) {
									errorMsg = "유효하지 않은 파일 확장자입니다.";
								} else {
									errorMsg = "파일업로드 유효성에 맞지 않습니다.";
								}
								
								if (log.isDebugEnabled()) {
									log.debug(".....................       FILE UPLOAD FAIL END     .....................");
									log.debug("==========================================================================");
								}
								throw new CommonAlertException(errorMsg);
							}
						}
						
					}
				}
				
				// 업로드 파일 리스트 
				request.setAttribute("FILE_LIST", result);
				
				if (log.isDebugEnabled()) {
					log.debug(".....................       FILE UPLOAD SUCCESS END   .....................");
					log.debug("==========================================================================");
				}
			}
		}
		return super.preHandle(request, response, handler);
	}
}
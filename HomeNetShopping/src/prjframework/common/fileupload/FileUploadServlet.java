package prjframework.common.fileupload;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DataConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import prjframework.common.util.SessionUtil;


public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2157915201347271033L;
	private Log log = getLogger();

	protected Log getLogger() {
		if(log == null) {
			log = LogFactory.getLog(getClass());
		}
		return log;
	}
	
	@Override
	public void init(ServletConfig config) {
	}

	public static String getTransPathKey(DataConfiguration conf, String pathKey, String uploadPathYn) {

		String useTmpYn = conf.getString(pathKey + ".UPTMP", "N");

		String path = null;
		if(("Y".equals(uploadPathYn) && "Y".equals(useTmpYn)) || StringUtils.isEmpty(pathKey)) {
			path = conf.getString("IO.TMPDIR", System.getProperty("java.io.tmpdir"));
		} else {
			path = conf.getString(pathKey + ".PATH");
		}

		return path;
	}

	@SuppressWarnings({ "unused", "null" })
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataConfiguration conf = null;
		try {
			conf = new DataConfiguration(new PropertiesConfiguration("prjframe/common/properties/fileUploadProperties.properties"));

			if(conf == null) {
				conf = null;
			}
		} catch(ConfigurationException e) {
			log.error("conf error");
		}

		String pathkey 		= request.getParameter("pathkey");
		String filePath 	= request.getParameter("filePath");
		String addSavePath 	= request.getParameter("addSavePath");

		String realFileName = request.getParameter("realFileName");
		if(StringUtils.isNotEmpty(realFileName)) {
			realFileName = 	URLDecoder.decode(realFileName, "UTF-8");
		}

		//String savePath = request.getSession().getServletContext().getRealPath("/") + getTransPathKey(conf, pathkey, "N");
		String savePath = "";
		if ( pathkey != null && !"".equals(pathkey) ) {
			savePath = request.getSession().getServletContext().getRealPath("/") + getTransPathKey(conf, pathkey, "N");
		} else {
			savePath = request.getSession().getServletContext().getRealPath("/") + filePath;
		}
		
		if(addSavePath != null && !addSavePath.equals("")) {
			savePath = savePath + "/" + addSavePath;
		}
		
		if(request.getParameter("getfile") != null && StringUtils.isNotEmpty(request.getParameter("getfile"))) {

			String getfile = request.getParameter("getfile");
			String strCrsKey = request.getParameter("crsKey");
			String[] mkList = null;
			boolean bChk = false;
			if(strCrsKey == null)
				strCrsKey = "";

			getfile = getfile.replaceAll("/", "");
			getfile = getfile.replaceAll("../", "");
			getfile = getfile.replaceAll("&", "");

			if(bChk) {
				savePath = savePath + "/" + mkList[1] + "/" + mkList[0] + "/" + mkList[2];
			}
			if(savePath.indexOf("../") >= 0) {
				savePath = savePath.replaceAll("../", "");
			}

			File file = new File(savePath, getfile);

			ServletOutputStream op 	= null;
			DataInputStream in 		= null;
			FileInputStream fileInputStream = null;
			if(file.exists()) {
				int bytes = 0;
				try {
    				byte[] buf = FileUtils.readFileToByteArray(file);
    
    				setResponseHeader(request, response, realFileName, buf.length);
    
    				op = response.getOutputStream();
    
    				byte[] bbuf = new byte[1024];
    				fileInputStream = new FileInputStream(file);
    				in = new DataInputStream(fileInputStream);
    
    				while((in != null) && ((bytes = in.read(bbuf)) != -1)) {
    					op.write(bbuf, 0, bytes);
    				}
				} catch (IOException e) {
					if (op != null){
						op.flush();
						op.close();
					}
					if (in != null){
						in.close();
					}
					if(fileInputStream != null) {
						fileInputStream.close();
					}
				} finally {
					if (op != null){
						op.flush();
						op.close();
					}
					if (in != null){
						in.close();
					}
					if(fileInputStream != null) {
						fileInputStream.close();
					}
				}
			} else {
				PrintWriter writer = response.getWriter();
				response.setContentType("text/html;charset=utf-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setCharacterEncoding("UTF-8");
				writer.append("<!DOCTYPE html>\n");
				writer.append("<html lang=\"ko\">\n<head>\n<title>미래지식포털</title>\n<script type=\"text/javascript\">\nfunction onFileNotMsg(){\n");
				writer.append("alert('File not found!'); window.open(\"about:blank\", \"_self\").close(); \n");
				writer.append("}\n</script>\n</head>\n<body onload=\"onFileNotMsg();\">\n");
				writer.append("</body>\n</html>\n");
				writer.flush();
				writer.close();
			}
		} else if(request.getParameter("delfile") != null && StringUtils.isNotEmpty(request.getParameter("delfile"))) {

			String delfile = request.getParameter("delfile");

			delfile = delfile.replaceAll("/", "");
			delfile = delfile.replaceAll("../", "");
			delfile = delfile.replaceAll("&", "");

			File file = new File(savePath, delfile);
			if(file.exists()) {
				if(file != null) {
					if(!file.delete()) {
						log.error("delete error");
					}
				}
			} else {
				PrintWriter writer = response.getWriter();
				response.setContentType("text/html;charset=utf-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setCharacterEncoding("UTF-8");
				writer.append("<!DOCTYPE html>\n");
				writer.append("<html lang=\"ko\">\n<head>\n<title>미래지식포털</title>\n<script type=\"text/javascript\">\nfunction onFileNotMsg(){\n");
				writer.append("alert('File not found!'); window.open(\"about:blank\", \"_self\").close(); \n");
				writer.append("}\n</script>\n</head>\n<body onload=\"onFileNotMsg();\">\n");
				writer.append("</body>\n</html>\n");
				writer.flush();
				writer.close();
			}
		} else if(request.getParameter("getthumb") != null && StringUtils.isNotEmpty(request.getParameter("getthumb"))) {

			String getthumb = request.getParameter("getthumb");
			
			String curMonth = request.getParameter("curMonth");
			if ( curMonth != null && !"".equals(curMonth) ) {
				savePath += File.separator + curMonth;
			}

			getthumb = getthumb.replaceAll("/", "");
			getthumb = getthumb.replaceAll("../", "");
			getthumb = getthumb.replaceAll("&", "");
 
			File file = new File(savePath, getthumb);
			String mimetype = getSuffix(file.getName());

			if(mimetype.toLowerCase().endsWith("png") || mimetype.toLowerCase().endsWith("jpeg") || mimetype.toLowerCase().endsWith("gif") || mimetype.toLowerCase().endsWith("jpg")) {
				BufferedImage im = null;
				try {
					im = ImageIO.read(file);
				} catch(IOException e) {
					log.error("get error");
				}
				if(im != null) {
					BufferedImage thumb = im;// Scalr.resize(im, 75);
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					if(mimetype.toLowerCase().endsWith("png")) {
						ImageIO.write(thumb, "PNG", os);
						response.setContentType("image/png");
					} else if(mimetype.toLowerCase().endsWith("jpeg") || mimetype.toLowerCase().endsWith("jpg")) {
						ImageIO.write(thumb, "JPG", os);
						response.setContentType("image/jpeg");
					} else {
						ImageIO.write(thumb, "GIF", os);
						response.setContentType("image/gif");
					}
					
					//response.setContentType("application/octet-stream");
					
					ServletOutputStream srvos = response.getOutputStream();
					response.setContentLength(os.size());
					response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
					os.writeTo(srvos);
					srvos.flush();
					srvos.close();
				}
			}
		} else if(request.getParameter("zipfile") != null && StringUtils.isNotEmpty(request.getParameter("zipfile"))) {
			
			//실제 파일명 파라미터
			String zipFile = request.getParameter("zipfile");
			//업로드 파일명 파라미터
			String zipFileRe = request.getParameter("zipfileRe");
			//게시물번호
			String seqNo = request.getParameter("seqNo");
			//게시물업데이트일자
			String date = request.getParameter("date");
			
			int bufferSize = 1024 * 2;
			
			//임시 폴더 경로
			String ioSavePath = request.getSession().getServletContext().getRealPath("/") + getTransPathKey(conf, "COURSE.DATAZIP", "N");
			
			//실제 파일경로 
			String[] mkList = null;
			boolean bChk = false;

			if(bChk) {
				savePath = savePath + "/" + mkList[1] + "/" + mkList[0] + "/" + mkList[2];
			}
			if(savePath.indexOf("../") >= 0) {
				savePath = savePath.replaceAll("../", "");
			}
			
			//zip파일명
			String ouputName = "EKP_DATA_" + System.currentTimeMillis();
			ZipOutputStream zos = null;
			FileInputStream fis = null;
			FileOutputStream fos = null;
			
			try {
			    
				//파일 다운로드 선언
			    if (request.getHeader("User-Agent").indexOf("MSIE 5.5") > -1) {
			        response.setHeader("Content-Disposition", "filename=" + ouputName + ".zip" + ";");
			    } else {
			        response.setHeader("Content-Disposition", "attachment; filename=" + ouputName + ".zip" + ";");
			    }
			    response.setHeader("Content-Transfer-Encoding", "binary");
			    
			    //파일 압축 선언
			    OutputStream os = response.getOutputStream();
			    zos = new ZipOutputStream(os); 
			    //압축 레벨(기본:8, 최대압축률:8)
			    zos.setLevel(8); 
			    BufferedInputStream bis = null;
			    
			    FileInputStream fs = null;
			                
			    int size = 1024;
			    //실제 파일명
			    String[] files = zipFile.split(",");
			    //업로드 파일명
			    String[] nameFiles = URLDecoder.decode(zipFileRe, "UTF-8").split(",");

			    File nPathFile = new File(ioSavePath + "/" + seqNo + date);
				
			    if(!nPathFile.exists()) {
			    	
			    	if(!nPathFile.mkdirs()) {
						log.error("nPathFile mkdirs error");
					}
					
			    	for (int i=0; i<files.length; i++) {

    			    	//파일을 임시 폴더로 이동 시작
			    		try {
        			    	fis = new FileInputStream(savePath + "/" + files[i]);
        					fos = new FileOutputStream(ioSavePath + "/" + files[i]);
        					int data = 0;
        					while ((data = fis.read()) != -1) {
            					try {
            						fos.write(data);
            					} catch(IOException e){
            						log.error("file zip error");
            					} finally {
            						if (fos != null){
            							fos.close();
            						}
            						if (fis != null){
            							fis.close();
            						}
            					}    						
        					}
        					fos.close();
        					fis.close();
			    		} catch (IOException e ) {
			    			log.error("file io error");
			    		} finally {
			    			if (fos != null){
    							fos.close();
    						}
			    			if (fis != null){
    							fis.close();
    						}
			    		}
    					//파일을 임시 폴더로 이동 종료
    					
    					//파일 이름 변경 시작
    					File rfile = new File(ioSavePath + "/" + files[i]);
    			        File nFile = new File(ioSavePath + "/" + seqNo + date + "/" +nameFiles[i]);

    			        if(!nPathFile.exists()) {
    						if(!nPathFile.mkdirs()) {
    							log.error("file zip mkdirs error");
    						}
    					}
    			        
    			        if(rfile.exists()){ 
    						if(!rfile.renameTo(nFile)) {
    							log.error("file zip rename error");
    						}
    		        	}
			    	}   
			    	//파일 이름 종료
			    }	
			    
			    try {
			    	
			   
    			    //실시간 파일 압축 처리
    		    	for (int j=0; j<files.length; j++) {
    			    	
    		    		try {
        					//파일 압축 시작
        			    	File realFile = new File(ioSavePath);
        			    	
        		            fs = new FileInputStream( ioSavePath + "/" + seqNo + date + "/" +nameFiles[j]);
        		            bis = new BufferedInputStream(fs, size);
        		            
        			        ZipEntry zentry = new ZipEntry(nameFiles[j]);
        			        zentry.setTime(realFile.lastModified());
        			        zos.putNextEntry(zentry);
        			        
        			        byte[] buffer = new byte[bufferSize];
        			        int cnt = 0;
        			        while ((cnt = bis.read(buffer, 0, bufferSize)) != -1) {
        			            zos.write(buffer, 0, cnt);
        			        }
        			        zos.closeEntry();
        			        //파일 압축 종료
    		    		} catch (IOException e) {
    		    			log.error("file zip error");
    		    		} finally {
    		    			
    		    			if(zos != null) {
    		    				zos.close();
    		    			}
    		    			if ( bis != null ) {
    		    				bis.close();
    		    			}
    		    			if ( fs != null ) {
    		    				fs.close();
    		    			}
    		    		}
    			    }
			    } catch (IOException e) {
	    			log.error("file zip error");
	    		} finally {
	    			if ( zos != null ) {
	    				zos.close();
	    			}
	    			if ( bis != null ) {
	    				bis.close();
	    			}
	    			if ( fs != null ) {
	    				fs.close();
	    			}
	    		}
			} catch(IOException e){
				log.error("file zip error");
			} finally {
				if(zos != null) {
					zos.close();
				}
				if (fis != null){
					fis.close();
				}
				if (fos != null){
					fos.close();
				}
			}
			
			
			
		} else {
			PrintWriter writer = null;
			try {
    			writer = response.getWriter();
    			response.setContentType("text/html;charset=utf-8");
    			response.setHeader("Cache-Control", "no-cache");
    			response.setCharacterEncoding("UTF-8");
    			writer.append("<!DOCTYPE html>\n");
    			writer.append("<html lang=\"ko\">\n<head>\n<title>미래지식포털</title>\n<script type=\"text/javascript\">\nfunction onFileNotMsg(){\n");
    			writer.append("alert('File not found!'); window.open(\"about:blank\", \"_self\").close(); \n");
    			writer.append("}\n</script>\n</head>\n<body onload=\"onFileNotMsg();\">\n");
    			writer.append("</body>\n</html>\n");
			} catch(IOException e){
				log.error("print witer error");
			} finally {
				if (writer != null){
					writer.flush();
					writer.close();
				}
			}
		}
	}

	@SuppressWarnings({"unchecked", "unused"})
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
		}

		DataConfiguration conf = null;
		try {
			conf = new DataConfiguration(new PropertiesConfiguration("/prjframe/common/properties/fileUploadProperties.properties"));

			if(conf == null) {
				conf = null;
			}

		} catch(ConfigurationException e) {
			log.error("conf error");
		}

		ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
		PrintWriter writer = response.getWriter();
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		JSONArray json = new JSONArray();
		try {

			String pathKey = request.getParameter("pathkey");
			String strCrsKey = request.getParameter("crsKey");
			String addSavePath = request.getParameter("addSavePath");
			String[] mkList = null;
			if(strCrsKey == null) {
				strCrsKey = "";
			}
			boolean bChk = false;

			if(conf != null && pathKey != null && request != null) {
				if(!checkAuth(conf, pathKey, request)) {
					log.error("checkAuth false");
				}
			}
			
			String savePath = "";
			String saveFileName = null;
			String realFileName = null;
			String useRealName = null;
			String fileExt = null;
			String filePath = null;
			String errorMsg = null;

			List<FileItem> items = uploadHandler.parseRequest(request);
			for(FileItem item : items) {
				if(!item.isFormField()) {
					if(bChk) {
						savePath = request.getSession().getServletContext().getRealPath("/") + getTransPathKey(conf, pathKey, "Y") + "/" + mkList[1] + "/" + mkList[0] + "/" + mkList[2];
						filePath = getTransPathKey(conf, pathKey, "Y") + "/" + mkList[1] + "/" + mkList[0] + "/" + mkList[2];
					} else {
						savePath = request.getSession().getServletContext().getRealPath("/") + getTransPathKey(conf, pathKey, "Y");
						filePath = getTransPathKey(conf, pathKey, "Y");
					}
					
					if(addSavePath != null && !addSavePath.equals("")) {
						savePath = savePath + "/" + addSavePath;
						filePath = filePath + "/" + addSavePath;
					}

					if(savePath.indexOf("../") > 0) {
						savePath = savePath.replaceAll("../", "");
						filePath = filePath + "/" + addSavePath;
					}
					realFileName = item.getName();  // 원래 파일명을 가져온다

					if(realFileName.indexOf("\\") != -1) {
						realFileName = realFileName.substring(realFileName.lastIndexOf("\\") + 1, realFileName.length());
					}

					File fileUploadPath = new File(savePath);

					if(!fileUploadPath.exists()) {
						if(!fileUploadPath.mkdirs()) {
							log.error("mkdirs error");
						}

					}

					fileExt = FilenameUtils.getExtension(realFileName);
					if(!checkExt(conf, pathKey, fileExt.toLowerCase())) {
						errorMsg = URLEncoder.encode("허용되지 않는 확장자 : " + fileExt, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
						//errorMsg = "허용되지 않는 확장자 : " + fileExt;
					}
					if(!checkSize(conf, pathKey, item.getSize())) {
						errorMsg = URLEncoder.encode("파일 사이즈 초과 : " + (item.getSize() / 1024) + " KB", "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
						//errorMsg = "파일 사이즈 초과 : " + (item.getSize() / 1024) + " KB";
					}
					if(errorMsg == null) {
						saveFileName = getCreateFileName(realFileName, useRealName); // 저장할 파일명을 가져온다

						if(saveFileName.endsWith(".jsp")) {
							saveFileName = "";
						}

						File savFile = new File(savePath, saveFileName);
//						System.out.println("saveFile path : "+ savFile.getAbsolutePath());
						item.write(savFile); // 실제파일 저장

						JSONObject jsono = new JSONObject();
						jsono.put("fileInfo", URLEncoder.encode(realFileName + "|" + saveFileName + "|" + fileExt + "|" + item.getSize() + "|" + filePath, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~"));
						jsono.put("realFileName", URLEncoder.encode(realFileName, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~"));
						jsono.put("fileName", saveFileName);
						jsono.put("fileSize", item.getSize());
						jsono.put("fileType", fileExt);
						jsono.put("filePath", filePath);
						json.add(jsono);
					} else {
						JSONObject jsono = new JSONObject();
						jsono.put("errorMsg", errorMsg);
						json.add(jsono);
					}
				}
			}
		} catch(FileUploadException e) {
			log.error("FileUpload error");
			//writer.write("FileUpload error");
			if ( writer != null ) {
				writer.flush();
				writer.close();
			}
		} catch(Exception e) {
			log.error("FileUpload error");
			//writer.write("FileUpload error");
			if ( writer != null ) {
				writer.flush();
				writer.close();
			}
		} finally {
			//log.error("FileUpload error");
			writer.write(json.toString());
			if ( writer != null ) {
				writer.flush();
				writer.close();
			}
		}

	}

	private String getSuffix(String filename) {
		String suffix = "";
		int pos = filename.lastIndexOf('.');
		if(pos > 0 && pos < filename.length() - 1) {
			suffix = filename.substring(pos + 1);
		}
		log.debug("suffix: " + suffix);
		return suffix;
	}

	/**
	 * 허용된 파일 사이즈인지 체크
	 * 
	 * @param conf
	 * @param pathKey
	 * @param fileSize
	 * @return
	 */
	private boolean checkSize(Configuration conf, String pathKey, Long fileSize) {
		Long maxSize = conf.getLong(pathKey + ".SIZE", 0);

		if(maxSize > 0 && maxSize < fileSize) {
			return false;
		}

		return true;
	}

	/**
	 * 인증된 사용자 여부 체크.
	 * 
	 * @param conf
	 * @param pathKey
	 * @param request
	 * @return
	 */
	private boolean checkAuth(Configuration conf, String pathKey, HttpServletRequest request) {
		String authYn = conf.getString(pathKey + ".AUTH", "N");
		if("Y".equals(authYn)) {
			// 로그인 체크
			if(SessionUtil.isLogin()) {
				return true;
			} else {
				return false;
			}
		}

		return true;
	}

	/**
	 * 파일 확장자 체크.
	 * 
	 * @param conf
	 * @param pathKey
	 * @param fileExt
	 * @return
	 */
	private boolean checkExt(Configuration conf, String pathKey, String fileExt) {
		fileExt = fileExt.toLowerCase();
		String[] userExts = conf.getStringArray(pathKey + ".EXT");

		if(userExts != null) {
			if(userExts.length > 0 && userExts[0].equals("*")) {
			} else {
				Boolean fileChk = false;
				for(String temp : userExts) {
					if(temp.indexOf(fileExt) > -1) {
						fileChk = true;
						break;
					}
				}

				return fileChk;
			}
		} else {
			String[] excludeExts = conf.getStringArray("FILE.EXCLUDE.EXT");
			String[] includeExts = conf.getStringArray("FILE.INCLUDE.EXT");

			if(excludeExts != null) {
				Boolean fileChk = false;
				for(String temp : excludeExts) {
					if(temp.indexOf(fileExt) > -1) {
						fileChk = true;
						break;
					}
				}

				return fileChk;
			} else if(includeExts != null) {
				Boolean fileChk = false;
				for(String temp : includeExts) {
					if(temp.indexOf(fileExt) > -1) {
						fileChk = true;
						break;
					}
				}

				return fileChk;
			}
		}

		return true;
	}

	/**
	 * 실제 저장될 파일이름을 생성한다.
	 * 
	 * @param realFileName : 실제 파일 이름
	 * @param useRealName : "Y" - 실제 이름사용, "N" - 변경된 이름사용
	 * @return 변경된 파일명
	 */
	private String getCreateFileName(String realFileName, String useRealName) {
		String ext = null; // 확장자
		String newFileName = null; // 변경된 FileName
		int lastIdx = -1; // 확장자까지 index 번호

		if("Y".equals(useRealName)) {
			// 실제 이름 사용
			return realFileName;
		} else {
			// 실제 이름을 사용하지 않고 생성된 이름 사용
			if(realFileName != null) {
				lastIdx = realFileName.lastIndexOf(".");
				if(lastIdx != -1) {
					ext = realFileName.substring(lastIdx);
				} else {
					ext = "";
				}
			}
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				log.error("getCreateFileName error");
			}
			newFileName = System.currentTimeMillis() + ext;
			return newFileName;
		}
	}

	/**
	 * HttpServletResponse 헤더 정보를 세팅한다.
	 * 
	 * @param request 요청 객체.
	 * @param response 응답 객체.
	 * @param fileName 파일 명
	 * @param contentLength 파일 사이즈.
	 * @throws UnsupportedEncodingException 인코딩 예외 발생 처리 객체.
	 */
	private void setResponseHeader(HttpServletRequest request, HttpServletResponse response, String fileName, int contentLength) throws UnsupportedEncodingException {
		
		String sUserAgent = request.getHeader("USER-AGENT");

		if(contentLength != -1) {
			response.setContentLength(contentLength);
		}

		if(sUserAgent.indexOf("MSIE 5.5") != -1) {
			response.setHeader("Content-Disposition", "filename=\"" + "utf-8" + "\"" + fileName + "\";");
		} else {
			boolean isFirefox = (sUserAgent.toLowerCase().indexOf("firefox") != -1) ? true : false;

			if(isFirefox) {
				response.setHeader("Content-Disposition", "attachment; filename=" + "\"=?" + "utf-8" + "?Q?" + URLEncoder.encode(fileName, "UTF-8") + "?=\";");
			} else {
				response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", " ") + "\"");
			}
		}

		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
	}
}
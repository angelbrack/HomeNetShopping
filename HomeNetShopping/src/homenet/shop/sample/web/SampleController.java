package homenet.shop.sample.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
//import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.sf.json.JSONObject;

/**
 * <p>샘플 Controller</p>
 *
 * <ul>
 * <li>Created by JaeduckEum, 2018. 7. 25.</li>
 * </ul>
 */
//@Controller
public class SampleController
{
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CommonAttachFileService commonAttachFileService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	@Qualifier("AdminProgramConnLogServiveImpl")
	private AdminProgramConnLogServive adminProgramConnLogServive;
	
	/**
	 * <pre>SAMPLE화면 호출</pre>
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/sample/sampleView")
	public String sampleView (Model model) {
		return CommonConstants.TILES_BACKOFFICE + "/sample/sampleView";
	}
	
	/**
	 * <pre>SAMPLE IFRAME 화면 호출</pre>
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/sampleIFrameView")
	public String sampleIFrameView (Model model)
	{
		return CommonConstants.TILES_BACKOFFICE + "/sample/sampleIFrameView";
	}
	
	/**
	 * <pre>SAMPLE SNS 화면 호출</pre>
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/sample/sampleSnsView")
	public String sampleISnsView (Model model, @ModelAttribute("searchVO") SampleVO paramVO, 
			@RequestParam("jsonText") String jsonText, @RequestParam("desc1") String desc1, HttpServletRequest request)
	{
		
		String jsonText1 = (String)request.getParameter("jsonText");
		
		// Kakao App ID
		String kakaoAppId	= env.getProperty("Kakao.app.id");
		model.addAttribute("kakaoAppId", 		kakaoAppId);
		
		// SERVER HTTP URL
		String serverHttpUrl	= env.getProperty("server.http.url");
		model.addAttribute("serverHttpUrl", 		serverHttpUrl);
				
		return CommonConstants.TILES_BACKOFFICE + "/sample/sampleSnsView";
	}
	
	/**
	 * <pre>SAMPLE 파일업로드/다운로드 화면 호출</pre>
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/sample/sampleFileView")
	public String sampleIFileView (Model model, @ModelAttribute("searchVO") CommonAttachFileVO paramVO)
	{
		Integer attachFileNo	= paramVO.getAttachFileNo();
		
		List<CommonAttachFileDtlVO> fileList = commonAttachFileService.selectAttachFileDtlList(paramVO);
		String castFileList	= "";
		try {
			castFileList = Casting.listToJSonString(fileList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("attachFileNo", 			attachFileNo);
		model.addAttribute("fileList", 				castFileList);
		
		return CommonConstants.TILES_BACKOFFICE + "/sample/sampleFileView";
	}
	
	/**
	  * 목적 		: 첨부파일 저장 처리
	  * 매개변수 	: ModelMap 
	  * 매개변수 	: NWrk300VO 
	  * 매개변수 	: HttpServletRequest 
	  * 반환값 	: String
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = CommonConstants.URI_SECURED_API + "/sample/sampleFileSave" )
	public @ResponseBody ExecutionContext<Map<String, Object>> sampleIFileSave(Model model, @RequestBody SampleVO paramVO) {
		
		String resultMsg 							= "";
		String completeYn 							= "Y";
		
		int result									= 0;
		
		paramVO.setRegId(SessionUtil.getAdminNo());
		paramVO.setUpdId(SessionUtil.getAdminNo());
		
		// 에러 처리를 위한 Exception 처리
		/*if ( 1 == 1 )
			throw new YtException(YtReturnCodeSpec.ERR_FRWK_DAO.code());*/
		
		CommonAttachFileVO attachFileVO	= new CommonAttachFileVO();
		attachFileVO.setAttachFileNo(paramVO.getAttachFileNo());
		attachFileVO.setAddFileList(paramVO.getAddFileList());
		attachFileVO.setRegId(paramVO.getRegId());
		attachFileVO.setUpdId(paramVO.getUpdId());
		
		// 저장
		result = commonAttachFileService.saveAttachFile(attachFileVO);
		if("I".equals(paramVO.getCmd())) {
			if(result > 0) {
				//resultMsg 	= YtDefaultMessageCodeSpec.MSG_DEFAULT_CREATE_SUCC;
				resultMsg	= messageSource.getMessage("operation.default.msg.create.succ", null, null);
				
				completeYn	= "Y";
			} else {
				resultMsg 	= messageSource.getMessage("operation.default.msg.create.fail", null, null);
				completeYn	= "N";
			}
		} else {
			if(result > 0) {
				resultMsg 	= messageSource.getMessage("operation.default.msg.modify.succ", null, null);
				completeYn	= "Y";
			} else {
				resultMsg 	= messageSource.getMessage("operation.default.msg.modify.fail", null, null);
				completeYn	= "N";
			}
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("resultMsg", 		resultMsg);
		resultMap.put("completeYn", 	completeYn);
		resultMap.put("attachFileNo", 	attachFileVO.getAttachFileNo());
		
		return this.getSuccResultContext(resultMap);
	}
	
	/**
	 * <pre>SAMPLE 파일업로드 호출(멀티)</pre>
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/sample/sampleMultiFileView")
	public String sampleMultiFileView(Model model, @ModelAttribute("searchVO") SampleVO paramVO)
	{
		Integer attachFileNo	= paramVO.getAttachFileNo();
		
		CommonAttachFileVO commonAttachFileVO	= null;
		
		commonAttachFileVO = new CommonAttachFileVO();
		
		// 첨부파일 1
		commonAttachFileVO.setAttachFileNo(paramVO.getAttachFileNo1());
		List<CommonAttachFileDtlVO> fileList1 = commonAttachFileService.selectAttachFileDtlList(commonAttachFileVO);
		
		// 첨부파일 2
		commonAttachFileVO.setAttachFileNo(paramVO.getAttachFileNo2());
		List<CommonAttachFileDtlVO> fileList2 = commonAttachFileService.selectAttachFileDtlList(commonAttachFileVO);
		
		String castFileList1	= "";
		String castFileList2	= "";
		try {
			castFileList1 = Casting.listToJSonString(fileList1);
			castFileList2 = Casting.listToJSonString(fileList2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("fileList1", 			castFileList1);
		model.addAttribute("fileList2", 			castFileList2);
		
		return CommonConstants.TILES_BACKOFFICE + "/sample/sampleMultiFileView";
	}
	
	/**
	  * 목적 		: 첨부파일 저장 처리 - 다중
	  * 매개변수 	: ModelMap 
	  * 매개변수 	: NWrk300VO 
	  * 매개변수 	: HttpServletRequest 
	  * 반환값 	: String
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = CommonConstants.URI_SECURED_API + "/sample/sampleMultiFileSave" )
	public @ResponseBody ExecutionContext<Map<String, Object>> sampleMultiFileSave(Model model, @RequestBody SampleVO paramVO) {
		
		String resultMsg 							= "";
		String completeYn 							= "Y";
		
		int result									= 0;
		
		paramVO.setRegId(SessionUtil.getAdminNo());
		paramVO.setUpdId(SessionUtil.getAdminNo());
		
		// 에러 처리를 위한 Exception 처리
		/*if ( 1 == 1 )
			throw new YtException(YtReturnCodeSpec.ERR_FRWK_DAO.code());*/
		
		// 첨부파일를 멀티로 저장할때는 트랙잭션 처리때문에 서비스 단에서 아래 로직를 
		// 추가해서 각 업무단에서 처리해야 함.
		// 현재는 샘플로 만들어서 컨트롤러에서 처리하게 만듬.   
		
		// 첨부파일 1
		CommonAttachFileVO attachFileVO1	= new CommonAttachFileVO();
		attachFileVO1.setAttachFileNo(paramVO.getAttachFileNo1());
		attachFileVO1.setAddFileList(paramVO.getAddFileList1());
		attachFileVO1.setRegId(paramVO.getRegId());
		attachFileVO1.setUpdId(paramVO.getUpdId());
		
		// 첨부파일 1 저장
		result += commonAttachFileService.saveAttachFile(attachFileVO1);
		
		// 첨부파일 2
		CommonAttachFileVO attachFileVO2	= new CommonAttachFileVO();
		attachFileVO2.setAttachFileNo(paramVO.getAttachFileNo2());
		attachFileVO2.setAddFileList(paramVO.getAddFileList2());
		attachFileVO2.setRegId(paramVO.getRegId());
		attachFileVO2.setUpdId(paramVO.getUpdId());
		
		// 첨부파일 2 저장
		result += commonAttachFileService.saveAttachFile(attachFileVO2);
		
		
		if("I".equals(paramVO.getCmd())) {
			if(result > 0) {
				//resultMsg 	= YtDefaultMessageCodeSpec.MSG_DEFAULT_CREATE_SUCC;
				resultMsg	= messageSource.getMessage("operation.default.msg.create.succ", null, null);
				
				completeYn	= "Y";
			} else {
				resultMsg 	= messageSource.getMessage("operation.default.msg.create.fail", null, null);
				completeYn	= "N";
			}
		} else {
			if(result > 0) {
				resultMsg 	= messageSource.getMessage("operation.default.msg.modify.succ", null, null);
				completeYn	= "Y";
			} else {
				resultMsg 	= messageSource.getMessage("operation.default.msg.modify.fail", null, null);
				completeYn	= "N";
			}
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("resultMsg", 	resultMsg);
		resultMap.put("completeYn", completeYn);
		
		return this.getSuccResultContext(resultMap);
	}
	
	/**
	  * 목적 		: 첨부파일 저장 처리
	  * 매개변수 	: ModelMap 
	  * 매개변수 	: NWrk300VO 
	  * 매개변수 	: HttpServletRequest 
	  * 반환값 	: String
	  * 개정이력 	: 없음
	  */
	@RequestMapping(value = CommonConstants.URI_SECURED_API + "/admin/sampleJsonXss" )
	public @ResponseBody ExecutionContext<Map<String, Object>> sampleJsonXss(Model model, @RequestBody SampleVO paramVO) {
		
		
		String jsonText = paramVO.getJsonText();
		logger.debug("jsonText=["+jsonText+"]");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("jsonText", 	jsonText);
		
		return this.getSuccResultContext(resultMap);
		
	}
	
	/**
	 * <pre>SAMPLE 파일업로드/다운로드 화면 호출</pre>
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/sample/sampleFileWarView")
	public String sampleIFileWarView (Model model, @ModelAttribute("searchVO") CommonAttachFileVO paramVO)
	{
		
		return CommonConstants.TILES_BACKOFFICE + "/sample/sampleFileWarView";
	}
	
	/**
	 * <pre>SAMPLE IFRAME 화면 호출</pre>
	 * @param model
	 * @return
	 */
	@GetMapping("/mobile/sampleFrameView")
	public String sampleIFrameView2 (Model model)
	{
		return CommonConstants.TILES_BACKOFFICE + "/sample/sampleFrameView";
	}
	
	/**
	 * <pre>SAMPLE 암호화 화면 호출</pre>
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/sample/sampleEncryptView")
	public String sampleIEncDecView (Model model) {
		
		String paramString = "";
		
		try {
    		AES256Util cipher = new AES256Util();
	    	
	    	String planText 	= "";
	    	String cmpsCd		= "0000046"; 					// 캠퍼스_코드
	    	String cmpsNm		= "가톨릭대학교";				// 캠퍼스_명
	    	String cmpsDtlNm	= "성심/성의/성신캠퍼스";		// 캠퍼스_상세_명
	    	String subjctNm		= "경영학과"; 					// 학과_명
	    	String level		= "레벨1";						// 레벨
	    	String ci			= "0000000051";					// CI
	    	String mdn			= "01011112379";				// MDN
	    	String cmps			= "CMPS";						// 고정값
	    	String token		= cmpsCd + subjctNm + level + ci + mdn + cmps;
	    	
	    	// 양반향 암호와
	    	String encCmpsCd	= cipher.encrypt(cmpsCd);
	    	String encCmpsNm	= cipher.encrypt(cmpsNm);
	    	String encCmpsDtlNm	= cipher.encrypt(cmpsDtlNm);
	    	String encSubjctNm	= cipher.encrypt(subjctNm);
	    	String encLevel		= cipher.encrypt(level);
	    	
	    	// 단방향 암호화(해싱)
	    	String encToken	= SHA256Util.encrypt(token);
	    			
	    	
	    	JSONObject jsonObject = new JSONObject();
	    	jsonObject.put("cmpsCd", 		encCmpsCd);
	    	jsonObject.put("cmpsNm", 		encCmpsNm);
	    	jsonObject.put("cmpsDtlNm", 	encCmpsDtlNm);
	    	jsonObject.put("subjctNm", 		encSubjctNm);
	    	jsonObject.put("level", 		encLevel);
	    	jsonObject.put("token", 		encToken);
	    	
	    	paramString = jsonObject.toString();
	    	
		} catch (Exception e) {
            e.printStackTrace();
        }
		
		model.addAttribute("paramString", paramString);
		
		return CommonConstants.TILES_BACKOFFICE + "/sample/sampleEncryptView";
	}
	
	/**
	 * <pre>SAMPLE 복호화 화면 호출</pre>
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/sample/sampleDecryptView")
	public String sampleIDecryptView (Model model, HttpServletRequest request)
	{
		
		String encParam = (String)request.getParameter("param");
		try {
			encParam		= java.net.URLDecoder.decode(encParam, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonEncObject = jsonObject.fromObject(encParam);
		
		// 암호화된 데이터
		String encCmpsCd 		= jsonEncObject.getString("cmpsCd");		// 캠퍼스_코드
		String encCmpsNm 		= jsonEncObject.getString("cmpsNm");		// 캠퍼스_명
		String encCmpsDtlNm 	= jsonEncObject.getString("cmpsDtlNm");		// 캠퍼스_상세_명
    	String encSubjctNm 		= jsonEncObject.getString("subjctNm");		// 학과_명
    	String encLevel 		= jsonEncObject.getString("level");			// 레벨
    	String encToken 		= jsonEncObject.getString("token");			// token(해싱 데이터)
    	
    	String cmpsCd			= "";	// 캠퍼스_코드
    	String cmpsNm			= "";	// 캠퍼스_명
    	String cmpsDtlNm		= "";	// 캠퍼스_상세_명
    	String subjctNm			= "";	// 학과_명	
    	String level			= "";	// 레벨
    	
    	try {
    		AES256Util cipher = new AES256Util();
    		
    		cmpsCd		= cipher.decrypt(encCmpsCd);
    		cmpsNm		= cipher.decrypt(encCmpsNm);
    		cmpsDtlNm	= cipher.decrypt(encCmpsDtlNm);
    		subjctNm	= cipher.decrypt(encSubjctNm);
    		level		= cipher.decrypt(encLevel);
    		
    	} catch (Exception e) {
            e.printStackTrace();
        }
		
    	model.addAttribute("cmpsCd", 		cmpsCd		);
    	model.addAttribute("cmpsNm", 		cmpsNm		);
    	model.addAttribute("cmpsDtlNm", 	cmpsDtlNm	);
    	model.addAttribute("subjctNm", 		subjctNm	);
    	model.addAttribute("level", 		level		);
    	
		return CommonConstants.TILES_BACKOFFICE + "/sample/sampleDecryptView";
	}
	
	/**
	 * <pre>SAMPLE화면 호출</pre>
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/sample/sampleDatepickerView")
	public String sampleDatepicker (Model model) {
		return CommonConstants.TILES_BACKOFFICE + "/sample/sampleDatepickerView";
	}
	
	/**
	 * <pre>SAMPLE Excel Upload 화면 호출</pre>
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/sample/sampleExcelUploadView")
	public String sampleExcelUploadView (Model model) {
		return CommonConstants.TILES_BACKOFFICE + "/sample/sampleExcelUploadView";
	}
	
	/**
	 * <pre>SAMPLE Excel Upload 화면 호출</pre>
	 * 
	 * @param  : BannerVO paramVO
	 * @param  : ModelMap model
	 * @param  : HttpServletRequest request
	 * @param  : HttpServletResponse response
	 * @return : String 
	 */
	/*@CrossOrigin(origins = "*")*/
	@RequestMapping(value="/admin/sample/sampleExcelUploadBackup")
	public void sampleExcelUploadBackup(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("filePathName");
			
			PopupVO popupVO = new PopupVO();
			//BeanUtils.copyProperties(paramVO, popupVO); // bind 완료된 Property 를 사용될 Property 로 Copy
			
			String excelPath = file.getOriginalFilename();
			
			String fileForm = excelPath.substring(excelPath.lastIndexOf("."), excelPath.length());
			
			 /* 엑셀 파일 설정 */
	        //CommonsMultipartFile file = paramVO.getFilePathName();
	        File excelFile = File.createTempFile("excel", fileForm);
	        file.transferTo(excelFile);
	        
	        
	        List<PopupVO> list = excelParse(excelFile.getAbsolutePath(), popupVO);
	        
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
	
	/**
	 * <pre>SAMPLE Excel Upload 화면 호출</pre>
	 * 
	 * @param  : BannerVO paramVO
	 * @param  : ModelMap model
	 * @param  : HttpServletRequest request
	 * @param  : HttpServletResponse response
	 * @return : String 
	 */
	/*@CrossOrigin(origins = "*")*/
	@RequestMapping(value="/admin/sample/sampleExcelUpload")
	public void sampleExcelUpload (@RequestParam("file") MultipartFile multipartFile) {
		
		try {
			
			PopupVO popupVO = new PopupVO();
			
			String excelPath = multipartFile.getOriginalFilename();
			
			String fileForm = excelPath.substring(excelPath.lastIndexOf("."), excelPath.length());
			
			// 엑셀 파일 설정 
	        File excelFile = File.createTempFile("excel", fileForm);
	        multipartFile.transferTo(excelFile);
	        
	        
	        List<PopupVO> list = excelParse(excelFile.getAbsolutePath(), popupVO);
	        
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
	
	/**
	 * <pre>SAMPLE Excel Upload 화면 호출</pre>
	 * 
	 * @param  : BannerVO paramVO
	 * @param  : ModelMap model
	 * @param  : HttpServletRequest request
	 * @param  : HttpServletResponse response
	 * @return : String 
	 */
	/*@CrossOrigin(origins = "*")*/
	@RequestMapping(value = CommonConstants.URI_SECURED_API + "/sample/sampleExcelUpload")
	public @ResponseBody ExecutionContext<Map<String, Object>> sampleExcelUploadAjax (
			@RequestParam("filePathName") MultipartFile multipartFile, @RequestParam("popupId") String popupId
			, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		String resultMsg 							= "성공";
		String completeYn 							= "Y";
		
		try {
			
			PopupVO popupVO = new PopupVO();
			
			logger.debug("popupId=["+popupId+"]");
			
			String excelPath = multipartFile.getOriginalFilename();
			
			String fileForm = excelPath.substring(excelPath.lastIndexOf("."), excelPath.length());
			
			// 엑셀 파일 설정 
	        File excelFile = File.createTempFile("excel", fileForm);
	        multipartFile.transferTo(excelFile);
	        
	        
	        List<PopupVO> list = excelParse(excelFile.getAbsolutePath(), popupVO);
	        
		} catch (Exception ex) {
			ex.printStackTrace();
			
			resultMsg	= ex.getMessage();
			
			completeYn	= "N";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("resultMsg", 	resultMsg);
		resultMap.put("completeYn", completeYn);
		
		return this.getSuccResultContext(resultMap);
	}
	
	/**
     * 엑셀 파일 Parsing 처리
     *
     *
     * @param absolutePath
     * @return List<PopupVO>
     */
    @SuppressWarnings("unchecked")
    public List<PopupVO> excelParse(String excelPath, PopupVO popupVO) throws Exception {

        File excelFile = new File(excelPath); // Parsing 할 데이터
        Class clazz = PopupVO.class; // 생성될 Bean Class

        List<PopupVO> list = null;

        // 매핑정보
        //String[] mapping = new String[] {"popupNm", "dircExpoStartDay", "dircExpoEndDay", "dircExpoYn", "dircExpoPrior"};
        String[] mapping = new String[] {"popupId", "popupNm", "dircExpoStartDt", "dircExpoEndDt", 
        		"dircExpoYn", "dircExpoPrior", "cmpsNm", "pgmClsfNm", "buttonText", "moveUrl"};

        try {

            list = ExcelUtil.parse(excelFile, clazz, mapping, 2);

            logger.debug("--->>Excel Parsing Total COUNT : " + list.size());
            int i = 0;
            for ( PopupVO popupVO2 : list ) {
            	logger.debug("popupId["+i+"]--->>"+popupVO2.getPopupId());
            	logger.debug("popupNm["+i+"]--->>"+popupVO2.getPopupNm());
            	logger.debug("dircExpoStartDt["+i+"]--->>"+popupVO2.getDircExpoStartDt());
            	logger.debug("dircExpoEndDt["+i+"]--->>"+popupVO2.getDircExpoEndDt());
            	logger.debug("dircExpoYn["+i+"]--->>"+popupVO2.getDircExpoYn());
            	logger.debug("dircExpoPrior["+i+"]--->>"+popupVO2.getDircExpoPrior());
            	logger.debug("cmpsNm["+i+"]--->>"+popupVO2.getCmpsNm());
            	logger.debug("pgmClsfNm["+i+"]--->>"+popupVO2.getPgmClsfNm());
            	logger.debug("buttonText["+i+"]--->>"+popupVO2.getButtonText());
            	logger.debug("moveUrl["+i+"]--->>"+popupVO2.getMoveUrl());
            	
            	i++;
            }
        } catch (IOException e) {
        	e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return list;
    }
    
    /**
	  * 프로그램 접속 로그 저장 처리
	  *
	  * @param 		: AdminProgramConnLogVO paramVO
	  * @param 		: Model model
	  * @param 		: HttpServletRequest request
	  * @param 		: HttpServletResponse response
	  * @return 	: ExecutionContext<Map<String, Object>>
	  */
	@RequestMapping(value = CommonConstants.URI_SECURED_API + "/sample/sampleAdminProgramConnLogSave" )
	public @ResponseBody ExecutionContext<Map<String, Object>> sampleAdminProgramConnLogSave(@RequestBody AdminProgramConnLogVO paramVO, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		String resultMsg 							= "";
		String completeYn 							= "Y";
		
		int result									= 0;
		
		paramVO.setAdminNo(SessionUtil.getAdminNo());	// 접속 계정
		paramVO.setConnIp(CommonUtil.getRemoteAddr());	// 접속자 정보(IP)
		/*
		 * 수행 업무(열람, 수정, 삭제, 인쇄 등의 처리내역을 의미하며 해당 정보주체를 식별할 수 있는 정보가 포함되어야 함)
		 */
		paramVO.setExecPtcr("OOO 회원 열람");
		
		// 저장
		result = adminProgramConnLogServive.insertAdminProgramConnLog(paramVO);
		if(result > 0) {
			resultMsg	= messageSource.getMessage("operation.default.msg.create.succ", null, null);
			
			completeYn	= "Y";
		} else {
			resultMsg 	= messageSource.getMessage("operation.default.msg.create.fail", null, null);
			completeYn	= "N";
		}
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("resultMsg", 	resultMsg);
		resultMap.put("completeYn", completeYn);
		
		return this.getSuccResultContext(resultMap);
	}
}

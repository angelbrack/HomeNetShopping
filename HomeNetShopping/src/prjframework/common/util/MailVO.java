package prjframework.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MailVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6097532704822882868L;
	
	private String mailFrom;			// 발송자이메일
	private String mailTo;				// 수신자이메일
	private String mailCc;				// 참조이메일
	private String mailSubject;			// 이메일제목명
	private String mailContent;			// 이메일본문내용
	private String mailSmyContent;		// 이메일요약내용
	private String templateName;		// 템플릿명
	
	private String emailSendDttm;		// 발송시간
	private String emailTempCd;			// 이메일유형번호
	private String emailSendYn;			// 이메일발송여부
	private String fwpUserNo;			// 발송자사용자번호
	private String emailFailRsnCn;		// 이메일실패사유내용
	private String rvpnUserNo;			// 수신자사용자번호
	private String wrtPnIp;				// 작성자IP
	private Map<String, Object> textParams;
	private List<Object> userNoList;
	
	private String userNo;
	private String email;
	
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getMailCc() {
		return mailCc;
	}
	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailContent() {
		return mailContent;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	
	public String getMailSmyContent() {
		return mailSmyContent;
	}
	public void setMailSmyContent(String mailSmyContent) {
		this.mailSmyContent = mailSmyContent;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getEmailSendDttm() {
		return emailSendDttm;
	}
	public void setEmailSendDttm(String emailSendDttm) {
		this.emailSendDttm = emailSendDttm;
	}
	public String getEmailTempCd() {
		return emailTempCd;
	}
	public void setEmailTempCd(String emailTempCd) {
		this.emailTempCd = emailTempCd;
	}
	public String getEmailSendYn() {
		return emailSendYn;
	}
	public void setEmailSendYn(String emailSendYn) {
		this.emailSendYn = emailSendYn;
	}
	public String getFwpUserNo() {
		return fwpUserNo;
	}
	public void setFwpUserNo(String fwpUserNo) {
		this.fwpUserNo = fwpUserNo;
	}
	public String getEmailFailRsnCn() {
		return emailFailRsnCn;
	}
	public void setEmailFailRsnCn(String emailFailRsnCn) {
		this.emailFailRsnCn = emailFailRsnCn;
	}
	public String getRvpnUserNo() {
		return rvpnUserNo;
	}
	public void setRvpnUserNo(String rvpnUserNo) {
		this.rvpnUserNo = rvpnUserNo;
	}
	public String getWrtPnIp() {
		return wrtPnIp;
	}
	public void setWrtPnIp(String wrtPnIp) {
		this.wrtPnIp = wrtPnIp;
	}
	public Map<String, Object> getTextParams() {
		return textParams;
	}
	public void setTextParams(Map<String, Object> textParams) {
		this.textParams = textParams;
	}
	public List<Object> getUserNoList() {
		List<Object> temp = null;
		
		if(this.userNoList != null){
			temp = new ArrayList<Object>();
			temp.addAll(this.userNoList);
		}
		
		return temp;
	}
	public void setUserNoList(List<Object> userNoList) {
		if(userNoList != null){
			List<Object> temp = new ArrayList<Object>();
			temp.addAll(userNoList);
			this.userNoList = temp;
		}else{
			this.userNoList = null;
		}
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}

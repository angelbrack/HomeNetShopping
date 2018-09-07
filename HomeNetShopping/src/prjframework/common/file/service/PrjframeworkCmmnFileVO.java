package prjframework.common.file.service;

import java.io.Serializable;

/**-----------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------
 * 소속 : 고려대학교
 * 수정일 : 2018.08.09
 * 수정자 : 엄재덕
 * 수정내용 : 최초생성
 *------------------------------------------------------------------------
 */
public class PrjframeworkCmmnFileVO implements Serializable {

	private static final long serialVersionUID = -7319353550822845685L;
	
	private String atchmnflNo;
	private String[] addFileList;
	private String atchmnflSn;
	private String atchmnflNm;
	private String atchmnflStreNm;
	private String atchmnflMg;
	private String wrtPnNo;
	private String wrtPnIp;		
	
	public String getAtchmnflNo() {
		return atchmnflNo;
	}
	public void setAtchmnflNo(String atchmnflNo) {
		this.atchmnflNo = atchmnflNo;
	}
	public String[] getAddFileList() {
		if (this.addFileList == null){ 
			return null;
		}else{
			String[] temp = new String[this.addFileList.length];
			System.arraycopy(this.addFileList, 0, temp, 0, this.addFileList.length);
			return temp;
		}
	}
	public void setAddFileList(String[] addFileList) {
		if(addFileList != null){
			this.addFileList = new String[addFileList.length];
			
			for (int i = 0; i < addFileList.length; ++i){
				this.addFileList[i] = addFileList[i];
			}
		}else{
			this.addFileList = null;
		}
	}
	public String getAtchmnflSn() {
		return atchmnflSn;
	}
	public void setAtchmnflSn(String atchmnflSn) {
		this.atchmnflSn = atchmnflSn;
	}
	public String getAtchmnflNm() {
		return atchmnflNm;
	}
	public void setAtchmnflNm(String atchmnflNm) {
		this.atchmnflNm = atchmnflNm;
	}
	public String getAtchmnflStreNm() {
		return atchmnflStreNm;
	}
	public void setAtchmnflStreNm(String atchmnflStreNm) {
		this.atchmnflStreNm = atchmnflStreNm;
	}
	public String getAtchmnflMg() {
		return atchmnflMg;
	}
	public void setAtchmnflMg(String atchmnflMg) {
		this.atchmnflMg = atchmnflMg;
	}
	public String getWrtPnNo() {
		return wrtPnNo;
	}
	public void setWrtPnNo(String wrtPnNo) {
		this.wrtPnNo = wrtPnNo;
	}
	public String getWrtPnIp() {
		return wrtPnIp;
	}
	public void setWrtPnIp(String wrtPnIp) {
		this.wrtPnIp = wrtPnIp;
	}
}

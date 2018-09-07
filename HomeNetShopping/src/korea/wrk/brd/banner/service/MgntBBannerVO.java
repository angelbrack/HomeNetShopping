package korea.wrk.brd.banner.service;

import prjframework.common.util.PagerVO;

public class MgntBBannerVO extends PagerVO {
	private static final long serialVersionUID = -8274004534207618049L;
	private String cmd;
	private String searchBanrKndc; 
	private String searchBanrSdt;
	private String searchBanrEdt;
	private String searchUseYn;
	private String searchNotcYn;
	private String searchBanrTitNm;
	private String[] addFileList;
	private String banrSeq;
	private String banrDc;
	private String banrKndc;
	private String banrTitNm;
	private String banrSdt;
	private String banrEdt;
	private String useYn;
	private String notcYn;
	private String linkTgtDc;
	private String linkUrl;
	private String apndFileNo;
	private String wrtPnNo;
	private String wrtDttm;
	private String updtPnNo;
	private String updtDttm;
	private String banrCnNm;
	
	
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getSearchBanrKndc() {
		return searchBanrKndc;
	}
	public void setSearchBanrKndc(String searchBanrKndc) {
		this.searchBanrKndc = searchBanrKndc;
	}
	public String getSearchBanrSdt() {
		return searchBanrSdt;
	}
	public void setSearchBanrSdt(String searchBanrSdt) {
		this.searchBanrSdt = searchBanrSdt;
	}
	public String getSearchBanrEdt() {
		return searchBanrEdt;
	}
	public void setSearchBanrEdt(String searchBanrEdt) {
		this.searchBanrEdt = searchBanrEdt;
	}
	public String getSearchUseYn() {
		return searchUseYn;
	}
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}
	public String getSearchNotcYn() {
		return searchNotcYn;
	}
	public void setSearchNotcYn(String searchNotcYn) {
		this.searchNotcYn = searchNotcYn;
	}
	public String getSearchBanrTitNm() {
		return searchBanrTitNm;
	}
	public void setSearchBanrTitNm(String searchBanrTitNm) {
		this.searchBanrTitNm = searchBanrTitNm;
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
		if (addFileList != null){ 
			this.addFileList = new String[addFileList.length];
			
			for (int i = 0; i < addFileList.length; ++i){
				this.addFileList[i] = addFileList[i];
			}
		}else{
			this.addFileList = null;
		}
	}
	public String getBanrSeq() {
		return banrSeq;
	}
	public void setBanrSeq(String banrSeq) {
		this.banrSeq = banrSeq;
	}
	public String getBanrDc() {
		return banrDc;
	}
	public void setBanrDc(String banrDc) {
		this.banrDc = banrDc;
	}
	public String getBanrKndc() {
		return banrKndc;
	}
	public void setBanrKndc(String banrKndc) {
		this.banrKndc = banrKndc;
	}
	public String getBanrTitNm() {
		return banrTitNm;
	}
	public void setBanrTitNm(String banrTitNm) {
		this.banrTitNm = banrTitNm;
	}
	public String getBanrSdt() {
		return banrSdt;
	}
	public void setBanrSdt(String banrSdt) {
		this.banrSdt = banrSdt;
	}
	public String getBanrEdt() {
		return banrEdt;
	}
	public void setBanrEdt(String banrEdt) {
		this.banrEdt = banrEdt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getNotcYn() {
		return notcYn;
	}
	public void setNotcYn(String notcYn) {
		this.notcYn = notcYn;
	}
	public String getLinkTgtDc() {
		return linkTgtDc;
	}
	public void setLinkTgtDc(String linkTgtDc) {
		this.linkTgtDc = linkTgtDc;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getApndFileNo() {
		return apndFileNo;
	}
	public void setApndFileNo(String apndFileNo) {
		this.apndFileNo = apndFileNo;
	}
	public String getWrtPnNo() {
		return wrtPnNo;
	}
	public void setWrtPnNo(String wrtPnNo) {
		this.wrtPnNo = wrtPnNo;
	}
	public String getWrtDttm() {
		return wrtDttm;
	}
	public void setWrtDttm(String wrtDttm) {
		this.wrtDttm = wrtDttm;
	}
	public String getUpdtPnNo() {
		return updtPnNo;
	}
	public void setUpdtPnNo(String updtPnNo) {
		this.updtPnNo = updtPnNo;
	}
	public String getUpdtDttm() {
		return updtDttm;
	}
	public void setUpdtDttm(String updtDttm) {
		this.updtDttm = updtDttm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getBanrCnNm() {
		return banrCnNm;
	}
	public void setBanrCnNm(String banrCnNm) {
		this.banrCnNm = banrCnNm;
	}


}

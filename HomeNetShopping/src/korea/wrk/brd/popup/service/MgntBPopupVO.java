package korea.wrk.brd.popup.service;

import prjframework.common.util.PagerVO;

public class MgntBPopupVO extends PagerVO {
	private static final long serialVersionUID = -8274004534207618049L;
	private String cmd;
	private String searchPopuDc;
	private String searchPopuSdt;
	private String searchPopuEdt;
	private String searchUseYn;
	private String searchNotcYn;
	private String searchPopuTitNm;
	private String[] addFileList;
	private String popuSeq;
	private String popuDc;
	private String popuTitNm;
	private String popuSdt;
	private String popuEdt;
	private String useYn;
	private String notcYn;
	private String linkTgtDc;
	private String linkUrl;
	private String apndFileNo;
	private String wrtPnNo;
	private String wrtDttm;
	private String updtPnNo;
	private String updtDttm;
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getSearchPopuSdt() {
		return searchPopuSdt;
	}
	public void setSearchPopuSdt(String searchPopuSdt) {
		this.searchPopuSdt = searchPopuSdt;
	}
	public String getSearchPopuEdt() {
		return searchPopuEdt;
	}
	public void setSearchPopuEdt(String searchPopuEdt) {
		this.searchPopuEdt = searchPopuEdt;
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
	public String getSearchPopuTitNm() {
		return searchPopuTitNm;
	}
	public void setSearchPopuTitNm(String searchPopuTitNm) {
		this.searchPopuTitNm = searchPopuTitNm;
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
	public String getPopuSeq() {
		return popuSeq;
	}
	public void setPopuSeq(String popuSeq) {
		this.popuSeq = popuSeq;
	}
	public String getPopuDc() {
		return popuDc;
	}
	public void setPopuDc(String popuDc) {
		this.popuDc = popuDc;
	}
	public String getPopuTitNm() {
		return popuTitNm;
	}
	public void setPopuTitNm(String popuTitNm) {
		this.popuTitNm = popuTitNm;
	}
	public String getPopuSdt() {
		return popuSdt;
	}
	public void setPopuSdt(String popuSdt) {
		this.popuSdt = popuSdt;
	}
	public String getPopuEdt() {
		return popuEdt;
	}
	public void setPopuEdt(String popuEdt) {
		this.popuEdt = popuEdt;
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
	public String getSearchPopuDc() {
		return searchPopuDc;
	}
	public void setSearchPopuDc(String searchPopuDc) {
		this.searchPopuDc = searchPopuDc;
	}

}

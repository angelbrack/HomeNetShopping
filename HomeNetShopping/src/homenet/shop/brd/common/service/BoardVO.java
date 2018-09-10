package homenet.shop.brd.common.service;

import prjframework.common.util.PagerVO;

/**
 * -----------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------------
 * 소속 : 고려대학교 경력개발센터
 * 수정일 : 2018.03.15
 * 수정자 : 엄재덕
 * 수정내용 : 최초생성
 * ------------------------------------------------------------------------
 */
public class BoardVO extends PagerVO {
	
	private static final long serialVersionUID = -8274004534207618049L;
	
	private String boardType;
	private String boardType2;
	//private String blbdId;
	//private String blbdNm;
	private String blbdDvCd;		// 게시판구분코드
	private String blbdDvCdNm;		// 게시판구분코드 명
	private String bdotCn;
	private String blbdCn;
	private String blbdFomDcNm;
	private String blbdFomDc;
	private String blbdTotalCnt;
	private String blbdDeleteCnt;
	private String wrtDttm;
	private String userNo;
	private String userNm;
	private String loginId;
	private String groupCodeId;
	private String codeId;
	private String codeNm;
	private String loginUseYn;
	private String apndFileNo;
	private String apndFileYn;
	private String apndFileCt;
	private String ntopYn;
	private String rplUseYn;
	private String pwdUseYn;
	private String ansDc;
	private String ansDcNm;
	private String ansUseYn;
	private String thumbUseYn;
	private String aspUseYn;
	private String notcTeUseYn;
	private String notcYnUseYn;
	private String toppNotcUseYn;
	private String mvpLinkUseYn;
	private String hmpgRegUseYn;
	private String clsUseYn;
	private String titLinkUseYn;
	private String bdltUseYn;
	private String ansSendCd;
	private String ansSendCdNm;
	private String newsBdotUseYn;
	private String rcmdUseYn;
	private String srchwrdUseYn;
	private String searchKey;
	private String searchNm;
	private String searchWrtPnNm;
	private String srchwrdNotcYn;
	private String searchLinkUrl;
	private String searchNtopYn;
	private String searchToppNotcYn;
	private String searchNotcYn;
	private String searchAnsYn;
	private String searchCls1Cd;
	private String searchCls2Cd;
	private String searchThClcd;
	private String searchWord;
	private String searchNotcSdt;
	private String searchNotcEdt;
	private String searchTgtDc ;
	private String[] addFileList;
	private String[] addFileList1;
	private String[] addFileList2;
	private String rnum;
	private String bdotSeq;
	private String hgrkBdotSeq;
	private String titNm;
	private String inqT;
	private String linkUrl;
	private String bdotPwd;
	private String toppNotcYn;
	
	private String smyInfoCn;		// 요약정보내용
	
	private String notcYn;			// 게시여부	
	private String notcSdt;			// 게시시작일자
	private String notcEdt;			// 게시종료일자
	private String mvpLinkYn;
	private String delYn;
	private String delDttm;
	private String delPnNo;
	private String mvpUrl;
	private String cls1Cd;
	private String cls1CdNm;
	private String cls2Cd;
	private String cls2CdNm;
	private String thClcd;
	private String wrtPnPnn;
	private String wrtPnTlno;
	private String wrtPnIp;
	private String wrtPnNo;
	private String updtPnNo;
	private String updtDttm;
	private String bdotCnNo;
	private String bdotRcmdNo;
	private String rcmdUserNo;
	private String rcmdIp;
	private String rcmdDttm;
	private String rplNo;
	private String rplCn;
	private String rplRcmdT;
	private String rplNrcmdT;
	private String srchwrdNo;
	private String srchwrd;
	private String srchwrdDc;
	private String srchwrdFc;
	private String listJspUrl;
	private String listUrl;
	private String listAjaxUrl;
	private String handleJspUrl;
	private String handleUrl;
	private String saveAjaxUrl;
	private String deleteAjaxUrl;
	private String cmd;
	private String rowNo;
	private String srchwrdCnt;
	private String bdotDc;
	private String blbdHistDc;
	private String inqTUseYn;
	private String cls1UseYn;
	private String cls2UseYn;
	private String cls3UseYn;
	private String cls1UseCd;
	private String cls2UseCd;
	private String cls3UseCd;
	private String bdltCn;
	private String thumbApndFileNo;
	private String ftptYn;
	private String bdotCn2;
	private String wrtPnNm;
	private String wrtPnEmail;
	private String ansYn;
	private String emailResvAgYn;
	private String wrtEmailUserNo;
	private String ansApndFileNo;
	private String userTc;
	private String tgtDc ;
	
	
	
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	
	/*public String getBlbdId() {
		return blbdId;
	}
	public void setBlbdId(String blbdId) {
		this.blbdId = blbdId;
	}
	public String getBlbdNm() {
		return blbdNm;
	}
	public void setBlbdNm(String blbdNm) {
		this.blbdNm = blbdNm;
	}*/
	
	
	public String getBoardType2() {
		return boardType2;
	}
	public void setBoardType2(String boardType2) {
		this.boardType2 = boardType2;
	}
	public String getBlbdDvCd() {
		return blbdDvCd;
	}
	public void setBlbdDvCd(String blbdDvCd) {
		this.blbdDvCd = blbdDvCd;
	}
	public String getBlbdDvCdNm() {
		return blbdDvCdNm;
	}
	public void setBlbdDvCdNm(String blbdDvCdNm) {
		this.blbdDvCdNm = blbdDvCdNm;
	}
	public String getBdotCn() {
		return bdotCn;
	}
	public void setBdotCn(String bdotCn) {
		this.bdotCn = bdotCn;
	}
	public String getBlbdCn() {
		return blbdCn;
	}
	public void setBlbdCn(String blbdCn) {
		this.blbdCn = blbdCn;
	}
	public String getBlbdFomDcNm() {
		return blbdFomDcNm;
	}
	public void setBlbdFomDcNm(String blbdFomDcNm) {
		this.blbdFomDcNm = blbdFomDcNm;
	}
	public String getBlbdFomDc() {
		return blbdFomDc;
	}
	public void setBlbdFomDc(String blbdFomDc) {
		this.blbdFomDc = blbdFomDc;
	}
	public String getBlbdTotalCnt() {
		return blbdTotalCnt;
	}
	public void setBlbdTotalCnt(String blbdTotalCnt) {
		this.blbdTotalCnt = blbdTotalCnt;
	}
	public String getBlbdDeleteCnt() {
		return blbdDeleteCnt;
	}
	public void setBlbdDeleteCnt(String blbdDeleteCnt) {
		this.blbdDeleteCnt = blbdDeleteCnt;
	}
	public String getWrtDttm() {
		return wrtDttm;
	}
	public void setWrtDttm(String wrtDttm) {
		this.wrtDttm = wrtDttm;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getGroupCodeId() {
		return groupCodeId;
	}
	public void setGroupCodeId(String groupCodeId) {
		this.groupCodeId = groupCodeId;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCodeNm() {
		return codeNm;
	}
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}
	public String getLoginUseYn() {
		return loginUseYn;
	}
	public void setLoginUseYn(String loginUseYn) {
		this.loginUseYn = loginUseYn;
	}
	public String getApndFileNo() {
		return apndFileNo;
	}
	public void setApndFileNo(String apndFileNo) {
		this.apndFileNo = apndFileNo;
	}
	public String getApndFileYn() {
		return apndFileYn;
	}
	public void setApndFileYn(String apndFileYn) {
		this.apndFileYn = apndFileYn;
	}
	public String getApndFileCt() {
		return apndFileCt;
	}
	public void setApndFileCt(String apndFileCt) {
		this.apndFileCt = apndFileCt;
	}
	public String getNtopYn() {
		return ntopYn;
	}
	public void setNtopYn(String ntopYn) {
		this.ntopYn = ntopYn;
	}
	public String getRplUseYn() {
		return rplUseYn;
	}
	public void setRplUseYn(String rplUseYn) {
		this.rplUseYn = rplUseYn;
	}
	public String getPwdUseYn() {
		return pwdUseYn;
	}
	public void setPwdUseYn(String pwdUseYn) {
		this.pwdUseYn = pwdUseYn;
	}
	public String getAnsDc() {
		return ansDc;
	}
	public void setAnsDc(String ansDc) {
		this.ansDc = ansDc;
	}
	public String getAnsDcNm() {
		return ansDcNm;
	}
	public void setAnsDcNm(String ansDcNm) {
		this.ansDcNm = ansDcNm;
	}
	public String getAnsUseYn() {
		return ansUseYn;
	}
	public void setAnsUseYn(String ansUseYn) {
		this.ansUseYn = ansUseYn;
	}
	public String getThumbUseYn() {
		return thumbUseYn;
	}
	public void setThumbUseYn(String thumbUseYn) {
		this.thumbUseYn = thumbUseYn;
	}
	public String getAspUseYn() {
		return aspUseYn;
	}
	public void setAspUseYn(String aspUseYn) {
		this.aspUseYn = aspUseYn;
	}
	public String getNotcTeUseYn() {
		return notcTeUseYn;
	}
	public void setNotcTeUseYn(String notcTeUseYn) {
		this.notcTeUseYn = notcTeUseYn;
	}
	public String getNotcYnUseYn() {
		return notcYnUseYn;
	}
	public void setNotcYnUseYn(String notcYnUseYn) {
		this.notcYnUseYn = notcYnUseYn;
	}
	public String getToppNotcUseYn() {
		return toppNotcUseYn;
	}
	public void setToppNotcUseYn(String toppNotcUseYn) {
		this.toppNotcUseYn = toppNotcUseYn;
	}
	public String getMvpLinkUseYn() {
		return mvpLinkUseYn;
	}
	public void setMvpLinkUseYn(String mvpLinkUseYn) {
		this.mvpLinkUseYn = mvpLinkUseYn;
	}
	public String getHmpgRegUseYn() {
		return hmpgRegUseYn;
	}
	public void setHmpgRegUseYn(String hmpgRegUseYn) {
		this.hmpgRegUseYn = hmpgRegUseYn;
	}
	public String getClsUseYn() {
		return clsUseYn;
	}
	public void setClsUseYn(String clsUseYn) {
		this.clsUseYn = clsUseYn;
	}
	public String getTitLinkUseYn() {
		return titLinkUseYn;
	}
	public void setTitLinkUseYn(String titLinkUseYn) {
		this.titLinkUseYn = titLinkUseYn;
	}
	public String getBdltUseYn() {
		return bdltUseYn;
	}
	public void setBdltUseYn(String bdltUseYn) {
		this.bdltUseYn = bdltUseYn;
	}
	public String getAnsSendCd() {
		return ansSendCd;
	}
	public void setAnsSendCd(String ansSendCd) {
		this.ansSendCd = ansSendCd;
	}
	public String getAnsSendCdNm() {
		return ansSendCdNm;
	}
	public void setAnsSendCdNm(String ansSendCdNm) {
		this.ansSendCdNm = ansSendCdNm;
	}
	public String getNewsBdotUseYn() {
		return newsBdotUseYn;
	}
	public void setNewsBdotUseYn(String newsBdotUseYn) {
		this.newsBdotUseYn = newsBdotUseYn;
	}
	public String getRcmdUseYn() {
		return rcmdUseYn;
	}
	public void setRcmdUseYn(String rcmdUseYn) {
		this.rcmdUseYn = rcmdUseYn;
	}
	public String getSrchwrdUseYn() {
		return srchwrdUseYn;
	}
	public void setSrchwrdUseYn(String srchwrdUseYn) {
		this.srchwrdUseYn = srchwrdUseYn;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchNm() {
		return searchNm;
	}
	public void setSearchNm(String searchNm) {
		this.searchNm = searchNm;
	}
	public String getSearchWrtPnNm() {
		return searchWrtPnNm;
	}
	public void setSearchWrtPnNm(String searchWrtPnNm) {
		this.searchWrtPnNm = searchWrtPnNm;
	}
	public String getSrchwrdNotcYn() {
		return srchwrdNotcYn;
	}
	public void setSrchwrdNotcYn(String srchwrdNotcYn) {
		this.srchwrdNotcYn = srchwrdNotcYn;
	}
	public String getSearchLinkUrl() {
		return searchLinkUrl;
	}
	public void setSearchLinkUrl(String searchLinkUrl) {
		this.searchLinkUrl = searchLinkUrl;
	}
	public String getSearchNtopYn() {
		return searchNtopYn;
	}
	public void setSearchNtopYn(String searchNtopYn) {
		this.searchNtopYn = searchNtopYn;
	}
	public String getSearchToppNotcYn() {
		return searchToppNotcYn;
	}
	public void setSearchToppNotcYn(String searchToppNotcYn) {
		this.searchToppNotcYn = searchToppNotcYn;
	}
	public String getSearchNotcYn() {
		return searchNotcYn;
	}
	public void setSearchNotcYn(String searchNotcYn) {
		this.searchNotcYn = searchNotcYn;
	}
	public String getSearchAnsYn() {
		return searchAnsYn;
	}
	public void setSearchAnsYn(String searchAnsYn) {
		this.searchAnsYn = searchAnsYn;
	}
	public String getSearchCls1Cd() {
		return searchCls1Cd;
	}
	public void setSearchCls1Cd(String searchCls1Cd) {
		this.searchCls1Cd = searchCls1Cd;
	}
	public String getSearchCls2Cd() {
		return searchCls2Cd;
	}
	public void setSearchCls2Cd(String searchCls2Cd) {
		this.searchCls2Cd = searchCls2Cd;
	}
	public String getSearchThClcd() {
		return searchThClcd;
	}
	public void setSearchThClcd(String searchThClcd) {
		this.searchThClcd = searchThClcd;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchNotcSdt() {
		return searchNotcSdt;
	}
	public void setSearchNotcSdt(String searchNotcSdt) {
		this.searchNotcSdt = searchNotcSdt;
	}
	public String getSearchNotcEdt() {
		return searchNotcEdt;
	}
	public void setSearchNotcEdt(String searchNotcEdt) {
		this.searchNotcEdt = searchNotcEdt;
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
	public String[] getAddFileList1() {
		if (this.addFileList1 == null){ 
			return null;
		}else{
			String[] temp = new String[this.addFileList1.length];
			System.arraycopy(this.addFileList1, 0, temp, 0, this.addFileList1.length);
			return temp;
		}
	}
	public void setAddFileList1(String[] addFileList1) {
		if(addFileList1 != null){
			this.addFileList1 = new String[addFileList1.length];
			
			for (int i = 0; i < addFileList1.length; ++i){
				this.addFileList1[i] = addFileList1[i];
			}
		}else{
			this.addFileList1 = null;
		}
	}
	public String[] getAddFileList2() {
		if (this.addFileList2 == null){ 
			return null;
		}else{
			String[] temp = new String[this.addFileList2.length];
			System.arraycopy(this.addFileList2, 0, temp, 0, this.addFileList2.length);
			return temp;
		}
	}
	public void setAddFileList2(String[] addFileList2) {
		if(addFileList2 != null){
			this.addFileList2 = new String[addFileList2.length];
			
			for (int i = 0; i < addFileList2.length; ++i){
				this.addFileList2[i] = addFileList2[i];
			}
		}else{
			this.addFileList2 = null;
		}
	}
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getBdotSeq() {
		return bdotSeq;
	}
	public void setBdotSeq(String bdotSeq) {
		this.bdotSeq = bdotSeq;
	}
	public String getHgrkBdotSeq() {
		return hgrkBdotSeq;
	}
	public void setHgrkBdotSeq(String hgrkBdotSeq) {
		this.hgrkBdotSeq = hgrkBdotSeq;
	}
	public String getTitNm() {
		return titNm;
	}
	public void setTitNm(String titNm) {
		this.titNm = titNm;
	}
	public String getInqT() {
		return inqT;
	}
	public void setInqT(String inqT) {
		this.inqT = inqT;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getBdotPwd() {
		return bdotPwd;
	}
	public void setBdotPwd(String bdotPwd) {
		this.bdotPwd = bdotPwd;
	}
	public String getToppNotcYn() {
		return toppNotcYn;
	}
	public void setToppNotcYn(String toppNotcYn) {
		this.toppNotcYn = toppNotcYn;
	}
	
	public String getSmyInfoCn() {
		return smyInfoCn;
	}
	public void setSmyInfoCn(String smyInfoCn) {
		this.smyInfoCn = smyInfoCn;
	}
	public String getNotcYn() {
		return notcYn;
	}
	public void setNotcYn(String notcYn) {
		this.notcYn = notcYn;
	}
	public String getNotcSdt() {
		return notcSdt;
	}
	public void setNotcSdt(String notcSdt) {
		this.notcSdt = notcSdt;
	}
	public String getNotcEdt() {
		return notcEdt;
	}
	public void setNotcEdt(String notcEdt) {
		this.notcEdt = notcEdt;
	}
	public String getMvpLinkYn() {
		return mvpLinkYn;
	}
	public void setMvpLinkYn(String mvpLinkYn) {
		this.mvpLinkYn = mvpLinkYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getDelDttm() {
		return delDttm;
	}
	public void setDelDttm(String delDttm) {
		this.delDttm = delDttm;
	}
	public String getDelPnNo() {
		return delPnNo;
	}
	public void setDelPnNo(String delPnNo) {
		this.delPnNo = delPnNo;
	}
	public String getMvpUrl() {
		return mvpUrl;
	}
	public void setMvpUrl(String mvpUrl) {
		this.mvpUrl = mvpUrl;
	}
	public String getCls1Cd() {
		return cls1Cd;
	}
	public void setCls1Cd(String cls1Cd) {
		this.cls1Cd = cls1Cd;
	}
	public String getCls1CdNm() {
		return cls1CdNm;
	}
	public void setCls1CdNm(String cls1CdNm) {
		this.cls1CdNm = cls1CdNm;
	}
	public String getCls2Cd() {
		return cls2Cd;
	}
	public void setCls2Cd(String cls2Cd) {
		this.cls2Cd = cls2Cd;
	}
	
	public String getCls2CdNm() {
		return cls2CdNm;
	}
	public void setCls2CdNm(String cls2CdNm) {
		this.cls2CdNm = cls2CdNm;
	}
	public String getThClcd() {
		return thClcd;
	}
	public void setThClcd(String thClcd) {
		this.thClcd = thClcd;
	}
	public String getWrtPnPnn() {
		return wrtPnPnn;
	}
	public void setWrtPnPnn(String wrtPnPnn) {
		this.wrtPnPnn = wrtPnPnn;
	}
	public String getWrtPnTlno() {
		return wrtPnTlno;
	}
	public void setWrtPnTlno(String wrtPnTlno) {
		this.wrtPnTlno = wrtPnTlno;
	}
	public String getWrtPnIp() {
		return wrtPnIp;
	}
	public void setWrtPnIp(String wrtPnIp) {
		this.wrtPnIp = wrtPnIp;
	}
	public String getWrtPnNo() {
		return wrtPnNo;
	}
	public void setWrtPnNo(String wrtPnNo) {
		this.wrtPnNo = wrtPnNo;
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
	public String getBdotCnNo() {
		return bdotCnNo;
	}
	public void setBdotCnNo(String bdotCnNo) {
		this.bdotCnNo = bdotCnNo;
	}
	public String getBdotRcmdNo() {
		return bdotRcmdNo;
	}
	public void setBdotRcmdNo(String bdotRcmdNo) {
		this.bdotRcmdNo = bdotRcmdNo;
	}
	public String getRcmdUserNo() {
		return rcmdUserNo;
	}
	public void setRcmdUserNo(String rcmdUserNo) {
		this.rcmdUserNo = rcmdUserNo;
	}
	public String getRcmdIp() {
		return rcmdIp;
	}
	public void setRcmdIp(String rcmdIp) {
		this.rcmdIp = rcmdIp;
	}
	public String getRcmdDttm() {
		return rcmdDttm;
	}
	public void setRcmdDttm(String rcmdDttm) {
		this.rcmdDttm = rcmdDttm;
	}
	public String getRplNo() {
		return rplNo;
	}
	public void setRplNo(String rplNo) {
		this.rplNo = rplNo;
	}
	public String getRplCn() {
		return rplCn;
	}
	public void setRplCn(String rplCn) {
		this.rplCn = rplCn;
	}
	public String getRplRcmdT() {
		return rplRcmdT;
	}
	public void setRplRcmdT(String rplRcmdT) {
		this.rplRcmdT = rplRcmdT;
	}
	public String getRplNrcmdT() {
		return rplNrcmdT;
	}
	public void setRplNrcmdT(String rplNrcmdT) {
		this.rplNrcmdT = rplNrcmdT;
	}
	public String getSrchwrdNo() {
		return srchwrdNo;
	}
	public void setSrchwrdNo(String srchwrdNo) {
		this.srchwrdNo = srchwrdNo;
	}
	public String getSrchwrd() {
		return srchwrd;
	}
	public void setSrchwrd(String srchwrd) {
		this.srchwrd = srchwrd;
	}
	public String getSrchwrdDc() {
		return srchwrdDc;
	}
	public void setSrchwrdDc(String srchwrdDc) {
		this.srchwrdDc = srchwrdDc;
	}
	public String getSrchwrdFc() {
		return srchwrdFc;
	}
	public void setSrchwrdFc(String srchwrdFc) {
		this.srchwrdFc = srchwrdFc;
	}
	public String getListJspUrl() {
		return listJspUrl;
	}
	public void setListJspUrl(String listJspUrl) {
		this.listJspUrl = listJspUrl;
	}
	public String getListUrl() {
		return listUrl;
	}
	public void setListUrl(String listUrl) {
		this.listUrl = listUrl;
	}
	public String getListAjaxUrl() {
		return listAjaxUrl;
	}
	public void setListAjaxUrl(String listAjaxUrl) {
		this.listAjaxUrl = listAjaxUrl;
	}
	public String getHandleJspUrl() {
		return handleJspUrl;
	}
	public void setHandleJspUrl(String handleJspUrl) {
		this.handleJspUrl = handleJspUrl;
	}
	public String getHandleUrl() {
		return handleUrl;
	}
	public void setHandleUrl(String handleUrl) {
		this.handleUrl = handleUrl;
	}
	public String getSaveAjaxUrl() {
		return saveAjaxUrl;
	}
	public void setSaveAjaxUrl(String saveAjaxUrl) {
		this.saveAjaxUrl = saveAjaxUrl;
	}
	public String getDeleteAjaxUrl() {
		return deleteAjaxUrl;
	}
	public void setDeleteAjaxUrl(String deleteAjaxUrl) {
		this.deleteAjaxUrl = deleteAjaxUrl;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getRowNo() {
		return rowNo;
	}
	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	public String getSrchwrdCnt() {
		return srchwrdCnt;
	}
	public void setSrchwrdCnt(String srchwrdCnt) {
		this.srchwrdCnt = srchwrdCnt;
	}
	public String getBdotDc() {
		return bdotDc;
	}
	public void setBdotDc(String bdotDc) {
		this.bdotDc = bdotDc;
	}
	public String getBlbdHistDc() {
		return blbdHistDc;
	}
	public void setBlbdHistDc(String blbdHistDc) {
		this.blbdHistDc = blbdHistDc;
	}
	public String getInqTUseYn() {
		return inqTUseYn;
	}
	public void setInqTUseYn(String inqTUseYn) {
		this.inqTUseYn = inqTUseYn;
	}
	public String getCls1UseYn() {
		return cls1UseYn;
	}
	public void setCls1UseYn(String cls1UseYn) {
		this.cls1UseYn = cls1UseYn;
	}
	public String getCls2UseYn() {
		return cls2UseYn;
	}
	public void setCls2UseYn(String cls2UseYn) {
		this.cls2UseYn = cls2UseYn;
	}
	public String getCls3UseYn() {
		return cls3UseYn;
	}
	public void setCls3UseYn(String cls3UseYn) {
		this.cls3UseYn = cls3UseYn;
	}
	public String getCls1UseCd() {
		return cls1UseCd;
	}
	public void setCls1UseCd(String cls1UseCd) {
		this.cls1UseCd = cls1UseCd;
	}
	public String getCls2UseCd() {
		return cls2UseCd;
	}
	public void setCls2UseCd(String cls2UseCd) {
		this.cls2UseCd = cls2UseCd;
	}
	public String getCls3UseCd() {
		return cls3UseCd;
	}
	public void setCls3UseCd(String cls3UseCd) {
		this.cls3UseCd = cls3UseCd;
	}
	public String getBdltCn() {
		return bdltCn;
	}
	public void setBdltCn(String bdltCn) {
		this.bdltCn = bdltCn;
	}
	public String getThumbApndFileNo() {
		return thumbApndFileNo;
	}
	public void setThumbApndFileNo(String thumbApndFileNo) {
		this.thumbApndFileNo = thumbApndFileNo;
	}
	public String getFtptYn() {
		return ftptYn;
	}
	public void setFtptYn(String ftptYn) {
		this.ftptYn = ftptYn;
	}
	public String getBdotCn2() {
		return bdotCn2;
	}
	public void setBdotCn2(String bdotCn2) {
		this.bdotCn2 = bdotCn2;
	}
	public String getWrtPnNm() {
		return wrtPnNm;
	}
	public void setWrtPnNm(String wrtPnNm) {
		this.wrtPnNm = wrtPnNm;
	}
	public String getWrtPnEmail() {
		return wrtPnEmail;
	}
	public void setWrtPnEmail(String wrtPnEmail) {
		this.wrtPnEmail = wrtPnEmail;
	}
	public String getAnsYn() {
		return ansYn;
	}
	public void setAnsYn(String ansYn) {
		this.ansYn = ansYn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getEmailResvAgYn() {
		return emailResvAgYn;
	}
	public void setEmailResvAgYn(String emailResvAgYn) {
		this.emailResvAgYn = emailResvAgYn;
	}
	public String getWrtEmailUserNo() {
		return wrtEmailUserNo;
	}
	public void setWrtEmailUserNo(String wrtEmailUserNo) {
		this.wrtEmailUserNo = wrtEmailUserNo;
	}
	public String getAnsApndFileNo() {
		return ansApndFileNo;
	}
	public void setAnsApndFileNo(String ansApndFileNo) {
		this.ansApndFileNo = ansApndFileNo;
	}
	public String getUserTc() {
		return userTc;
	}
	public void setUserTc(String userTc) {
		this.userTc = userTc;
	}
	public String getTgtDc() {
		return tgtDc;
	}
	public void setTgtDc(String tgtDc) {
		this.tgtDc = tgtDc;
	}
	public String getSearchTgtDc() {
		return searchTgtDc;
	}
	public void setSearchTgtDc(String searchTgtDc) {
		this.searchTgtDc = searchTgtDc;
	}

	

}

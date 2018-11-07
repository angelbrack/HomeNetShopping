package prjframework.common.util;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * PagerVO
 * @author LMS
 * @since 2014.12.09
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2014.12.09  LMS         최초 생성
 *  
 *  </pre>
 */
public class PagerVO implements Serializable {

	private static final long serialVersionUID = 5745131850727129844L;

	private String cmd = "I";
    /** 현재페이지 */
    private int pageIndex = 1;

    /** 현재페이지 */
    private int pageSubIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** 페이지사이즈 */
    private int subPageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    private int subRecordCountPerPage = 10;
    
    private int currentPage = 1;
    
    private int currentSubPage = 1;
    
    private String searchWord = "";
    
    private String searchKey = "";
    
    /* 첨부파일여뷰 */
    private String atchFileXn;
    /* 첨부파일번호 */
    private String apndFileNo;
    /* 업로드 파일 정보 */
    private String[] addFileList;
    
    /** 작성자번호 */
	private String wrtPnNo = "";
	/** 작성자명 */
	private String wrtPnNm = "";
	/** 작성자IP */
	private String wrtPnIp = "";
	/** 작성일시 */
	private String wrtDttm = "";
	/** 수정자번호 */
	private String updtPnNo = "";
	/** 수정자명 */
	private String updtPnNm = "";
	/** 수정자IP */
	private String updtPnIp = "";
	/** 수정일시 */
	private String updtDttm = "";
	/** ORDER 컬럼 */
	private String orderCol = "";
	/** ORDER 순서 */
	private String orderSort = "";
	
	private Integer totalCount	= null;

	private String  searchStartDate					; // 검색 기간 시작일자
	private String  searchEndDate					; // 검색 기간 종료일자
	private String  searchCheckOptDate				; // 검색 기간 체크 옵션
	
	/**
	 * pageIndex attribute 를 리턴한다.
	 * @return the int
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		try {
			if (pageIndex != null && pageIndex.length()>0) {
				this.pageIndex = Integer.parseInt(pageIndex);
			} else {
				this.pageIndex = 1;
			}
		} catch (Exception e) {
			this.pageIndex = 1;
		}
	}


	/**
	 * pageIndex attribute 를 리턴한다.
	 * @return the int
	 */
	public int getPageSubIndex() {
		return pageSubIndex;
	}

	public void setPageSubIndex(int pageSubIndex) {
		this.pageSubIndex = pageSubIndex;
	}

	public void setPageSubIndex(String pageSubIndex) {
		try {
			if (pageSubIndex != null && pageSubIndex.length()>0) {
				this.pageSubIndex = Integer.parseInt(pageSubIndex);
			} else {
				this.pageSubIndex = 1;
			}
		} catch (Exception e) {
			this.pageSubIndex = 1;
		}
	}

	/**
	 * pageUnit attribute 를 리턴한다.
	 * @return the int
	 */
	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public void setPageUnit(String pageUnit) {
		if (pageUnit != null && pageUnit.length()>0) {
			this.pageUnit = Integer.parseInt(pageUnit);
		} else {
			this.pageUnit = 10;
		}
	}

	/**
	 * pageSize attribute 를 리턴한다.
	 * @return the int
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * pageSize attribute 값을 설정한다.
	 * @return pageSize int
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * firstIndex attribute 를 리턴한다.
	 * @return the int
	 */
	public int getFirstIndex() {
		return firstIndex;
	}

	/**
	 * firstIndex attribute 값을 설정한다.
	 * @return firstIndex int
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	/**
	 * lastIndex attribute 를 리턴한다.
	 * @return the int
	 */
	public int getLastIndex() {
		return lastIndex;
	}

	/**
	 * lastIndex attribute 값을 설정한다.
	 * @return lastIndex int
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * recordCountPerPage attribute 를 리턴한다.
	 * @return the int
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	/**
	 * recordCountPerPage attribute 값을 설정한다.
	 * @return recordCountPerPage int
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getSubPageSize() {
		return subPageSize;
	}

	public void setSubPageSize(int subPageSize) {
		this.subPageSize = subPageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
	public String getAtchFileXn() {
		return atchFileXn;
	}

	public void setAtchFileXn(String atchFileXn) {
		this.atchFileXn = atchFileXn;
	}

	public String getApndFileNo() {
		return apndFileNo;
	}

	public void setApndFileNo(String apndFileNo) {
		this.apndFileNo = apndFileNo;
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

	public String getWrtPnNo() {
		return wrtPnNo;
	}

	public void setWrtPnNo(String wrtPnNo) {
		this.wrtPnNo = wrtPnNo;
	}

	public String getWrtPnNm() {
		return wrtPnNm;
	}

	public void setWrtPnNm(String wrtPnNm) {
		this.wrtPnNm = wrtPnNm;
	}

	public String getWrtPnIp() {
		return wrtPnIp;
	}

	public void setWrtPnIp(String wrtPnIp) {
		this.wrtPnIp = wrtPnIp;
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

	public String getUpdtPnNm() {
		return updtPnNm;
	}

	public void setUpdtPnNm(String updtPnNm) {
		this.updtPnNm = updtPnNm;
	}

	public String getUpdtPnIp() {
		return updtPnIp;
	}

	public void setUpdtPnIp(String updtPnIp) {
		this.updtPnIp = updtPnIp;
	}

	public String getUpdtDttm() {
		return updtDttm;
	}

	public void setUpdtDttm(String updtDttm) {
		this.updtDttm = updtDttm;
	}

	public String getOrderCol() {
		return orderCol;
	}

	public void setOrderCol(String orderCol) {
		this.orderCol = orderCol;
	}

	public String getOrderSort() {
		return orderSort;
	}

	public void setOrderSort(String orderSort) {
		this.orderSort = orderSort;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public int getSubRecordCountPerPage() {
		return subRecordCountPerPage;
	}

	public void setSubRecordCountPerPage(int subRecordCountPerPage) {
		this.subRecordCountPerPage = subRecordCountPerPage;
	}

	public int getCurrentSubPage() {
		return currentSubPage;
	}

	public void setCurrentSubPage(int currentSubPage) {
		this.currentSubPage = currentSubPage;
	}

	public String getSearchStartDate() {
		return searchStartDate;
	}

	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}

	public String getSearchEndDate() {
		return searchEndDate;
	}

	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

	public String getSearchCheckOptDate() {
		return searchCheckOptDate;
	}

	public void setSearchCheckOptDate(String searchCheckOptDate) {
		this.searchCheckOptDate = searchCheckOptDate;
	}
	
	
}

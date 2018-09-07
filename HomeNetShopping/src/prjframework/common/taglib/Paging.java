package prjframework.common.taglib;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**-----------------------------------------------------------------------
 * lmsframework
 *------------------------------------------------------------------------
 * @Class Paging.java
 * @Description PagingTag 생성
 *
 * @author 윤석진
 * @since 2014. 12. 19
 * @version 1.0
 *
 * @Copyright (c) 2014 (주) 넥스젠어쏘시에이트
 *------------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------
 * 수정일               수정자       수정내용
 * -----------   ---------  -----------------------------------------------
 * 2014. 12. 19  윤석진       최초생성
 */

public class Paging extends TagSupport {
	private static final long serialVersionUID = 1L;
	private PaginationInfo paginationInfo;
	private String type;
	private String jsFunction;
	private String jsRowFunction;
	private boolean rowControl;
	
	private String firstPageLabel;
	private String previousPageLabel;
	private String currentPageLabel;
	private String otherPageLabel;
	private String nextPageLabel;
	private String lastPageLabel;
	private String numberSpliter;
	
	public int doEndTag() throws JspException {
		
		int firstPageNo 			= this.paginationInfo.getFirstPageNo();
		int firstPageNoOnPageList 	= this.paginationInfo.getFirstPageNoOnPageList();
		int totalPageCount 			= this.paginationInfo.getTotalPageCount();
		int pageSize 				= this.paginationInfo.getPageSize();
		int lastPageNoOnPageList 	= this.paginationInfo.getLastPageNoOnPageList();
		int currentPageNo 			= this.paginationInfo.getCurrentPageNo();
		int lastPageNo 				= this.paginationInfo.getLastPageNo();
		int recordCountPerPage 		= this.paginationInfo.getRecordCountPerPage();
		
		try {
			JspWriter out = this.pageContext.getOut();
			
			/* tyep of paging design  = portal, support, mngt */
			if( "portal".equals(this.type) ) {
				this.firstPageLabel    = "<a href=javascript:{0}({1}) ><i class=\"fa fa-angle-left\"></i><i class=\"fa fa-angle-left\"></i></a> ";
				this.previousPageLabel = "<a href=javascript:{0}({1}) onclick={0}({1});return false;><i class=\"fa fa-angle-left\"></i></a> ";
				this.currentPageLabel  = "<span>{0}</span>";
				this.otherPageLabel    = "<a href=javascript:{0}({1})>{2}</a> ";
				this.nextPageLabel     = "<a href=javascript:{0}({1})><i class=\"fa fa-angle-right\"></i></a> ";
				this.lastPageLabel     = "<a href=javascript:{0}({1})><i class=\"fa fa-angle-right\"></i><i class=\"fa fa-angle-right\"></i></a> ";
				this.numberSpliter     = "";
			} else if( "mobile".equals(this.type) ) {
				this.firstPageLabel    = "";
				if ( firstPageNoOnPageList == 1 ) {
					this.previousPageLabel = "<a href=\"#\" data-role=\"button\" data-mini=\"true\" class=\"ui-disabled ui-link ui-btn ui-shadow ui-corner-all ui-mini ui-first-child\" role=\"button\">Prev</a>";
				} else {
					this.previousPageLabel = "<a href=\"javascript:{0}({1})\" data-role=\"button\" data-mini=\"true\" class=\"ui-link ui-btn ui-shadow ui-corner-all ui-mini ui-first-child\" role=\"button\">Prev</a>";
				}
				this.currentPageLabel  = "<a href=\"#\" data-role=\"button\" data-mini=\"true\" class=\"ui-btn-active ui-link ui-btn ui-shadow ui-corner-all ui-mini\" role=\"button\">{0}</a>";
				this.otherPageLabel    = "<a href=\"javascript:{0}({1})\" data-role=\"button\" data-mini=\"true\" data-ajax=\"false\" class=\"ui-link ui-btn ui-shadow ui-corner-all ui-mini\" role=\"button\">{2}</a>";
				if ( lastPageNoOnPageList < lastPageNo ) {
					this.nextPageLabel     = "<a href=\"javascript:{0}({1})\" data-role=\"button\" data-mini=\"true\" class=\"ui-link ui-btn ui-shadow ui-corner-all ui-mini ui-last-child\" role=\"button\">Next</a>";
				} else {
					this.nextPageLabel     = "<a href=\"#\" data-role=\"button\" data-mini=\"true\" class=\"ui-disabled ui-link ui-btn ui-shadow ui-corner-all ui-mini ui-last-child\" role=\"button\">Next</a>";
				}
				this.lastPageLabel     = "";
				this.numberSpliter     = "";
			} else if( "mgnt".equals(this.type)) {
				this.firstPageLabel    = "<a href=javascript:{0}({1}) class=mr5><img src=/theme/00001/ko/mgnt/images/paging_01.jpg alt=처음으로 /></a> ";
				this.previousPageLabel = "<a href=javascript:{0}({1}) class=mr10><img src=/theme/00001/ko/mgnt/images/paging_02.jpg alt=이전 /></a> ";
				this.currentPageLabel  = "<a href=javascript:void(0) class=on>{0}</a> ";
				this.otherPageLabel    = "<a href=javascript:{0}({1})>{2}</a> ";
				this.nextPageLabel     = "<a href=javascript:{0}({1}) class=ml10><img src=/theme/00001/ko/mgnt/images/paging_03.jpg alt=다음 /></a> ";
				this.lastPageLabel     = "<a href=javascript:{0}({1}) class=ml5><img src=/theme/00001/ko/mgnt/images/paging_04.jpg alt=마지막으로 /></a> ";
				this.numberSpliter     = "<span class=\"gray1 ml5 mr5\">|</span> ";
			} else {
				this.firstPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">[처음]</a>&#160;";
				this.previousPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">[이전]</a>&#160;";
				this.currentPageLabel = "<strong>{0}</strong>&#160;";
				this.otherPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a>&#160;";
				this.nextPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">[다음]</a>&#160;";
				this.lastPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">[마지막]</a>&#160;";
				this.numberSpliter = "";
			}
			
			String contents = renderPagination(this.paginationInfo, this.jsFunction, this.jsRowFunction);

			out.println(contents);

			return 6;
		} catch (IOException e) {
			throw new JspException();
		}
	}

	public void setPaginationInfo(PaginationInfo paginationInfo) {
		this.paginationInfo = paginationInfo;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setJsFunction(String jsFunction) {
		this.jsFunction = jsFunction;
	}
	
	public void setJsRowFunction(String jsRowFunction) {
		this.jsRowFunction = jsRowFunction;
	}
	
	public void setRowControl(boolean rowControl) {
		this.rowControl = rowControl;
	}
	
	/* logic of paging */
	public String renderPagination(PaginationInfo paginationInfo, String jsFunction, String jsRowFunction) {
		StringBuffer strBuff = new StringBuffer();

		int firstPageNo = paginationInfo.getFirstPageNo();
		int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
		int totalPageCount = paginationInfo.getTotalPageCount();
		int pageSize = paginationInfo.getPageSize();
		int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
		int currentPageNo = paginationInfo.getCurrentPageNo();
		int lastPageNo = paginationInfo.getLastPageNo();
		int recordCountPerPage = paginationInfo.getRecordCountPerPage();
		
		if( this.rowControl ) {
			strBuff.append("<select name=recordCountPerPage style=\"width:60px;height:22px;margin-right:20px;\" onchange="+jsRowFunction+"(this.value)>");
			for(int i=10; i<=100 ; i+=10) {
				if( recordCountPerPage == i) {
					strBuff.append("<option value="+i+" selected=selected>"+i+"</option>");
				} else {
					strBuff.append("<option value="+i+">"+i+"</option>");
				}
			}
			strBuff.append("</select>");
		}
		
		if (totalPageCount > pageSize) {
			if (firstPageNoOnPageList > pageSize) {
				strBuff.append(MessageFormat.format(this.firstPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNo) }));
				strBuff.append(MessageFormat.format(this.previousPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList - 1) }));
			} else {
				strBuff.append(MessageFormat.format(this.firstPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNo) }));
				strBuff.append(MessageFormat.format(this.previousPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNo) }));
			}
		}

		for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
			if (i == currentPageNo){
				strBuff.append(MessageFormat.format(this.currentPageLabel, new Object[] { Integer.toString(i) }));
				if (lastPageNoOnPageList != i) {
					strBuff.append(this.numberSpliter);
				}
			} else {
				strBuff.append(MessageFormat.format(this.otherPageLabel, new Object[] { jsFunction, Integer.toString(i), Integer.toString(i) }));
				if (lastPageNoOnPageList != i) {
					strBuff.append(this.numberSpliter);
				}
			}
		}

		if (totalPageCount > pageSize) {
			if (lastPageNoOnPageList < totalPageCount) {
				strBuff.append(MessageFormat.format(this.nextPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList + pageSize) }));
				strBuff.append(MessageFormat.format(this.lastPageLabel, new Object[] { jsFunction, Integer.toString(lastPageNo) }));
			} else {
				strBuff.append(MessageFormat.format(this.nextPageLabel, new Object[] { jsFunction, Integer.toString(lastPageNo) }));
				strBuff.append(MessageFormat.format(this.lastPageLabel, new Object[] { jsFunction, Integer.toString(lastPageNo) }));
			}
		}
		return strBuff.toString();
	}
}

package egovframework.com.cmm;

import java.text.MessageFormat;
import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * ImagePaginationRenderer.java 클래스
 * 
 * @author 서준식
 * @since 2011. 9. 16.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 *   2011. 9. 16.   서준식       이미지 경로에 ContextPath추가
 * </pre>
 */
public class ImagePaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	private ServletContext servletContext;
	
	public ImagePaginationRenderer() {
	
	}
	
	public void initVariables(){
		/*
		 * <img src=\"" + servletContext.getContextPath() +  "/images/egovframework/com/cmm/mod/icon/icon_nextend.gif\" alt=\"마지막\" border=\"0\"/>
        */
        firstPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \" class=\"mr5\"><img src=\"/design/support/images/paging_01.jpg\" alt=\"처음으로\" /></a>";
        previousPageLabel = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \" class=\"mr10\"><img src=\"/design/support/images/paging_02.jpg\" alt=\"이전\" /></a>";
        currentPageLabel  = "<a href=\"#\" class=\"on\">{0}</a>";
        otherPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">{2}</a>";
        nextPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \" class=\"ml10\"><img src=\"/design/support/images/paging_03.jpg\" alt=\"다음\" /></a>";
        lastPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \" class=\"ml5\"><img src=\"/design/support/images/paging_04.jpg\" alt=\"마지막으로\" /></a>";
        
	}

	

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}
	
	
	
	@Override
	public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {
		StringBuffer strBuff = new StringBuffer();

		int firstPageNo = paginationInfo.getFirstPageNo();
		int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
		int totalPageCount = paginationInfo.getTotalPageCount();
		int pageSize = paginationInfo.getPageSize();
		int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
		int currentPageNo = paginationInfo.getCurrentPageNo();
		int lastPageNo = paginationInfo.getLastPageNo();
		
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
					strBuff.append("<span class=\"gray1 ml5 mr5\">|</span>");
				}
			} else {
				strBuff.append(MessageFormat.format(this.otherPageLabel, new Object[] { jsFunction, Integer.toString(i), Integer.toString(i) }));
				if (lastPageNoOnPageList != i) {
					strBuff.append("<span class=\"gray1 ml5 mr5\">|</span>");
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

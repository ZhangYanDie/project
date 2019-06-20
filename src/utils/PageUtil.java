package utils;

import beans.Page;

public class PageUtil {
	
	public static final int PAGESIZE = 5;
	
	public static Page getPage(int pageSize,int pageIndex,int rowCount){
		Page page = new Page();
		page.setPageSize(pageSize);
		page.setBeginRow(pageSize*(pageIndex-1));
		page.setPageCount((rowCount+pageSize-1)/pageSize);
		page.setPageIndex(pageIndex);
		page.setRowCount(rowCount);
		page.setHasNext(!(page.getPageCount()-pageIndex<=0));
		page.setHasPre(!(pageIndex==1||rowCount==0));
		return page;
		
	}
}

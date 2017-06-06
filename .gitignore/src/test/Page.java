package test;

public class Page {
    private int counts;		// �ܼ�¼��
    private int pagesize;   // ÿҳ����
    private int viewpages;  // ��ʾҳ��
    private int currentpage;// ��ǰҳ
        
    private int start;	// ��ʾ�ĵ�һҳ
    private int end;	// ��ʾ�����һҳ
    private int pages;		// ��ҳ��
    
    public Page(int counts, int currentpage, int pagesize, int viewpages)
    {
     this.counts = counts;
     this.currentpage = currentpage;
     this.pagesize = pagesize;
     this.viewpages = viewpages;
     
     pages= counts/pagesize + (counts%pagesize==0?0:1);
     
     
     if(pages<=viewpages) {
    	 start = 1;
    	 end = pages;} 
     else 
    	 if(currentpage<viewpages/2) {
    		 start = 1;
    		 end = viewpages;} 
    	 else 
    		 if((currentpage + viewpages / 2) > pages) {
    			 start = pages - viewpages + 1;
    			 end = pages;}
    		 else {   		        
    			 start = currentpage - viewpages / 2;
    		     end = currentpage + viewpages / 2;}
     
    }

    public int getCounts() {
	    return counts;
	}
	public void setCounts(int counts) {
	    this.counts = counts;
	}
	
	public int getPagesize() {
	    return pagesize;
	}
	public void setPagesize(int pagesize) {
	    this.pagesize = pagesize;
	}
	
	public int getViewpages() {
	    return viewpages;
	}
	public void setViewpages(int viewpages) {
	    this.viewpages = viewpages;
	}
	
	public int getCurrentpage() {
	    return currentpage;
	}
	public void setCurrentpage(int currentpage) {
	    this.currentpage = currentpage;
	}
	
	public int getPages() {
	    return pages;
	}

	
	public int getStart() {
	    return start;
	}

	
	public int getEnd() {
	    return end;
	}


}

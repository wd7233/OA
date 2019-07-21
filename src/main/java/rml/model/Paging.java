package rml.model;

public class Paging
{
    private int pageNumber; // 当前页码，前端页面传递
    
    private int totalRecord; // 总记录数，数据库查询得到
    
    private int pageSize; // 每页显示条数，在Servlet中指定
    
    private int totalPage; // 总页数，计算得到
    
    private int index; // 当前页的起始索引，计算得到

    public int getPageNumber()
    {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    public int getTotalRecord()
    {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord)
    {
        this.totalRecord = totalRecord;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }
    
}

package rml.model;

import java.util.List;

public class OaMenu
{
    private Integer id;
    
    private Integer parentid;
    
    private String name;
    
    private String url;
    
    private List<OaMenu> menus;
    
    public List<OaMenu> getMenus()
    {
        return menus;
    }

    public void setMenus(List<OaMenu> menus)
    {
        this.menus = menus;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public Integer getParentid()
    {
        return parentid;
    }
    
    public void setParentid(Integer parentid)
    {
        this.parentid = parentid;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url == null ? null : url.trim();
    }
}
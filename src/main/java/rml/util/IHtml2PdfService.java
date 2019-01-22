package rml.util;


public interface IHtml2PdfService {
    /** html完整内容的前缀标识 */
    public static final String INTACT_FLAG = "<html>";
    
    /**
     * html模板，当待转换的html只是片断时，需将其插入到模板的body内.
     */
    public static final String TEMPLATE_HTML = 
            "<html>" +
            "    <head>" +
            "        <style type='text/css'>body {font-family: SimSun;}</style>" +
            "    </head>" +
            "    <body>" +
            "        ${content}" +
            "    </body>" +
            "</html>";
    
    /** 将指定的html内容转化成pdf文档之后，写入到指定的输出流. */
    public void write(java.lang.String htmlContent, java.io.OutputStream os);
    
}
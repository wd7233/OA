package rml.util;

import javax.servlet.http.HttpServletRequest;

public class ToolUtil
{
    
    /**
     * 获取客户端真是IP
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        if (null == request) return "";
        // 
        String remoteAddr;
        // X-Forwarded-For
        String xff = request.getHeader("X-Forwarded-For");
        remoteAddr = (null == xff ? request.getHeader("x-forwarded-for") : xff);
        // Proxy-Client-IP
        if (null == remoteAddr || "".equals(remoteAddr)) {
            String pci = request.getHeader("Proxy-Client-IP");
            remoteAddr = (null == pci ? request.getHeader("proxy-client-ip") : pci);
        }
        // WL-Proxy-Client-IP
        if (null == remoteAddr || "".equals(remoteAddr)) {
            String wpci = request.getHeader("WL-Proxy-Client-IP");
            remoteAddr = (null == wpci ? request.getHeader("wl-proxy-client-ip") : wpci);
        }
        // RemoteAddr
        if (null == remoteAddr || "".equals(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
        } else {
            remoteAddr = remoteAddr.split(",")[0];
        }
        
        return remoteAddr;
    }

    
}

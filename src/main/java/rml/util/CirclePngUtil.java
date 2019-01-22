package rml.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.imageio.ImageIO;

public class CirclePngUtil
{
    public static void main(String[] args)
    {
        String picUrl  =  "http://wx.qlogo.cn/mmopen/94XyCIOHjgJ0NtyrHaNhFXshfaum3bso6icny2atovz16lfqhibgML7QY8qGuBZgBdtSzGwQyvxEXSPjUibDul1eHgEc5rjQy6I/0";
        picUrl = picUrl.substring(0, picUrl.length() - 1) + "0";
        toCircle(picUrl,"50.png");
    }
    public static String toCircle(String picUrl, String imageName)
    {
        String imagePath = "";
        try
        {
            // 获取图片的流
            BufferedImage url = getUrlByBufferedImage(picUrl);
            // Image src = ImageIO.read(new File("C:/Imag.png"));
            // BufferedImage url = (BufferedImage)src;
            // 处理图片将其压缩成正方形的小图
          BufferedImage convertImage = scaleByPercentage(url,40, 40);
            // 裁剪成圆形 （传入的图像必须是正方形的 才会 圆形 如果是长方形的比例则会变成椭圆的）
          convertImage = convertCircular(convertImage);
            // 生成的图片位置
            imagePath = "/home/data/web/photo/" + imageName + ".png";
          // imagePath = "/photo/" + imageName + ".png";
            File file = new File(imagePath);
            ImageIO.write(convertImage, imagePath.substring(imagePath.lastIndexOf(".") + 1), file);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return imagePath;
    }
    
    /**
     * 缩小Image，此方法返回源图像按给定宽度、高度限制下缩放后的图像
     * 
     * @param inputImage
     * @param maxWidth：压缩后宽度
     * @param maxHeight：压缩后高度
     * @throws java.io.IOException
     *             return
     */
    public static BufferedImage scaleByPercentage(BufferedImage inputImage, int newWidth, int newHeight)
        throws Exception
    {
        // 获取原始图像透明度类型
        int type = inputImage.getColorModel().getTransparency();
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        // 开启抗锯齿
        RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 使用高质量压缩
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        BufferedImage img = new BufferedImage(newWidth, newHeight, type);
        Graphics2D graphics2d = img.createGraphics();
        graphics2d.setRenderingHints(renderingHints);
        graphics2d.drawImage(inputImage, 0, 0, newWidth, newHeight, 0, 0, width, height, null);
        graphics2d.dispose();
        return img;
    }
    
    /**
     * 通过网络获取图片
     * 
     * @param url
     * @return
     */
    public static BufferedImage getUrlByBufferedImage(String url)
    {
        try
        {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)urlObj.openConnection();
            // 连接超时
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(25000);
            // 读取超时 --服务器响应比较慢,增大时间
            conn.setReadTimeout(25000);
            conn.setRequestMethod("GET");
            conn.addRequestProperty("Accept-Language", "zh-cn");
            conn.addRequestProperty("Content-type", "image/jpeg");
            conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727)");
            conn.connect();
            BufferedImage bufImg = ImageIO.read(conn.getInputStream());
            conn.disconnect();
            return bufImg;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (ProtocolException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 传入的图像必须是正方形的 才会 圆形 如果是长方形的比例则会变成椭圆的
     * 
     * @param url 用户头像地址
     * @return
     * @throws IOException
     */
    public static BufferedImage convertCircular(BufferedImage bi1)
        throws IOException
    {
        // BufferedImage bi1 = ImageIO.read(new File(url));
        // 这种是黑色底的
        // BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_INT_RGB);
        // 透明底的图片
        BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
        // 开启抗锯齿
        RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 使用高质量压缩
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        Graphics2D g2 = bi2.createGraphics();
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2.setRenderingHints(renderingHints);
        g2.drawImage(bi1, 0, 0, null);
        // 设置颜色
        g2.setBackground(Color.green);
        g2.dispose();
        return bi2;
    }
    
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius)
    {
        // 获取图片宽度和高度
        int w = image.getWidth();
        int h = image.getHeight();
        // 创建图片缓冲
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        //
        Graphics2D g2 = output.createGraphics();
        output = g2.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
        g2.dispose();
        
        g2 = output.createGraphics();
        // 防止锯齿
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 圆角
        g2.fillRoundRect(0, 0, w, h, w/2,h/2);
        g2.setComposite(AlphaComposite.SrcIn);
        // 写入图片
        g2.drawImage(image, 0, 0, w, h, null);
        // 释放
        g2.dispose();
        
        return output;
    }
    
}

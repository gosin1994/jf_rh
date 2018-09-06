package com.jt.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeUtil {

	public static boolean generateQRCode(String fileName, String message, String imgFolderDir, String text) throws Exception {
        int width = 300; // 二维码图片宽度  
        int height = 300; // 二维码图片高度  
        String format = "png";// 二维码的图片格式  
        
          
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   // 内容所使用字符集编码  
          
        BitMatrix bitMatrix = new MultiFormatWriter().encode(message, BarcodeFormat.QR_CODE, width, height, hints);  
        // 生成二维码  
        File qrcodeFile = new File(fileName);  
        MatrixToImageWriter.writeToFile(bitMatrix, format, qrcodeFile);  
        
        File textFile = new File(fileName.replace(".png", "_text.jpg"));
        //System.out.println(imgFolderDir+"/qrcode_bg.jpg");
        
        pressText(text, textFile, new File(imgFolderDir+"/qrcode_bg.jpg"));
        
        mergeImage(textFile, qrcodeFile, new File(fileName.replace(".png", ".jpg")));
        
		return true;
	}
	
	
	
	public static void pressText(String pressText, File targetFile, File sourceFile) {
        
        //计算文字开始的位置
        //x开始的位置：（图片宽度-字体大小*字的个数）/2
        //int startX = 400;//(width-(fontSize*pressText.length()))/2;
        //y开始的位置：图片高度-（图片高度-图片宽度）/2
        //int startY = 890;//height-(height-width)/2;    
        
        //新图片文字开始的位置
        //x开始的位置：（图片宽度-字体大小*字的个数）/2
        int startX = 275;//(width-(fontSize*pressText.length()))/2;
        //y开始的位置：图片高度-（图片高度-图片宽度）/2
        int startY = 520;//height-(height-width)/2;    
        
        
        try {
            BufferedImage source = ImageIO.read(sourceFile);
            
            int imageW = source.getWidth(null);
            int imageH = source.getHeight(null);
            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(source, 0, 0, imageW, imageH, null);
            g.setColor(Color.red);
            g.setFont(new Font("宋体", Font.BOLD, 43));
            g.drawString(pressText, startX, startY);
            g.dispose();

            ImageIO.write(image, "JPEG", targetFile);
            /*JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	private static void mergeImage(File file0, File file1, File newFile ) {
		BufferedImage bi_0 = null;
    	BufferedImage bi_1 = null;
        //读取文件
        try{
        	bi_0 = ImageIO.read(file0);
        	bi_1 = ImageIO.read(file1);
        }catch(IOException ex){}

        //new 一个新的图像
        int w = bi_0.getWidth();
        int h_0 = bi_0.getHeight();
        int h_1 = bi_1.getHeight();
        int h = h_0 + h_1;
        BufferedImage bi=new BufferedImage(w,h_0,BufferedImage.TYPE_INT_RGB);

        //像素一个一个复制过来
        for(int y=0; y<h_0; y++){
            for(int x=0;x<w;x++){
            	if((x>273 && x<(273+300)) && (y>590 && y<(590+300))){//其中273为二维码的横坐标，590为二维码的纵坐标
            		bi.setRGB(x,y,bi_1.getRGB(x-273,y-590));
            	}else {
            		bi.setRGB(x,y,bi_0.getRGB(x,y));
					
				}
            }
        }		
        /*for(int y=0;y<h_1;y++){
            for(int x=0;x<w;x++){
                bi.setRGB(x,h_0+y,bi_1.getRGB(x,y));
            }
        }	*/	

        //输出文件
        try{
            ImageIO.write(bi,"JPEG",newFile);
        }catch(IOException ex){
        	ex.printStackTrace();
        }
	}
}

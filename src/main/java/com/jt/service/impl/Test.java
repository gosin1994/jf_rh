package com.jt.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Test{
    public static void main(String[] args){
    	
    	File file0 = new File("D:/work/task/ruhu/img/h5/base2.png");
    	File file1 = new File("D:/work/task/ruhu/img/h5/base3.jpg");
    	
    	File file2 = new File("D:/work/task/ruhu/img/testnew446.jpg");

    	mergeWidthImage(file0, file1);
    	
    	//pressText("推荐人：深*湾", file2, file1);
    	
    	
    	/*String name = "刘小红";
		if(name.length()>2){
			name = name.substring(0, 1)+"*"+name.substring(2);
		}else {
			name = name.substring(0, 1)+"*";
		}
		
		System.out.println(name);*/
    }

	private static void mergeImage(File file0, File file1) {
		BufferedImage bi_0 = null;
    	BufferedImage bi_1 = null;
        //读取文件
        try{
        	bi_0 = ImageIO.read(file0);
        	bi_1 = ImageIO.read(file1);
        }catch(IOException ex){}

        //假设图片0 和图片1 宽度相同，上下合成
        //new 一个新的图像
        int w = bi_0.getWidth();
        int h_0 = bi_0.getHeight();
        int h_1 = bi_1.getHeight();
        int h = h_0 + h_1;
        BufferedImage bi=new BufferedImage(w,h_0,BufferedImage.TYPE_INT_RGB);

        //像素一个一个复制过来
        for(int y=0; y<h_0; y++){
            for(int x=0;x<w;x++){
            	if((x>380 && x<(380+320)) && (y>929 && y<(929+320))){
            		bi.setRGB(x,y,bi_1.getRGB(x-380,y-929));
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
            ImageIO.write(bi,"JPEG",new File("D:/test/bgPic/testnew.jpg"));
        }catch(IOException ex){
        	ex.printStackTrace();
        }
	}
	
	private static void mergeWidthImage(File file0, File file1) {
		BufferedImage bi_0 = null;
    	BufferedImage bi_1 = null;
        //读取文件
        try{
        	bi_0 = ImageIO.read(file0);
        	bi_1 = ImageIO.read(file1);
        }catch(IOException ex){}

        //假设图片0 和图片1 宽度相同，上下合成
        //new 一个新的图像
        int w = bi_0.getWidth();
        int h_0 = bi_0.getHeight();
        int h_1 = bi_1.getHeight();
        int h = h_0 + h_1;
        BufferedImage bi=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);

        //像素一个一个复制过来
        for(int y=0; y<h_0; y++){
            for(int x=0;x<w;x++){
            	bi.setRGB(x,y,bi_0.getRGB(x,y));
            }
        }		
        for(int y=0;y<h_1;y++){
            for(int x=0;x<w;x++){
                bi.setRGB(x,h_0+y,bi_1.getRGB(x,y));
            }
        }

        //输出文件
        try{
            ImageIO.write(bi,"JPEG",new File("D:/work/task/ruhu/img/h5/base3.jpg"));
        }catch(IOException ex){
        	ex.printStackTrace();
        }
	}
	
	
	/**
	        * @为图片添加文字
            * @param pressText 文字
	        * @param newImg    带文字的图片
	        * @param targetImg 需要添加文字的图片
	        * @param fontStyle 
	        * @param color
	        * @param fontSize
	        * @param width
	        * @param heigh
	   */
	public static void pressText(String pressText, File targetFile, File sourceFile) {
        
        //计算文字开始的位置
        //x开始的位置：（图片宽度-字体大小*字的个数）/2
        int startX = 405;//(width-(fontSize*pressText.length()))/2;
        //y开始的位置：图片高度-（图片高度-图片宽度）/2
        int startY = 890;//height-(height-width)/2;        
        
        try {
            BufferedImage source = ImageIO.read(sourceFile);
            
            int imageW = source.getWidth(null);
            int imageH = source.getHeight(null);
            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(source, 0, 0, imageW, imageH, null);
            g.setColor(Color.WHITE);
            g.setFont(new Font("宋体", Font.BOLD, 40));
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
	     
	
	
	
}
package cn.powerrun.locate.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Calendar;

import javax.imageio.ImageIO;

public class ImageDeal {
	String openUrl;
	String saveUrl;
	String saveName;
	String suffix;
	
	public ImageDeal(String openUrl, String saveUrl, String saveName, String suffix) {
		super();
		this.openUrl = openUrl;
		this.saveUrl = saveUrl;
		this.saveName = saveName;
		this.suffix = suffix;
	}
	
	//图片旋转
	public void spin(int degree) throws Exception {
		int swidth = 0;          //旋转后的宽度
		int sheight = 0;         //旋转后的高度
		int x;                   //原点横坐标
		int y;                   //原点纵坐标
		
		File file = new File(openUrl);
		if(!file.isFile()) {
			throw new Exception(file + "不是一个图片文件");
			
		}
		
		BufferedImage bi = ImageIO.read(file);
		//处理角度 -- 确定旋转的弧度
		degree = degree % 360;
		if(degree < 0) {
			degree = 360 + degree;   //将角度切换到0-360度之间
		}
		double theta = Math.toRadians(degree);
		
		//确定旋转后的宽和高
		if(degree == 180 || degree == 0 || degree == 360) {
			swidth = bi.getWidth();
			sheight = bi.getHeight();
			
		}else if(degree == 90 || degree == 270) {
			swidth = bi.getHeight();
			sheight = bi.getWidth();
		}else {
			swidth = (int) (Math.sqrt(bi.getWidth() * bi.getWidth()
                    + bi.getHeight() * bi.getHeight()));
            sheight = (int) (Math.sqrt(bi.getWidth() * bi.getWidth()
                    + bi.getHeight() * bi.getHeight()));
		}
System.out.println("旋转后的宽为:"+swidth + "  旋转后的高为:"+sheight);
		
		x = (swidth/2) - (bi.getWidth()/2);      //确定原点坐标
		y = (sheight/2) - (bi.getHeight()/2);
System.out.println("原点坐标为:("+x+","+y+")1");
		BufferedImage spinImage = new BufferedImage(swidth,sheight,bi.getType());
		//设置图片背景颜色
		Graphics2D gs = (Graphics2D) spinImage.getGraphics();
		gs.setColor(Color.white);
		gs.fillRect(0, 0, swidth, sheight);
		
		AffineTransform at = new AffineTransform();
		at.rotate(theta,swidth/2,sheight/2);    //旋转图像
		at.translate(x, y);
		AffineTransformOp op = new AffineTransformOp(at,AffineTransformOp.TYPE_BICUBIC);
		
		spinImage = op.filter(bi, spinImage);
		File sf = new File(saveUrl,saveName + "-正北图." + suffix);
		ImageIO.write(spinImage, suffix, sf);
	}
	public static void main(String[] args) {
		String openUrl = "D:"+File.separator+"git_repository_beta"+File.separator+"repository_2"+File.separator+"CoordinateTransfer"+File.separator+"image"+File.separator+"恒信花园A公用配电站.jpg";
		String saveUrl = "D:"+File.separator+"git_repository_beta"+File.separator+"repository_2"+File.separator+"CoordinateTransfer"+File.separator+"image"+File.separator;;
		String saveName = "my";
		String suffix = "jpg";
		ImageDeal imageDeal = new ImageDeal(openUrl,saveUrl,saveName,suffix);
		try {
			imageDeal.spin(45);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

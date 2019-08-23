package cn.powerrun.locate.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FileDeal {
	public static void autoReplace(String filePath,String outPath,double[] d) throws IOException {
		File file = new File(filePath);
		Long fileLength = file.length();
		byte[] fileContext = new byte[fileLength.intValue()];
		
		FileInputStream in = new FileInputStream(filePath);
		
		in.read(fileContext);
		in.close();
		String str = new String(fileContext);
System.out.println(str);
str = str.replace("package cn.powerrun.locate.template;", "package cn.powerrun.result;");
		str = str.replace("private double dX;", "private double dX = "+d[1]+";");
		str = str.replace("private double dY;", "private double dY = "+d[2]+";");
		str = str.replace("private transient double mapDeflectionTheta;", "private transient double mapDeflectionTheta = "+d[0]+";");
		str = str.replace("private double[] gpsZeroPoint;", "private double[] gpsZeroPoint = {"+d[3]+","+d[4]+"};");
System.out.println("-----------");
System.out.println(str);
		PrintWriter out = new PrintWriter(outPath);
		out.write(str.toCharArray());
		out.flush();
		out.close();
		
	}
}

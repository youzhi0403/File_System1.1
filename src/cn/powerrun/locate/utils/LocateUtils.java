package cn.powerrun.locate.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.powerrun.locate.model.Coordinates;
import cn.powerrun.locate.model.GPSCoordinate;
import cn.powerrun.locate.model.JsonObj;
import cn.powerrun.locate.model.PixelCoordinate;
import cn.powerrun.utils.EncodingDetect;

public class LocateUtils {
	final public static String tempDir = "D:"+File.separator+"eclipse_workspace"+File.separator+"File_System1.1";
	
	final public static String dir = "D:" + File.separator + "workspace" + File.separator + "PowerRunWeb";
	
	public static List<String> locate(File img,File json,String regionName) throws Exception {
		List<String> result = new ArrayList();
		
		String content = "";
		content = FileUtils.readFileToString(json, EncodingDetect.detect(json));
		JSONObject jsonObject = new JSONObject(content);
		String name = jsonObject.getString("image").substring(0, jsonObject.getString("image").indexOf("."));
		JsonObj taiQu = new JsonObj();
		List<Coordinates> drawPoints = new ArrayList();
		JSONArray jsonArray = (JSONArray) jsonObject.get("pointList");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject j = (JSONObject) jsonArray.get(i);
			GPSCoordinate g = new GPSCoordinate(j.getDouble("longitude"), j.getDouble("latitude"));
			PixelCoordinate p = new PixelCoordinate(j.getInt("mapX"), j.getInt("mapY"));
			Coordinates c = new Coordinates(p, g);
			drawPoints.add(c);
		}
		taiQu.setDrawPoints(drawPoints);
		double[] d = taiQu.getResult();
System.out.println("平均角度:"+Arrays.toString(d));

		String filePath = tempDir + File.separator + "src" + File.separator + "cn" + File.separator + "powerrun" + File.separator + "locate" + File.separator + "template" + File.separator + "TaiQuMapInfo.java";
		String resultPath = tempDir + File.separator + "src" + File.separator + "cn" + File.separator + "powerrun" + File.separator + "locate" + File.separator + "result" + File.separator + "TaiQuMapInfo.java";;
		FileDeal.autoReplace(filePath, resultPath, d);
		
		String outputPath = dir + File.separator + regionName;
		
		String jsonPath = outputJson(d,outputPath + File.separator + name + ".json");
		
		String sourcePath = tempDir + File.separator + "src" + File.separator + "cn" + File.separator + "powerrun" + File.separator + "locate" + File.separator + "result";
		String classPath = tempDir + File.separator + "classes";
		//拿到数据后,输出jar包
		String jarPath = outputJar(DateUtils.getDateFileName()+".jar",dir + File.separator + regionName + File.separator,classPath,sourcePath);
		//拿到数据后,输出正北图
		String imgPath = outputImage(d[0],img.getPath(),outputPath + File.separator,name,"jpg");
		result.add(jsonPath);
		result.add(jarPath);
		result.add(imgPath);
		return result;
		
		
}
	
	public static String outputJson(double[] d,String output) throws FileNotFoundException{
		JSONObject result = new JSONObject();
		result.put("angle", d[0]);
		result.put("dX", d[1]);
		result.put("dY", d[2]);
		result.put("zeroX", d[3]);
		result.put("zeroY", d[4]);
		
		PrintWriter out = new PrintWriter(output);
		out.write(result.toString());
		out.flush();
		out.close();
		return output;
	}
	
	public static String outputJar(String name,String output,String classPath,String sourcePath) {
		JarUtils.createJAR(sourcePath,classPath,output,name);
		return output+name;
		
	}
	
	public static String outputImage(double angle,String openUrl,String saveUrl,String name,String suffix) throws Exception {
		ImageDeal imageDeal = new ImageDeal(openUrl,saveUrl,name,suffix);
		imageDeal.spin((int) angle);
		return saveUrl + name + "-正北图." + suffix;
	}
	
	public static void main(String[] args) throws IOException {
		File directory = new File("");
		System.out.println(directory.getCanonicalPath());
	}
}

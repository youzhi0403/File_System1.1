package cn.powerrun.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.powerrun.model.Pagination;

public class FileUtils {
	final public static String dir = "D:" + File.separator + "workspace" + File.separator + "PowerRunWeb";// 应当在程序执行时可修改
	final public static String tempPath = FileUtils.dir + File.separator + "temp";

	/**
	 * 拼接路径
	 * 
	 * @param RootDir
	 * @param AddPath
	 * @return
	 */
	public static String makePath(String RootDir, String AddPath) {
		if (StringUtil.isEmpty(AddPath)) {
			return RootDir;
		} else {
			return RootDir + File.separator + AddPath;
		}
	}

	public static JSONObject getFileList(String path, Pagination pagination) {
		int index = 0;
		JSONObject result = new JSONObject();
		File file = new File(path);// File类型可以是文件也可以是文件夹
		File[] fileList = file.listFiles();// 将该目录下的所有文件放置在一个File类型的数组中
		JSONArray array = new JSONArray();// 新建一个文件集合
		for (int i = pagination.getCurrentPage(); i < fileList.length && index < pagination.getRows(); i++) {
			index++;
			if (fileList[i].isFile()) {// 判断是否为文件
				JSONObject tempjsoj = new JSONObject();
				tempjsoj.put("name", fileList[i].getName()); // 名字
				tempjsoj.put("size", getPrintSize(fileList[i].length())); // 大小
				tempjsoj.put("modifiedTime", getModifiedTime(fileList[i])); // 修改时间
				tempjsoj.put("type", "file");
				tempjsoj.put("id", i + 1);
				array.put(tempjsoj);
			} else if (fileList[i].isDirectory()) {
				JSONObject tempjsoj = new JSONObject();
				tempjsoj.put("name", fileList[i].getName()); // 名字
				tempjsoj.put("modifiedTime", getModifiedTime(fileList[i])); // 修改时间
				tempjsoj.put("type", "directory");
				tempjsoj.put("id", i + 1);
				array.put(tempjsoj);
			}
		}
		result.put("rows", array);
		result.put("total", fileList.length);
		return result;

	}

	public static String getPrintSize(long size) {
		// 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
		double value = (double) size;
		if (value < 1024) {
			return String.valueOf(value) + "B";
		} else {
			value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		}
		// 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
		// 因为还没有到达要使用另一个单位的时候
		// 接下去以此类推
		if (value < 1024) {
			return String.valueOf(value) + "KB";
		} else {
			value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		}
		if (value < 1024) {
			return String.valueOf(value) + "MB";
		} else {
			// 否则如果要以GB为单位的，先除于1024再作同样的处理
			value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
			return String.valueOf(value) + "GB";
		}
	}

	/**
	 * 读取文件的修改时间
	 * 
	 * @param f
	 * @return
	 */
	public static String getModifiedTime(File f) {
		Calendar cal = Calendar.getInstance();
		long time = f.lastModified();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		cal.setTimeInMillis(time);
		return formatter.format(cal.getTime());
	}

	public static boolean isRepeat(File directory, String newDirectory) {
		File[] fileList = directory.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				if (fileList[i].getName().equals(newDirectory)) {
					return true;
				}
			}
		}
		return false;
	}

	// 封装压缩文件的方法
	public static void zipFile(File inputFile, ZipOutputStream zipoutputStream) {
		try {
			if (inputFile.exists()) { // 判断文件是否存在
				if (inputFile.isFile()) { // 判断是否属于文件，还是文件夹
					// 创建输入流读取文件
					FileInputStream fis = new FileInputStream(inputFile);
					BufferedInputStream bis = new BufferedInputStream(fis);

					// 将文件写入zip内,即将文件进行打包
					ZipEntry ze = new ZipEntry(inputFile.getName());
					zipoutputStream.putNextEntry(ze);

					// 写入文件的方法，同上
					byte[] b = new byte[1024];
					long l = 0;
					while (l < inputFile.length()) {
						int j = bis.read(b, 0, 1024);
						l += j;
						zipoutputStream.write(b, 0, j);

					}
					// 关闭输入输出流
					bis.close();
					fis.close();
				} else {
					try {
						File[] files = inputFile.listFiles();
						for (int i = 0; i < files.length; i++) {
							zipFile(files[i], zipoutputStream);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// IO字节流下载文件,用于小文件
	public static void outputFile(File file, HttpServletResponse response) throws IOException {
		InputStream is = new FileInputStream(file);
		OutputStream out = response.getOutputStream();
		byte[] buffer = new byte[2048];
		int size = 0;
		while ((size = is.read(buffer)) != -1) {
			out.write(buffer, 0, size);
		}
		out.flush();
		out.close();
	}

	// IO字符流下载,用于大文件
	public static void outputFile2(File file, HttpServletResponse response) throws IOException {
		OutputStream out = response.getOutputStream();
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream buff = new BufferedInputStream(fis);
		byte[] b = new byte[2048];
		long l = 0;
		while (l < file.length()) {
			int j = buff.read(b, 0, 2048);
			l += j;
			out.write(b, 0, j);
		}
		out.flush();
		out.close();

	}

	/*
	 * 上传文件
	 */
	public static Map<String, String> uploadFiles(HttpServletRequest request) {
		Map<String, String> result = new HashMap();
		List<FileItem> tempFile = new ArrayList();

		File tempPathDirectory = new File(tempPath);

		try {
			// 创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();

			factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
			factory.setRepository(tempPathDirectory);// 设置缓冲区目录

			// 创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");

			upload.setSizeMax(419430400); // 设置最大文件尺寸，这里是400MB

			List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
			Iterator<FileItem> i = items.iterator();
			int index = 1;
			while (i.hasNext()) {
				FileItem fi = i.next();
				if (fi.isFormField()) {
					String name = fi.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = fi.getString("UTF-8");
					result.put(name, value);

				} else {
					String fileName = fi.getName();

					if (fileName != null || fileName.trim().equals("")) {
						tempFile.add(fi);
					}
				}

			}
			// 获取regionName
			String regionName = result.get("regionName");
			if (regionName == null || regionName.trim().equals("")) {
				return null;
			} else {
				String myDir = dir + File.separator + regionName;
				File myFile = new File(myDir);
				if (!myFile.exists()) {
					myFile.mkdirs();
				}
				for (FileItem item : tempFile) {
					String fileName = new String(item.getName().getBytes(), "utf-8");
					File f = new File(myDir + File.separator + fileName);
					result.put("file"+index, f.getAbsolutePath());
					item.write(f);
					index++;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 获取文件的文本内容
	 * @param filePath
	 * @return
	 * @throws IOException 
	 */
	public static String readFiles(String filePath) throws IOException{
		StringBuffer sb = new StringBuffer();
		readToBuffer(sb,filePath);
		return sb.toString();
	}
	
	public static void readToBuffer(StringBuffer buffer,String filePath) throws IOException{
		InputStream is = new FileInputStream(filePath);
		String line; //用来保存每行读取的内容
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		line = reader.readLine(); //读取第一行
		while(line != null){
			buffer.append(line);
			buffer.append("\n");
			line = reader.readLine();
		}
		reader.close();
		is.close();
		
	}

}

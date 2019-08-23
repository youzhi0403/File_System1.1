package cn.powerrun.locate.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JarUtils {
	public static void createJAR(String sourcePath, String classPath, String outPath, String jarName) {
		File classFile = new File(classPath);
		BufferedInputStream in = null;
		try {
			if (!classFile.exists()) {
				classFile.mkdirs();

			}
			File file = new File(sourcePath);//
			if(file.isDirectory()) {
				System.out.println(true);
			}
			File[] listFiles = file.listFiles();
			ArrayList<String> list = new ArrayList();
			for (File file2 : listFiles) {
				list.add(file2.getPath());
			}
			JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
			int result = -1;
			for (int i = 0; list.size() > 0 && i < list.size(); i++) {
				result = javaCompiler.run(null, null, null, "-d", classPath, list.get(i));
			}
			// 结果为0,说明编译成功,可以打包
			if (result == 0) {
				compress(classPath, outPath + jarName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	public static void compress(String dir, String jarName) throws IOException {
		File folderObject = new File(dir);
		if (folderObject.exists()) {
			List fileList = getSubFiles(new File(dir));
			// 压缩文件名
			Manifest manifest = new Manifest();
			manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
			FileOutputStream fos = new FileOutputStream(jarName);
			JarOutputStream zos = new JarOutputStream(fos, manifest);
			JarEntry ze = null;
			byte[] buf = new byte[1024];

			int readLen = 0;
			for (int i = 0; i < fileList.size(); i++) {
				File f = (File) fileList.get(i);
				ze = new JarEntry(getAbsFileName(dir, f));
				ze.setSize(f.length());
				ze.setTime(f.lastModified());
				zos.putNextEntry(ze);
				InputStream is = new BufferedInputStream(new FileInputStream(f));
				while ((readLen = is.read(buf, 0, 1024)) != -1) {
					zos.write(buf, 0, readLen);

				}
				is.close();

			}
			zos.close();

		}
	}

	private static List getSubFiles(File baseDir) {
		List fileList = new ArrayList();
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].isFile()) {
				fileList.add(tmp[i]);
			}
			if (tmp[i].isDirectory()) {
				fileList.addAll(getSubFiles(tmp[i]));
			}
		}
		return fileList;
	}

	private static String getAbsFileName(String baseDir, File realFileName) {
		File real = realFileName;
		File base = new File(baseDir);
		String ret = real.getName();
		while (true) {
			real = real.getParentFile();
			if (real == null) {
				break;

			}
			if (real.equals(base)) {
				break;
			} else {
				ret = real.getName() + "/" + ret;
			}
		}
		return ret;
	}
}

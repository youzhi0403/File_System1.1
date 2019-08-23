package cn.powerrun.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.powerrun.model.Region;
import cn.powerrun.service.IRegionService;
import cn.powerrun.service.impl.RegionServiceImpl;

public class ImageServlet extends HttpServlet{
	IRegionService regionService = new RegionServiceImpl();	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String regionId = req.getParameter("regionId");
		
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			Region region = regionService.regionQueryById(regionId);
			fis = new FileInputStream(region.getLocate().getNorthPhoto());
			os = resp.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024*8];
			while((count = fis.read(buffer)) != -1){
				os.write(buffer,0,count);
				os.flush();
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}

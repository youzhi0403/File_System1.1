package cn.powerrun.locate.utils;

public class LocationUtils {
	public static double[] fun(double[] pixA, double[] pixB, double[] gpsA, double[] gpsB) {
		double[] result = new double[5];
		double[] center=new double[2];
		center[0]=2000;
		center[1]=1500;
		
		double[] gpsP = new double[2];
		gpsP[0] = gpsB[0] - gpsA[0];
		gpsP[1] = gpsB[1] - gpsA[1];
		int gpsPXiangXian = XiangXian(gpsP);
		double gpsRate = Math.abs((gpsP[0] / 1.09) / gpsP[1]);
		double[] pixP = new double[2];
		double[] pixQ = new double[2];
		pixP[0] = pixB[0] - pixA[0];
		pixP[1] = (pixB[1] - pixA[1]) * -1;
		boolean find = false;
		double d = 0.01;
		while (find == false) {
			for (double angleB = 0; angleB < 360; angleB += 0.1) {
				pixQ[0] = (pixP[0] * Math.cos(angleB * Math.PI / 180) - pixP[1] * Math.sin(angleB * Math.PI / 180));
				pixQ[1] = (pixP[0] * Math.sin(angleB * Math.PI / 180) + pixP[1] * Math.cos(angleB * Math.PI / 180));
				// System.out.print(angleB);show(pixQ);
				int piQXiangxian = XiangXian(pixQ);
				double pixRate = Math.abs(pixQ[0] / pixQ[1]);
				if ((piQXiangxian == gpsPXiangXian) && (Math.abs(pixRate - gpsRate) < d)) {
					find = true;
					result[0] = dealCritical(angleB);
					// System.out.println(Math.abs(pixRate - gpsRate));
					double RateGPSx_jingdu = (gpsP[0] / pixQ[0]);
					double RateGPSy_weidu = (gpsP[1] / pixQ[1]);
					result[1] = RateGPSx_jingdu;
					result[2] = RateGPSy_weidu;
					double s1=center[0]-pixA[0];
					double s2=center[1]-pixA[1];
					double gpsx=s1*RateGPSx_jingdu+gpsA[0];
					double gpsy=s2*RateGPSy_weidu+gpsA[1];
					
					double gpsCenterX=(center[0]-pixA[0])*RateGPSx_jingdu+gpsA[0];
					double gpsCenterY=(center[1]-pixA[1])*RateGPSy_weidu+gpsA[1];
					double gpsZerox=(0-2500)*RateGPSx_jingdu+gpsCenterX;
					double gpsZeroy=(0-2500)*RateGPSy_weidu+gpsCenterY;
					result[3] = gpsZerox;
					result[4] = gpsZeroy;
					
					break;
				}
			}
			d += 0.01;
		}
		return result;
	}

	static void show(double[] x) {
		System.out.println("x:" + (int) x[0] + "  y:" + (int) x[1]);
	}

	static double angleIn360(double[] x, double[] y) {
		double xr = Math.sqrt(x[0] * x[0] + x[1] + x[1]);
		double yr = Math.sqrt(y[0] * y[0] + y[1] + y[1]);
		double angleInPi = Math.acos((x[0] * y[0] + x[1] * y[1]) / (xr * yr));
		return angleInPi * Math.PI / 180;

	}

	static int XiangXian(double[] x) {
		if (x[0] >= 0 && x[1] >= 0)
			return 1;
		else if (x[0] <= 0 && x[1] >= 0)
			return 2;
		else if (x[0] <= 0 && x[1] <= 0)
			return 3;
		else if (x[0] >= 0 && x[1] <= 0)
			return 4;
		return 0;
	}
	
	//处理角度的临界问题,角度为[330,360]的,统一转为[-30,0]
	static double dealCritical(double d) {
		String s = String.valueOf(d);
		String value = s.substring(0, s.indexOf("."));
		int i = Integer.parseInt(value);
		if(i >= 330 && i <= 360) {
			i = i - 360;
		}
		return Double.parseDouble(String.valueOf(i) + s.substring(s.indexOf("."),s.length()));
	}

}

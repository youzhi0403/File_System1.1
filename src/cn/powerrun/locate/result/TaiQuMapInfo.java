package cn.powerrun.locate.result;

public class TaiQuMapInfo {

	// 单位像素经度偏移量
	private double dX = 1.1620196122334925E-6;
	// 单位纬度偏移量
	private double dY = 1.102850513618398E-6;
	// 偏转角度
	private transient double mapDeflectionTheta = 266.1666666666587;
	// 基准点,以5000*5000的图的左上角(0,0)为基准点
	private double[] gpsZeroPoint = {113.4007072518378,22.497077606104643};
	
	private double[] pixZeroPoint = {0,0};

	public double getdX() {
		return dX;
	}

	public double getdY() {
		return dY;
	}

	public double getMapDeflectionTheta() {
		return mapDeflectionTheta;
	}

	/*
	 * param:像素坐标 return：gps坐标
	 */
	public double[] getGpsZeroPoint() {
		return gpsZeroPoint;
	}

	public double[] getPixZeroPoint() {
		return pixZeroPoint;
	}

	public double[] getGPSCoordinate(double[] pixelCoordinate) {
		double[] result = new double[2];
		result[0] = gpsZeroPoint[0] + (pixelCoordinate[0] - gpsZeroPoint[0]) * dX;
		result[1] = gpsZeroPoint[1] + (pixelCoordinate[1] - gpsZeroPoint[1]) * dY;
		return result;
	}

	/*
	 * param:gps坐标 return：像素坐标
	 */
	public double[] getPixCoordinate(double[] gpsCoordinate) {
		double[] result = new double[2];
		
		result[0] = pixZeroPoint[0] + (gpsCoordinate[0] - gpsZeroPoint[0]) / dX;
		result[1] = pixZeroPoint[1] + (gpsCoordinate[1] - gpsZeroPoint[1]) / dY;

		return result;
	}

}
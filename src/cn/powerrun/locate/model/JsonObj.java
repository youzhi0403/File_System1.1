package cn.powerrun.locate.model;

import java.util.Arrays;
import java.util.List;

import cn.powerrun.locate.utils.LocationUtils;



public class JsonObj {
	// 其他点的坐标
	private List<Coordinates> drawPoints;

	public List<Coordinates> getDrawPoints() {
		return drawPoints;
	}

	public void setDrawPoints(List<Coordinates> drawPoints) {
		this.drawPoints = drawPoints;
	}

	public double[] getResult() {
		return divide(getParams(), getResultNum());
	}

	public double[] getParams() {
		double[] sum = new double[5];
		for (int i = 0; i < drawPoints.size() - 1; i++) {
			for (int j = i + 1; j < drawPoints.size(); j++) {
				double[] pixA = { drawPoints.get(i).getPixelCoordinate().getPixelX(),
						drawPoints.get(i).getPixelCoordinate().getPixelY() };
				double[] pixB = { drawPoints.get(j).getPixelCoordinate().getPixelX(),
						drawPoints.get(j).getPixelCoordinate().getPixelY() };
				double[] gpsA = { drawPoints.get(i).getGpsCoordinate().getGpsX(),
						drawPoints.get(i).getGpsCoordinate().getGpsY() };
				double[] gpsB = { drawPoints.get(j).getGpsCoordinate().getGpsX(),
						drawPoints.get(j).getGpsCoordinate().getGpsY() };
System.out.println(Arrays.toString(LocationUtils.fun(pixA, pixB, gpsA, gpsB)));
				sum = addSum(sum, LocationUtils.fun(pixA, pixB, gpsA, gpsB));
			}
		}
		return sum;
	}

	public static double[] divide(double[] d, double j) {
		for (int i = 0; i < d.length; i++) {
			d[i] = d[i] / j;
		}
		return d;
	}

	public static double[] addSum(double[] s1, double[] s2) {
		double[] result = new double[s1.length];
		for (int i = 0; i < s1.length; i++) {
			result[i] = s1[i] + s2[i];
		}
		return result;
	}

	public double[] getParam(Coordinates c1, Coordinates c2) {
		double[] pixA = { c1.getPixelCoordinate().getPixelX(), c1.getPixelCoordinate().getPixelY() };
		double[] pixB = { c2.getPixelCoordinate().getPixelX(), c2.getPixelCoordinate().getPixelY() };
		double[] gpsA = { c1.getGpsCoordinate().getGpsX(), c1.getGpsCoordinate().getGpsY() };
		double[] gpsB = { c2.getGpsCoordinate().getGpsX(), c2.getGpsCoordinate().getGpsY() };
		return LocationUtils.fun(pixA, pixB, gpsA, gpsB);
	}

	public int getResultNum() {
		int sum = drawPoints.size();
		if (sum == 0) {
			return 0;
		} else if (sum == 1 || sum == 2) {
			return 1;
		} else {
			int result = 0;
			for (int i = 1; i < sum; i++) {
				result += i;
			}
			return result;
		}
	}
}

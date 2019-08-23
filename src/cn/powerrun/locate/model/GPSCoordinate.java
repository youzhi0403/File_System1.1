package cn.powerrun.locate.model;
@SuppressWarnings("unchecked")
public class GPSCoordinate {
	private double gpsX;
	private double gpsY;

	public GPSCoordinate() {
		super();
	}

	public GPSCoordinate(double gpsX, double gpsY) {
		super();
		this.gpsX = gpsX;
		this.gpsY = gpsY;
	}

	public double getGpsX() {
		return gpsX;
	}

	public void setGpsX(double gpsX) {
		this.gpsX = gpsX;
	}

	public double getGpsY() {
		return gpsY;
	}

	public void setGpsY(double gpsY) {
		this.gpsY = gpsY;
	}

	@Override
	public String toString() {
		return "GPSCoordinate [gpsX=" + gpsX + ", gpsY=" + gpsY + "]";
	}

}

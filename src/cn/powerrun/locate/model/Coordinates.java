package cn.powerrun.locate.model;
@SuppressWarnings("unchecked")
public class Coordinates {
	private PixelCoordinate pixelCoordinate;
	private GPSCoordinate gpsCoordinate;

	public Coordinates() {
		super();
	}

	public Coordinates(PixelCoordinate pixelCoordinate, GPSCoordinate gpsCoordinate) {
		super();
		this.pixelCoordinate = pixelCoordinate;
		this.gpsCoordinate = gpsCoordinate;
	}

	public PixelCoordinate getPixelCoordinate() {
		return pixelCoordinate;
	}

	public void setPixelCoordinate(PixelCoordinate pixelCoordinate) {
		this.pixelCoordinate = pixelCoordinate;
	}

	public GPSCoordinate getGpsCoordinate() {
		return gpsCoordinate;
	}

	public void setGpsCoordinate(GPSCoordinate gpsCoordinate) {
		this.gpsCoordinate = gpsCoordinate;
	}

	@Override
	public String toString() {
		return "Coordinates [pixelCoordinate=" + pixelCoordinate + ", gpsCoordinate=" + gpsCoordinate + "]";
	}

}

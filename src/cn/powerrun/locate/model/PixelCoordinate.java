package cn.powerrun.locate.model;
@SuppressWarnings("unchecked")
public class PixelCoordinate {
	private double pixelX;
	private double pixelY;

	public PixelCoordinate() {
		super();
	}

	public PixelCoordinate(double pixelX, double pixelY) {
		super();
		this.pixelX = pixelX;
		this.pixelY = pixelY;
	}

	public double getPixelX() {
		return pixelX;
	}

	public void setPixelX(double pixelX) {
		this.pixelX = pixelX;
	}

	public double getPixelY() {
		return pixelY;
	}

	public void setPixelY(double pixelY) {
		this.pixelY = pixelY;
	}

	@Override
	public String toString() {
		return "PixelCoordinate [pixelX=" + pixelX + ", pixelY=" + pixelY + "]";
	}

}

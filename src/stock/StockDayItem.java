package stock;

public class StockDayItem {
	private String date;
	private float open, hight, low, close, volume;
	private DrawableStock drawableStock;
	
	
	public DrawableStock getDrawableStock() {
		return drawableStock;
	}

	public void setDrawableStock(DrawableStock drawableStock) {
		this.drawableStock = drawableStock;
	}

	public StockDayItem(String date, String open, String hight, String low, String close, String volume) {
		this.date = date;
		this.open = Float.parseFloat(open);
		this.hight = Float.parseFloat(hight);
		this.low = Float.parseFloat(low);
		this.close = Float.parseFloat(close);
		this.volume = Float.parseFloat(volume);
	}

	public String getDate() {
		return date;
	}

	public float getOpen() {
		return open;
	}

	public float getHight() {
		return hight;
	}

	public float getLow() {
		return low;
	}

	public float getClose() {
		return close;
	}

	public float getVolume() {
		return volume;
	}

}


package test;

import java.util.ArrayList;

import stock.StockDayItem;
import tool.MyRequest;
import tool.MyURL;
import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

import stock.DrawableStock;
import stock.Line;
import stock.Rectangle;
import stock.StockDayItem;

public class ArrayTest {
	 public static int WIDTH = 800, HEIGHT = 600;
	 public static int HEADERHEIGHT = 400; 
	 public static int STEPWIDTH = 10; 

	  
	public static void main(String[] args) {
		ArrayList<StockDayItem> dayItems;
		String feedback = MyRequest.get(new MyURL("AAPL","10","01/01/2018","12/31/2018"));
		Parse parse = new Parse();
		dayItems = parse.paeseStockDataToArraylist(feedback);
		dayItems = parse.processToDrawable(dayItems, HEIGHT, HEADERHEIGHT, STEPWIDTH);
		
		
		
		for (StockDayItem item: dayItems) {
			System.out.println(item.getDrawableStock().getRectangle().height);
//			System.out.println(item.getHight() + " " + parse.pscale +  " " +  parse.pmiddle);
//			System.out.println(parse.doNormalisationAndSaveToInt(item.getHight(), 100f, parse.pscale, parse.pmiddle));
		}
		
//		String[] a = {"1","2","3","4","5","6"};
//		normalisation(a);
//		System.out.println();
	}
	
	
}

class Parse {
	public float pmax= Float.MIN_VALUE, pmin=Float.MAX_VALUE, pmiddle, pscale; // These variables are price's parameters of normalisation
	public float vmax= Float.MIN_VALUE, vmin= Float.MAX_VALUE, vmiddle, vscale; // These variables are volume's parameters of normalisation
	
	public ArrayList<StockDayItem> paeseStockDataToArraylist (String data) {
		ArrayList<StockDayItem> stockDayItems = new ArrayList<StockDayItem>();
		String[] dataitem = data.split("\n");
		
		for(int i =1; i<dataitem.length; i++) {
			String[] items = dataitem[i].split(",");
			stockDayItems.add(new StockDayItem(items[0], items[1], items[2], items[3], items[4], items[5]));
			normalisation(items);
		}
		
//		for(int i =1; i<5; i++) {
//			String[] items = dataitem[i].split(",");
//			stockDayItems.add(new StockDayItem(items[0], items[1], items[2], items[3], items[4], items[5]));
//			normalisation(items);
//		}
		pmiddle = (pmax + pmin) / 2;
		vmiddle = 0;
		pscale = pmax - pmin;
		vscale = vmax - vmin;
		return stockDayItems;
	}
	
	// This function would be complex because all the stock information would be calculated right here and build the drawing information
	// All data type would be translate into Integer
	public ArrayList<StockDayItem> processToDrawable(ArrayList<StockDayItem> items, int height, int headerHeight, int stepWidth) { 
		// The height is the canvas', the headerHeight is the header canvas'
		int widthMiddle = stepWidth/2;
		int headerHeightMiddle = headerHeight/2;
		int footerHeight = height-headerHeight;
		int footerHeightMiddle = footerHeight/2;
		int itemWidth = 2*stepWidth/3;
		float lastClose = 0;
		
		for(StockDayItem data: items) { // Calculate the Candlestick chart
			DrawableStock drawableStock = new DrawableStock();
			Line line = new Line();		// Candle line
			line.startX = widthMiddle;
			line.endX = widthMiddle;
			line.startY = headerHeightMiddle - doNormalisationAndSaveToInt(data.getHight(), headerHeight, pscale, pmiddle);
			line.endY = headerHeightMiddle - doNormalisationAndSaveToInt(data.getLow(), headerHeight, pscale, pmiddle);
			
			Rectangle rectangle = new Rectangle(); // Candle body
			rectangle.x = widthMiddle - itemWidth/2;
			if (data.getOpen()>data.getClose()) {
				rectangle.y = headerHeightMiddle - doNormalisationAndSaveToInt(data.getOpen(), headerHeight, pscale, pmiddle);
			} else {
				rectangle.y = headerHeightMiddle - doNormalisationAndSaveToInt(data.getClose(), headerHeight, pscale, pmiddle);
			} 
			rectangle.width = itemWidth;
			float normalClose = doNormalisationAndSaveToFloat(data.getClose(), 1, pscale, pmiddle);
			float normalOpen = doNormalisationAndSaveToFloat(data.getOpen(), 1, pscale, pmiddle);
			rectangle.height = Math.round(Math.abs(normalClose-normalOpen)*headerHeight);
			
			Rectangle volumeRectangle = new Rectangle(); // Volume 
			volumeRectangle.x = widthMiddle - itemWidth/2;
			volumeRectangle.y = footerHeight - doNormalisationAndSaveToInt(data.getVolume(), headerHeight, vscale, vmiddle);
			volumeRectangle.width = itemWidth;
			volumeRectangle.height = doNormalisationAndSaveToInt(data.getVolume(), headerHeight, vscale, vmiddle);
			
			Color color; 
			if(data.getClose() > lastClose) {
				color = new Color(255, 0, 0);
			} else {
				color = new Color(0, 255, 0);
			}
			lastClose = data.getClose();
			
			drawableStock.setLine(line);
			drawableStock.setRectangle(rectangle);
			drawableStock.setVolumeRectangle(volumeRectangle);
			drawableStock.setColor(color);
			
			data.setDrawableStock(drawableStock);
		}
		
		return items;
	}
	
	// This function would do any operation on original data, it only calculate the parameters of normalisation.
	void normalisation(String[] items) {
		for(int i=1; i<=4; i++) {
			pmax = Float.parseFloat(items[i])>pmax?Float.parseFloat(items[i]):pmax;
			pmin = Float.parseFloat(items[i])<pmin?Float.parseFloat(items[i]):pmin;
		}
		vmax = Float.parseFloat(items[5])>vmax?Float.parseFloat(items[5]):vmax;
		vmin = Float.parseFloat(items[5])<vmin?Float.parseFloat(items[5]):vmin;
	}
	
	// This function would do normalisation for the data according to the parameters calculated before and finally transfer to integer
	public int doNormalisationAndSaveToInt(float data, float standScale, float nScale, float middle) {
		data = standScale*(data-middle)/nScale;
		return (int)Math.round(data);
	}
	
	public float doNormalisationAndSaveToFloat(float data, float standScale, float nScale, float middle) {
		data = standScale*(data-middle)/nScale;
		return data;
	}
	
}

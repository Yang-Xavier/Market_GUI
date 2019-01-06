package tool;

import java.util.List;
import java.util.ArrayList;

import stock.StockDayItem;


/**
   This parse tool is mainly to process the stock inquiring data and store into the object StockDayItem then collect them into an ArrayList.
 */
public class Parse {
	private float pmax= Float.MIN_VALUE, pmin=Float.MAX_VALUE, pmiddle, pscale; // These variables are price's parameters of normalisation
	private float vmax= Float.MIN_VALUE, vmin= Float.MAX_VALUE, vmiddle, vscale; // These variables are volume's parameters of normalisation
	
	public ArrayList<StockDayItem> paeseStockDataToArraylist (String data) {
		ArrayList<StockDayItem> stockDayItems = new ArrayList<StockDayItem>();
		String[] dataitem = data.split("\n");
		
		for(int i =1; i<dataitem.length; i++) {
			String[] items = dataitem[i].split(",");
			stockDayItems.add(new StockDayItem(items[0], items[1], items[2], items[3], items[4], items[5]));
			normalisation(items);
		}
		pmiddle = (pmax + pmin) / 2;
		vmiddle = (vmax + vmin) / 2;
		pscale = pmax - pmin;
		vscale = vmax - vmin;
		return stockDayItems;
	}
	
	public ArrayList<StockDayItem> processToDraw(ArrayList<StockDayItem> items, int height, int headerHeight, float stepWidth) { 
		// The height is the canvas', the headerHeight is the header canvas'
		
		return items;
	}
	
	void normalisation(String[] items) {
		for(int i=1; i<=4; i++) {
			pmax = Float.parseFloat(items[i])>pmax?Float.parseFloat(items[i]):pmax;
			pmin = Float.parseFloat(items[i])<pmin?Float.parseFloat(items[i]):pmin;
		}
		vmax = Float.parseFloat(items[5])>vmax?Float.parseFloat(items[5]):vmax;
		vmin = Float.parseFloat(items[5])<vmin?Float.parseFloat(items[5]):vmin;
	}
	
}

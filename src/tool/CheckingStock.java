package tool;

public class CheckingStock {
	private String tiker, stockName,  numRows,  start,  end;
	
	public void setTiker(String tiker, String stockName) {
		this.tiker = tiker;
		this.stockName = stockName;
	}
	
	public void setNumRows(int numRows) {
		this.numRows = numRows+"";
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	
	public String getTicker() {
		return tiker;
	}
	
	public String getStockName() {
		return stockName;
	}
	
	public String getNumRows() {
		return numRows;
	}
	
	public String getStart() {
		return start;
	}
	
	public String getEnd() {
		return end;
	}
}

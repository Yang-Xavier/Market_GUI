package tool;

public class CheckingStock {
	private String tiker,  numRows,  start,  end;
	
	public void setTiker(String tiker) {
		this.tiker = tiker;
	}
	
	public void setNumRows(String numRows) {
		this.numRows = numRows;
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

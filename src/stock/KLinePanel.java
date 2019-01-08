package stock;

import javax.swing.JPanel;

public class KLinePanel extends JPanel{
	Rectangle rectangle; 
	Line line;
	
	public KLinePanel(Rectangle rectangle, Line line) {
		this.rectangle = rectangle;
		this.line = line;
	}
	
	
}

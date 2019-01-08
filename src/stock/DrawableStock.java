package stock;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * This class contain the attribute about the graph which contains the K-Line graph panel and volume graph panel
 * */

public class DrawableStock {
	Line line;
	Rectangle rectangle;
	Rectangle volumeRectangle;
	Color color;
	
	JPanel itemPanel;
	
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Rectangle getVolumeRectangle() {
		return volumeRectangle;
	}
	public void setVolumeRectangle(Rectangle volumeRectangle) {
		this.volumeRectangle = volumeRectangle;
	}
	public JPanel getItemPanel() {
		return itemPanel;
	}
	public void setItemPanel(JPanel itemPanel) {
		this.itemPanel = itemPanel;
	}
	
}

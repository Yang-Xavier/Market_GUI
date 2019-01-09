package stock;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/** This class would create the K-Line panel which already contain the K-Line Graph*/

public class KLinePanel extends JPanel{
	Rectangle rectangle; 
	Line line;
	Color color;
	
	public KLinePanel(Rectangle rectangle, Line line, Color color) {
		this.rectangle = rectangle;
		this.line = line;
		this.color = color;
		
		setOpaque(false);
		setBackground(null);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(color);
		g2d.drawLine(line.startX, line.startY, line.endX, line.endY);
		g2d.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		
	}
}

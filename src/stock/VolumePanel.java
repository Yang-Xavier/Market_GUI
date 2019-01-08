package stock;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/** This class would create the K-Line panel which already contain the K-Line Graph*/

public class VolumePanel extends JPanel{
	Rectangle volumeRectangle;
	Color color;
	
	public VolumePanel(Rectangle volumeRectangle, Color color) {
		this.volumeRectangle = volumeRectangle;
		this.color = color;
		
		setOpaque(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(color);
		g2d.fillRect(volumeRectangle.x, volumeRectangle.y, volumeRectangle.width, volumeRectangle.height);
	}
}

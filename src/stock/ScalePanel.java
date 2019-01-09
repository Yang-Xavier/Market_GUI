package stock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**This is the price and volume scale panel on the left*/

public class ScalePanel extends JPanel{
	JPanel priceScalePanel;
	JPanel volumeScalePanel;
	
	final private int WIDTH = 50;
	
	public ScalePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void setPriceScalePanel(float max, float min, int height, int scaleN) {
		priceScalePanel = new JPanel();
		priceScalePanel.setLayout(null);
		priceScalePanel.setPreferredSize(new Dimension(WIDTH, height));
		
		for(int i=0;i<scaleN;i++) {
			JLabel label = new JLabel();
			int ds = Math.round(min+ i*(max-min)/((float)scaleN-1)); 
			int dh = Math.round(i*(height)/(scaleN-1)); 
			label.setText(""+ds);
			label.setBounds(0, height-dh, WIDTH, 10);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			priceScalePanel.add(label);
		}
		JLabel label = new JLabel("Price/$");
		EmptyLine line=new EmptyLine(0, 0);
		EmptyLine line2=new EmptyLine(0,0);
		line.add(label);
		this.add(line);
		this.add(line2);
		this.add(priceScalePanel);
	}
	
	public void setVolumeScalePanel(float max, float min, int height, int scaleN) {
		volumeScalePanel = new JPanel();
		volumeScalePanel.setLayout(null);
		volumeScalePanel.setPreferredSize(new Dimension(WIDTH, height));
		
		for(int i=0;i<scaleN;i++) {
			JLabel label = new JLabel();
			int ds = Math.round((min+ i*(max-min)/((float)scaleN-1))/1000000); 
			int dh = Math.round(i*(height)/(scaleN-1)); 
			
			label.setText(""+ds);
			label.setBounds(0, height-dh, WIDTH, 10);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			volumeScalePanel.add(label);
		}
		JLabel label = new JLabel("Vol./M");
		EmptyLine line=new EmptyLine(0, 0);
		EmptyLine line2=new EmptyLine(0,0);
		line.add(label);
		this.add(line);
		this.add(line2);
		this.add(volumeScalePanel);
	}
	
	public JPanel getPriceScalePanel() {
		return this.priceScalePanel;
	}
	
	public JPanel getVolumeScalePanel() {
		return this.volumeScalePanel;
	}
	
}

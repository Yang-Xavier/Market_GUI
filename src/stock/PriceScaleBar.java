package stock;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

/**This is the price and volume scale panel on the left*/

public class PriceScaleBar extends JPanel{
	JPanel priceScalePanel;
	JPanel volumeScalePanel;
	
	final private int WIDTH = 50;
	final private int MARGIN = 20;
	
	public PriceScaleBar() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(null);
	}
	
	public void setPriceScalePanel(float max, float min, int height, int scaleN) {
		priceScalePanel = new JPanel();
		priceScalePanel.setLayout(null);
		priceScalePanel.setPreferredSize(new Dimension(WIDTH, height));
		priceScalePanel.setBackground(null);

		JLabel plabel = new JLabel("Price/$");
		plabel.setForeground(new Color(186, 186, 186));
		plabel.setBounds(0, 5, WIDTH, 10);
		plabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceScalePanel.add(plabel);
		
		for(int i=0;i<scaleN;i++) {
			JLabel label = new JLabel();
			label.setForeground(new Color(186, 186, 186));
			int ds = Math.round(min+ i*(max-min)/((float)scaleN-1)); 
			int dh = Math.round(i*(height)/(scaleN-1)); 
			label.setText(""+ds);
			label.setBounds(0, height-dh+MARGIN, WIDTH, 10);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			priceScalePanel.add(label);
		}
		
		priceScalePanel.setBorder(new MatteBorder(0,0,1,0, new Color(50,50,50)));
		this.add(priceScalePanel);
	}
	
	public void setVolumeScalePanel(float max, float min, int height, int scaleN) {
		volumeScalePanel = new JPanel();
		volumeScalePanel.setLayout(null);
		volumeScalePanel.setPreferredSize(new Dimension(WIDTH, height));
		volumeScalePanel.setBackground(null);
		
		JLabel vlabel = new JLabel("Vol./M");
		vlabel.setForeground(new Color(186, 186, 186));
		vlabel.setBounds(0, 5, WIDTH, 10);
		vlabel.setHorizontalAlignment(SwingConstants.CENTER);
		volumeScalePanel.add(vlabel);
		
		for(int i=0;i<scaleN;i++) {
			JLabel label = new JLabel();
			label.setForeground(new Color(186, 186, 186));
			float ds = (min+ i*(max-min)/((float)scaleN-1))/1000000f; 
			int dh = Math.round(i*(height)/(scaleN-1)); 
			
			label.setText(String.format("%.2f", ds));
			label.setBounds(0, height-dh+MARGIN, WIDTH, 10);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			volumeScalePanel.add(label);
		}
		
		volumeScalePanel.setBorder(new MatteBorder(1,0,0,0, new Color(50,50,50)));
		this.add(volumeScalePanel);
	}
	
	public JPanel getPriceScalePanel() {
		return this.priceScalePanel;
	}
	
	public JPanel getVolumeScalePanel() {
		return this.volumeScalePanel;
	}
}

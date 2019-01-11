package stock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InformationDialog extends JDialog{
	private int WIDTH = 200,HEIGHT = 200;
	private JFrame parent;
	private InformLabel  highLabel, lowLabel, closeLabel, openLabel, volumeLabel, dateLabel;
	private String high = "   High: %.2f USD", low = "   Low: %.2f USD", close = "   Close: %.2f USD", open = "   Open: %.2f USD", volume = "   Volume: %s", date = "   Date: %s";
	
	public InformationDialog(JFrame parent) {
		this.parent = parent;
		Point positon = parent.getLocation();
		Dimension size = parent.getSize();
		getContentPane().setBackground(new Color(55, 58, 62));
		
		highLabel = new InformLabel();
		lowLabel = new InformLabel();
		closeLabel = new InformLabel();
		openLabel = new InformLabel();
		volumeLabel = new InformLabel();
		dateLabel = new InformLabel();
		
		setSize(WIDTH, HEIGHT);
		setLocation(positon.x+(size.width-WIDTH),positon.y);
		setAlwaysOnTop(true);
		setLayout(new GridLayout(6, 1));
		
		add(dateLabel);
		add(highLabel);
		add(lowLabel);
		add(openLabel);
		add(closeLabel);
		add(volumeLabel);
	}
	
	public void updateInformation(StockDayItem item) {
		highLabel.setText(String.format(high, item.getHight()));
		lowLabel.setText(String.format(low, item.getLow()));
		closeLabel.setText(String.format(close, item.getClose()));
		openLabel.setText(String.format(open, item.getOpen()));
		dateLabel.setText(String.format(date, item.getDate()));
		volumeLabel.setText(String.format(volume, processVolume(item.getVolume())));
		
		Color color = item.getDrawableStock().getColor();
		openLabel.setForeground(color);
		volumeLabel.setForeground(color);
		closeLabel.setForeground(color);
	}
	
	String processVolume(float data) {
		String vdata;
		DecimalFormat df = new DecimalFormat("#,###");
		vdata = df.format(data);
		return vdata;
	}
	
	class InformLabel extends JLabel{
		public InformLabel() {
			setFont(new Font("arial", Font.PLAIN, 15));
			setForeground(new Color(186, 186, 186));
		}
	}
}

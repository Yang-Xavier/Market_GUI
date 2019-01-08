package stock;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**This is the scale panel on the left*/

public class ScalePanel extends JPanel{
	JPanel priceScalePanel;
	JPanel volumeScalePanel;
	int lines;
	
	public ScalePanel(int lines) {
		this.lines = lines;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
}

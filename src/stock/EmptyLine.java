package stock;

import java.awt.Dimension;

import javax.swing.JPanel;

public class EmptyLine extends JPanel{
public EmptyLine(int width, int height) {
	setPreferredSize(new Dimension(width, height));
	setOpaque(false);
	setBackground(null);
}
}

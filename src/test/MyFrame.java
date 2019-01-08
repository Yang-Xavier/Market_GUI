package test;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class MyFrame extends JFrame {
	public MyFrame() {
		// TODO Auto-generated constructor stub
	setSize(200,200);
	
	JPanel p = new JPanel();
	p.setPreferredSize(new Dimension(400, 100)); // key point
	p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
	p.add(new MyPanel());
	p.add(new MyPanel());
	p.add(new MyPanel());
	p.add(new MyPanel());
	p.add(new MyPanel());
	
	JScrollPane jScrollPane = new JScrollPane(p);
	
	add(jScrollPane);
	jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//	add(new MyPanel());
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
	(new MyFrame()).setVisible(true);
	 }
	

	
}

class MyPanel extends JPanel {
	public MyPanel() {
		setSize(new Dimension(200, 200));
		setOpaque(true);
		setBackground(Color.black);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		setBorder(new EtchedBorder());
		
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.RED);
		g2d.drawLine(10, 10, 10, 50);
		
	}
}
/**

 * */
 
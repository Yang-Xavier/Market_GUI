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

import javax.swing.Box;
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
	setSize(800,650);
	
	JPanel p = new JPanel();
	JPanel p2 = new JPanel();
	
	JPanel p3 = new JPanel();
//	p.setPreferredSize(new Dimension(400, 400));
//	p2.setPreferredSize(new Dimension(6000, 500)); // key point
//	p3.setPreferredSize(new Dimension(6000, 100));
	p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
	
	
//	p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
//	p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
	
	for(int i = 0; i< 300;i++) {
		MyPanel3 myPanel3 = new MyPanel3();
		p.add(myPanel3);
	}
	
	JScrollPane jScrollPane = new JScrollPane(p);
	
	add(jScrollPane);
	jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
		myFrame.setVisible(true);
		System.out.println(myFrame.getInsets().top+" " + myFrame.getInsets().bottom);
	 }
	

	
}

class MyPanel extends JPanel {
	public MyPanel() {
		setOpaque(true);
		setBackground(Color.BLUE);
		setPreferredSize(new Dimension(20, 500));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.RED);
//		g2d.drawLine(10, 10, 10, 50);
		g2d.fillRect(5, 5, 10, 20);
		
	}
}

class MyPanel2 extends JPanel {
	public MyPanel2() {
		setOpaque(true);
		setBackground(Color.black);
		setPreferredSize(new Dimension(20, 100));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.RED);
//		g2d.drawLine(10, 10, 10, 50);
		g2d.fillRect(5, 5, 10, 20);
		
	}
}

class MyPanel3 extends JPanel {
	public MyPanel3() {
		setOpaque(true);
		setBackground(Color.black);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(new MyPanel());
		add(new MyPanel2());
	}
}


 
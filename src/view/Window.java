package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame implements KeyListener {
	
	public Window(){
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    ImageIcon img = new ImageIcon("game11.png");
		this.setTitle("Benzema Processor");
		this.setIconImage(img.getImage());
		JLabel panel = new JLabel();
		img = new ImageIcon("Karim2.jpg");
		panel.setPreferredSize(new Dimension(img.getIconWidth(),img.getIconHeight()));
		this.setBounds(200,100,img.getIconWidth(),img.getIconHeight());
		JLabel label = new JLabel();
		label.setText("Welcome to Karim Benzema MicroArcheticture");
		label.setFont(new Font("Serif Bold",Font.BOLD,40));
	    label.setForeground(Color.white);
	    label.setBounds(300,40,900,70);
	    panel.add(label);
		panel.setIcon(img);
		this.addKeyListener(this);
		this.add(panel);
		this.revalidate();
		this.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		EnterYourFile w2 = null;
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				w2 = new EnterYourFile(this);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.getContentPane().removeAll();
			this.add(w2);
			this.revalidate();
			this.repaint();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}

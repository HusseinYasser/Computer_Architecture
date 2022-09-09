package view;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")

public class JPanelWithBackground extends JPanel {
	


			  private Image backgroundImage;
			  private Image backgroundImagee;
			  public JPanelWithBackground(String fileName) throws IOException {
			    backgroundImagee = ImageIO.read(new File(fileName));
			  }

			  public void paintComponent(Graphics g) {
			    super.paintComponent(g);
			    Image backgroundImage = backgroundImagee.getScaledInstance(1440, 810, Image.SCALE_DEFAULT);
			    g.drawImage(backgroundImage, 0, 0, this);
			    this.setVisible(true);
			  }
			  public static void main(String[] args) {
				Frame a = new Frame();
				
			}
			
	

}

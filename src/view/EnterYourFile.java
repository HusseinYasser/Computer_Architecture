package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Project.ALU;
import Project.CodeParser;
import Project.DataMemory;
import Project.InstructionMemory;
import Project.MicroArcheticture;
import Project.RegisterFile;

public class EnterYourFile extends JPanelWithBackground implements ActionListener {

	JFrame origin;
	JTextField input ;
	JButton confirm;
	JLabel txt;
	


	//enter the name of the background
	public EnterYourFile(JFrame origin) throws IOException {
		super("Karim3re.jpg");
		this.origin = origin;
		this.setLayout(null);
		
		input = new JTextField(20);
		confirm = new JButton();
		
		
		txt = new JLabel();
		txt.setText("Enter Your File Name");
		txt.setForeground(Color.WHITE);
		txt.setBounds(10, 250, 400, 50);
		txt.setFont(new Font("Serif Bold",Font.BOLD,20));
		
		
		
		
		Font fo = new Font("Serif", Font.BOLD, 20);
		input.setFont(fo);
		input.setForeground(Color.BLACK);
		input.setBounds(10, 175, 200, 50);
		
		confirm.setBounds(10,300,150,30);
		confirm.setText("Submit");
		confirm.addActionListener(this);
		
		
		this.add(confirm);
		this.add(txt);
		this.add(input);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == confirm)
		{
			String filename = input.getText();
			
			InstructionMemory im = new InstructionMemory();
			
			DataMemory dm = new DataMemory();
			
			ALU alu = new ALU();
			
			RegisterFile rf = new RegisterFile();
			
			MicroArcheticture mac = new MicroArcheticture(im,dm,rf,alu);
			
			CodeParser cp = new CodeParser();
			
			try {
				cp.parse(new File(filename), im);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			origin.getContentPane().removeAll();
			Output o = null;
			String res = "";
			res = mac.pipeline();
			try {
				 o = new Output(res);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			origin.add(o);
			origin.revalidate();
			origin.repaint();
		}
		
		
	}

}

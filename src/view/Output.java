package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Output extends JPanelWithBackground implements ActionListener{
	
	JScrollPane scroll;
	JTextArea area;

	public Output(String text) throws IOException {
		super("BigMac.png");
		
		this.setLayout(null);
		
		area = new JTextArea();
		scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		area.setFont(new Font("Serif Bold",Font.ROMAN_BASELINE,15));
		
		area.setText(text);
		
		scroll.setBounds(50,10,1350,760);
		
	
		this.add(scroll);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

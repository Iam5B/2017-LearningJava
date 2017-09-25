import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NestingLayout{
	
	public static void main(String args[]){

		JFrame frame = new JFrame();

		JPanel panelA = new JPanel();

		JPanel panelB = new JPanel();

		panelB.add(new JButton("button 1"));

		panelB.add(new JButton("button 2"));

		panelB.add(new JButton("button 3"));

		panelA.add(panelB);

		frame.getContentPane().add(panelA);

		frame.setSize(300,300);

		frame.setVisible(true);
	
	}

}
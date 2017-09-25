import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComplicatedLayout{
	
	public static void main(String args[]){

		JFrame frame = new JFrame();

		JPanel panel1 = new JPanel();

		JPanel panel2 = new JPanel();

		panel1.add(new JButton("1"));

		panel1.add(new JButton("1"));

		panel1.add(new JButton("1"));

		panel2.add(new JButton("1"));

		panel2.add(new JButton("1"));

		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		//SET BOXLAYOUT 
		frame.getContentPane().add(panel1);

		frame.getContentPane().add(panel2);

		frame.setSize(300,300);

		frame.setVisible(true);

	}

}
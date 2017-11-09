import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Widgets{

	public static void main(String[] args){

		JFrame frame = new JFrame();

		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel1 = new JPanel();

		JTextField textField1 = new JTextField(20);

		JTextField textField2 = new JTextField("#");

		System.out.println(textField2.getText());

		textField1.setText("Doge");

		textField2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ev){

				System.out.println("Triggered...");

				textField1.selectAll();

			}

		});

		panel1.add(textField1);

		panel1.add(textField2);

		frame.getContentPane().add(panel1);

		JPanel panel2 = new JPanel();

		JTextArea textArea = new JTextArea(10,20);

		JScrollPane scroller = new JScrollPane(textArea);
		//textArea relies on the scroller ?!
		textArea.setLineWrap(true);

		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		panel2.add(scroller);

		textArea.append("Initial words... ");

		textArea.append("additional words... ");

		frame.getContentPane().add(panel2);

		frame.setSize(300,600);

		frame.setVisible(true);

	}

	

}
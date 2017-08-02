import javax.swing.*;
import java.awt.event.*;
//A clearer mode
class SimpleGui1C{

	private JButton button;

	class ActionListenerA implements ActionListener{

		public void actionPerformed(ActionEvent event){

			button.setText("I'v been clicked!");

		}

	}
	
	public void go(){

		JFrame frame = new JFrame();

		button = new JButton("click me");

		frame.getContentPane().add(button);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(300,300);

		ActionListenerA listener = new ActionListenerA();

		button.addActionListener(listener);

		frame.setVisible(true);

	}

	public static void main(String[] args){

		SimpleGui1C gui = new SimpleGui1C();

		gui.go();

	}

}
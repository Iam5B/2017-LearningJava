import java.awt.*;
import javax.swing.*;

class MyDrawPanel extends JPanel{
	
	public void paintComponent(Graphics g){

		g.setColor(Color.orange);

		g.fillRect(20,50,100,100);

	}

}

class MyDrawPanelTestDrive{

	public static void main(String[] args){

		JFrame frame = new JFrame();

		MyDrawPanel panel = new MyDrawPanel();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(panel);

		frame.setSize(300,300);

		frame.setVisible(true);

	}

}
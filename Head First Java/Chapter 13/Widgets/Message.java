import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Message{
//尝试在不用implement的前提下使用监听器
	public static void main(String[] args){
	
		JFrame frame = new JFrame();

		JCheckBox check = new JCheckBox("G");

		check.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent ev){

				String onOrOff = "off";

				if(check.isSelected()){

					onOrOff = "on";

				}

				System.out.println("Check box is " + onOrOff);

			}

		});

		frame.getContentPane().add(check);

		frame.setSize(300,600);

		frame.setVisible(true);

	}

}
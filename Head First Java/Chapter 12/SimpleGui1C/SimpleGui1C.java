//本文现在无法编译，必须将中文注释去除
import javax.swing.*;
import java.awt.event.*;

class SimpleGui1C{

	JButton button;

	public void go(){

		JFrame frame = new JFrame();

		button = new JButton("click me");

		frame.getContentPane().add(button);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(300,300);

		frame.setVisible(true);

	}

}//这个类实现的功能是初始化一个frame和button 生成样式 并设定基本参数


class SimpleGui1CTestDrive{

	public static void main(String[] args){

		SimpleGui1C gui = new SimpleGui1C();

		gui.go();

		ActionListener listener = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				gui.button.setText("I'v been clicked!");
			}
		};//注意此处近似于实例化一个对象，但是由于这个是将接口不经过实现而直接实例化出一个对象，所以格式是这样的，即在花括号内将待实现的方法也定义出来

		gui.button.addActionListener(listener);
		//这儿说明设置监听是将listener对象加到button对象上这样一个过程，在前面那个示例中 这个过程被隐藏的很厉害
		//在前面的示例中 那个对象本身同时就是一个ActionListener的实例 因此添加了自己作为listener
	}

}
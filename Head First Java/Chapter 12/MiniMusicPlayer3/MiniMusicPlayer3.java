/*本文无法编译 需要清除中文*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.midi.*;

class MiniMusicPlayer3{
	
	Sequencer sequencer;

	JFrame frame;

	boolean msg;

	/*类中的三个成员 一个播放器 一个窗口 一个用于确认是否是特定信息的布尔变量*/

	public MiniMusicPlayer3(){

		msg = false;
		sequencer = null;
		frame = null;
		/*防止报未定义的错误*/

		frame = new JFrame();
	
		try{
			sequencer = MidiSystem.getSequencer();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		/*简单的初始化一下成员变量*/
	}

	public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick){
		MidiEvent event = null;
		try{
			ShortMessage a = new ShortMessage();
			a.setMessage(comd,chan,one,two);
			event = new MidiEvent(a,tick);
		}
		catch(Exception e){}
		return event;
	}/*快速产生MidiEvent的工具函数*/

	public void go()throws Exception{

		JPanel drawPanel = new JPanel(){
			public void paintComponent(Graphics g){
				if(msg){/*注意这里用msg来区分是否是特定信息源传来的信息*/

					/*g.setColor(Color.white);
					g.fillRect(0,0,this.getWidth(),this.getHeight());
					Graphics2D g2d = (Graphics2D) g;
					int red = (int)(Math.random()*255);
					int green = (int)(Math.random()*255);
					int blue = (int)(Math.random()*255);
					Color startColor = new Color(red,green,blue);
					red = (int)(Math.random()*255);
					green = (int)(Math.random()*255);
					blue = (int)(Math.random()*255);
					Color endColor = new Color(red,green,blue);
					GradientPaint gradient = new GradientPaint(70,70,startColor,150,150,endColor);
					g2d.setPaint(gradient);
					g2d.fillOval(70,70,100,100);
					msg = false;*/
					/*You can also use this one*/

					Graphics2D g2 = (Graphics2D)g;

					int r = (int)(Math.random()*250);
					int gr = (int)(Math.random()*250);
					int b = (int)(Math.random()*250);

					g.setColor(new Color(r,gr,b));

					int ht = (int)((Math.random()*120)+10);
					int width = (int)((Math.random()*120)+10);

					int x = (int)((Math.random()*40)+10);
					int y = (int)((Math.random()*40)+10);

					g.fillRect(x,y,ht,width);
					msg = false;
				}
			}
		};/*基于接口直接创建对象的方法 同时建议好好掌握内部类的方法*/

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(drawPanel);
		frame.setSize(300,300);
		frame.setVisible(true);
		sequencer.open();
		int[] eventsIWant = {127};/*设置监听器捕捉的编号范围，这里只捕捉编号为127的Event*/


		ControllerEventListener listener = new ControllerEventListener(){
			public void controlChange(ShortMessage event){
				msg = true;
				frame.repaint();	
			}
		};/*在音乐Event的监听器中 设置"若监听到 则尝试重绘(因为绘制是随机过程，所以就是变化的方块)"*/
		

		sequencer.addControllerEventListener(listener,eventsIWant);
		Sequence seq = new Sequence(Sequence.PPQ,4);
		Track track = seq.createTrack();
		for(int i = 5;i < 61;i += 4){
			track.add(makeEvent(144,1,i,100,i));
			track.add(makeEvent(176,1,127,0,i));/*加了个176消息型事件，不会产生声音，但是会被监听器捕获到*/
			track.add(makeEvent(128,1,i,100,i + 2));
		}
		sequencer.setSequence(seq);
		sequencer.setTempoInBPM(120);
		sequencer.start();

	}
	
	public static void main(String[] args) throws Exception{
		MiniMusicPlayer3 s = new MiniMusicPlayer3();
		s.go();
	}

}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Controller{

	private int landForm;

	private Plat p;

	private JFrame frame;

	private JTextField textfield;

	private Runnable runnable; 

	private Thread thread;

	private boolean threadIsOpen = false;

	public Controller(){

		p = new Plat();

		landForm = 0;

		initialize();

		Plat.lifeColor = new Color[1000];

		int i;

		for(i = 0; i < 1000; i++){

			int r, g, b;

			r = (int)Math.floor(Math.random() * 256.0);

			g = (int)Math.floor(Math.random() * 256.0);

			b = (int)Math.floor(Math.random() * 256.0);

			Plat.lifeColor[i] = new Color(r, g, b);

		}

		frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(){

			public void paintComponent(Graphics g){

				Graphics2D g2d = (Graphics2D)g;

				int i;

				int j;

				switch(landForm){

					case 1:

						g2d.setPaint(new Color(255, 140, 0));

						g2d.fillRect(0, 0, 800, 800);

						break;

					case 2:

						g2d.setPaint(new Color(152, 251, 152));

						g2d.fillRect(0, 0, 800, 800);

						break;

					case 3:

						g2d.setPaint(new Color(255, 228, 181));

						g2d.fillRect(0, 0, 400, 800);

						g2d.setPaint(new Color(173, 255, 47));

						g2d.fillRect(400, 0, 400, 800);

						break;

					case 4:

						g2d.setPaint(new Color(0, 206, 209));

						g2d.fillRect(0, 0, 800, 800);

						g2d.setPaint(new Color(225,255,255));

						g2d.fillRect(320, 320, 160, 160);

						break;

				}

				for(i = 0; i < 100; i++){

					for(j = 0; j < 100; j++){

						if(p.getLifeMap()[i][j] == null){

							/*g2d.setPaint(Color.WHITE);

							g2d.fillRect(i * 8, j * 8, 8, 8);*/

						}

						else{

							g2d.setPaint(Plat.lifeColor[p.getLifeMap()[i][j].getId()]);

							g2d.fillRect(i * 8, j * 8, 8, 8);

							if(p.getLifeMap()[i][j].getVirusList().size()>0){

								g2d.setPaint(Color.BLACK);

								g2d.fillRect(i * 8, j * 8 + 3, 8, 2);

							}

						}

					}

				}

			}

		};

		runnable = new Runnable(){

			public void run(){

				while(true){

					moveARound();

					panel.repaint();

					showMessage();

					try{

						Thread.sleep(100);

					}

					catch(InterruptedException e){

						e.printStackTrace();

					}

				}

			}

		};


		JButton button = new JButton("Go A Round");

		textfield = new JTextField("");

		JButton buttonSave = new JButton("Save");

		JButton buttonLoad = new JButton("Load");

		frame.getContentPane().add(BorderLayout.SOUTH,button);

		frame.getContentPane().add(BorderLayout.CENTER,panel);

		frame.getContentPane().add(BorderLayout.NORTH,textfield);

		frame.getContentPane().add(BorderLayout.EAST,buttonSave);

		frame.getContentPane().add(BorderLayout.WEST,buttonLoad);

		frame.setSize(940,900);

		frame.setVisible(true);

		ActionListener goARoundListener = new ActionListener(){

			public void actionPerformed(ActionEvent event){

				if(!threadIsOpen){

					thread = new Thread(runnable);

					thread.start();

					threadIsOpen = true;

				}

				else{

					thread.stop();

					threadIsOpen = false;

				}

				

			}

		};

		ActionListener saveListener = new ActionListener(){

			public void actionPerformed(ActionEvent event){

				save();

			}

		};

		ActionListener loadListener = new ActionListener(){

			public void actionPerformed(ActionEvent event){

				load();

			}

		};

		button.addActionListener(goARoundListener);

		buttonSave.addActionListener(saveListener);

		buttonLoad.addActionListener(loadListener);

	}
	//初始化构造函数还需要负责进行布局和功能绑定 hhh

	public void moveARound(){

		p.reproductionAndWitherStage();

		p.newKindStage();

		p.eatingStage();

		p.huntingStage();

		p.hungerStage();

		p.saveAndDieStage();

		p.newVirusStage();

	}

	public void showMessage(){

		p.countStage(textfield);

		p.sectionRefreshStage();

		p.resetStage();

	}

	public void save(){

		/* 暂时不想实现的内容 */

	}

	public void load(){

		/* 暂时不想实现的内容 */

	}

	public void initialize(){

		Scanner input = new Scanner(System.in);

		System.out.println("请选择地理条件(仅提供若干种预设):");

		System.out.println("1.平坦型(贫瘠) 2.平坦型(富饶) 3.沙漠绿洲型 4.盆地型");

		landForm = input.nextInt();

		int i,j;

		switch(landForm){

			case 1:

				for(i = 0; i < 10; i++)

					for(j = 0; j < 10; j++)
			
						p.getSectionMap()[i][j] = new Section(Section.defaultProductivity, Section.defaultFittestNumOfLife);

				break;

			case 2:

				for(i = 0; i < 10; i++)

					for(j = 0; j < 10; j++)

						p.getSectionMap()[i][j] = new Section(Section.defaultProductivity * 4.0, Section.defaultFittestNumOfLife);

				break;

			case 3:

				for(i = 0 ; i < 5; i++)

					for(j = 0; j < 10; j++)

						p.getSectionMap()[i][j] = new Section(Section.defaultProductivity * 0.25, Section.defaultFittestNumOfLife / 4);

				for(; i < 10; i++)

					for(j = 0; j < 10; j++)

						p.getSectionMap()[i][j] = new Section(Section.defaultProductivity * 4.0, Section.defaultFittestNumOfLife);

				break;

			case 4:

				for(i = 0; i < 10; i++){

					for(j = 0; j < 10; j++){

						if((i == 4 || i == 5) && (j == 4 || j == 5)){

							p.getSectionMap()[i][j] = new Section(Section.defaultProductivity * 4.0, Section.defaultFittestNumOfLife);

						}

						else{

							p.getSectionMap()[i][j] = new Section(Section.defaultProductivity * 0.5, Section.defaultFittestNumOfLife / 2);

						}

					}

				}

				break;

		}

		System.out.println("请输入初始物种数目(输入2333可以设置为七夕专属物种布局):");

		int lifeNum = input.nextInt();

		if(lifeNum == 2333){

			Life a = new Life();

			Life b = new Life();

			Life[][] m = p.getLifeMap();

			m[15][15] = new Life(a);

			m[14][14] = new Life(a);

			m[13][13] = new Life(a);

			m[12][12] = new Life(a);

			m[11][12] = new Life(a);

			m[10][12] = new Life(a);

			m[9][13] = new Life(a);

			m[9][14] = new Life(a);

			m[10][15] = new Life(a);

			m[9][16] = new Life(a);

			m[9][17] = new Life(a);

			m[10][18] = new Life(a);

			m[11][18] = new Life(a);

			m[12][18] = new Life(a);

			m[13][17] = new Life(a);

			m[14][16] = new Life(a);

			m[8][80] = new Life(b);

			m[9][80] = new Life(b);

			m[10][80] = new Life(b);

			m[11][80] = new Life(b);

			m[12][80] = new Life(b);

			m[13][80] = new Life(b);

			m[14][80] = new Life(b);

			m[15][80] = new Life(b);

			m[16][80] = new Life(b);

			m[17][80] = new Life(b);

			m[8][82] = new Life(b);

			m[9][82] = new Life(b);

			m[10][82] = new Life(b);

			m[11][82] = new Life(b);

			m[12][82] = new Life(b);

			m[13][82] = new Life(b);

			m[14][82] = new Life(b);

			m[15][82] = new Life(b);

			m[16][82] = new Life(b);

			m[17][82] = new Life(b);

			m[11][79] = new Life(b);

			m[11][81] = new Life(b);

			m[11][83] = new Life(b);

			m[15][79] = new Life(b);

			m[15][81] = new Life(b);

			m[15][83] = new Life(b);

		}

		else{

			int k;

			ArrayList<Life> l = new ArrayList<Life>();

			for(k = 0; k < lifeNum; k++){

				System.out.println("请输入两个整数，代表第" + k + "种生命的等级和物资需求(参考值为1 1):");

				int levelToSet, foodDemandToSet;

				levelToSet = input.nextInt();

				foodDemandToSet = input.nextInt();

				l.add(new Life((double)levelToSet,(double)foodDemandToSet));

			}

			int lcounter = 0;

			int m,n;

			for(lcounter = 0; lcounter < l.size(); lcounter++){

				Section s = p.selectSectionRandomly();

				int x,y;

				x = y = 0;

				for(i = 0; i < 10; i++){

					for(j = 0; j < 10; j++){

						if(p.getSectionMap()[i][j] == s){

							x = i;

							y = j;

						}

					}

				}
				/* 获取了一个随机区域 */
				int[] pair;

				pair = p.selectSpaceRandomly(x, y);

				if(pair[0] >= 0){

					Life newOne = l.get(lcounter);

					p.getLifeMap()[pair[0]][pair[1]] = newOne;

					x = pair[0];

					y = pair[1];

					for(i = x - 1; i <= x + 1; i++){

						for(j = y - 1; j <= y + 1; j++){

							if(i < 0 || j < 0 || i > 99 || j > 99 || (i == x && j == y) || p.getLifeMap()[i][j] != null){

								continue;

							}

							else{

								if(Math.random() < 0.8){

									p.getLifeMap()[i][j] = new Life(newOne);

								}

							}

						}

					}

				}

			}
			

		}

	}

	public static void main(String[] args){

		Controller c = new Controller();

	}

}
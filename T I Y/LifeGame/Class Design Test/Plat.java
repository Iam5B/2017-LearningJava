import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.io.Serializable;  

public class Plat implements Serializable{

	public static Color[] lifeColor;
	/* 需要初始化 */
	private Life[][] lifeMap;

	private Section[][] sectionMap;

	private ArrayList<Life> newLife;
	/* 请务必在构造函数中初始化他们 */
	private ArrayList<Virus> newVirus;

	private ArrayList<AntiBody> newAntiBody;

	public Plat(){

		newLife = new ArrayList<Life>();

		newVirus = new ArrayList<Virus>();

		newAntiBody = new ArrayList<AntiBody>();

		lifeMap = new Life[100][100];

		sectionMap = new Section[10][10];

		int i,j;

		for(i=0;i<10;i++)

			for(j=0;j<10;j++)
			
				sectionMap[i][j]=new Section();

		// lifeMap[15][12] = new Life();

		// lifeMap[15][12].getVirusList().add(new Virus(1,1));

		// lifeMap[88][87] = new Life(10.0,2.5);

		// lifeMap[88][88] = new Life(lifeMap[88][87]);

		// lifeMap[15][13] = new Life(lifeMap[15][12]);

		// lifeMap[15][14] = new Life(lifeMap[15][12]);

		// lifeMap[14][14] = new Life(lifeMap[15][12]);

	}

	/* 如何进行初始化构造暂时不考虑 */

	/* 如何进行初始化构造暂时不考虑 */

	/* 假装这个display已经有了 */
	/*public void display(){

		frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(){

			public void paintComponent(Graphics g){

				Graphics2D g2d = (Graphics2D)g;

				int i;

				int j;

				for(i = 0; i < 100; i++){

					for(j = 0; j < 100; j++){

						if(lifeMap[i][j] == null){

							g2d.setPaint(Color.WHITE);

							g2d.fillRect(i * 8, j * 8, 8, 8);

						}

						else{

							g2d.setPaint(Plat.lifeColor[lifeMap[i][j].getId()]);

							g2d.fillRect(i * 8, j * 8, 8, 8);

							if(lifeMap[i][j].getVirusList().size()>0){

								g2d.setPaint(Color.BLACK);

								g2d.fillRect(i * 8, j * 8 + 3, 8, 2);

							}

						}

					}

				}

			}

		};

		JButton button = new JButton("Go A Round");

		frame.getContentPane().add(BorderLayout.SOUTH,button);

		frame.getContentPane().add(BorderLayout.CENTER,panel);

		frame.setSize(800,900);

		frame.setVisible(true);

		ActionListener listener = new ActionListener(){

			public void actionPerformed(ActionEvent event){

				reproductionAndWitherStage();

				newKindStage();

				eatingStage();

				huntingStage();

				hungerStage();

				saveAndDieStage();

				newVirusStage();

				countStage();

				sectionRefreshStage();

				resetStage();

				frame.repaint();

			}

		};

		button.addActionListener(listener);

	}*/
	/* 这个display已经没有了 */

	public Life[][] getLifeMap(){

		return lifeMap;

	}

	public Section[][] getSectionMap(){

		return sectionMap;

	}

	public static Comparator<Life> eatingAbilityComparator = new Comparator<Life>(){

		public int compare(Life a, Life b){

			return (b.getLevel() < a.getLevel() ? 1 : (b.getLevel() == a.getLevel() ? 0 : -1 ));

		}

	};

	public void sortEatingAbility(ArrayList<Life> l){

		Collections.sort(l,eatingAbilityComparator);

	}

	public ArrayList<Life> getLifeAround(int x, int y){
	/* 返回活体生命列表 可能是0长度的 值得注意的是这里的是直接引用 那么如果回过头来想要销毁特定对象可就有点麻烦了 需要遍历地图并使用 == 判断 orz */
		int i, j;

		ArrayList<Life> a = new ArrayList<Life>();

		for(i = x - 1; i <= x + 1; i++){

			for(j = y - 1; j <= y + 1;j++){

				if(i < 0 || i > 99 || j < 0 || j > 99 || (i == x && j == y)){

					continue;

				}

				else{

					if(lifeMap[i][j] != null){

						a.add(lifeMap[i][j]);

					}

				}

			}

		}

		return a;

	}

	public Life selectLifeRandomly(int x, int y){
	/* 调用时请注意这个玩意可能返回null */
		int xFloor = x * 10;

		int xUtmost = x * 10 + 9;

		int yFloor = y * 10;

		int yUtmost = y * 10 + 9;

		ArrayList<Life> a = new ArrayList<Life>();

		int i,j;

		for(i = xFloor; i<= xUtmost; i++){

			for(j = yFloor; j <= yUtmost; j++){

				if(lifeMap[i][j] != null){

					a.add(lifeMap[i][j]);

				}

			}

		}

		if(a.size() == 0){

			return null;

		}

		return a.get((int)Math.floor( Math.random() * (double)a.size() ));

	}

	public Section locateSection(int x, int y){

		return sectionMap[x/10][y/10];

	}
	/* 锁定生物所在区域 方便传送死亡信息之类的 */

	public int[] selectSpaceRandomly(int x, int y){
	/* 注意这个也有可能搜索失败 失败的情况为-1 -1 */
		ArrayList<Integer> xs = new ArrayList<Integer>();

		ArrayList<Integer> ys = new ArrayList<Integer>();

		int xFloor = x * 10;

		int xUtmost = x * 10 + 9;

		int yFloor = y * 10;

		int yUtmost = y * 10 + 9;

		int i,j;

		for(i = xFloor; i<= xUtmost; i++){

			for(j = yFloor; j <= yUtmost; j++){

				if(lifeMap[i][j] == null){

					xs.add(i);

					ys.add(j);

				}

			}

		}
		
		if(xs.size() == 0){

			int[] arr = {-1,-1};

			return arr;

		}

		else{

			int pos = (int)Math.floor( Math.random() * (double)xs.size() );

			int[] arr = new int[2];

			arr[0] = xs.get(pos);

			arr[1] = ys.get(pos);

			return arr;

		}

	}

	public Section selectSectionRandomly(){

		return sectionMap[(int)Math.floor(Math.random()*10.0)][(int)Math.floor(Math.random()*10.0)];

	}

	public void reproductionAndWitherStage(){
	/* 繁衍/自然死亡阶段 */
		int i,j;

		int[][] rep = new int[10][10];

		int[][] die = new int[10][10];

		for(i = 0; i < 10; i++){

			for(j = 0; j < 10; j++){

				rep[i][j] = 0;

				die[i][j] = 0;

			}

		}

		ArrayList<Integer> repXs = new ArrayList<Integer>();

		ArrayList<Integer> repYs = new ArrayList<Integer>();

		ArrayList<Life> repLs = new ArrayList<Life>();

		for(i = 0; i < 100; i++){

			for(j = 0; j < 100; j++){

				if(lifeMap[i][j] == null){

					ArrayList<Life> l = getLifeAround(i,j);

					if(l.size() > 1){

						int k;

						boolean finded = false;

						Life tester = null;

						for(k = 0; k < l.size(); k++){

							tester = l.get(k);

							int m;

							for(m = k + 1; m < l.size(); m++){

								if(tester.equals(l.get(m))){

									finded = true;

									break;

								}

							}

							if(finded){

								break;

							}

						}

						int m;

						for(m = 0; m < l.size(); m++){

							if(!tester.equals(l.get(m))){

								l.remove(m);

								m--;

							}

						}

						if(!finded){

							continue;

						}

						else{

							rep[i/10][j/10]++;

							repXs.add(i);

							repYs.add(j);

							repLs.add(new Life(l));

							if(repXs.size() != repLs.size())System.out.println(88);

							l.clear();

						}

					}

				}

			}

		}
		/* 消亡↓ */

		ArrayList<Integer> lifeXToRemove = new ArrayList<Integer>();

		ArrayList<Integer> lifeYToRemove = new ArrayList<Integer>();

		for(i = 0; i < 100; i++){

			for(j = 0; j < 100; j++){

				if(lifeMap[i][j] != null){

					ArrayList<Life> lifeAround = getLifeAround(i, j);

					int k;

					int cnt = 0;

					for(k = 0; k < lifeAround.size(); k++){

						if(lifeAround.get(k).getId() == lifeMap[i][j].getId()){

							cnt++;

						}

					}

					if(cnt == 0 || cnt >= 4){

						die[i/10][j/10]++;

						lifeXToRemove.add(i);

						lifeYToRemove.add(j);

					}

				}

			}

		}

		/* 消亡↑ */
		for(i = 0; i < repLs.size(); i++){

			lifeMap[repXs.get(i)][repYs.get(i)] = repLs.get(i);

		}

		for(i = 0; i < lifeXToRemove.size(); i++){

			lifeMap[lifeXToRemove.get(i)][lifeYToRemove.get(i)] = null;

		}

		for(i = 0; i < 10; i++){

			for(j = 0; j < 10; j++){

				sectionMap[i][j].addNumOfBirth(rep[i][j]);

				sectionMap[i][j].addNumOfDeath(die[i][j]);

			}

		}

	}	/* break here life counting ! */
	
	public void newKindStage(){
	/* 新物种出现的阶段 注意相应的初始化构造函数需要实现，以及那个函数应该接受一个区域作为参数 */
		if(Life.newKindTrial()){

			Section s = selectSectionRandomly();

			int x,y;

			x = y = 0;

			int i,j;

			for(i = 0; i < 10; i++){

				for(j = 0; j < 10; j++){

					if(sectionMap[i][j] == s){

						x = i;

						y = j;

					}

				}

			}

			int[] pair;

			pair = selectSpaceRandomly(x, y);

			if(pair[0] >= 0){

				ArrayList<Life> l = getLifeAround(pair[0],pair[1]);

				Life newOne = new Life(l, s);

				newLife.add(newOne);

				lifeMap[pair[0]][pair[1]] = newOne;

				s.addNumOfBirth(1);

				x = pair[0];

				y = pair[1];

				for(i = x - 1; i <= x + 1; i++){

					for(j = y - 1; j <= y + 1; j++){

						if(i < 0 || j < 0 || i > 99 || j > 99 || (i == x && j == y) || lifeMap[i][j] != null){

							continue;

						}

						else{

							if(Math.random() < 0.8){

								lifeMap[i][j] = new Life(newOne);

								s.addNumOfBirth(1);

							}

						}

					}

				}

			}

		}

	}
	
	public void eatingStage(){
	 /* 竞争自然资源 延伸出的需求:区域资源重置 */
		int i,j;

		for(i = 0;i < 10; i++){

			for(j = 0; j < 10; j++){

				Section s = sectionMap[i][j];

				ArrayList<Life> l = new ArrayList<Life>();

				int p,q;

				for(p = i * 10; p < (i + 1) * 10; p++){

					for(q = j * 10; q < (j + 1) * 10; q++){

						if(lifeMap[p][q] != null){

							l.add(lifeMap[p][q]);

						}

					}

				}

				sortEatingAbility(l);

				int k;

				for(k = 0; k < l.size(); k++){

					double r = s.getRemainingProduct();

					s.consumeRemainingProduct(l.get(k).eat(r));

					if((int)s.getRemainingProduct() == 0){

						break;

					}

				}

			}

		}

	}

	public void huntingStage(){
    /* 掠食其他生物的阶段 */
		int i,j;

		for(i = 0; i < 100; i++){

			for(j = 0; j < 100; j++){

				if(lifeMap[i][j] != null && !lifeMap[i][j].isSatisfied()){

					ArrayList<Life> l = getLifeAround(i, j);

					int k;

					for(k = 0; k < l.size(); k++){

						if(l.get(k).getId() != lifeMap[i][j].getId()){

							if(lifeMap[i][j].hunt(l.get(k))){

								int p,q;

								for(p = i - 1;p <= i + 1; p++){

									for(q = j - 1; q <= j + 1; q++){

										if(p < 0 || p > 99 || q < 0 || q > 99 || p == i && q ==j){

											continue;

										}

										if(lifeMap[p][q] == l.get(k)){

											lifeMap[p][q] = null;

											locateSection(p,q).addNumOfDeath(1);

										}

									}

								}

								break;

							}

						}

					}

				}

			}

		}

	}
	
	public void hungerStage(){
	/* 饥饿致死阶段 */
		int i,j;

		for(i = 0; i < 100; i++){

			for(j = 0; j < 100; j++){

				if(lifeMap[i][j] != null){

					if(!lifeMap[i][j].isSatisfied()){

						lifeMap[i][j] = null;

						locateSection(i,j).addNumOfDeath(1);

					}

				}

			}

		}

	}

	public void saveAndDieStage(){
	/* 自愈及死亡阶段，注意应该在事前做好排除重复，自我免疫检测 */
		int i,j;

		for(i = 0; i < 100; i++){

			for(j = 0; j < 100; j++){

				if(lifeMap[i][j] != null){

					lifeMap[i][j].selfDuplicate();

					lifeMap[i][j].selfImmune();

					ArrayList<Virus> v = lifeMap[i][j].getVirusList();

					ArrayList<AntiBody> a = lifeMap[i][j].getAntiBodyList();

					int k;

					for(k = 0 ; k < v.size(); k++){

						boolean help = false;

						Virus vv = v.get(k);

						ArrayList<Life> l = getLifeAround(i, j);

						int p;

						for(p = 0; p < l.size(); p++){

							ArrayList<Virus> vl = l.get(p).getVirusList();

							if(vl.contains(vv)){

								help = true;

							}

						}

						if(v.get(k).selfSecureTrial() || help){

							AntiBody anti = new AntiBody(v.get(k));

							a.add(anti);

							newAntiBody.add(anti);

							v.remove(k);

							k--;

						}

					}

					for(k = 0; k < v.size(); k++){

						if(v.get(k).deathTrial()){

							lifeMap[i][j] = null;

							locateSection(i,j).addNumOfDeath(1);

						}

					}

				}

			}

		}

	}

	public void newVirusStage(){
	/* 新病毒尝试爆发阶段 */
		int i,j;

		for(i = 0; i < 10; i++){

			for(j = 0; j < 10; j++){

				if(sectionMap[i][j].virusOutBreakTrial()){

					Virus v = new Virus(sectionMap[i][j].getNumOfLife(),sectionMap[i][j].getNumOfDeath());

					Life l = selectLifeRandomly(i,j);

					if(l != null){

						l.getVirusList().add(v);

						newVirus.add(v);

					}

				}

			}

		}

	}

	public void countStage(JTextField textfield){
	/* 显示统计数据阶段 先考虑在这个方法内得到或者枚举若干有关数据 */
	/* 可能会引入新的变量并考虑销毁过程 */
	/* 增加一个类似每日要闻的部分 在控制台进行文字输出 */
		ArrayList<String> boomNews= new ArrayList<String>();

		int[][] lifeNum = new int[10][10];
		/* 各个区域生命数组 */
		int[][] birthNum = new int[10][10];
		/* 各个区域的出生情况数组 */
		int[][] deathNum = new int[10][10];
		/* 各个区域的死亡数目情况数组 */
		int sumLife = 0;

		int sumBirth = 0;

		int sumDeath = 0;
		/* 各类数据的总和 */
		int maxLifeNum;

		int minLifeNum;

		int maxBirthNum;

		int minBirthNum;

		int maxDeathNum;

		int minDeathNum;
		/* 上面数值中的最大和最小值 */
		ArrayList<Life> l = new ArrayList<Life>();
		/* 物种列表 */
		int i,j;

		for(i = 0; i < 10 ; i++){

			for(j = 0; j < 10; j++){

				lifeNum[i][j] = sectionMap[i][j].getNumOfLife() + sectionMap[i][j].getNumOfBirth() - sectionMap[i][j].getNumOfDeath();

				sectionMap[i][j].setNumOfLife(lifeNum[i][j]);

				lifeNum[i][j] = 0;

			}

		}

		for(i = 0; i < 100; i++){

			for(j = 0; j < 100; j++){

				if(lifeMap[i][j] != null){

					lifeNum[i/10][j/10]++;

				}

			}

		}

		boomNews.add("现在地图上共有" + sumLife + "个生命体");

		for(i = 0; i < 10 ; i++){

			for(j = 0; j < 10; j++){

				birthNum[i][j] = sectionMap[i][j].getNumOfBirth();

				sumBirth += birthNum[i][j];

			}

		}

		boomNews.add("本轮出生了" + sumBirth + "个生命");		
		
		for(i = 0; i < 10 ; i++){

			for(j = 0; j < 10; j++){

				deathNum[i][j] = sectionMap[i][j].getNumOfDeath();

				sumDeath += deathNum[i][j];

			}

		}

		maxLifeNum = minLifeNum = lifeNum[0][0];

		maxBirthNum = minBirthNum = birthNum[0][0];

		maxDeathNum = minDeathNum = deathNum[0][0];

		for(i = 0; i < 10; i++){

			for(j = 0; j < 10; j++){

				maxLifeNum = maxLifeNum >= lifeNum[i][j] ? maxLifeNum : lifeNum[i][j];

				minLifeNum = minLifeNum <= lifeNum[i][j] ? minLifeNum : lifeNum[i][j];

				maxBirthNum = maxBirthNum >= birthNum[i][j] ? maxBirthNum : birthNum[i][j];

				minBirthNum = minBirthNum <= birthNum[i][j] ? minBirthNum : birthNum[i][j];

				maxDeathNum = maxDeathNum >= deathNum[i][j] ? maxDeathNum : deathNum[i][j];

				minDeathNum = minDeathNum <= deathNum[i][j] ? minDeathNum : deathNum[i][j]; 

			}

		}

		boomNews.add("本轮过后生命最密集的区域有" + maxLifeNum + "个生命");

		boomNews.add("本轮过后生命最贫瘠的区域有" + minLifeNum + "个生命");

		boomNews.add("本轮中最残酷的地带有" + maxDeathNum + "个生命死去");

		boomNews.add("本轮中最蓬勃的地带有" + maxBirthNum + "个生命诞生");

		boomNews.add("本轮中产生了" + newLife.size() + "个新物种");

		boomNews.add("本轮中爆发出了" + newVirus.size() + "个新的病毒");

		boomNews.add("本轮中产生了" + newAntiBody.size() + "个新的抗体");

		newLife.clear();

		newVirus.clear();

		newAntiBody.clear();

		for(i = 0; i < 100; i++){

			for(j = 0; j < 100; j++){

				if(lifeMap[i][j] != null){

					Life ll = lifeMap[i][j];

					int k;

					boolean different = true;

					for(k = 0; k < l.size(); k++){

						if(ll.equals(l.get(k))){

							different = false;

							break;

						}

					}

					if(different){

						l.add(ll);

					}

				}

			}

		}

		int index = (int)Math.floor(Math.random() * (double)boomNews.size());

		textfield.setText(boomNews.get(index));

		/* 总体数目，物种数目通览 */

	}
	
	public void sectionRefreshStage(){
	/* 自然环境演替阶段 */
		int i,j;

		for(i = 0 ; i < 10; i++){

			for(j = 0; j < 10; j++){

				sectionMap[i][j].resetStatus();

			}

		}

	}

	public void resetStage(){

		int i,j;

		for(i = 0; i < 100; i++){

			for(j = 0; j < 100; j++){

				if(lifeMap[i][j] != null){

					lifeMap[i][j].resetSatisfied();

					lifeMap[i][j].selfDuplicate();

					lifeMap[i][j].selfImmune();

				}

			}

		}

	}
	/* 刷新 重置部分数据 显示部分数值 重置环境 饥饿性 我还是等会儿再写这个吧 */


	public static void main(String[] args){



		Plat.lifeColor = new Color[1000];

		int i;

		for(i = 0; i < 1000; i++){

			int r, g, b;

			r = (int)Math.floor(Math.random() * 256.0);

			g = (int)Math.floor(Math.random() * 256.0);

			b = (int)Math.floor(Math.random() * 256.0);

			lifeColor[i] = new Color(r, g, b);

		}

		Plat p = new Plat();

		

		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			System.exit(0);//退出程序
		}

		// p.reproductionAndWitherStage();

		// p.newKindStage();

		// p.eatingStage();

		// p.huntingStage();

		// p.hungerStage();

		// p.saveAndDieStage();

		// p.newVirusStage();

		// p.countStage();

		// p.sectionRefreshStage();

		// p.resetStage();

	}

}
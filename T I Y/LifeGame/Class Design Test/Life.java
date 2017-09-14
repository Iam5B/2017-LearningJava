import java.util.ArrayList;
import java.io.Serializable;  

public class Life implements Serializable{
	/* 注意应该有人工生成，随机生成，依据自然条件生成，繁衍四种构造方式 */
	public static int nextId = 0;

	private int id;

	private double level;
	/* 物种等级 可以用于摄食低级生物 */
	private double foodDemand;
	/* 物种所需摄食量 */
	private boolean satisfied;
	/* 是否吃饱 */
	public static final double variationPossibility = 0.0001;
	/* 遗传突变概率 */
	public static final double newKindPossibility = 0.00001;
	/* 新物种出现概率 */
	public static final double lowFoodDemand = 1.0;

	public static final double lowLevel = 1.0;
	/* 食物需求和生物等级的低水平标准 */
	private ArrayList<Virus> virusList;

	private ArrayList<AntiBody> antiBodyList;

	public double getLevel(){

		return level;

	}

	public double getFoodDemand(){

		return foodDemand;

	}

	public int getId(){

		return id;

	}

	public ArrayList<Virus> getVirusList(){

		return virusList;

	}

	public ArrayList<AntiBody> getAntiBodyList(){

		return antiBodyList;

	}

	public boolean isSatisfied(){

		return satisfied;

	}

	public void resetSatisfied(){

		satisfied = false;

	}

	public Life(Life l){
	/* 复制生命，仅测试用 */
		id = l.getId();

		level = l.getLevel();

		foodDemand = l.getFoodDemand();

		satisfied = false;

		virusList = new ArrayList<Virus>();

		antiBodyList = new ArrayList<AntiBody>();

	}

	public Life(double levelToSet,double foodDemandToSet){
	/* 千万注意繁衍，捕食过程中可能存在的变故 */
		level = levelToSet;

		id = nextId;

		Life.nextId++;

		foodDemand = foodDemandToSet;

		satisfied = false;

		virusList = new ArrayList<Virus>();

		antiBodyList = new ArrayList<AntiBody>();

	}
	
	public Life(){
	/* 随机过程 但是只会产生较为弱势的生命 */
		id = nextId;

		Life.nextId++;

		foodDemand = Life.lowFoodDemand * (Math.random() + 9.5)/10.0;

		satisfied = false;

		level = Life.lowLevel * (Math.random() + 9.5)/10.0;

		virusList = new ArrayList<Virus>();

		antiBodyList = new ArrayList<AntiBody>();

	}
	
	public Life(ArrayList<Life> l, Section s){

		if(l.size() == 0){

			foodDemand = s.getProductivity()/(double)s.getFittestNumOfLife();

			satisfied = false;

			level = Life.lowLevel * (Math.random() + 9.5)/10.0;

			virusList = new ArrayList<Virus>();

			antiBodyList = new ArrayList<AntiBody>();

		}

		else{

			foodDemand = s.getProductivity()/(double)s.getFittestNumOfLife();

			int num = l.size();

			double sumFoodDemand = 0;

			double sumLevel = 0;

			virusList = new ArrayList<Virus>();

			antiBodyList = new ArrayList<AntiBody>();

			int i;

			for(i = 0; i < l.size(); i++){

				sumFoodDemand += l.get(i).getFoodDemand();

				sumLevel += l.get(i).getLevel();

			}

			double rate;

			if(variationTrial()){
			/* 借用下遗传变异的概念 */
				rate = (Math.random() - 0.5) * 2.0 + 1.0;

			}

			else{

				rate = (Math.random() + 9.5)/10.0;

			}

			foodDemand = (sumFoodDemand/(double)num) * rate;

			level = (sumLevel/(double)num) * rate;

			satisfied = false;

		}

		id = nextId;

		Life.nextId++;

	}
	/* 依据环境和周边生物产生 待完成 */

	public Life(ArrayList<Life> l){
	/* 此处先约定无上限 繁衍过程 */

		id = l.get(0).getId();

		int num = l.size();

		double sumFoodDemand = 0;

		double sumLevel = 0;

		virusList = new ArrayList<Virus>();

		antiBodyList = new ArrayList<AntiBody>();

		int i;

		for(i = 0; i < l.size(); i++){

			sumFoodDemand += l.get(i).getFoodDemand();

			sumLevel += l.get(i).getLevel();

		}

		double rate;

		if(variationTrial()){
		/* 发生遗传变异(适度的) */
			rate = (Math.random() - 0.5) * 2.0 + 1.0;

		}

		else{

			rate = (Math.random() + 9.5)/10.0;

		}

		foodDemand = (sumFoodDemand/(double)num) * rate;

		level = (sumLevel/(double)num) * rate;

		satisfied = false;

		for(i = 0;i < l.size(); i++){

			Life ll = l.get(i);

			ArrayList<Virus> v = ll.getVirusList();

			ArrayList<AntiBody> a = ll.getAntiBodyList();

			int j;

			for(j = 0; j < v.size(); j++){

				virusList.add(new Virus(v.get(j)));

			}

			for(j = 0; j < a.size(); j++){

				antiBodyList.add(new AntiBody(a.get(j)));

			}

			selfDuplicate();

			selfImmune();

		}

	}

	public double eat(double amount){
	/* 食用自然资源，返回食用了多少，并设置是否满足 */
		if(amount>=foodDemand){

			satisfied = true;

			return foodDemand;

		}

		else{

			satisfied = false;

			return amount;

		}

	}
	
	public boolean hunt(Life l){
	/* 尝试捕食并设置饱和 */
		if(id==l.getId()){

			return false;

		}

		else if((level - l.getLevel())>1.0){

			satisfied = true;

			ArrayList<Virus> v = l.getVirusList();

			ArrayList<AntiBody> a = l.getAntiBodyList();

			int i;

			for(i = 0; i < v.size(); i++){

				if(v.get(i).foodInfectTrial()){

					virusList.add(new Virus(v.get(i)));

				}

			}

			/*for(i = 0; i < a.size(); i++){

				if(i = 0; i < a.size(); i++){

					antiBodyList.add(new AntiBody(a.get(i)));

				}

			}*/

			selfImmune();

			return true;

		}

		else{

			return false;

		}

	}

	public boolean variationTrial(){
	/* 这是遗传变异的检测函数 */
		return Math.random() < Life.variationPossibility;

	}

	public void selfImmune(){

		int i;

		for(i = 0; i < virusList.size(); i++){

			Virus v = virusList.get(i);

			int j;

			for(j = 0; j < antiBodyList.size(); j++){

				if(antiBodyList.get(j).getId() == v.getId()){

					virusList.remove(i);

					i--;

				}

			}

		}
	/* 在经历了可能被感染的过程后 应该执行下自我免疫保平安 */
	}

	public void selfDuplicate(){
	/* 对两个表进行去重 */
		if(virusList.size() > 0){

			int i,j;

			for(i = 0; i < virusList.size(); i++){

				for(j = i + 1;j < virusList.size(); j++){

					if(virusList.get(i).equals(virusList.get(j))){

						virusList.remove(j);

						j--;

					}

				}

			}

		}

		if(antiBodyList.size() > 0){

			int i,j;

			for(i = 0; i < antiBodyList.size(); i++){

				for(j = i + 1;j < antiBodyList.size(); j++){

					if(antiBodyList.get(i).equals(antiBodyList.get(j))){

						antiBodyList.remove(j);

						j--;

					}

				}

			}

		}

	}

	public boolean deathTrial(){

		int i;

		for(i = 0;i < virusList.size(); i++){

			if(virusList.get(i).deathTrial()){

				return true;

			}

		}

		return false;

	}

	public static boolean newKindTrial(){

		return Math.random() < newKindPossibility;

	}

	public boolean equals(Life l){

		return id == l.getId();

	}

}
import java.util.Date;
import java.util.ArrayList;
/*
enum Status{

	SPARE("SPARE"),
	OCCUPIED("OCCUPIED"),
	DESOLATE("DESOLATE"),
	WITHERED("WITHERED");

	public String name1;

	public Status(String name1){  

        this.name1 = name1;   
    
    }

    public String toString(){  

        return this.name();

    }  

}

enum Disaster{

	DRY("DRY"),
	WEEDS("WEEDS"),
	PEST("PEST");

	public String name1;

	public Disaster(String name1){  

        this.name1 = name1;   
    
    }

    public String toString(){  

        return this.name();
          
    }  

}

enum Reaction{

	WATER("WATER"),
	WEED("WEED"),
	INSECTICIDE("INSECTICIDE"); 

	public String name1;

	public Reaction(String name1){  

        this.name1 = name1;   
    
    }

    public String toString(){  

        return this.name();
          
    }  

}
以上部分仅作为概念存在 */
public class Land{

	private String status;

	private double worth;

	private Seed plant;

	private Date start;

	private static final String[][] DIS_REP = {{"DRY","WATER"},{"WEEDS","WEED"},{"PEST","INSECTICIDE"}};
	/* 灾害以及补救措施的键值对常量 */

	private static final int DIS_NUM = 3;

	private ArrayList<String> disasterTypePreparation;
	/* 此处的数组需要动态的增长以及缩短 因此不得不使用ArrayList 与之相照应的 外部传入数据是静态的 即数据在外部空间中不会发生类似的改变 可以使用相对静态的存储模式 */
	private ArrayList<Integer> disasterPeriodPreparation;

	private ArrayList<String> disaster;

	public Land(Seed plantToSet, String statusToSet, double worthToSet, long startToSet, String[] disasterTypePreparationToSet, int[] disasterPeriodPreparationToSet, String[] disasterToSet){

		plant = plantToSet;

		worth = worthToSet;

		status = statusToSet;

		start = new Date(startToSet);

		int i;

		disasterTypePreparation = new ArrayList<String>();

		disasterPeriodPreparation = new ArrayList<Integer>();

		for(i = 0; i < disasterTypePreparationToSet.length; i++){

			disasterTypePreparation.add(disasterTypePreparationToSet[i]);

			disasterPeriodPreparation.add(disasterPeriodPreparationToSet[i]);

		}

		disaster = new ArrayList<String>();

		for(i = 0; i < disasterToSet.length; i++){

			disaster.add(disasterToSet[i]);

		}

	}

	public Land(double worthToSet, String statusToSet){

		plant = null;

		worth = worthToSet;

		status = statusToSet;

		start = null;

		disasterTypePreparation = new ArrayList<String>();

		disasterPeriodPreparation = new ArrayList<Integer>();

		disaster = new ArrayList<String>();

	}
	/* 应该需要标记游戏是否 进行过 从而执行不同的土地初始化过程 */

	public String[] getDisaster(){

		String[] d = new String[disaster.size()];

		int i;

		for(i = 0; i < disaster.size(); i++){

			d[i] = disaster.get(i) + "";

		}

		return d;

	}
	/* 服务于显示当前灾害的可能的方法 */

	public boolean repair(String react){

		int i;

		for(i = 0; i < Land.DIS_NUM; i++){

			if(react.equals(Land.DIS_REP[i][1])){

				break;

			}

		}

		String disasterToEliminate = Land.DIS_REP[i][1] + "";

		boolean repaired = false;

		for(i = 0; i < disaster.size(); i++){

			if(disaster.get(i).equals(disasterToEliminate)){

				disaster.remove(i);

				repaired = true;

				i--;

			}

		}

		return repaired;

	}

	public void refresh(){

		Date current = new Date();

		long timePassed = current.getTime() - start.getTime();

		int currentPeriodNumber = Seed.getCurrentPeriodNumber(timePassed,plant);

		int i;

		for(i = 0; i < disasterPeriodPreparation.size(); i++){

			if(currentPeriodNumber >= disasterPeriodPreparation.get(i)){

				disasterPeriodPreparation.remove(i);

				disaster.add(disasterTypePreparation.get(i) + "");

				disasterTypePreparation.remove(i);

				i--;

			}

		}

		if(currentPeriodNumber == (plant.getPeriodAmounts() - 1)){

			int len = disaster.size();

			disaster.clear();

			double finalHarvest = ((10.0 - ((double)len) ) / 10.0) * ((double)plant.getExpectedHarvest());

			plant.setExpectedHarvest((int)Math.rint(finalHarvest));

		}

	}
	/* 若成熟,则应该将负面状态清空,并依据负面状态进行收成的缩减 */

	public double unlock(double money){
	/* 仅用于判断当前所有的钱是否可以解锁土地 如果不足 或者不可以解锁土地 则返回-1.0 */
		if(status == "DESOLATE"){

			if(money > worth){

				status = "SPARE";

				return worth;

			}

		}

		return -1.0;

	}

	public boolean clear(){

		if(status == "WITHERED"){

			status = "SPARE";

			return true;

		}

		return false;

	}

	public boolean sow(Seed s){

		if(status == "SPARE"){

			status = "OCCUPIED";

			plant = s;

			start = new Date();

			int upperBound = plant.getPeriodAmounts() - 1;

			int i;

			int j;

			for(i = 0; i < upperBound; i++){

				for(j = 0; j < DIS_NUM; j++){

					if((int)(Math.round(Math.random()))==1){

						disasterPeriodPreparation.add(i);

						disasterTypePreparation.add((DIS_REP[j][0] + ""));

					}

					else{

						/* Do nothing */

					}

				}

			}

			return true;

		}

		return false;

	}

	public Fruit harvest(){
	/* 注意返回的是果实 并且在内部将数据清理干净 */
		this.refresh();

		if(plant&&plant.getCurrentPeriodName().equals("成熟")){

			Fruit t = new Fruit(plant);

			plant = null;

			start = null;

			disaster.clear();

			disasterPeriodPreparation.clear();

			disasterTypePreparation.clear();

			return t;

		}

		return null;

	}

}
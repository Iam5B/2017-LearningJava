public class Seed extends Item{
	
	private double[] periodTimeList;

	private String[] periodNameList;

	private double fruitValue;

	private int expectedHarvest;
	/* 预期收入的果实数目 */
	public Seed(String nameToSet, double valueToSet, double fruitValueToSet, int amountToSet, String imageToSet, int expectedHarvestToSet, double[] periodTimeListToSet, String[] periodNameListToSet){
	/* 构造函数要求数组是正好的那种静态数组，即大小正好合适的数组 且Name数组应该比Time数组长1(因为多一个成熟阶段) */
		super(nameToSet, valueToSet, amountToSet, imageToSet);

		fruitValue = fruitValueToSet;

		expectedHarvest = expectedHarvestToSet;

		periodTimeList = periodTimeListToSet;

		periodNameList = periodNameListToSet;

	}

	public int getExpectedHarvest(){

		return expectedHarvest;

	}

	public void setExpectedHarvest(int expectedHarvestToSet){

		expectedHarvest = expectedHarvestToSet;

		if(expectedHarvest < 0){

			expectedHarvest = 0;

		}

	}

	public int getPeriodAmounts(){
	/* 该作物的生长周期数 */
		return periodNameList.length;
	
	}

	public double getFruitValue(){

		return fruitValue;

	}	

	public double[] getPeriodTimeList(){
	/* 得到生长周期列表 即一个反应了各阶段所需时间的列表 不包括成熟阶段 */
		return periodTimeList;

	}

	public String[] getPeriodNameList(){

		return periodNameList;

	}

	public static int getCurrentPeriodTimeLeft(long timePassed, Seed s){
	/* 传入已经经过的时间(毫秒) 然而返回的是秒 反应距离下一阶段还剩的时间 */
		double hourPassed = ((double)timePassed) / (1000.0 * 3600.0);

		int i;

		double[] tList = s.getPeriodTimeList();

		for(i = 0; i < tList.length; i++){

			if(hourPassed - tList[i] > 0){

				hourPassed -= tList[i];

				continue;

			}

			else{

				hourPassed -= tList[i];

				hourPassed = 0 - hourPassed;
				/* 计算下一阶段剩余时间 */
				break;

			}

		}

		if(i != tList.length){
		/* 尚未成熟 */
			return (int)(hourPassed * 3600);

		}

		else{
		/* 已经成熟 约定返回一个负数 */
			return -1;

		}

	}

	public static int getCurrentPeriodNumber(long timePassed, Seed s){
	/* 思路相同 返回当前阶段编号 */
		double hourPassed = ((double)timePassed) / (1000.0 * 3600.0);

		int i;

		double[] tList = s.getPeriodTimeList();

		String[] nList = s.getPeriodNameList();

		for(i = 0; i < tList.length; i++){

			if(hourPassed - tList[i] > 0){

				hourPassed -= tList[i];

				continue;

			}

			else{

				break;

			}

		}

		return i;
		/* 利用了变量i */
	}

	public static String getCurrentPeriodName(long timePassed, Seed s){
	/* 思路相同 返回当前阶段名称 */
		double hourPassed = ((double)timePassed) / (1000.0 * 3600.0);

		int i;

		double[] tList = s.getPeriodTimeList();

		String[] nList = s.getPeriodNameList();

		for(i = 0; i < tList.length; i++){

			if(hourPassed - tList[i] > 0){

				hourPassed -= tList[i];

				continue;

			}

			else{

				break;

			}

		}

		return nList[i];
		/* 利用了变量i */
	}

	public static String getNextPeriodName(long timePassed, Seed s){
	/* 思路相同 返回下一阶段名称 若无下一阶段则返回空 */
		double hourPassed = ((double)timePassed) / (1000.0 * 3600.0);

		int i;

		double[] tList = s.getPeriodTimeList();

		String[] nList = s.getPeriodNameList();

		for(i = 0; i < tList.length; i++){

			if(hourPassed - tList[i] > 0){

				hourPassed -= tList[i];

				continue;

			}

			else{

				break;

			}

		}

		if(i + 1 < nList.length){

			return nList[i + 1];
		
		}
		else{

			return "";

		}
		/* 利用了变量i */
	}

	public int harvest(){

		return (int)( 0.8*(double)expectedHarvest + 0.4*Math.random()*(double)expectedHarvest );

	}

	public static void main(String[] args){

		double[] a = {1,1,1};

		String[] b = {"发芽","小叶","开花","成熟"};

		Seed q = new Seed("class",1.1,23.0,2,"java",20,a,b);

		Fruit t = new Fruit(q);

		System.out.println(t.getAmount());

		System.out.println(Seed.getCurrentPeriodTimeLeft(3500000,q));

	}

}
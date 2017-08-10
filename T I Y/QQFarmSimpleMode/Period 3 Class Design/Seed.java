public class Seed extends Item{
	
	private double[] periodTimeList;

	private String[] periodNameList;

	public Seed(String nameToSet, double valueToSet, int amountToSet, String imageToSet, double[] periodTimeListToSet, String[] periodNameListToSet){
	/* 构造函数要求数组是正好的那种静态数组，即大小正好合适的数组 且Name数组应该比Time数组长1(因为多一个成熟阶段) */
		super(nameToSet, valueToSet, amountToSet, imageToSet);

		periodTimeList = periodTimeListToSet;

		periodNameList = periodNameListToSet;

	}

	public int getPeriodAmounts(){

		return periodNameList.length;

	}

	public double[] getPeriodTimeList(){

		return periodTimeList;

	}

	public String[] getPeriodNameList(){

		return periodNameList;

	}

	public static int getCurrentPeriodTimeLeft(long timePassed, Seed s){
	/* 传入已经经过的时间(毫秒) 然而返回的是秒 */
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

	public static String getCurrentPeriodName(long timePassed, Seed s){
	/* 思路相同 */
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
	/* 思路相同 */
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

	public static void main(String[] args){

		double[] a = {1,1,1};

		String[] b = {"发芽","小叶","开花","成熟"};

		Seed q = new Seed("class",1.1,2,"java",a,b);

		System.out.println(Seed.getCurrentPeriodTimeLeft(3500000,q));

	}

}
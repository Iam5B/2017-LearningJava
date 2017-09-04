import java.io.Serializable;  

public class Section implements Serializable{
	/* 区域类的一个重要缺陷，无法自行获取区域内生物数目 */
	private double productivity;

	public static final double productivityFloor = 2.0;
	/* 生产力底限 */
	public static final double productivityUtmost = 200.0;

	public static final int defaultFittestNumOfLife = 25;

	public static final double defaultProductivity = 25.0;

	private int fittestNumOfLife;

	private double roundProductivity;
	/* 该回合还剩余的 */
	private int numOfLife;

	private int numOfBirth;

	private int numOfDeath;

	public double getProductivity(){

		return productivity;

	}

	public int getFittestNumOfLife(){

		return fittestNumOfLife;

	}

	public void setNumOfLife(int s){

		numOfLife = s;

	}

	public int getNumOfLife(){

		return numOfLife;

	}

	public double getRemainingProduct(){

		return roundProductivity;

	}

	public void consumeRemainingProduct(double d){

		roundProductivity -= d;

	}

	public void addNumOfBirth(int i){

		numOfBirth += i;

		numOfLife += i;

	}

	public void addNumOfDeath(int i){

		numOfDeath += i;

		numOfLife -= i;

	}

	public int getNumOfDeath(){

		return numOfDeath;

	}

	public int getNumOfBirth(){

		return numOfBirth;

	}

	public Section(){

		productivity = defaultProductivity;

		fittestNumOfLife = defaultFittestNumOfLife;

		roundProductivity = productivity;

		numOfBirth = 0;

		numOfDeath = 0;

	}
	/* 生成默认形区域块 */
	public Section(double productivityToSet, int fittestNumOfLifeToSet){

		productivity = productivityToSet;

		fittestNumOfLife = fittestNumOfLifeToSet;

		roundProductivity = productivity;

		numOfBirth = 0;

		numOfDeath = 0;

	}

	public void resetStatus(){

		productivity = productivity * (double)fittestNumOfLife / (double)numOfLife; 

		if(productivity > productivityUtmost){

			productivity = productivityUtmost;

		}

		if(productivity < productivityFloor){

			productivity = productivityFloor;

		}

		roundProductivity = productivity;

		numOfBirth = 0;

		numOfDeath = 0;
	/* reset前必须借助高级类来获取实时的numOfLife 并不用 */
	}

	public boolean virusOutBreakTrial(){

		return Virus.virusOutbreakTest(numOfLife,numOfDeath);

	}

	public void virusOutBreak(Life l){

		l.getVirusList().add(new Virus(numOfLife,numOfDeath));

	}

}
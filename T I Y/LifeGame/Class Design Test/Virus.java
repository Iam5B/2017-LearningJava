import java.io.Serializable;  

public class Virus implements Serializable{

	private int id;
	/* 应该附设一个颜色列表 */
	public static int nextId = 0;
	/* 在save&load过程中需要注意存取 */
	private double sexInfect;
	/* 区域内生物越多 这个概率越高 */
	private double foodInfect;
	/* 区域死亡的个体数越多 概率越高 */
	private double selfSecure;
	/* 自然康复的概率 */
	private double deathRate;
	/* 病发死亡概率 */
	public static final double sexInfectBasicPossibility = 0.5;

	public static final double foodInfectBasicPossibility = 0.1;

	public static final double selfSecureBasicPossibility = 0.1;

	public static final double deathRateBasicPossibility = 0.1;

	public static final double virusOutbreakPossibility = 0.0001;

	public int getId(){

		return id;

	}

	public double getSexInfect(){

		return sexInfect;

	}

	public double getFoodInfect(){

		return foodInfect;

	}

	public double getSelfSecure(){

		return selfSecure;

	}

	public double getDeathRate(){

		return deathRate;

	}

	public Virus(int bioMass, int deathToll){
	/* 包含了各种概率的初始化过程 可修改 */
		id = nextId;

		nextId++;

		sexInfect = Virus.sexInfectBasicPossibility * (((double)bioMass) / 50.0);

		foodInfect = Virus.foodInfectBasicPossibility * (((double)deathToll) / 25.0);

		selfSecure = Virus.selfSecureBasicPossibility * (((double)bioMass) / 10.0);

		deathRate = Virus.deathRateBasicPossibility * (((double)deathToll) / 50.0);

	}

	public Virus(Virus v){
	/* 用于病毒传播 */
		id = v.getId();

		sexInfect = v.getSexInfect();

		foodInfect = v.getFoodInfect();

		selfSecure = v.getSelfSecure();

		deathRate = v.getDeathRate();

	}

	public boolean equals(Virus v){

		return id == v.getId();

	}

	public boolean selfSecureTrial(){
	/* 自愈尝试 */
		return Math.random()<selfSecure;

	}

	public boolean deathTrial(){
	/* 致死尝试 */
		return Math.random()<deathRate;

	}

	public boolean sexInfectTrial(){
	/* 性传播 */
		return Math.random()<sexInfect;

	}

	public boolean foodInfectTrial(){
	/* 掠食传播 */
		return Math.random()<foodInfect;

	}

	public static boolean virusOutbreakTest(int bioMass, int deathToll){
	/* 区域疾病爆发测试  */
		return Math.random()<Virus.virusOutbreakPossibility * (((double)bioMass) / 50.0) * (((double)deathToll) / 25.0);

	}

}
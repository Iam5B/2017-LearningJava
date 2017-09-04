public class AntiBody{

	private int id;
	/* 与病毒保持一致即可 */

	public int getId(){

		return id;

	}

	public AntiBody(Virus v){
	/* 用于抗体的诞生 */
		id = v.getId();

	}

	public AntiBody(AntiBody a){

		id = a.getId();

	}

	public boolean equals(AntiBody a){

		return id == a.getId();

	}

}
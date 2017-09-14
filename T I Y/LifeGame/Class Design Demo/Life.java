public class Life{

	private int kind;

	public int getKind(){

		return kind;

	}

	public Life(int kindToSet){

		kind = kindToSet;

	}

	public Life(Life l){

		kind = l.getKind();

	}

	public Life(){}

}
import java.util.ArrayList;

public class SimpleDotCom{
	
	private ArrayList<String> locationCells;

	private int numOfHits;

	public String checkYourself(String stringGuess){

		String result = "miss";

		int index = locationCells.indexOf(stringGuess);

		if(index >= 0){

			locationCells.remove(index);

			if(locationCells.isEmpty()){

				result = "kill";

			}
			else{

				result = "hit";

			}

		}

		System.out.println(result);

		return result;

	}

	public void setLocationCells(ArrayList<String> loc){

		locationCells = loc;
	
	};

}
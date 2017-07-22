import java.util.ArrayList;

public class SimpleDotComGame{
	
	public static void main(String[] args){

		int numOfGuesses = 0;

		GameHelper helper = new GameHelper();

		SimpleDotCom theDotCom = new SimpleDotCom();

		int randomNum = (int)(Math.random() * 5);

		int[] locations = {randomNum, randomNum + 1, randomNum + 2};

		ArrayList<String> loc = new ArrayList<String>();

		int i;

		for(i=0; i<3; i++){

			String s = locations[i] + "";

			loc.add(s);

		}

		theDotCom.setLocationCells(loc);

		boolean isAlive = true;

		while(isAlive == true){

			String guess = helper.getUserInput("enter a number");

			String result = theDotCom.checkYourself(guess);

			numOfGuesses++;

			if(result.equals("kill")){

				isAlive = false;

				System.out.println("You took " + numOfGuesses + " guesses");

			}

		}

	}

}
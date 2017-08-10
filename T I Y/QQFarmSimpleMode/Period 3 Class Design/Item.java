public class Item{

	private String name;

	private double buyValue;

	private double sellValue;

	private int amount;

	private String image;

	public Item(String nameToSet, double valueToSet, int amountToSet, String imageToSet){

		amount = amountToSet;

		buyValue = valueToSet;

		sellValue = valueToSet * 0.9;

		image = imageToSet;

	}

	public String getName(){

		return name;

	}

	public double getBuyValue(){

		return buyValue;

	}

	public double getSellValue(){

		return sellValue;

	}

	public int getAmount(){

		return amount;

	}

	public void setAmount(int amountToSet){

		if(amountToSet >= 0){

			amount = amountToSet;

		}

	}

}
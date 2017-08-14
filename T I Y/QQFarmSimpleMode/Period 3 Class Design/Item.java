public class Item{

	private String name;

	private double buyValue;

	private double sellValue;

	private int amount;

	private String image;

	public Item(){}

	public Item(String nameToSet, double valueToSet, int amountToSet, String imageToSet){

		amount = amountToSet;

		buyValue = valueToSet;

		sellValue = valueToSet * 0.9;

		image = imageToSet;

	}

	public String getImage(){

		return image;

	}

	public void setImage(String imageToSet){

		image = imageToSet;

	}

	public String getName(){

		return name;

	}

	public void setName(String nameToSet){

		name = nameToSet;

	}

	public double getBuyValue(){

		return buyValue;

	}

	public double getSellValue(){

		return sellValue;

	}

	public void setBuyValue(double valueToSet){

		buyValue = valueToSet;

	}

	public void setSellValue(double valueToSet){

		sellValue = valueToSet;

	}

	public int getAmount(){

		return amount;

	}

	public void setAmount(int amountToSet){

		if(amountToSet >= 0){

			amount = amountToSet;

		}

	}

	public boolean sameTo(Item i){

		return name.equals(i.getName());

	}

}
public class Fruit extends Item{

	public Fruit(Seed s){
	/* 基于土地中的种子进行收获生产果实 */
		setAmount(s.harvest());

		setName(s.getName());

		setSellValue(s.getFruitValue());

		setImage(s.getImage());

		/* super(nameToSet, valueToSet, amountToSet, imageToSet); */
		/* writing in this way is unachievable */

	}

	public Fruit(String nameToSet, double valueToSet, int amountToSet, String imageToSet){

		super(nameToSet, valueToSet, amountToSet, imageToSet);

	}

}
class Animal{
	
	public void makeNoise(){

		System.out.println("emmmm");
	
	}

}

class Dog extends Animal{
	
	public void makeNoise(){

		System.out.println("Wang Wang Wang");

	}

}

class Cat extends Animal{
	
	public void makeNoise(){

		System.out.println("Miao Miao Miao");

	}

}

class Vet{

	public void giveShot(Animal a){

		a.makeNoise();

	}

}

class AnimalTestDrive{
	
	public static void main(String[] args){

		Animal[] animals = new Animal[2];

		animals[0] = new Dog();

		animals[1] = new Cat();

		for(int i = 0;i < animals.length; i++){

			animals[i].makeNoise();

		}



		Vet v = new Vet();

		v.giveShot(animals[0]);

		v.giveShot(animals[1]);


	}

}
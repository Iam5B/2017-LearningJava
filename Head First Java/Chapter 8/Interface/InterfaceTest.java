class Robot{
	public void encharge(){
		System.out.println("Encharging...");
	}
}

interface Pet{
	public abstract void interact();
}

class RoboDog extends Robot implements Pet{
	public void interact(){
		System.out.println("Aproaching...");
	}
}

class RoboDogTestDrive{
	public static void main(String[] args){
		Pet p = new RoboDog();
		p.interact();
		Robot r = new RoboDog();
		r.encharge();
	}
}
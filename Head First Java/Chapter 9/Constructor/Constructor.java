class Car{
	int size;
	public Car(int s){
		size = s;
	}
}

class Mini extends Car{
	int speed;
	public Mini(){
		super(10);
		speed = 100;
	}
	public Mini(int s){
		this();
		speed = s;
	}
}

class MiniTestDrive{
	public static void main(String[] args){
		Mini m = new Mini();
		Mini n = new Mini(200);
	}
}
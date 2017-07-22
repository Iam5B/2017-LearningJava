class S{

	private int value;

	public static S ins = new S();

	private S(){

		value = 1;

	}

	public static void z(){

		System.out.println(ins.value);
	
	}

}

class STestDrive{
	
	public static void main(String[] args){

		S.z();

	}

}
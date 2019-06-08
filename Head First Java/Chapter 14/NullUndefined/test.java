class Test{

	String a;

	String b;

	Test(){

		a = null;

	}

	public static void main(String[] args){

		Test s = new Test();

		if(s.a == null){

			System.out.println("Null");

		}

		if(s.b == null){

			System.out.println("Undefined");

		}

		return;

	}

}
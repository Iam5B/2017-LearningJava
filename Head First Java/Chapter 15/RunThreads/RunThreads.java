public class RunThreads implements Runnable{

	// private static int index = 0;

	public static void main(String[] args){

		RunThreads runner = new RunThreads();

		Thread alpha = new Thread(runner);

		Thread beta = new Thread(runner);

		alpha.setName("Alpha thread");

		beta.setName("Beta thread");

		alpha.start();

		beta.start();

	}

	public void run(){

		for(int i = 0; i < 25; i++){

			String threadName = Thread.currentThread().getName();

			System.out.println(threadName + " is running");

			// System.out.println(index);

			// index++;

			// it will show you that the thread might stop at any time...

		}

	}

}
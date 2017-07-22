ISSUES
------
*	Can you understand why those mathematic methods are designed to be related to class, not object instance ? And what's the job of such kind of classes like Math(*A set of function and concept that are in the same field*).
*	For the Brain Barbell on page 280, my answer is in the ./Chapter 10/SingleInstance. My solution is using *static* to create a static object instance inside the class with a private constructor, and use static method to communicate with the only instance.
*	Knowing the way of creating a constant variable.
*	For the test on page 285, my answer is T F F T F F
*	Get familiar with simple math methods like Math.abs(), Math.round()...
*	Knowing how to make use of autoboxing when creating an ArrayList that contains primitive data type.
	>   ArrayList<**Integer**> listOfNumbers = new ArrayList<**Integer**>();

*	For the code on page 291
	*	The program will compile successfully, but run wrongly, it's really a special case, because the process
		>   j = i;
		
		is actually a process of
		>   j = null;
		
		if you modify the definition of the data member i like this
		>   Integer i = new Integer(1);

		then the program will run successfully :-D

	*	Actually, I'm worrying about something else
		>public static void main(String[] args){

		>&nbsp;&nbsp;&nbsp;TestBox t = new TestBox();

		>&nbsp;&nbsp;&nbsp; mt.go();

		>}

		it seems like a static method called a public method, which is not allowed, but actually not, you can view the case on page 278. Then you will find the difference.
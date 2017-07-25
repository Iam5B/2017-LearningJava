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
*	Distinguish the following methods
	>Integer.parseInt("233");

	>(new Integer(1)).intValue();

*	Know the two methods of creating String object based on primitive data types.
	> 233 \+ "" ;

	> (new Double(2.33)).toString();/* do you remember that .toString() is a method inherited from class Object, it's obvious that they override the method.*/

*	What does String.format() return ?

*	For the class Calendar, Emmmm... , simply know it could take the job of caculate the time. And to import the class you need to add
	>   import java.util.Calendar;

	to create a instance

	>   Calendar a = Calendar.getInstance();
* My detailed answer for the second question of the test on page 311
	
	I think we should first recall the format of using a static method, such as
	>   int i = Integer.parseInt("233");

	in this case, we could see that a static method requies two factors, the name of method and the return type. Suppose there is a static constructor, as a constructor, it could only have a name of the constructor, but as a method, it requires the name and the return type, so here comes the conflict.
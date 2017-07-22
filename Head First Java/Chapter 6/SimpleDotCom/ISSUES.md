ISSUES
------
*	SimpleDotCom.java


	*	Notice that the &lt;String&gt; also appears at
		>   public void setLocationCells(ArrayList&lt;String&gt; loc){}

		that is to say, ArrayList&lt;String&gt; is a name of data type, only ArrayList isn't enough.
	*	What would the method return if
		>   int index = locationCells.indexOf(stringGuess);
		
		couldn't find the certain String object in the ArrayList. 
		
		And what is the searching progress based on, reference or content ?(*Here the method matched the String object according to its content*).


*	SimpleDotComGame.java
	*	If you viewed my code, try to learn what happened in the following statement
		>   String s = locations[i] + "";
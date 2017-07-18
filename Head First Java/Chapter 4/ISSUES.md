##ISSUES
*	As is known to us, JAVA passed parameters by value. So what would happen if we passed an object, will the function create a copy of the object and handle the copy of the object seperatedly ?
*	Use your own word to express the advantages of encapsulation compared with  direct setting or getting data.
*	For the question on page 87, my answer is T T F T F F F F
*	For the code B on page 88, look at the following statement
	>String getTime(){
	></br>
	>&nbsp;&nbsp;&nbsp;&nbsp;return time;
	></br>
	>}

	What would the function return ? A reference to a new string copy or just a reference to the existing private member. If it returns a reference to the private member, I don't think it's a good idea.
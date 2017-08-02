ISSUES
------
*	Notice that 
	>   MidiEvent event = null;
	
	if you write as
	>   MidiEvent event;

	the compiler will think it's wrong, why ?

*	This code uses subprogram to create MidiEvent, we know that 
	>   public static MidiEvent makeEvent(){};
	
	is the subprogram, do you know why it's static ?(*You may delete the key word static and try to compile the file*)
*	Today I found a website that introduces JAVA API in chinese, <a href="http://tool.oschina.net/apidocs/apidoc?api=jdk-zh">click here</a>. You may use it to get to know the meaning of some unfamiliar methods or variable. Such as
	>   new Sequence(Sequence.PPQ,4);

	>setTempoInBPM(220);

	>ex.printStackTrace();

	notice you could find their explanation in the correct package and the correct class.


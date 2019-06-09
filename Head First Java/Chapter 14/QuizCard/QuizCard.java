import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class QuizCard implements Serializable{

	private String question;

	private String answer;

	public String getQuestion(){

		return question;

	}

	public String getAnswer(){

		return answer;

	}

	QuizCard(String questionToSet, String answerToSet){

		question = questionToSet;

		answer = answerToSet;

	}

}
import java.util.ArrayList;
import java.util.HashMap;

public class WhileStatement extends Statement
{
	private ArrayList<Statement> whileCommands;
	private String[] elementsInsideWhile;
	private Expression lData;
	private Expression rData;
	private boolean lIsVariable;
	private boolean rIsVariable;

	/**
	 *
	 * @param s - while condition
	 * @param whileCommands - lines within the while statement
	 */
	public WhileStatement(String s, ArrayList<Statement> whileCommands)
	{
		this.elementsInsideWhile = s.split(" ");
		this.whileCommands = whileCommands; //copies the commands within the while statement that need to be executed

		// checks if the left Expression is a constant or variable
		if (checkAllNumbers(this.elementsInsideWhile[0]) == true)
		{
			this.lData = new Const(this.elementsInsideWhile[0]);
			this.lIsVariable = false;
		}
		else if(checkAllLetters(this.elementsInsideWhile[0]) == true)
		{
			this.lData = null;
			this.lIsVariable = true;
		}

		// checks if the right Expression is a constant or variable
		if (checkAllNumbers(this.elementsInsideWhile[2]) == true)
		{
			this.rData = new Const(this.elementsInsideWhile[2]);
			this.rIsVariable = false;
		}
		else if(checkAllLetters(this.elementsInsideWhile[2]) == true)
		{
			this.rData = null;
			this.rIsVariable = true;
		}

	}
	/**
	 * Helper method to check if a string is all nums
	 * @param s - string to be checked
	 * @return - boolean true if all nums false if not
	 */
	private boolean checkAllNumbers(String s)
	{
		//this will only ever return true if the string is filled AND has all numbers in it
		if(s.equals(""))
		{
			return false;
		}
		for(int i = 0; i < s.length();i++)
		{
			if(Character.isLetter(s.charAt(i)) == true)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Helper method to check if a string is all letters
	 * @param s - string to be checked
	 * @return - boolean true if all letters false if not
	 */
	private boolean checkAllLetters(String s)
	{
		if(s.equals(""))
		{
			return false;
		}
		for(int i = 0; i < s.length();i++)
		{
			if(Character.isLetter(s.charAt(i)) != true)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Evaluates the while condition and executes the while lines when true until evaluates to false
	 * @param namespace - namespace containing variables
	 */
	public void execute(HashMap<String,Expression> namespace)
	{
		Expression calculated;

		if (this.lIsVariable == true)
		{
			this.lData = new Variable(this.elementsInsideWhile[0], namespace);
		}
		if (this.rIsVariable == true)
		{
			this.rData = new Variable(this.elementsInsideWhile[2], namespace);
		}
		calculated = new Compare(this.lData.eval(namespace),this.rData.eval(namespace),this.elementsInsideWhile[1]);

		while(calculated.eval(namespace) == 1)
		{
			for(Statement whileLines : whileCommands)
			{
				whileLines.execute(namespace);
			}
			if (this.lIsVariable == true)
			{
				this.lData = new Variable(this.elementsInsideWhile[0], namespace);
			}
			if (this.rIsVariable == true)
			{
				this.rData = new Variable(this.elementsInsideWhile[2], namespace);
			}
			calculated = new Compare(this.lData.eval(namespace),this.rData.eval(namespace),this.elementsInsideWhile[1]);
		}
	}
}

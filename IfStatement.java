import java.util.ArrayList;
import java.util.HashMap;

public class IfStatement extends Statement
{
	//private boolean checkIfStatement; //boolean that checks if the if statement is true or not
	private ArrayList<Statement> ifCommands;
	private String[] elementsInsideIf;
	private Expression lData;
	private Expression rData;
	private boolean lIsVariable;
	private boolean rIsVariable;

	/**
	 *
	 * @param s - the if condition in string format
	 * @param ifCommands - the lines within if
	 */
	public IfStatement(String s, ArrayList<Statement> ifCommands)
	{
		this.elementsInsideIf = s.split(" ");
		this.ifCommands = ifCommands; //copies the commands within the if statement that need to be executed

		// checks if the left Expression is a constant or variable
		if (checkAllNumbers(this.elementsInsideIf[0]) == true)
		{
			this.lData = new Const(this.elementsInsideIf[0]);
			this.lIsVariable = false;
		}
		else if(checkAllLetters(this.elementsInsideIf[0]) == true)
		{
			this.lData = null;
			this.lIsVariable = true;
		}

		// checks if the right Expression is a constant or variable
		if (checkAllNumbers(this.elementsInsideIf[2]) == true)
		{
			this.rData = new Const(this.elementsInsideIf[2]);
			this.rIsVariable = false;
		}
		else if(checkAllLetters(this.elementsInsideIf[2]) == true)
		{
			this.rData = null;
			this.rIsVariable = true;
		}


	}

	/**
	 * Helper method to check if a string is all numbers
	 * @param s - string to be checked
	 * @return - boolean true if all numbers false if not
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

	@Override
	/**
	 * Execute evaluates the variables if any then compares them as longs
	 * If the comparison is true then it executes the lines within the if statement
	 * @param namespace - given namespace containing variables
	 */
	public void execute(HashMap<String,Expression> namespace)
	{
		Expression calculated;

		if (this.lIsVariable == true)
		{
			this.lData = new Variable(this.elementsInsideIf[0], namespace);
		}
		if (this.rIsVariable == true)
		{
			this.rData = new Variable(this.elementsInsideIf[2], namespace);
		}

		calculated = new Compare(this.lData.eval(namespace),this.rData.eval(namespace),this.elementsInsideIf[1]);

		if(calculated.eval(namespace) == 1)
		{
			for(Statement ifLines : ifCommands)
			{
				ifLines.execute(namespace);
			}
		}
	}

}

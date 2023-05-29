import java.util.HashMap;

/**
 * A class to contain the information needed for a print statement.
 *
 *
 * A subclass of Statement. This is a partially implemented class that supports Constant type print statements.
 * You should flesh it out to handle the other types of print statements.
 */
public class PrintStatement extends Statement
{

	private Expression data;
	private String inside;
	private Boolean isVariable = false;
	private Boolean printIsEmpty = false;

	/**
	 *
	 * @param s string to be printed
	 */
	public PrintStatement(String s)
	{
		this.inside = s.substring(6,s.length()-1); //extract the substring

		if(isEmpty(inside) == true)//this code works
		{
			this.data = null; //pretty sure i can set it to null
			this.printIsEmpty = true;

		}
		else if(checkAllNumbers(inside) == true)
		{
			this.data = new Const(inside);
		}
		else if(checkAllLetters(inside) == true)
		{
			this.isVariable = true;
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
	private boolean isEmpty(String s)
	{
		//checking if the string is empty
		if(s.equals(""))
		{
			return true;
		}
		return false;

	}


	@Override
	/*
	 * Execute either prints an empty line, retrieves a variable and prints the data, or prints a given numeric value
	 */
	public void execute(HashMap<String,Expression> namespace)
	{
		if(this.printIsEmpty && this.data == null)
		{
			System.out.println();
		}
		else if(this.isVariable)
		{
			this.data = namespace.get(inside);
			System.out.println(data.eval(namespace));
		}
		else
		{
			System.out.println(data.eval(namespace));
		}
	}

}

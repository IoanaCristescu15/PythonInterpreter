import java.util.HashMap;

public class ReturnStatement extends Statement
{
	private String toReturn;

	/**
	 *
	 * @param s - the string of what is to be returned
	 */
	public ReturnStatement(String s)
	{
		this.toReturn = s;
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
	 * Evaluates what the return content is and evalutes it into a const if necessary
	 * Adds the const into the variable namespace
	 * @param namespace - namespace containing variables of a function
	 */
	public void execute(HashMap<String,Expression> namespace) //namespace of the function
	{
		Expression tmp = null;
		if (checkAllNumbers(this.toReturn) == true)
		{
			tmp = new Const(this.toReturn);
		}
		else if (checkAllLetters(this.toReturn) == true)
		{
			tmp = new Variable(this.toReturn,namespace);
			tmp = new Const(tmp.eval(namespace));
		}

		namespace.put("return", tmp);

	}
}

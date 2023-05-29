import java.util.HashMap;
import java.util.ArrayList;

public class FunctionCall extends Expression
{

	private String[] parameters;
	private HashMap<String,FunctionDef> functions;
	private String functionName;
	private boolean paramIsEmpty;

	/**
	 * Constructor that takes the params and sets them to class variabels
	 * @param parameters - the params of a function
	 * @param functions - the hashmap of functions
	 * @param functionName - the function name in the form of a string
	 */
	public FunctionCall(String parameters, HashMap<String,FunctionDef> functions, String functionName)
	{
		this.parameters = parameters.split(","); //string of parameters from the call; ex. f(2,4,a) -> string is 2,4,a and then it's split
		this.functions = functions; //hashmap of functions from Boa
		this.functionName = functionName; //the name of the function -> gonna use it as key when accessing the hashmap of functions

	}

	/**
	 * Helper method to check if a string contains all numbers
	 * @param s - the string being checked
	 * @return - boolean true if all numbers, false if not
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
			if(Character.isDigit(s.charAt(i)) == false)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Helper method to check if a string contains all letters
	 * @param s - the string being checked
	 * @return - boolean true if all numbers, false if not
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
	 * Creates a function specific namespace and adds the data correlating to the functions params by accessing the functionDefinition
	 * Runs the function commands
	 * Then evaluates the return statement from the function namespace and returns it accordingly
	 * @param namespace - the namespace containing variables from Boa
	 */
	public long eval(HashMap<String,Expression> namespace)
	{
		HashMap<String,Expression> functionNamespace = new HashMap<String,Expression>();
		ArrayList<Expression> data = new ArrayList<Expression>();
		String currentParameter;
		FunctionDef currentFunction;
		//get the current function definition
		currentFunction = this.functions.get(functionName);

		for (int i = 0; i < parameters.length; i++)
		{
			//get the numerical value of each parameter (before this each parameter is only a string)
			if(parameters[i].equals(""))
			{
				break;
			}
			else if (checkAllNumbers(this.parameters[i]) == true)
			{
				data.add(new Const(this.parameters[i]));
			}
			else if (checkAllLetters(this.parameters[i]) == true)
			{
				Expression tmp = new Variable(this.parameters[i],namespace);
				//				Long tmpData = tmp.eval(namespace); //has to be the old namespace
				data.add(new Const(tmp.eval(namespace)));
			}
			// Chained parameters
			else
			{
				ArrayList<String> dataAndOps = new ArrayList<String>();
				String s = this.parameters[i];
				int k = 0;
				for (int j = 1; j < s.length()-1; j++)
				{
					if(s.charAt(j) == '+' || s.charAt(j) == '-' || s.charAt(j) == '*' || s.charAt(j) == '/' || s.charAt(j) == '%')
					{
						if(s.charAt(j-1) != '+' && s.charAt(j-1) != '-' && s.charAt(j-1) != '*' && s.charAt(j-1) != '/' && s.charAt(j-1) != '%')
						{
							dataAndOps.add(s.substring(k,j));
							dataAndOps.add(s.substring(j,j+1));
							k = j + 1;
						}
					}
				}
				dataAndOps.add(s.substring(k,s.length()));
				Expression tmp = new Chained(dataAndOps);
				data.add(new Const(tmp.eval(namespace)));
			}
			//get the name of the current parameter from function definition
			currentParameter = currentFunction.getParameterName(i);
			//add the current data from function call in the new hashmap of the function
			functionNamespace.put(currentParameter, data.get(i)); //out of bounds here
			//the reason it's out of bounds is because data doesn't get added anywhere so data.get(0) doesn't exist
		}



		for(Statement functionLines : currentFunction.getCommands())
		{
			functionLines.execute(functionNamespace);
		}

		Expression toReturn = functionNamespace.get("return");
		return toReturn.eval(functionNamespace);

	}
}

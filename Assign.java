import java.util.HashMap;
import java.util.ArrayList;

/**
 * Subclass of the class Statement
 *
 * Evaluates a statement that assigns a value
 * Examples: a = 3, a = b, a = 5 + 3, a = b + c, a = a + 5
 */
public class Assign extends Statement
{
	private ArrayList<Expression> data;
	private String[] elementsInsideExpression;
	private ArrayList<Boolean> isVariable; // checks for each thing if it's a variable or not
	private ArrayList<Boolean> isFunction; // checks for each thing if it's a function or not
	private int operatorsNumber = 0; // counts the number of operators in the statement
	private FunctionCall tmp;
	private ArrayList<String> dataAndOps;

	// can deal with more than 2 operators

	/**
	 * Constructor that identifies the elements of the assignment
	 *
	 * @param s the string of elements combined
	 * @param functions the hashmap for functions
	 */
	public Assign(String s, HashMap<String,FunctionDef> functions)
	{
		data = new ArrayList<Expression>();
		isVariable = new ArrayList<Boolean>();
		isFunction = new ArrayList<Boolean>();
		dataAndOps = new ArrayList<String>();
		//elementsInsideExpression = new String[5];
		// separates the string into an array of strings
		this.elementsInsideExpression = s.split(" ");//write a method that prints the elements inside expression then call it in boa or add a main method in assign

		for(int i = 2; i < this.elementsInsideExpression.length;i++) //starts from 2 because for a = 4 + 5 * 2, this.elementsInsideExpression[0] = a and this.elementsInsideExpression[1] = =
		{
			this.dataAndOps.add(this.elementsInsideExpression[i]); //should follow that at 0 is data then every other one is data and the in betweens are operators
		}
		// looks at the elements and decide if they are numbers, variables or functions
		for (int i = 2; i < this.elementsInsideExpression.length; i+=2)
		{
			if(checkAllNumbers(this.elementsInsideExpression[i]) == true)
			{
				this.data.add(new Const(this.elementsInsideExpression[i]));
				this.isVariable.add(false);
				this.isFunction.add(false);
			}
			else if(checkAllLetters(this.elementsInsideExpression[i]) == true)
			{				this.data.add(null);
				this.isVariable.add(true);
				this.isFunction.add(false);
			}
			else
			{
				String parameters = this.elementsInsideExpression[i].trim().substring((this.elementsInsideExpression[i].trim().indexOf('(')+1),this.elementsInsideExpression[i].trim().length()-1);
				String functionName = this.elementsInsideExpression[i].trim().substring(0,this.elementsInsideExpression[i].trim().indexOf('('));
				this.tmp = new FunctionCall(parameters, functions, functionName);
				this.data.add(tmp);
				this.isVariable.add(false);
				this.isFunction.add(true);
			}
			this.operatorsNumber+=1;
		}
	}

	/**
	 * Helper method to see if a string is made up of all numbers or not
	 * @param s string to check
	 * @return boolean - true if all numbers false if not
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
	 * Helper method to see if a string is made up of all letters or not
	 * @param s - String to check
	 * @return - boolean true or false, true if all letters false if not
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

	// can only deal with 1 or 2 operators
	// for more than 2 operators we need to consider that some calculations have priority over others (*, / over +, -)
	// also the else statement has to be changed so it can deal with more than 2 operators
	// a for loop would be necessary
	/*
	 * for (int i = 3; i < this.elementsInsideExpression.length; i += 2) // looks at +, -, /, *
	 * now we would use the expression array of data and boolean array of isVariable but have to be carefull at the indices
	 */

	@Override
	/**
	 * Execute method that calculates the assignment then adds it to the designated namespace
	 * @param namespace - the namespace that contains the variables
	 */
	public void execute(HashMap<String,Expression> namespace)
	{
		Expression calculated; // variable that stores the data as an expression after the execution of calculations

		// if there is only one variable - it is added in the hashmap
		if (this.elementsInsideExpression.length == 3)
		{
			if (this.isVariable.get(0) == true && this.isFunction.get(0) == false)
			{
				this.data.set(0, new Variable(this.elementsInsideExpression[2], namespace));
			}
			else if (this.isVariable.get(0) == false && this.isFunction.get(0) == true)
			{
				long functionValue = tmp.eval(namespace);
				//System.out.println(functionValue);
				this.data.set(0, new Const(functionValue));
			}
			namespace.put(this.elementsInsideExpression[0], this.data.get(0));
		}
		else if(this.elementsInsideExpression.length == 5)
		{
			// fetches the data from the namespace if it's a variable; otherwise the data is already known
			if (this.isVariable.get(0) == true)
			{
				this.data.set(0, new Variable(this.elementsInsideExpression[2], namespace));
			}
			if (this.isVariable.get(1) == true)
			{
				this.data.set(1, new Variable(this.elementsInsideExpression[4], namespace));
			}

			// perform one operation at a time through the operation class
			calculated = new Operations(this.data.get(0).eval(namespace), this.data.get(1).eval(namespace), this.elementsInsideExpression[3]);
			// adds the expression calculated in namespace
			namespace.put(this.elementsInsideExpression[0], calculated);
		}
		else
		{
			// this is a chain type of operation
			calculated = new Chained(this.dataAndOps);
			// adds the expression calculated in namespace
			namespace.put(this.elementsInsideExpression[0], new Const(calculated.eval(namespace)));
		}
	}

}

import java.util.HashMap;
import java.util.ArrayList;

public class Chained extends Expression
{
	private ArrayList<String> dataAndOps;

	/**
	 * 
	 * @param dataAndOps a string ArrayList given from the Assign class containing the data and operators 
	 */
	public Chained(ArrayList<String> dataAndOps)
	{
    this.dataAndOps = dataAndOps;
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
			if(Character.isLetter(s.charAt(i)) == true)
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
	 * @Return long value of the calculated assignment
	 * Uses two for loops to find operators in order of importance, 
	 * once found will calculate the sub expression then replace with the calculated value until no more operators are left
	 */
	public long eval(HashMap<String,Expression> namespace)
	{

		for(int i = 1; i < this.dataAndOps.size()-1;i++)//shouldnt ever be the one at index 0
		{
			if(this.dataAndOps.get(i).equals("/") || this.dataAndOps.get(i).equals("*") || this.dataAndOps.get(i).equals("%"))
			{
				Long left = null;
				Long right = null;
				Long answer;
				//gets the variables to be longs and string of nums to be longs
				if(checkAllLetters(this.dataAndOps.get(i-1)) == true)//checks left
				{
					//takes the one before it, turns it into a variable using the string then takes that and makes it a variable then
					//evaluates it to a long
          left = new Variable(this.dataAndOps.get(i-1),namespace).eval(namespace);
				}
				if(checkAllNumbers(this.dataAndOps.get(i-1)) == true)//checks left
				{
					//takes the one before it, turns it into a variable using the string then takes that and makes it a variable then
					//evaluates it to a long
					left = new Const(this.dataAndOps.get(i-1)).eval(namespace);
				}
				if(checkAllLetters(this.dataAndOps.get(i+1)) == true)//checks right
				{
					right = new Variable(this.dataAndOps.get(i+1),namespace).eval(namespace);
				}
				if(checkAllNumbers(this.dataAndOps.get(i+1)) == true)//checks right
				{
					//takes the one before it, turns it into a variable using the string then takes that and makes it a variable then
					//evaluates it to a long
					right = new Const(this.dataAndOps.get(i+1)).eval(namespace);
				}
				answer = new Operations(left,right,this.dataAndOps.get(i)).eval(namespace);
				String ans = "" + answer;
				this.dataAndOps.set(i-1,ans); //replace the left value with the new evaluated answer
        //delete the operator and the right value
				this.dataAndOps.remove(i+1);
				this.dataAndOps.remove(i); //this is under the assumption the length was changed automatically
				i = 0; //gets in the for loop; i++ is performed; starts from i = 1
			}

		}


		for(int i = 1; i < this.dataAndOps.size()-1;i++)//shouldnt ever be the one at index 0 --> the -1 may not be needed but because we do i+1 just in case
		{
			if(this.dataAndOps.get(i).equals("+") || this.dataAndOps.get(i).equals("-"))
			{
				Long left = null;
				Long right = null;
				Long answer;
				//gets the variables to be longs and string of nums to be longs
				if(checkAllLetters(this.dataAndOps.get(i-1)) == true)//checks left
				{
					//takes the one before it, turns it into a variable using the string then takes that and makes it a variable then
					//evaluates it to a long
					left = new Variable(this.dataAndOps.get(i-1),namespace).eval(namespace);
				}
				if(checkAllNumbers(this.dataAndOps.get(i-1)) == true)//checks left
				{
					//takes the one before it, turns it into a variable using the string then takes that and makes it a variable then
					//evaluates it to a long
					left = new Const(this.dataAndOps.get(i-1)).eval(namespace);
				}
				if(checkAllLetters(this.dataAndOps.get(i+1)) == true)//checks right
				{
					right = new Variable(this.dataAndOps.get(i+1),namespace).eval(namespace);
				}
				if(checkAllNumbers(this.dataAndOps.get(i+1)) == true)//checks right
				{
					//takes the one before it, turns it into a variable using the string then takes that and makes it a variable then
					//evaluates it to a long
					right = new Const(this.dataAndOps.get(i+1)).eval(namespace);
				}
				answer = new Operations(left,right,this.dataAndOps.get(i)).eval(namespace);
				String ans = "" + answer;
				this.dataAndOps.set(i-1,ans); //replace the left value with the new evaluated answer
        //delete the operator and the right value
				this.dataAndOps.remove(i+1);
				this.dataAndOps.remove(i); //this is under the assumption the length was changed automatically
				i = 0; //gets in the for loop; i++ is performed; starts from i = 1
			}

		}
		//currently the thing at index 0 is the answer, but in string format. So turn it into a const using the string
		//constructor then evaluate it to be a long which is what we want to return
		return new Const(this.dataAndOps.get(0)).eval(namespace);


	}
}

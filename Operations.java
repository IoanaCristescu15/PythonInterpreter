import java.util.HashMap;

// child of expression
public class Operations extends Expression {

	private long data;

	/**
	 * Evaluates and sets the data variable
	 * @param left - long to the left of the operator
	 * @param right - long to the right of the operator
	 * @param s - the operator in string format
	 */
	public Operations(long left, long right, String s)
	{
		if (s.equals("+"))
		{
			this.data = left + right;
		}
		else if (s.equals("-"))
		{
			this.data = left - right;
		}
		else if (s.equals("*"))
		{
			this.data = left * right;
		}
		else if (s.equals("/"))
		{
			this.data = left / right;
		}
		else if(s.contentEquals("%"))
		{
			this.data = left % right;
		}


	}


	@Override
	public long eval(HashMap<String,Expression> namespace)
	{
		return data;
	}

}

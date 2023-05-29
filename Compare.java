import java.util.HashMap;

// subclass of expression
//<, <=, ==, >=, >, !=

/**
 * A class that compares two longs based on a given operator
 */
public class Compare extends Expression
{

	private long data = 0;

	/**
	 *
	 * @param left - the long to the left of the operator
	 * @param right - the long to the right of the operator
	 * @param s - the string containing the operator
	 */
	public Compare(long left, long right, String s)
	{
		if (s.equals("<"))
		{
			if (left < right)
			{
				this.data = 1;
			}
		}
		else if (s.equals("<="))
		{
			if (left <= right)
			{
				this.data = 1;
			}
		}
		else if (s.equals("=="))
		{
			if (left == right)
			{
				this.data = 1;
			}
		}
		else if (s.equals(">="))
		{
			if (left >= right)
			{
				this.data = 1;
			}
		}
		else if (s.equals(">"))
		{
			if (left > right)
			{
				this.data = 1;
			}
		}
		else if (s.equals("!="))
		{
			if (left != right)
			{
				this.data = 1;
			}
		}
	}


	@Override
	/**
	 * Returns the data of 1 or 0
	 */
	public long eval(HashMap<String,Expression> namespace)
	{
		return data;
	}
}

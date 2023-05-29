import java.util.HashMap;
/*
 *
 * a subclass of expression that represents constant integer expressions
 */

public class Const extends Expression
{

	private long data;

	public Const(String s)
	{
		this.data = Long.parseLong(s);
	}

	public Const(long data)
	{
		this.data = data;
	}

	@Override
	public long eval(HashMap<String,Expression> namespace)
	{
		return data;
	}

}

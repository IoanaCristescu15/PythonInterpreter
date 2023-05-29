import java.util.HashMap;

public class Variable extends Expression
{
	private Expression data;

	/**
	 *
	 * @param s - variable name in the form of the string
	 * @param namespace - namespace of variables
	 */
	public Variable(String s, HashMap<String,Expression> namespace)
	{
		this.data = namespace.get(s);;
	}

	@Override
	public long eval(HashMap<String,Expression> namespace) {
		return data.eval(namespace);
	}
}

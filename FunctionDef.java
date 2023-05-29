import java.util.ArrayList;
import java.util.HashMap;
public class FunctionDef extends Statement
{
	private ArrayList<Statement> functionCommands;
	private String paramsAll;
	private String[] paramsSeparated;

	/**
	 *
	 * @param functionCommands the parsed function commands
	 * @param params the string of parameters for the function
	 */
	public FunctionDef(ArrayList<Statement> functionCommands, String params)
	{
		this.functionCommands = functionCommands;
		// Do we need this line?
		this.paramsAll = params; //this is just in String format
		this.paramsSeparated = params.split(","); //this is an array of the params now --> this.length will get how many params exist
	}

	public ArrayList<Statement> getCommands()
	{
		return this.functionCommands;
	}

	public String getParameterName(int index)
	{
		return this.paramsSeparated[index];
	}

	public void execute(HashMap<String,Expression> namespace)
	{

	}
}

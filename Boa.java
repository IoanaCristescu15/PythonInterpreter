import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.HashMap;

/**
 * This class implements an interpreter for the Boa language, a subset of
 * Python.
 *
 *
 * This file is the entry point for your interpreter. The main method will
 * - Read the contents of the file into a string arraylist
 * - parse it into a statement arraylist
 * - run each statement in order
 *
 * The parse method, as given, only handles a single type of statement. add to it to handle the other types
 * of expressions
 */
public class Boa
{


	/**
	 * Usage:
	 * java Boa filename.py
	 * will run the interpreter on the code contained in filename.py
	 *
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		//takes in a new file - in this case will be a python file
		Scanner inp = new Scanner(new File(args[0]));

		//an arrayList of all the rawcode from the python file
		ArrayList<String> rawCode = new ArrayList<String>();

		//while the python file has next
		//add the nextLine of the python file to the raw code arrayList
		//the arrayList will look like a bunch of long strings at each index
		while(inp.hasNext())
		{
			rawCode.add(inp.nextLine());
		}
		//this is an arrayList of statements that should be printed
		//the parse method finds any statement that says print and adds it to this main statement
		ArrayList<Statement> mainStatements = new ArrayList<Statement>();

		HashMap<String,FunctionDef> functionDefs = new HashMap<String, FunctionDef>();

		//System.out.println(rawCode.size());
		parse(rawCode, 0,rawCode.size(), mainStatements, functionDefs);
		//the namespace is essentially like a stack/heap
		//you have variables and those variables have data that change with new assignments
		HashMap<String,Expression> namespace = new HashMap<String,Expression>();


		for(Statement s : mainStatements)
		{
			//execute should ideally evaluate the namesapce aka
			//find in the hashmap the variable name then print the attatched data
			s.execute(namespace);
		}
	}

	/**
	 * A function that parses an arrayList of rawCodeStatements and turns it
	 * into CodeElement objects.
	 *
	 * @param rawCode - the list of all code statements
	 * @param start - the first index to parse into code
	 * @param end - one more than the last index to parse into code
	 * @param statementArray - the array that statements will be added to
	 * @param functionDefs - the hashmap of functions
	 *
	 * @return An arraylist of statement objects, one for each main statement
	 * at the current level
	 */
	public static ArrayList<Statement> parse(ArrayList<String> rawCode, int start, int end, ArrayList<Statement> statementArray, HashMap<String,FunctionDef> functionDefs)
	{
		ArrayList<Statement> statements = statementArray;
		boolean isFunction = false;
		int lineNo = start;
		while(lineNo < end)
		{
			String s = rawCode.get(lineNo);
			if(s.contains("print"))
			{
				statements.add(new PrintStatement(s.trim()));//s.trim gets rid of whitespace
			}
			//if not in a function and an assignment (and not like a loop or something)
			if (s.contains("=") && !s.contains("if") && !s.contains("while"))
			{
				statements.add(new Assign(s.trim(), functionDefs)); //this passes in the function definition namespace
			}
			if((s.contains("if")))
			{

				int indentLevel = calculateIndentLevel(s);
				int linesInIf = 0;
				int startLine = lineNo+1; //this is the first line after the if statement
				String ifLine = rawCode.get(startLine);
				while(calculateIndentLevel(ifLine) > indentLevel)
				{
					linesInIf++;
					startLine++;
					if(startLine < rawCode.size())
					{
						ifLine = rawCode.get(startLine); //this will be the next line that is then checked for indent level
					}
					else
					{
						break;
					}
				}
				int endLine = lineNo + linesInIf; //this is the last line of the if block --> this will still be part of if
				ArrayList<Statement> ifCommands = new ArrayList<Statement>();
				parse(rawCode, (lineNo+1), endLine+1, ifCommands, functionDefs); //the +1 is for the end param the end param is one more than the last index to parse into code so this will work

				String sendOperation = s.trim().substring(3,s.trim().length()-2); //the trim somewhat helped
				statements.add(new IfStatement(sendOperation,ifCommands));
				lineNo+=linesInIf;
			}
			if(s.contains("while"))
			{
				int indentLevel = calculateIndentLevel(s);
				int linesInWhile = 0;
				int startLine = lineNo+1; //this is the first line after the if statement
				String whileLine = rawCode.get(startLine);
				while(calculateIndentLevel(whileLine) > indentLevel)
				{
					linesInWhile++;
					startLine++;
					if(startLine < rawCode.size())
					{
						whileLine = rawCode.get(startLine); //this will be the next line that is then checked for indent level
					}
					else
					{
						break;
					}
				}
				int endLine = lineNo + linesInWhile; //this is the last line of the if block --> this will still be part of if
				ArrayList<Statement> whileCommands = new ArrayList<Statement>();
				parse(rawCode, (lineNo+1), endLine+1, whileCommands, functionDefs); //the +1 is for the end param the end param is one more than the last index to parse into code so this will work
				String sendOperation = s.trim().substring(6,s.trim().length()-2);
				statements.add(new WhileStatement(sendOperation,whileCommands));

				lineNo+=linesInWhile;
			}
			if(s.contains("def"))
			{
				//this is the string of params
				String params = s.substring((s.indexOf('(')+1), s.length()-2);
				int indentLevel = 0;
				int linesInFunct = 0;
				int startLine =  lineNo+1;
				String functLine = rawCode.get(startLine);
				while(calculateIndentLevel(functLine) > indentLevel)//getting the lines within the function
				{
					linesInFunct++;
					startLine++;
					if(startLine < rawCode.size())
					{
						functLine = rawCode.get(startLine);
					}
					else
					{
						break;
					}
				}

				int endLine = lineNo + linesInFunct;
				ArrayList<Statement> functCommands = new ArrayList<Statement>();
				parse(rawCode, (lineNo+1), endLine+1, functCommands, functionDefs); //getting the liens in the function and turning them into the correct things
				FunctionDef function = new FunctionDef(functCommands, params);

				String functionName = s.substring(4, s.indexOf('(')); //4 because calculating def Name
				functionDefs.put(functionName, function); //have to get the right string for this
				lineNo+=linesInFunct;
			}
			if(s.contains("return"))
			{
				String returnVals = s.trim().substring(7).trim();
				statements.add(new ReturnStatement(returnVals));
			}
			lineNo++;
		}
		return statements;
	}
	/**
	 * A possibly useful method when dealing with if/while/function statements.
	 * Will be useful for the second part of the project
	 *
	 * @param line - the line to identify the indent level
	 * @return an integer value of how much the given line is indented
	 */
	protected static int calculateIndentLevel(String line)
	{
		int x = 0;
		while(true)
		{
			char c = line.charAt(x);
			if(c == ' ' || c == '\t')
			{
				x++;
			}
			else
			{
				return x;
			}
		}
	}

}

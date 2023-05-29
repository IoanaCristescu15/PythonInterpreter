import java.util.HashMap;

/**
 * This abstract class contains the ADT for an expression - a piece of code that evaluates to an integer.
 * An abstract class that other types of expressions (const, additions, multiplications, function calls, etc.) can inherit from.
 * Subclasses need to implement the eval(HashMap<String,Expression> namespace) method.
 *
 * An expression is unlike a statement, expressions are more like the nouns i think
 * They are the something while a statement does the something
 */

/*
 * can have many expression classes that extend from this to account for
 * - assignments to a variable
 * - assignments with an expression
 * - assignments with a function call
 *
 * the issue is how to make them all work together, currently the thing that would bind them is the eval which prints
 * out the information from a hashmap
 *
 * perhaps when executing there needs to be something done that says whether to call the execute for one class or another since
 * currently the eval here doesn't do anything so it must be overriden somewhere --> maybe it can tell depending on the params?
 * except the parmams have to be the same so nevermind
 *
 */
public abstract class Expression
{

	public abstract long eval(HashMap<String,Expression> namespace);

}

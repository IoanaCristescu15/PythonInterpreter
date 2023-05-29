import java.util.ArrayList;
import java.util.HashMap;

/**
 * This abstract class contains the ADT for a Statement - a line of code
 * Statements are the do something (like a verb)
 *
 * An abstract class that other types of statements (print statements, if statements, assignment
 * statements, etc.) can inherit from. Subclasses need to implement the execute(HashMap<String,Expression> namespace) method.
 */
public abstract class Statement
{

	public abstract void execute(HashMap<String,Expression> namespace);

}

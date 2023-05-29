# PythonInterpreter
Author: Ioana Cristescu

## Direction for compiling:
To compile the program: javac Boa.java <br /> <br />
To run executable: java Boa [file_name.py] <br />
&emsp;- first argument: python file to be executed through the interpretor coded in Java <br /> <br />
To build and run the test: javac [test.java] and java [test] <br />
&emsp;- test.java: Project1Test.java or Project2Test.java

## Design Decisions:
### Boa
This class implements an interpreter for the Boa language, a subset of Python. It reads the contents of the given pyhton file into a string arraylist, parses it into a statement arraylist, and runs each statement in order.

### Statement
Statement is an abstract class that other types of statements (such as print statements, if statements, assignment statements, etc.) can inherit from.

#### Assign
Assign is a class that evaluates a statement that assigns a value (for examples: a = 3, a = b, a = 5 + 3, a = b + c, a = a + 5). It is able to identify the type of each element involved in the assignment (numbers, variables or functions).

#### PrintStatement
PrintStatement is a class that contains the information needed for a print statement. It can handle printing an empty line, printing the value of a variable, or printing a given numeric value.

#### FunctionDef
FunctionDef is a class that gives access to a function's chain of commands or the function's name upon def. 

#### IfStatement
IfStatement is a class that executes an if statement if the condition is true. This process is done through the following steps: <br />
&emsp;- it evaluates the variables identified in the if condition and compares them  <br />
&emsp;- iff the comparison is true then, it executes the lines within the if statement <br />

#### ReturnStatement
ReturnStatement is a class that executes the return command of a function. This process is done through the following steps: <br />
&emsp;- it identifies the return content and evalutes it into a const if necessary <br />
&emsp;- it adds the const into the variable namespace <br />

#### WhileStatement
WhileStatement is a class that evaluates the while condition and executes the corresponding lines until the condition evaluates to false.

### Expression
Expression is an abstract class that other types of expressions (such as const, additions, multiplications, function calls, etc.) can inherit from.

#### Chained
Chained is a class that evaluates chained expressions and returns their value by identifying the operators in order of importance. Once one operator is found, it calculates the sub expression, and replaces with the calculated value. This process is repeated until no more operators are left. 

#### Compare
Compare is a class that compares two variables based on a given operator. It can handle the following operators: <, <=, ==, >=, >, !=.

#### Const 
Const is a class that represents constant integer expressions.

#### FunctionCall
FunctionCall is a class that evaluates a function upon it being called. This process is done through the following steps: <br />
&emsp;- it creats a function specific namespace and adds the data correlating to the functions params by accessing the functionDefinition <br />
&emsp;- it runs the function commands <br />
&emsp;- it evaluates the return statement from the function namespace and returns it accordingly <br />

#### Operations
Operations is a class that evaluates a single operration. The handled operations are: +,-, *, /, %.

#### Variable
Variable is a class that evaluates the value of a variable.



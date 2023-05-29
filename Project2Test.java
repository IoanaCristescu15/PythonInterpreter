import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
/**
 * This class is a collection of JUnit test cases for Homework5
 * You should add tests to these
 *
 */

public class Project2Test {

  //***************************
  //Don't worry about this part when testing - Begin
  //These are used to redirect/compare Systemout
  //***************************
  private static final InputStream systemIn = System.in;
  private static final PrintStream systemOut = System.out;

  private ByteArrayInputStream testIn;
  private static ByteArrayOutputStream testOut;
  @BeforeEach
  public void setUpOutput() {
    testOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOut));
  }

  private void provideInput(String data) {
    testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }

  private String getOutput() {
    return testOut.toString();
  }

  @AfterEach
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }
  //***************************
  //Don't worry about this part when testing - END
  //***************************}
  //
  @Nested
  @DisplayName("Project Part 1 tests")
  class ProjectPart1Tests {


    @Test
    public void PrintConst1() throws FileNotFoundException {
      Boa.main(new String[] {"testconst1.py"});
      assertEquals("1\n2\n3\n4\n5\n", getOutput());
    }


    @Test
    public void PrintAssign1() throws FileNotFoundException {
      Boa.main(new String[] {"testvar1.py"});
      assertEquals("6\n8\n10\n12\n", getOutput());
    }

    @Test
    public void PrintTest1() throws FileNotFoundException {
      Boa.main(new String[] {"test1.py"});
      assertEquals("\n\n3\n4\n", getOutput());
    }

    @Test
    public void PrintTest2() throws FileNotFoundException {
      Boa.main(new String[] {"test2.py"});
      assertEquals("2\n3\n1\n8\n", getOutput());
    }

    @Test
    public void PrintTest3() throws FileNotFoundException {
      Boa.main(new String[] {"test3.py"});
      assertEquals("2\n", getOutput());
    }

    @Test
    public void PrintTest4() throws FileNotFoundException {
      Boa.main(new String[] {"test4.py"});
      assertEquals("0\n", getOutput());
    }

    @Test
    public void PrintTest5() throws FileNotFoundException {
      Boa.main(new String[] {"test5.py"});
      assertEquals("8\n", getOutput());
    }

    @Test
    public void PrintTest6() throws FileNotFoundException {
      Boa.main(new String[] {"test6.py"});
      assertEquals("3\n6\n", getOutput());
    }

    @Test
    public void PrintTest7() throws FileNotFoundException {
      Boa.main(new String[] {"test7.py"});
      assertEquals("-7\n\n0\n", getOutput());
    }
  }

  @Nested
  @DisplayName("Project Part 2 tests")
  class ProjectPart2Tests {

    @Test
    public void PrintTestIf1() throws FileNotFoundException
    {
      Boa.main(new String[] {"TestIf1.py"});
      assertEquals("10\n5\n4\n", getOutput());
    }

    @Test
    public void PrintTestIf2() throws FileNotFoundException
    {
      Boa.main(new String[] {"TestIf2.py"});
      assertEquals("4\n2\n15\n20\n\n5\n5\n\n10\n6\n", getOutput());
    }

    @Test
    public void PrintTestIf3() throws FileNotFoundException
    {
      Boa.main(new String[] {"TestIf3.py"});
      assertEquals("-15\n-3\n", getOutput());
    }

    @Test
    public void PrintTestWhile1() throws FileNotFoundException
    {
      Boa.main(new String[] {"TestWhile1.py"});
      assertEquals("2\n3\n4\n", getOutput());
    }

    @Test
    public void PrintTestWhile2() throws FileNotFoundException
    {
      Boa.main(new String[] {"TestWhile2.py"});
      assertEquals("1\n\n1\n2\n\n2\n\n1\n2\n\n3\n\n1\n2\n\n", getOutput());
    }

    @Test
    public void PrintTestWhile3() throws FileNotFoundException
    {
      Boa.main(new String[] {"TestWhile3.py"});
      assertEquals("-2\n-2\n-2\n-2\n", getOutput());
    }

    @Test
    public void PrintTestIfAndWhile1() throws FileNotFoundException
    {
      Boa.main(new String[] {"TestIfAndWhile1.py"});
      assertEquals("16\n4\n1\n", getOutput());
    }

    @Test
    public void PrintTestIfAndWhile2() throws FileNotFoundException
    {
      Boa.main(new String[] {"TestIfAndWhile2.py"});
      assertEquals("20\n10\n", getOutput());
    }

    @Test
    public void PrintTestFunction1() throws FileNotFoundException
    {
      Boa.main(new String[] {"TestFunction1.py"});
      assertEquals("6\n\n\n100\n", getOutput());
    }

    @Test
    public void PrintTestFunction2() throws FileNotFoundException
    {
      Boa.main(new String[] {"TestFunction2.py"});
      assertEquals("7\n", getOutput());
    }

    @Test
    public void PrintTestFunction3() throws FileNotFoundException
    {
      Boa.main(new String[] {"TestFunction3.py"});
      assertEquals("5\n", getOutput());
    }

    @Test
    public void PrintTestFunction4() throws FileNotFoundException
    {
      Boa.main(new String[] {"TestFunction4.py"});
      assertEquals("10000\n\n1\n-2\n", getOutput());
    }

    @Test
    public void PrintTestRecursiveFunction1() throws FileNotFoundException
    {
      Boa.main(new String[] {"TestRecursiveFunction1.py"});
      assertEquals("2\n", getOutput());
    }

}

}

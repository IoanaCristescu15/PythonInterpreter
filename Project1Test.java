import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
/**
 * This class is a collection of JUnit test cases for Homework5
 * You should add tests to these
 *
 */

public class Project1Test {

  //***************************
  //Don't worry about this part when testing - Begin
  //These are used to redirect/compare Systemout
  //***************************
  private static final InputStream systemIn = System.in;
  private static final PrintStream systemOut = System.out;

  private ByteArrayInputStream testIn;
  private static ByteArrayOutputStream testOut;
  private String n = System.lineSeparator(); // "+n+" in mac/unix, \r"+n+" in windows
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

    @Nested
    @DisplayName("Variable tests")
    class ProjectVariableTests {

      @Test
      public void PrintConst1() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testconst1.py"});
        assertEquals("1"+n+"2"+n+"3"+n+"4"+n+"5"+n+"", getOutput());
      }


      @Test
      public void PrintVar1() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testvar1.py"});
        assertEquals("6"+n+"8"+n+"10"+n+"12"+n+"", getOutput());
      }

      @Test
      public void PrintVar2() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testvar2.py"});
        assertEquals("8"+n+"8"+n+"10"+n+"8"+n+"4"+n+"10"+n+"8"+n+"", getOutput());
      }

      @Test
      public void PrintVar3() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testvar3.py"});
        assertEquals("8"+n+"4"+n+"", getOutput());
      }
    }

    @Nested
    @DisplayName("Arithmetic tests")
    class ProjectArithmeticTests {
      @Test
      public void Arith() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testassign1.py"});
        assertEquals("9"+n+"9"+n+"8"+n+"-5"+n+"-45"+n+"3"+n+"0"+n+"", getOutput());
      }
    }





  }

  @Nested
  @DisplayName("Project Part 2 tests")
  class ProjectPart2Tests {
    @Nested
    @DisplayName("If tests")
    class ProjectIfTests {
      @Test
      public void Iftest1() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testif1.py"});
        assertEquals("1"+n+"", getOutput());
      }

      @Test
      public void Iftest2() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testif2.py"});
        assertEquals("1"+n+"2"+n+"4"+n+"5"+n+"", getOutput());
      }

      @Test
      public void Iftest3() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testif3.py"});
        assertEquals("5"+n+"7"+n+"", getOutput());
      }
    }

    @Nested
    @DisplayName("While tests")
    class ProjectWhileTests {
      @Test
      public void Whiletest1() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testwhile1.py"});
        assertEquals("", getOutput());
      }

      @Test
      public void Whiletest2() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testwhile2.py"});
        assertEquals("7142857857142"+n, getOutput());
      }

      @Test
      public void Whiletest3() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testwhile3.py"});
        assertEquals("40"+n+"781"+n+"10695720"+n, getOutput());
      }

    }

    @Nested
    @DisplayName("Function tests")
    class ProjectFuncTests {
      @Test
      public void Functest1() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testfunc1.py"});
        assertEquals("5"+n, getOutput());
      }
      @Test
      public void Functest2() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testfunc2.py"});
        assertEquals("12"+n, getOutput());
      }
      @Test
      public void Functest3() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testfunc3.py"});
        assertEquals("5"+n+"7"+n+"35"+n+"5"+n+"12"+n+"35"+n+"47"+n+"12"+n+"35"+n+"47"+n+"12"+n+"70"+n, getOutput());
      }
      @Test
      public void Functest4() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testfunc4.py"});
        assertEquals("29686813949951"+n+"59373627899904"+n, getOutput());
      }
      @Test
      public void Functest5() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testfunc5.py"});
        assertEquals("1"+n+"1"+n+"2"+n+"3"+n+"13"+n+"89"+n, getOutput());
      }
      @Test
      public void Functest6() throws FileNotFoundException {
        Boa.main(new String[] {"testinputs/testfunc6.py"});
        assertEquals("20"+n+"20"+n+"10"+n+"10"+n+"2"+n+"2"+n+"8"+n+"8"+n+"25"+n+"25"+n+"45"+n+"45"+n, getOutput());
      }
    }
  }


}

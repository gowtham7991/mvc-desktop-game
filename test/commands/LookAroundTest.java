package commands;

import static org.junit.Assert.assertEquals;

import controller.commands.Command;
import controller.commands.LookAround;
import mocks.MockModelValid;
import mocks.MockViewValid;
import model.Model;
import org.junit.Before;
import org.junit.Test;
import view.View;

/**
 * This is the test class for the command for Look Around.
 * 
 */
public class LookAroundTest {

  private StringBuffer log;

  @Before
  public void setUp() {
    log = new StringBuffer();
  }

  @Test
  public void testExecute() {
    int uniqueId = 345;
    Model m = new MockModelValid(log, uniqueId);
    View v = new MockViewValid(log, uniqueId);
    Command c = new LookAround();
    c.execute(m, v);
    assertEquals("Inside method lookAround. Id = 345"
        + "Inside method openLookAroundPrompt. Id = 345345"
        + "Inside method refresh. Id = 345", log.toString());
  }

}

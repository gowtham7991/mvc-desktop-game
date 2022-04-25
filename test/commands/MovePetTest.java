package commands;

import static org.junit.Assert.assertEquals;

import controller.commands.Command;
import controller.commands.MovePet;
import mocks.MockModelValid;
import mocks.MockViewValid;
import model.Model;
import org.junit.Before;
import org.junit.Test;
import view.View;

/**
 * This is the test class for the command for Move Pet.
 * 
 */
public class MovePetTest {

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
    Command c = new MovePet();
    c.execute(m, v);
    assertEquals("Inside method getAllSpaces. Id = 345"
        + "Inside method openPrompt. Id = 345"
        + "Choose a space[space]Inside method movePet. "
        + "Id = 345345Inside method showSuccessMessage. "
        + "Id = 345Pet moved!Inside method refresh. Id = 345", log.toString());
  }

}

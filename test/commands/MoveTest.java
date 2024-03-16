package commands;

import static org.junit.Assert.assertEquals;

import controller.commands.Command;
import controller.commands.Move;
import mocks.MockModelValid;
import mocks.MockViewValid;
import model.Model;
import org.junit.Before;
import org.junit.Test;
import view.View;

/**
 * This is the test class for the command for Move player.
 * 
 */
public class MoveTest {

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
    Command c = new Move(1200, 1200);
    c.execute(m, v);
    assertEquals(
        "Inside method move. Id = 34512001200" + "Inside method "
            + "showSuccessMessage. Id = 345"
            + "Player moved!Inside method refresh. Id = 345",
        log.toString());
  }

}

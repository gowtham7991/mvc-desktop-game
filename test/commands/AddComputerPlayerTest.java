package commands;

import static org.junit.Assert.assertEquals;

import controller.commands.AddComputerPlayer;
import controller.commands.Command;
import mocks.MockModelValid;
import mocks.MockViewValid;
import model.Model;
import org.junit.Before;
import org.junit.Test;
import view.View;

/**
 * This is the test class for the command for adding a computer player to the
 * game.
 *
 */
public class AddComputerPlayerTest {

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
    Command c = new AddComputerPlayer();
    c.execute(m, v);
    assertEquals("Inside method addComputerPlayer. Id = 345"
        + "Inside method showSuccessMessage. Id = 345345"
        + "Player added!Inside method refresh. Id = 345", log.toString());
  }

}

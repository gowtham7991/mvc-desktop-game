package commands;

import static org.junit.Assert.assertEquals;

import controller.commands.Command;
import controller.commands.DisplayPlayerDescription;
import mocks.MockModelValid;
import mocks.MockViewValid;
import model.Model;
import org.junit.Before;
import org.junit.Test;
import view.View;

/**
 * This is the test class for the command for displaying player information.
 * 
 */
public class DisplayPlayerDescriptionTest {

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
    Command c = new DisplayPlayerDescription();
    c.execute(m, v);
    assertEquals("Inside method displayPlayerDescription. Id = 345"
        + "Inside method showSuccessMessage. Id = 345345"
        + "Player detailsInside method refresh. Id = 345", log.toString());
  }

}

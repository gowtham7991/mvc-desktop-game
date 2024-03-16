package commands;

import static org.junit.Assert.assertEquals;

import controller.commands.Attack;
import controller.commands.Command;
import mocks.MockModelValid;
import mocks.MockViewValid;
import model.Model;
import org.junit.Before;
import org.junit.Test;
import view.View;

/**
 * This is the test class for the command for attacking target.
 * 
 */
public class AttackTest {

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
    Command c = new Attack();
    c.execute(m, v);
    assertEquals(
        "Inside method getItemsOfPlayerInTurn. Id = 345"
            + "Inside method openPrompt. Id = 345Choose an item[item, poke]"
            + "Inside method attack. Id = 345345Inside method "
            + "showSuccessMessage. Id = 345345Target attacked!" + "Inside method refresh. Id = 345",
        log.toString());
  }

}

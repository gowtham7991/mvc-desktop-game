package commands;

import static org.junit.Assert.assertEquals;

import controller.commands.Command;
import controller.commands.PickUpItem;
import mocks.MockModelValid;
import mocks.MockViewValid;
import model.Model;
import org.junit.Before;
import org.junit.Test;
import view.View;

/**
 * This is the test class for the command to Pick up an item.
 * 
 */
public class PickUpItemTest {

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
    Command c = new PickUpItem();
    c.execute(m, v);
    assertEquals(
        "Inside method getItemsInCurrentSpace. Id = 345"
        + "Inside method openPrompt. Id = 345Pick an "
        + "item[item]Inside method pickUpItem. Id = 345345"
        + "Inside method showSuccessMessage. Id = 345"
        + "Item picked up!Inside method refresh. Id = 345",
        log.toString());
  }

}

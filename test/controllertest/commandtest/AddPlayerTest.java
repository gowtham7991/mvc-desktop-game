package controllertest.commandtest;

import static org.junit.Assert.assertEquals;

import controller.Command;
import controller.commands.AddPlayer;
import controllertest.mocks.MockModel;
import java.io.StringReader;
import java.util.Scanner;
import model.Model;
import org.junit.Test;

/**
 * Test suite for the add normal player command.
 */
public class AddPlayerTest {

  @Test
  public void testAddPlayer() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("Gowtham\nArmory\n5\n");
    StringBuilder log = new StringBuilder();
    Scanner scan = new Scanner(in);
    Model model = new MockModel(log, "123456789");
    Command c = new AddPlayer(scan, out);
    c.execute(model);

    StringBuilder logBuilder = new StringBuilder();
    StringBuilder outBuilder = new StringBuilder();

    String logExpected = logBuilder.append("Player Gowtham added to Armory\n")
        .append("Max items limit : 5.\n").toString();
    String outExpected = outBuilder.append("Enter your name: \n")
        .append("Enter the Space you wish to enter: \n").append("Enter the item limit: \n")
        .append("123456789\n").toString();

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }
}
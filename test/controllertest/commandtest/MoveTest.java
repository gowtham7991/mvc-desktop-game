package controllertest.commandtest;

import static org.junit.Assert.assertEquals;

import controller.Command;
import controller.commands.Move;
import controllertest.mocks.MockModel;
import java.io.StringReader;
import java.util.Scanner;
import model.Model;
import org.junit.Test;

/**
 * Test suite for the move player command.
 */
public class MoveTest {

  @Test
  public void testMovePlayer() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("Dining Hall\n");
    StringBuilder log = new StringBuilder();
    Scanner scan = new Scanner(in);
    Model model = new MockModel(log, "123456789");
    Command c = new Move(scan, out);
    c.execute(model);

    StringBuilder logBuilder = new StringBuilder();
    StringBuilder outBuilder = new StringBuilder();

    String logExpected = logBuilder.append("Displaying neighbours of the player.\n")
        .append("Player moved to space Dining Hall\n").toString();
    String outExpected = outBuilder.append("Neighbouring spaces : \n").append("123456789\n")
        .append("Enter the Space you wish to enter: \n").append("123456789\n").toString();

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }
}
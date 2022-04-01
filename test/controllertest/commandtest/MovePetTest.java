package controllertest.commandtest;

import static org.junit.Assert.assertEquals;

import controller.Command;
import controller.commands.Move;
import controller.commands.MovePet;
import controllertest.mocks.MockModel;
import java.io.StringReader;
import java.util.Scanner;
import model.Model;
import org.junit.Test;

/**
 * Test suite for the move player command.
 */
public class MovePetTest {

  @Test
  public void testMovePet() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("Dining Hall\n");
    StringBuilder log = new StringBuilder();
    Scanner scan = new Scanner(in);
    Model model = new MockModel(log, "123456789");
    Command c = new MovePet(scan, out);
    c.execute(model);

    StringBuilder logBuilder = new StringBuilder();
    StringBuilder outBuilder = new StringBuilder();

    String logExpected = logBuilder.append("List of spaces.\n")
        .append("Pet is moved to Dining Hall.\n").toString();
    String outExpected = outBuilder.append("Available spaces : \n").append("123456789\n")
        .append("Enter the Space you wish to move the pet : \n").append("123456789\n").toString();

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }
}
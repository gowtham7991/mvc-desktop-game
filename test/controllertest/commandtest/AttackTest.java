package controllertest.commandtest;

import static org.junit.Assert.assertEquals;

import controller.Command;
import controller.commands.Attack;
import controller.commands.Move;
import controllertest.mocks.MockModel;
import java.io.StringReader;
import java.util.Scanner;
import model.Model;
import org.junit.Test;

/**
 * Test suite for the move player command.
 */
public class AttackTest {

  @Test
  public void testAttackTarget() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("Item1\n");
    StringBuilder log = new StringBuilder();
    Scanner scan = new Scanner(in);
    Model model = new MockModel(log, "123456789");
    Command c = new Attack(scan, out);
    c.execute(model);

    StringBuilder logBuilder = new StringBuilder();
    StringBuilder outBuilder = new StringBuilder();

    String logExpected = logBuilder.append("Displaying the items of player.\n")
        .append("Target is attacked using Item1.\n").toString();
    String outExpected = outBuilder.append("Available items : \n").append("123456789\n")
        .append("Enter the item you would want to use or use poke: \n")
        .append("123456789\n").toString();

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }
}
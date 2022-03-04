package controllertest.commandtest;

import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.Command;
import controller.commands.AddComputerPlayer;
import controller.commands.Move;
import controllertest.MockModel;
import model.Model;

import static org.junit.Assert.assertEquals;

public class MoveTest {

  @Test
  public void testMovePlayer () {
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
            .append("Player moved to space Dining Hall\n")
            .toString();
    String outExpected = outBuilder.append("123456789\n")
            .append("Enter the Space you wish to enter: \n")
            .append("123456789")
            .toString();

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }
}
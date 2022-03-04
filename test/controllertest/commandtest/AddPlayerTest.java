package controllertest.commandtest;

import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.Command;
import controller.commands.AddComputerPlayer;
import controller.commands.AddPlayer;
import controllertest.MockModel;
import model.Model;

import static org.junit.Assert.assertEquals;

public class AddPlayerTest {

  @Test
  public void testAddPlayer () {
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
            .append("Max items limit : 5.\n")
            .toString();
    String outExpected = outBuilder
            .append("Enter your name: \n")
            .append("Enter the Space you wish to enter: \n")
            .append("Enter the item limit: \n")
            .append("123456789\n")
            .toString();

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }
}
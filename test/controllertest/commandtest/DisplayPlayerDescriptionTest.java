package controllertest.commandtest;

import static org.junit.Assert.assertEquals;

import controller.Command;
import controller.commands.DisplayPlayerDescription;
import controllertest.mocks.MockModel;
import java.io.StringReader;
import java.util.Scanner;
import model.Model;
import org.junit.Test;

public class DisplayPlayerDescriptionTest {

  @Test
  public void testDisplayPlayerDescription() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("Player0\n");
    StringBuilder log = new StringBuilder();
    Scanner scan = new Scanner(in);
    Model model = new MockModel(log, "123456789");
    Command c = new DisplayPlayerDescription(scan, out);
    c.execute(model);

    StringBuilder logBuilder = new StringBuilder();
    StringBuilder outBuilder = new StringBuilder();

    String logExpected = "Displaying player description.\n";
    String outExpected = logBuilder.append("Enter a player's name: \n").append("123456789")
        .toString();

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }
}
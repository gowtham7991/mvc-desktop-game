package controllertest.commandtest;

import static org.junit.Assert.assertEquals;

import controller.Command;
import controller.commands.AddComputerPlayer;
import controllertest.mocks.MockModel;
import java.io.StringReader;
import java.util.Scanner;
import model.Model;
import org.junit.Test;

public class AddComputerPlayerTest {

  @Test
  public void testAddComputerPlayer() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("Gowtham\nArmory\n");
    StringBuilder log = new StringBuilder();
    Scanner scan = new Scanner(in);
    Model model = new MockModel(log, "123456789");
    Command c = new AddComputerPlayer(scan, out);
    c.execute(model);

    String expected = "Computer player added.\n";
    assertEquals(expected, log.toString());
    assertEquals("123456789\n", out.toString());
  }
}
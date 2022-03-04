package controllertest.commandtest;

import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.Command;
import controller.commands.AddComputerPlayer;
import controller.commands.PickUpItem;
import controllertest.MockModel;
import model.Model;

import static org.junit.Assert.assertEquals;

public class PickUpItemTest {

  @Test
  public void testPickUpItem () {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("Gowtham\nArmory\n");
    StringBuilder log = new StringBuilder();
    Scanner scan = new Scanner(in);
    Model model = new MockModel(log, "123456789");
    Command c = new PickUpItem(scan, out);
    c.execute(model);

    StringBuilder logBuilder = new StringBuilder();
    StringBuilder outBuilder = new StringBuilder();

    String logExpected = logBuilder.append("Displaying the items in current space\n")
            .append("Player has picked up the item.\n")
            .toString();
    String outExpected = outBuilder.append("Available items : 123456789\n")
            .append("Enter the item name: \n")
            .append("123456789")
            .toString();

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }
}
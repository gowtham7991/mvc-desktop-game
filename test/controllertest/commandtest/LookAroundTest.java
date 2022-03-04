package controllertest.commandtest;

import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.Command;
import controller.commands.AddComputerPlayer;
import controller.commands.LookAround;
import controllertest.MockModel;
import model.Model;

import static org.junit.Assert.assertEquals;

public class LookAroundTest {

  @Test
  public void testLookAround () {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("");
    StringBuilder log = new StringBuilder();
    Scanner scan = new Scanner(in);
    Model model = new MockModel(log, "123456789");
    Command c = new LookAround(scan, out);
    c.execute(model);

    String logExpected = "Looking around the space player is in.\n";
    String outExpected = "123456789";

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }
}
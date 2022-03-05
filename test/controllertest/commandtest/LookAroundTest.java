package controllertest.commandtest;

import static org.junit.Assert.assertEquals;

import controller.Command;
import controller.commands.LookAround;
import controllertest.mocks.MockModel;
import java.io.StringReader;
import java.util.Scanner;
import model.Model;
import org.junit.Test;

public class LookAroundTest {

  @Test
  public void testLookAround() {
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
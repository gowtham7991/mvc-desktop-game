package controllertest.commandtest;

import static org.junit.Assert.assertEquals;

import controller.Command;
import controller.commands.CreateLayout;
import controller.commands.DisplayPlayerDescription;
import controllertest.mocks.MockModel;
import java.io.StringReader;
import java.util.Scanner;
import model.Model;
import org.junit.Test;

/**
 * Test suite for the display player description command.
 */
public class CreateLayoutTest {

  @Test
  public void testCreateLayout() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("Player0\n");
    StringBuilder log = new StringBuilder();
    Scanner scan = new Scanner(in);
    Model model = new MockModel(log, "123456789");
    Command c = new CreateLayout(scan, out);
    c.execute(model);

    StringBuilder logBuilder = new StringBuilder();
    StringBuilder outBuilder = new StringBuilder();

    String logExpected = "Created the graphical representation for the world.\n";
    String outExpected = logBuilder.append("Could not create a layout!\n").toString();

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }
}
package controllertest.commandtest;

import static org.junit.Assert.assertEquals;

import controller.Command;
import controller.commands.GetInfoOfSpace;
import controllertest.mocks.MockModel;
import java.io.StringReader;
import java.util.Scanner;
import model.Model;
import org.junit.Test;

public class GetInfoOfSpaceTest {

  @Test
  public void testGetInfoOfSpace() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("Armory\n");
    StringBuilder log = new StringBuilder();
    Scanner scan = new Scanner(in);
    Model model = new MockModel(log, "123456789");
    Command c = new GetInfoOfSpace(scan, out);
    c.execute(model);

    StringBuilder logBuilder = new StringBuilder();
    StringBuilder outBuilder = new StringBuilder();

    String logExpected = "Displaying information of Armory\n";
    String outExpected = outBuilder.append("Enter the name of the space: \n").append("123456789")
        .toString();

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }
}
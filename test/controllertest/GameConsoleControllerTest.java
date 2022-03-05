package controllertest;

import static org.junit.Assert.assertEquals;

import controller.GameConsoleController;
import controller.GameController;
import controllertest.mocks.FailingAppendable;
import controllertest.mocks.MockModel;
import java.io.StringReader;
import model.Model;
import org.junit.Test;

/**
 * Test suite for the game console controller. The controller tests the functionality using the
 * mock model. The commands from the controller are tested using the output from mock.
 */
public class GameConsoleControllerTest {

  @Test
  public void testStartWithModel() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    outBuilder.append("\n");
    outBuilder.append("### Welcome to 123456 ###\n");
    outBuilder.append("\n");
    outBuilder.append("### Add Players ###\n");
    outBuilder.append("\n");
    outBuilder.append("addplayer - to add a new human player\n");
    outBuilder.append("addcomputerplayer - to add a new computer player\n");
    outBuilder.append("start - to start the game\n");
    outBuilder.append("Enter your name: \n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("Enter the item limit: \n");
    outBuilder.append("123456\n");
    outBuilder.append("### Game has started ###\n");
    outBuilder.append("\n");
    outBuilder.append("Players : \n");
    outBuilder.append("123456--- Available Commands ---\n");
    outBuilder.append("\n");
    outBuilder.append("layout - generate a layout of the game\n");
    outBuilder.append("playerdesc - Displays the description of a player\n");
    outBuilder.append("getinfo - Displays information about a space\n");
    outBuilder.append("lookaround - Displays the details of a");
    outBuilder.append(" specific space the player currently is in\n");
    outBuilder.append("move - Move to the neighbouring space\n");
    outBuilder.append("pickup - Pickup an item from the current space\n");
    outBuilder.append("quit - quit the game\n");
    outBuilder.append("\n");
    outBuilder.append("123456### User quit the game ###\n");
    outBuilder.append("GAME HAS ENDED!\n");
    String outExpected = outBuilder.toString();

    assertEquals(outExpected, out.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartWithNullModel() {
    Appendable log = new FailingAppendable();
    StringBuffer out = new StringBuffer();
    int maxTurns = 20;
    Model model = new MockModel(log, "123456");
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\nmove\nDining Hall\n");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailingAppendable() {
    Appendable log = new FailingAppendable();
    StringBuffer out = new StringBuffer();
    int maxTurns = 20;
    Model model = new MockModel(log, "123456");
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\nmove\nDining Hall\n");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);
  }

  @Test
  public void testMovePlayer() {
    StringBuilder outBuilder = new StringBuilder();
    outBuilder.append("\n");
    outBuilder.append("### Welcome to 123456 ###\n");
    outBuilder.append("\n");
    outBuilder.append("### Add Players ###\n");
    outBuilder.append("\n");
    outBuilder.append("addplayer - to add a new human player\n");
    outBuilder.append("addcomputerplayer - to add a new computer player\n");
    outBuilder.append("start - to start the game\n");
    outBuilder.append("Enter your name: \n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("Enter the item limit: \n");
    outBuilder.append("123456\n");
    outBuilder.append("### Game has started ###\n");
    outBuilder.append("\n");
    outBuilder.append("Players : \n");
    outBuilder.append("123456--- Available Commands ---\n");
    outBuilder.append("\n");
    outBuilder.append("layout - generate a layout of the game\n");
    outBuilder.append("playerdesc - Displays the description of a player\n");
    outBuilder.append("getinfo - Displays information about a space\n");
    outBuilder.append("lookaround - Displays the details of a ");
    outBuilder.append("specific space the player currently is in\n");
    outBuilder.append("move - Move to the neighbouring space\n");
    outBuilder.append("pickup - Pickup an item from the current space\n");
    outBuilder.append("quit - quit the game\n");
    outBuilder.append("\n");
    outBuilder.append("123456123456\n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("123456123456### User quit the game ###\n");
    outBuilder.append("GAME HAS ENDED!\n");
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\nmove\nDining Hall\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);
    String outExpected = outBuilder.toString();

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testPickUpItem() {
    StringBuilder outBuilder = new StringBuilder();
    outBuilder.append("\n");
    outBuilder.append("### Welcome to 123456 ###\n");
    outBuilder.append("\n");
    outBuilder.append("### Add Players ###\n");
    outBuilder.append("\n");
    outBuilder.append("addplayer - to add a new human player\n");
    outBuilder.append("addcomputerplayer - to add a new computer player\n");
    outBuilder.append("start - to start the game\n");
    outBuilder.append("Enter your name: \n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("Enter the item limit: \n");
    outBuilder.append("123456\n");
    outBuilder.append("### Game has started ###\n");
    outBuilder.append("\n");
    outBuilder.append("Players : \n");
    outBuilder.append("123456--- Available Commands ---\n");
    outBuilder.append("\n");
    outBuilder.append("layout - generate a layout of the game\n");
    outBuilder.append("playerdesc - Displays the description of a player\n");
    outBuilder.append("getinfo - Displays information about a space\n");
    outBuilder.append("lookaround - Displays the details of a ");
    outBuilder.append("specific space the player currently is in\n");
    outBuilder.append("move - Move to the neighbouring space\n");
    outBuilder.append("pickup - Pickup an item from the current space\n");
    outBuilder.append("quit - quit the game\n");
    outBuilder.append("\n");
    outBuilder.append("123456Available items : 123456\n");
    outBuilder.append("Enter the item name: \n");
    outBuilder.append("123456123456### User quit the game ###\n");
    outBuilder.append("GAME HAS ENDED!\n");
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\npickup\nRevolver\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);
    String outExpected = outBuilder.toString();

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testLookAround() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\nlookaround\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    outBuilder.append("\n");
    outBuilder.append("### Welcome to 123456 ###\n");
    outBuilder.append("\n");
    outBuilder.append("### Add Players ###\n");
    outBuilder.append("\n");
    outBuilder.append("addplayer - to add a new human player\n");
    outBuilder.append("addcomputerplayer - to add a new computer player\n");
    outBuilder.append("start - to start the game\n");
    outBuilder.append("Enter your name: \n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("Enter the item limit: \n");
    outBuilder.append("123456\n");
    outBuilder.append("### Game has started ###\n");
    outBuilder.append("\n");
    outBuilder.append("Players : \n");
    outBuilder.append("123456--- Available Commands ---\n");
    outBuilder.append("\n");
    outBuilder.append("layout - generate a layout of the game\n");
    outBuilder.append("playerdesc - Displays the description of a player\n");
    outBuilder.append("getinfo - Displays information about a space\n");
    outBuilder.append("lookaround - Displays the details of a ");
    outBuilder.append("specific space the player currently is in\n");
    outBuilder.append("move - Move to the neighbouring space\n");
    outBuilder.append("pickup - Pickup an item from the current space\n");
    outBuilder.append("quit - quit the game\n");
    outBuilder.append("\n");
    outBuilder.append("123456123456123456### User quit the game ###\n");
    outBuilder.append("GAME HAS ENDED!\n");
    String outExpected = outBuilder.toString();

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testDisplayPlayerDescription() {
    StringBuffer out = new StringBuffer();
    StringBuilder inBuilder = new StringBuilder();
    inBuilder.append("addplayer\nGowtham\nArmory\n5\naddplayer");
    inBuilder.append("\nGowtham1\nArmory\n5\nstart\nplayerdesc\nGowtham1\n");
    Readable in = new StringReader(inBuilder.toString());
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    outBuilder.append("\n");
    outBuilder.append("### Welcome to 123456 ###\n");
    outBuilder.append("\n");
    outBuilder.append("### Add Players ###\n");
    outBuilder.append("\n");
    outBuilder.append("addplayer - to add a new human player\n");
    outBuilder.append("addcomputerplayer - to add a new computer player\n");
    outBuilder.append("start - to start the game\n");
    outBuilder.append("Enter your name: \n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("Enter the item limit: \n");
    outBuilder.append("123456\n");
    outBuilder.append("Enter your name: \n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("Enter the item limit: \n");
    outBuilder.append("123456\n");
    outBuilder.append("### Game has started ###\n");
    outBuilder.append("\n");
    outBuilder.append("Players : \n");
    outBuilder.append("123456--- Available Commands ---\n");
    outBuilder.append("\n");
    outBuilder.append("layout - generate a layout of the game\n");
    outBuilder.append("playerdesc - Displays the description of a player\n");
    outBuilder.append("getinfo - Displays information about a space\n");
    outBuilder.append("lookaround - Displays the details of a ");
    outBuilder.append("specific space the player currently is in\n");
    outBuilder.append("move - Move to the neighbouring space\n");
    outBuilder.append("pickup - Pickup an item from the current space\n");
    outBuilder.append("quit - quit the game\n");
    outBuilder.append("\n");
    outBuilder.append("123456Enter a player's name: \n");
    outBuilder.append("123456123456### User quit the game ###\n");
    outBuilder.append("GAME HAS ENDED!\n");
    String outExpected = outBuilder.toString();

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testGetInfoOfSpace() {
    StringBuffer out = new StringBuffer();
    StringBuilder inBuilder = new StringBuilder();
    inBuilder.append("addplayer\nGowtham\nArmory\n5\naddplayer");
    inBuilder.append("\nGowtham1\nArmory\n5\nstart\ngetinfo\nArmory\n");
    Readable in = new StringReader(inBuilder.toString());
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    outBuilder.append("\n");
    outBuilder.append("### Welcome to 123456 ###\n");
    outBuilder.append("\n");
    outBuilder.append("### Add Players ###\n");
    outBuilder.append("\n");
    outBuilder.append("addplayer - to add a new human player\n");
    outBuilder.append("addcomputerplayer - to add a new computer player\n");
    outBuilder.append("start - to start the game\n");
    outBuilder.append("Enter your name: \n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("Enter the item limit: \n");
    outBuilder.append("123456\n");
    outBuilder.append("Enter your name: \n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("Enter the item limit: \n");
    outBuilder.append("123456\n");
    outBuilder.append("### Game has started ###\n");
    outBuilder.append("\n");
    outBuilder.append("Players : \n");
    outBuilder.append("123456--- Available Commands ---\n");
    outBuilder.append("\n");
    outBuilder.append("layout - generate a layout of the game\n");
    outBuilder.append("playerdesc - Displays the description of a player\n");
    outBuilder.append("getinfo - Displays information about a space\n");
    outBuilder.append("lookaround - Displays the details of a ");
    outBuilder.append("specific space the player currently is in\n");
    outBuilder.append("move - Move to the neighbouring space\n");
    outBuilder.append("pickup - Pickup an item from the current space\n");
    outBuilder.append("quit - quit the game\n");
    outBuilder.append("\n");
    outBuilder.append("123456Enter the name of the space: \n");
    outBuilder.append("123456123456### User quit the game ###\n");
    outBuilder.append("GAME HAS ENDED!\n");
    String outExpected = outBuilder.toString();

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testAddPlayer() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader(
        "addplayer\nGowtham\nArmory\n5\naddplayer\nGowtham1\nArmory\n5\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    outBuilder.append("\n");
    outBuilder.append("### Welcome to 123456 ###\n");
    outBuilder.append("\n");
    outBuilder.append("### Add Players ###\n");
    outBuilder.append("\n");
    outBuilder.append("addplayer - to add a new human player\n");
    outBuilder.append("addcomputerplayer - to add a new computer player\n");
    outBuilder.append("start - to start the game\n");
    outBuilder.append("Enter your name: \n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("Enter the item limit: \n");
    outBuilder.append("123456\n");
    outBuilder.append("Enter your name: \n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("Enter the item limit: \n");
    outBuilder.append("123456\n");
    outBuilder.append("### Game has started ###\n");
    outBuilder.append("\n");
    outBuilder.append("Players : \n");
    outBuilder.append("123456--- Available Commands ---\n");
    outBuilder.append("\n");
    outBuilder.append("layout - generate a layout of the game\n");
    outBuilder.append("playerdesc - Displays the description of a player\n");
    outBuilder.append("getinfo - Displays information about a space\n");
    outBuilder.append("lookaround - Displays the details of a ");
    outBuilder.append("specific space the player currently is in\n");
    outBuilder.append("move - Move to the neighbouring space\n");
    outBuilder.append("pickup - Pickup an item from the current space\n");
    outBuilder.append("quit - quit the game\n");
    outBuilder.append("\n");
    outBuilder.append("123456### User quit the game ###\n");
    outBuilder.append("GAME HAS ENDED!\n");
    String outExpected = outBuilder.toString();

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testAddComputerPlayer() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addcomputerplayer\naddcomputerplayer\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    outBuilder.append("\n");
    outBuilder.append("### Welcome to 123456 ###\n");
    outBuilder.append("\n");
    outBuilder.append("### Add Players ###\n");
    outBuilder.append("\n");
    outBuilder.append("addplayer - to add a new human player\n");
    outBuilder.append("addcomputerplayer - to add a new computer player\n");
    outBuilder.append("start - to start the game\n");
    outBuilder.append("123456\n");
    outBuilder.append("123456\n");
    outBuilder.append("### Game has started ###\n");
    outBuilder.append("\n");
    outBuilder.append("Players : \n");
    outBuilder.append("123456--- Available Commands ---\n");
    outBuilder.append("\n");
    outBuilder.append("layout - generate a layout of the game\n");
    outBuilder.append("playerdesc - Displays the description of a player\n");
    outBuilder.append("getinfo - Displays information about a space\n");
    outBuilder.append("lookaround - Displays the details of a ");
    outBuilder.append("specific space the player currently is in\n");
    outBuilder.append("move - Move to the neighbouring space\n");
    outBuilder.append("pickup - Pickup an item from the current space\n");
    outBuilder.append("quit - quit the game\n");
    outBuilder.append("\n");
    outBuilder.append("123456### User quit the game ###\n");
    outBuilder.append("GAME HAS ENDED!\n");
    String outExpected = outBuilder.toString();

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testMoveAfterMaxTurns() {
    StringBuffer out = new StringBuffer();
    StringBuilder inBuilder = new StringBuilder();
    inBuilder.append("addplayer\nGowtham\nArmory\n5\nstart\nmove\nDining Hall");
    inBuilder.append("\nmove\nDrawing Room\nmove\nLilac Room\nmove\nBilliard Room\n");
    Readable in = new StringReader(inBuilder.toString());
    StringBuilder log = new StringBuilder();
    int maxTurns = 3;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    outBuilder.append("\n");
    outBuilder.append("### Welcome to 123456 ###\n");
    outBuilder.append("\n");
    outBuilder.append("### Add Players ###\n");
    outBuilder.append("\n");
    outBuilder.append("addplayer - to add a new human player\n");
    outBuilder.append("addcomputerplayer - to add a new computer player\n");
    outBuilder.append("start - to start the game\n");
    outBuilder.append("Enter your name: \n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("Enter the item limit: \n");
    outBuilder.append("123456\n");
    outBuilder.append("### Game has started ###\n");
    outBuilder.append("\n");
    outBuilder.append("Players : \n");
    outBuilder.append("123456--- Available Commands ---\n");
    outBuilder.append("\n");
    outBuilder.append("layout - generate a layout of the game\n");
    outBuilder.append("playerdesc - Displays the description of a player\n");
    outBuilder.append("getinfo - Displays information about a space\n");
    outBuilder.append("lookaround - Displays the details of a ");
    outBuilder.append("specific space the player currently is in\n");
    outBuilder.append("move - Move to the neighbouring space\n");
    outBuilder.append("pickup - Pickup an item from the current space\n");
    outBuilder.append("quit - quit the game\n");
    outBuilder.append("\n");
    outBuilder.append("123456123456\n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("123456123456123456\n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("123456123456123456\n");
    outBuilder.append("Enter the Space you wish to enter: \n");
    outBuilder.append("123456123456### Max turns in the reached ###\n");
    outBuilder.append("GAME HAS ENDED!\n");
    String outExpected = outBuilder.toString();

    assertEquals(outExpected, out.toString());
  }
}